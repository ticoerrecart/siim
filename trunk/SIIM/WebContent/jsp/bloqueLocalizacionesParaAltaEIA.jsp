<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>

<script>
	var tr = null;
	var clase = null;
	function mostrarDatos(idLocalizacion,idTr){
		$('#exitoGrabado').empty();
		if(tr!=null){
			$('#tr'+tr).attr("class", clase);	
		}
		tr=idTr;
		clase = $('#tr'+tr).attr("class");
		$('#tr'+tr).attr("class", "seleccionado");

		$('#divCargando2').show();	
		$('#divModificacion').html("");
		
		$('#divModificacion').load('../../localizacion.do?metodo=cargarLocalizacionParaAltaEIA&idLocalizacion='+idLocalizacion);
		$('#divModificacion').hide();

		Concurrent.Thread.create(function(){
		    while ($('#divModificacion').html() == "") {}
		    $('#divCargando2').hide();
		    $('#divModificacion').show(600);
		});		
	}
</script>

<table border="0" class="cuadrado" align="center" width="90%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado"><bean:message key='SIIM.label.RazonSocial'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.Estado'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.FechaDesde'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.FechaHasta'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.NroResolucion'/></td>
		<td class="azulAjustado"></td>
	</tr>
	<%String clase=""; %>
	<c:forEach items="${localizaciones}" var="localizacion" varStatus="i">
		<%clase=(clase.equals("")?"par":""); %>
		<tr id="tr${i.count}" class="botonerab <%=clase%>">
			<td><c:out value="${localizacion.razonSocial}" /></td>
			<td><c:out value="${localizacion.estudioVigente.estado}" /></td>
			<td><c:out value="${localizacion.estudioVigente.fechaDesde}" /></td>
			<td><c:out value="${localizacion.estudioVigente.fechaHasta}" /></td>
			<td><c:out value="${localizacion.estudioVigente.nroResolucionEIA}" /></td>
			<td>
				<a href="javascript:mostrarDatos(${localizacion.id},${i.count});">
					<bean:message key='SIIM.label.Editar'/>
				</a>
			</td>
		</tr>
	</c:forEach>
</table>
<table class="cuadradoSinBorde">
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td id="divCargando2" style="display: none" align="center">
			<center><img src="<html:rewrite page='/imagenes/cargando.gif'/>"></center>
		</td>
	</tr>	
	<tr>
		<td height="10"></td>
	</tr>	
</table>		

<div id="divModificacion"></div>




