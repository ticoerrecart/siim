<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script>
	function pagarBoletas(id){
		parent.location=contextRoot() + '${fwdDetalle}' + '&id=' + id;
	}
</script>

<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2" cellspacing="0">		
	<c:choose>
		<c:when test="${canonMinero==null}">
			<tr>
				<td height="15" colspan="3">
					No existen datos
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="azulAjustado botoneralNegritaRight">
					Hectáreas
				</td>
				<td class="azulAjustado botoneralNegritaRight">
					Cant Ha x Pertenencia Minera
				</td>
				<td class="azulAjustado botoneralNegritaRight">
					Canon Minero x Pertenencia
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
					${canonMinero.hectareas}
				</td>
				<td>
					${canonMinero.cantHaXPertenenciaMinera}
				</td>
			
				<td>
					${canonMinero.canonMineroXPertenencia}
				</td>
				
				<td>
					${canonMinero.montoTotal}
				</td>
				<td>
					<a href="javascript:pagarBoletas(${canonMinero.id});">${tituloLinkDetalle}</a>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>	