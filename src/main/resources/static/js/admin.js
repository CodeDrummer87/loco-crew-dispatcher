// получаем ссылки на модальные окна по их Id на странице
const profileModal = document.getElementById('profileModal'); // окно редактирования профиля
const employeesModal = document.getElementById('employeesModal'); // список нарядчиков
const workScheduleModal = document.getElementById('workScheduleModal'); // график работы нарядчиков
const orderFileModal = document.getElementById('orderFileModal'); // таблица файла наряда

// получаем ссылки на кнопки в меню (слева), которые открывают соответствующие окна
const editProfileBtn = document.getElementById('editProfileBtn');
const employeesBtn = document.getElementById('employeesBtn');
const workScheduleBtn = document.getElementById('workScheduleBtn');
const orderFileBtn = document.getElementById('orderFileBtn');

// кнопка Очистить в окне редактирования профиля
const clearProfileBtn = document.getElementById('clearProfileBtn');

// кнопка Закрыть список в окне Сотрудники
const closeEmployeesListBtn = document.getElementById('closeEmployeesListBtn');

// получаем крестики для закрытия модальных окон
const closeButtons = document.querySelectorAll('.close');

// переменная для хранения ссылки на текущее открытое окно
// она нужна, чтобы при открытии нового окна автоматически закрывать предыдущее
let currentOpenModal = null;

// cобытие, которое срабатывает, когда вcя HTML-страница загрузилась
document.addEventListener('DOMContentLoaded', function () {

    // функция для открытия модального окна
    function openModal(modal, activeButton) {
        // закрываем предыдущее открытое модальное окно, если оно есть
        if (currentOpenModal) {
            closeModal(currentOpenModal);
        }

        // если окно уже открыто, то ничего не делаем
        if (modal.style.display === 'block') return;

        // показываем окно
        modal.style.display = 'block';
        modal.style.position = 'relative';

        // запоминаем текущее открытое окно
        currentOpenModal = modal;

        // устанавливаем активную (нажатую) кнопку
        setActiveButton(activeButton);
    }

    // функция для закрытия модального окна
    function closeModal(modal) {

        // скрываем окно (делаем невидимым)
        modal.style.display = 'none';

        // если закрываем текущее открытое окно, то обнуляем ссылку на него
        if (currentOpenModal === modal) {
            currentOpenModal = null;
        }
    }

    // функция для установки активности на конкретную кнопку
    function setActiveButton(activeButton) {
        removeActiveClassFromButtons();
        if (activeButton) {
            activeButton.classList.add('active');
        }
    }

    // функция для снятия активности со всех кнопок
    function removeActiveClassFromButtons() {
        // создаем массив кнопок
        const buttons = [editProfileBtn, employeesBtn, workScheduleBtn, orderFileBtn];

        // проходим по всем кнопкам
        for (let i = 0; i < buttons.length; i++) {
            // получаем текущую кнопку из массива
            let btn = buttons[i];

            // удаляем у кнопки класс active
            btn.classList.remove('active');
        }
    }

    // обработчики событий на кнопки меню
    // кнопка Редактировать профиль открывает окно profileModal
    editProfileBtn.onclick = function () {
        openModal(profileModal, editProfileBtn);
    };

    // кнопка Сотрудники открывает окно employeesModal
    employeesBtn.onclick = function () {
        openModal(employeesModal, employeesBtn);
    };

    // кнопка График работы нарядчиков открывает окно workScheduleModal
    workScheduleBtn.onclick = function () {
        openModal(workScheduleModal, workScheduleBtn);
    };

    // кнопка Файл наряда открывает окно orderFileModal
    orderFileBtn.onclick = function () {
        openModal(orderFileModal, orderFileBtn);
    };

    // функция для назначения обработчика закрытия на кнопку (крестик)
    function closeButton(button) {
        button.onclick = function handleClick() {
            // находим элемент с классом modal
            let modal = button.closest('.modal');

            // закрываем окно
            closeModal(modal);
        };
    }

    // проходим по всем крестикам и для каждого вызываем closeButton
    // у нас есть несколько крестиков, и каждый должен закрыть свое конкретное окно
    // без цикла мы не сможем связать каждую кнопку с её окном
    for (let i = 0; i < closeButtons.length; i++) {
        closeButton(closeButtons[i]);
    }

    // обработчик для кнопки Очистить в окне редактирования профиля
    clearProfileBtn.onclick = function () {
        // находим все инпуты
        let inputs = document.querySelectorAll('input');

        // перебираем каждый инпут
        for (let input of inputs) {
            // делаем значение инпута пустой строкой
            input.value = '';
        }
    };

    // обработчик для кнопки Закрыть список в окне Сотрудники
    if (closeEmployeesListBtn) {
        closeEmployeesListBtn.onclick = function () {
            // закрываем окно сотрудников
            closeModal(employeesModal);
        };
    }
});