<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
	
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
			
<script type="text/javascript">
	function submitir(){
		validarForm("periodoFormId","../periodo","validarPeriodoForm","PeriodoForm");
	}
</script>


<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="periodo" styleId="periodoFormId">
	<c:choose>
		<c:when test="${empty metodo}">
			<html:hidden property="metodo" value="${param.metodo}" />
		</c:when>
		<c:otherwise>
			<html:hidden property="metodo" value="${metodo}" />
		</c:otherwise>
	</c:choose>
	<html:hidden property="periodoDTO.id" value="${periodo.id}" />

	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				<c:choose>
					<c:when test="${metodo == 'altaPeriodo'}">
						<bean:message key='SIIM.titulo.AltaAnioDeclaracion'/>
					</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.AnioDeclaracion'/></td>
			<td align="left">
				<html:text styleId="periodo" styleClass="botonerab" property="periodoDTO.periodo" value="${periodo.periodo}" maxlength="4" size="4"
						onkeypress="esNumerico(event); return evitarAutoSubmit(event)"/>
			</td>
		</tr>
		
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.fechaVencPrimerTrimestre'/></td>
			<td align="left">
				<html:text styleId="datepicker0" readonly="readonly" styleClass="botonerab" property="periodoDTO.fechaVencimientoPrimerTrimestre" value="${periodo.fechaVencimientoPrimerTrimestre}" 
						onkeypress="esNumerico(event); return evitarAutoSubmit(event)"/>
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>
			</td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.fechaVencSegundoTrimestre'/></td>
			<td align="left">
				<html:text styleId="datepicker1" readonly="readonly" styleClass="botonerab" property="periodoDTO.fechaVencimientoSegundoTrimestre" value="${periodo.fechaVencimientoSegundoTrimestre}" 
						onkeypress="esNumerico(event); return evitarAutoSubmit(event)"/>
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>						
			</td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.fechaVencTercerTrimestre'/></td>
			<td align="left">
				<html:text styleId="datepicker2" readonly="readonly" styleClass="botonerab" property="periodoDTO.fechaVencimientoTercerTrimestre" value="${periodo.fechaVencimientoTercerTrimestre}" 
						onkeypress="esNumerico(event); return evitarAutoSubmit(event)"/>
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>						
			</td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.fechaVencCuartoTrimestre'/></td>
			<td align="left">
				<html:text styleId="datepicker3" readonly="readonly" styleClass="botonerab" property="periodoDTO.fechaVencimientoCuartoTrimestre" value="${periodo.fechaVencimientoCuartoTrimestre}" 
						onkeypress="esNumerico(event); return evitarAutoSubmit(event)"/>
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>
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

<script>
	for(var i=0;i<4;i++){
		$( "#datepicker"+i ).datepicker({ dateFormat: 'dd/mm/yy'});
	}

	$("#periodo").focus();
</script>
</html:form>
