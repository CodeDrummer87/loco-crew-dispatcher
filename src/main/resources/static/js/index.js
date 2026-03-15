//region Locomotive crew count
const locomotive_crew_count = document.getElementById('s_locomotive_crew_count');

document.addEventListener('DOMContentLoaded', async function () {
    await getLocomotiveCrewCount();
});

async function getLocomotiveCrewCount() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/locomotive-crews/count');

        if (!response.ok) {
            throw new Error(`HTTP Error! status: ${response.status}`);
        }

        locomotive_crew_count.innerText = await response.json();
    } catch (error) {
        console.error("Ошибка выполнения запроса (счётчик локомотивных бригад): " + error);
    }
}
//endregion