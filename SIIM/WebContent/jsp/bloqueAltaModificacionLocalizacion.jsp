<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>	
	
<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		
	
<script type="text/javascript">
	function submitir(){
		validarForm("localizacionFormId","../localizacion","validarLocalizacionForm","LocalizacionForm");
	}

	function cambiarEstadoEIA(){

		var estado = $("#idEstadoEIA").val();
		if(estado == "Aprobado"){
			$("#idTituloObs").hide();
			$("#idObs").hide();
			$("#idObservaciones").val("");
			
			$("#idResolucion").show();
			$("#idFechaDesde").show();
			$("#idFechaHasta").show();						
			
		}else{
			$("#idTituloObs").show();
			$("#idObs").show();

			$("#idResolucion").hide();
			$("#idFechaDesde").hide();
			$("#idFechaHasta").hide();

			$("#datepickerFechaDesde").val("");
			$("#datepickerFechaHasta").val("");
			$("#idNroResolucion").val("");
			
		}
	}

	$(function() {

		$( "#datepickerFechaDesde" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFechaHasta" ).datepicker({ dateFormat: 'dd/mm/yy'});		
	});	
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
	
	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2" cellspacing="0">
		<tr>
			<td colspan="4" class="grisSubtitulo"><bean:message key='SIIM.subTitulo.DatosZonaExtraccion'/></td>
		</tr>
		<tr>
			<td colspan="4" height="20"></td>
		</tr>	
		<tr>
			<td width="22%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.RazonSocial'/>
			</td>
			<td width="25%" align="left">
				<html:text styleClass="botonerab" property="localizacionDTO.razonSocial" value="${localizacion.razonSocial}" />
			</td>
			<td width="20%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.Expediente'/>
			</td>
			<td width="33%"  align="left">
				<html:text styleClass="botonerab" property="localizacionDTO.expediente" value="${localizacion.expediente}" />
			</td>
		</tr>
		<tr>
			<td width="22%"  class="botoneralNegritaRight">
				<bean:message key='SIIM.label.Resolucion'/>
			</td>
			<td width="25%"  align="left">
				<html:text styleClass="botonerab" property="localizacionDTO.resolucion" value="${localizacion.resolucion}" />
			</td>
			<td width="20%"  class="botoneralNegritaRight">
				<bean:message key='SIIM.label.Domicilio'/>
			</td>
			<td width="33%"  align="left">
				<html:text styleClass="botonerab" property="localizacionDTO.domicilio" value="${localizacion.domicilio}" />
			</td>
		</tr>				
		<tr>
			<td width="22%"  class="botoneralNegritaRight">
				<bean:message key='SIIM.label.Superficie'/>
			</td>
			<td colspan="3" align="left">
				<html:text styleId="idSuperficie" styleClass="botonerab" property="localizacionDTO.superficie" value="${localizacion.superficie}" 
						onkeypress="javascript:esNumericoConDecimal(event);" onblur="roundNumber('idSuperficie');"/>
			</td>
		</tr>				
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
	</table>
	<br>
	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2" cellspacing="0">
		<tr>
			<td colspan="2" class="grisSubtitulo"><bean:message key='SIIM.subTitulo.EstudioImpactoAmbiental'/></td>
		</tr>
		<tr>
			<td colspan="2" height="20"></td>
		</tr>			
		<tr>
			<td width="40%" class="botoneralNegritaRight">Estado</td>
			<td width="60%" align="left">
				<input type="hidden" value="${localizacion.estudioVigente.id}" name="estudioVigente.id">
				<input type="hidden" value="${localizacion.estudioVigente.vigente}" name="estudioVigente.vigente">
				<html:select styleId="idEstadoEIA" disabled="disabled"
					property="estudioVigente.estado" styleClass="botonerab disabled"
					value="${localizacion.estudioVigente.estado}"
					onchange="cambiarEstadoEIA();">
					<c:forEach items="${estadoEIA}" var="estado">
						<html:option value="${estado.name}">
							<c:out value="${estado.descripcion}"></c:out>
						</html:option>
					</c:forEach>
				</html:select>			
				
			</td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>		
		<tr id="idFechaDesde">
			<td width="40%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.FechaDesde'/>
			</td>
			<td width="60%" align="left">				
				<input id="datepickerFechaDesde" type="text" name="estudioVigente.fechaDesde" readonly="readonly" class="botonerab" value="${localizacion.estudioVigente.fechaDesde}">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				
			</td>
		</tr>	
		<tr id="idFechaHasta">	
			<td width="40%" class="botoneralNegritaRight">
				<bean:message key='SIIM.label.FechaHasta'/>
			</td>
			<td width="60%" align="left">				
				<input id="datepickerFechaHasta" type="text" name="estudioVigente.fechaHasta" readonly="readonly" class="botonerab" value="${localizacion.estudioVigente.fechaHasta}">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				
			</td>			
		</tr>
		<tr id="idResolucion">
			<td width="40%" class="botoneralNegritaRight">
				Nro de Resolución
			</td>
			<td width="60%" align="left">				
				<html:text styleId="idNroResolucion" styleClass="botonerab" property="estudioVigente.nroResolucionEIA" value="${localizacion.estudioVigente.nroResolucionEIA}" />				
			</td>				
		</tr>
		<tr style="display: none;" id="idTituloObs">
			<td colspan="2" class="botoneralNegrita">				
				Observaciones				
			</td>
		</tr>		
		<tr style="display: none;" id="idObs">					
			<td colspan="2">				
				<textarea id="idObservaciones" name="estudioVigente.observaciones" class="botonerab" cols="75" rows="3">
					<c:out value="${localizacion.estudioVigente.observaciones}"></c:out>
				</textarea>				
			</td>				
		</tr>		
		<tr>
			<td colspan="2" height="20"></td>
		</tr>					
	</table>		
	<br>
	<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2" cellspacing="0">			
		<tr>
			<td height="20">
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
			<td height="10" colspan="4"></td>
		</tr>
	</table>

</html:form>

<script type="text/javascript">
cambiarEstadoEIA();
</script>
