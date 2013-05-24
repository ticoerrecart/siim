<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.3.2.min.js'/>"></script>
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

		$('#divCargando').show();	
		$('#divModificacion').html("");
		
		$('#divModificacion').load('../../localizacion.do?metodo=cargarLocalizacionParaModificar&idLocalizacion='+idLocalizacion);
		$('#divModificacion').hide();
		//$('#divModificacion').fadeIn(600);

		Concurrent.Thread.create(function(){
		    while ($('#divModificacion').html() == "") {}
		    $('#divCargando').hide();
		    $('#divModificacion').show(600);
		});		
	}
</script>

<table border="0" class="cuadrado" align="center" width="90%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado"><bean:message key='SIIM.label.RazonSocial'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.Expediente'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.Resolucion'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.Domicilio'/></td>
		<td class="azulAjustado"><bean:message key='SIIM.label.Superficie'/></td>
		<td class="azulAjustado"></td>
	</tr>
	<%String clase=""; %>
	<c:forEach items="${localizaciones}" var="localizacion" varStatus="i">
		<%clase=(clase.equals("")?"par":""); %>
		<tr id="tr${i.count}" class="botonerab <%=clase%>">
			<td><c:out value="${localizacion.razonSocial}" /></td>
			<td><c:out value="${localizacion.expediente}" /></td>
			<td><c:out value="${localizacion.resolucion}" /></td>
			<td><c:out value="${localizacion.domicilio}" /></td>
			<td><c:out value="${localizacion.superficie}" /></td>
			<td>
				<a href="javascript:mostrarDatos(${localizacion.id},${i.count});">
					<bean:message key='SIIM.label.Editar'/>
				</a>
			</td>
		</tr>
	</c:forEach>
</table>
<table>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td id="divCargando" style="display: none">
			<img src="<html:rewrite page='/imagenes/cargando.gif'/>">
		</td>
	</tr>	
	<tr>
		<td height="10"></td>
	</tr>	
</table>		

<div id="divModificacion"></div>




