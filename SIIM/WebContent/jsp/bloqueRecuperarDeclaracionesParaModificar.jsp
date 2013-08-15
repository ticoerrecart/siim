<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script>
	function recuperarDeclaracion(id){
		parent.location=contextRoot() + '${fwdDetalle}' + '&id=' + id;
	}
</script>

<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2" cellspacing="1">		
	<c:choose>
		<c:when test="${declaracion == null}">
			<tr>
				<td height="15" colspan="3">
					No existen datos
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="azulAjustado botoneralNegrita">
					Número
				</td>
				<td class="azulAjustado botoneralNegrita">
					Fecha
				</td>
				<td class="azulAjustado botoneralNegrita">
					Localidad
				</td>
				<td class="azulAjustado botoneralNegrita">
					Monto Total
				</td>
				<td class="azulAjustado botoneralNegrita">
					&nbsp;		
				</td>
			</tr>
			<tr class="par">
				<td class="botonerab">
					${declaracion.numero}
				</td>
				<td class="botonerab">
					${declaracion.fecha}
				</td>
				<td class="botonerab">
					 ${declaracion.localidad.nombre}
				</td>
				<td class="botonerab">
					${declaracion.importeTotal}
				</td>
				<td class="botonerab">
					<a href="javascript:recuperarDeclaracion(${declaracion.id});">${tituloLinkDetalle}</a>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>	