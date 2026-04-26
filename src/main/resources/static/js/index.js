const employee_count = document.getElementById("s_employee_count");
const positionItems = document.querySelectorAll('.position-item');
const tchm_at_work_count = document.getElementById('s_tchm_at_work_count');
const tchpm_at_work_count = document.getElementById('s_tchpm_at_work_count');
const tchm_not_at_work_count = document.getElementById('s_tchm_not_at_work_count');
const tchpm_not_at_work_count = document.getElementById('s_tchpm_not_at_work_count');

const locomotive_crew_count = document.getElementById('s_locomotive_crew_count');
const single_face_count = document.getElementById('s_single_face_count');

document.addEventListener('DOMContentLoaded', async function () {
    await getEmployeeCount();
    await getAllPositionsCount();
    await getTchmAtWork();
    await getTchpmAtWork();
    await getTchmNotAtWork();
    await getTchpmNotAtWork()

    await getLocomotiveCrewCount();
    await getSingleDriverCount();
});

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

async function getEmployeeCountByPositionAndStatus(positionId, statusId) {
    try {
        const response = await fetch(`http://localhost:8080/api/v1/employees/count/position/${positionId}/state/${statusId}`);

        if (!response.ok) {
            throw new Error(`HTTP Error! status: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error(`Ошибка выполнения запроса (счётчик должности ${positionId} со статусом ${statusId}):` + error);
    }
}

async function getTchmAtWork() {
    tchm_at_work_count.innerText = await getEmployeeCountByPositionAndStatus(1, 0);
}

async function getTchpmAtWork() {
    tchpm_at_work_count.innerText = await getEmployeeCountByPositionAndStatus(2, 0);
}

async function getTchmNotAtWork() {
    let total = 0;
    for (const statusId of [1, 2, 3, 4, 5, 6, 7, 8]) {
        total += await getEmployeeCountByPositionAndStatus(1, statusId);
    }
    tchm_not_at_work_count.innerText = total;
}

async function getTchpmNotAtWork() {
    let total = 0;
    for (const statusId of [1, 2, 3, 4, 5, 6, 7, 8]) {
        total += await getEmployeeCountByPositionAndStatus(2, statusId)
    }
    tchpm_not_at_work_count.innerText = total;
}

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

async function getSingleDriverCount() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/locomotive-crews/count/single-driver');

        if (!response.ok) {
            throw new Error(`HTTP Error! status: ${response.status}`);
        }

        single_face_count.innerText = await response.json();

    } catch (error) {
        console.error("Ошибка выполнения запроса счетчика бригад в одно лицо: " + error);
    }
}