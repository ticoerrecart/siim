<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript">
	function submitir(){
		$("#exitoGrabado").html("");
		validarForm("localidadDestinoFormId","../localidadDestino","validarLocalidadDestinoForm","LocalidadDestinoForm");
	}
</script>


<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>
<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<html:form action="localidadDestino" styleId="localidadDestinoFormId">

	<html:hidden property="metodo" value="altaLocalidadDestino" />

	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				<bean:message key='SIIM.titulo.AltaLocalidad'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Nombre'/></td>
			<td align="left">
				<html:text styleClass="botonerab" property="localidadDestinoDTO.nombre"
							onkeypress="return evitarAutoSubmit(event)"/>
			</td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.ProvinciaDestino'/></td>
			<td align="left">
				<select class="botonerab" name="localidadDestinoDTO.provinciaDestinoDTO.id">
					<c:forEach items="${provincias}" var="provincia" varStatus="i">
						<option value="<c:out value='${provincia.id}'></c:out>">
							<c:out value="${provincia.nombre}"></c:out>
						</option>							
					</c:forEach>
				</select>				
			</td>
		</tr>		
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2">
				<input type="button" class="botonerab" value="Aceptar" id="enviar"
						onclick="javascript:submitir();"> 
				<input type="button" class="botonerab" value="Cancelar"
						onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">

			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>

</html:form>
