package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.LocalidadDestinoDTO;
import ar.com.siim.dto.ProvinciaDestinoDTO;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.LocalidadDestino;
import ar.com.siim.negocio.exception.NegocioException;

public interface ILocalidadFachada {

	public List<Localidad> getLocalidades();

	public Localidad getLocalidadPorId(Long id);
	
	public LocalidadDestino getLocalidadDestinoPorId(Long id);

	public boolean existeLocalidad(LocalidadDTO localidad);
	
	public void altaLocalidad(LocalidadDTO localidadDTO) throws NegocioException;
	
	public List<LocalidadDTO> getLocalidadesDTO();
	
	public LocalidadDTO getLocalidadDTOPorId(Long id);
	
	public void modificacionLocalidad(LocalidadDTO localidadDTO) throws NegocioException;
	
	public void altaProvincia(ProvinciaDestinoDTO provinciaDTO) throws NegocioException;	
	
	public boolean existeProvincia(ProvinciaDestinoDTO provincia);
	
	public List<ProvinciaDestinoDTO> getProvinciasDTO();
	
	public ProvinciaDestinoDTO getProvinciaDestinoDTOPorId(Long id);	
	
	public void modificacionProvinciaDestino(ProvinciaDestinoDTO provinciaDTO) throws NegocioException;	
	
	public void altaLocalidadDestino(LocalidadDestinoDTO localidadDTO) throws NegocioException;	
	
	public boolean existeLocalidadDestino(LocalidadDestinoDTO localidad);
	
	public List<LocalidadDestinoDTO> getLocalidadesDetinoDTODeProvincia(Long idProvincia)throws NegocioException;	
	
	public LocalidadDestinoDTO getLocalidadDestinoDTOPorId(Long id);
	
	public void modificacionLocalidadDestino(LocalidadDestinoDTO localidadDTO) throws NegocioException;	
}
