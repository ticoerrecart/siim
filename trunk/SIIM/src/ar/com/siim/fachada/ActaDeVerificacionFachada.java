package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.negocio.Usuario;
import ar.com.siim.dao.ActaDeVerificacionDAO;
import ar.com.siim.dto.ActaDeVerificacionDTO;
import ar.com.siim.dto.OperacionActaVerificacionDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.ActaDeVerificacion;
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
	private UsuarioFachada usuarioFachada;
	
	public ActaDeVerificacionFachada() {
	}

	public ActaDeVerificacionFachada(ActaDeVerificacionDAO actaDeVerificacionDAO, LocalidadFachada localidadFachada,
			LocalizacionFachada localizacionFachada, EntidadFachada entidadFachada, UsuarioFachada usuarioFachada) {
		this.actaDAO = actaDeVerificacionDAO;
		this.localidadFachada = localidadFachada;
		this.localizacionFachada = localizacionFachada;
		this.entidadFachada = entidadFachada;
		this.usuarioFachada = usuarioFachada;
	}

	public void altaActaDeVerificacion(ActaDeVerificacionDTO actaDTO) throws NegocioException {

		Localidad oficinaMinera = localidadFachada.getLocalidadPorId(actaDTO.getOficinaMinera().getId());
		Entidad productor = entidadFachada.getEntidad(actaDTO.getProductor().getId());
		Localizacion yacimiento = localizacionFachada.getLocalizacion(actaDTO.getYacimiento().getId());
		LocalidadDestino destino = localidadFachada.getLocalidadDestinoPorId(actaDTO.getDestino().getId());
		OperacionActaVerificacionDTO operacionDTO = actaDTO.getOperacionAlta();
		Usuario usuario = usuarioFachada.getUsuario(operacionDTO.getUsuario().getId());		
		actaDAO.altaActaDeVerificacion(ProviderDominio.getActa(actaDTO, destino, oficinaMinera, 
																productor, yacimiento,usuario));
	}

	@Override
	public boolean existeActaDeVerificacion(long numero) {
		return this.actaDAO.existeActaDeVerificacion(numero);
		
	}

	@Override
	public List<ActaDeVerificacion> getActas(long idLocalizacion, String periodo) {
		return this.actaDAO.getActas(idLocalizacion, periodo);
	}

	@Override
	public ActaDeVerificacion getActa(long idActa) {
		return (ActaDeVerificacion) this.actaDAO.getHibernateTemplate().get(ActaDeVerificacion.class, idActa);
	}

}
