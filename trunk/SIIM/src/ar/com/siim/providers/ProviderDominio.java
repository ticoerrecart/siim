package ar.com.siim.providers;

import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.negocio.Localidad;

public abstract class ProviderDominio {

	public static Localidad getLocalidad(LocalidadDTO localidadDTO){
		
		Localidad localidad = new Localidad();		
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}

	public static Localidad getLocalidad(Localidad localidad, LocalidadDTO localidadDTO){
		
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}	
	
	public static Periodo getPeriodo(PeriodoDTO periodoDTO){
		
		Periodo periodo = new Periodo();		
		periodo.setPeriodo(periodoDTO.getPeriodo());
		return periodo;
	}

	public static Periodo getPeriodo(Periodo periodo, PeriodoDTO periodoDTO){
		periodo.setPeriodo(periodoDTO.getPeriodo());
		return periodo;
	}	
}
