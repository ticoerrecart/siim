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

<script>
	$(function() {
		$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});
	});

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volverAltaDeclaracionExtraccion(){
	parent.location = contextRoot() + '/jsp.do?page=.index';		
}

function volverModificacionDeclaracionExtraccion(){
	var idProductor = $("#idProductor").val();
	var idLocalizacion = $("#idZonaExtraccion").val();
	var idPeriodo = $("#periodo").val();
	var params = "&idProductor=" + idProductor + "&idLocalizacion=" + idLocalizacion + "&idPeriodo=" + idPeriodo; 
	parent.location = contextRoot() + '/declaracionExtraccion.do?metodo=cargarProductoresParaModificacionDeDeclaracion' + params;		
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

	$('#dummy').load('/SIIM/jsp/bloqueBoletaDeposito.jsp?nombreProductor='+ nombre +'&indice=' + indice, 
			function(){
		var i = "divPlanDePagos"+indice;
				document.getElementById(i).innerHTML = document.getElementById("dummy").innerHTML;
				document.getElementById("dummy").innerHTML = "";

				var ind = indice;
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

	<c:choose>
		<c:when test="${modificacion!='S'}">
			if(indice == 2){
				document.getElementById("idBotonRemoverCuota").disabled="disabled";
			}																			
		</c:when>
		<c:otherwise>
			if(indice == ${fn:length(boletas)+1}){
				document.getElementById("idBotonRemoverCuota").disabled="disabled";
			}
		</c:otherwise>
		
	</c:choose>
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
	
	var imp1 = new Number($('#idVolxRegalia1').val()).toFixed(2);
	var imp2 = new Number($('#idVolxRegalia2').val()).toFixed(2);
	var imp3 = new Number($('#idVolxRegalia3').val()).toFixed(2);
	var imp4 = new Number($('#idVolxRegalia4').val()).toFixed(2);

	var impTotal =  parseFloat(new Number(imp1)+new Number(imp2)+new Number(imp3)+new Number(imp4));
	$('#idImporteTotal').val(new Number(impTotal).toFixed(2));	

	/*var regalia = new Number($('#idRegalia').val()).toFixed(2);
	var importeTotal = parseFloat(new Number(volTotal)*new Number(regalia));
	$('#idImporteTotal').val(new Number(importeTotal).toFixed(2));*/
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
	
	var volTotal =  new Number($("#idTotal1").val()) + new Number($("#idTotal2").val()) + new Number($("#idTotal3").val()) + new Number($("#idTotal4").val());
	$('#idVolumenTotal').val(volTotal);

	$('#idVolxRegalia' +nroTrimestre).val( $('#idTotal'+nroTrimestre).val() * $('#idRegalia' + nroTrimestre).val());
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
			cambioPeriodo();
		}
	}
	

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
				<c:choose>
					<c:when test="${modificacion!='S'}">
						<bean:message key='SIIM.titulo.AltaDeclaracionExtraccion'/>
					</c:when>
					<c:otherwise>
						<bean:message key='SIIM.label.ModificacionDeDeclaracion'/>
					</c:otherwise>
				</c:choose>
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
									<input type="text" value="${declaracionDeExtraccion.localizacion.razonSocial}" class="botonerab" readonly="readonly">
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
						
						<!-- TRIMESTRE x-->
						<c:forEach var="indMes" begin="1" end="4">
							<tr>
								<td width="9%" class="grisSubtituloCenter">Trimestre</td>						
								<td width="14%" class="grisSubtituloCenter"><bean:message key='SIIM.label.TipoDeProducto'/></td>
								<c:forEach items="${meses[indMes]}" var="mes">
									<td width="10%" class="grisSubtituloCenter">${mes}</td>
								</c:forEach>
								<td width="10%" class="grisSubtituloCenter">Vol. Total</td>
								<td width="13%" class="grisSubtituloCenter">Regalía Minera</td>
								<td width="10%" class="grisSubtituloCenter">Importe</td>
								<td width="14%" class="grisSubtituloCenter">Vencimiento</td>
							</tr>
							<tr>
								<td >
									<input class="botonerab" name="trimestres[${indMes-1}].nroTrimestre" type="text" value="${indMes}" readonly="readonly" size="4">																						
								</td>						
								<td >
									<input type="hidden" name="trimestres[${indMes-1}].tipoProducto.id" value="${productoTurba.id}">
									<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="12">																						
								</td>
								
								<td >
									<input class="botonerab" type="text" name="trimestres[${indMes-1}].volumenPrimerMes" value="${trimestres[indMes].volumenPrimerMes}" onkeypress="javascript:esNumericoConDecimal(event);"
										size="10" onblur="calcularVolumenTotalTrimestre(${indMes});" id="id${indMes}_1">																	
								</td>
								<td >
									<input class="botonerab" type="text" name="trimestres[${indMes-1}].volumenSegundoMes" value="${trimestres[indMes].volumenSegundoMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="10" onblur="calcularVolumenTotalTrimestre(${indMes});" id="id${indMes}_2">
								</td>
								<td >
									<input class="botonerab" type="text" name="trimestres[${indMes-1}].volumenTercerMes" value="${trimestres[indMes].volumenTercerMes}" onkeypress="javascript:esNumericoConDecimal(event);"
									size="10" onblur="calcularVolumenTotalTrimestre(${indMes});" id="id${indMes}_3">		
								</td>
								
								<td >
									<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="10" readonly="readonly" id="idTotal${indMes}">
								</td>
								<td>
									<c:choose>
										<c:when test="${trimestres[indMes].regaliaMinera!=null && trimestres[indMes].regaliaMinera>0}">
											x
											<input id="idRegalia${indMes}" name="trimestres[${indMes-1}].regaliaMinera" readonly="readonly" class="botonerab" type="text"
												value="${trimestres[indMes].regaliaMinera}" size="11">
											=	
										</c:when>
										<c:otherwise>
											x
											<input id="idRegalia${indMes}" name="trimestres[${indMes-1}].regaliaMinera" readonly="readonly" class="botonerab" type="text"
												value="${productoTurba.regaliaMinera}" size="11">
											=	
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${trimestres[indMes].regaliaMinera!=null && trimestres[indMes].regaliaMinera>0}">
											<input id="idVolxRegalia${indMes}" readonly="readonly" class="botonerab" type="text"
														value="${trimestres[indMes].importeTotal}" size="10">
										</c:when>
										<c:otherwise>
											<input id="idVolxRegalia${indMes}" readonly="readonly" class="botonerab" type="text"
														value="0" size="10">
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<input id="datepickerFechaTrim${indMes}" name="trimestres[${indMes-1}].fechaVencimiento" value="" type="text" readonly="readonly" class="botonerab" size="11">
									<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
								</td>
							</tr>
							
							<c:if test="${trimestres[indMes].importeTotal== null || trimestres[indMes].importeTotal== 0}">
								<script>
									//$( "#datepickerFechaTrim${indMes}" ).datepicker({ dateFormat: 'dd/mm/yy'});
								</script>
							</c:if>
							
						</c:forEach>
						
					</table>

					<table border="0" class="cuadrado" align="center" width="90%"
						cellpadding="2" cellspacing="0">
						<tr>
							<td colspan="5">&nbsp;</td>
						</tr>
						<tr>							
							<td width="53%" class="botoneralNegritaRight">Volúmen Total</td>
							<td width="10%">
								<input id="idVolumenTotal" readonly="readonly" class="botonerab" type="text" size="10">
							</td>
							<td width="13%" class="botoneralNegritaRight">IMPORTE TOTAL</td>
							<td width="10%">
								<input id="idImporteTotal" name="declaracion.importeTotal" readonly="readonly" class="botonerab" type="text" size="10">
							</td>
							<td width="14%">&nbsp;</td>							
						</tr>
						<tr>
							<td colspan="5">&nbsp;</td>
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
					
					<c:choose>
						<c:when test="${modificacion!='S'}"><!-- ALTA -->
							<tr>
								<td colspan="5">
									<table border="0" class="cuadradoSinBorde" align="center" width="90%" cellpadding="2">
										<input type="hidden" name="boletasDeposito[0].anulado" value="false"/>
										<input type="hidden" name="boletasDeposito[0].idBoleta" value="}"/>
		
										<tr onclick="$('#idTrBoleta1').toggle();">
											<td colspan="5" class="grisSubtitulo" id="tdBoleta1" 									
												onmouseover="javascript:pintarFilaVale('tdBoleta1');"
												onmouseout="javascript:despintarFilaVale('tdBoleta1');">
												Boleta de Deposito n° 1
											</td>
										</tr>
										
										<tr id="idTrBoleta1">
											<td>
												<table id="tBoleta1" border="0" class="cuadrado" align="center" width="100%" cellpadding="2">
													<tr>
														<td height="5" colspan="5"></td>
													</tr>										
													<tr>
														<td width="10%" class="botoneralNegritaRight">
															<bean:message key='SIIM.label.NroBoleta'/>
														</td>
														<td width="35%" align="left">
															
															<input name="boletasDeposito[0].numero" class="botonerab" type="text"
																size="20" onkeypress="javascript:esNumerico(event);">
													
															<font class="rojoAdvertencia">*</font>
														</td>
														<td width="15%" class="botoneralNegritaRight">
															<bean:message key='SIIM.label.Productor'/>
														</td>
														<td width="40%" align="left" colspan="2">
															<input id="idProductor0" value="" class="botonerab" type="text" size="40" readonly="readonly">
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight">
															<bean:message key='SIIM.label.Concepto'/>
														</td>
														<td colspan="4" align="left">
															
															<input name="boletasDeposito[0].concepto" class="botonerab" type="text" size="90"
															value="Aforo">
															
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight">
															<bean:message key='SIIM.label.Area'/>
														</td>
														<td colspan="4" align="left">
															<input name="boletasDeposito[0].area" class="botonerab" type="text" size="90" 
																value="Dirección General de Minería">
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight">
															<bean:message key='SIIM.label.EfectivoCheque'/>
														</td>
														<td width="35%" align="left">
															<input name="boletasDeposito[0].efectivoCheque" class="botonerab" 
																	type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);">
														</td>
														<td width="15%" class="botoneralNegritaRight">
															<bean:message key='SIIM.label.Monto$'/>
														</td>
														<td width="40%" align="left" colspan="2">
															<input name="boletasDeposito[0].monto" class="botonerab" type="text"
																size="20" onkeypress="javascript:esNumericoConDecimal(event);">
															<font class="rojoAdvertencia">*</font>
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaVencimiento'/></td>
														<td align="left" width="35%">
															<input id="datepicker0" type="text" readonly="readonly" class="botonerab"
																	name="boletasDeposito[0].fechaVencimiento">
															<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
																align="top" width='17' height='21'>															
															<font class="rojoAdvertencia">*</font>
														</td>

														<script>
															$( "#datepicker0" ).datepicker({ dateFormat: 'dd/mm/yy'});
														</script>

														
														<td width="15%" class="botoneralNegritaRight">
															&nbsp;
														</td>
														<td width="40%" align="left">
															&nbsp;
														</td>
														
														<td width="17%">
														</td>
																			
													</tr>
													
													<tr>
														<td height="5" colspan="5"></td>
													</tr>
																
												</table><!-- id="tBoleta1" -->
											</td>
										</tr>
									</table>
									
									<div id="divPlanDePagos2"></div>
																	
								</td>
							</tr>
						</c:when>
						
						<c:otherwise><!-- MODIFICACION -->
							<c:forEach items="${boletas}" var="boleta" varStatus="status">
								<tr>
									<td colspan="5">
										<table border="0" class="cuadradoSinBorde" align="center" width="90%" cellpadding="2">
											<input type="hidden" name="boletasDeposito[${status.index}].anulado" value="false"/>
											<input type="hidden" name="boletasDeposito[${status.index}].idBoleta" value="${boleta.id}"/>
			
											<tr onclick="$('#idTrBoleta${status.count}').toggle();">
												<td colspan="5" class="grisSubtitulo" id="tdBoleta${status.count}" 									
													onmouseover="javascript:pintarFilaVale('tdBoleta${status.count}');"
													onmouseout="javascript:despintarFilaVale('tdBoleta${status.count}');">
													Boleta de Deposito n° ${status.count} 
												</td>
											</tr>

											<tr id="idTrBoleta${status.count}">
												<td>
													<table id="tBoleta${status.count}" border="0" class="cuadrado" align="center" width="100%" cellpadding="2">
														<tr>
															<td width="10%" class="botoneralNegritaRight">
																<bean:message key='SIIM.label.NroBoleta'/>
															</td>
															<td width="35%" align="left">
																<c:choose>
																	<c:when test="${boleta.fechaPago!=null}">
																		<input name="boletasDeposito[${status.index}].numero" class="botonerab" type="text" readonly="readonly"
																			size="20" onkeypress="javascript:esNumerico(event);" value="${boleta.numero}">
																	</c:when>
																	<c:otherwise>
																		<input name="boletasDeposito[${status.index}].numero" class="botonerab" type="text"
																			size="20" onkeypress="javascript:esNumerico(event);" value="${boleta.numero}">
																	</c:otherwise>
																</c:choose>
																<font class="rojoAdvertencia">*</font>
															</td>
															<td width="15%" class="botoneralNegritaRight">
																<bean:message key='SIIM.label.Productor'/>
															</td>
															<td width="40%" align="left" colspan="2">
																<input id="idProductor0" value="${declaracionDeExtraccion.entidad.nombre}" class="botonerab" type="text" size="40" readonly="readonly">
															</td>
														</tr>
														<tr>
															<td width="10%" class="botoneralNegritaRight">
																<bean:message key='SIIM.label.Concepto'/>
															</td>
															<td colspan="4" align="left">
																<c:choose>
																	<c:when test="${boleta.fechaPago!=null}">
																		<input name="boletasDeposito[${status.index}].concepto" class="botonerab" type="text" size="90" readonly="readonly"
																			value="Aforo" value="${boleta.concepto}">
																	</c:when>
																	<c:otherwise>
																		<input name="boletasDeposito[${status.index}].concepto" class="botonerab" type="text" size="90"
																		value="Aforo" value="${boleta.concepto}">
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
																	<c:when test="${boleta.fechaPago!=null}">
																		<input name="boletasDeposito[${status.index}].area" class="botonerab" type="text" size="90" readonly="readonly" 
																				value="Dirección General de Minería" value="${boleta.area}">
																	</c:when>
																	<c:otherwise>
																		<input name="boletasDeposito[${status.index}].area" class="botonerab" type="text" size="90" 
																				value="Dirección General de Minería" value="${boleta.area}">
																	</c:otherwise>
																</c:choose>
															</td>
														</tr>
														<tr>
															<td width="10%" class="botoneralNegritaRight">
																<bean:message key='SIIM.label.EfectivoCheque'/>
															</td>
															<td width="35%" align="left">
																<c:choose>
																	<c:when test="${boleta.fechaPago!=null}">
																		<input name="boletasDeposito[${status.index}].efectivoCheque" class="botonerab" readonly="readonly" 
																			type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);" value="${boleta.efectivoCheque}">
																	</c:when>
																	<c:otherwise>
																		<input name="boletasDeposito[${status.index}].efectivoCheque" class="botonerab" 
																			type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);" value="${boleta.efectivoCheque}">
																	</c:otherwise>
																</c:choose>
															</td>
															<td width="15%" class="botoneralNegritaRight">
																<bean:message key='SIIM.label.Monto$'/>
															</td>
															<td width="40%" align="left" colspan="2">
																<c:choose>
																	<c:when test="${boleta.fechaPago!=null}">
																		<input name="boletasDeposito[${status.index}].monto" class="botonerab" type="text" readonly="readonly"
																			size="20" onkeypress="javascript:esNumericoConDecimal(event);" value="${boleta.monto}">
																	</c:when>
																	<c:otherwise>
																		<input name="boletasDeposito[${status.index}].monto" class="botonerab" type="text"
																			size="20" onkeypress="javascript:esNumericoConDecimal(event);" value="${boleta.monto}">
																	</c:otherwise>
																</c:choose>
																<font class="rojoAdvertencia">*</font>
															</td>
														</tr>
														<tr>
															<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaVencimiento'/></td>
															<td align="left" width="35%">
																<input id="datepicker${status.count}" type="text" readonly="readonly" class="botonerab"
																		name="boletasDeposito[${status.index}].fechaVencimiento" value="${boleta.fechaVencimientoStr}">
																<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
																	align="top" width='17' height='21'>															
																<font class="rojoAdvertencia">*</font>
															</td>
															<c:if test="${boleta.fechaPago==null}">
																<script>
																	$( "#datepicker${status.count}" ).datepicker({ dateFormat: 'dd/mm/yy'});
																</script>
															</c:if>
															
															<c:choose>
																<c:when test="${boleta.fechaPago!=null}">">
																	<td width="15%" class="botoneralNegritaRight">
																		<bean:message key='SIIM.label.FechaPago'/>
																	</td>
																	<td width="40%" align="left">
																		<input type="text" id="idFechaPago${boleta.id}" readonly="readonly" class="botonerab" size="17"
																			   value="<c:out value='${boleta.fechaPagoStr}'/>">
																		<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
																			 align="top" width='17' height='21'>
																	</td>
																</c:when>
																<c:otherwise>
																	<td width="15%" class="botoneralNegritaRight">
																		&nbsp;
																	</td>
																	<td width="40%" align="left">
																		&nbsp;
																	</td>
																</c:otherwise>
															</c:choose>
															
															<c:choose>
																<c:when test="${boleta.fechaPago==null}">
																	<td id="idEstadoBoleta${status.count}" class="rojoAdvertenciaLeft">
																		<bean:message key='SIIM.label.NOPAGADA'/>
																	</td>		
																</c:when>
																<c:otherwise>
																	<td id="idEstadoBoleta${status.count}" class="verdeExitoLeft">
																		<bean:message key='SIIM.label.PAGADA'/>
																	</td>
																</c:otherwise>
																
															</c:choose>
																				
														</tr>
														
													</table>												
												</td>
											</tr>
											
											<tr>
												<td height="5" colspan="5">
													<c:choose>
														<c:when test="${boleta.fechaPago==null}">
															<input id="idBotonEliminarCuota${status.count}" type="button"	value="-" onclick="javascript:eliminarCuota(${status.count});">
															<input style="display: none" id="idBotonRestituirCuota${status.count}" type="button"	value="+" onclick="javascript:restituirCuota(${status.count});">
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</table>								
									</td>
								</tr>
								
							</c:forEach>
						</c:otherwise>
						
						
					</c:choose>
				</table>
				
				<c:if test="${modificacion=='S'}"><!-- MODIFICACION -->
					<table  class="cuadradoSinBorde" align="center" width="90%" cellpadding="2">
						<tr>
							<td height="5" colspan="5"></td>
						</tr>
						<tr>
							<td colspan="5" class="grisSubtitulo">
								Agregar Cuotas Nuevas
							</td>
						</tr>
						
					</table>
				</c:if>

				<div id="dummy" style="display: none"></div>
				<c:choose>
					<c:when test="${modificacion=='S'}"><!-- MODIFICACION -->
						<table class="cuadrado" align="center" width="90%" cellpadding="2">
							<tr>
								<td>
									<div id="divPlanDePagos${fn:length(boletas)+1}"></div>
								</td>	
							</tr>		
						</table>	
							
						<script>
							indice = ${fn:length(boletas)} + 1;
						</script>
						
					</c:when>
				</c:choose>
				
				
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
			<td class="botoneralNegritaRight" colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td height="5" colspan="4"></td>
		</tr>
		<tr>
			<td colspan="4">
				<font class="rojoAdvertenciaChico">* Campos Obligatorios</font>	
			</td>
		</tr>		
		<tr>
			<td height="5" colspan="4"></td>
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
				<c:choose>
					<c:when test="${modificacion!='S'}">
						<input type="button" class="botonerab" value="Cancelar" onclick="javascript:volverAltaDeclaracionExtraccion();">
					</c:when>
					<c:otherwise>
						<input type="button" class="botonerab" value="Cancelar" onclick="javascript:volverModificacionDeclaracionExtraccion();">
					</c:otherwise>
				</c:choose>
				
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
