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
		validarForm("entidadFormId","../entidad","validarEntidadForm","EntidadForm");
	}

	function cambioCuit(){
		$("#cuit").val($("#prefijoCuit").val() + $("#nroCuit").val() + $("#sufijoCuit").val());
	}
	
	function cambioTipoEntidad(){
		if($("#selectTiposDeEntidad").val() == "RN"){
			$(".matriculaCuitCP").hide();
			$(".matriculaCuitCP input").each(
					function(i){
						$(this).val("");
					}
			);
		}else{
			$(".matriculaCuitCP").show();
		}

	}
</script>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>
<html:form action="entidad" styleId="entidadFormId">
	<html:hidden property="metodo" value="${metodo}" />
	<html:hidden property="entidadDTO.id" value="${entidad.id}" />

	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">${titulo}</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="45%" class="botoneralNegritaRight"><bean:message key='SIIM.label.TipoDeProductor'/></td>
			<td align="left"><c:choose>
				<c:when test="${metodo=='altaEntidad'}">
					<select id="selectTiposDeEntidad" class="botonerab" name="entidadDTO.tipoEntidad" onchange="cambioTipoEntidad();">
						<option value="-1">-Seleccione un Tipo de Entidad-</option>
						<c:forEach items="${tiposDeEntidad}" var="tipoDeEntidad" varStatus="i">
							<option value="<c:out value='${tipoDeEntidad.name}'></c:out>">
								<c:out value="${tipoDeEntidad.descripcion}"></c:out>
							</option>							
						</c:forEach>
					</select>
				</c:when>
				<c:otherwise>
					<!-- AL MODIFICAR NO PUEDO CAMBIAR EL TIPO DE ENTIDAD -->
					<input type="text" readonly="readonly" class="botonerab" name="entidadDTO.tipoEntidadDesc" 
							value="${entidad.tipoEntidadDesc}" size="30">
					<input type="hidden" id="selectTiposDeEntidad" name="entidadDTO.tipoEntidad" value="${entidad.tipoEntidad}">
					<script>
						cambioTipoEntidad();
					</script>
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.Nombre'/></td>
			<td align="left"><html:text property="entidadDTO.nombre" size="30"
				styleClass="botonerab" value="${entidad.nombre}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.Localidad'/></td>
			<td align="left">
				<html:select styleClass="botonerab" property="entidadDTO.idLocalidad" value="${entidad.localidad.id}">
					<c:forEach items="${localidades}" var="localidad">
						<html:option value="${localidad.id}">
							<c:out value="${localidad.nombre}" />
						</html:option>
					</c:forEach>

				</html:select>
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.Direccion'/></td>
			<td align="left"><html:text property="entidadDTO.direccion" size="30"
				styleClass="botonerab" value="${entidad.direccion}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.Telefono'/></td>
			<td align="left"><html:text property="entidadDTO.telefono" size="30"
				styleClass="botonerab" value="${entidad.telefono}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.EMail'/></td>
			<td align="left"><html:text property="entidadDTO.email" size="30"
				styleClass="botonerab" value="${entidad.email}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.ConfirmacionEMail'/></td>
			<td align="left"><html:text property="confirmacionEmail" size="30"
				styleClass="botonerab" value="${entidad.email}" /></td>
		</tr>
		
		<tr class="matriculaCuitCP">
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.NroMatricula'/></td>
			<td align="left"><html:text property="entidadDTO.nroMatricula" size="30"
				styleClass="botonerab" value="${entidad.nroMatricula}" onkeypress="javascript:esNumerico(event);"/></td>
		</tr>
		<tr class="matriculaCuitCP">
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.Cuit'/></td>
			<td align="left">
				<html:hidden property="entidadDTO.cuit" value="${entidad.cuit}" styleId="cuit"/>
				<input type="text" class="botonerab" size="2" id="prefijoCuit" value="${prefijoCuit}" onkeypress="javascript:esNumerico(event);" onblur="cambioCuit();">
				<input type="text" class="botonerab" size="9" maxlength="8" id="nroCuit" value="${nroCuit}" onkeypress="javascript:esNumerico(event);" onblur="cambioCuit();">
				<input type="text" class="botonerab" size="2" id="sufijoCuit" value="${sufijoCuit}" onkeypress="javascript:esNumerico(event);" onblur="cambioCuit();">
			</td>
		</tr>
		<tr class="matriculaCuitCP">
			<td class="botoneralNegritaRight"><bean:message key='SIIM.label.CodigoPostal'/></td>
			<td align="left"><html:text property="entidadDTO.codigoPostal" size="5"
				styleClass="botonerab" value="${entidad.codigoPostal}" onkeypress="javascript:esNumerico(event);"/></td>
		</tr>
		
		
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2"><c:choose>
				<c:when test="${metodo=='altaEntidad'}">
					<input type="button" class="botonerab" value="Aceptar" id="enviar"
						onclick="javascript:submitir();">
					<input type="button" class="botonerab" value="Cancelar"
						onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
				</c:when>
				<c:otherwise>
					<input type="button" class="botonerab" value="Aceptar" id="enviar"
						onclick="javascript:submitir();">
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>

</html:form>
