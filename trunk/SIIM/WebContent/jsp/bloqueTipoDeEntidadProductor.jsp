<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

<script type="text/javascript">

var tr = null;
var clase = null;
function mostrarDatos(idTr){
	
	if(tr!=null){
		$('#tr'+tr).attr("class", clase);	
	}
	tr=idTr;
	clase = $('#tr'+tr).attr("class");
	$('#tr'+tr).attr("class", "seleccionado");	
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

function mostrarDetalle(){

	var idProductor = $('#selectProductores').val();
	var forward = $('#paramForward').val();
	$('#divCargando').show();	
	$('#divDetalle').html("");
	
	if(idProductor != "" && idProductor != "-1"){

		$('#divDetalle').load( $('#paramUrlDetalle').val() + '&idProductor='+idProductor + '&forward=' + forward);
		$('#divDetalle').hide();
		//$('#divDetalle').fadeIn(600);

		Concurrent.Thread.create(function(){
		    while ($('#divDetalle').html() == "") {}
		    $('#divCargando').hide();
		    $('#divDetalle').show(600);
		});		
		
	}else{
		$('#divDetalle').hide(600);
		$('#divDetalle').html("");
		$('#divCargando').hide();
	}	
}

function cargarProductores(){

	var idTipoDeEntidad = $('#selectTiposDeEntidad').val();
	if(idTipoDeEntidad != "-1"){
		$('#selectProductores').attr('disabled',false);
		EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresCallback );		
	}else{
		dwr.util.removeAllOptions("selectProductores");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("selectProductores", data, "id", "nombre");		
		$('#selectProductores').attr('disabled','disabled');
	}		
	$('#divDetalle').hide(600);
	$('#divDetalle').html("");
}

function actualizarProductoresCallback(productores){
	dwr.util.removeAllOptions("selectProductores");
	var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
	dwr.util.addOptions("selectProductores", data, "id", "nombre");	
	dwr.util.addOptions("selectProductores", productores,"id","nombre");	
}

function cargarProductoresVolver(){
	$('#selectTiposDeEntidad').val($('#paramIdTipoDeEntidad').val());
	var idTipoDeEntidad = $('#paramIdTipoDeEntidad').val();

	$('#selectProductores').attr('disabled',false);
	EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresVolverCallback );		

	$('#divDetalle').hide(600);
	$('#divDetalle').html("");
}

function actualizarProductoresVolverCallback(productores){
	dwr.util.removeAllOptions("selectProductores");
	var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
	dwr.util.addOptions("selectProductores", data, "id", "nombre");	
	dwr.util.addOptions("selectProductores", productores,"id","nombre");

	$('#selectProductores').val($('#paramProductor').val());
	mostrarDetalle();		
}

</script>

<input id="paramIdTipoDeEntidad" type="hidden" value="${idTipoDeEntidad}">
<input id="paramProductor" type="hidden" value="${idProductor}">
<input id="paramUrlDetalle" type="hidden" value="${urlDetalle}">
<input id="paramForward" type="hidden" value="${paramForward}">
<tr>
	<td>
		<table border="0" class="cuadrado" align="center" width="70%"
			cellpadding="2">
			<tr>
				<td height="10" colspan="3"></td>
			</tr>
			<tr>
				<td class="botoneralNegritaRight" width="30%"><bean:message key='SIIM.label.TipoDeProductor'/></td>
				<td class="botonerab">
					<select id="selectTiposDeEntidad" class="botonerab" onchange="cargarProductores()">
						<option value="-1">-Seleccione un Tipo de Entidad-</option>
						<c:forEach items="${tiposDeEntidad}" var="tipoDeEntidad" varStatus="i">
													
							<c:choose>
								<c:when test="${tipoDeEntidad.name == idLocalidad}">
									<option value="<c:out value='${tipoDeEntidad.name}'></c:out>" selected="selected">
										<c:out value="${tipoDeEntidad.descripcion}"></c:out>
									</option>
								</c:when>
								<c:otherwise>
									<option value="<c:out value='${tipoDeEntidad.name}'></c:out>">
										<c:out value="${tipoDeEntidad.descripcion}"></c:out>
									</option>
								</c:otherwise>
							</c:choose>									
							
						</c:forEach>
					</select>
				</td>
				<td width="15%"></td>					
			</tr>
			
			<tr>
				<td class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
				<td class="botonerab">
					<select id="selectProductores" class="botonerab" disabled="disabled" onchange="mostrarDetalle()">
						<option value="">-Seleccione un Productor-</option>
					</select>
				</td>
			</tr>				
			 
			<tr>
				<td height="10" colspan="3"></td>
			</tr>
		</table>		
	
	</td>
</tr>
<tr>
	<td height="20"></td>
</tr>
<tr>
	<td id="divCargando" style="display: none">
		<img src="<html:rewrite page='/imagenes/cargando.gif'/>">
	</td>	
	<td>
		<div id="divDetalle"></div>		
	</td>
</tr>
<tr>
	<td height="20"></td>
</tr>	


<script type="text/javascript">
	var idTipoDeEntidad = $('#paramIdTipoDeEntidad').val();
	if(idTipoDeEntidad != null && idTipoDeEntidad != ""){
		cargarProductoresVolver();
	}
</script>
