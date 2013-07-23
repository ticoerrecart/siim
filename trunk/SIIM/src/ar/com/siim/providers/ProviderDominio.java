package ar.com.siim.providers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import ar.com.siim.dto.ActaDeVerificacionDTO;
import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.CanonMineroDTO;
import ar.com.siim.dto.DeclaracionExtraccionDTO;
import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.dto.EstudioImpactoAmbientalDTO;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.dto.LocalidadDestinoDTO;
import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.dto.ProvinciaDestinoDTO;
import ar.com.siim.dto.RolDTO;
import ar.com.siim.dto.TransporteDTO;
import ar.com.siim.dto.TrimestreDeclaracionDeExtraccionDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.enums.TipoDeEntidad;
import ar.com.siim.negocio.ActaDeVerificacion;
import ar.com.siim.negocio.BoletaDeposito;
import ar.com.siim.negocio.CanonMinero;
import ar.com.siim.negocio.DeclaracionDeExtraccion;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.EstudioImpactoAmbiental;
import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.LocalidadDestino;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.negocio.Productor;
import ar.com.siim.negocio.ProvinciaDestino;
import ar.com.siim.negocio.RecursosNaturales;
import ar.com.siim.negocio.Rol;
import ar.com.siim.negocio.TipoProducto;
import ar.com.siim.negocio.Transporte;
import ar.com.siim.negocio.TrimestreDeclaracionDeExtraccion;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.VolumenDeclaracionDeExtraccion;
import ar.com.siim.utils.Fecha;

public abstract class ProviderDominio {

	public static Localidad getLocalidad(LocalidadDTO localidadDTO) {

		Localidad localidad = new Localidad();
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}

	public static Localidad getLocalidad(Localidad localidad,
			LocalidadDTO localidadDTO) {

		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}

	public static Periodo getPeriodo(PeriodoDTO periodoDTO) {

		Periodo periodo = new Periodo();
		periodo.setPeriodo(periodoDTO.getPeriodo());

		periodo.setFechaVencimientoPrimerTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoPrimerTrimestre()));
		periodo.setFechaVencimientoSegundoTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoSegundoTrimestre()));
		periodo.setFechaVencimientoTercerTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoTercerTrimestre()));
		periodo.setFechaVencimientoCuartoTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoCuartoTrimestre()));
		return periodo;
	}

	public static Periodo getPeriodo(Periodo periodo, PeriodoDTO periodoDTO) {
		periodo.setPeriodo(periodoDTO.getPeriodo());
		periodo.setFechaVencimientoPrimerTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoPrimerTrimestre()));
		periodo.setFechaVencimientoSegundoTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoSegundoTrimestre()));
		periodo.setFechaVencimientoTercerTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoTercerTrimestre()));
		periodo.setFechaVencimientoCuartoTrimestre(Fecha
				.stringDDMMAAAAToDate(periodoDTO
						.getFechaVencimientoCuartoTrimestre()));

		return periodo;
	}

	public static Entidad getEntidad(EntidadDTO entidadDTO, Localidad localidad) {

		Entidad entidad = null;
		if (TipoDeEntidad.PRD.getName().equalsIgnoreCase(
				entidadDTO.getTipoEntidad())) {
			entidad = new Productor();
		} else {
			entidad = new RecursosNaturales();
		}

		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());
		entidad.setLocalidad(localidad);
		entidad.setNroMatricula(entidadDTO.getNroMatricula());
		entidad.setCuit(entidadDTO.getCuit());
		entidad.setCuil(entidadDTO.getCuil());
		entidad.setDni(entidadDTO.getDni());
		entidad.setTipoDocumento(entidadDTO.getTipoDocumento());
		entidad.setCodigoPostal(entidadDTO.getCodigoPostal());

		return entidad;
	}

	public static Entidad getEntidad(Entidad entidad, EntidadDTO entidadDTO,
			Localidad localidad) {

		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setLocalidad(localidad);
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());
		entidad.setNroMatricula(entidadDTO.getNroMatricula());
		entidad.setCuit(entidadDTO.getCuit());
		entidad.setCuil(entidadDTO.getCuil());
		entidad.setDni(entidadDTO.getDni());
		entidad.setTipoDocumento(entidadDTO.getTipoDocumento());
		entidad.setCodigoPostal(entidadDTO.getCodigoPostal());
		return entidad;
	}

	public static Usuario getUsuario(UsuarioDTO usuarioDTO, Entidad entidad,
			Rol rol) {

		Usuario usuario = new Usuario();
		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}

	public static Usuario getUsuario(Usuario usuario, UsuarioDTO usuarioDTO,
			Entidad entidad, Rol rol) {

		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}

	public static Rol getRol(RolDTO rolDTO) {

		Rol rol = new Rol();
		rol.setRol(rolDTO.getRol());
		rol.setMenues(new ArrayList<ItemMenu>());

		return rol;
	}

	public static Localizacion getLocalizacion(LocalizacionDTO localizacionDTO,
			Entidad productor) {

		Localizacion localizacion = new Localizacion();
		localizacion.setExpediente(localizacionDTO.getExpediente());
		localizacion.setProductor(productor);
		localizacion.setRazonSocial(localizacionDTO.getRazonSocial());
		localizacion.setResolucion(localizacionDTO.getResolucion());
		localizacion.setDomicilio(localizacionDTO.getDomicilio());
		localizacion.setSuperficie(localizacionDTO.getSuperficie());

		List<EstudioImpactoAmbiental> lista = new ArrayList<EstudioImpactoAmbiental>();

		for (EstudioImpactoAmbientalDTO eiaDTO : localizacionDTO.getListaEIA()) {

			lista.add(ProviderDominio.getEstudioImpactoAmbiental(eiaDTO,
					localizacion));
		}
		localizacion.setListaEIA(lista);

		return localizacion;
	}

	public static EstudioImpactoAmbiental getEstudioImpactoAmbiental(
			EstudioImpactoAmbientalDTO eiaDTO, Localizacion localizacion) {

		EstudioImpactoAmbiental eia = new EstudioImpactoAmbiental();

		eia.setEstado(eiaDTO.getEstado());
		if (eiaDTO.getFechaDesde() != "") {
			eia.setFechaDesde(Fecha.stringDDMMAAAAToUtilDate(eiaDTO
					.getFechaDesde()));
		}
		if (eiaDTO.getFechaHasta() != "") {
			eia.setFechaHasta(Fecha.stringDDMMAAAAToUtilDate(eiaDTO
					.getFechaHasta()));
		}
		eia.setLocalizacion(localizacion);
		eia.setNroResolucionEIA(eiaDTO.getNroResolucionEIA());
		eia.setObservaciones(eiaDTO.getObservaciones());
		eia.setVigente(eiaDTO.isVigente());

		return eia;
	}

	public static ProvinciaDestino getProvincia(ProvinciaDestinoDTO provinciaDTO) {

		ProvinciaDestino provincia = new ProvinciaDestino();
		provincia.setNombre(provinciaDTO.getNombre());
		return provincia;
	}

	public static ProvinciaDestino getProvincia(ProvinciaDestino provincia,
			ProvinciaDestinoDTO provinciaDTO) {

		provincia.setNombre(provinciaDTO.getNombre());
		return provincia;
	}

	public static LocalidadDestino getLocalidadDestino(
			LocalidadDestinoDTO localidadDTO, ProvinciaDestino provincia) {

		LocalidadDestino localidad = new LocalidadDestino();
		localidad.setNombre(localidadDTO.getNombre());
		localidad.setProvinciaDestino(provincia);

		return localidad;
	}

	public static LocalidadDestino getLocalidadDestino(
			LocalidadDestinoDTO localidadDTO, LocalidadDestino localidad,
			ProvinciaDestino provincia) {

		localidad.setNombre(localidadDTO.getNombre());
		localidad.setProvinciaDestino(provincia);

		return localidad;
	}

	public static ActaDeVerificacion getActa(ActaDeVerificacionDTO actaDTO,
			LocalidadDestino destino, Localidad oficinaMinera,
			Entidad productor, Localizacion yacimiento) {
		ActaDeVerificacion acta = new ActaDeVerificacion();

		acta.setAgenteVerificacion(actaDTO.getAgenteVerificacion());
		acta.setAreaDeVerificacion(actaDTO.getAreaDeVerificacion());
		acta.setAreaFiscalizadora(actaDTO.getAreaFiscalizadora());
		acta.setBolsaCantidad(actaDTO.getBolsaCantidad());
		acta.setBolsaObservaciones(actaDTO.getBolsaObservaciones());
		acta.setBolsaTitularMembrete(actaDTO.getBolsaTitularMembrete());
		acta.setBolsaVolumenD3(actaDTO.getBolsaVolumenD3());

		acta.setDomicilioDestinatario(actaDTO.getDomicilioDestinatario());
		if (actaDTO.getFecha() != "") {
			acta.setFecha(Fecha.stringDDMMAAAAToUtilDate(actaDTO.getFecha()));
		}
		if (actaDTO.getFechaVerificacion() != "") {
			acta.setFechaVerificacion(Fecha.stringDDMMAAAAToUtilDate(actaDTO
					.getFechaVerificacion()));
		}
		acta.setFuncionarioActuante(actaDTO.getFuncionarioActuante());
		acta.setGranelObservaciones(actaDTO.getGranelObservaciones());
		acta.setGranelVolumenM3Declarado(actaDTO.getGranelVolumenM3Declarado());
		acta.setGranelVolumenM3Medido(actaDTO.getGranelVolumenM3Medido());
		acta.setNumero(actaDTO.getNumero());
		acta.setNumeroDeFactura(actaDTO.getNumeroDeFactura());
		acta.setNumeroDeRemito(actaDTO.getNumeroDeRemito());
		acta.setObservaciones(actaDTO.getObservaciones());

		acta.setTransporte(ProviderDominio.getTransporte(actaDTO
				.getTransporte()));

		acta.setYacimiento(yacimiento);
		acta.setDestino(destino);
		acta.setOficinaMinera(oficinaMinera);
		acta.setProductor(productor);
		return acta;
	}

	private static Transporte getTransporte(TransporteDTO transporteDTO) {
		Transporte transporte = new Transporte();
		try {
			BeanUtils.populate(transporte, BeanUtils.describe(transporteDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transporte;
	}

	public static CanonMinero getCanonMinero(CanonMineroDTO canonMineroDTO,
			List<BoletaDepositoDTO> boletasDepositoDTO, Localidad localidad,
			Localizacion localizacion, Entidad entidad) {
		CanonMinero canonMinero = new CanonMinero();
		canonMinero.setCanonMineroXPertenencia(canonMineroDTO
				.getCanonXPertenencia());
		canonMinero.setCantHaXPertenenciaMinera(canonMineroDTO
				.getCantHaXPertenenciaMinera());
		canonMinero.setCantPertenenciasMineras(canonMineroDTO
				.getCantPertenenciasMineras());
		canonMinero.setHectareas(canonMineroDTO.getHectareas());
		canonMinero.setFecha(Fecha.stringDDMMAAAAToUtilDate(canonMineroDTO
				.getFecha()));
		canonMinero.setMontoTotal(canonMineroDTO.getMontoTotal());
		canonMinero.setPeriodo(canonMineroDTO.getPeriodo());

		canonMinero.setLocalidad(localidad);
		canonMinero.setLocalizacion(localizacion);
		canonMinero.setEntidad(entidad);

		List<BoletaDeposito> boletas = new ArrayList<BoletaDeposito>();
		for (BoletaDepositoDTO boletaDepositoDTO : boletasDepositoDTO) {
			BoletaDeposito boletaDeposito = new BoletaDeposito();
			// boletaDeposito.setAnulado(boletaDepositoDTO.get);
			boletaDeposito.setCanonMinero(canonMinero);
			boletaDeposito.setArea(boletaDepositoDTO.getArea());
			boletaDeposito.setConcepto(boletaDepositoDTO.getConcepto());
			boletaDeposito.setEfectivoCheque(boletaDepositoDTO
					.getEfectivoCheque());

			if (boletaDepositoDTO.getFechaPago() != null) {
				boletaDeposito.setFechaPago(Fecha
						.stringDDMMAAAAToUtilDate(boletaDepositoDTO
								.getFechaPago()));// boletaDeposito.setFechaPago();
			}

			boletaDeposito.setFechaVencimiento(Fecha
					.stringDDMMAAAAToUtilDate(boletaDepositoDTO
							.getFechaVencimiento()));// boletaDeposito.setFechaVencimiento();
			boletaDeposito.setMonto(boletaDepositoDTO.getMonto());
			boletaDeposito.setNumero(boletaDepositoDTO.getNumero());

			boletas.add(boletaDeposito);
		}
		canonMinero.setBoletasDeposito(boletas);

		return canonMinero;
	}

	public static BoletaDeposito getBoletaDeposito(BoletaDepositoDTO boletaDTO,
			VolumenDeclaracionDeExtraccion volumen) {
		BoletaDeposito boleta = new BoletaDeposito();
		boleta.setVolumenDeclaracionDeExtraccion(volumen);
		boleta.setAnulado(boletaDTO.getAnulado());
		boleta.setArea(boletaDTO.getArea());
		// boleta.setCanonMinero(canonMinero);
		boleta.setConcepto(boletaDTO.getConcepto());
		boleta.setEfectivoCheque(boletaDTO.getEfectivoCheque());
		if (boletaDTO.getFechaPago() != null) {
			boleta.setFechaPago(Fecha.stringDDMMAAAAToUtilDate(boletaDTO
					.getFechaPago()));
		}

		boleta.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(boletaDTO
				.getFechaVencimiento()));
		boleta.setMonto(boletaDTO.getMonto());
		boleta.setNumero(boletaDTO.getNumero());
		return boleta;
	}

	public static TrimestreDeclaracionDeExtraccion getTrimestreDeclaracionDeExtraccion(
			TrimestreDeclaracionDeExtraccionDTO trimestreDTO,
			TipoProducto tipoProducto, VolumenDeclaracionDeExtraccion volumen) {
		TrimestreDeclaracionDeExtraccion trimestre = new TrimestreDeclaracionDeExtraccion();
		trimestre.setVolumenDeclaracionDeExtraccion(volumen);
		if (trimestreDTO.getFechaVencimiento() != null) {
			trimestre.setFechaVencimiento(Fecha
					.stringDDMMAAAAToUtilDate(trimestreDTO
							.getFechaVencimiento()));
		}

		trimestre.setNroTrimestre(trimestreDTO.getNroTrimestre());
		trimestre.setTipoProducto(tipoProducto);
		trimestre.setVolumenPrimerMes(trimestreDTO.getVolumenPrimerMes());
		trimestre.setVolumenSegundoMes(trimestreDTO.getVolumenSegundoMes());
		trimestre.setVolumenTercerMes(trimestreDTO.getVolumenTercerMes());
		return trimestre;
	}

	public static DeclaracionDeExtraccion getDeclaracionDeExtraccion(
			DeclaracionExtraccionDTO declaracionExtraccionDTO,
			List<TrimestreDeclaracionDeExtraccionDTO> trimestresDTO,
			List<BoletaDepositoDTO> boletasDTO, Entidad entidad,
			Localidad localidad, Localizacion localizacion,
			TipoProducto tipoProducto) {

		DeclaracionDeExtraccion declaracionDeExtraccion = new DeclaracionDeExtraccion();
		VolumenDeclaracionDeExtraccion volumen = new VolumenDeclaracionDeExtraccion();
		volumen.setDeclaracionDeExtraccion(declaracionDeExtraccion);
		volumen.setFecha(Fecha.getFechaHoy());
		declaracionDeExtraccion.addVolumenDeclaracionDeExtraccion(volumen);

		declaracionDeExtraccion.setAnulado(declaracionExtraccionDTO
				.getAnulado());
		declaracionDeExtraccion.setEntidad(entidad);
		declaracionDeExtraccion.setFecha(declaracionExtraccionDTO.getFecha());
		declaracionDeExtraccion.setImporteTotal(declaracionExtraccionDTO
				.getImporteTotal());
		declaracionDeExtraccion.setLocalidad(localidad);
		declaracionDeExtraccion.setLocalizacion(localizacion);
		declaracionDeExtraccion.setNumero(declaracionExtraccionDTO.getNumero());
		declaracionDeExtraccion.setPeriodo(declaracionExtraccionDTO
				.getPeriodo());

		List<BoletaDeposito> boletas = new ArrayList<BoletaDeposito>();
		for (BoletaDepositoDTO boletaDepositoDTO : boletasDTO) {
			boletas.add(getBoletaDeposito(boletaDepositoDTO, volumen));
		}

		List<TrimestreDeclaracionDeExtraccion> trimestres = new ArrayList<TrimestreDeclaracionDeExtraccion>();
		for (TrimestreDeclaracionDeExtraccionDTO trimestreDTO : trimestresDTO) {
			if (!trimestreDTO.esNulo()) {
				trimestres.add(getTrimestreDeclaracionDeExtraccion(
						trimestreDTO, tipoProducto, volumen));
			}
		}

		volumen.addBoletaDeposito(boletas);
		volumen.addTrimestreDeclaracionDeExtraccion(trimestres);

		return declaracionDeExtraccion;
	}
}
