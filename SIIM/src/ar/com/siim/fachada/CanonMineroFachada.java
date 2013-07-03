package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dao.CanonMineroDAO;
import ar.com.siim.dao.EntidadDAO;
import ar.com.siim.dao.LocalidadDAO;
import ar.com.siim.dao.LocalizacionDAO;
import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.CanonMineroDTO;
import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.negocio.CanonMinero;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDominio;
import ar.com.siim.utils.MyLogger;

public class CanonMineroFachada implements ICanonMineroFachada {

	private CanonMineroDAO canonMineroDAO;
	private LocalidadDAO localidadDAO;
	private LocalizacionDAO localizacionDAO;
	private EntidadDAO entidadDAO;

	public CanonMineroFachada() {
	}

	public CanonMineroFachada(CanonMineroDAO canonDAO) {

		this.canonMineroDAO = canonDAO;
	}

	public CanonMineroFachada(CanonMineroDAO canonDAO,
			LocalidadDAO localidadDAO, LocalizacionDAO localizacionDAO,
			EntidadDAO entidadDAO) {
		this.canonMineroDAO = canonDAO;
		this.localidadDAO = localidadDAO;
		this.localizacionDAO = localizacionDAO;
		this.entidadDAO = entidadDAO;
	}

	public int recuperarCanonMineroXPertenencia() {

		return canonMineroDAO.recuperarCanonMineroXPertenencia();
	}

	public void modificacionValorCanonMinero(double valor) {

		canonMineroDAO.modificacionValorCanonMinero(valor);
	}

	public void altaCanonMinero(CanonMineroDTO canonMineroDTO,
			List<BoletaDepositoDTO> boletasDeposito) {
		Localidad localidad = localidadDAO.getLocalidadPorId(canonMineroDTO
				.getLocalidad().getId());
		Localizacion localizacion = localizacionDAO
				.getLocalizacionPorId(canonMineroDTO.getZonaExtraccion()
						.getId());
		Entidad entidad = entidadDAO.getEntidad(canonMineroDTO.getEntidad()
				.getId());

		canonMineroDAO.altaCanonMinero(ProviderDominio.getCanonMinero(
				canonMineroDTO, boletasDeposito, localidad, localizacion,
				entidad));
	}

	public boolean existeCanonMinero(LocalizacionDTO yacimiento, String periodo) {
		return canonMineroDAO.existeCanonMinero(yacimiento, periodo);
	}

	public CanonMinero getCanonMinero(Long idYacimiento, String periodo) {
		return canonMineroDAO.getCanonMinero(idYacimiento, periodo);
	}

	public CanonMinero getCanonMinero(Long idCanonMinero) {
		return canonMineroDAO.getCanonMinero(idCanonMinero);
	}

	public String registrarPagoBoletaDeposito(Long idBoleta, String fechaPago)
			throws NegocioException {
		try {
			return canonMineroDAO.registrarPagoBoletaDeposito(idBoleta,
					fechaPago);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}
}
