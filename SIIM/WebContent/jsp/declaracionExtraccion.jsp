<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volverAltaDeclaracionExtraccion(){
	parent.location = contextRoot() + '/jsp.do?page=.index';		
}

function volver(){
	
	var periodo = $("#idPeriodo").val();
	var productor = $("#idProductor").val();
	var localizacion = $("#idLocalizacion").val();
	
	parent.location = contextRoot() + '/declaracionExtraccion.do?metodo=cargarProductoresParaModificacionDeDeclaracion&consulta=S&idProductor='+productor+'&idLocalizacion='+localizacion+'&idPeriodo='+periodo;		
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

function imprimir(){
	
	var idDeclaracion = $('#idDecExt').val();
	
	var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
	if(type == "IE"){
		window.open("./reportes.do?metodo=generarReporteDeclaracionExtraccion&idDeclaracion="+idDeclaracion,"",especificaciones);
	}else{
		window.open("../../reportes.do?metodo=generarReporteDeclaracionExtraccion&idDeclaracion="+idDeclaracion,"",especificaciones);
	}
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<div id="idDeclaracion">


<input id="idProductor" type="hidden" value="${declaracion.entidad.id}">
<input id="idLocalizacion" type="hidden" value="${declaracion.localizacion.id}">
<input id="idPeriodo" type="hidden" value="${declaracion.periodo}">
<input id="idDecExt" type="hidden" value="${declaracion.id}">


	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIM.titulo.ConsultaDeclaracionExtraccion'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Numero'/></td>
			<td width="30%" align="left">
				<input id="nroDeclaracion" name="declaracion.numero" class="botonerab" type="text" size="20"
						readonly="readonly" value="${declaracion.numero}" />
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td align="left">
				<input name="declaracion.productor.id" class="botonerab" type="text" size="20"
						readonly="readonly" value="${declaracion.entidad.nombre}" />
			</td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaDeclaracion'/></td>
			<td width="30%" align="left">
				
				<input type="text" name="declaracion.fecha" readonly="readonly" class="botonerab" value="${declaracion.fecha}">				
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.AnioDeclaracion'/></td>
			<td align="left">
				<input name="declaracion.periodo" class="botonerab" type="text" size="20" readonly="readonly" value="${declaracion.periodo}" >
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
							<input id="nomProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${declaracion.entidad.nombre}">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Domicilio
						</td>
						<td align="left">
							<input id="domProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${declaracion.entidad.direccion}">
						</td>
					</tr>
					<tr>
						<td width="20%" class="botoneralNegritaRight">
							Localidad
						</td>
						<td width="30%" align="left">
							<input id="locProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${declaracion.entidad.localidad.nombre}">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Telefono
						</td>
						<td align="left">
							<input id="telProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${declaracion.entidad.telefono}">
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
							<input type="text" name="declaracion.localizacion" readonly="readonly" class="botonerab" value="${declaracion.localizacion.razonSocial}" />			
						</td>						
					</tr>	
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Domicilio'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="domZona" class="botonerab" type="text" size="25" readonly="readonly" value="${declaracion.localizacion.domicilio}">
						</td>						
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Superficie'/>(ha)
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly" value="${declaracion.localizacion.superficie}">
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
							<td width="10%" class="grisSubtituloCenter">Importe $</td>
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
								<c:choose>
									<c:when test="${trimestres[indMes] != null}">
										<input class="botonerab" type="text" value="${trimestres[indMes].volumenTotal} m³" size="10" readonly="readonly">
									</c:when>
									<c:otherwise>
										<input class="botonerab" type="text" value="${trimestres[indMes].volumenTotal}" size="10" readonly="readonly">
									</c:otherwise>
								</c:choose>
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
							<input readonly="readonly" class="botonerab" type="text" size="10" value="${declaracion.volumenTotal} m³">
						</td>
						<td width="13%" class="botoneralNegritaRight">IMPORTE TOTAL $</td>
						<td width="10%">
							<input readonly="readonly" class="botonerab" type="text" size="10" value="${declaracion.importeTotal}">
						</td>
						<td width="14%">&nbsp;</td>							
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>
					</tr>
				</table>				
			</td>
		</tr>

		<script>
			calcularImporteTotal();
		</script>
		<!-- PLAN DE PAGO -->

		<tr>
			<td height="10" colspan="4"></td>
		</tr>	
		<tr>
			<td colspan="4">		
				<table class="cuadrado" align="center" width="90%" cellpadding="2"> 
					<tr>
						<td colspan="4" class="grisSubtituloCenter"><bean:message key='SIIM.label.BoletasDeposito'/></td>
					</tr>				

								<c:forEach items="${declaracion.boletas}" var="boletaDeposito" varStatus="index">
								
									<tr>								
										<td colspan="5" class="grisSubtitulo" id="tdBoleta<c:out value='${boletaDeposito.numero}'></c:out>" >
											Boleta de Deposito n° <c:out value="${boletaDeposito.numero}"></c:out>
										</td>
									</tr>						
								
									<tr id="idTrBoleta${boletaDeposito.numero}" style="display: ">
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
														<input value="${declaracion.localizacion.razonSocial}"
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
	
	
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Localidad'/></td>
			<td width="30%" align="left">
			
				<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly"
						value="${declaracion.localidad.nombre}">		
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
			<td height="10" colspan="3"></td>
		</tr>
		<tr>
			<td width="48%" align="right">
				<input type="button" class="botonerab" value="Imprimir" onclick="javascript:imprimir();" />
			</td>	
			<td width="4%"></td> 	
			<td width="48%" align="left">
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volver();" />
			</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td> 
		</tr>
	</table>

</div>
