document.addEventListener("DOMContentLoaded", function() {
    let tableBody = document.getElementById("table-body");
    let rowCount = 5; // Количество пустых строк

    for (let i = 0; i < rowCount; i++) {
        let row = document.createElement("tr");
        for (let j = 0; j < 7; j++) {
            let cell = document.createElement("td");
            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }
});