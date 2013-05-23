/*
 * Creado el 05/12/2006
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package ar.com.siim.struts.utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

/*import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.Fiscalizacion;*/
import ar.com.siim.utils.DateUtils;
import ar.com.siim.utils.MathUtils;

/**
 * @author rdiaz
 * 
 *         Contiene metodos statics para la validacion de los objetos es para
 *         hacer validaciones usando AJAX.
 * 
 */
public abstract class Validator {

	public static String XML_HEADER = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?> ";

	public static StringBuffer abrirXML() {
		return new StringBuffer(XML_HEADER + "\n<datos>");
	}

	public static StringBuffer cerrarXML(StringBuffer xml) {
		return xml.append("\n</datos>");
	}

	public static StringBuffer addErrorXML(StringBuffer xml, String pMensaje) {
		xml.append("\n" + "<error>" + pMensaje + "</error>");
		return xml;
	}

	public static StringBuffer addIdJspXML(StringBuffer xml, String pMensaje) {
		xml.append("\n" + "<formId>" + pMensaje + "</formId>");
		return xml;
	}

	public static boolean requerido(String entrada, String label, StringBuffer pError) {
		if (entrada != null && !entrada.equals("")) {
			return true;
		}
		addErrorXML(pError, label + " es un dato obligatorio");

		return false;
	}

	public static boolean validarRequeridoSi(String entradaCondicion, String valorCondicion,
			String entrada, String label, StringBuffer pError) {

		if (entradaCondicion != null && entradaCondicion.equals(valorCondicion)) {
			if (entrada != null && !entrada.equals("")) {
				return true;
			}
			addErrorXML(pError, label + " es un dato obligatorio");

			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarComboRequerido(String valorEntradaDistinto, String entrada,
			String label, StringBuffer pError) {

		if (entrada == null || entrada.equals("")) {
			return true;
		}
		if (entrada.equals(valorEntradaDistinto)) {
			addErrorXML(pError, label + " es un dato obligatorio");
			return false;
		}
		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarComboRequeridoSi(String entradaCondicion, String valorCondicion,
			String valorEntradaDistinto, String entrada, String label, StringBuffer pError) {

		if (entradaCondicion != null && entradaCondicion.equals(valorCondicion)) {
			if (entrada != null && !entrada.equals(valorEntradaDistinto)) {
				return true;
			}
			addErrorXML(pError, label + " es un dato obligatorio");

			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarEnteroMayorQue(int numeroMinimo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt <= numeroMinimo) {
				addErrorXML(pError,
						label + " debe ser un número mayor a " + Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, label + " debe ser un número entero");
			return false;
		}
		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarEnteroMenorQue(int numeroMaximo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt >= numeroMaximo) {
				addErrorXML(pError,
						label + " debe ser un número menor a " + Integer.toString(numeroMaximo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, label + " debe ser un número entero");
			return false;
		}
		return true;
	}

	public static boolean validarLongMayorQue(int numeroMinimo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			long entradaLong = Long.parseLong(entrada);
			if (entradaLong <= numeroMinimo) {
				addErrorXML(pError,
						label + " debe ser un número mayor a " + Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, " debe ser un número entero");
			return false;
		}

		return true;
	}

	public static boolean validarDoubleMayorQue(int numeroMinimo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			double entradaDouble = Double.parseDouble(entrada);
			if (isNaN(entradaDouble) || entradaDouble <= numeroMinimo) {
				addErrorXML(pError,
						label + " debe ser un número mayor a " + Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, " debe ser un número entero con decimales válido");
			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido chequea que el a�o se
	 * mayor que 1900 y menor que 2100
	 */
	public static boolean validarFechaValida(String pEntrada, String label, StringBuffer pError) {
		if (pEntrada == null || pEntrada.equals("")) {
			return true;
		}
		String entrada = new String(pEntrada);
		try {
			int positionBarra = entrada.indexOf('/');
			String dia = entrada.substring(0, positionBarra);
			entrada = entrada.substring(positionBarra + 1);
			positionBarra = entrada.indexOf('/');
			String mes = entrada.substring(0, positionBarra);
			entrada = entrada.substring(positionBarra + 1);
			String anio = entrada;
			int anioInt = Integer.parseInt(anio);
			int mesInt = Integer.parseInt(mes);
			int diaInt = Integer.parseInt(dia);
			if (diaInt > 31 || diaInt < 1) {
				addErrorXML(pError, label
						+ " invalida, el dia debe ser menor igual que 31 y mayor que 0");
				return false;
			} else if (mesInt > 12 || mesInt < 1) {
				addErrorXML(pError, label
						+ " invalida, el mes debe ser menor igual que 12 y mayor que 0");
				return false;
			} else if (anioInt > 2100 || anioInt < 1900) {
				addErrorXML(pError, label
						+ " invalida, el año debe ser menor igual que 2100 y mayor que 1900");
				return false;
			}

			return DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy");

		} catch (Exception e) {
			addErrorXML(pError, label
					+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
			return false;
		}

	}

	/**
	 * Verifica que la fecha sea v�lida y anterior a fecha actual. si es nula o
	 * blanco devuelve true
	 * 
	 * @param pEntrada
	 * @param label
	 * @param pError
	 * @return boolean
	 */
	public static boolean validarFechaPasadaValida(String pEntrada, String label,
			StringBuffer pError) {
		boolean salida = true;
		if (pEntrada == null || pEntrada.equals("")) {
			return true;
		}
		if (!DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy")) {
			addErrorXML(pError, label
					+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
			return false;
		}
		if (!DateUtils.validateDatePeriod(DateUtils.dateFromString(pEntrada, "dd/MM/yyyy"),
				GregorianCalendar.getInstance().getTime())) {
			addErrorXML(pError, label + " invalida, debe ser una fecha pasada.");
			salida = false;
		}

		return salida;
	}

	/**
	 * Verifica que la fecha sea v�lida y anterior a fecha actual. si es nula o
	 * blanco devuelve true
	 * 
	 * @param pEntrada
	 * @param label
	 * @param pError
	 * @return boolean
	 */
	public static boolean validarFechaMenorA(String pFechaDesde, String pFechaHasta,
			String labelFechaDesde, String labelFechaHasta, StringBuffer pError) {

		boolean salida = true;
		if (pFechaDesde == null || pFechaDesde.equals("")) {
			addErrorXML(pError, labelFechaDesde + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaDesde, true, "dd/MM/yyyy")) {
				addErrorXML(pError, labelFechaDesde
						+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (pFechaHasta == null || pFechaHasta.equals("")) {
			addErrorXML(pError, labelFechaHasta + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaHasta, true, "dd/MM/yyyy")) {
				addErrorXML(pError, labelFechaHasta
						+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (salida
				&& DateUtils.isBefore(DateUtils.dateFromString(pFechaHasta, "dd/MM/yyyy"),
						DateUtils.dateFromString(pFechaDesde, "dd/MM/yyyy"))) {
			addErrorXML(pError, "Fecha Desde debe ser menor a Fecha Hasta");
			salida = false;
		}

		return salida;
	}

	public static boolean validarSN(String tipoLote) {
		return (tipoLote.equals("S") || tipoLote.equals("N"));
	}

	public static boolean validarEmail(String email, String label, StringBuffer pError) {
		if (email == null || email.equals("")) {
			return true;
		}
		// Input the string for validation
		// String email = "xyz@.com";
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher m = p.matcher(email);

		// check whether match is found
		boolean matchFound = m.matches();

		StringTokenizer st = new StringTokenizer(email, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}

		if (matchFound && lastToken.length() >= 2 && email.length() - 1 != lastToken.length()) {
			// validate the country code
			return true;
		}

		addErrorXML(pError, label + " no es un e-mail válido");
		return false;

	}

	public static boolean validarAlfaNumerico(String valor, String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("[\\w]*");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un AlfaNumérico válido");
		return false;
	}

	public static boolean validarNumerico(String valor, String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("(?=[^A-Za-z]+$).*[0-9].*");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un Numérico válido");
		return false;
	}

	public static boolean validarCaracter(String valor, String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("[a-zA-Z]");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un Caracter válido");
		return false;
	}

	public static boolean validarLongitudHasta(String valor, int longitud, String label,
			StringBuffer pError) {
		if ((valor == null) || valor.length() <= longitud) {
			return true;
		}
		addErrorXML(pError, label + " no es válido, se permite hasta " + longitud + " posiciones");
		return false;
	}

	/**
	 * true si es null , string vac�o o letras, si el valor longitudMaxima es 0,
	 * no toma cuenta el largo del string.
	 */
	public static boolean validarLetras(String valor, int longitudMaxima, String label,
			StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		if ((valor.matches(" *[a-zA-Z]* *"))
				&& (longitudMaxima == 0 || valor.length() <= longitudMaxima)) {
			return true;
		}
		addErrorXML(pError, label + " no es válido, solo letras"
				+ (longitudMaxima != 0 ? " hasta " + longitudMaxima + " posiciones" : ""));
		return false;
	}

	public static boolean validarLetras(String valor, String label, StringBuffer pError) {
		return validarLetras(valor, 0, label, pError);
	}

	public static boolean validarFormatoPeriodo(String periodo, StringBuffer pError){
		try {
			String[] strArray = periodo.split("-");
			int n = Integer.parseInt(strArray[0]);
			int n2 = Integer.parseInt(strArray[1]);
			if (n+1 != n2) {
				addErrorXML(pError, "Los Años del periodo deben ser consecutivos");
				return false;
			} 
			return true;
		} catch (Exception e) {
			addErrorXML(pError, "El formato del periodo deben ser AAAA-AAAA. Ej 2011-2012");
			return false;
		}
	}	
	
	public static boolean isNaN(double d){
		
		return (d!=d);
	}
	
	/*
	public static boolean validarMuestras(List<MuestraDTO> muestras, Long idTipoProducto,
			StringBuffer pError) {

		//int cantNulos = 0;

		if (muestras.size() == 0) {
			addErrorXML(pError, "Cantidad de Muestras debe ser un numero mayor a 0");
			return false;
		}

		for (MuestraDTO muestra : muestras) {
			if (muestra != null) {
				if (idTipoProducto == 2 || idTipoProducto == 5) {
					if (muestra.getLargo() == 0.0 || muestra.getDiametro1() == 0.0
							|| muestra.getDiametro2() == 0.0) {
						addErrorXML(pError, "Faltan datos de Largo y/o Diametro en las Muestras");
						return false;
					}
				} else {
					if (idTipoProducto == 1 || idTipoProducto == 4) {
						if (muestra.getLargo() == 0.0 || muestra.getDiametro1() == 0.0) {
							addErrorXML(pError,
									"Faltan datos de Largo y/o Diametro en las Muestras");
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	public static boolean validarBoletasDeposito(List<BoletaDepositoDTO> boletas,
			double montoTotal, StringBuffer pError) {

		if (boletas.size() == 0) {
			addErrorXML(pError, "La Cantidad de Boletas de Deposito debe ser un numero mayor a 0");
			return false;
		}
		double montoSumaBoletas = 0;
		for (BoletaDepositoDTO boleta : boletas) {
			montoSumaBoletas = montoSumaBoletas + boleta.getMonto();
			if (boleta.getNumero() <= 0 || boleta.getConcepto() == null
					|| boleta.getConcepto().equals("") || boleta.getArea() == null
					|| boleta.getArea().equals("") || boleta.getMonto() <= 0.0) {
				addErrorXML(pError, "Faltan datos en las Boletas de Deposito");
				return false;
			}
		}
		
		montoSumaBoletas = MathUtils.round(montoSumaBoletas, 2);
		
		if (montoSumaBoletas != montoTotal) {
			addErrorXML(pError,
					"La suma de los montos de las Boletas de Deposito debe ser igual al Importe Total");
			return false;
		}
		return true;
	}

	public static boolean validarValesTransporte(List<ValeTransporteDTO> vales, StringBuffer pError) {

		if (vales.size() == 0) {
			addErrorXML(pError, "La Cantidad de Vales de Transporte debe ser un numero mayor a 0");
			return false;
		}
		for (ValeTransporteDTO vale : vales) {

			if (vale.getNumero() <= 0 || vale.getOrigen() == null || vale.getOrigen().equals("")
					|| vale.getDestino() == null || vale.getDestino().equals("")
					|| vale.getVehiculo() == null || vale.getVehiculo().equals("")
					|| vale.getMarca() == null || vale.getMarca().equals("")
					|| vale.getDominio() == null || vale.getDominio().equals("")
					|| vale.getProducto() == null || vale.getProducto().equals("")
					|| (vale.getNroPiezas() <= 0 && vale.getCantidadMts() <= 0.0)
					|| vale.getEspecie() == null || vale.getEspecie().equals("")) {
				addErrorXML(pError, "Faltan datos en los Vales de Transporte");
				return false;
			}
		}
		return true;
	}

	public static boolean validarTipoProductoAltaGFB(Long idTipoProductoGuia,
			Long idTipoProductoFiscalizacion, StringBuffer pError) {

		if (idTipoProductoGuia.longValue() != idTipoProductoFiscalizacion.longValue()) {
			addErrorXML(pError, "El Tipo de Producto debe ser igual al de las Fiscalizaciones");
			return false;
		}
		return true;
	}

	public static boolean validarRangos(List<RangoDTO> rangos, StringBuffer pError) {
		if (rangos.size() == 0) {
			addErrorXML(pError, "La Cantidad de Rangos debe ser un numero mayor a 0");
			return false;
		}
		for (RangoDTO rango : rangos) {
			if (rango.getDesde() <= 0 || rango.getHasta() <= 0) {
				addErrorXML(pError, "Los valores Desde y Hasta deben ser enteros positivos");
				return false;
			}
			if (rango.getDesde() > rango.getHasta()) {
				addErrorXML(pError, "El valor Desde no puede ser mayor que el valor Hasta");
				return false;
			}
		}
		List<Integer> lista = new ArrayList<Integer>();
		for (RangoDTO rango : rangos) {
			lista.add(rango.getDesde());
			lista.add(rango.getHasta() + 1);
		}
		Integer i = new Integer(-1);
		for (Integer integer : lista) {
			if (i > integer) {
				addErrorXML(pError, "Los valores de los rangos estan superpuestos");
				return false;
			} else {
				i = integer;
			}
		}
		return true;
	}		
	
	public static boolean validarSubImportes(List<SubImporteDTO> listaSubImportes, List<FiscalizacionDTO> listaFiscalizaciones,
													String tipoTerreno,StringBuffer pError)
	{

		HashMap<Long,SubImporteDTO> hashProductosFiscalizados = new HashMap<Long, SubImporteDTO>();
		Set<SubImporteDTO> listaIdTipoProducto = new TreeSet<SubImporteDTO>();
		
		for (SubImporteDTO subImporteDTO : listaSubImportes) {
			
			hashProductosFiscalizados.put(subImporteDTO.getTipoProducto().getId(), subImporteDTO);
			
			if(listaIdTipoProducto.contains(subImporteDTO)){
				addErrorXML(pError, "Debe especificar un solo subImporte por cada Tipo de Producto");
				return false;
			}
			if(subImporteDTO.getEstado()== null || subImporteDTO.getEstado().equals("")){
				addErrorXML(pError, "Faltan especificar datos en los subImportes");
				return false;
			}
			if(subImporteDTO.getEspecie()== null || subImporteDTO.getEspecie().trim().equals("")){
				addErrorXML(pError, "Faltan especificar datos en los subImportes");
				return false;
			}	
			if(subImporteDTO.getCantidadMts() <= 0.0){
				addErrorXML(pError, "Faltan especificar datos en los subImportes");
				return false;
			}
			if(subImporteDTO.getValorAforos() <= 0.0){
				addErrorXML(pError, "Faltan especificar datos en los subImportes");
				return false;
			}
			if(!tipoTerreno.equals("Privado") && subImporteDTO.getImporte() <= 0.0){
				addErrorXML(pError, "Faltan especificar datos en los subImportes");
				return false;
			}			
			listaIdTipoProducto.add(subImporteDTO);
		}
		
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizaciones) {
			if(fiscalizacionDTO.getId() != null){
				SubImporteDTO subImporte = hashProductosFiscalizados.get(fiscalizacionDTO.getTipoProducto().getId());
				if(subImporte == null){
					addErrorXML(pError, "Debe agregar todas las fiscalizaciones al calculo del importe");
					return false;				
				}				
			}	
		}		
		
		return true;
	}
	
	public static boolean validarRodalRequerido(Long idRodal , StringBuffer pError){
		
		if(idRodal == null || idRodal.longValue() <= 0){
			addErrorXML(pError, "Rodal es un dato obligatorio");
			return false;
		}
		return true;
	}
	
	public static boolean validarFormatoPeriodo(String periodo, StringBuffer pError){
		try {
			String[] strArray = periodo.split("-");
			int n = Integer.parseInt(strArray[0]);
			int n2 = Integer.parseInt(strArray[1]);
			if (n+1 != n2) {
				addErrorXML(pError, "Los A�os del periodo deben ser consecutivos");
				return false;
			} 
			return true;
		} catch (Exception e) {
			addErrorXML(pError, "El formato del periodo deben ser AAAA-AAAA. Ej 2011-2012");
			return false;
		}
	}
	
	public static boolean validarM3ValesMenorQueM3Fiscalizaciones(double m3Vales, double m3Fiscalizaciones, StringBuffer pError){
		if (m3Vales > m3Fiscalizaciones + 1){
			addErrorXML(pError, "La suma de los M3 de los vales de transporte deben ser menores que los M3 declarados en las fiscalizaciones");
			return false;
		}
		return true;
	}
	
	public static boolean validarFiscalizacionExistenteParaVale(List<Fiscalizacion> fiscalizaciones, String tipoProducto, StringBuffer pError){
		for (Fiscalizacion fiscalizacion : fiscalizaciones) {
			if (fiscalizacion.getTipoProducto().getNombre().equalsIgnoreCase(tipoProducto)){
				return true;
			}
		}
		addErrorXML(pError, "Debe existir al menos una Fiscalizacion para el tipo de Proucto del Vale de Transporte");
		return false;
	}*/
	
}