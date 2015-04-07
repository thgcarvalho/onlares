function teste() {
	console.log("teste");
}

function alteraUnidade() {
   var unidadesBox = document.getElementById("unidadesBox");
   var selectedValue = unidadesBox.options[unidadesBox.selectedIndex].value;
 
   document.unidadesForm.unidade.value = selectedValue;
   document.unidadesForm.submit();
   document.getElementById("unidadesForm").submit();
}
