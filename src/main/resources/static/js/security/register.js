document.addEventListener('DOMContentLoaded', () => {
//.:: Только админ может регистрировать новых юзеров
    const payload = requireRole('ROLE_ADMIN');
    if (!payload) return;

    const registerBtn = document.getElementById('registerBtn');
    let regMessage = document.getElementById('regMessage');
    let regLogin = document.getElementById('regLogin');
    let regPassword = document.getElementById('regPassword');

    registerBtn.onclick = async () => {

        const username = regLogin.value.trim();
        const password = regPassword.value;
        const role = document.getElementById('roleSelect').value;

        if (!username || !password || !role) {
            displayMessage(regMessage, 'Заполните все поля', false);
            return;
        }

        try {
            const response = await authFetch(`${AUTH_API}/register`, {
                method: 'POST',
                body: JSON.stringify({ username, password, role })
            });

            if (!response.ok) {
                if (response.status === 409) {
                    displayMessage(regMessage, 'Пользователь с таким логином уже существует', false);
                    throw new Error('Пользователь с таким логином уже существует');
                }
                displayMessage(regMessage, 'Ошибка регистрации', false);
                throw new Error('Ошибка регистрации');
            }

            displayMessage(regMessage, 'Пользователь создан', true);
            regLogin.value = '';
            regPassword.value = '';
        } catch (err) {
            displayMessage(regMessage, "Ошибка регистрации пользователя", false);
            console.error("Ошибка регистрации: " + err.message);
        }
    }
});