document.getElementById("prductsForm").addEventListener("input", validate);
document.getElementById("prductsFrom").addEventListener("submit", async function (event) {
	event.preventDefault(); // Запобігає перезавантаженню сторінки

	console.log("function submit called!");

	const productsData = {
		product_name: document.getElementById("product_name").value,
		product_code: document.getElementById("product_code").value,
		cost_price: document.getElementById("cost_price").value,
		selling_price: document.getElementById("selling_price").value,
		date_made: document.getElementById("date_made").value,
		factories_id: document.getElementById("factories_id").value
	};

	console.log(productsData)

	try {
		const response = await fetch("/products", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(productsData)
		});

		if (response.ok) {
			alert("Factory created successfully!");
			document.getElementById("prductsFrom").reset();
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
	let product_name = document.getElementById("product_name").value.trim();
	let product_code = document.getElementById("product_code").value.trim();
	let cost_price = document.getElementById("cost_price").value.trim();
	let selling_price = document.getElementById("selling_price").value.trim();
	let date_made = document.getElementById("date_made").value.trim();
	let factories_id = document.getElementById("factories_id").value.trim();
	let submitBtn = document.getElementById("submitBtn");

	console.log({ product_name, product_code, cost_price, selling_price, date_made, factories_id});

	if (product_name !== "" && product_code !== "" && cost_price !== "" && selling_price !== "" && date_made !== "" && factories_id !== "") {
		submitBtn.style.display = 'block';
	} else {
		submitBtn.style.display = 'none';
	}
}
