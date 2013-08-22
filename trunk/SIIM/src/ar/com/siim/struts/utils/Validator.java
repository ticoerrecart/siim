/*
 * Creado el 05/12/2006
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package ar.com.siim.struts.utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;

import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.TrimestreDeclaracionDeExtraccionDTO;
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

	public static boolean requerido(String entrada, String label,
			StringBuffer pError) {
		if (entrada != null && !entrada.equals("")) {
			return true;
		}
		addErrorXML(pError, label + " es un dato obligatorio");

		return false;
	}

	public static boolean requerido(Object entrada, String label,
			StringBuffer pError) {
		if (entrada != null) {
			return true;
		}
		addErrorXML(pError, label + " es un dato obligatorio");

		return false;
	}

	public static boolean validarRequeridoSi(String entradaCondicion,
			String valorCondicion, String entrada, String label,
			StringBuffer pError) {

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
	public static boolean validarComboRequerido(String valorEntradaDistinto,
			String entrada, String label, StringBuffer pError) {

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
	 * Si la entrada es nula entonces se considera invalido
	 */
	public static boolean validarComboRequeridoSinNull(
			String valorEntradaDistinto, String entrada, String label,
			StringBuffer pError) {

		if (entrada == null || entrada.equals("") || entrada.equals("null")
				|| entrada.equals(valorEntradaDistinto)) {
			addErrorXML(pError, label + " es un dato obligatorio");
			return false;
		}
		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarComboRequeridoSi(String entradaCondicion,
			String valorCondicion, String valorEntradaDistinto, String entrada,
			String label, StringBuffer pError) {

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
	public static boolean validarEnteroMayorQue(int numeroMinimo,
			String entrada, String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt <= numeroMinimo) {
				addErrorXML(pError, label + " debe ser un número mayor a "
						+ Integer.toString(numeroMinimo));
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
	public static boolean validarEnteroMenorQue(int numeroMaximo,
			String entrada, String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt >= numeroMaximo) {
				addErrorXML(pError, label + " debe ser un número menor a "
						+ Integer.toString(numeroMaximo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, label + " debe ser un número entero");
			return false;
		}
		return true;
	}

	public static boolean validarLongMayorQue(int numeroMinimo, String entrada,
			String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			long entradaLong = Long.parseLong(entrada);
			if (entradaLong <= numeroMinimo) {
				addErrorXML(pError, label + " debe ser un número mayor a "
						+ Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, " debe ser un número entero");
			return false;
		}

		return true;
	}

	public static boolean validarDoubleMayorQue(int numeroMinimo,
			String entrada, String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			double entradaDouble = Double.parseDouble(entrada);
			if (isNaN(entradaDouble) || entradaDouble <= numeroMinimo) {
				addErrorXML(pError, label + " debe ser un número mayor a "
						+ Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError,
					" debe ser un número entero con decimales válido");
			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido chequea que el a�o se
	 * mayor que 1900 y menor que 2100
	 */
	public static boolean validarFechaValida(String pEntrada, String label,
			StringBuffer pError) {
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
				addErrorXML(
						pError,
						label
								+ " invalida, el dia debe ser menor igual que 31 y mayor que 0");
				return false;
			} else if (mesInt > 12 || mesInt < 1) {
				addErrorXML(
						pError,
						label
								+ " invalida, el mes debe ser menor igual que 12 y mayor que 0");
				return false;
			} else if (anioInt > 2100 || anioInt < 1900) {
				addErrorXML(
						pError,
						label
								+ " invalida, el año debe ser menor igual que 2100 y mayor que 1900");
				return false;
			}

			return DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy");

		} catch (Exception e) {
			addErrorXML(
					pError,
					label
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
	public static boolean validarFechaPasadaValida(String pEntrada,
			String label, StringBuffer pError) {
		boolean salida = true;
		if (pEntrada == null || pEntrada.equals("")) {
			return true;
		}
		if (!DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy")) {
			addErrorXML(
					pError,
					label
							+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
			return false;
		}
		if (!DateUtils.validateDatePeriod(
				DateUtils.dateFromString(pEntrada, "dd/MM/yyyy"),
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
	public static boolean validarFechaMenorA(String pFechaDesde,
			String pFechaHasta, String labelFechaDesde, String labelFechaHasta,
			StringBuffer pError) {

		boolean salida = true;
		if (pFechaDesde == null || pFechaDesde.equals("")) {
			addErrorXML(pError, labelFechaDesde + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaDesde, true, "dd/MM/yyyy")) {
				addErrorXML(
						pError,
						labelFechaDesde
								+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (pFechaHasta == null || pFechaHasta.equals("")) {
			addErrorXML(pError, labelFechaHasta + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaHasta, true, "dd/MM/yyyy")) {
				addErrorXML(
						pError,
						labelFechaHasta
								+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (salida
				&& DateUtils.isBefore(
						DateUtils.dateFromString(pFechaHasta, "dd/MM/yyyy"),
						DateUtils.dateFromString(pFechaDesde, "dd/MM/yyyy"))) {
			addErrorXML(pError, "Fecha Desde debe ser menor a Fecha Hasta");
			salida = false;
		}

		return salida;
	}

	public static boolean validarSN(String tipoLote) {
		return (tipoLote.equals("S") || tipoLote.equals("N"));
	}

	public static boolean validarEmail(String email, String label,
			StringBuffer pError) {
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

		if (matchFound && lastToken.length() >= 2
				&& email.length() - 1 != lastToken.length()) {
			// validate the country code
			return true;
		}

		addErrorXML(pError, label + " no es un e-mail válido");
		return false;

	}

	public static boolean validarAlfaNumerico(String valor, String label,
			StringBuffer pError) {
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

	public static boolean validarNumerico(String valor, String label,
			StringBuffer pError) {
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

	public static boolean validarCaracter(String valor, String label,
			StringBuffer pError) {
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

	public static boolean validarLongitudHasta(String valor, int longitud,
			String label, StringBuffer pError) {
		if ((valor == null) || valor.length() <= longitud) {
			return true;
		}
		addErrorXML(pError, label + " no es válido, se permite hasta "
				+ longitud + " posiciones");
		return false;
	}

	/**
	 * true si es null , string vac�o o letras, si el valor longitudMaxima es 0,
	 * no toma cuenta el largo del string.
	 */
	public static boolean validarLetras(String valor, int longitudMaxima,
			String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		if ((valor.matches(" *[a-zA-Z]* *"))
				&& (longitudMaxima == 0 || valor.length() <= longitudMaxima)) {
			return true;
		}
		addErrorXML(pError, label
				+ " no es válido, solo letras"
				+ (longitudMaxima != 0 ? " hasta " + longitudMaxima
						+ " posiciones" : ""));
		return false;
	}

	public static boolean validarLetras(String valor, String label,
			StringBuffer pError) {
		return validarLetras(valor, 0, label, pError);
	}

	public static boolean validarFormatoPeriodo(String periodo,
			StringBuffer pError) {
		try {
			String[] strArray = periodo.split("-");
			int n = Integer.parseInt(strArray[0]);
			int n2 = Integer.parseInt(strArray[1]);
			if (n + 1 != n2) {
				addErrorXML(pError,
						"Los Años del periodo deben ser consecutivos");
				return false;
			}
			return true;
		} catch (Exception e) {
			addErrorXML(pError,
					"El formato del periodo deben ser AAAA-AAAA. Ej 2011-2012");
			return false;
		}
	}

	public static boolean isNaN(double d) {

		return (d != d);
	}

	public static boolean validarProductorRequerido(String idProductor,
			StringBuffer pError) {

		if (idProductor == null || idProductor.equals("")
				|| (idProductor != null && Long.parseLong(idProductor) <= 0)) {
			addErrorXML(pError, "Productor es un dato obligatorio");
			return false;
		}
		return true;
	}

	public static boolean validarLocalizacionRequerido(String idLocalizacion,
			StringBuffer pError) {

		if (idLocalizacion == null
				|| idLocalizacion.equals("")
				|| (idLocalizacion != null && Long.parseLong(idLocalizacion) <= 0)) {
			addErrorXML(pError, "Localización es un dato obligatorio");
			return false;
		}
		return true;
	}

	public static boolean validarBoletasDeposito(
			List<BoletaDepositoDTO> boletas, double montoTotal,
			StringBuffer pError) {

		if (boletas.size() == 0) {
			addErrorXML(pError,
					"La Cantidad de Boletas de Deposito debe ser un numero mayor a 0");
			return false;
		}/*
		 * else { // verifico que haya al menos una boleta no eliminada int
		 * cantEliminadas = 0; for (BoletaDepositoDTO boletaDepositoDTO :
		 * boletas) { if (boletaDepositoDTO.getAnulado()) { cantEliminadas++; }
		 * } if (cantEliminadas == boletas.size()) { addErrorXML(pError,
		 * "La Cantidad de Boletas de Deposito debe ser un numero mayor a 0");
		 * return false; } }
		 */

		double montoSumaBoletas = 0;
		List<BoletaDepositoDTO> listaBoletas = new ArrayList<BoletaDepositoDTO>();
		for (BoletaDepositoDTO boleta : boletas) {
			if (!boleta.esNula() && !boleta.getAnulado()) {
				if (listaBoletas.size() == 0) {
					// tengo q crear una nueva porq sino no anda, se borra de la
					// coleccion
					BoletaDepositoDTO b = new BoletaDepositoDTO();
					b.setNumero(boleta.getNumero());
					listaBoletas.add(b);
				} else {
					BoletaDepositoDTO boletaEncontrada = (BoletaDepositoDTO) CollectionUtils
							.find(listaBoletas,
									new BeanPropertyValueEqualsPredicate(
											"numero", boleta.getNumero()));
					if (boletaEncontrada == null) {
						// tengo q crear una nueva porq sino no anda, se borra
						// de la
						// coleccion
						BoletaDepositoDTO b = new BoletaDepositoDTO();
						b.setNumero(boleta.getNumero());
						listaBoletas.add(b);
					} else {
						addErrorXML(pError,
								"Los Números de las Boletas de Deposito no se pueden repetir");
						return false;
					}
				}

				montoSumaBoletas = montoSumaBoletas + boleta.getMonto();
				if (boleta.getNumero() <= 0) {
					addErrorXML(pError,
							"Faltan datos en el Nro de Boleta de Deposito");
					return false;
				}

				if (boleta.getConcepto() == null
						|| boleta.getConcepto().equals("")) {
					addErrorXML(pError,
							"Faltan datos en el Concepto de la Boleta de Deposito");
					return false;
				}

				if (boleta.getArea() == null || boleta.getArea().equals("")) {
					addErrorXML(pError,
							"Faltan datos en el Area de la Boleta de Deposito");
					return false;
				}

				if (boleta.getFechaVencimiento() == null
						|| boleta.getFechaVencimiento().equals("")) {
					addErrorXML(pError,
							"Faltan datos en la Fecha de Vencimiento de la Boleta de Deposito");
					return false;
				}

				if (boleta.getMonto() <= 0.0) {
					addErrorXML(pError,
							"Faltan datos en el monto de la Boleta de Deposito");
					return false;
				}

			}
		}// end for

		montoSumaBoletas = MathUtils.round(montoSumaBoletas, 2);

		if (montoSumaBoletas != montoTotal) {
			addErrorXML(
					pError,
					"La suma de los montos de las Boletas de Deposito debe ser igual al Importe Total");
			return false;
		}
		return true;
	}

	public static boolean validarTrimestres(
			List<TrimestreDeclaracionDeExtraccionDTO> trimestres,
			StringBuffer pError) {
		boolean trimestresNulos = true;
		boolean fechasValidas = true;
		for (TrimestreDeclaracionDeExtraccionDTO trimestreDTO : trimestres) {
			if (trimestreDTO.getVolumenPrimerMes() != 0
					|| trimestreDTO.getVolumenSegundoMes() != 0
					|| trimestreDTO.getVolumenTercerMes() != 0) {
				fechasValidas = fechasValidas
						&& Validator.requerido(
								trimestreDTO.getFechaVencimiento(),
								"La fecha de Vencimiento del Trimestre Nro."
										+ trimestreDTO.getNroTrimestre(),
								pError);
				trimestresNulos = false;
				break;
			}
		}

		if (trimestresNulos) {
			addErrorXML(pError, "Debe ingresar algún Trimestre");
		}
		return !trimestresNulos && fechasValidas;
	}
}