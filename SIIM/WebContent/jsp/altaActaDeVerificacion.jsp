<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

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
	$(function() {

		$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFecha" ).datepicker({ dateFormat: 'dd/mm/yy'});	
	});

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function setValorLocalizacion(valor){
	$("#idLocalizacion").val(valor);
}

function submitir(){
	validarForm("actaDeVerificacionForm","../actaDeVerificacion","validarActaDeVerificacionForm","ActaDeVerificacionForm");
}

function exp(sec) {
	
	 if (type=="IE") { 
	 	 eval("document.all." + "e"+sec + ".style.display='none'");
	 	 eval("document.all." + "c"+sec + ".style.display=''"); 	 
	 	}
	 if (type=="MO" || type=="OP") {
	    eval("document.getElementById('" + "e"+sec + "').style.display='none'");
	    eval("document.getElementById('" + "c"+sec + "').style.display=''");
	   }
}

function col(sec) {
	
 if (type=="IE") { 
 	 eval("document.all." + "c"+sec + ".style.display='none'");
 	 eval("document.all." + "e"+sec + ".style.display=''"); 	 
 	}
 if (type=="MO" || type=="OP") {
    eval("document.getElementById('" + "c"+sec + "').style.display='none'");
    eval("document.getElementById('" + "e"+sec + "').style.display=''");
   }
}

function reemplazarCaracter(caracAReemp, caracterNuevo, stringViejo){

	var stringNuevo = "";
	
	for(var i=0;i<stringViejo.length;i++){
		if(stringViejo.charAt(i) == caracAReemp){
			stringNuevo += caracterNuevo;	
		}else{
			stringNuevo += stringViejo.charAt(i);
		}	
	}

	return stringNuevo;
	
}

var clase2;
function pintarFila(idTr){

	$('#tr'+idTr).attr("class", "seleccionado");	
}

function despintarFila(idTr){

	if(!$('#idCheck'+idTr).is(':checked')){
		if(idTr%2){
			clase2 = "par";		
		}else{
			clase2 = "";
		}	
		$('#tr'+idTr).attr("class", clase2);
	}		
}


//-----------------------------------------------------//
//FUNCIONES DE ACTA DE VERIFICACION DE EXTRACCION//


function cambiarProductor(){

	var idProductor = $('#idProductor').val();

	if(idProductor != "-1"){
		$('#idZonaExtraccion').attr('disabled',false);
		EntidadFachada.getEntidadDTO(idProductor,cambiarProductorCallback );

		LocalizacionFachada.getLocalizacionesPorProductorDTO(idProductor,actualizarZonasExtraccionCallback);
	}
	else{
		$('#nomProductor').val("");
		$('#domProductor').val("");
		$('#locProductor').val("");
		$('#telProductor').val("");

		dwr.util.removeAllOptions("idZonaExtraccion");
		var data = [ { razonSocial:"-Seleccione una Zona-", id:-1 }];
		dwr.util.addOptions("idZonaExtraccion", data, "id", "razonSocial");		
		$('#idZonaExtraccion').attr('disabled','disabled');		
	}	
}

function cambiarProductorCallback(productor) {
	dwr.util.setValue("nomProductor", productor.nombre);
	dwr.util.setValue("domProductor", productor.direccion);
	dwr.util.setValue("locProductor", productor.localidad.nombre);
	dwr.util.setValue("telProductor", productor.telefono);

}

function actualizarZonasExtraccionCallback(zonas){
	dwr.util.removeAllOptions("idZonaExtraccion");
	var data = [ { razonSocial:"-Seleccione una Zona-", id:-1 }];
	dwr.util.addOptions("idZonaExtraccion", data, "id", "razonSocial");	
	dwr.util.addOptions("idZonaExtraccion", zonas,"id","razonSocial");	
}


function cambiarZonaExtraccion(){
	var idZonaExtraccion = $('#idZonaExtraccion').val();

	if(idZonaExtraccion != "-1"){
		LocalizacionFachada.getLocalizacionDTOPorId(idZonaExtraccion,cambiarZonaExtraccionCallback );
		$('#idYacimientoOrigen').val($('#idZonaExtraccion option:selected' ).text());
	}
	else{
		$('#domZona').val("");
		$('#supZona').val("");	
		$('#idYacimientoOrigen').val("");
		
	}	
}

function cambiarZonaExtraccionCallback(localizacion) {
	dwr.util.setValue("domZona", localizacion.domicilio);
	dwr.util.setValue("supZona", localizacion.superficie);				
}

function mostrarLocalidades(){

	var idProvinciaSelect = $('#idProvinciaSelect').val();
	if(idProvinciaSelect != "-1"){
		
		$('#idLocalidadSelect').attr('disabled',false);
		LocalidadFachada.getLocalidadesDetinoDTODeProvincia(idProvinciaSelect,actualizarLocalidadesCallback );
		
	}else{
		$('#divLocalidad').html("");
		
		dwr.util.removeAllOptions("idLocalidadSelect");
		var data = [ { nombre:"-Seleccione una Localidad-", id:-1 }];
		dwr.util.addOptions("idLocalidadSelect", data, "id", "nombre");		
		$('#idLocalidadSelect').attr('disabled',true);			
	}			
}

function actualizarLocalidadesCallback(localidades) {

	dwr.util.removeAllOptions("idLocalidadSelect");
	var data = [ {
		nombre : "-Seleccione una Localidad-",
		id : -1
	} ];
	dwr.util.addOptions("idLocalidadSelect", data, "id", "nombre");
	dwr.util.addOptions("idLocalidadSelect", localidades, "id", "nombre");
}	


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
		parent.location = contextRoot() + '/jsp.do?page=.index';		
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
<html:form action="actaDeVerificacion" styleId="actaDeVerificacionForm">
	<html:hidden property="metodo" value="altaActaDeVerificacion" />
	<table border="0" class="cuadrado" align="center" width="90%" 	cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIM.titulo.AltaActaDeVerificacion'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Numero'/></td>
			<td width="35%" align="left">
				<input name="acta.numero" class="botonerab" type="text" size="20" 
						onkeypress="javascript:esNumerico(event);">
			</td>
			<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Productor'/></td>
			<td width="35%" align="left">
				<select id="idProductor" name="acta.productor.id" class="botonerab" onchange="cambiarProductor();">
					<option value="-1">- Seleccione un Productor -</option>
					<c:forEach items="${productores}" var="prod">						
						<option value="${prod.id}">
							<c:out value="${prod.nombre}"></c:out>
						</option>
					</c:forEach>
				</select>				
			</td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIM.label.FechaVerificacion'/></td>
			<td width="35%" align="left">
				<input id="datepicker" type="text" name="acta.fechaVerificacion" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
			</td>

			<td width="15%" class="botoneralNegritaRight">Area De Verificación</td>
			<td width="35%" align="left">
				<input name="acta.areaDeVerificacion" class="botonerab" type="text" size="30" value="Dirección General de Mineria">
			</td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight">Agente Verificación</td>
			<td width="35%" align="left">
				<select id="idAgenteVerificacion" name="acta.agenteVerificacion" class="botonerab" >
					<option value="-1">- Seleccione un Usuario -</option>
					<c:forEach items="${usuarios}" var="user">						
						<option value="${user.nombreUsuario}">
							<c:out value="${user.nombreUsuario}"></c:out>
						</option>
					</c:forEach>
				</select>			
			</td>

			<td width="15%" class="botoneralNegritaRight">Area Fiscalizadora</td>
			<td width="35%" align="left">
				<input type="text"  name="acta.areaFiscalizadora" class="botonerab" size="30" value="Dirección General de Mineria" >
			</td>
		</tr>
		
		<tr>
			<td width="15%" class="botoneralNegritaRight">Funcionario Acturante</td>
			<td width="35%" align="left">
				<input type="text" name="acta.funcionarioActuante" class="botonerab" size="30" >
			</td>
		</tr>

		<tr>
			<td width="15%" class="botoneralNegritaRight">Observaciones</td>
			<td width="85%" align="left" colspan="3">
				<textarea rows="4" cols="120" name="acta.observaciones" class="botonerab" ></textarea>
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
							<input id="nomProductor" class="botonerab" type="text" size="30" readonly="readonly">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Domicilio
						</td>
						<td align="left">
							<input id="domProductor" class="botonerab" type="text" size="30" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td width="20%" class="botoneralNegritaRight">
							Localidad
						</td>
						<td width="30%" align="left">
							<input id="locProductor" class="botonerab" type="text" size="30" readonly="readonly">
						</td>					
						<td width="20%" class="botoneralNegritaRight">
							Telefono
						</td>
						<td align="left">
							<input id="telProductor" class="botonerab" type="text" size="30" readonly="readonly">
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
							<select id="idZonaExtraccion" class="botonerab" disabled="disabled" name="acta.yacimiento.id" onchange="cambiarZonaExtraccion();">
								<option value="-1">--Seleccione una Zona--</option>
							</select>					
						</td>						
					</tr>	
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Domicilio'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="domZona" class="botonerab" type="text" size="25" readonly="readonly">
						</td>						
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.Superficie'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<input id="supZona" class="botonerab" type="text" size="25" readonly="readonly">
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
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.marca" />
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Dominio
						</td>
						<td width="12%"  align="left">
							<input id="dominio" class="botonerab" type="text" size="15" name="acta.transporte.dominio" />
						</td>
						
						<td width="12%" class="botoneralNegritaRight">
							Marca Semirremolque
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.semirremolqueMarca" />
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Dominio Semirremolque
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.semirremolqueDominio" />
						</td>
					</tr>

					<tr>
						<td width="12%" class="botoneralNegritaRight">
							Ticket Balanza
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.ticketBalanza" />
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Empresa de Pesaje
						</td>
						<td width="12%"  align="left">
							<input id="dominio" class="botonerab" type="text" size="15" name="acta.transporte.empresaDePesaje" />
						</td>
						
						<td width="12%" class="botoneralNegritaRight">
							Peso Bruto(Kgs)
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.pesoBrutoEnKilos" />
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Tara(Kgs)
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.taraEnKilos" />
						</td>
						
					</tr>					
					
					<tr>
						<td width="12%" class="botoneralNegritaRight">
							Empresa de Transporte
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.empresaDeTransporte" />
						</td>					
						<td width="12%" class="botoneralNegritaRight">
							Nombre Chofer
						</td>
						<td width="12%"  align="left">
							<input id="dominio" class="botonerab" type="text" size="15" name="acta.transporte.nombreChofer" />
						</td>
						
						<td width="12%" class="botoneralNegritaRight">
							Peso Neto (Kgs)
						</td>
						<td width="12%" align="left">
							<input id="marca" class="botonerab" type="text" size="15" name="acta.transporte.pesoNetoEnKilos" />
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
							<input id="idYacimientoOrigen" class="botonerab" type="text" size="15" readonly="readonly">
						</td>
						
						<td width="15%"  class="botoneralNegritaRight">
							Número de Remito
						</td>
						<td width="15%" align="left">
							<input name="acta.numeroDeRemito" class="botonerab" type="text" size="15" />
						</td>
						
						<td width="15%" class="botoneralNegritaRight">
							Número de Factura
						</td>
						<td width="15%" align="left">
							<input name="acta.numeroDeFactura" class="botonerab" type="text" size="15">
						</td>
					</tr>		
					
					<tr>
						<td width="15%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.ProvinciaDestino'/>
						</td>
									
						
						<td width="15">
							<select id="idProvinciaSelect" class="botonerab" onchange="mostrarLocalidades();">
								<option value="-1">-Seleccione una Provincia-</option>
								<c:forEach items="${provincias}" var="provincia" varStatus="i">
									<option value="${provincia.id}">
										<c:out value="${provincia.nombre}"></c:out>
									</option>						
								</c:forEach>							
							</select>
						</td>
					
						<td width="15%" class="botoneralNegritaRight">
							<bean:message key='SIIM.label.LocalidadDestino'/>
						</td>

						<td width="15%" align="left">
							<select id="idLocalidadSelect" class="botonerab" disabled="disabled" 
									name="acta.destino.id">
								<option value="-1">-Seleccione una Localidad-</option>
							</select>																
						</td>	
						
						<td width="15%" class="botoneralNegritaRight">
							Domicilio Destinatario
						</td>
						<td width="15%" align="left">
							<input name="acta.domicilioDestinatario" class="botonerab" type="text" size="15">
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
							<input id="granelVolumenTotal" name="acta.granelVolumenM3Declarado" class="botonerab" type="text" size="15" onchange="calcularVolTotal();">
						</td>
						
						<td width="15%" colspan="1" class="botoneralNegritaRight">
							Volumen Medido(m3)
						</td>
						<td width="15%" colspan="2" align="left">
							<input name="acta.granelVolumenM3Medido" class="botonerab" type="text" size="15">			
						</td>
					</tr>	
					<tr>	
						<td width="15%" class="botoneralNegritaRight">
							Observaciones
						</td>
						<td  colspan="7" align="left">
							<textarea name="acta.granelObservaciones" class="botonerab" type="text" cols="120" rows="4"></textarea>
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
							<input name="acta.bolsaCantidad" class="botonerab" type="text" size="15" onchange="calcularVolBolsa();">
						</td>
						<td width="15%" class="botoneralNegritaRight">
							Volumen De Bolsa(dm3)
						</td>
						<td width="15%" align="left">
							<input name="acta.bolsaVolumenD3" class="botonerab" type="text" size="15" onchange="calcularVolBolsa();">
						</td>
						
						<td width="15%"  class="botoneralNegritaRight">
							Volumen Total Embolsado(m3)
						</td>
						<td width="15%" align="left">
							<input id="bolsaVolumenTotal" class="botonerab" type="text" size="15" readonly="readonly" onchange="calcularVolTotal();">			
						</td>
						
						<td width="15%" class="botoneralNegritaRight">
							Titular Membrete
						</td>
						<td width="15%" align="left">
							<input name="acta.bolsaTitularMembrete" class="botonerab" type="text" size="50">
						</td>
					</tr>							
					<tr>	
						<td width="15%"  class="botoneralNegritaRight">
							Observaciones
						</td>
						<td colspan="7" align="left">
							<textarea name="acta.bolsaObservaciones" class="botonerab" type="text" cols="120" rows="4"></textarea>
						</td>
					</tr>	
						
						 
					<tr>
						<td colspan="8" class="grisMuyClaroSubtituloCenter ">Volumen Total(m3): <div id="totalVol"></div></td>
						
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
				<select id="idLocalidad" class="botonerab" name="acta.oficinaMinera.id">
					<option value="">- Seleccione una Localidad -</option>
					<c:forEach items="${localidades}" var="localidad">
						<option value="${localidad.id}">
							<c:out value="${localidad.nombre}"></c:out>
						</option>
					</c:forEach>
				</select>				
				
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIM.label.Fecha'/></td>
			<td align="left">		
				<input id="datepickerFecha" type="text" name="acta.fecha" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				
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
				<input type="button" value="Aceptar" id="enviar" 
					class="botonerab" onclick="javascript:submitir();" > 
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volverAltaActa();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</html:form>
</div>	 				  