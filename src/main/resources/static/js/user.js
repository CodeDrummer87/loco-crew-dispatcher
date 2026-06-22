// получаем ссылки на модальные окна по их Id на странице
const dashboardModal = document.getElementById('dashboardModal'); // окно дашборда
const employeesModal = document.getElementById('employeesModal'); // список сотрудников
const crewsModal = document.getElementById('crewsModal'); // список локомотивных бригад
const statisticsModal = document.getElementById('statisticsModal'); // окно статистики

// получаем ссылки на кнопки в меню (слева), которые открывают соответствующие окна
const dashboardBtn = document.getElementById('dashboardBtn');
const employeesBtn = document.getElementById('employeesBtn');
const crewsBtn = document.getElementById('crewsBtn');
const statisticsBtn = document.getElementById('statisticsBtn');

// получаем крестики для закрытия модальных окон
const closeButtons = document.querySelectorAll('.close');

// переменная для хранения ссылки на текущее открытое окно
// она нужна, чтобы при открытии нового окна автоматически закрывать предыдущее
let currentOpenModal = null;

const employeeTableBody = document.getElementById('employeesTableBody');

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
        const buttons = [dashboardBtn, employeesBtn, crewsBtn, statisticsBtn];

        // проходим по всем кнопкам
        for (let i = 0; i < buttons.length; i++) {
            // получаем текущую кнопку из массива
            let btn = buttons[i];

            // удаляем у кнопки класс active
            btn.classList.remove('active');
        }
    }

    // обработчики событий на кнопки меню
    // кнопка Дашборд открывает окно dashboardModal
    dashboardBtn.onclick = function () {
        openModal(dashboardModal, dashboardBtn);
    };

    // кнопка Сотрудники открывает окно employeesModal
    employeesBtn.onclick = async function () {
        openModal(employeesModal, employeesBtn);
        let employees = await getAllEmployees();
        employeeTableBody.innerHTML = '';
        for (let i = 0; i < employees.length; i++) {
            employeeTableBody.appendChild(createRow(employees[i]));
        }
    };

    // кнопка Бригады открывает окно crewsModal
    crewsBtn.onclick = function () {
        openModal(crewsModal, crewsBtn);
    };

    // кнопка Статистика открывает окно statisticsModal
    statisticsBtn.onclick = function () {
        openModal(statisticsModal, statisticsBtn);
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

    // Открываем окно дашборда по умолчанию
    if (dashboardModal) {
        dashboardModal.style.display = 'block';
        dashboardModal.style.position = 'relative';
        currentOpenModal = dashboardModal;
    }
    // делаем кнопку Дашборд активной
    if (dashboardBtn) {
        dashboardBtn.classList.add('active');
    }
});

async function getAllEmployees(){
    try {
        const response = await fetch("http://localhost:8080/api/v1/employees")

        if (!response.ok) {
            throw new Error(`HTTP Error! status: ${response.status}`);
        }

        const pageResult = await response.json();
        return Array.isArray(pageResult.content) ? pageResult.content : [];
    } catch (error) {
        console.error("Ошибка выполнения запроса на получение списка сотрудников: " + error);
    }

}

function createTd(value) {
    const td = document.createElement('td');
    td.innerText = value;
    return td;
}

function createRow(employee) {
    const tr = document.createElement('tr');
    tr.appendChild(createTd(employee.personnelNumber));
    tr.appendChild(createTd(employee.fullName));
    tr.appendChild(createTd(employee.positionAbbreviate));
    tr.appendChild(createTd(employee.statusTitle))
    return tr;
}