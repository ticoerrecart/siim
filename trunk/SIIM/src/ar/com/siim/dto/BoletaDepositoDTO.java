package ar.com.siim.dto;

public class BoletaDepositoDTO {

	private long idBoleta;

	private Long numero;

	private String concepto;

	private String area;

	private String efectivoCheque;

	private double monto;

	private String fechaPago;

	private String fechaVencimiento;

	private DeclaracionExtraccionDTO declaracionExtraccion;

	private boolean anulado;

	public BoletaDepositoDTO() {

		declaracionExtraccion = new DeclaracionExtraccionDTO();
	}

	public long getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(long idBoleta) {
		this.idBoleta = idBoleta;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEfectivoCheque() {
		return efectivoCheque;
	}

	public void setEfectivoCheque(String efectivoCheque) {
		this.efectivoCheque = efectivoCheque;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public DeclaracionExtraccionDTO getDeclaracionExtraccion() {
		return declaracionExtraccion;
	}

	public void setDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracionExtraccion) {
		this.declaracionExtraccion = declaracionExtraccion;
	}

	public boolean getAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public boolean esNula() {
		return area == null && concepto == null && efectivoCheque == null
				&& fechaVencimiento == null && monto == 0.0 && numero == null;
	}
}
