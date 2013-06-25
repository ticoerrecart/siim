package ar.com.siim.negocio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
public class ActaDeVerificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private long numero;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "productor_fk")
	private Entidad productor;

	private Date fechaVerificacion;
	private Date fecha;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "oficinaMinera_fk")
	private Localidad oficinaMinera;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "yacimiento_fk")
	private Localizacion yacimiento;

	
	private String areaDeVerificacion;

	@Column(nullable = false)
	private String agenteVerificacion;

	private String areaFiscalizadora;

	private String funcionarioActuante;

	private String observaciones;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "transporte_fk")
	private Transporte transporte;

	@Column(nullable = false)
	private int numeroDeRemito;

	private int numeroDeFactura;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "destino_fk")
	private LocalidadDestino destino;

	private String domicilioDestinatario;

	private int granelVolumenM3Declarado;

	private int granelVolumenM3Medido;

	private String granelObservaciones;

	private int bolsaCantidad;

	private int bolsaVolumenD3;

	private String bolsaTitularMembrete;

	private String bolsaObservaciones;

	public ActaDeVerificacion() {
		transporte = new Transporte();
		destino = new LocalidadDestino();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public Entidad getProductor() {
		return productor;
	}

	public void setProductor(Entidad productor) {
		this.productor = productor;
	}

	public Date getFechaVerificacion() {
		return fechaVerificacion;
	}

	public void setFechaVerificacion(Date fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Localidad getOficinaMinera() {
		return oficinaMinera;
	}

	public void setOficinaMinera(Localidad oficinaMinera) {
		this.oficinaMinera = oficinaMinera;
	}

	public Localizacion getYacimiento() {
		return yacimiento;
	}

	public void setYacimiento(Localizacion yacimiento) {
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

	public Transporte getTransporte() {
		return transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	public int getNumeroDeRemito() {
		return numeroDeRemito;
	}

	public void setNumeroDeRemito(int numeroDeRemito) {
		this.numeroDeRemito = numeroDeRemito;
	}

	public int getNumeroDeFactura() {
		return numeroDeFactura;
	}

	public void setNumeroDeFactura(int numeroDeFactura) {
		this.numeroDeFactura = numeroDeFactura;
	}

	public LocalidadDestino getDestino() {
		return destino;
	}

	public void setDestino(LocalidadDestino destino) {
		this.destino = destino;
	}

	public String getDomicilioDestinatario() {
		return domicilioDestinatario;
	}

	public void setDomicilioDestinatario(String domicilioDestinatario) {
		this.domicilioDestinatario = domicilioDestinatario;
	}

	public int getGranelVolumenM3Declarado() {
		return granelVolumenM3Declarado;
	}

	public void setGranelVolumenM3Declarado(int granelVolumenM3Declarado) {
		this.granelVolumenM3Declarado = granelVolumenM3Declarado;
	}

	public int getGranelVolumenM3Medido() {
		return granelVolumenM3Medido;
	}

	public void setGranelVolumenM3Medido(int granelVolumenM3Medido) {
		this.granelVolumenM3Medido = granelVolumenM3Medido;
	}

	public String getGranelObservaciones() {
		return granelObservaciones;
	}

	public void setGranelObservaciones(String granelObservaciones) {
		this.granelObservaciones = granelObservaciones;
	}

	public int getBolsaCantidad() {
		return bolsaCantidad;
	}

	public void setBolsaCantidad(int bolsaCantidad) {
		this.bolsaCantidad = bolsaCantidad;
	}

	public int getBolsaVolumenD3() {
		return bolsaVolumenD3;
	}

	public void setBolsaVolumenD3(int bolsaVolumenD3) {
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
