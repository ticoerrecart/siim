<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/LocalizacionFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/fiscalizacion.js'/>"></script>

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	

<script>
	$(function() {

		$( "#datepickerFecha" ).datepicker({ dateFormat: 'dd/mm/yy'});
	});

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function submitir(){
	validarForm("canonMineroFormId","../canonMinero","validarAltaCanonMineroForm","CanonMineroForm");
}

function exp(sec) {
	
	 if (type=="IE") { 
	 	 eval("document.all." + "e"+sec + ".style.display='none'");
	 	 eval("document.all." + "c"+sec + ".style.display=''"); 	 
	 	}
	 if (type=="MO" || type=="OP") {
	    eval("document.getElementById('" + "e"+sec + "').style.display='none'");
	    eval("document.getElementById('" + "c"+sec + "').style.display=''");
	   }
}

function col(sec) {
	
 if (type=="IE") { 
 	 eval("document.all." + "c"+sec + ".style.display='none'");
 	 eval("document.all." + "e"+sec + ".style.display=''"); 	 
 	}
 if (type=="MO" || type=="OP") {
    eval("document.getElementById('" + "c"+sec + "').style.display='none'");
    eval("document.getElementById('" + "e"+sec + "').style.display=''");
   }
}

function reemplazarCaracter(caracAReemp, caracterNuevo, stringViejo){

	var stringNuevo = "";
	
	for(var i=0;i<stringViejo.length;i++){
		if(stringViejo.charAt(i) == caracAReemp){
			stringNuevo += caracterNuevo;	
		}else{
			stringNuevo += stringViejo.charAt(i);
		}	
	}

	return stringNuevo;
	
}

var clase2;
function pintarFila(idTr){

	$('#tr'+idTr).attr("class", "seleccionado");	
}

function despintarFila(idTr){

	if(!$('#idCheck'+idTr).is(':checked')){
		if(idTr%2){
			clase2 = "par";		
		}else{
			clase2 = "";
		}	
		$('#tr'+idTr).attr("class", clase2);
	}		
}


//-----------------------------------------------------//
//FUNCIONES DE PAGO DE CANON MINERO//

var indice = 2;										 
function agregarCuota(){
	var nom = $("#nombreProductor").val();
	
	var nombre = "";
	nombre = reemplazarCaracter(" ","%20",nom,nombre);
	
	$('#dummy').load('/SIIM/jsp/bloqueBoletaDeposito.jsp?nombreProductor='+ nombre +'&indice=' + indice , 
			function(){

				var i = "divPlanDePagos"+indice;																
				document.getElementById(i).innerHTML = document.getElementById("dummy").innerHTML;
				document.getElementById("dummy").innerHTML = "";

				var ind = indice-1;
				var indiceDate = "#datepicker"+ind;
				$( indiceDate ).datepicker({ dateFormat: 'dd/mm/yy'});
				
				document.getElementById("idBotonRemoverCuota").disabled="";
				indice++;													
			}								
	);	
}

function removerCuota(){

	indice--;
	document.getElementById("dummy").innerHTML = "";	

	var i = "divPlanDePagos"+indice;
	document.getElementById(i).innerHTML = "";

	if(indice == 2){
		document.getElementById("idBotonRemoverCuota").disabled="disabled";
	}																			
}

function cambiarProductor(){

	var idProductor = $('#idProductor').val();

	limpiarCamposLocalizacion();
	
	if(idProductor != "-1"){
		$('#idZonaExtraccion').attr('disabled',false);
		EntidadFachada.getEntidadDTO(idProductor,cambiarProductorCallback );

		LocalizacionFachada.getLocalizacionesPorProductorDTO(idProductor,actualizarZonasExtraccionCallback);
	}
	else{
		$('#nomProductor').val("");
		$('#domProductor').val("");
		$('#locProductor').val("");
		$('#telProductor').val("");

		dwr.util.removeAllOptions("idZonaExtraccion");
		var data = [ { razonSocial:"-Seleccione una Zona-", id:-1 }];
		dwr.util.addOptions("idZonaExtraccion", data, "id", "razonSocial");		
		$('#idZonaExtraccion').attr('disabled','disabled');		
	}	
}

function cambiarProductorCallback(productor) {
	
	dwr.util.setValue("nombreProductor", productor.nombre);

	var ind = indice-2;
	for(var i=0;i<=ind;i++){
		dwr.util.setValue("idProductor"+i, productor.nombre);	
	}
}

function actualizarZonasExtraccionCallback(zonas){
	dwr.util.removeAllOptions("idZonaExtraccion");
	var data = [ { razonSocial:"-Seleccione una Zona-", id:-1 }];
	dwr.util.addOptions("idZonaExtraccion", data, "id", "razonSocial");	
	dwr.util.addOptions("idZonaExtraccion", zonas,"id","razonSocial");	
}

function calcularImporteTotal(){

	var vol1 = new Number($('#idTotal1').val()).toFixed(2);
	var vol2 = new Number($('#idTotal2').val()).toFixed(2);
	var vol3 = new Number($('#idTotal3').val()).toFixed(2);
	var vol4 = new Number($('#idTotal4').val()).toFixed(2);

	var volTotal =  parseFloat(new Number(vol1)+new Number(vol2)+new Number(vol3)+new Number(vol4));
	$('#idVolumenTotal').val(new Number(volTotal).toFixed(2));	

	var regalia = new Number($('#idRegalia').val()).toFixed(2);
	var importeTotal = parseFloat(new Number(volTotal)*new Number(regalia));
	$('#idImporteTotal').val(new Number(importeTotal).toFixed(2));
}

function calcularVolumenTotalTrimestre(nroTrimestre){

	var mes1 = new Number($('#id'+nroTrimestre+'_1').val()).toFixed(2);
	$('#id'+nroTrimestre+'_1').val(mes1)
	var mes2 = new Number($('#id'+nroTrimestre+'_2').val()).toFixed(2);
	$('#id'+nroTrimestre+'_2').val(mes2)
	var mes3 = new Number($('#id'+nroTrimestre+'_3').val()).toFixed(2);
	$('#id'+nroTrimestre+'_3').val(mes3)
	
	var volSubTotal =  parseFloat(new Number(mes1)+new Number(mes2)+new Number(mes3));
	$('#idTotal'+nroTrimestre).val(new Number(volSubTotal).toFixed(2));

	calcularImporteTotal();
}

function cambiarZonaExtraccion(){

	var idZonaExtraccion = $('#idZonaExtraccion').val();

	if(idZonaExtraccion != "-1"){
		
		LocalizacionFachada.getLocalizacionDTOPorId(idZonaExtraccion,cambiarZonaExtraccionCallback );
	}
	else{
		/*$('#domZona').val("");
		$('#supZona').val("");
		$('#cantHectareas').val("");*/

		limpiarCamposLocalizacion();		
	}	
}

function cambiarZonaExtraccionCallback(localizacion) {
	
	dwr.util.setValue("domZona", localizacion.domicilio);
	dwr.util.setValue("supZona", localizacion.superficie);
	//dwr.util.setValue("cantHectareas", localizacion.superficie);	

	$('#cantHectareas').html(localizacion.superficie);
	
	var cantHa = $('#cantHectareas').html(); 
	var haXPert = $('#haXPertenencia').html();
	
	var num = Math.floor(cantHa/haXPert);
	var decimal = cantHa%haXPert;	
 
	var entero = (decimal<= 0)?num: new Number(num)+new Number(1); 
	
	$('#cantPertenencias').html(entero);
	
	var canonMinero = $('#canonXPertenencia').html();
	
	$('#montoTotal').html("$ "+new Number(entero)*new Number(canonMinero));

	$('#idCanonXPertenencia').val(canonMinero);
	$('#idMontoTotal').val(new Number(entero)*new Number(canonMinero));
}

function limpiarCamposLocalizacion(){

	$('#domZona').val("");
	$('#supZona').val("");
	$('#cantHectareas').html("");
	$('#cantPertenencias').html("");
	$('#montoTotal').html("");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<html:form action="canonMinero" styleId="canonMineroFormId">
	<html:hidden property="metodo" value="altaCanonMinero" />
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIM.titulo.AltaCanonMinero'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td width="30%" align="left">
				<input type="hidden" id="nombreProductor" value="" />
				<select id="idProductor" name="canonMinero.productor.id" class="botonerab" onchange="cambiarProductor();">
					<option value="-1">- Seleccione un Productor -</option>
					<c:forEach items="${productores}" var="prod">						
						<option value="${prod.id}">
							<c:out value="${prod.nombre}"></c:out>
						</option>
					</c:forEach>
				</select>	
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Periodo'/></td>
			<td align="left">
					<select name="canonMinero.periodo" class="botonerab" >
						<c:forEach items="${periodos}" var="per">
							<option value="${per.periodo}">
								<c:out value="${per.periodo}"></c:out>
							</option>
						</c:forEach>
					</select>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>


	<!-- LOCALIZACION -->
	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIM.subTitulo.Localizacion'/></td>
					</tr>
					<tr>
						<td colspan="3" height="10"></td>
					</tr>			
					
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.RazonSocial'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<select id="idZonaExtraccion" class="botonerab" name="canonMinero.zonaExtraccion.id" 
								disabled="disabled" onchange="cambiarZonaExtraccion();">
								<option value="-1">--Seleccione una Zona--</option>
							</select>					
						</td>						
					</tr>	
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Domicilio'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="domZona" class="botonerab" type="text" size="25" readonly="readonly">
						</td>						
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Superficie'/>(ha)
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly">
						</td>						
					</tr>																					
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>				
	</table>									

	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>	

		<!-- SUBIMPORTES -->
		<tr>
			<td colspan="4" align="left">
				<input id="idCanonXPertenencia" type="hidden" name="canonMinero.canonXPertenencia">			
				<input id="idMontoTotal" type="hidden" name="canonMinero.montoTotal">
				<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
					<tr>
						<td width="20%" class="grisSubtituloCenter">Hectareas</td>						
						<td width="20%" class="grisSubtituloCenter">Cant Ha x Pertenencia Minera</td>
						<td width="20%" class="grisSubtituloCenter">Cant Pertenencias Mineras</td>
						<td width="20%" class="grisSubtituloCenter">Canon Minero x Pertenencia</td>
						<td width="20%" class="grisSubtituloCenter">Monto Total</td>
					</tr>
					<tr>
						<td class="grisMuyClaroSubtituloCenter">
							<p id="cantHectareas"></p>																						
						</td>						
						<td class="grisMuyClaroSubtituloCenter">
							<p id="haXPertenencia">20</p>																						
						</td>
						<td class="grisMuyClaroSubtituloCenter">
							<p id="cantPertenencias"></p>																	
						</td>
						<td class="grisMuyClaroSubtituloCenter">							
							<p id="canonXPertenencia"><c:out value="${canonXPertenencia}"></c:out></p>
						</td>
						<td class="grisMuyClaroSubtituloCenter">
							<p id="montoTotal"></p>								
						</td>
					</tr>
				</table>
			</td>		
		</tr>	
		<tr>
			<td height="10" colspan="4"></td>
		</tr>	
							
		<!-- PLAN DE PAGO -->
		<tr>
			<td colspan="4" align="left">
			<div id="e2" style="DISPLAY: ">
				<label onclick="javascript:exp('2')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIM.subTitulo.PlanPagos'/></U><BR>
				</label>
			</div>
			<div id="c2" style="DISPLAY: none">
				<label onclick="javascript:col('2')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIM.subTitulo.PlanPagos'/> </U><BR>
				</label>
				<br>
				<table class="cuadrado" align="center" width="90%" cellpadding="2"> 
					<tr>
						<td colspan="4" class="azulAjustado"><bean:message key='SIIM.label.BoletasDeposito'/></td>
					</tr>				
	
					<tr>
						<td height="10" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
							<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
								<tr>
									<td colspan="4" class="grisSubtitulo"><bean:message key='SIIM.label.CuotaNro'/>1</td>
								</tr>
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIM.label.NroBoleta'/>
									</td>
									<td width="40%" align="left">
										<input name="boletasDeposito[0].numero" class="botonerab" type="text"
											size="20" onkeypress="javascript:esNumerico(event);">
									</td>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIM.label.Productor'/>
									</td>
									<td width="40%" align="left">
										<input id="idProductor0" value="" class="botonerab" type="text" size="40" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIM.label.Concepto'/>
									</td>
									<td colspan="3" align="left">
										<input name="boletasDeposito[0].concepto" class="botonerab" type="text" size="90"
												value="Aforo">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIM.label.Area'/>
									</td>
									<td colspan="3" align="left">
										<input name="boletasDeposito[0].area" class="botonerab" type="text" size="90" 
												value="Dirección General de Minería">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIM.label.EfectivoCheque'/>
									</td>
									<td width="40%" align="left">
										<input name="boletasDeposito[0].efectivoCheque" class="botonerab" 
										type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);">
									</td>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIM.label.Monto$'/>
									</td>
									<td width="40%" align="left">
										<input name="boletasDeposito[0].monto" class="botonerab" type="text"
											size="20" onkeypress="javascript:esNumericoConDecimal(event);">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIM.label.FechaVencimiento'/>
									</td>
									<td colspan="3" align="left">
										<input id="datepicker0" type="text" readonly="readonly" class="botonerab" 
												name='boletasDeposito[0].fechaVencimiento'>
										<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
											align="top" width='17' height='21'>		
																					
										<script>
											$(function() {
										
												$( "#datepicker0" ).datepicker({ dateFormat: 'dd/mm/yy'});
											});
										</script>	
																																
									</td>
								</tr>
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
		
							</table>								
		
							<div id="dummy" style="display: none"></div>
							<div id="divPlanDePagos2"></div>
							
							<table  class="cuadradoSinBorde" align="center" width="80%" cellpadding="2">
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
								<tr>
									<td colspan="4">
										<input id="idBotonAgregarCuota" type="button" value="+" 
											onclick="javascript:agregarCuota();">
										<input id="idBotonRemoverCuota" disabled="disabled" type="button"
											value="-" onclick="javascript:removerCuota();"></td>
								</tr>
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Localidad'/></td>
			<td width="30%" align="left">				
				<select id="idLocalidad" class="botonerab" name="canonMinero.localidad.id">
					<option value="-1">- Seleccione una Localidad -</option>
					<c:forEach items="${localidades}" var="localidad">
						<option value="${localidad.id}">
							<c:out value="${localidad.nombre}"></c:out>
						</option>
					</c:forEach>
				</select>				
				
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Fecha'/></td>
			<td align="left">		
				<input id="datepickerFecha" type="text" name="canonMinero.fecha" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4">
				<input type="button" value="Aceptar" id="enviar" disabled="disabled"
					class="botonerab" onclick="javascript:submitir();" > 
				<input type="button" class="botonerab" value="Volver"
						onclick="javascript:parent.location= contextRoot() + '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</html:form> 				  