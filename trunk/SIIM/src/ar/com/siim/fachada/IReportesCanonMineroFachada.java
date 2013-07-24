package ar.com.siim.fachada;

public interface IReportesCanonMineroFachada {

	public byte[] generarReporteCanonMineroEstadoDeudaPorProductorYPeriodo(
								String path,String periodo,Long idProductor) throws Exception;	
}
