package ar.com.siim.fachada;

public interface IReportesDeclaracionExtraccionFachada {

	public byte[] generarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo(
			String path,String periodo,Long idProductor,Long idLocalizacion) throws Exception;	
	
}
