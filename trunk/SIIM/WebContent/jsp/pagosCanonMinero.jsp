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
	src="<html:rewrite page='/dwr/interface/CanonMineroFachada.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/fiscalizacion.js'/>"></script>

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	
<script>
var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";


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



//-----------------------------------------------------//
//FUNCIONES DE PAGO DE CANON MINERO//

	var idBoleta;
	function registrarPagoSeleccionarFecha(boleta,numero){
		/*if(confirm("¿Está seguro que desea registrar el pago?")){
	
			idBoleta = boleta;
			GuiaForestalFachada.registrarPagoBoletaDeposito(idBoleta,registrarPagoCallback);		
		}*/
		$('#idBoletaAPagar').val(boleta);
		$( "#idFechaPagoDatePicker" ).datepicker({ dateFormat: 'dd/mm/yy'});	
		$('#dialogo').dialog({title: 'Registrar Pago Boleta de Deposito nro '+numero, height: 200, width: 500, modal: true});			
	}
	
	function registrarPago(){
	
		var fechaPago = $('#idFechaPagoDatePicker').val();
		if(fechaPago != ""){
			idBoleta = $('#idBoletaAPagar').val();		
			//alert("Se Pago la boleta "+idBoleta+" con la fecha "+fechaPago);	
			cerrarVentanaPagoBoleta();	
			CanonMineroFachada.registrarPagoBoletaDeposito(idBoleta,fechaPago,registrarPagoCallback);		
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

		$('#'+idTd).attr("class", "verdeSubtitulo");	
	}

	function despintarFilaVale(idTd){
		
		$('#'+idTd).attr("class", "grisSubtitulo");		
	}

	function imprimir(){
		
		var idCanon = $('#idCanon').val();
		
		var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
		if(type == "IE"){
			window.open("./reportes.do?metodo=generarReporteCanonMinero&idCanon="+idCanon,"",especificaciones);
		}else{
			window.open("../../reportes.do?metodo=generarReporteCanonMinero&idCanon="+idCanon,"",especificaciones);
		}
	}
	
</script>





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




<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<input type="hidden" id="idCanon" value="${canonMinero.id}" />

<html:form action="canonMinero" styleId="canonMineroFormId">
	<html:hidden property="metodo" value="altaCanonMinero" />
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				${titulo}
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td width="30%" align="left">
				<input type="hidden" id="nombreProductor" value="" />
				<input type="text" class="botonerab" value="${canonMinero.entidad.nombre}" readonly="readonly">
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Periodo'/></td>
			<td align="left">
				<input type="text" class="botonerab" value="${canonMinero.periodo}" readonly="readonly">
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
							<input type="text" class="botonerab" value="${canonMinero.localizacion.razonSocial}" readonly="readonly">
						</td>						
					</tr>	
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Domicilio'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input type="text" class="botonerab" value="${canonMinero.localizacion.domicilio}" readonly="readonly">
						</td>						
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Superficie'/>(ha)
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="supZona" class="botonerab" type="text" value="${canonMinero.localizacion.superficie}" size="25" readonly="readonly">
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
				<input id="idHectareas" type="hidden" name="canonMinero.hectareas">
				<input id="idCantHaXPertenenciaMinera" type="hidden" name="canonMinero.cantHaXPertenenciaMinera">
				<input id="idCantPertenenciasMineras" type="hidden" name="canonMinero.cantPertenenciasMineras">			
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
							<p id="cantHectareas">${canonMinero.hectareas}</p>																						
						</td>						
						<td class="grisMuyClaroSubtituloCenter">
							<p id="haXPertenencia">${canonMinero.cantHaXPertenenciaMinera}</p>																						
						</td>
						<td class="grisMuyClaroSubtituloCenter">
							<p id="cantPertenencias">${canonMinero.cantPertenenciasMineras}</p>																	
						</td>
						<td class="grisMuyClaroSubtituloCenter">							
							<p id="canonXPertenencia">${canonMinero.canonMineroXPertenencia}</p>
						</td>
						<td class="grisMuyClaroSubtituloCenter">
							<p id="montoTotal">${canonMinero.montoTotal}</p>
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
						<td height="10" colspan="4"></td>
					</tr>

					<tr>
						<td colspan="4">
							<c:choose>					
								<c:when test="${fn:length(canonMinero.boletasDeposito)>0}">	
				
									<c:forEach items="${canonMinero.boletasDeposito}" var="boletaDeposito" varStatus="index">
									
										<tr onclick="$('#idTrBoleta<c:out value='${boletaDeposito.numero}'/>').toggle();">								
											<td colspan="5" class="grisSubtitulo" id="tdBoleta<c:out value='${index.count}'></c:out>" 									
												onmouseover="javascript:pintarFilaVale('tdBoleta<c:out value='${index.count}'></c:out>');"
												onmouseout="javascript:despintarFilaVale('tdBoleta<c:out value='${index.count}'></c:out>');">
												Boleta de Deposito n° <c:out value="${boletaDeposito.numero}"></c:out>
											</td>
										</tr>						
									
										<tr id="idTrBoleta${boletaDeposito.numero}" style="display: none">
											<td colspan="4">								
											
												<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
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
															<input value="${canonMinero.localizacion.razonSocial}"
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
												<c:if test="${boletaDeposito.fechaPago ==null && habilitarPagar!=null}">
													<input id="idBotonPago<c:out value='${boletaDeposito.id}'></c:out>"
														   type="button" value="Registrar Pago" class="botonerab" 
														   onclick="registrarPagoSeleccionarFecha('${boletaDeposito.id}','${boletaDeposito.numero}')">
												</c:if>	
											</td>
										</tr>										
									</c:forEach>	
								</c:when>
								<c:otherwise>
									<bean:message key='SIIM.error.NoExiBoletas'/>
								</c:otherwise>													
							</c:choose>									
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
				<input type="text" value="${canonMinero.localidad.nombre}" readonly="readonly" class="botonerab">
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Fecha'/></td>
			<td align="left">
				<input id="datepickerFecha" type="text" value="${canonMinero.fechaStr}" name="canonMinero.fecha" readonly="readonly" class="botonerab">
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
				<c:if test="${habilitarPagar == null}">
					<input type="button" class="botonerab" value="Imprimir" onclick="javascript:imprimir();" />
				</c:if>			
				<input type="button" class="botonerab" value="Volver"
						onclick="javascript:parent.location= contextRoot() + '${volver}'+'&idProductor=${canonMinero.entidad.id}&idLocalizacion=${canonMinero.localizacion.id}&idPeriodo=${canonMinero.periodo}'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</html:form> 				  