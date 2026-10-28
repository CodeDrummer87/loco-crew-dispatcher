const AUTH_API = '/api/v1/auth';
const TOKEN_KEY = 'token';

/**

 Декодирует payload JWT без проверки подписи.
 Возвращает объект или null при ошибке.
 */
function parseJwt(token) {
    try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(
            atob(base64)
                .split('')
                .map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
                .join('')
        );
        return JSON.parse(jsonPayload);
    } catch (e) {
        return null;
    }
}
function displayMessage(paragraph,message, success) {
    paragraph.style.color = success ? 'yellowgreen' : 'indianred';
    paragraph.textContent = message;
    setTimeout(()=>{ paragraph.textContent = '\u00A0'; }, 4500);
}

