package ar.com.siim.utils;

public abstract class MathUtils {
	public static double round(double value, int cantidadDecimales) {
		double x = Math.pow(10, cantidadDecimales);
		return (double) Math.round(value * x) / x;
	}
}
