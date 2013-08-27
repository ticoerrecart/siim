<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	src="<html:rewrite page='/dwr/interface/DeclaracionDeExtraccionFachada.js'/>"></script>
	
<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	

<script>

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volver(){
	
	var periodo = $("#idPeriodo").val();
	var productor = $("#idProductor").val();
	var localizacion = $("#idLocalizacion").val();
	
	parent.location = contextRoot() + '/declaracionExtraccion.do?metodo=cargarProductoresParaPagoBoletas&idProductor='+productor+'&idLocalizacion='+localizacion+'&idPeriodo='+periodo;		
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

function pintarFila(idTd){

	$('#'+idTd).addClass("verdeSubtitulo");
	$('#'+idTd).removeClass("grisSubtitulo");
}

function despintarFila(idTd){
	
	$('#'+idTd).addClass("grisSubtitulo");
	$('#'+idTd).removeClass("verdeSubtitulo");
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


var idBoleta;
function registrarPagoSeleccionarFecha(boleta,numero){

	$('#idBoletaAPagar').val(boleta);
	$( "#idFechaPagoDatePicker" ).datepicker({ dateFormat: 'dd/mm/yy'});	
	$('#dialogo').dialog({title: 'Registrar Pago Boleta de Deposito nro '+numero, height: 200, width: 500, modal: true});			
}

function registrarPago(){

	var fechaPago = $('#idFechaPagoDatePicker').val();
	if(fechaPago != ""){
		idBoleta = $('#idBoletaAPagar').val();			
		cerrarVentanaPagoBoleta();	
		DeclaracionDeExtraccionFachada.registrarPagoBoletaDeposito(idBoleta,fechaPago,registrarPagoCallback);		
	}
	else{
		$('#textoError').show();
	}
}

function registrarPagoCallback(valor){
	
	var idEstado = "#idEstadoBoleta"+idBoleta;
	$(idEstado).html("PAGADA");	
	$(idEstado).attr('class', "verdeExitoLeft");

	var idBotonPago = "#idBotonPago"+idBoleta;
	$(idBotonPago).toggle();

	var idFechaPago = "#idFechaPago"+idBoleta;	
	$(idFechaPago).attr('value', valor);
}

function cerrarVentanaPagoBoleta(){

	$('#textoError').hide();
	$('#idFechaPagoDatePicker').val("");
	$('#dialogo').dialog( "close" );
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
</script>

<input id="idProductor" type="hidden" value="${declaracionDeExtraccion.entidad.id}">
<input id="idLocalizacion" type="hidden" value="${declaracionDeExtraccion.localizacion.id}">
<input id="idPeriodo" type="hidden" value="${declaracionDeExtraccion.periodo}">

<div id="dialogo" style="display: none" >
	<br>
	<div id="textoError" class="rojoAdvertencia" style="display: none" >Debe especificar una fecha de pago</div>	
	<br>
	<input id="idBoletaAPagar" type="hidden" value="">
	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10" colspan="3"></td>
		</tr>	
		<tr>
			<td width="30%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.FechaPago'/>
			</td>
			<td class="botoneralNegritaRight">
				<input id="idFechaPagoDatePicker" class="botonerab" type="text" size="23"	readonly="readonly">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
			</td>				
			<td width="20%" ></td>			
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
	</table>
	<table border="0" class="cuadradoSinBorde" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10" colspan="3"></td>
		</tr>	
		<tr>
			<td width="48%" class="botonerab" align="right">
				<input type="button" class="botonerab" value="Aceptar" onclick="javascript:registrarPago();">
			</td>
			<td width="4%"></td>			
			<td width="48%" class="botonerab" align="left">
				<input type="button" class="botonerab" value="Cancelar" onclick="javascript:cerrarVentanaPagoBoleta();">
			</td>							
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
	</table>	
</div>


<div id="idDeclaracion">

	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				Pago de Boleta de Depósito
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Numero'/></td>
			<td width="30%" align="left">
				<input id="nroDeclaracion" readonly="readonly" class="botonerab" type="text" size="20"
						value="${declaracionDeExtraccion.numero}">
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td align="left">
			
				<input id="productor" readonly="readonly" class="botonerab" type="text" size="30"
						value="${declaracionDeExtraccion.entidad.nombre}">			
			</td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaDeclaracion'/></td>
			<td width="30%" align="left">
				<input type="text" readonly="readonly" class="botonerab" value="${declaracionDeExtraccion.fecha}">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.AnioDeclaracion'/></td>
			<td align="left">
				<input type="text" readonly="readonly" size="10" class="botonerab" value="${declaracionDeExtraccion.periodo}">			
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
							<input id="nomProductor" class="botonerab" type="text" size="30" readonly="readonly" 
								value="${declaracionDeExtraccion.entidad.nombre}">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Domicilio
						</td>
						<td align="left">
							<input id="domProductor" class="botonerab" type="text" size="30" readonly="readonly"
								value="${declaracionDeExtraccion.entidad.direccion}">
						</td>
					</tr>
					<tr>
						<td width="20%" class="botoneralNegritaRight">
							Localidad
						</td>
						<td width="30%" align="left">
							<input id="locProductor" class="botonerab" type="text" size="30" readonly="readonly"
								value="${declaracionDeExtraccion.entidad.localidad.nombre}">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Telefono
						</td>
						<td align="left">
							<input id="telProductor" class="botonerab" type="text" size="30" readonly="readonly"
								value="${declaracionDeExtraccion.entidad.telefono}">
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
							<input id="domZona" class="botonerab" type="text" size="25" readonly="readonly"
								value="${declaracionDeExtraccion.localizacion.razonSocial}">					
						</td>						
					</tr>	
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Domicilio'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="domZona" class="botonerab" type="text" size="25" readonly="readonly"
								value="${declaracionDeExtraccion.localizacion.domicilio}">
						</td>						
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Superficie'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly"
								value="${declaracionDeExtraccion.localizacion.superficie}">
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
									<input class="botonerab" type="text" value="${indMes}" readonly="readonly" size="4">																						
								</td>						
								<td >
									<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="12">																						
								</td>
								
								<td >
									<input class="botonerab" type="text" value="${trimestres[indMes].volumenPrimerMes}" size="10" readonly="readonly">																	
								</td>
								<td >
									<input class="botonerab" type="text" value="${trimestres[indMes].volumenSegundoMes}" size="10" readonly="readonly">
								</td>
								<td >
									<input class="botonerab" type="text" value="${trimestres[indMes].volumenTercerMes}" size="10" readonly="readonly">		
								</td>
								
								<td >
									<input class="botonerab" type="text" value="${trimestres[indMes].volumenTotal}" size="10" readonly="readonly">
								</td>
								<td>x
									<input readonly="readonly" class="botonerab" type="text" value="${trimestres[indMes].regaliaMinera}" size="11">
									=
								</td>
								<td>
									<input readonly="readonly" class="botonerab" type="text"value="${trimestres[indMes].importeTotal}" size="10">
								</td>
								<td>
									<input value="<fmt:formatDate value="${trimestres[indMes].fechaVencimiento}" pattern="dd/MM/yyyy" />" 
										type="text" readonly="readonly" class="botonerab" size="11">
									<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
								</td>
							</tr>
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
								<input readonly="readonly" class="botonerab" type="text" size="10" value="${declaracionDeExtraccion.volumenTotal}">
							</td>
							<td width="13%" class="botoneralNegritaRight">IMPORTE TOTAL</td>
							<td width="10%">
								<input readonly="readonly" class="botonerab" type="text" size="10" value="${declaracionDeExtraccion.importeTotal}">
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
		<script>
			calcularImporteTotal();
		</script>

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
						<td colspan="4">
	
							<c:forEach items="${declaracionDeExtraccion.boletas}" var="boletaDeposito" varStatus="index">									
								
								<tr onclick="$('#idTrBoleta<c:out value='${boletaDeposito.numero}'/>').toggle();">								
									<td colspan="5" class="grisSubtitulo" id="tdBoleta<c:out value='${boletaDeposito.numero}'></c:out>" 									
										onmouseover="javascript:pintarFila('tdBoleta<c:out value='${boletaDeposito.numero}'></c:out>');"
										onmouseout="javascript:despintarFila('tdBoleta<c:out value='${boletaDeposito.numero}'></c:out>');">
										Boleta de Deposito n° <c:out value="${boletaDeposito.numero}"></c:out>
									</td>
								</tr>						
							
								<tr id="idTrBoleta${boletaDeposito.numero}" style="display: none">
									<td colspan="4">								
									
										<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
											<tr>
												<td height="5" colspan="5"></td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.BoletaDeposito'/>
												</td>
												<td width="35%" align="left">
													<input value="${boletaDeposito.numero}" class="botonerab" type="text"
														   size="20" readonly="readonly">
												</td>
												<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.RazonSocial'/></td>
												<td width="40%" align="left" colspan="2">
													<input value="${declaracionDeExtraccion.localizacion.razonSocial}"
														   class="botonerab" type="text" size="40" readonly="readonly">
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Concepto'/></td>
												<td colspan="4" align="left">
													<input value="${boletaDeposito.concepto}" class="botonerab" type="text" size="94"
													 	   readonly="readonly">
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Area'/></td>
												<td colspan="4" align="left">
													<input value="${boletaDeposito.area}" class="botonerab" type="text" size="94"
														   readonly="readonly">
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.EfectivoCheque'/></td>
												<td width="35%" align="left">
													<input value="${boletaDeposito.efectivoCheque}" class="botonerab"
														   type="text" size="20" readonly="readonly">
												</td>
												<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Monto$'/></td>
												<td width="40%" align="left" colspan="2">
													<input value="${boletaDeposito.monto}" class="botonerab" type="text"
														   size="20" readonly="readonly">
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.FechaVencimiento'/>
												</td>
												<td width="35%" align="left">
													<input type="text" readonly="readonly" class="botonerab" size="17"
														   value="<c:out value='${boletaDeposito.fechaVencimientoStr}'/>">
													<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
														 align="top" width='17' height='21'>		
												</td>
												<td width="15%" class="botoneralNegritaRight">
													<bean:message key='SIIM.label.FechaPago'/>
												</td>
												<td width="23%" align="left">
													<input type="text" id="idFechaPago${boletaDeposito.id}" readonly="readonly" class="botonerab" size="17"
														   value="<c:out value='${boletaDeposito.fechaPagoStr}'/>">
													<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
														 align="top" width='17' height='21'>																						
												</td>										
												<c:choose>
													<c:when test="${boletaDeposito.fechaPago ==null}">
														<td id="idEstadoBoleta${boletaDeposito.id}" width="17%" class="rojoAdvertenciaLeft">
															<bean:message key='SIIM.label.NOPAGADA'/>
														</td>		
													</c:when>
													<c:otherwise>
														<td id="idEstadoBoleta${boletaDeposito.id}" width="17%" class="verdeExitoLeft">
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
									<td>
										<c:if test="${boletaDeposito.fechaPago ==null}">
											<input id="idBotonPago<c:out value='${boletaDeposito.id}'></c:out>"
												   type="button" value="Registrar Pago" class="botonerab" 
												   onclick="registrarPagoSeleccionarFecha('${boletaDeposito.id}','${boletaDeposito.numero}')">
										</c:if>	
									</td>
								</tr>									

							</c:forEach>	
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


	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Localidad'/></td>
			<td width="30%" align="left">
			
				<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly"
						value="${declaracionDeExtraccion.localidad.nombre}">		
			</td>
			<td width="30%" class="botoneralNegritaRight">&nbsp;</td>
			<td align="left">
				&nbsp;
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
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volver();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</div>