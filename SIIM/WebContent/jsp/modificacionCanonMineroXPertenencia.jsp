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
		$("#exito").html("");
		validarForm("canonMineroFormId","../canonMinero","validarValorCanonMineroForm","CanonMineroForm");
	}
</script>


<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>
<div id="exito" class="verdeExito">${exito}</div>
<html:form action="canonMinero" styleId="canonMineroFormId">

	<html:hidden property="metodo" value="modificacionValorCanonMinero" />
	<html:hidden property="canonMinero.id" value="${canonMinero.id}" />

	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				Modificación de Canon Minero
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight">Valor Canon Minero Por Pertenencia</td>
			<td align="left">
				<html:text styleClass="botonerab" property="canonMinero.canonXPertenencia" 
						value="${canonMinero.canonXPertenencia}"
						onkeypress="esNumerico(event); return evitarAutoSubmit(event)"/>
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
