<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>	
	
<script type="text/javascript">
	function submitir(){
		validarForm("localizacionFormId","../localizacion","validarLocalizacionForm","LocalizacionForm");
	}
</script>


<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="localizacion" styleId="localizacionFormId">

	<c:choose>
		<c:when test="${empty metodo}">
			<html:hidden property="metodo" value="${param.metodo}" />
		</c:when>
		<c:otherwise>
			<html:hidden property="metodo" value="${metodo}" />
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty idProductor}">
			<html:hidden property="localizacionDTO.productor.id" value="${param.idProductor}" />
		</c:when>
		<c:otherwise>
			<html:hidden property="localizacionDTO.productor.id" value="${idProductor}" />
		</c:otherwise>
	</c:choose>
	<html:hidden property="localizacionDTO.id" value="${localizacion.id}" />

	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.RazonSocial'/>
			</td>
			<td align="left">
				<html:text styleClass="botonerab" property="localizacionDTO.razonSocial" value="${localizacion.razonSocial}" />
			</td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.Expediente'/>
			</td>
			<td align="left">
				<html:text styleClass="botonerab" property="localizacionDTO.expediente" value="${localizacion.expediente}" />
			</td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.Resolucion'/>
			</td>
			<td align="left">
				<html:text styleClass="botonerab" property="localizacionDTO.resolucion" value="${localizacion.resolucion}" />
			</td>
		</tr>		
		<tr>
			<td width="40%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.Superficie'/>
			</td>
			<td align="left">
				<html:text styleId="idSuperficie" styleClass="botonerab" property="localizacionDTO.superficie" value="${localizacion.superficie}" 
						onkeypress="javascript:esNumericoConDecimal(event);" onblur="roundNumber('idSuperficie');"/>
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
