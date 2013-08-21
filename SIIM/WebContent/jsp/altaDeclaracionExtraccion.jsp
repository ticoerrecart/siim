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
	src="<html:rewrite page='/dwr/interface/PeriodoFachada.js'/>"></script>
	
<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
<link rel="stylesheet" href="<html:rewrite page='/css/main.css'/>"
	type="text/css">

<script>
	$(function() {
		
		$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});

		$( "#datepickerFechaTrim1" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFechaTrim2" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFechaTrim3" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFechaTrim4" ).datepicker({ dateFormat: 'dd/mm/yy'});	
	});

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volverAltaDeclaracionExtraccion(){
	parent.location = contextRoot() + '/jsp.do?page=.index';		
}

function setValorLocalizacion(valor){
	$("#idLocalizacion").val(valor);
}

function submitir(){
	validarForm("declaracionExtraccionForm","../declaracionExtraccion","validarAltaDeclaracionExtraccionForm","DeclaracionExtraccionForm");
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

function volverAltaGFB(){

	$("#idGuia").show();
	$("#idDivFiscalizacion").hide();
	$("#idDivFiscalizacion").empty();
	$("#errores").show();
}


//-----------------------------------------------------//
//FUNCIONES DE DECLARACION DE EXTRACCION//

var indice = 2;										 
function agregarCuota(){
	var nom = $("#nomProductor").val();
	
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

	$('#domZona').val("");
	$('#supZona').val("");	
}

function cambiarProductorCallback(productor) {

	dwr.util.setValue("nomProductor", productor.nombre);
	dwr.util.setValue("domProductor", productor.direccion);
	dwr.util.setValue("locProductor", productor.localidad.nombre);
	dwr.util.setValue("telProductor", productor.telefono);

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
		$('#domZona').val("");
		$('#supZona').val("");	
	}	
}

function cambiarZonaExtraccionCallback(localizacion) {
	
	dwr.util.setValue("domZona", localizacion.domicilio);
	dwr.util.setValue("supZona", localizacion.superficie);				
}

	//recupera las fechas de vencimiento
	function cambioPeriodo(){
		PeriodoFachada.getPeriodoDTOPorPeriodo($("#periodo").val(),cambioPeriodoCbk);
	}
	
	function cambioPeriodoCbk(periodo){
		$("#datepickerFechaTrim1").val(periodo.fechaVencimientoPrimerTrimestre);
		$("#datepickerFechaTrim2").val(periodo.fechaVencimientoSegundoTrimestre);
		$("#datepickerFechaTrim3").val(periodo.fechaVencimientoTercerTrimestre);
		$("#datepickerFechaTrim4").val(periodo.fechaVencimientoCuartoTrimestre);
	}
	
	/*function setDisable(){
		$("#idProductor").attr("disabled","disabled");
		$("#periodo").attr("disabled","disabled");
		$("#idZonaExtraccion").attr("disabled","disabled");
	}*/

	/*function setEnable(){
		$("#idProductor").removeAttr("disabled");
		$("#periodo").removeAttr("disabled");
		$("#idZonaExtraccion").removeAttr("disabled");
	}*/

	function setModificacion(){
		if('${modificacion}'=="S"){
			//setDisable();
			for(var i=1;i<5;i++){
				calcularVolumenTotalTrimestre(i);
			}

			//seteo readonly los trimestres que ya estén cargados
			for(var i=0;i<4;i++){
				if ($('[name="trimestres[' +i+ '].volumenPrimerMes"]').val()>0
					|| $('[name="trimestres[' +i+ '].volumenSegundoMes"]').val()>0
					|| $('[name="trimestres[' +i+ '].volumenTercerMes"]').val()>0){
					$('[name="trimestres[' +i+ '].volumenPrimerMes"]').attr("readonly","readonly");
					$('[name="trimestres[' +i+ '].volumenSegundoMes"]').attr("readonly","readonly");
					$('[name="trimestres[' +i+ '].volumenTercerMes"]').attr("readonly","readonly");
				}
				
			}
			cambiarProductor();
			cambiarZonaExtraccion();
			//cambiarProductorModificacion();
			//cambioPeriodo();

		}
	}
	
	/*function cambiarProductorModificacion(){

		var idProductor = $('#idProductor').val();

		if(idProductor != "-1"){
			EntidadFachada.getEntidadDTO(idProductor,cambiarProductorCallback );

			LocalizacionFachada.getLocalizacionesPorProductorDTO(idProductor,actualizarZonasExtraccionModificacionCallback);
		}
	}*/
	
	/*function actualizarZonasExtraccionModificacionCallback(zonas){
		actualizarZonasExtraccionCallback(zonas);
		$("#idZonaExtraccion option[value="+'${declaracionDeExtraccion.localizacion.id}'+"]").attr("selected",true);
	}*/

	function expBoletaNro(){
		var idBoletaExp = $('#expBoleta').val();
		$('[id="idTrBoleta' + idBoletaExp +'"]').show();
	}

	function expBoletasPagas(){
		$('td.verdeExitoLeft').parents('[id^="idTrBoleta"]').show();
		$('td.rojoAdvertenciaLeft').parents('[id^="idTrBoleta"]').hide();
	}

	function expBoletasImpagas(){
		$('td.rojoAdvertenciaLeft').parents('[id^="idTrBoleta"]').show();
		$('td.verdeExitoLeft').parents('[id^="idTrBoleta"]').hide();	
	}

	function pintarFilaVale(idTd){

		$('#'+idTd).addClass("verdeSubtitulo");
		$('#'+idTd).removeClass("grisSubtitulo");
	}

	function despintarFilaVale(idTd){
		
		$('#'+idTd).addClass("grisSubtitulo");
		$('#'+idTd).removeClass("verdeSubtitulo");
	}
	
	function eliminarCuota(index){
		ind = index - 1;
		bd = 'boletasDeposito['+ ind +'].anulado';
		$("[name='"+bd+"']").val(true);	
		apagar($('#tBoleta'+index));
		$("#tdBoleta" + index).addClass("tachado");
		$("#idBotonEliminarCuota"+index).hide();
		$("#idBotonRestituirCuota"+index).show();
		
	}
	
	function restituirCuota(index){
			ind = index - 1;
			bd = 'boletasDeposito['+ ind +'].anulado';
			$("[name='"+bd+"']").val(false);	
			encender($('#tBoleta'+index));
			$("#tdBoleta" + index).removeClass("tachado");
			$("#idBotonEliminarCuota"+index).show();
			$("#idBotonRestituirCuota"+index).hide();
	}

	function apagar(id){
		$(id).animate(
			{opacity:"0.3"},
			{duration:300}
		  );
	}

	function encender(id){
		$(id).animate(
			{opacity:"1"},
			{duration:300}
		  );
	}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<div id="idDeclaracion">
<html:form action="declaracionExtraccion" styleId="declaracionExtraccionForm">
	<html:hidden property="metodo" value="${metodo}" />
	<input type="hidden" name="declaracion.id" value="${declaracionDeExtraccion.id}" />

	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIM.titulo.AltaDeclaracionExtraccion'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Numero'/></td>
			<td width="30%" align="left">
				<input id="nroDeclaracion" name="declaracion.numero" class="botonerab" type="text" size="20"
						onkeypress="javascript:esNumerico(event);" value="${declaracionDeExtraccion.numero}">
				<font class="rojoAdvertencia">*</font>	
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td align="left">
				<c:choose>
					<c:when test="${modificacion!='S'}">
						<select id="idProductor" name="declaracion.productor.id" class="botonerab" onchange="cambiarProductor();">
							<option value="-1">- Seleccione un Productor -</option>
							<c:forEach items="${productores}" var="prod">
								<c:choose>
									<c:when test="${prod.id==declaracionDeExtraccion.entidad.id}">
										<option value="${prod.id}" selected="selected">
											<c:out value="${prod.nombre}"/>
										</option>
									</c:when>
									<c:otherwise>
										<option value="${prod.id}">
											<c:out value="${prod.nombre}"/>
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<font class="rojoAdvertencia">*</font>	
					</c:when>

					<c:otherwise>
						<input type="hidden" id="idProductor" name="declaracion.productor.id" value="${declaracionDeExtraccion.entidad.id}">
						<input type="text" value="${declaracionDeExtraccion.entidad.nombre}" class="botonerab" readonly="readonly">
					</c:otherwise>
					
				</c:choose>
			</td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaDeclaracion'/></td>
			<td width="30%" align="left">
				<input id="datepicker" type="text" name="declaracion.fecha" readonly="readonly" class="botonerab" value="${declaracionDeExtraccion.fecha}">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
				<font class="rojoAdvertencia">*</font>					
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.AnioDeclaracion'/></td>
			<td align="left">
				<c:choose>
					<c:when test="${modificacion!='S'}">
						<select id="periodo" name="declaracion.periodo" class="botonerab" onchange="cambioPeriodo();">
							<c:forEach items="${periodos}" var="per">
								<c:choose>
									<c:when test="${per.periodo==declaracionDeExtraccion.periodo}">
										<option value="${per.periodo}" selected="selected">
											<c:out value="${per.periodo}"/>
										</option>
									</c:when>
									<c:otherwise>
										<option value="${per.periodo}">
											<c:out value="${per.periodo}"/>
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>						
					</c:when>
					
					<c:otherwise>
						<input type="hidden" id="periodo" name="declaracion.periodo" value="${declaracionDeExtraccion.periodo}">
						<input type="text" value="${declaracionDeExtraccion.periodo}" class="botonerab" readonly="readonly">
					</c:otherwise>
					
				</c:choose>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td colspan="4">		
				<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="4" class="grisSubtitulo"><bean:message key='SIIM.subTitulo.DatosProductor'/></td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>			
					<tr>
						<td width="20%" class="botoneralNegritaRight">
							Nombre y Apellido
						</td>
						<td width="30%" align="left">
							<input id="nomProductor" class="botonerab" type="text" size="30" readonly="readonly">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Domicilio
						</td>
						<td align="left">
							<input id="domProductor" class="botonerab" type="text" size="30" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td width="20%" class="botoneralNegritaRight">
							Localidad
						</td>
						<td width="30%" align="left">
							<input id="locProductor" class="botonerab" type="text" size="30" readonly="readonly">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Telefono
						</td>
						<td align="left">
							<input id="telProductor" class="botonerab" type="text" size="30" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>				
				</table>		
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
							<c:choose>
								<c:when test="${modificacion!='S'}">
									<select id="idZonaExtraccion" name="declaracion.localizacion.id" class="botonerab" disabled="disabled" onchange="cambiarZonaExtraccion();">
										<option value="-1">--Seleccione una Zona--</option>
									</select>
									<font class="rojoAdvertencia">*</font>
								</c:when>
								
								<c:otherwise>
									<input type="hidden" id="idZonaExtraccion" name="declaracion.localizacion.id" value="${declaracionDeExtraccion.localizacion.id}">
									<input type="text" value="${declaracionDeExtraccion.localizacion.razonSocial}" class="botonerab">
								</c:otherwise>
							</c:choose>
						</td>						
					</tr>	
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Domicilio'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="domZona" class="botonerab" type="text" size="25" readonly="readonly" value="${declaracionDeExtraccion.localizacion.domicilio}">
						</td>						
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Superficie'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly" value="${declaracionDeExtraccion.localizacion.superficie}">
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

	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>	

		<!-- Volumenes -->
		<tr>
			<td colspan="4" align="left">
				<div id="e1" style="DISPLAY: ">
					<label onclick="javascript:exp('1')"> 
						<img src="../../imagenes/expand.gif" border="0" /> 
						<U class="azulOpcion">
							<bean:message key='SIIM.label.Volumenes'/>						
						</U>
						<BR>
					</label>
				</div>
				<div id="c1" style="DISPLAY: none">
					<label onclick="javascript:col('1')"> 
						<img src="../../imagenes/collapse.gif" border="0" /> 
						<U class="azulOpcion">
							<bean:message key='SIIM.label.Volumenes'/>						
						</U>
						<BR>
					</label>
					<br>
					<table border="0" class="cuadrado" align="center" width="90%"
						cellpadding="2" cellspacing="0" id="tablaImportes">
						
						<!-- 1er TRIMESTRE -->
						<tr>
							<td width="13%" class="grisSubtituloCenter">Trimestre</td>						
							<td width="16%" class="grisSubtituloCenter"><bean:message key='SIIM.label.TipoDeProducto'/></td>
							<td width="13%" class="grisSubtituloCenter">Enero</td>
							<td width="13%" class="grisSubtituloCenter">Febrero</td>
							<td width="13%" class="grisSubtituloCenter">Marzo</td>
							<td width="14%" class="grisSubtituloCenter">Total</td>
							<td width="18%" class="grisSubtituloCenter">Vencimiento</td>
						</tr>
						<tr>
							<td>
								<input class="botonerab" name="trimestres[0].nroTrimestre" type="text" value="1" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" name="trimestres[0].tipoProducto.id" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[0].volumenPrimerMes" value="${trimestres['1'].volumenPrimerMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(1);" id="id1_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[0].volumenSegundoMes" value="${trimestres['1'].volumenSegundoMes}" onkeypress="javascript:esNumericoConDecimal(event);"
								size="15" onblur="calcularVolumenTotalTrimestre(1);" id="id1_2">
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[0].volumenTercerMes" value="${trimestres['1'].volumenTercerMes}" onkeypress="javascript:esNumericoConDecimal(event);"
								size="15" onblur="calcularVolumenTotalTrimestre(1);" id="id1_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
								size="15" readonly="readonly" id="idTotal1">														
							</td>
							<td>
								<input id="datepickerFechaTrim1" name="trimestres[0].fechaVencimiento" type="text" readonly="readonly" class="botonerab" size="14">
								<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
							</td>
						</tr>
						
						<!-- 2do TRIMESTRE -->
						<tr>
							<td width="13%" class="grisSubtituloCenter">Trimestre</td>						
							<td width="16%" class="grisSubtituloCenter"><bean:message key='SIIM.label.TipoDeProducto'/></td>
							<td width="13%" class="grisSubtituloCenter">Abril</td>
							<td width="13%" class="grisSubtituloCenter">Mayo</td>
							<td width="13%" class="grisSubtituloCenter">Junio</td>
							<td width="14%" class="grisSubtituloCenter">Total</td>
							<td width="18%" class="grisSubtituloCenter">Vencimiento</td>
						</tr>
						<tr>
							<td>
								<input class="botonerab" type="text" name="trimestres[1].nroTrimestre" value="2" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" name="trimestres[1].tipoProducto.id" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[1].volumenPrimerMes" value=""${trimestres['2'].volumenPrimerMes} onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(2);" id="id2_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[1].volumenSegundoMes" value="${trimestres['2'].volumenSegundoMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(2);" id="id2_2">
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[1].volumenTercerMes" value="${trimestres['2'].volumenTercerMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(2);" id="id2_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" id="idTotal2" readonly="readonly">														
							</td>
							<td>
								<input id="datepickerFechaTrim2" name="trimestres[1].fechaVencimiento" type="text" readonly="readonly" class="botonerab" size="14">
								<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
							</td>
						</tr>						
						
						<!-- 3er TRIMESTRE -->
						<tr>
							<td width="13%" class="grisSubtituloCenter">Trimestre</td>						
							<td width="16%" class="grisSubtituloCenter"><bean:message key='SIIM.label.TipoDeProducto'/></td>
							<td width="13%" class="grisSubtituloCenter">Julio</td>
							<td width="13%" class="grisSubtituloCenter">Agosto</td>
							<td width="13%" class="grisSubtituloCenter">Septiembre</td>
							<td width="14%" class="grisSubtituloCenter">Total</td>
							<td width="18%" class="grisSubtituloCenter">Vencimiento</td>
						</tr>
						<tr>
							<td>
								<input class="botonerab" type="text" name="trimestres[2].nroTrimestre" value="3" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" name="trimestres[2].tipoProducto.id" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[2].volumenPrimerMes" value="${trimestres['3'].volumenPrimeroMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(3);" id="id3_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[2].volumenSegundoMes" value="${trimestres['3'].volumenSegundoMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(3);" id="id3_2">
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[2].volumenTercerMes" value="${trimestres['3'].volumenTercerMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(3);" id="id3_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" id="idTotal3" readonly="readonly">														
							</td>
							<td>
								<input id="datepickerFechaTrim3" name="trimestres[2].fechaVencimiento" type="text" readonly="readonly" class="botonerab" size="14">
								<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
							</td>
						</tr>						
												
						<!-- 4to TRIMESTRE -->
						<tr>
							<td width="13%" class="grisSubtituloCenter">Trimestre</td>						
							<td width="16%" class="grisSubtituloCenter"><bean:message key='SIIM.label.TipoDeProducto'/></td>
							<td width="13%" class="grisSubtituloCenter">Octubre</td>
							<td width="13%" class="grisSubtituloCenter">Noviembre</td>
							<td width="13%" class="grisSubtituloCenter">Diciembre</td>
							<td width="14%" class="grisSubtituloCenter">Total</td>
							<td width="18%" class="grisSubtituloCenter">Vencimiento</td>
						</tr>
						<tr>
							<td>
								<input class="botonerab" type="text" name="trimestres[3].nroTrimestre" value="4" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" name="trimestres[3].tipoProducto.id" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[3].volumenPrimerMes" value="${trimestres['4'].volumenPrimeroMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(4);" id="id4_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[3].volumenSegundoMes" value="${trimestres['4'].volumenSegundoMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(4);" id="id4_2">
							</td>
							<td>
								<input class="botonerab" type="text" name="trimestres[3].volumenTercerMes" value="${trimestres['4'].volumenTercerMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(4);" id="id4_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" id="idTotal4" readonly="readonly">														
							</td>
							<td>
								<input id="datepickerFechaTrim4" name="trimestres[3].fechaVencimiento" type="text" readonly="readonly" class="botonerab" size="14">
								<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
							</td>
						</tr>

					</table>

					<table border="0" class="cuadrado" align="center" width="90%"
						cellpadding="2" cellspacing="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td width="55%">&nbsp;</td>
							<td width="13%" class="botoneralNegritaRight">Volúmen Total</td>
							<td width="14%">
								<input id="idVolumenTotal" readonly="readonly" class="botonerab" type="text" size="15">
							</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td width="55%">&nbsp;</td>
							<td width="13%" class="botoneralNegritaRight">Regalía Minera</td>
							<td width="14%">
								<input id="idRegalia" readonly="readonly" class="botonerab" type="text" 
										value="${productoTurba.regaliaMinera}" size="15">
							</td> 
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td width="55%">&nbsp;</td>
							<td colspan="2"><hr></td>
							<td width="18%">&nbsp;</td>
						</tr>						
						<tr>
							<td width="55%">&nbsp;</td>
							<td width="13%" class="botoneralNegritaRight">IMPORTE TOTAL</td>
							<td width="14%">
								<input id="idImporteTotal" name="declaracion.importeTotal" readonly="readonly" class="botonerab" type="text" size="15">
							</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
					</table>

				</div>
			</td>
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
						<td colspan="5" class="azulAjustado"><bean:message key='SIIM.label.BoletasDeposito'/></td>
					</tr>				
					<tr>
						<td colspan="5"  class="azulAjustado">
							<input type="button" class="botonerab" onclick="expBoletasImpagas();" value="Expandir Boletas Impagas">
							<input type="button" class="botonerab" onclick="expBoletasPagas();" value="Expandir Boletas Pagas">
							<input type="button" class="botonerab" onclick="expBoletaNro();" value="Expandir Boleta Nro">
							<input type="text" value="" id="expBoleta">
						</td>
					</tr>
					<tr>
						<td height="10" colspan="5"></td>
					</tr>
					<tr>
						<td colspan="5">
							<table border="0" class="cuadrado" align="center" width="100%" cellpadding="2">
								<input type="hidden" name="boletasDeposito[0].anulado" value="false"/>

								<tr onclick="$('#idTrBoleta${boletas[0].numero}').toggle();">
									<td colspan="5" class="grisSubtitulo" id="tdBoleta1" 									
										onmouseover="javascript:pintarFilaVale('tdBoleta1');"
										onmouseout="javascript:despintarFilaVale('tdBoleta1');">
										Boleta de Deposito n° ${boletas[0].numero}
									</td>
								</tr>
								
								<tr id="idTrBoleta${boletas[0].numero}">
									<td>
										<table id="tBoleta1" border="0" align="center" width="100%" cellpadding="2">
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.NroBoleta'/>
												</td>
												<td width="40%" align="left">
													<c:choose>
														<c:when test="${boletas[0].fechaPago!=null}">
															<input name="boletasDeposito[0].numero" class="botonerab" type="text" readonly="readonly"
																size="20" onkeypress="javascript:esNumerico(event);" value="${boletas[0].numero}">
														</c:when>
														<c:otherwise>
															<input name="boletasDeposito[0].numero" class="botonerab" type="text"
																size="20" onkeypress="javascript:esNumerico(event);" value="${boletas[0].numero}">
														</c:otherwise>
													</c:choose>
													<font class="rojoAdvertencia">*</font>
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.Productor'/>
												</td>
												<td width="40%" align="left">
													<input id="idProductor0" value="" class="botonerab" type="text" size="40" readonly="readonly">
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.Concepto'/>
												</td>
												<td colspan="4" align="left">
													<c:choose>
														<c:when test="${boletas[0].fechaPago!=null}">
															<input name="boletasDeposito[0].concepto" class="botonerab" type="text" size="90" readonly="readonly"
																value="Aforo" value="${boletas[0].concepto}">
														</c:when>
														<c:otherwise>
															<input name="boletasDeposito[0].concepto" class="botonerab" type="text" size="90"
															value="Aforo" value="${boletas[0].concepto}">
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.Area'/>
												</td>
												<td colspan="4" align="left">
													<c:choose>
														<c:when test="${boletas[0].fechaPago!=null}">
															<input name="boletasDeposito[0].area" class="botonerab" type="text" size="90" readonly="readonly" 
																	value="Dirección General de Minería" value="${boletas[0].area}">
														</c:when>
														<c:otherwise>
															<input name="boletasDeposito[0].area" class="botonerab" type="text" size="90" 
																	value="Dirección General de Minería" value="${boletas[0].area}">
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.EfectivoCheque'/>
												</td>
												<td width="40%" align="left">
													<c:choose>
														<c:when test="${boletas[0].fechaPago!=null}">
															<input name="boletasDeposito[0].efectivoCheque" class="botonerab" readonly="readonly" 
																type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);" value="${boletas[0].efectivoCheque}">
														</c:when>
														<c:otherwise>
															<input name="boletasDeposito[0].efectivoCheque" class="botonerab" 
																type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);" value="${boletas[0].efectivoCheque}">
														</c:otherwise>
													</c:choose>
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.Monto$'/>
												</td>
												<td width="40%" align="left">
													<c:choose>
														<c:when test="${boletas[0].fechaPago!=null}">
															<input name="boletasDeposito[0].monto" class="botonerab" type="text" readonly="readonly"
																size="20" onkeypress="javascript:esNumericoConDecimal(event);" value="${boletas[0].monto}">
														</c:when>
														<c:otherwise>
															<input name="boletasDeposito[0].monto" class="botonerab" type="text"
																size="20" onkeypress="javascript:esNumericoConDecimal(event);" value="${boletas[0].monto}">
														</c:otherwise>
													</c:choose>
													<font class="rojoAdvertencia">*</font>
												</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaVencimiento'/></td>
												<td align="left">
													<input id="datepicker0" type="text" readonly="readonly" class="botonerab"
															name='<%="boletasDeposito[0].fechaVencimiento"%>' value="${boletas[0].fechaVencimientoStr}">
													<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
														align="top" width='17' height='21'>															
													<font class="rojoAdvertencia">*</font>
												</td>
												<c:if test="${boletas[0].fechaPago==null}">
													<script>
														$( "#datepicker0" ).datepicker({ dateFormat: 'dd/mm/yy'});
													</script>
												</c:if>
												
												<td width="15%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.FechaPago'/>
												</td>
												<td width="23%" align="left">
													<input type="text" id="idFechaPago${boletas[0].id}" readonly="readonly" class="botonerab" size="17"
														   value="<c:out value='${boletas[0].fechaPagoStr}'/>">
													<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
														 align="top" width='17' height='21'>																						
												</td>										
												<c:choose>
													<c:when test="${boletas[0].fechaPago==null}">
														<td id="idEstadoBoleta${boletas[0].id}" width="17%" class="rojoAdvertenciaLeft">
															<bean:message key='SIIM.label.NOPAGADA'/>
														</td>		
													</c:when>
													<c:otherwise>
														<td id="idEstadoBoleta${boletas[0].id}" width="17%" class="verdeExitoLeft">
															<bean:message key='SIIM.label.PAGADA'/>
														</td>
													</c:otherwise>
												</c:choose>	
																	
											</tr>
											
											<tr>
												<td height="5" colspan="5"></td>
											</tr>
														
										</table>
									</td>
								</tr>
								
										
							</table>								
		
		
							<table  class="cuadradoSinBorde" align="center" width="100%" cellpadding="2">
								<tr>
									<td height="5" colspan="5"></td>
								</tr>
								<tr>
									<td colspan="5" class="grisSubtitulo">
										Agregar Cuotas Nuevas
									</td>
								</tr>
								
							</table>
				
				
				
							<div id="dummy" style="display: none"></div>
							<div id="divPlanDePagos2"></div>
							
							<table  class="cuadradoSinBorde" align="center" width="100%" cellpadding="2">
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
				<select id="idLocalidad" class="botonerab" name="declaracion.localidad.id">
					<option value="-1">- Seleccione una Localidad -</option>
					<c:forEach items="${localidades}" var="localidad">
						<c:choose>
							<c:when test="${localidad.id==declaracionDeExtraccion.localidad.id}">
								<option value="${localidad.id}" selected="selected">
									<c:out value="${localidad.nombre}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value="${localidad.id}">
									<c:out value="${localidad.nombre}"></c:out>
								</option>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
				</select>				
				<font class="rojoAdvertencia">*</font>
			</td>
			<td width="30%" class="botoneralNegritaRight">&nbsp;</td>
			<td align="left">
				<font class="rojoAdvertenciaChico">* Campos Obligatorios</font>	
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
				<input type="button" value="Aceptar" id="enviar" 
					class="botonerab" onclick="javascript:submitir();" > 
				<input type="button" class="botonerab" value="Cancelar" onclick="javascript:volverAltaDeclaracionExtraccion();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</html:form>
</div>

<script>
	if('${modificacion}'!="S"){
		cambioPeriodo();
	}
	$("#nroDeclaracion").focus();
	setModificacion();
</script>