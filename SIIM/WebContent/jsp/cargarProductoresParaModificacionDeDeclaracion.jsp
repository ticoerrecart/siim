<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<br>
<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>

		<c:if test="${consulta == null}">
			<td class="azulAjustado">
				<c:choose>
					<c:when test="${titulo != null}">
						<c:out value="${titulo}"></c:out>
					</c:when>
					<c:otherwise>
						<bean:message key='SIIM.label.ModificacionDeDeclaracion'/>
					</c:otherwise>
				</c:choose>			
			</td>
		</c:if>
		<c:if test="${consulta != null}">
			<td class="azulAjustado"><bean:message key='SIIM.titulo.ConsultaDeclaracionExtraccion'/></td>
		</c:if>
		
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	
	<%@include file="bloqueSeleccionProductorLocalizacionPeriodo.jsp" %>	
</table>