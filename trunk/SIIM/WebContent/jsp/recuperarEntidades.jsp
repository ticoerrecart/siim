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
	function mostrarDatos(idEntidad,idTr){
		$('#exitoGrabado').empty();
		if(tr!=null){
			$('#tr'+tr).attr("class", clase);	
		}
		tr=idTr;
		clase = $('#tr'+tr).attr("class");
		$('#tr'+tr).attr("class", "seleccionado");

		$('#divCargando').show();	
		$('#divModificacion').html("");
		
		$('#divModificacion').load('../../entidad.do?metodo=cargarEntidadAModificar&id=' + idEntidad);
		$('#divModificacion').hide();
		$('#divModificacion').fadeIn(600);

		Concurrent.Thread.create(function(){
		    while ($('#divModificacion').html() == "") {}
		    $('#divCargando').hide();
		});		
	}
</script>


<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<table border="0" class="cuadrado" align="center" width="80%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado">
			<bean:message key='SIIM.titulo.ModificacionEntidad'/>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
		<table border="0" class="cuadrado" align="center" width="90%"
			cellpadding="2">
			<tr>
				<td class="azulAjustado"><bean:message key='SIIM.label.Nombre'/></td>
				<td class="azulAjustado"><bean:message key='SIIM.label.Direccion'/></td>
				<td class="azulAjustado"><bean:message key='SIIM.label.Localidad'/></td>
				<td class="azulAjustado"><bean:message key='SIIM.label.Telefono'/></td>
				<td class="azulAjustado"><bean:message key='SIIM.label.EMail'/></td>
				<td class="azulAjustado"><bean:message key='SIIM.label.TipoDeProductor'/></td>
				<td class="azulAjustado"></td>
			</tr>
			<%String clase=""; %>
			<c:forEach items="${entidades}" var="entidad" varStatus="i">
				<%clase=(clase.equals("")?"par":""); %>
				<tr id="tr${i.count}" class="botonerab <%=clase%>">
					<td><c:out value="${entidad.nombre}" /></td>
					<td><c:out value="${entidad.direccion}" /></td>
					<td><c:out value="${entidad.localidad.nombre}" /></td>
					<td><c:out value="${entidad.telefono}" /></td>
					<td><c:out value="${entidad.email}" /></td>
					<td><c:out value="${entidad.tipoEntidadDesc}" /></td>
					<td>
						<a href="javascript:mostrarDatos(${entidad.id},${i.count});">
							<bean:message key='SIIM.label.Editar'/>
						</a>
					</td>
				</tr>
			</c:forEach>
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
	</tr>	
	<tr>
		<td>
			<div id="divModificacion"></div>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>	
</table>

