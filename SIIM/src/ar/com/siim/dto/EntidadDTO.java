package ar.com.siim.dto;

public class EntidadDTO {

	private Long id;

	private String nombre;

	private long idLocalidad;

	private String tipoEntidad;
	
	private String tipoEntidadDesc;
	
	private String direccion;

	private String telefono;

	private String email;

	private Long nroMatricula;

	private String cuit;

	private Integer codigoPostal;
	
	private LocalidadDTO localidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public String getTipoEntidadDesc() {
		return tipoEntidadDesc;
	}

	public void setTipoEntidadDesc(String tipoEntidadDesc) {
		this.tipoEntidadDesc = tipoEntidadDesc;
	}

	public Long getNroMatricula() {
		return nroMatricula;
	}

	public void setNroMatricula(Long nroMatricula) {
		this.nroMatricula = nroMatricula;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
}
