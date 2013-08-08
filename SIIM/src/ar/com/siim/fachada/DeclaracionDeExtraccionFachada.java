package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dao.DeclaracionDeExtraccionDAO;
import ar.com.siim.dao.EntidadDAO;
import ar.com.siim.dao.LocalidadDAO;
import ar.com.siim.dao.LocalizacionDAO;
import ar.com.siim.dao.TipoProductoDAO;
import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.DeclaracionExtraccionDTO;
import ar.com.siim.dto.TrimestreDeclaracionDeExtraccionDTO;
import ar.com.siim.negocio.DeclaracionDeExtraccion;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.TipoProducto;
import ar.com.siim.providers.ProviderDominio;
import ar.com.siim.utils.Constantes;

public class DeclaracionDeExtraccionFachada implements
		IDeclaracionDeExtraccionFachada {

	private DeclaracionDeExtraccionDAO declaracionDeExtraccionDAO;

	private EntidadDAO entidadDAO;

	private LocalidadDAO localidadDAO;

	private LocalizacionDAO localizacionDAO;

	private TipoProductoDAO tipoProductoDAO;

	public DeclaracionDeExtraccionFachada() {
	}

	public DeclaracionDeExtraccionFachada(
			DeclaracionDeExtraccionDAO declaracionDeExtraccionDAO,
			EntidadDAO entidadDAO, LocalidadDAO localidadDAO,
			LocalizacionDAO localizacionDAO, TipoProductoDAO tipoProductoDAO) {
		this.declaracionDeExtraccionDAO = declaracionDeExtraccionDAO;
		this.entidadDAO = entidadDAO;
		this.localidadDAO = localidadDAO;
		this.localizacionDAO = localizacionDAO;
		this.tipoProductoDAO = tipoProductoDAO;
	}

	public String existeDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracion) {
		return declaracionDeExtraccionDAO
				.existeDeclaracionExtraccion(declaracion);
	}

	public void altaDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracionExtraccionDTO,
			List<TrimestreDeclaracionDeExtraccionDTO> trimestresDTO,
			List<BoletaDepositoDTO> boletasDTO) {
		Entidad entidad = entidadDAO.getEntidad(declaracionExtraccionDTO
				.getProductor().getId());
		Localidad localidad = localidadDAO
				.getLocalidadPorId(declaracionExtraccionDTO.getLocalidad()
						.getId());
		Localizacion localizacion = localizacionDAO
				.getLocalizacionPorId(declaracionExtraccionDTO
						.getLocalizacion().getId());
		TipoProducto tipoProducto = tipoProductoDAO
				.recuperarTipoProducto(Constantes.TURBA);
		DeclaracionDeExtraccion declaracionDeExtraccion = ProviderDominio
				.getDeclaracionDeExtraccion(declaracionExtraccionDTO,
						trimestresDTO, boletasDTO, entidad, localidad,
						localizacion, tipoProducto);

		declaracionDeExtraccionDAO
				.altaDeclaracionExtraccion(declaracionDeExtraccion);
	}

	public DeclaracionDeExtraccion getDeclaracionDeExtraccionById(Long id) {
		return declaracionDeExtraccionDAO.getDeclaracionDeExtraccionById(id);
	}

	public DeclaracionDeExtraccion getDeclaracionDeExtraccion(Long idEntidad,
			Long idLocalizacion, String idPeriodo, boolean sinAnuladas) {
		return declaracionDeExtraccionDAO.getDeclaracionDeExtraccion(idEntidad,
				idLocalizacion, idPeriodo, sinAnuladas);
	}
}
