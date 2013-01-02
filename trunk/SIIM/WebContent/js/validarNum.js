//Función que permite que sólo se ingresen números si el campo es de tipo numerico
var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO"; 

function stop(event) {
    if (event.preventDefault) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      event.returnValue = false;
      event.cancelBubble = true;
    }
 }

function esNumerico(event) {
 	var ok;
 	var key;
	if (type=="IE") { 
		key = event.keyCode;
	}

	if (type=="MO" || type=="OP") {
		key = event.which;		  
	}
	
	if ((key >8 && key < 48) || key > 57) {
		stop(event);
	}else{
		event.returnValue = true;
	}
}

function esNumericoConDecimal(event) {
	var ok;
 	var key;
	 if (type=="IE") { 
		key = event.keyCode;
	}
	 if (type=="MO" || type=="OP") {
		key = event.which;		  
	 }

	 if ((key != 46) && ((key >8 && key < 48) || key > 57)) {
	 	stop(event);
	 }
	 else{
		event.returnValue = true;
	}
}

function esDouble(ob) { 
	if (ob.value.length > 0) {
	
		var valor = parseFloat(ob.value);	
		if (isNaN(valor)) { 
			alert("No es un valor válido");
			ob.focus();
		} else ob.value = valor;
	}
 }


function leftPad(item ,cPad, cant) {
	newItem = new String(item);
	while (newItem.length < cant) {
		newItem = cPad + newItem;
	}
	return newItem;
}

//Estas son numericas
function DotJ_isPositiveInteger (s) {
	return (DotJ_isInteger(s) && parseInt(s,10) > 0);
}
function DotJ_isInteger (s) {
	var re = /^(\+|\-)?\d+$/
	var isMatch = re.exec(s);
	return (isMatch != null && s == isMatch[0]);
}

function DotJ_isIntegerInRange (s, a, b) {
	if (!DotJ_isInteger(s)) return false;
	var num = s - 0;
	return ((num >= a) && (num <= b));
}
