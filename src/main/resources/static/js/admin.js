const contentArea = document.querySelector('.content-area');
const profileModal = document.getElementById('profileModal');
const editProfileBtn = document.getElementById('editProfileBtn');
const clearProfileBtn = document.getElementById('clearProfileBtn');
const closeButtons = document.querySelectorAll('.close');

document.addEventListener('DOMContentLoaded',  function (){
    function openModal(modal) {
        if (modal) {
            contentArea.appendChild(modal);
            modal.style.display = 'block';
            modal.style.position = 'relative';
        }
    }

    editProfileBtn.onclick = function() {
        openModal(profileModal);
    };

    function closeModal(modal) {
        if (modal) modal.style.display = 'none';
    }

    function closeButton(button) {
        button.onclick = function handleClick() {
            let modal = button.closest('.modal');
            closeModal(modal);
        };
    }
    for (let i = 0; i < closeButtons.length; i++) {
        closeButton(closeButtons[i]);
    }

    clearProfileBtn.onclick = function() {
        let inputs = document.querySelectorAll('input');
        for (let input of inputs) {
            input.value = '';
        }
    };
});