package ar.com.siim.fachada;

public interface IReportesFachada {

	public byte[] generarReporteVolDeclaradoFiscalizado(
			String path, String periodo, Long idProductor, Long idLocalizacion) throws Exception;
	
	public byte[] generarReporteEstadoDeDeudaGeneralPorProductor(
			String path, String periodo, Long idProductor) throws Exception;

	public byte[] generarReporteDeclaracionExtraccion(String path, Long idDeclaracion) throws Exception;
	
	public byte[] generarReporteCanonMinero(String path, Long idCanon) throws Exception;
}
