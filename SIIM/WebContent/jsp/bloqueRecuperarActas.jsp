<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script>
	function recuperarActa(id){
		parent.location=contextRoot() + '${fwdDetalle}' + '&id=' + id;
	}

	var clase;
	function pintarFila(idTr){
		
		clase = $('#'+idTr).attr("class");
		$('#'+idTr).removeClass(clase);
		$('#'+idTr).addClass("verdeSeleccionFila");		
	}

	function despintarFila(idTr){
		
		$('#'+idTr).addClass(clase);
		$('#'+idTr).removeClass("verdeSeleccionFila");
	}
	
</script>

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="1">		
	<c:choose>
		<c:when test="${empty actas}">
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
						Productor
					</td>
					<td class="azulAjustado botoneralNegrita">
						Fecha de Verificación
					</td>
					<td class="azulAjustado botoneralNegrita">
						Remito
					</td>
					<td class="azulAjustado botoneralNegrita">
						Localidad Destino		
					</td>
					<td class="azulAjustado botoneralNegrita">
						Volumen Total		
					</td>
					<td class="azulAjustado botoneralNegrita">
								
					</td>
				</tr>
			<%String clase=""; %>	
			<c:forEach items="${actas}" var="acta" varStatus="index">
				<%clase=(clase.equals("")?"par":""); %>
				<tr class="<%=clase%>" onmouseover="javascript:pintarFila('idTr<c:out value='${index.index}'></c:out>');"
					onmouseout="javascript:despintarFila('idTr<c:out value='${index.index}'></c:out>');"
					id="idTr<c:out value='${index.index}'></c:out>">
					<td>
						${acta.numero}
					</td>
					<td>
						${acta.productor.nombre}
					</td>
					<td>
						<fmt:formatDate value="${acta.fechaVerificacion}" pattern="dd/MM/yyyy" />
					</td>
					<td>
						${acta.numeroDeRemito}
					</td>
					<td>
						${acta.destino.nombre}
					</td>
					<td>
						${acta.volumenTotal}
					</td>
					<td>
						<a href="javascript:recuperarActa(${acta.id});">${tituloLinkDetalle}</a>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>	