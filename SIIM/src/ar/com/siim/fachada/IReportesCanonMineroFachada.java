package ar.com.siim.fachada;

public interface IReportesCanonMineroFachada {

	public byte[] generarReporteCanonMineroEstadoDeudaPorProductorLocalizacionYPeriodo(
					String path,String periodo,Long idProductor, Long idLocalizacion) throws Exception;	
}
