document.addEventListener('DOMContentLoaded', () => {
    const login_btn = document.getElementById('login_btn');
    const loginInput = document.getElementById('login');
    const passwordInput = document.getElementById('password');
    let currentMessage = document.getElementById('currentMessage');

//.:: Если токен уже есть — сразу редирект
    const existing = getTokenPayload();
    if (existing) {
        redirectByRole(existing);
        return;
    }


    login_btn.onclick = async () => {

        const username = loginInput.value.trim();
        const password = passwordInput.value;

        if (!username || !password) {
            displayMessage(currentMessage, 'Заполните все поля', false);
            return;
        }

        try {
            const response = await fetch(`${AUTH_API}/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            if (!response.ok) {
                displayMessage(currentMessage, 'Неверный логин или пароль', false);
                throw new Error('Неверный логин или пароль');
            }

            const data = await response.json();
            // data = { token: "..." }

            localStorage.setItem('token', data.token);

            const payload = parseJwt(data.token);
            redirectByRole(payload);

        } catch (err) {
            displayMessage(currentMessage, err.message, false);
            console.error('Ошибка логина: ' + err.message);
        }
    }
});

/**

 Редирект в зависимости от роли в payload.
 */
function redirectByRole(payload) {
    console.log("role is " + payload.role);
    if (!payload) {
        localStorage.removeItem('token');
        window.location.href = '/login';
        return;
    }

    if (payload.exp && payload.exp * 1000 < Date.now()) {
        localStorage.removeItem('token');
        window.location.href = '/login';
        return;
    }

    if (payload.role === 'ROLE_ADMIN') {
        window.location.href = '/admin';
    } else {
        window.location.href = '/user';
    }
}