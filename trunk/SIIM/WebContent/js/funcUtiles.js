// funcUtiles.js
//ARREGLAR
// See http://www.webreference.com/tools/browser/javascript.html
var DotJ_agt            = navigator.userAgent.toLowerCase();
var DotJ_appVer         = navigator.appVersion.toLowerCase();
var DotJ_is_minor       = parseFloat(DotJ_appVer);
var DotJ_is_major       = parseInt(DotJ_is_minor,10);

var DotJ_is_opera       = (DotJ_agt.indexOf("opera") != -1);
var DotJ_is_opera2      = (DotJ_agt.indexOf("opera 2") != -1 || DotJ_agt.indexOf("opera/2") != -1);
var DotJ_is_opera3      = (DotJ_agt.indexOf("opera 3") != -1 || DotJ_agt.indexOf("opera/3") != -1);
var DotJ_is_opera4      = (DotJ_agt.indexOf("opera 4") != -1 || DotJ_agt.indexOf("opera/4") != -1);
var DotJ_is_opera5      = (DotJ_agt.indexOf("opera 5") != -1 || DotJ_agt.indexOf("opera/5") != -1);
var DotJ_is_opera6      = (DotJ_agt.indexOf("opera 6") != -1 || DotJ_agt.indexOf("opera/6") != -1);
var DotJ_is_opera7      = (DotJ_agt.indexOf("opera 7") != -1 || DotJ_agt.indexOf("opera/7") != -1);
var DotJ_is_opera5up    = (DotJ_is_opera && !DotJ_is_opera2 && !DotJ_is_opera3 && !DotJ_is_opera4);
var DotJ_is_opera6up    = (DotJ_is_opera && !DotJ_is_opera2 && !DotJ_is_opera3 && !DotJ_is_opera4 && !DotJ_is_opera5);
var DotJ_is_opera7up    = (DotJ_is_opera && !DotJ_is_opera2 && !DotJ_is_opera3 && !DotJ_is_opera4 && !DotJ_is_opera5 && !DotJ_is_opera6);

var DotJ_iePos  = DotJ_appVer.indexOf('msie');
if (DotJ_iePos !=-1) {
	DotJ_is_minor = parseFloat(DotJ_appVer.substring(DotJ_iePos+5,DotJ_appVer.indexOf(';',DotJ_iePos)))
	DotJ_is_major = parseInt(DotJ_is_minor,10);
}

var DotJ_is_konq = false;
var DotJ_kqPos   = DotJ_agt.indexOf('konqueror');
if (DotJ_kqPos !=-1) {
	DotJ_is_konq  = true;
	DotJ_is_minor = parseFloat(DotJ_agt.substring(DotJ_kqPos+10,DotJ_agt.indexOf(';',DotJ_kqPos)));
	DotJ_is_major = parseInt(DotJ_is_minor,10);
}

var DotJ_is_safari = ((DotJ_agt.indexOf('safari')!=-1)&&(DotJ_agt.indexOf('mac')!=-1))?true:false;
var DotJ_is_khtml  = (DotJ_is_safari || DotJ_is_konq);

var DotJ_is_gecko  = ((!DotJ_is_khtml)&&(navigator.product)&&(navigator.product.toLowerCase()=="gecko"))?true:false;
var DotJ_is_gver = 0;
if (DotJ_is_gecko) DotJ_is_gver = navigator.productSub;

var DotJ_is_moz   = ((DotJ_agt.indexOf('mozilla/5')!=-1) && (DotJ_agt.indexOf('spoofer')==-1) &&
				(DotJ_agt.indexOf('compatible')==-1) && (DotJ_agt.indexOf('opera')==-1)  &&
				(DotJ_agt.indexOf('webtv')==-1) && (DotJ_agt.indexOf('hotjava')==-1)     &&
				(DotJ_is_gecko) &&
				((navigator.vendor=="")||(navigator.vendor=="Mozilla")||(navigator.vendor=="Debian")));
var DotJ_is_fb = ((DotJ_agt.indexOf('mozilla/5')!=-1) && (DotJ_agt.indexOf('spoofer')==-1) &&
                 (DotJ_agt.indexOf('compatible')==-1) && (DotJ_agt.indexOf('opera')==-1)  &&
                 (DotJ_agt.indexOf('webtv')==-1) && (DotJ_agt.indexOf('hotjava')==-1)     &&
                 (DotJ_is_gecko) && (navigator.vendor=="Firebird"));
var DotJ_is_fx = ((DotJ_agt.indexOf('mozilla/5')!=-1) && (DotJ_agt.indexOf('spoofer')==-1) &&
                 (DotJ_agt.indexOf('compatible')==-1) && (DotJ_agt.indexOf('opera')==-1)  &&
                 (DotJ_agt.indexOf('webtv')==-1) && (DotJ_agt.indexOf('hotjava')==-1)     &&
                 (DotJ_is_gecko) && (navigator.vendor=="Firefox"));
if ((DotJ_is_moz)||(DotJ_is_fb)||(DotJ_is_fx)) {
	var DotJ_is_moz_ver = (navigator.vendorSub)?navigator.vendorSub:0;
	if (!(DotJ_is_moz_ver)) {
		DotJ_is_moz_ver = DotJ_agt.indexOf('rv:');
		DotJ_is_moz_ver = DotJ_agt.substring(DotJ_is_moz_ver+3);
		DotJ_is_paren   = DotJ_is_moz_ver.indexOf(')');
		DotJ_is_moz_ver = DotJ_is_moz_ver.substring(0,DotJ_is_paren);
	}
	DotJ_is_minor = DotJ_is_moz_ver;
	DotJ_is_major = parseInt(DotJ_is_moz_ver,10);
}
var DotJ_is_fb_ver = DotJ_is_moz_ver;
var DotJ_is_fx_ver = DotJ_is_moz_ver;

var DotJ_is_nav = ((DotJ_agt.indexOf('mozilla')!=-1) && (DotJ_agt.indexOf('spoofer')==-1) &&
				   (DotJ_agt.indexOf('compatible') == -1) && (DotJ_agt.indexOf('opera')==-1) &&
				   (DotJ_agt.indexOf('webtv')==-1) && (DotJ_agt.indexOf('hotjava')==-1) &&
				   (!DotJ_is_khtml) && (!(DotJ_is_moz)) && (!DotJ_is_fb) && (!DotJ_is_fx));

if ((navigator.vendor) && ((navigator.vendor=="Netscape6")||(navigator.vendor=="Netscape"))&& (DotJ_is_nav)) {
	DotJ_is_major = parseInt(navigator.vendorSub,10);
	DotJ_is_minor = parseFloat(navigator.vendorSub);
}

var DotJ_is_nav2        = (DotJ_is_nav && (DotJ_is_major == 2));
var DotJ_is_nav3        = (DotJ_is_nav && (DotJ_is_major == 3));
var DotJ_is_nav4        = (DotJ_is_nav && (DotJ_is_major == 4));
var DotJ_is_nav4up      = (DotJ_is_nav && (DotJ_is_minor >= 4));
var DotJ_is_navonly     = (DotJ_is_nav && ((DotJ_agt.indexOf(";nav") != -1) || (DotJ_agt.indexOf("; nav") != -1)) );
var DotJ_is_nav6        = (DotJ_is_nav && (DotJ_is_major == 6));
var DotJ_is_nav6up      = (DotJ_is_nav && (DotJ_is_minor >= 6));
var DotJ_is_nav5        = (DotJ_is_nav && (DotJ_is_major == 5) && !DotJ_is_nav6);
var DotJ_is_nav5up      = (DotJ_is_nav && (DotJ_is_minor >= 5));
var DotJ_is_nav7        = (DotJ_is_nav && (DotJ_is_major == 7));
var DotJ_is_nav7up      = (DotJ_is_nav && (DotJ_is_minor >= 7));

var DotJ_is_ie   = ((DotJ_iePos!=-1) && (!DotJ_is_opera) && (!DotJ_is_khtml));
var DotJ_is_ie3  = (DotJ_is_ie && (DotJ_is_major < 4));

var DotJ_is_ie4   = (DotJ_is_ie && DotJ_is_major == 4);
var DotJ_is_ie4up = (DotJ_is_ie && DotJ_is_minor >= 4);
var DotJ_is_ie5   = (DotJ_is_ie && DotJ_is_major == 5);
var DotJ_is_ie5up = (DotJ_is_ie && DotJ_is_minor >= 5);

var DotJ_is_ie5_5  = (DotJ_is_ie && (DotJ_agt.indexOf("msie 5.5") !=-1));
var DotJ_is_ie5_5up =(DotJ_is_ie && DotJ_is_minor >= 5.5);

var DotJ_is_ie6   = (DotJ_is_ie && DotJ_is_major == 6);
var DotJ_is_ie6up = (DotJ_is_ie && DotJ_is_minor >= 6);

var DotJ_is_hotjava = (DotJ_agt.indexOf("hotjava") != -1);
var DotJ_is_hotjava3 = (DotJ_is_hotjava && (DotJ_is_major == 3));
var DotJ_is_hotjava3up = (DotJ_is_hotjava && (DotJ_is_major >= 3));

var DotJ_js;
if (DotJ_is_nav2 || DotJ_is_ie3) DotJ_js = 1.0;
else if (DotJ_is_nav3) DotJ_js = 1.1;
else if ((DotJ_is_opera5)||(DotJ_is_opera6)) DotJ_js = 1.3;
else if (DotJ_is_opera7up) DotJ_js = 1.5;
else if (DotJ_is_khtml) DotJ_js = 1.5;
else if (DotJ_is_opera) DotJ_js = 1.1;
else if ((DotJ_is_nav4 && (DotJ_is_minor <= 4.05)) || DotJ_is_ie4) DotJ_js = 1.2;
else if ((DotJ_is_nav4 && (DotJ_is_minor > 4.05)) || DotJ_is_ie5) DotJ_js = 1.3;
else if (DotJ_is_nav5 && !(DotJ_is_nav6)) DotJ_js = 1.4;
else if (DotJ_is_hotjava3up) DotJ_js = 1.4;
else if (DotJ_is_nav6 || DotJ_is_gecko) DotJ_js = 1.5;
else if (DotJ_is_nav && (DotJ_is_major > 5)) DotJ_js = 1.4;
else if (DotJ_is_ie && (DotJ_is_major > 5)) DotJ_js = 1.3;
else if (DotJ_is_moz) DotJ_js = 1.5;
else if (DotJ_is_fb||DotJ_is_fx) DotJ_is_js = 1.5;
else DotJ_js = 0.0;
if ((DotJ_agt.indexOf("mac")!=-1) && DotJ_is_ie5up) DotJ_js = 1.4;
if (DotJ_is_nav6up) {
	DotJ_is_minor = navigator.vendorSub;
}


//Varias

function DotJ_MozillaFamily() {
	if (DotJ_is_nav6up || DotJ_is_moz || DotJ_is_fx) return true;
	return false;
}

function DotJ_getVisibility(element) {
	if (DotJ_is_nav4) return element.visibility;
	else if (DotJ_is_ie4up && typeof(element.style) != 'undefined') return element.style.visibility;
	else if (DotJ_MozillaFamily() && typeof(element.style) != 'undefined') return element.style.visibility;
	return '';
}
function DotJ_displayElement(element) {
	if (DotJ_is_nav4) element.visibility = "show";
	else if (DotJ_is_ie4up) element.style.visibility = "visible";
	else if (DotJ_MozillaFamily()) element.style.visibility = "visible";
}

function DotJ_hideElement(element) {
	if (DotJ_is_nav4) element.visibility = "hide";
	else if (DotJ_is_ie4up) element.style.visibility = "hidden";
	else if (DotJ_MozillaFamily()) element.style.visibility = "hidden";
}

// Ubicar objetos en la pagina
function DotJ_getObj(elName) {
	if (DotJ_is_nav4) return eval('document.' + elName);
	if (DotJ_is_ie4up) return document.all[elName];
	if (DotJ_MozillaFamily() || DotJ_is_opera7up) return document.getElementById(elName);

	return null;
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

//AutoTab Next
var tamCero=0; 
function TabNextCero(obj,event,len,next_field){	
	if (event == "down"){		
		tamCero=obj.value.length;
	}
	else if (event == "up"){
		if (obj.value.length != tamCero){
			tamCero=obj.value.length;
			if (tamCero == len){
				elem = eval("document.getElementById('"+next_field+"')");
				if (elem!=null){
					eval("document.getElementById('"+next_field+"').focus()");
				}
			}
		}
	}
}

/*
var params = new Array();
params.push({ name: "tipoFormulario",  value: formuChequeado });
params.push({ name: "supCubierta",  value: document.getElementById("supCubierta").value });
buildQueryString(params) arma un string de la forma param1=valor1&param2=valor2....
*/
function buildQueryString(params) {
    var query = "";
    for (var i = 0; i < params.length; i++) {
        query += (i > 0 ? "&" : "")
            + escape(params[i].name) + "="
            + escape(params[i].value);
    }
    return query;
}	


function contextRoot (){
	var path = window.location.pathname;
	var firstSlashPosition = 0;
	var secondSlashPosition = path.indexOf('/',1);
	return path.slice(firstSlashPosition,secondSlashPosition);
}

function evitarAutoSubmit(e) { 
	  tecla = (document.all) ? e.keyCode :e.which; 
	  return (tecla!=13); 
} 

function roundNumber(id){
	
	var nro = $('#'+id).val();
	$('#'+id).val(new Number(nro).toFixed(2));
}