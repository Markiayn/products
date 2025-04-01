function validate() {
	let valid = false;
	let Name = document.getElementById("Name");
	console.log(Name.value);

	let Produced = document.getElementById("Produced")
	console.log(Produced.value)

	let Sold = document.getElementById("Sold")
	console.log(Sold.value)

	let DateMade = document.getElementById("date_made")
	console.log(DateMade.value)

	let submitBtn = document.getElementById("submitBtn")

	if (Name.value.trim() !== "" && Produced.value.trim() !== "" && Sold.value.trim() !== "" && DateMade.value.trim() !== "") {
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