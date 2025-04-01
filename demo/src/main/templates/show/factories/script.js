document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("product-id").textContent = product.id;
    document.getElementById("product-name").textContent = product.name;
    document.getElementById("product-code").textContent = product.code;
    document.getElementById("cost-price").textContent = product.costPrice;
    document.getElementById("selling-price").textContent = product.sellingPrice;
    document.getElementById("date-made").textContent = product.dateMade;
    document.getElementById("factory-id").textContent = product.factoryId;
});
