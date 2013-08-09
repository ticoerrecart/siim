package ar.com.siim.utils;

public class Constantes {

	public static final String USER_LABEL_SESSION = "usuario";

	public static final Long ID_ROL_ADMINISTRADOR = 2L;

	// USUARIO
	public static final String TITULO_ALTA_USUARIO = "Alta de Usuario";

	public static final String TITULO_MODIFICACION_USUARIO = "Modificación de Usuario";// solo
																						// para
																						// cuando
																						// no
																						// es
																						// Administrador

	public static final String EXISTE_USUARIO = "Ya existe un Usuario con este nombre";

	public static final String USUARIO_INVALIDO = "Usuario y/o Contraseña invalido";

	public static final String EXITO_ALTA_USUARIO = "El Usuario se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_USUARIO = "El Usuario se ha modificado con exito";

	public static final String ERROR_ALTA_USUARIO = "Ha ocurrido un error en el alta del usuario";

	public static final String ERROR_MODIFICACION_USUARIO = "Ha ocurrido un error en la modificación del usuario";

	public static final String ERROR_RECUPERACION_USUARIOS = "Ha ocurrido un error en la recuperación de los usuarios";

	public static final String ERROR_RECUPERACION_USUARIO = "Ha ocurrido un error en la recuperación del usuario";

	public static final String ERROR_LOGIN_USUARIO = "Ha ocurrido un error en el login del usuario";
	// FIN USUARIO

	// ROL
	public static final String ADMINISTRADOR = "Administrador";

	public static final String EXISTE_ROL = "Ya existe un Rol con ese nombre";

	public static final String EXITO_ALTA_ROL = "El Rol se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_ROL = "El Rol se ha modificado con exito";

	public static final String ERROR_ALTA_ROL = "Ha ocurrido un error en el alta del rol";

	public static final String ERROR_MODIFICACION_ROL = "Ha ocurrido un error en la modificación del rol";

	public static final String ERROR_RECUPERACION_ROLES = "Ha ocurrido un error en la recuperación de los roles";

	public static final String ERROR_RECUPERACION_ROL = "Ha ocurrido un error en la recuperación del rol";

	public static final String ERROR_RECUPERACION_MENUES = "Ha ocurrido un error en la recuperación de los menues";

	public static final String ERROR_RECUPERACION_MENU = "Ha ocurrido un error en la recuperación del menú";
	// FIN ROL

	// LOCALIDAD
	public static final String EXISTE_LOCALIDAD = "Ya existe una Localidad con este nombre";

	public static final String EXISTE_PROVINCIA = "Ya existe una Provincia con éste nombre";

	public static final String EXITO_ALTA_LOCALIDAD = "La Localidad se ha dado de alta con exito";

	public static final String EXITO_ALTA_PROVINCIA = "La Provincia se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_LOCALIDAD = "La Localidad se ha modificado con exito";

	public static final String EXITO_MODIFICACION_PROVINCIA = "La Provincia se ha modificado con exito";

	public static final String ERROR_ALTA_LOCALIDAD = "Ha ocurrido un error en el alta de la localidad";

	public static final String ERROR_RECUPERACION_LOCALIDAD = "Ha ocurrido un error en la recuperación de la localidad";

	public static final String ERROR_RECUPERACION_LOCALIDADES = "Ha ocurrido un error en la recuperación de las localidades";

	public static final String EXISTE_LOCALIDAD_DESTINO = "Ya existe una Localidad con éste nombre para esta Provincia";
	// FIN LOCALIDAD

	// PERIODO
	public static final String EXISTE_PERIODO = "Ya existe un Año de Declaración con éste nombre";

	public static final String EXITO_ALTA_PERIODO = "El período se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_PERIODO = "El período se ha modificado con exito";

	public static final String ERROR_ALTA_PERIODO = "Ha ocurrido un error en el alta del período";

	public static final String ERROR_RECUPERACION_PERIODO = "Ha ocurrido un error en la recuperación del período";

	public static final String ERROR_RECUPERACION_PERIODOS = "Ha ocurrido un error en la recuperación de los períodos";
	// FIN PERIODO

	// ENTIDAD
	public static final String TITULO_ALTA_ENTIDAD = "Alta de Entidad";

	public static final String TITULO_MODIFICACION_ENTIDAD = "Modificación de Entidad";

	public static final String EXITO_MODIFICACION_ENTIDAD = "La Entidad se ha modificado con exito";

	public static final String EXITO_ALTA_ENTIDAD = "La Entidad se ha dado de alta con exito";

	public static final String EXISTE_ENTIDAD = "Ya existe una Entidad con éste nombre";

	public static final String EXISTE_ENTIDAD_CON_MATRICULA = "Ya existe una Entidad con éste nro de matrícula";

	public static final String ENTIDAD_RN = "RN";

	public static final String ERROR_RECUPERAR_OFICINAS_FORESTALES = "Ha ocurrido un error en la recuperación de las oficinas forestales";

	public static final String ERROR_MODIFICACION_ENTIDAD = "Ha ocurrido un error en la modificación de la entidad";

	public static final String ERROR_RECUPERACION_ENTIDAD = "Ha ocurrido un error en la recuperación de la entidad";

	public static final String ERROR_ALTA_ENTIDAD = "Ha ocurrido un error en el alta de la entidad";

	public static final String ERROR_RECUPERAR_PRODUCTORES = "Ha ocurrido un error en la recuperación de los productores";
	// FIN ENTIDAD

	// TIPO DE PRODUCTO
	public static final String EXITO_MODIFICACION_TIPO_PRODUCTO = "El Tipo de Producto se ha modificado con exito";
	// FIN TIPO DE PRODUCTO

	// LOCALIZACION
	public static final String TITULO_ALTA_LOCALIZACION = "Alta de Zona de Extracción Minera";

	public static final String TITULO_MODIFICACION_LOCALIZACION = "Modificación de Zona de Extracción Minera";

	public static final String TITULO_ALTA_EIA = "Alta de Estudio de Impacto Ambiental";

	public static final String EXITO_ALTA_LOCALIZACION = "La Zona de Extracción Minera se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_LOCALIZACION = "La Zona de Extracción Minera se ha modificado con exito";

	public static final String EXITO_ALTA_EIA = "El Estudio de Impacto Ambiental se ha dado de alta con exito";

	public static final String ERROR_EXISTE_LOCALIZACION = "Ya existe una Zona de Extracción Minera con la misma Razón Social para este productor";

	// FIN LOCALIZACION

	// CANON MINERO
	public static final String EXITO_ALTA_CANON_MINERO = "El Canon Minero se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_VALOR_CANON_MINERO = "El Valor del Canon Minero se ha modificado con exito";

	// FIN CANON MINERO

	// REPORTES
	public static final String REPORTE_CANON_MINERO_ESTADO_DEUDA_POR_PRODUCTOR_Y_PERIODO = "reporteCanonMineroEstadoDeDeudaPorProductor";
	
	public static final String REPORTE_ESTADO_EIA = "reporteEstadoEIA";
	
	public static final String REPORTE_ESTADO_DEUDA_DECLARACION_EXTRACCION_POR_PRODUCTOR = "reporteEstadoDeudaDeclaracionExtraccionPorProductor";
	
	public static final String REPORTE_VOLUMEN_DECLARADO_VERIFICADO = "reporteVolumenDeclaradoVerificado";
	
	public static final String REPORTE_ESTADO_DEUDA_GENERAL_POR_PRODUCTOR = "reporteEstadoDeDeudaGeneralPorProductor";
	//FIN REPORTES
	
	public static long TURBA = 1L;
}
