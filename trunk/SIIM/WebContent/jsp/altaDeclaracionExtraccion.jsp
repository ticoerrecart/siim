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
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>
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

		$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFecha" ).datepicker({ dateFormat: 'dd/mm/yy'});	
		$( "#datepickerFechaTrim1" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFechaTrim2" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFechaTrim3" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFechaTrim4" ).datepicker({ dateFormat: 'dd/mm/yy'});	
	});

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volverAltaGuia(){	
	var entidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();
	parent.location = contextRoot() +
	'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaAltaGFB&idTipoDeEntidad=' + entidad +  '&idProductor=' + productor;		
}

function setValorLocalizacion(valor){
	$("#idLocalizacion").val(valor);
}

function submitir(){
	validarForm("guiaForestalForm","../guiaForestal","validarAltaGuiaForestalBasicaForm","GuiaForestalForm");
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

var indice = 2;										 
function agregarCuota(){
	var nom = $("#nombreProductor").val();
	
	var nombre = "";
	nombre = reemplazarCaracter(" ","%20",nom,nombre);
	
	$('#dummy').load('/SIIF/jsp/bloquePlanPagosAltaGFB.jsp?nombreProductor='+ nombre +'&indice=' + indice , 
			function(){
				//$("#prueba1").append($("#dummy").html()); NO ANDA ESTA LINEA EN IE
								
				/*document.getElementById("divPlanDePagos").innerHTML = document.getElementById("divPlanDePagos").innerHTML 
																+ document.getElementById("dummy").innerHTML*/  

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
	
	/*var i = "#idTable"+indice;	
	$(i).remove();*/

	var i = "divPlanDePagos"+indice;
	document.getElementById(i).innerHTML = "";

	if(indice == 2){
		document.getElementById("idBotonRemoverCuota").disabled="disabled";
	}																			
}

var fila0Vales ='<tr id="filaVales0">'+
				'<td width="2%" class="botoneralNegrita grisSubtitulo ind"> 1 </td>'+
				'<td width="10%" class="botoneralNegritaRight"> Desde </td>'+
				'<td width="40%" align="left"> <input name="rangos[0].desde" class="botonerab" type="text" size="25" onkeypress="javascript:esNumerico(event);"> </td>'+
				'<td width="10%" class="botoneralNegritaRight"> Hasta</td>'+
				'<td width="40%" align="left"> <input name="rangos[0].hasta" class="botonerab" type="text" size="25" onkeypress="javascript:esNumerico(event);"> </td>'+
				'</tr>';
								 
function agregarVale(){
	if($('#filaVales0').length==0){
		$("#tablaVales").append(fila0Vales);
		$("#fechaVencimientoVales").show();
		$("#idBotonRemoverVale").attr("disabled",false);
	}else{
		var j = $('#tablaVales tr:last .ind').text().trim();
		$("#tablaVales tr:last").clone().find("input").each(function() {
			$(this).attr({
				'name' : function(_, name) {
					return name.replace([j-1], [j]);
				},
				'value' : ''
			});
		}).end().appendTo("#tablaVales");
		$('#tablaVales tr:last .ind').text(1 + parseInt(j));
		var newId = $("#tablaVales tr:last").attr('id')
				.replace(j - 1, j);
		$("#tablaVales tr:last").attr('id', newId);
		$("#idBotonRemoverVale").attr("disabled",false);
	}
}

function removerVale(){
	var j = $('#tablaVales tr:last .ind').text().trim();
	
	$('#tablaVales tr:last').remove();

	if(j <= 1){
		$("#idBotonRemoverVale").attr("disabled",true);
		$("#fechaVencimientoVales").hide();
	}
}

var idRenglon;
function cambiarEstado(ind){

	var estado = $('#idEstado'+ind).val();
	var idTipoProducto = $('#selectTiposDeProductos'+ind).val();
	
	var idProdForestal = $('#idProductor').val();

	if(estado != ""){
		idRenglon = ind;
		//AforoFachada.getValor(estado,idTipoProducto,idProdForestal,actualizarImporteCallback );
		
		AforoFachada.getValor(estado,idTipoProducto,idProdForestal, 
			{callback : actualizarImporteCallback,
			 async : false}
		);
	}
}

function actualizarImporteCallback(valor){
	
	if(valor!=null){
		$('#TDValorAforo'+idRenglon).show();
		$('#errorAforo'+idRenglon).hide();
		$('#idValorAforo'+idRenglon).val(valor);
		
	}
	else{
		$('#TDValorAforo'+idRenglon).hide();
		$('#idImporte'+idRenglon).val(0.0);
		$('#idValorAforo'+idRenglon).val(0.0);
		$('#errorAforo'+idRenglon).show();
		$('#idPorcentaje').val(0);
		$('#idTotal').val(0);
	}	
	actualizarImporte(idRenglon);
}

function calcularSubImporteRenglon(ind){
	
	var cantidadMts = $('#idCantidadMts'+ind).val();
	var valorAforo = $('#idValorAforo'+ind).val();
	var result = Math.round(valorAforo*cantidadMts*100)/100;
		
	$('#idImporte'+ind).val(result);	
}

function actualizarImporte(ind){

	calcularSubImporteRenglon(ind);
	calcularTotales();	
}

function calcularTotales(){

	var tipoTerreno = $('#tipoTerrenoPMF').val();
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	var sumaImportes = 0;
	
	if(tipoTerreno == "Privado"){

		for ( var i = 0; i <= j; i++) {
			if($('#idImporte'+i) != null && $('#idImporte'+i).val() != null && $('#idImporte'+i).val() != ""){
				calcularSubImporteRenglon(i);
				sumaImportes += parseFloat($('#idImporte'+i).val());
				$('#idImporte'+i).val(0); 
			}	 
		}		
		var porcentaje = parseFloat(sumaImportes*0.2);
		$('#idPorcentaje').val(new Number(porcentaje).toFixed(2));		
		$('#idTotal').val(new Number(parseFloat($('#idPorcentaje').val())).toFixed(2));		
	}
	else{	
		for ( var i = 0; i <= j; i++) {
			if($('#idImporte'+i) != null && $('#idImporte'+i).val() != null && $('#idImporte'+i).val() != ""){
				sumaImportes += parseFloat($('#idImporte'+i).val());  
			}	 
		}
				
		if(sumaImportes == 0){
			$('#idPorcentaje').val(0);
			$('#idTotal').val(0);
		}
		else{
			var porcentaje = parseFloat(sumaImportes*0.2);
			
			$('#idPorcentaje').val(new Number(porcentaje).toFixed(2));
			$('#idTotal').val(new Number(parseFloat(sumaImportes * 1.2)).toFixed(2));
		}	
	}
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

function mostrarFiscalizacion(idFiscalizacion){

	$("#idGuia").hide();	
	$("#idDivFiscalizacion").load("../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion="+idFiscalizacion+"&strForward=exitoCargarFiscalizacionDesdeAltaGFB");
	$("#idDivFiscalizacion").show(); 
	$("#errores").hide();
}

function volverAltaGFB(){

	$("#idGuia").show();
	$("#idDivFiscalizacion").hide();
	$("#idDivFiscalizacion").empty();
	$("#errores").show();
}

//Funciones para agregar filas en el calculo de la deuda//

function agregarFila() {

	$('#idRemFila').attr('disabled',false);
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();

	if(j==3){
		$('#idAgrFila').attr('disabled',true);	
	}

	var k = parseInt(j)+1;
	
	$("#tablaImportes tr[id*=fila]:last").clone().find("input,select,td").each(function() {
		$(this).attr(
			{'name' : function(_, name){
							if(name != null)
								return name.replace([ j ], [ k ]);
					  },
			'value' : '',
			'onchange' : function(_, name){
							if(name != null)
								return name.replace([ j ], [ k ]);
						  },
			'id' : function(_, name){
							if(name != null)
								return name.replace([ j ], [ k ]);
					  	  }
			}
		);
	}).end().appendTo("#tablaImportes");
		
	$('#tablaImportes tr[id*=fila]:last input.ind').val(k);

	$("#tablaImportes tr[id*=fila]:last").attr('id', "fila"+k);
	

	$("input[name='listaSubImportes[" + k + "].especie']").val("${defaultEspecie}");
	cambiarEstado(k);
}

function removerFila() {

	var cantSubImportes = (${fn:length(subImportes)}>0)?${fn:length(subImportes)}-1:0;
	
	$('#idAgrFila').attr('disabled',false);
	$('#tablaImportes tr:last').remove();
	calcularTotales();

	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	if(j==cantSubImportes){
		$('#idRemFila').attr('disabled',true);
	}
}

function actualizarTipoTerrenoPMF(){

	idPMF = $('#idPMF').val();
	if (idPMF > 0) {
		UbicacionFachada.getTipoTerrenoPMF(idPMF,
				actualizarTipoTerrenoPMFCallback);
	}
	$('#tipoTerrenoPMF').val("");		
}

function actualizarTipoTerrenoPMFCallback(tipoTerrenoPMF) {

	$('#tipoTerrenoPMF').val(tipoTerrenoPMF);

	//Vuelvo a poner los valores en los subImportes por si cambio a un PMF con terreno Fiscal.
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	for ( var i = 0; i <= j; i++) {
		if($('#idImporte'+i) != null && $('#idImporte'+i).val() != null && $('#idImporte'+i).val() != ""){
			calcularSubImporteRenglon(i);
		}	 
	}
		
	calcularTotales();
}



//-----------------------------------------------------//
//FUNCIONES DE DECLARACION DE EXTRACCION//

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
}

function cambiarProductorCallback(productor) {
	
	dwr.util.setValue("nomProductor", productor.nombre);
	dwr.util.setValue("domProductor", productor.direccion);
	dwr.util.setValue("locProductor", productor.localidad.nombre);
	dwr.util.setValue("telProductor", productor.telefono);				
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

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<div id="idDeclaracion">
<html:form action="declaracionExtraccion" styleId="DeclaracionExtraccionForm">
	<html:hidden property="metodo" value="altaDeclaracionExtraccion" />
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
				<input name="declaracion.numero" class="botonerab" type="text" size="20" 
						onkeypress="javascript:esNumerico(event);">
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td align="left">
				<select id="idProductor" name="declaracion.productor.id" class="botonerab" onchange="cambiarProductor();">
					<option value="-1">- Seleccione un Productor -</option>
					<c:forEach items="${productores}" var="prod">						
						<option value="${prod.id}">
							<c:out value="${prod.nombre}"></c:out>
						</option>
					</c:forEach>
				</select>				
			</td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIM.label.ValidoHasta'/></td>
			<td width="30%" align="left">
				<input id="datepicker" type="text" name="declaracion.fechaVencimiento" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Periodo'/></td>
			<td align="left">
					<select name="declaracion.periodo" class="botonerab" >
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
							<bean:message key='SIIM.label.ZonaExtraccion'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<select id="idZonaExtraccion" class="botonerab" disabled="disabled">
								<option value="-1">--Seleccione una Zona--</option>
							</select>					
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

		<!-- SUBIMPORTES -->
		<tr>
			<td colspan="4" align="left">
				<div id="e1" style="DISPLAY: ">
					<label onclick="javascript:exp('1')"> 
						<img src="../../imagenes/expand.gif" border="0" /> 
						<U class="azulOpcion">
							<bean:message key='SIIM.subTitulo.SubImportes'/>						
						</U>
						<BR>
					</label>
				</div>
				<div id="c1" style="DISPLAY: none">
					<label onclick="javascript:col('1')"> 
						<img src="../../imagenes/collapse.gif" border="0" /> 
						<U class="azulOpcion">
							<bean:message key='SIIM.subTitulo.SubImportes'/>						
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
								<input class="botonerab" type="text" value="1er Trimestre" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(1);" id="id1_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
								size="15" onblur="calcularVolumenTotalTrimestre(1);" id="id1_2">
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
								size="15" onblur="calcularVolumenTotalTrimestre(1);" id="id1_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
								size="15" readonly="readonly" id="idTotal1">														
							</td>
							<td>
								<input id="datepickerFechaTrim1" type="text" readonly="readonly" class="botonerab" size="14">
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
								<input class="botonerab" type="text" value="2do Trimestre" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(2);" id="id2_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(2);" id="id2_2">
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(2);" id="id2_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" id="idTotal2" readonly="readonly">														
							</td>
							<td>
								<input id="datepickerFechaTrim2" type="text" readonly="readonly" class="botonerab" size="14">
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
								<input class="botonerab" type="text" value="3er Trimestre" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(3);" id="id3_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(3);" id="id3_2">
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(3);" id="id3_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" id="idTotal3" readonly="readonly">														
							</td>
							<td>
								<input id="datepickerFechaTrim3" type="text" readonly="readonly" class="botonerab" size="14">
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
								<input class="botonerab" type="text" value="4to Trimestre" readonly="readonly" size="15">																						
							</td>						
							<td>
								<input type="hidden" value="${productoTurba.id}">
								<input class="botonerab" type="text" value="${productoTurba.nombre}" readonly="readonly" size="17">																						
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(4);" id="id4_1">																	
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(4);" id="id4_2">
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" onblur="calcularVolumenTotalTrimestre(4);" id="id4_3">		
							</td>
							<td>
								<input class="botonerab" type="text" value="" onkeypress="javascript:esNumericoConDecimal(event);"
									size="15" id="idTotal4" readonly="readonly">														
							</td>
							<td>
								<input id="datepickerFechaTrim4" type="text" readonly="readonly" class="botonerab" size="14">
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
								<input id="idImporteTotal" readonly="readonly" class="botonerab" type="text" size="15">
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
										<input value="" class="botonerab" type="text" size="40" readonly="readonly">
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
				<select id="idLocalidad" class="botonerab" name="declaracion.localidad.id">
					<option value="">- Seleccione una Localidad -</option>
					<c:forEach items="${localidades}" var="localidad">
						<option value="${localidad.id}">
							<c:out value="${localidad.nombre}"></c:out>
						</option>
					</c:forEach>
				</select>				
				
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Fecha'/></td>
			<td align="left">		
				<input id="datepickerFecha" type="text" name="declaracion.fecha" readonly="readonly" class="botonerab">
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
				<input type="button" value="Aceptar" id="enviar" 
					class="botonerab" onclick="javascript:submitir();" > 
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volverAltaGuia();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</html:form>
</div>	 				  