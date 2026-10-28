/**

 Возвращает payload токена или null, если токена нет / истёк / битый.

 При истечении удаляет токен.
 */
function getTokenPayload() {
    const token = localStorage.getItem(TOKEN_KEY);
    if (!token) return null;

    const payload = parseJwt(token);
    if (!payload) {
        localStorage.removeItem(TOKEN_KEY);
        return null;
    }

    if (payload.exp && payload.exp * 1000 < Date.now()) {
        localStorage.removeItem(TOKEN_KEY);
        return null;
    }

    return payload;
}

/**

 Требует авторизации. Если не авторизован — редирект на /login.
 Возвращает payload или null.
 */
function requireAuth() {
    const payload = getTokenPayload();
    if (!payload) {
        window.location.href = '/login';
        return null;
    }
    return payload;
}
/**

 Требует конкретной роли ('ROLE_ADMIN' | 'ROLE_USER').

 Если роль не та — редирект на соответствующую страницу.
 */
function requireRole(requiredRole) {
    const payload = requireAuth();
    if (!payload) return null;

    const role = payload.role || '';
    if (role !== requiredRole) {
        const target = role === 'ROLE_ADMIN' ? '/admin' : '/user';
        window.location.href = target;
        return null;
    }
    return payload;
}

/**

 Logout — удаляет токен и уводит на /login.
 */
function logout() {
    localStorage.removeItem(TOKEN_KEY);
    window.location.href = '/login';
}

/**

 Универсальный fetch с Authorization: Bearer .

 При 401/403 — logout.
 */
async function authFetch(url, options = {}) {
    const token = localStorage.getItem(TOKEN_KEY);
    const headers = {
        ...(options.headers || {})
    };

// Content-Type только если есть тело и он не задан явно
    if (options.body && !headers['Content-Type']) {
        headers['Content-Type'] = 'application/json';
    }
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const response = await fetch(url, { ...options, headers });

    if (response.status === 401 || response.status === 403) {
        logout();
        throw new Error('Сессия истекла');
    }

    return response;
}

/**

 Периодическая проверка токена (каждые 30 секунд).
 Как только истёк — редирект на /login.
 */
function startTokenWatcher() {
    setInterval(() => {
        if (!getTokenPayload()) {
            window.location.href = '/login';
        }
    }, 30000);
}