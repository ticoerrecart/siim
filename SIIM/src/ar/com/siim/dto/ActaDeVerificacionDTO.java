package ar.com.siim.dto;

public class ActaDeVerificacionDTO {

	private Long id;

	private Long numero;

	private EntidadDTO productor;

	private String fecha;
	
	private String fechaVerificacion;

	private LocalidadDTO oficinaMinera;

	private LocalizacionDTO yacimiento;

	private String areaDeVerificacion;

	private String agenteVerificacion;

	private String areaFiscalizadora;

	private String funcionarioActuante;

	private String observaciones;

	private TransporteDTO transporte;

	private Integer numeroDeRemito;

	private Integer numeroDeFactura;

	private LocalidadDestinoDTO destino;

	private String domicilioDestinatario;

	private Integer granelVolumenM3Declarado;

	private Integer granelVolumenM3Medido;

	private String granelObservaciones;

	private Integer bolsaCantidad;

	private Integer bolsaVolumenD3;

	private String bolsaTitularMembrete;

	private String bolsaObservaciones;

	public ActaDeVerificacionDTO() {

		productor = new EntidadDTO();
		yacimiento = new LocalizacionDTO();
		oficinaMinera = new LocalidadDTO();
		transporte = new TransporteDTO();
		destino = new LocalidadDestinoDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public EntidadDTO getProductor() {
		return productor;
	}

	public void setProductor(EntidadDTO productor) {
		this.productor = productor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaVerificacion() {
		return fechaVerificacion;
	}

	public void setFechaVerificacion(String fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
	}

	public LocalidadDTO getOficinaMinera() {
		return oficinaMinera;
	}

	public void setOficinaMinera(LocalidadDTO oficinaMinera) {
		this.oficinaMinera = oficinaMinera;
	}

	public LocalizacionDTO getYacimiento() {
		return yacimiento;
	}

	public void setYacimiento(LocalizacionDTO yacimiento) {
		this.yacimiento = yacimiento;
	}

	public String getAreaDeVerificacion() {
		return areaDeVerificacion;
	}

	public void setAreaDeVerificacion(String areaDeVerificacion) {
		this.areaDeVerificacion = areaDeVerificacion;
	}

	public String getAgenteVerificacion() {
		return agenteVerificacion;
	}

	public void setAgenteVerificacion(String agenteVerificacion) {
		this.agenteVerificacion = agenteVerificacion;
	}

	public String getAreaFiscalizadora() {
		return areaFiscalizadora;
	}

	public void setAreaFiscalizadora(String areaFiscalizadora) {
		this.areaFiscalizadora = areaFiscalizadora;
	}

	public String getFuncionarioActuante() {
		return funcionarioActuante;
	}

	public void setFuncionarioActuante(String funcionarioActuante) {
		this.funcionarioActuante = funcionarioActuante;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public TransporteDTO getTransporte() {
		return transporte;
	}

	public void setTransporte(TransporteDTO transporte) {
		this.transporte = transporte;
	}

	public Integer getNumeroDeRemito() {
		return numeroDeRemito;
	}

	public void setNumeroDeRemito(Integer numeroDeRemito) {
		this.numeroDeRemito = numeroDeRemito;
	}

	public Integer getNumeroDeFactura() {
		return numeroDeFactura;
	}

	public void setNumeroDeFactura(Integer numeroDeFactura) {
		this.numeroDeFactura = numeroDeFactura;
	}

	public LocalidadDestinoDTO getDestino() {
		return destino;
	}

	public void setDestino(LocalidadDestinoDTO destino) {
		this.destino = destino;
	}

	public String getDomicilioDestinatario() {
		return domicilioDestinatario;
	}

	public void setDomicilioDestinatario(String domicilioDestinatario) {
		this.domicilioDestinatario = domicilioDestinatario;
	}

	public Integer getGranelVolumenM3Declarado() {
		return granelVolumenM3Declarado;
	}

	public void setGranelVolumenM3Declarado(Integer granelVolumenM3Declarado) {
		this.granelVolumenM3Declarado = granelVolumenM3Declarado;
	}

	public Integer getGranelVolumenM3Medido() {
		return granelVolumenM3Medido;
	}

	public void setGranelVolumenM3Medido(Integer granelVolumenM3Medido) {
		this.granelVolumenM3Medido = granelVolumenM3Medido;
	}

	public String getGranelObservaciones() {
		return granelObservaciones;
	}

	public void setGranelObservaciones(String granelObservaciones) {
		this.granelObservaciones = granelObservaciones;
	}

	public Integer getBolsaCantidad() {
		return bolsaCantidad;
	}

	public void setBolsaCantidad(Integer bolsaCantidad) {
		this.bolsaCantidad = bolsaCantidad;
	}

	public Integer getBolsaVolumenD3() {
		return bolsaVolumenD3;
	}

	public void setBolsaVolumenD3(Integer bolsaVolumenD3) {
		this.bolsaVolumenD3 = bolsaVolumenD3;
	}

	public String getBolsaTitularMembrete() {
		return bolsaTitularMembrete;
	}

	public void setBolsaTitularMembrete(String bolsaTitularMembrete) {
		this.bolsaTitularMembrete = bolsaTitularMembrete;
	}

	public String getBolsaObservaciones() {
		return bolsaObservaciones;
	}

	public void setBolsaObservaciones(String bolsaObservaciones) {
		this.bolsaObservaciones = bolsaObservaciones;
	}
	
}
