<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
	Integer i = Integer.parseInt(request.getParameter("indice"));	
									
	String nombre = request.getParameter("nombreProductor");
	
%>

<table id="idTable<%=i%>" border="0" class="cuadradoSinBorde" align="center" width="100%" cellpadding="2">
	<input type="hidden" name="boletasDeposito[<%=i%>].anulado" value="false"/>
	
	<tr id="idTrBoletaEspacio<%=i%>">
		<td height="5" colspan="4"></td>
	</tr>
	
	<tr onclick="$('#idTrBoleta<%=i%>').toggle();">
		<td colspan="5" class="grisSubtitulo" id="tdBoleta<%=i%>" 									
			onmouseover="javascript:pintarFilaVale('tdBoleta<%=i%>');"
			onmouseout="javascript:despintarFilaVale('tdBoleta<%=i%>');">
			Boleta de Deposito n° <%=i%>
		</td>
	</tr>
	
	<tr id="idTrBoleta<%=i%>">
		<td>
			<table id="tBoleta<%=i%>" border="0" align="center" width="100%" cellpadding="2">
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.NroBoleta'/></td>
					<td width="40%" align="left">
						<input name='<%="boletasDeposito["+(i-1)+"].numero"%>' class="botonerab" type="text" 
							size="20" onkeypress="javascript:esNumerico(event);">
						<font class="rojoAdvertencia">*</font>	
					</td>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
					<td width="40%" align="left">
						<input id="idProductor<%=i-1%>" value="<%=nombre %>" class="botonerab" type="text" size="40" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Concepto'/></td>
					<td colspan="3" align="left">
						<input name='<%="boletasDeposito["+(i-1)+"].concepto"%>' class="botonerab"
							type="text" size="90" value="Aforo">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Area'/></td>
					<td colspan="3" align="left">
						<input name='<%="boletasDeposito["+(i-1)+"].area"%>' class="botonerab"
							type="text" size="90" value="Direccion General de Bosques">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.EfectivoCheque'/></td>
					<td width="40%" align="left">
						<input name='<%="boletasDeposito["+(i-1)+"].efectivoCheque"%>' class="botonerab" 
							type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);">
					</td>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Monto$'/></td>
					<td width="40%" align="left">
						<input name='<%="boletasDeposito["+(i-1)+"].monto"%>' class="botonerab" type="text" 
							size="20" onkeypress="javascript:esNumericoConDecimal(event);">
						<font class="rojoAdvertencia">*</font>	
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaVencimiento'/></td>
					<td colspan="3" align="left">
						<input id="datepicker<%=i-1%>" type="text" readonly="readonly" class="botonerab" 
								name='<%="boletasDeposito["+(i-1)+"].fechaVencimiento"%>'>
						<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
							align="top" width='17' height='21'>															
						<font class="rojoAdvertencia">*</font>
					</td>
				</tr>
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
			</table>
		
			<input id="idBotonEliminarCuota<%=i%>" type="button"	value="-" onclick="javascript:eliminarCuota(<%=i%>);">
			<input style="display: none" id="idBotonRestituirCuota<%=i%>" type="button"	value="+" onclick="javascript:restituirCuota(<%=i%>);">
												
		</td>
	</tr>
</table>

<div id="divPlanDePagos<%=i+1%>"></div>
