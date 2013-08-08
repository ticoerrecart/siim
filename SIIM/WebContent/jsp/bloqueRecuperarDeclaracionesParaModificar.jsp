<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script>
	function recuperarDeclaracion(id){
		parent.location=contextRoot() + '${fwdDetalle}' + '&id=' + id;
	}
</script>

<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2" cellspacing="0">		
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
				<td class="azulAjustado botoneralNegritaRight">
					Número
				</td>
				<td class="azulAjustado botoneralNegritaRight">
					Fecha
				</td>
				<td class="azulAjustado botoneralNegritaRight">
					Localidad
				</td>
				<td class="azulAjustado botoneralNegritaRight">
					Monto Total
				</td>
				<td class="azulAjustado botoneralNegritaRight">
					&nbsp;		
				</td>
			</tr>
			<tr>
				<td>
					${declaracion.numero}
				</td>
				<td>
					${declaracion.fecha}
				</td>
				<td>
					${declaracion.localidad.nombre}
				</td>
				<td>
					${declaracion.importeTotal}
				</td>
				<td>
					<a href="javascript:recuperarDeclaracion(${declaracion.id});">${tituloLinkDetalle}</a>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>	