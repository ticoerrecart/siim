<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript">
	function submitir(){
		validarForm("localidadFormId","../localidad","validarLocalidadForm","LocalidadForm");
	}
</script>


<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="localidad" styleId="localidadFormId">
	<c:choose>
		<c:when test="${empty metodo}">
			<html:hidden property="metodo" value="${param.metodo}" />
		</c:when>
		<c:otherwise>
			<html:hidden property="metodo" value="${metodo}" />
		</c:otherwise>
	</c:choose>
	<html:hidden property="localidadDTO.id" value="${localidad.id}" />

	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				<c:choose>
					<c:when test="${metodo == 'altaLocalidad'}">
						<bean:message key='SIIM.titulo.AltaLocalidad'/>
					</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Nombre'/></td>
			<td align="left">
				<html:text styleClass="botonerab" property="localidadDTO.nombre" value="${localidad.nombre}" />
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2">
				<input type="button" class="botonerab" value="Aceptar" id="enviar"
						onclick="javascript:submitir();"> 
				<c:choose>
					<c:when test="${empty metodo}">
						<input type="button" class="botonerab" value="Cancelar"
							onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
					</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>

</html:form>
