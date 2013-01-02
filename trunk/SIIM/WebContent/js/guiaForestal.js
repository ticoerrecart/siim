
function submitir(){

	validarForm("guiaForestalForm","../guiaForestal","validarGuiaForestalBasicaForm","GuiaForestalForm");
}

/***** Funciones asociadas a las cuotas ******/
var cuotaIndex = 1;
function agregarCuota() {
	cuotaIndex = cuotaIndex + 1;

	var trCuota = document.getElementById("idTrCuota"+cuotaIndex);
	var trCuotaEspacio = document.getElementById("idTrCuotaEspacio"+cuotaIndex);
	trCuota.style.display = "";
	trCuotaEspacio.style.display = "";

	document.getElementById("idBotonRemoverCuota").disabled="";
	
	if(cuotaIndex > 9){
		document.getElementById("idBotonAgregarCuota").disabled="disabled";
	}
	
	/*var myTrId = "idCuota";
	//creo un TR nuevo.
	var myTr = document.createElement('TR');
	
    myTr.id= myTrId + cuotaIndex;
    
    //busco el último TR y le agrego el nuevo.
    var tabla = document.getElementById("idTablaCuotas");

	//Creo el 1er TD
	var myTd1 = document.createElement('TD');
	a = document.createAttribute('width');
	a.value = "36%";
	a1 = document.createAttribute('class');
	a1.value = "botoneralNegritaRight";
	myTd1.setAttributeNode(a);       		
	myTd1.setAttributeNode(a1);		
	myTd1.innerHTML = "Cuota n°"+cuotaIndex+" - Número de Cuota:";
	
	//Creo el 2do TD
	var myTd2 = document.createElement('TD');
	a2 = document.createAttribute('width');
	a2.value = "30%";
	a3 = document.createAttribute('align');
	a3.value = "left";
	myTd2.setAttributeNode(a2);       		
	myTd2.setAttributeNode(a3);
	myTd2.innerHTML = "<input class=\"botonerab\" type=\"text\" size=\"40\" onkeypress=\"javascript:esAlfaNumerico(event);\">";

	//Creo el 3er TD
	var myTd3 = document.createElement('TD');
	a4 = document.createAttribute('colspan');
	a4.value = "2";	 		
	myTd3.setAttributeNode(a4);		

	myTr.appendChild(myTd1);
	myTr.appendChild(myTd2);
	myTr.appendChild(myTd3);
		
	var tBody = tabla.getElementsByTagName("tbody") 
	var trEspacio = document.getElementById("trEspacio");
	tBody[0].removeChild(trEspacio);	
	tBody[0].appendChild(myTr);   
	tBody[0].appendChild(trEspacio);   

	var agregarCuota = document.getElementById("idAgregarCuota");
	agregarCuota.style.display = "";*/
	
}

function removerCuota(){

	/*var tabla = document.getElementById("idTablaCuotas");
	var tBody = tabla.getElementsByTagName("tbody")
	var myTr = document.getElementById("idCuota"+cuotaIndex); 
	tBody[0].removeChild(myTr);
	cuotaIndex = cuotaIndex - 1;*/

	var trCuota = document.getElementById("idTrCuota"+cuotaIndex);
	var trCuotaEspacio = document.getElementById("idTrCuotaEspacio"+cuotaIndex);
	trCuota.style.display = "none";
	trCuotaEspacio.style.display = "none";

	document.getElementById("idBotonAgregarCuota").disabled="";
	
	cuotaIndex = cuotaIndex - 1;
	
	if(cuotaIndex == 1){
		document.getElementById("idBotonRemoverCuota").disabled="disabled";	
	}
	
}
/***** ********/

/*****Funciones asociadas a los vales de transportes******/
var valeIndex = 1;
function agregarVale() {
	valeIndex = valeIndex + 1;

	var trVale = document.getElementById("idTrVale"+valeIndex);
	var trValeEspacio = document.getElementById("idTrValeEspacio"+valeIndex);
	trVale.style.display = "";
	trValeEspacio.style.display = "";

	document.getElementById("idBotonRemoverVale").disabled="";
	
	if(valeIndex > 9){
		document.getElementById("idBotonAgregarVale").disabled="disabled";
	}
}	

function removerVale(){

	var trVale = document.getElementById("idTrVale"+valeIndex);
	var trValeEspacio = document.getElementById("idTrValeEspacio"+valeIndex);
	trVale.style.display = "none";
	trValeEspacio.style.display = "none";

	document.getElementById("idBotonAgregarVale").disabled="";
	
	valeIndex = valeIndex - 1;
	
	if(valeIndex == 1){
		document.getElementById("idBotonRemoverVale").disabled="disabled";	
	}	
}
/******* *********/