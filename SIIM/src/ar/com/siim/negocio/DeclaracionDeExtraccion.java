package ar.com.siim.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siim.enums.TipoOperacion;

@Entity
public class DeclaracionDeExtraccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long numero;

	private String periodo;

	private String fecha;

	private double importeTotal;

	private boolean anulado;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "productor_fk")
	private Entidad entidad;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localizacion_fk")
	private Localizacion localizacion;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localidad_fk")
	private Localidad localidad;

	@OneToMany(mappedBy = "declaracionDeExtraccion")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<VolumenDeclaracionDeExtraccion> volumenes = new ArrayList<VolumenDeclaracionDeExtraccion>();

	@OneToMany(mappedBy = "declaracion")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<OperacionDeclaracionExtraccion> operaciones;	
	
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public List<VolumenDeclaracionDeExtraccion> getVolumenes() {
		return volumenes;
	}

	public void addVolumenDeclaracionDeExtraccion(
			VolumenDeclaracionDeExtraccion volumen) {
		this.volumenes.add(volumen);
	}

	public List<OperacionDeclaracionExtraccion> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<OperacionDeclaracionExtraccion> operaciones) {
		this.operaciones = operaciones;
	}

	public OperacionDeclaracionExtraccion getOperacionAlta() {
		for (OperacionDeclaracionExtraccion operacion : this.getOperaciones()) {
			if (operacion.getTipoOperacion().equals(TipoOperacion.ALTA.getDescripcion())){
				return operacion;
			}
		}
		return null;
	}

	public List<OperacionDeclaracionExtraccion> getOperacionesModificacion() {
		List<OperacionDeclaracionExtraccion> operacionesModificacion = new ArrayList<OperacionDeclaracionExtraccion>();
		for (OperacionDeclaracionExtraccion operacion : this.getOperaciones()) {
			if (operacion.getTipoOperacion().equals(TipoOperacion.MOD.getDescripcion())){
				operacionesModificacion.add(operacion);
			}
		}
		return operacionesModificacion;
	}

	public void addOperacion(OperacionDeclaracionExtraccion operacionActaVerificacion) {
		if (this.operaciones == null) {
			this.operaciones = new ArrayList<OperacionDeclaracionExtraccion>();
		}
		this.operaciones.add(operacionActaVerificacion);
	}
	public TrimestreDeclaracionDeExtraccion getTrimestre(Long nroTrimestre){
		List<VolumenDeclaracionDeExtraccion> voluemenes = this.getVolumenes();
		for (VolumenDeclaracionDeExtraccion volumen : voluemenes) {
			List<TrimestreDeclaracionDeExtraccion>  trimestres = volumen.getTrimestres();
			for (TrimestreDeclaracionDeExtraccion trimestre : trimestres) {
				if (nroTrimestre.intValue() == trimestre.getNroTrimestre().intValue()){
					return trimestre;
				}
			}
		}
		return null;
	}
}
