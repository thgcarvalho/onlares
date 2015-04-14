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

function setAddCPF() {
	console.log("setAddCPF");
	document.getElementById("cpf").style.background = "#FFFFFF";
	document.getElementById("cnpj").style.background = "#CCCCCC";
	document.getElementById("cnpj").value = "";
}

function setAddCNPJ() {
	console.log("setAddCNPJ");
	var cpf = document.getElementById("cpf").value;
	console.log(cpf);
	if (cpf == "") {
		document.getElementById("cnpj").style.color = "#000000";
		document.getElementById("cnpj").style.background = "#FFFFFF";
		document.getElementById("cpf").style.background = "#CCCCCC";
		document.getElementById("cpf").value = "";
	} else{
		document.getElementById("cnpj").style.color = "#CCCCCC";
	}
}

function setEdtCPF() {
	document.getElementById("cpf").style.background = "#FFFFFF";
	document.getElementById("cnpj").style.background = "#CCCCCC";
	document.getElementById("cnpj").value = "";
}

function setEdtCNPJ() {
	var cpf = document.getElementById("cpf").value;
	console.log(cpf);
	if (cpf == "") {
		document.getElementById("cnpj").style.color = "#000000";
		document.getElementById("cnpj").style.background = "#FFFFFF";
		document.getElementById("cpf").style.background = "#CCCCCC";
		document.getElementById("cpf").value = "";
	} else{
		document.getElementById("cnpj").style.color = "#CCCCCC";
	}
}
