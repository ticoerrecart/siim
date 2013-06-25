package ar.com.siim.struts.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Fecha {
	public Fecha() {
	}

	public static void main(String[] args) {
		//testing ...
		//String s = formatAAAAMMDDHHMMSS("19750304175536");	
		
		String fecha = getFechaHoyDDMMAAAAhhmmssSlash();
		Date d = stringAAAAMMDDHHMMSSToDateSlash(fecha);
		System.out.println(d);
	}

	/**
	 *   Recibe una fecha y la convierte a fecha juliana.
	 */
	public static String julianDate(int intYear, int intMonth, int intDay) {
		Calendar cal = Calendar.getInstance();
		cal.set(intYear, intMonth - 1, intDay);

		/* Día Juliano -> rellena a izquierda con 0 hasta completar 3 posiciones */
		String strJulianDay = Integer.toString(cal.get(Calendar.DAY_OF_YEAR));
		int i;
		int intLength = strJulianDay.length();

		for (i = 0; i < (3 - intLength); i++)
			strJulianDay = String.valueOf('0') + strJulianDay;

		/* Año Juliano -> Toma las ultimas 3 posiciones de la suma del año + 100 */
		String strJulianYear = Integer.toString(cal.get(Calendar.YEAR) + 100);
		strJulianYear = strJulianYear.substring(1, 4);

		return (strJulianYear + strJulianDay);
	}

	/**
	 * Devuelve el ultimo dia del mes de un año.
	 * el mes recibido es ENERO = 1, DICIEMBRE = 12 
	 */
	public static byte getUltimoDiaMes(short anio, byte mes) {
		Calendar cal = Calendar.getInstance();
		//le pasamos el mes siguiente ya que el calendar interpreta 0=enero, 1=febrero, etc.. 
		cal.set(anio, mes, 1);
		//le restamos uno para pasar al ultimo dia del mes pasado por parametro
		cal.add(Calendar.DATE, -1);
		return (byte) cal.get(Calendar.DATE);
	}

	/**
	 * Verifica si un año es bisiesto.
	 */
	public static boolean bisiesto(short intYear) {
		if ((((intYear % 4) == 0) && ((intYear % 100) != 0)) || ((intYear % 400) == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Convierte una fecha en una cadena de caracteres
	 public static String dateToString(Date pFecha) {
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(pFecha);
	 
	 String d = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	 String m = String.valueOf(cal.get(Calendar.MONTH) + 1);
	 String a = String.valueOf(cal.get(Calendar.YEAR));
	 
	 return d + "-" + m + "-" + a;
	 } */
	/**
	 * Convierte una fecha en una cadena de caracteres
	 */
	public static String dateToString(Date pFecha) {
		String strDate;
		DateFormat dateFormatter;
		Locale argLocale;

		argLocale = new Locale("es", "AR");

		dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, argLocale);
		strDate = dateFormatter.format(pFecha);

		return strDate.toString();
	}

	/**
	 * Convierte una fecha en una cadena de caracteres, con la hora incluída
	 */
	public static String dateTimeToString(Date pFecha) {
		String strDate;
		DateFormat dateFormatter;
		Locale argLocale;

		argLocale = new Locale("es", "AR");

		dateFormatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM,
				argLocale);
		strDate = dateFormatter.format(pFecha);

		return strDate.toString();
	}

	/**
	 * Convierte una fecha en una cadena de tipo aaaammdd
	 */
	public static String dateToStringAAAAMMDD(Date pFecha) {
		String strDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		strDate = sdf.format(pFecha);

		return strDate;
	}

	/**
	 * Convierte la hora de una fecha en una cadena de caracteres de tipo hhmmss
	 */
	public static String dateTimeToStringHHMMSS(Date pFecha) {
		String strDate;
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

		strDate = sdf.format(pFecha);

		return strDate;
	}

	/**
	 * Convierte un String de la forma aaaammdd a un Date
	 */
	public static Date stringAAAAMMDDToDate(String pFecha) {
		return new GregorianCalendar(Integer.parseInt(pFecha.substring(0, 4)),
				Integer.parseInt(pFecha.substring(4, 6)) - 1, Integer.parseInt(pFecha.substring(6,
						8)), 0, 0, 0).getTime();
	}

	/**
	 * Convierte un String de la forma aaaammddhhMMss a un Date
	 */
	public static Date stringAAAAMMDDHHMMSSToDate(String pFecha) {
		return new GregorianCalendar(Integer.parseInt(pFecha.substring(0, 4)),
				Integer.parseInt(pFecha.substring(4, 6)) - 1, Integer.parseInt(pFecha.substring(6,
						8)), Integer.parseInt(pFecha.substring(8, 10)), Integer.parseInt(pFecha
						.substring(10, 12)), Integer.parseInt(pFecha.substring(12, 14))).getTime();
	}

	/**
	 * Convierte un String de la forma "aaaa/mm/dd/ hh:MM:ss" a un Date
	 */
	public static Date stringAAAAMMDDHHMMSSToDateSlash(String pFecha) {
		return new GregorianCalendar(Integer.parseInt(pFecha.substring(0, 4)),
				Integer.parseInt(pFecha.substring(5, 7)) - 1, Integer.parseInt(pFecha.substring(8,
						10)), Integer.parseInt(pFecha.substring(11, 13)), Integer.parseInt(pFecha
						.substring(14, 16)), Integer.parseInt(pFecha.substring(17, 19))).getTime();
	}	
	
	/**
	 * Formatea una fecha String AAAAMMDDHHMMSS para ser visualizada
	 */
	public static String formatAAAAMMDDHHMMSS(String pFecha) {
		return (pFecha.substring(6, 8) + "/" + pFecha.substring(4, 6) + "/"
				+ pFecha.substring(0, 4) + "  " + pFecha.substring(8, 10) + ":"
				+ pFecha.substring(10, 12) + ":" + pFecha.substring(12, 14));
	}

	/**
	 * Convierte un string en un date
	 */
	public static Date stringToDate(String pFecha) throws ParseException {
		Date dDate;
		DateFormat dateFormatter;
		Locale argLocale;

		argLocale = new Locale("es", "AR");
		dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, argLocale);

		dDate = dateFormatter.parse(pFecha);
		return dDate;
	}

	/**
	 * Metodo que devuelve la cantidad de dias que hay entre un rango
	 * de fechas.
	 * @author Hernan Gabriel Zapata
	 * @param pFechaDesde Date
	 * @param pFechaHasta Date
	 * @return int
	 */
	public static int getDiasEnRangoFecha(Date pFechaDesde, Date pFechaHasta) {
		// Obtengo la cantidad de milisegundos de las fechas
		// del rango.
		long lMsecDesde = pFechaDesde.getTime();
		long lMsecHasta = pFechaHasta.getTime();

		// Saco la diferencia de tiempo 
		long lDiff = (lMsecHasta - lMsecDesde);

		// 86.400.000 Es la cantidad de milisegundos que 
		// hay por dia, por lo tanto al dividirlo obtengo
		// la cantidad de dias.
		double nDias = (double) (lDiff / 86400000.0);

		return (int) (nDias + 0.3); // Para salvar el Gap (ver Javadoc de la clase Date) 
	}

	/**
	 * Metodo que devuelve próxima fecha hábil
	 * @author Natalia Spañol
	 * @param pFecha Date
	 * @return Date
	 */

	/*
	 public static Date proximaFechaHabil(Date pFecha)
	 throws ErrorInesperadoException {
	 Date dProximoHabil;
	 GregorianCalendar gregorianCalendar;
	 int diaSemana;
	 
	 try {
	 gregorianCalendar = new GregorianCalendar();
	 gregorianCalendar.setTime(pFecha);
	 
	 // Valida que no este en tabla de feriado
	 while (!esDiaHabil(pFecha)) {
	 gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
	 pFecha = gregorianCalendar.getTime();
	 }
	 
	 dProximoHabil = gregorianCalendar.getTime();
	 } catch (ErrorInesperadoException ei) {
	 throw ei;
	 }
	 
	 dProximoHabil = gregorianCalendar.getTime();
	 
	 return dProximoHabil;
	 }
	 
	 public static boolean esDiaHabil(Date pFecha)
	 throws ErrorInesperadoException {
	 GregorianCalendar gregorianCalendar;
	 int diaSemana;
	 boolean esHabil;
	 
	 try {
	 gregorianCalendar = new GregorianCalendar();
	 gregorianCalendar.setTime(pFecha);
	 diaSemana = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);
	 esHabil = false;
	 
	 // No existe en tabla feriados y no es sabado ni domingo
	 esHabil = (!(ParametrosSistemaDAO.existeEnTablaFeriado(pFecha)) &&
	 (diaSemana != GregorianCalendar.SATURDAY) &&
	 (diaSemana != GregorianCalendar.SUNDAY));
	 } catch (ErrorInesperadoException ei) {
	 throw ei;
	 }
	 
	 return esHabil;
	 }
	 */

	/**
	 * Convierte una fecha en una cadena de tipo ddmmaaaa
	 */
	public static String dateToStringDDMMAAAA(Date pFecha) {
		String strDate;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

		strDate = sdf.format(pFecha);

		return strDate;
	}

	/**
	 * Convierte un String de la forma dd/mm/aaaa a un java.sql.Date	 
	 * Vic
	 */
	public static java.sql.Date stringDDMMAAAAToDate(String pFecha) {
		return new java.sql.Date((new GregorianCalendar(Integer.parseInt(pFecha.substring(6, 10)),
				Integer.parseInt(pFecha.substring(3, 5)) - 1, Integer.parseInt(pFecha.substring(0,
						2))).getTime()).getTime());
	}

	/**
	 * Convierte un String de la forma dd/mm/aaaa a un java.util.Date	 
	 */
	public static java.util.Date stringDDMMAAAAToUtilDate(String pFecha) {
		return new java.util.Date((new GregorianCalendar(Integer.parseInt(pFecha.substring(6, 10)),
				Integer.parseInt(pFecha.substring(3, 5)) - 1, Integer.parseInt(pFecha.substring(0,
						2))).getTime()).getTime());
	}	
	
	/**
	 * formatea la fecha para timeStamp yyyy-mm-dd hh:mi:ss
	 */
	public static String formatFecha(String pFecha) {
		String f = pFecha.substring(6, 10) + "-" + pFecha.substring(3, 5) + "-"
				+ pFecha.substring(0, 2) + " " + pFecha.substring(11, 19);
		return f;
	}

	/**
	 * Fecha de hoy con formato DD/MM/AAAA separada por Slash ("/") 
	 */
	public static String getFechaHoyDDMMAAAASlash(){
		
		Timestamp ts = new Timestamp(new Date().getTime());		
		return getFechaDDMMAAAASlash(dateToStringDDMMAAAA(ts));
	}
	
	public static String getFechaHoyDDMMAAAAhhmmssSlash(){
		
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   Date date = new Date();
	   return dateFormat.format(date);
	}  
	
	/*public static String getFechaHoyDDMMAAAASlash() {
		Calendar calendar = new GregorianCalendar(new Locale("es", "AR"));
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		Texto t = new Texto();
		return t.lpad(new Integer(calendar.get(Calendar.DATE)).toString(), 2, "0") + "/"
				+ t.lpad(new Integer(calendar.get(Calendar.MONTH) + 1).toString(), 2, "0") + "/"
				+ calendar.get(Calendar.YEAR);
	}*/

	/**
	 * Formatea la Fecha pasada como parametro (DDMMAAA) a DD/MM/AAAA separada por Slash ("/") 
	 */
	public static String getFechaDDMMAAAASlash(String pFecha) {

		String dia = "";
		String mes = "";
		String anio = "";
		dia = pFecha.substring(0, 2);
		mes = pFecha.substring(2, 4);
		anio = pFecha.substring(4, 8);

		return dia + "/" + mes + "/" + anio;
	}

	/**
	 * Validate whether the argument string can be parsed into a
	 * legal date.<br />
	 *
	 * Does check for formating errors and illegal data (so an invalid
	 * month or day number is detected).
	 * @param allowPast set to true to allow dates in the past, false if
	 * only dates in the future should be allowed.
	 * @param formatStr date format string to be used to validate against
	 * @return true if a correct date and conforms to the restrictions
	 */

	public static boolean validateDate(String dateStr, boolean allowPast, String formatStr) {

		if (formatStr == null) {

			return false; // or throw some kinda exception, possibly a InvalidArgumentExceptionVoIP Services and Information			 
		}

		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		Date testDate = null;

		try {
			df.setLenient(false);
			testDate = df.parse(dateStr);
		} catch (ParseException e) {
			//invalid date format		
			return false;
		}
		if (!allowPast) {

			//initialise the calendar to midnight to prevent	
			//the current day from being rejected

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			if (cal.getTime().after(testDate)) {
				return false;
			}

		}

		//now test for legal values of parameters		
		if (!df.format(testDate).equals(dateStr)) {
			return false;
		}
		return true;
	}

	public static int getAnioActual() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
}
