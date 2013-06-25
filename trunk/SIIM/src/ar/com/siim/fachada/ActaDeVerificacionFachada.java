package ar.com.siim.fachada;

import ar.com.siim.dao.ActaDeVerificacionDAO;
import ar.com.siim.dto.ActaDeVerificacionDTO;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.LocalidadDestino;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDominio;

public class ActaDeVerificacionFachada implements IActaDeVerificacionFachada {

	private ActaDeVerificacionDAO actaDAO;

	private LocalidadFachada localidadFachada;
	private LocalizacionFachada localizacionFachada;
	private EntidadFachada entidadFachada;

	public ActaDeVerificacionFachada() {
	}

	public ActaDeVerificacionFachada(ActaDeVerificacionDAO actaDeVerificacionDAO, LocalidadFachada localidadFachada,
			LocalizacionFachada localizacionFachada, EntidadFachada entidadFachada) {
		this.actaDAO = actaDeVerificacionDAO;
		this.localidadFachada = localidadFachada;
		this.localizacionFachada = localizacionFachada;
		this.entidadFachada = entidadFachada;

	}

	public void altaActaDeVerificacion(ActaDeVerificacionDTO actaDTO) throws NegocioException {

		Localidad oficinaMinera = localidadFachada.getLocalidadPorId(actaDTO.getOficinaMinera().getId());
		Entidad productor = entidadFachada.getEntidad(actaDTO.getProductor().getId());
		Localizacion yacimiento = localizacionFachada.getLocalizacion(actaDTO.getYacimiento().getId());
		LocalidadDestino destino = localidadFachada.getLocalidadDestinoPorId(actaDTO.getDestino().getId());
		actaDAO.altaActaDeVerificacion(ProviderDominio.getActa(actaDTO, destino, oficinaMinera, productor, yacimiento));
	}

	@Override
	public boolean existeActaDeVerificacion(long numero) {
		return this.actaDAO.existeActaDeVerificacion(numero);
		
	}

}
