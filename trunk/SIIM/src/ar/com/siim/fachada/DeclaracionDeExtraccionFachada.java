package ar.com.siim.fachada;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import ar.com.siim.dao.DeclaracionDeExtraccionDAO;
import ar.com.siim.dao.EntidadDAO;
import ar.com.siim.dao.LocalidadDAO;
import ar.com.siim.dao.LocalizacionDAO;
import ar.com.siim.dao.TipoProductoDAO;
import ar.com.siim.dao.UsuarioDAO;
import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.DeclaracionExtraccionDTO;
import ar.com.siim.dto.OperacionDeclaracionExtraccionDTO;
import ar.com.siim.dto.TrimestreDeclaracionDeExtraccionDTO;
import ar.com.siim.negocio.BoletaDeposito;
import ar.com.siim.negocio.DeclaracionDeExtraccion;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.TipoProducto;
import ar.com.siim.negocio.TrimestreDeclaracionDeExtraccion;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDominio;
import ar.com.siim.utils.Constantes;
import ar.com.siim.utils.Fecha;
import ar.com.siim.utils.MyLogger;

public class DeclaracionDeExtraccionFachada implements
		IDeclaracionDeExtraccionFachada {

	private DeclaracionDeExtraccionDAO declaracionDeExtraccionDAO;

	private EntidadDAO entidadDAO;

	private LocalidadDAO localidadDAO;

	private LocalizacionDAO localizacionDAO;

	private TipoProductoDAO tipoProductoDAO;

	private UsuarioDAO usuarioDAO;

	public DeclaracionDeExtraccionFachada() {
	}

	public DeclaracionDeExtraccionFachada(
			DeclaracionDeExtraccionDAO declaracionDeExtraccionDAO,
			EntidadDAO entidadDAO, LocalidadDAO localidadDAO,
			LocalizacionDAO localizacionDAO, TipoProductoDAO tipoProductoDAO,
			UsuarioDAO usuarioDAO) {

		this.declaracionDeExtraccionDAO = declaracionDeExtraccionDAO;
		this.entidadDAO = entidadDAO;
		this.localidadDAO = localidadDAO;
		this.localizacionDAO = localizacionDAO;
		this.tipoProductoDAO = tipoProductoDAO;
		this.usuarioDAO = usuarioDAO;
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

		OperacionDeclaracionExtraccionDTO operacionDTO = declaracionExtraccionDTO
				.getOperacionAlta();
		Usuario usuario = usuarioDAO.getUsuario(operacionDTO.getUsuario()
				.getId());

		DeclaracionDeExtraccion declaracionDeExtraccion = ProviderDominio
				.getDeclaracionDeExtraccion(declaracionExtraccionDTO,
						trimestresDTO, boletasDTO, entidad, localidad,
						localizacion, tipoProducto, usuario);

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

	public String registrarPagoBoletaDeposito(Long idBoleta, String fechaPago)
			throws NegocioException {
		try {
			return declaracionDeExtraccionDAO.registrarPagoBoletaDeposito(
					idBoleta, fechaPago);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void modificacionDeclaracionDeExtraccion(
			DeclaracionExtraccionDTO declaracionDTO,
			List<BoletaDepositoDTO> boletasDepositoDTO,
			List<TrimestreDeclaracionDeExtraccionDTO> trimestres)
			throws NegocioException {
		String existe = existeDeclaracionExtraccion(declaracionDTO);
		if (!StringUtils.hasText(existe)) {

			DeclaracionDeExtraccion declaracionDeExtraccion = declaracionDeExtraccionDAO
					.getDeclaracionDeExtraccionById(declaracionDTO.getId());
			Localidad localidad = localidadDAO.getLocalidadPorId(declaracionDTO
					.getLocalidad().getId());

			TipoProducto tipoProducto = tipoProductoDAO
					.recuperarTipoProducto(1L);

			declaracionDeExtraccion.setLocalidad(localidad);
			declaracionDeExtraccion.setNumero(declaracionDTO.getNumero());
			declaracionDeExtraccion.setFecha(declaracionDTO.getFecha());

			/* actualizo boletas */
			List<BoletaDeposito> boletasABorrar = new ArrayList<BoletaDeposito>();
			List<BoletaDeposito> boletas = declaracionDeExtraccion.getBoletas();
			for (BoletaDeposito boleta : boletas) {
				for (BoletaDepositoDTO boletaDTO : boletasDepositoDTO) {

					if (boleta.getId().longValue() == boletaDTO.getIdBoleta()) {
						if (boletaDTO.getAnulado()) {
							// borro
							boletasABorrar.add(boleta);
							boleta.setCanonMinero(null);
							boleta.setDeclaracionDeExtraccion(null);
						} else {
							// actualizo los datos
							boleta.setNumero(boletaDTO.getNumero());
							boleta.setConcepto(boletaDTO.getConcepto());
							boleta.setArea(boletaDTO.getArea());
							boleta.setEfectivoCheque(boletaDTO
									.getEfectivoCheque());
							boleta.setFechaVencimiento(Fecha
									.stringDDMMAAAAToUtilDate(boletaDTO
											.getFechaVencimiento()));
							boleta.setMonto(boletaDTO.getMonto());

							if (boletaDTO.getFechaPago() != null
									&& boletaDTO.getFechaPago() != "") {
								boleta.setFechaPago(Fecha
										.stringDDMMAAAAToUtilDate(boletaDTO
												.getFechaPago()));
							}
						}
					}
				}
			}

			boletas.removeAll(boletasABorrar);

			/* agrego boletas nuevas */
			for (BoletaDepositoDTO boletaDTO : boletasDepositoDTO) {
				if (boletaDTO.getIdBoleta() == 0 && !boletaDTO.esNula()
						&& !boletaDTO.getAnulado()) {
					boletas.add(ProviderDominio.getBoletaDeposito(
							declaracionDeExtraccion, boletaDTO));
				}
			}

			// Trimestres.

			for (TrimestreDeclaracionDeExtraccionDTO trimestreDTO : trimestres) {
				boolean encontreTrimestre = false;
				if (!trimestreDTO.esNulo()) {
					for (TrimestreDeclaracionDeExtraccion trimestre : declaracionDeExtraccion
							.getTrimestres()) {
						encontreTrimestre = trimestreDTO.getNroTrimestre()
								.intValue() == trimestre.getNroTrimestre()
								.intValue();
						if (encontreTrimestre) {
							break;
						}
					}

					if (!encontreTrimestre) {
						TrimestreDeclaracionDeExtraccion trimestreNuevo = ProviderDominio
								.getTrimestreDeclaracionDeExtraccion(
										trimestreDTO, tipoProducto,
										declaracionDeExtraccion);
						declaracionDeExtraccion.addTrimestre(trimestreNuevo);
					}
				}
			}
			declaracionDeExtraccionDAO.modificacionDeclaracionExtraccion(
					declaracionDeExtraccion, boletasABorrar);
		} else {
			throw new NegocioException(
					"Ya existe una Declaración de Extracción con éste número.");
		}

	}
}
