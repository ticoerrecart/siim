package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.enums.TipoDeEntidad;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.exception.NegocioException;

public interface IEntidadFachada {

	public void altaEntidad(EntidadDTO entidadDTO) throws NegocioException;

	public List<Entidad> getEntidades();

	public Entidad getEntidad(Long id);

	public boolean existeEntidad(String nombre, Long id);

	public List<Entidad> getEntidadesPorLocalidad(Long idLocalidad) throws NegocioException;

	public List<TipoDeEntidad> getTiposDeEntidad();

	public List<TipoDeEntidad> getTiposDeEntidadProductores();

	public List<Entidad> getEntidadesPorTipoDeEntidad(String tipoDeEntidad);

	public List<Entidad> getOficinas();

	public List<EntidadDTO> getOficinasDTO();

	public List<EntidadDTO> getEntidadesPorTipoDeEntidadDTO(String tipoDeEntidad)throws NegocioException;

	public List<EntidadDTO> getEntidadesDTO();

	public EntidadDTO getEntidadDTO(Long id) throws NegocioException;

	public void modificacionEntidad(EntidadDTO entidad);

	public List<EntidadDTO> getProductoresDTO();

}
