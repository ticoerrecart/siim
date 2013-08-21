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

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<div id="idDeclaracion">


<input id="idProductor" type="hidden" value="${declaracion.entidad.id}">
<input id="idLocalizacion" type="hidden" value="${declaracion.localizacion.id}">
<input id="idPeriodo" type="hidden" value="${declaracion.periodo}">



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
							<bean:message key='SIIM.label.Superficie'/>
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
						<tr>
							<td width="100%" colspan="7" class="grisSubtituloCenter">
								<bean:message key='SIIM.label.Volumenes'/>
							</td>
						</tr>	
							<tr>
							<td width="100%" colspan="7">
								&nbsp;
							</td>
						</tr>
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
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[0].volumenPrimerMes" value="${declaracion.getTrimestre(1).volumenPrimerMes}"  size="15" >																	
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[0].volumenSegundoMes" value="${declaracion.getTrimestre(1).volumenSegundoMes}" size="15" >
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[0].volumenTercerMes" value="${declaracion.getTrimestre(1).volumenTercerMes}" size="15" >		
							</td>
							<td>
								<input id="idTotal1" readonly="readonly" class="botonerab" type="text" value="${declaracion.getTrimestre(1).volumenTotal}" size="15" >														
							</td>
							<td>
								<input readonly="readonly" class="botonerab"  type="text" value="<fmt:formatDate value="${declaracion.getTrimestre(1).fechaVencimiento}" pattern="dd/MM/yyyy" />" size="14">
																
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
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[1].volumenPrimerMes" value="${declaracion.getTrimestre(2).volumenPrimerMes}" size="15" >																	
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[1].volumenSegundoMes" value="${declaracion.getTrimestre(2).volumenSegundoMes}" size="15" >
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[1].volumenTercerMes" value="${declaracion.getTrimestre(2).volumenTercerMes}" size="15" >		
							</td>
							<td>
								<input id="idTotal2" readonly="readonly" class="botonerab" type="text" value="${declaracion.getTrimestre(2).volumenTotal}" size="15" >														
							</td>
							<td>
								<input readonly="readonly" class="botonerab"  type="text" value="<fmt:formatDate value="${declaracion.getTrimestre(2).fechaVencimiento}" pattern="dd/MM/yyyy" />" size="14">								
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
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[2].volumenPrimerMes" value="${declaracion.getTrimestre(3).volumenPrimerMes}" size="15" >																	
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[2].volumenSegundoMes" value="${declaracion.getTrimestre(3).volumenSegundoMes}" size="15">
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[2].volumenTercerMes" value="${declaracion.getTrimestre(3).volumenTercerMes}" size="15">		
							</td>
							<td>
								<input id="idTotal3" readonly="readonly" class="botonerab" type="text" value="${declaracion.getTrimestre(3).volumenTotal}" size="15" >														
							</td>
							<td>
								<input readonly="readonly" class="botonerab"  type="text" value="<fmt:formatDate value="${declaracion.getTrimestre(3).fechaVencimiento}" pattern="dd/MM/yyyy" />" size="14">								
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
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[3].volumenPrimerMes" value="${declaracion.getTrimestre(4).volumenPrimerMes}" size="15" >																	
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[3].volumenSegundoMes" value="${declaracion.getTrimestre(4).volumenSegundoMes}" size="15" >
							</td>
							<td>
								<input readonly="readonly" class="botonerab" type="text" name="trimestres[3].volumenTercerMes" value="${declaracion.getTrimestre(4).volumenTercerMes}" size="15" >
							</td>
							<td>
								<input id="idTotal4" readonly="readonly" class="botonerab" type="text" value="${declaracion.getTrimestre(4).volumenTotal}" size="15" >														
							</td>
							<td>
								<input readonly="readonly" class="botonerab"  type="text" value="<fmt:formatDate value="${declaracion.getTrimestre(4).fechaVencimiento}" pattern="dd/MM/yyyy" />" size="14">								
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
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4">
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volver();" />
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>

</div>
