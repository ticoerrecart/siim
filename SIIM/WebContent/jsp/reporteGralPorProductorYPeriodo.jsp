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
	
<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function generarReporte(){

	var idProductor = $("#selectProductores").val();
	var periodo = $("#idPeriodo").val();
	var metodo = $("#idMetodo").val();
	var action = $("#idAction").val();
	
	if(idProductor != "-1" && periodo != "-1"){
		$("#error").html("");
		var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
		if(type == "IE"){
			window.open("./"+action+".do?metodo="+metodo+"&productor="+idProductor+"&periodo="+periodo,"",especificaciones);
		}else{
			window.open("../../"+action+".do?metodo="+metodo+"&productor="+idProductor+"&periodo="+periodo,"",especificaciones);
		}
	}
	else{
		var textoError1 = (idProductor == "-1")?"* Productor es un dato obligatorio<br>":"";
		var textoError2 = (periodo == "-1")?"* Periodo es un dato obligatorio":"";
		$("#error").html(textoError1 + textoError2);		
	}
}

</script>

<div id="error" class="rojoAdvertencia"></div>
<html:hidden styleId="idMetodo" value="${metodo}" property=""/>
<html:hidden styleId="idAction" value="${action}" property=""/>

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td class="azulAjustado">
			<c:out value="${titulo}"></c:out>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>	
	<tr>
		<td>
			<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2" cellspacing="0">		
				<tr>
					<td height="15" colspan="3"></td>
				</tr>
				<tr>
					<td class="botoneralNegritaRight">Productor</td>
					<td class="botonerab" align="left">
						<select id="selectProductores" class="botonerab" onchange="">
							<option value="-1">- Seleccione un Productor -</option>
							<c:if test="${permitirTodosLosProductores != 'N'}">
								<option value="0">Todos los Productores</option>
							</c:if>								
							<c:forEach items="${productores}" var="productor" varStatus="i">
								<option value="<c:out value='${productor.id}'></c:out>">
									<c:out value="${productor.nombre}"></c:out>
								</option>							
							</c:forEach>
						</select>					
					</td>
				</tr>				
				 <tr>
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIM.label.PeríodoForestal'/></td>
					<td class="botonerab" align="left">
						<select id="idPeriodo" class="botonerab" onchange="">
							<option value="-1">- Seleccione un Periodo -</option>
							<c:if test="${permitirTodosLosPeriodos != 'N'}">
								<option value="0">Todos los Periodos</option>
							</c:if>														
							<c:forEach items="${periodos}" var="periodo" varStatus="i">
								<option value="<c:out value='${periodo.periodo}'></c:out>">
									<c:out value="${periodo.periodo}"></c:out>
								</option>
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
		<td height="10"></td>
	</tr>	
	<tr>
		<td>
			<input type="button" value="Generar Reporte" class="botonerab" onclick="generarReporte();">
		</td>
	</tr>	
	<tr>
		<td height="20"></td>
	</tr>
</table>