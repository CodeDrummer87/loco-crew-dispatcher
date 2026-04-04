const locomotive_crew_count = document.getElementById('s_locomotive_crew_count');
const employee_count = document.getElementById("s_employee_count");
const positionItems = document.querySelectorAll('.position-item');

document.addEventListener('DOMContentLoaded', async function () {
    await getLocomotiveCrewCount();
    await getEmployeeCount();
    await getAllPositionsCount();
});

async function getLocomotiveCrewCount() {
    try {
        const response = await fetch(`http://localhost:8080/api/v1/locomotive-crews/count`);

        if (!response.ok) {
            throw new Error(`HTTP Error! status: ${response.status}`);
        }

        locomotive_crew_count.innerText = await response.json();
    } catch (error) {
        console.error("Ошибка выполнения запроса (счётчик локомотивных бригад): " + error);
    }
}

async function getEmployeeCount() {
    try {
        const response = await fetch(`http://localhost:8080/api/v1/employees/count`);

        if (!response.ok) {
            throw new Error(`HTTP Error! status: ${response.status}`);
        }

        employee_count.innerText = await response.json();
    } catch (error) {
        console.error("Ошибка выполнения запроса (счётчик сотрудников): " + error);
    }
}

async function getEmployeeCountByPosition(positionId) {
    try {
        const response = await fetch(`http://localhost:8080/api/v1/employees/count/position/${positionId}`);

        if (!response.ok) {
            throw new Error(`HTTP Error! status: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error(`Ошибка выполнения запроса (счётчик сотрудников по должности ${positionId}): ` + error);
    }
}

async function getAllPositionsCount() {
    for (const item of positionItems) {
        const positionId = item.dataset.id;
        if (positionId) {
            const count = await getEmployeeCountByPosition(positionId);
            const positionCount = item.querySelector('.s_position_count');
            if (positionCount) {
                positionCount.innerText = count;
            }
        }
    }
}