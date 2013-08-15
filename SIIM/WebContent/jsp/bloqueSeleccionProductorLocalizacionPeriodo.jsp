<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/LocalizacionFachada.js'/>"></script>	

<script type="text/javascript">
	
	//ok
	function mostrarDetalle(){
	
		var idLocalizacion = $('#selectLocalizaciones').val();
		var idPeriodo = $('#selectPeriodo').val();
		var idEntidad = $('#selectProductores').val();
		var consulta = $('#consulta').val();
		var forward = $('#paramForward').val();
		$('#divCargando').show();	
		$('#divDetalle').html("");
		$('#errores').html("");
		
		if(idLocalizacion != "" && idLocalizacion != "-1"){
			$('#divDetalle').load( $('#paramUrlDetalle').val() + '&idLocalizacion='+idLocalizacion + '&forward=' + forward + '&idPeriodo=' + idPeriodo + '&idEntidad=' + idEntidad + '&consulta=' + consulta, mostrarDetalleCbk);
			$('#divDetalle').hide();
			$('#divDetalle').fadeIn(600);
				
		}else{
			$('#divDetalle').hide(600);
			$('#divDetalle').html("");
			$('#divCargando').hide();
		}	
	}

	//ok
	function mostrarDetalleCbk(){
	    $('#divCargando').hide();
	}

	
	//ok
	function cargarYacimientosVolver(){
		var idProductor= $("#selectProductores").val();
		if(idProductor!=-1){
			LocalizacionFachada.getLocalizacionesPorProductorDTO(idProductor,cargarYacimientosVolverCbk);
		}
	}

	//ok
	function cargarYacimientosVolverCbk(localizaciones){
		dwr.util.removeAllOptions("selectLocalizaciones");
		var data = [ { razonSocial:"-Seleccione una Localizacion-", id:-1 }];
		dwr.util.addOptions("selectLocalizaciones", data, "id", "razonSocial");
		dwr.util.addOptions("selectLocalizaciones", localizaciones, "id", "razonSocial");
		$("#selectLocalizaciones").val("${idLocalizacion}");
		mostrarDetalle();
	}

	//ok
	function cargarYacimientos(){
		var idProductor= $("#selectProductores").val();
		if(idProductor!=-1){
			LocalizacionFachada.getLocalizacionesPorProductorDTO(idProductor,cargarYacimientosCbk);
		}
	}

	//ok
	function cargarYacimientosCbk(localizaciones){
		dwr.util.removeAllOptions("selectLocalizaciones");
		var data = [ { razonSocial:"-Seleccione una Localizacion-", id:-1 }];
		dwr.util.addOptions("selectLocalizaciones", data, "id", "razonSocial");
		dwr.util.addOptions("selectLocalizaciones", localizaciones, "id", "razonSocial");
	}

</script>

<input id="paramProductor" type="hidden" value="${idProductor}">
<input id="paramUrlDetalle" type="hidden" value="${urlDetalle}">
<input id="paramForward" type="hidden" value="${paramForward}">
<input id="consulta" type="hidden" value="${consulta}">
<tr>
	<td>
		<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2" cellspacing="0">		
			<tr>
				<td height="15" colspan="3"></td>
			</tr>

			<tr>
				<td class="botoneralNegritaRight">Productor</td>
				<td class="botonerab" align="left">
					<select id="selectProductores" class="botonerab" onchange="cargarYacimientos()">
						<option value="-1">- Seleccione un Productor -</option>
						<c:forEach items="${productores}" var="productor" varStatus="i">
							<c:choose>
								<c:when test="${productor.id == idProductor}">
									<option value="<c:out value='${productor.id}'></c:out>" selected="selected">
										<c:out value="${productor.nombre}"></c:out>
									</option>																
								</c:when>
								<c:otherwise>
									<option value="<c:out value='${productor.id}'></c:out>">
										<c:out value="${productor.nombre}"></c:out>
									</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>					
					
				</td>
			</tr>				
			 
			<tr>
				<td class="botoneralNegritaRight"><bean:message key='SIIM.subTitulo.Localizacion'/></td>
				<td class="botonerab" align="left">
					<select id="selectLocalizaciones" class="botonerab" onchange="mostrarDetalle()">
						<option value="-1">- Seleccione una Localización -</option>
						<c:forEach items="${localizaciones}" var="localizacion" varStatus="i">
							<c:choose>
								<c:when test="${localizacion.id == idLocalizacion}">
									<option value="<c:out value='${localizacion.id}'></c:out>" selected="selected">
										<c:out value="${localizacion.nombre}"></c:out>
									</option>																
								</c:when>
								<c:otherwise>
									<option value="<c:out value='${localizacion.id}'></c:out>">
										<c:out value="${localizacion.nombre}"></c:out>
									</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>					
					
				</td>
			</tr>


			 <tr>
				<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.PeríodoForestal'/></td>
				<td class="botonerab" align="left">
					<select id="selectPeriodo" class="botonerab" onchange="mostrarDetalle()">
						<c:forEach items="${periodos}" var="periodo" varStatus="i">
							<c:choose>
								<c:when test="${periodo.periodo == idPeriodo}">
									<option value="<c:out value='${periodo.periodo}'></c:out>" selected="selected">
										<c:out value="${periodo.periodo}"></c:out>
									</option>																
								</c:when>
								<c:otherwise>
									<option value="<c:out value='${periodo.periodo}'></c:out>">
										<c:out value="${periodo.periodo}"></c:out>
									</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td height="15" colspan="3"></td>
			</tr>
		</table>		
	</td>
</tr>



		
<tr>
	<td height="20"></td>
</tr>
<tr>
	<td id="divCargando" style="display: none">
		<img src="<html:rewrite page='/imagenes/cargando.gif'/>">
	</td>	
	<td>
		<div id="divDetalle"></div>		
	</td>
</tr>
<tr>
	<td height="20"></td>
</tr>	


<script type="text/javascript">
	if($("#paramProductor").val()!=""){
		cargarYacimientosVolver();
	}
</script>
