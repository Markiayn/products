function validate() {
	let valid = false;
	let ProductName = document.getElementById("product_name");
	console.log(ProductName.value);

	let ProductCode = document.getElementById("product_code")
	console.log(ProductCode.value)

	let CostPrice = document.getElementById("cost_price")
	console.log(CostPrice.value)

    let SellingPrice = document.getElementById("selling_price")
	console.log(SellingPrice.value)

	let DateMade = document.getElementById("date_made")
	console.log(DateMade.value)

	let FactoriesId = document.getElementById("factories_id")
	console.log(FactoriesId.value)

	let sumbitBtn = document.getElementById("sumbitBtn")

	if (ProductName.value.trim() !== "" && ProductCode.value.trim() !== "" && CostPrice.value.trim() !== "" && SellingPrice.value.trim() !== "" && DateMade.value.trim() !== "" && FactoriesId.value.trim() !== "") {
		valid = true;
	}
	
	if (valid) {
		submitBtn.style.display = 'block';
	} else {
		submitBtn.style.display = 'none';
    }
};
function submit(){
	console.log("function submit called!");
}