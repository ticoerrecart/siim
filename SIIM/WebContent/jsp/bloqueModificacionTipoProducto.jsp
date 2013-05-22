<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

<script type="text/javascript">

</script>

<html:form action="tipoProducto" styleId="tipoProductoForm">
	<html:hidden property="metodo" value="modificacionTipoProducto" />
	<html:hidden property="tipoProducto.id" value="${tipoProducto.id}" />
	<table border="0" class="cuadrado" align="center" width="70%"
		cellpadding="2">
		<tr>
			<td colspan="3" height="15"></td>
		</tr>
		<tr>
			<td width="35%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.TipoDeProducto'/>
			</td>
			<td align="left">
				<input name="tipoProducto.nombre" class="botonerab" type="text" size="30"
						value="<c:out value='${tipoProducto.nombre}'></c:out>" 
						onkeypress="return evitarAutoSubmit(event)" readonly="readonly">
			</td>
			<td width="10%"></td>
		</tr>
		<tr>
			<td width="35%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.RegaliaMinera'/>
			</td>
			<td align="left">
				<input name="tipoProducto.regaliaMinera" class="botonerab" type="text" size="30"
						value="<c:out value='${tipoProducto.regaliaMinera}'></c:out>" 
						onkeypress="esNumerico(event);return evitarAutoSubmit(event)">
			</td>
			<td width="10%"></td>
		</tr>	
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="button" class="botonerab" value="Modificar" id="enviar" 
						onclick="javascript:submitir();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
	</table>
</html:form>
