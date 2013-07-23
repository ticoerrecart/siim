package ar.com.siim.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class VolumenDeclaracionDeExtraccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Date fecha;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "declaracionDeExtraccion_fk")
	private DeclaracionDeExtraccion declaracionDeExtraccion;

	@OneToMany(mappedBy = "volumenDeclaracionDeExtraccion")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<TrimestreDeclaracionDeExtraccion> trimestres = new ArrayList<TrimestreDeclaracionDeExtraccion>();

	@OneToMany(mappedBy = "volumenDeclaracionDeExtraccion")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<BoletaDeposito> boletas = new ArrayList<BoletaDeposito>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<BoletaDeposito> getBoletas() {
		return boletas;
	}

	public List<TrimestreDeclaracionDeExtraccion> getTrimestres() {
		return trimestres;
	}

	public void addTrimestreDeclaracionDeExtraccion(
			TrimestreDeclaracionDeExtraccion trimestre) {
		this.trimestres.add(trimestre);
	}

	public void addTrimestreDeclaracionDeExtraccion(
			List<TrimestreDeclaracionDeExtraccion> trimestres) {
		this.trimestres.addAll(trimestres);
	}

	public void addBoletaDeposito(BoletaDeposito boleta) {
		this.boletas.add(boleta);
	}

	public void addBoletaDeposito(List<BoletaDeposito> boletas) {
		this.boletas.addAll(boletas);
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public DeclaracionDeExtraccion getDeclaracionDeExtraccion() {
		return declaracionDeExtraccion;
	}

	public void setDeclaracionDeExtraccion(
			DeclaracionDeExtraccion declaracionDeExtraccion) {
		this.declaracionDeExtraccion = declaracionDeExtraccion;
	}

}
