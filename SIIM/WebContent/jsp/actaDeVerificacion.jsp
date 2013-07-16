<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/LocalizacionFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/fiscalizacion.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/LocalidadFachada.js'/>"></script>	

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	

<script>

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";


function calcularVolBolsa(){
	var cant = $('input[name="acta.bolsaCantidad"]').val();
	var vol = $('input[name="acta.bolsaVolumenD3"]').val();
	if (cant!=null && vol!=null && cant >= 0 && vol >= 0 ){
		$('#bolsaVolumenTotal').val(cant*vol/1000);	
	} else {
		$('#bolsaVolumenTotal').val(0);
	}
	calcularVolTotal();
}

function volverAltaActa(){
		periodo = <fmt:formatDate value="${acta.fechaVerificacion}" pattern="yyyy" />;
		parent.location= contextRoot() + '${volver}'+'&idProductor=${acta.productor.id}&idLocalizacion=${acta.yacimiento.id}&idPeriodo='+periodo;		
}

function calcularVolTotal(){
	n = $('#granelVolumenTotal').val();
	n2 = $('#bolsaVolumenTotal').val();
    n = isNaN(parseInt(n)) ? 0 : parseInt(n);
	n2 = isNaN(parseInt(n2)) ? 0 : parseInt(n2);
	$('#totalVol').text(n + n2);
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<div id="idActa">
	
	<table border="0" class="cuadrado" align="center" width="90%" 	cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIM.titulo.ConsultaActa'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Numero'/></td>
			<td width="35%" align="left">
				<input name="acta.numero" class="botonerab" type="text" size="20" readonly="readonly" value="${acta.numero}">
			</td>
			<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td width="35%" align="left">
				<input name="acta.productor" class="botonerab" type="text" size="20" readonly="readonly" value="${acta.productor.nombre}">
			</td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaVerificacion'/></td>
			<td width="35%" align="left">
				<input type="text" name="acta.fechaVerificacion" readonly="readonly" class="botonerab" value="<fmt:formatDate value="${acta.fechaVerificacion}" pattern="dd/MM/yyyy" />">
				
			</td>

			<td width="15%" class="botoneralNegritaRight">Area De Verificación</td>
			<td width="35%" align="left">
				<input name="acta.areaDeVerificacion" class="botonerab" type="text" size="30" readonly="readonly" value="${acta.areaDeVerificacion}">
			</td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight">Agente Verificación</td>
			<td width="35%" align="left">
				<input type="text" name="acta.fechaVerificacion" readonly="readonly" class="botonerab" value="${acta.agenteVerificacion}">
			</td>

			<td width="15%" class="botoneralNegritaRight">Area Fiscalizadora</td>
			<td width="35%" align="left">
				<input type="text" name="acta.areaFiscalizadora" class="botonerab" size="30" readonly="readonly" value="${acta.areaFiscalizadora}" >
			</td>
		</tr>
		
		<tr>
			<td width="15%" class="botoneralNegritaRight">Funcionario Acturante</td>
			<td width="35%" align="left">
				<input type="text" name="acta.funcionarioActuante" class="botonerab" size="30" readonly="readonly" value="${acta.funcionarioActuante}">
			</td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight">Observaciones</td>
			<td width="85%" align="left" colspan="3">
				<textarea rows="4" cols="120" name="acta.observaciones" class="botonerab" readonly="readonly">${acta.observaciones}</textarea>
			</td>
		</tr>
		
	<!-- DATOS DEL PRODUCTOR -->
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td colspan="4">		
				<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="4" class="grisSubtitulo"><bean:message key='SIIM.subTitulo.DatosProductor'/></td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>			
					<tr>
						<td width="20%" class="botoneralNegritaRight">
							Nombre y Apellido
						</td>
						<td width="30%" align="left">
							<input id="nomProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${acta.productor.nombre}">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Domicilio
						</td>
						<td align="left">
							<input id="domProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${acta.productor.direccion}">
						</td>
					</tr>
					<tr>
						<td width="20%" class="botoneralNegritaRight">
							Localidad
						</td>
						<td width="30%" align="left">
							<input id="locProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${acta.productor.localidad.nombre}">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Telefono
						</td>
						<td align="left">
							<input id="telProductor" class="botonerab" type="text" size="30" readonly="readonly" value="${acta.productor.telefono}">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>				
				</table>		
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>


	<!-- LOCALIZACION -->
	<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIM.subTitulo.Localizacion'/></td>
					</tr>
					<tr>
						<td colspan="3" height="10"></td>
					</tr>			
					
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.RazonSocial'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="idZonaExtraccion" class="botonerab" type="text" size="25" readonly="readonly" value="${acta.yacimiento.razonSocial}" />
						</td>						
					</tr>	
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Domicilio'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="domZona" class="botonerab" type="text" size="25" readonly="readonly" value="${acta.yacimiento.domicilio}">
						</td>						
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Superficie'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly" value="${acta.yacimiento.superficie}">
						</td>						
					</tr>																					
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>				
	</table>									

	
	<!-- TRANSPORTE -->
	<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="8" class="grisSubtitulo"><bean:message key='SIIM.subTitulo.Transporte'/></td>
					</tr>
					<tr>
						<td colspan="8" height="10"></td>
					</tr>			
					
					<tr>
						<td width="12%" class="botoneralNegritaRight">
							Marca
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.marca" readonly="readonly"  value="${acta.transporte.marca}"/>
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Dominio
						</td>
						<td width="12%"  align="left">
							<input id="dominio" class="botonerab" type="text" size="15" name="acta.transporte.dominio" readonly="readonly" value="${acta.transporte.dominio}"/>
						</td>
						
						<td width="12%" class="botoneralNegritaRight">
							Marca Semirremolque
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.semirremolqueMarca" readonly="readonly" value="${acta.transporte.semirremolqueMarca}"/>
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Dominio Semirremolque
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.semirremolqueDominio" readonly="readonly" value="${acta.transporte.semirremolqueDominio}"/>
						</td>
					</tr>

					<tr>
						<td width="12%" class="botoneralNegritaRight">
							Ticket Balanza
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.ticketBalanza" readonly="readonly" value="${acta.transporte.ticketBalanza}"/>
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Empresa de Pesaje
						</td>
						<td width="12%"  align="left">
							<input id="dominio" class="botonerab" type="text" size="15" name="acta.transporte.empresaDePesaje" readonly="readonly" value="${acta.transporte.empresaDePesaje}" />
						</td>
						
						<td width="12%" class="botoneralNegritaRight">
							Peso Bruto(Kgs)
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.pesoBrutoEnKilos" readonly="readonly" value="${acta.transporte.pesoBrutoEnKilos}"/>
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Tara(Kgs)
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.taraEnKilos" readonly="readonly" value="${acta.transporte.taraEnKilos}"/>
						</td>
						
					</tr>					
					
					<tr>
						<td width="12%" class="botoneralNegritaRight">
							Empresa de Transporte
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.empresaDeTransporte" readonly="readonly" value="${acta.transporte.empresaDeTransporte}"/>
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Nombre Chofer
						</td>
						<td width="12%"  align="left">
							<input id="dominio" class="botonerab" type="text" size="15" name="acta.transporte.nombreChofer" readonly="readonly" value="${acta.transporte.nombreChofer}"/>
						</td>
						
						<td width="12%" class="botoneralNegritaRight">
							Peso Neto (Kgs)
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.pesoNetoEnKilos" readonly="readonly" value="${acta.transporte.pesoNetoEnKilos}"/>
						</td>					
						
					</tr>	
																	
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>				
	</table>		
	
	
	<!-- ORIGEN Y DESTINO -->
	<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="6" class="grisSubtitulo">Origen y Destino</td>
					</tr>
					<tr>
						<td colspan="6" height="10"></td>
					</tr>			
					
					<tr>
						<td width="15%" class="botoneralNegritaRight">
							Yacimiento de Origen
						</td>
						<td width="15%" align="left">
							<input id="idYacimientoOrigen" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.yacimiento.razonSocial}" />
						</td>
						
						<td width="15%"  class="botoneralNegritaRight">
							Número de Remito
						</td>
						<td width="15%" align="left">
							<input name="acta.numeroDeRemito" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.numeroDeRemito}"/>
						</td>
						
						<td width="15%" class="botoneralNegritaRight">
							Número de Factura
						</td>
						<td width="15%" align="left">
							<input name="acta.numeroDeFactura" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.numeroDeFactura}">
						</td>
					</tr>		
					
					<tr>
						<td width="15%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.ProvinciaDestino'/>
						</td>
									
						
						<td width="15">
							<input type="text" id="idProvinciaSelect" class="botonerab" readonly="readonly" value="${acta.destino.provinciaDestino.nombre}">
						</td>
					
						<td width="15%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.LocalidadDestino'/>
						</td>

						<td width="15%" align="left">
							<input type="text" readonly="readonly" id="idLocalidadSelect" class="botonerab" value="${acta.destino.nombre}"> 
						</td>	
						
						<td width="15%" class="botoneralNegritaRight">
							Domicilio Destinatario
						</td>
						<td width="15%" align="left">
							<input name="acta.domicilioDestinatario" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.domicilioDestinatario}">
						</td>
					</tr>	
					
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>				
	</table>	
	
	
	<!-- PRODUCTO-->
	<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="8" class="grisSubtitulo">Producto</td>
					</tr>
					<tr>
						<td colspan="8" height="10"></td>
					</tr>			
					<tr>
						<td colspan="8" class="grisMuyClaroSubtituloCenter ">En Granel</td>
					</tr>
					
					<tr>
						<td width="15%" colspan="1" class="botoneralNegritaRight">
							Volumen Declarado(m3)
						</td>
						<td width="15%" colspan="2" align="left">
							<input id="granelVolumenTotal" name="acta.granelVolumenM3Declarado" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.granelVolumenM3Declarado}" >
						</td>
						
						<td width="15%" colspan="1" class="botoneralNegritaRight">
							Volumen Medido(m3)
						</td>
						<td width="15%" colspan="2" align="left">
							<input name="acta.granelVolumenM3Medido" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.granelVolumenM3Medido}" >			
						</td>
					</tr>	
					<tr>	
						<td width="15%" class="botoneralNegritaRight">
							Observaciones
						</td>
						<td  colspan="7" align="left">
							<textarea name="acta.granelObservaciones" class="botonerab" type="text" cols="120" rows="4" readonly="readonly">${acta.granelObservaciones}</textarea>
						</td>
					</tr>	
					<tr>
						<td colspan="8" class="grisMuyClaroSubtituloCenter ">En Bolsa</td>
					</tr>	
					<tr>
						<td width="15%" class="botoneralNegritaRight" >
							Cantidad
						</td>
						<td width="15%" align="left">
							<input name="acta.bolsaCantidad" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.bolsaCantidad}">
						</td>
						<td width="15%" class="botoneralNegritaRight">
							Volumen De Bolsa(dm3)
						</td>
						<td width="15%" align="left">
							<input name="acta.bolsaVolumenD3" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.bolsaVolumenD3}">
						</td>
						
						<td width="15%"  class="botoneralNegritaRight">
							Volumen Total Embolsado(m3)
						</td>
						<td width="15%" align="left">
							<input id="bolsaVolumenTotal" class="botonerab" type="text" size="15" readonly="readonly" value="${acta.bolsaVolumenTotal}">			
						</td>
						
						<td width="15%" class="botoneralNegritaRight">
							Titular Membrete
						</td>
						<td width="15%" align="left">
							<input name="acta.bolsaTitularMembrete" class="botonerab" type="text" size="50" readonly="readonly" value="${acta.bolsaTitularMembrete}">
						</td>
					</tr>							
					<tr>	
						<td width="15%"  class="botoneralNegritaRight">
							Observaciones
						</td>
						<td colspan="7" align="left">
							<textarea name="acta.bolsaObservaciones" class="botonerab" type="text" cols="120" rows="4" readonly="readonly">${acta.bolsaObservaciones}</textarea>
						</td>
					</tr>	
						
						 
					<tr>
						<td colspan="8" class="grisMuyClaroSubtituloCenter ">Volumen Total(m3): <div id="totalVol">${acta.volumenTotal}</div></td>
						
					</tr>															
					<tr>
						<td colspan="8" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>				
	</table>	
	
	
	<table border="0" class="cuadrado" align="center" width="90%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight">Oficina Minera</td>
			<td width="30%" align="left">				
				<input type="text" readonly="readonly" value="${acta.oficinaMinera.nombre}"/> 
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Fecha'/></td>
			<td align="left">		
				<input id="datepickerFecha" type="text" name="acta.fecha" readonly="readonly" class="botonerab" value="<fmt:formatDate value="${acta.fecha}" pattern="dd/MM/yyyy" />">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
	
	
	
	
	<table border="0" class="cuadrado" align="center" width="90%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4">
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volverAltaActa();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>

</div>	 				  