package ar.com.siim.negocio.exception;

public class AccesoDenegadoException extends Exception {

	public AccesoDenegadoException(){}
	
	public AccesoDenegadoException(String mensaje){
		super(mensaje);
	}
}
