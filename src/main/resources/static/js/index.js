//region Locomotive crew count
const locomotive_crew_count = document.getElementById('s_locomotive_crew_count');

document.addEventListener('DOMContentLoaded', async function () {
    const response = await fetch('http://localhost:8080/api/v1/locomotive-crews/count');
    locomotive_crew_count.innerText = await response.json();
});
//endregion