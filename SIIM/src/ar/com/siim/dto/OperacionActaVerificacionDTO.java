package ar.com.siim.dto;

public class OperacionActaVerificacionDTO extends OperacionDTO {

	private ActaDeVerificacionDTO actaVerificacion;

	public ActaDeVerificacionDTO getActaVerificacion() {
		return actaVerificacion;
	}

	public void setActaVerificacion(ActaDeVerificacionDTO actaVerificacion) {
		this.actaVerificacion = actaVerificacion;
	}	
}
