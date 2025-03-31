document.getElementById("factoryForm").addEventListener("input", validate);
document.getElementById("factoryForm").addEventListener("submit", async function (event) {
	event.preventDefault(); // Запобігає перезавантаженню сторінки

	console.log("function submit called!");

	const factoryData = {
		name: document.getElementById("Name").value,
		produced: document.getElementById("Produced").value,
		sold: document.getElementById("Sold").value,
		date_made: document.getElementById("date_made").value
	};

	try {
		const response = await fetch("/factories", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(factoryData)
		});

		if (response.ok) {
			alert("Factory created successfully!");
			document.getElementById("factoryForm").reset();
			validate(); // Приховуємо кнопку після успішного сабміту
		} else {
			alert("Error creating factory.");
		}
	} catch (error) {
		console.error("Error:", error);
		alert("An error occurred.");
	}
});

function validate() {
	let Name = document.getElementById("Name").value.trim();
	let Produced = document.getElementById("Produced").value.trim();
	let Sold = document.getElementById("Sold").value.trim();
	let DateMade = document.getElementById("date_made").value.trim();
	let submitBtn = document.getElementById("submitBtn");

	console.log({ Name, Produced, Sold, DateMade });

	if (Name !== "" && Produced !== "" && Sold !== "" && DateMade !== "") {
		submitBtn.style.display = 'block';
	} else {
		submitBtn.style.display = 'none';
	}
}
