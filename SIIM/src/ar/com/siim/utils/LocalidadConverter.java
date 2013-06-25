package ar.com.siim.utils;

import org.apache.commons.beanutils.converters.AbstractConverter;

import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.negocio.Localidad;

public class LocalidadConverter extends AbstractConverter {

	@Override
	protected Object convertToType(Class type, Object value) throws Throwable {
		// TODO Auto-generated method stub
		LocalidadDTO localidadDTO = (LocalidadDTO)value;
		Localidad l = new Localidad();
		l.setId(localidadDTO.getId());
		return l;
	}

	@Override
	protected Class getDefaultType() {
		return Localidad.class;
	}

}
