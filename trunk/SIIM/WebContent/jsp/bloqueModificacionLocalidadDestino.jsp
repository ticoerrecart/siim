<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript">
	function submitir(){
		validarForm("localidadDestinoFormId","../localidadDestino","validarLocalidadDestinoForm","LocalidadDestinoForm");
	}
</script>


<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>
<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<html:form action="localidadDestino" styleId="localidadDestinoFormId">

	<html:hidden property="metodo" value="modificacionLocalidadDestino" />

	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Nombre'/></td>
			<td align="left">
				<html:hidden property="localidadDestinoDTO.id" value="${localidadDTO.id}" />			
				<html:text styleClass="botonerab" property="localidadDestinoDTO.nombre" 
							value="${localidadDTO.nombre}" onkeypress="return evitarAutoSubmit(event)" />
			</td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.ProvinciaDestino'/></td>
			<td align="left">
				<html:select property="localidadDestinoDTO.provinciaDestinoDTO.id"
					styleClass="botonerab" value="${localidadDTO.provinciaDestinoDTO.id}">
					<c:forEach items="${provincias}" var="provincia">
						<html:option value="${provincia.id}">
							<c:out value="${provincia.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select>						
			</td>
		</tr>		
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2">
				<input type="button" class="botonerab" value="Aceptar" id="enviar"
						onclick="javascript:submitir();"> 
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>

</html:form>
