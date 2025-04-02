document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".edit-btn").forEach(button => {
        button.addEventListener("click", function () {
            const row = this.closest("tr");
            const inputs = row.querySelectorAll(".edit-input");
            const spans = row.querySelectorAll("span");

            if (this.textContent === "Edit") {
                spans.forEach(span => span.style.display = "none");
                inputs.forEach(input => input.style.display = "inline-block");
                this.textContent = "Save";
            } else {
                const id = row.dataset.id;
                const updatedData = {
                    name: inputs[0].value,
                    produced: inputs[1].value,
                    sold: inputs[2].value,
                    date_made: inputs[3].value
                };

                fetch(`/factories/update/${id}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(updatedData)
                }).then(response => {
                    if (response.ok) {
                        spans.forEach((span, i) => {
                            span.textContent = inputs[i].value;
                            span.style.display = "inline";
                            inputs[i].style.display = "none";
                        });
                        this.textContent = "Edit";
                    } else {
                        alert("Помилка оновлення.");
                    }
                }).catch(error => {
                    console.error("Error:", error);
                    alert("Помилка оновлення.");
                });
            }
        });
    });

    document.querySelectorAll(".delete-btn").forEach(button => {
        button.addEventListener("click", function () {
            const row = this.closest("tr");
            const id = row.dataset.id;

            fetch(`/factories/delete/${id}`, { method: "DELETE" })
                .then(response => {
                    if (response.ok) {
                        row.remove();
                    } else {
                        alert("Помилка видалення.");
                    }
                }).catch(error => {
                console.error("Error:", error);
                alert("Помилка видалення.");
            });
        });
    });
});