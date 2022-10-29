package com.asitec.util;

import java.math.BigDecimal;

public class Constantes {

	/* DATOS GENERALES */
	public static final String DIRECTORIO_UPLOAD 	= "uploads";
	public static final String MENSAJE 				= "mensaje";
	public static final String ERROR 				= "error";
	public static final String ERRORES 				= "errores";
	public static final String CLIENTE 				= "cliente";
	public static final String RETURN_RESULT_SET_PREFIX = "#result-set-1"; 
	public static final String ASIGNAR_DERIVAR = "ASIGNAR_DERIVAR"; 
	public static final String ASIGNAR = "ASIGNAR"; 
	public static final String DERIVAR = "DERIVAR"; 
	public static final String DEVOLVER = "DEVOLVER"; 
	public static final String REGISTRAR = "REGISTRAR"; 
	public static final String LISTAR = "LISTAR"; 
	public static final String LISTAR_ARCHIVO_UPP = "LISTARUPP"; 
	public static final String ELIMINAR = "ELIMINAR"; 
	public static final String OBTENER_RUTA = "OBTENER_RUTA"; 
	public static final String ENVIAR_RECHAZO_AL_ENCARGADO = "ENVIAR_RECHAZO_AL_ENCARGADO";
	public static final String ACEPTAR = "ACEPTAR";
	
	public static final String ALERTA = "ALERTA"; 
	public static final String TIPO_DOCUMENTO = "TIPO_DOCUMENTO"; 
	public static final String MODALIDAD = "MODALIDAD"; 
	public static final String CID_CODIGO_UNIDAD = "011";
	public static final String TIPO_EJECUCION = "TIPO_EJECUCION"; 
	public static final String ESTADO = "ESTADO"; 
	public static final String ARQUITECTO = "210"; 
	public static final String CIVIL = "299"; 
	public static final String ELECTRICO = "273"; 
	public static final String SANITARIO = "356"; 
	public static final String OTROS = "00"; 
	
	public static final String LISTAR_OBSERVACION = "LISTAR_OBSERVACION"; 
	public static final String GUARDAR_OBSERVACION = "GUARDAR_OBSERVACION"; 
	
	public static final String VER_DETALLE = "VER_DETALLE"; 
	public static final String CANTIDAD_PENDIENTE = "CANTIDAD_PENDIENTE"; 
	public static final String DAR_CONFORMIDAD = "DAR_CONFORMIDAD"; 
	public static final String ASIGNAR_EQUIPO_ELABORADOR = "ASIGNAR_EQUIPO_ELABORADOR"; 
	public static final String ASIGNAR_EQUIPO_EVALUADOR = "ASIGNAR_EQUIPO_EVALUADOR"; 
	public static final String OBSERVADO = "OBSERVADO";  
	public static final String LISTAR_ELABORADOR = "LISTAR_ELABORADOR";  
	public static final String LISTAR_REVISOR = "LISTAR_REVISOR";  
	public static final String REGISTRAR_AUSENCIA = "REGISTRAR_AUSENCIA";  
	public static final String RETORNO_AUSENCIA = "RETORNO_AUSENCIA";
	public static final String VERIFICAR_AUSENCIA = "VERIFICAR_AUSENCIA";
	public static final String RECHAZAR_JEFE= "RECHAZAR_JEFE"; 
	public static final String RECHAZAR_COORDINADOR = "RECHAZO_APROBACION_EQUIPOS"; 
	public static final String APROBAR_JEFE = "APROBAR_JEFE"; 
	public static final String APROBACION = "APROBACION"; 
	public static final String RECHAZADO = "RECHAZADO";
	public static final String DERIVAR_PARA_APROBACION = "DERIVAR_PARA_APROBACION"; 
	public static final String TEMPORAL_PRESUPUESTO = "TEMPORAL_PRESUPUESTO";
	public static final String TEMPORAL_INFO_S10 = "TEMPORAL_INFO_S10";
	public static final String TEMPORAL_PRESUPUESTO_ITEM = "TEMPORAL_PRESUPUESTO_ITEM";
	
	public static final String TEMPORAL_PARTIDA = "TEMPORAL_PARTIDA";
	public static final String TEMPORAL_PARTIDA_ITEM = "TEMPORAL_PARTIDA_ITEM";
	public static final String TEMPORAL_GASTO_GENERAL_ITEM = "TEMPORAL_GASTO_GENERAL_ITEM";
	public static final String TEMPORAL_GASTO_SUPERVISION_ITEM = "TEMPORAL_GASTO_SUPERVISION_ITEM";
	
	public static final String EXTENSION_FORMATO_PDF = ".pdf";
	public static final String EXTENSION_FORMATO_XLS = ".xls";
	public static final String EXTENSION_FORMATO_XLSX = ".xlsx";
	public static final String EXTENSION_FORMATO_DOC = ".doc";
	public static final String EXTENSION_FORMATO_DOCX = ".docx";
	public static final String EXTENSION_XLS = "XSL";
	public static final String EXTENSION_XLSX = "XLSX";
	public static final String EMPTY = "";
	public static final short ZERO_SHORT = 0;
	public static final int ZERO_INT = 0;
	public static final BigDecimal ZERO_BIG_DECIMAL = BigDecimal.valueOf(0);
	public static final long ZERO_LONG = 0;
	public static final String ZERO_STRING = "0";
	
	/* CODIGOS PARA MANEJO DE CADENAS */
	public static final String CADENA_VACIO = "";
	public static final String COD_CADENA_CORTADA = "...(*)";
	public static final String PUNTO = ".";
	public static final String DOS_PUNTOS = ":";
	public static final String SEPARADOR = "-";
	public static final String DIVISOR = "/";
	public static final String PORCENTAJE = "%";
	public static final String EXPRESION_OR = "||";
	public static final String EXPRESION_AND = "&&";
	public static final String UNDERLINE = "_";
	public static final String PIPELINE = "|";
	public static final String SALTO_LINEA = "\r\n";
	public static final String SALTO_LINEA_PARRAFO = "\n";
	public static final String IMAGEN_NO_DISPONIBLE	= "no-disponible.png";
	
	/* CODIGOS PARA MESES */
	public static final short ENERO = 1;
	public static final short FEBRERO = 2;
	public static final short MARZO = 3;
	public static final short ABRIL = 4;
	public static final short MAYO = 5;
	public static final short JUNIO = 6;
	public static final short JULIO = 7;
	public static final short AGOSTO = 8;
	public static final short SEPTIEMBRE = 9;
	public static final short OCTUBRE = 10;
	public static final short NOVIEMBRE = 11;
	public static final short DICIEMBRE = 12;
	/* MEDIA TYPE */
	public static final String MIME_APPLICATION_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	public static final String MIME_APPLICATION_DOC = "application/msword";
	public static final String MIME_APPLICATION_PDF = "application/pdf";
	public static final String MIME_APPLICATION_XLS = "application/vnd.ms-excel";
	public static final String MIME_APPLICATION_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	public static final String MIME_APPLICATION_TXT = "text/plain";
	public static final String MIME_IMAGE_JPG = "image/jpeg";
	public static final String MIME_IMAGE_PNG = "image/png";
	public static final String MIME_IMAGE_GIF = "image/gif";
	/* CODIGO DE ERRORES */
    public static final String COD_VALIDACION_GENERAL = "02";
	public static final String COD_EXITO_GENERAL = "01";
	public static final String COD_ERROR_GENERAL = "00";
	
	/* DEFINICION DE LA RUTA DEL REPOSITROIO DE ARCHIVO */
	public static final String RUTA_ARCHIVO = "D:\\prefactibilidad\\";
	public static final String ETIQUETA_LINUX = "LINUX";
	
	
	public static final String TRANSACCION_INSTITUCION_EDICATIVA = "paInstitucionEducativaInsertar";
	public static final String TRANSACCION_TDR = "transaccionTdr";
	public static final String ID_OPCION = "ID_OPCION";
	public static final String FID_FORMATO = "FID_FORMATO";
	public static final String FID_UNIDAD_ELABORA = "FID_UNIDAD_ELABORA";
	public static final String FID_UNIDAD_REVISA = "FID_UNIDAD_REVISA";
	public static final String FID_UNIDAD_APRUEBA = "FID_UNIDAD_APRUEBA";
	public static final String FID_UNIDAD_ORGANICA = "FID_UNIDAD_ORGANICA";
	public static final String DENOMINACION = "DENOMINACION";
	public static final String FINALIDAD_PUBLICA = "FINALIDAD_PUBLICA";
	public static final String ANTECEDENTE= "ANTECEDENTE";
	public static final String ID_USUARIO=  "ID_USUARIO";
	public static final String OBJETIVO= "OBJETIVO";
	public static final String LUGAR_PRESTACION= "LUGAR_PRESTACION";
	public static final String PLAZO_EJECUCION= "PLAZO_EJECUCION";
	public static final String ALCANCE=    "ALCANCE";
	public static final String FID_TDR=    "FID_TDR";
	public static final String NOMBRE_ENTREGABLE=    "NOMBRE_ENTREGABLE";
	public static final String PLAZO_ENTREGABLE=    "PLAZO_ENTREGABLE";
	public static final String DESCRIPCION_ENTREGABLE=    "DESCRIPCION_ENTREGABLE";
	public static final String FID_CONDICION_GENERAL=   "FID_CONDICION_GENERAL";
	public static final String FID_PERFIL_CONTRATACION=   "FID_PERFIL_CONTRATACION";
	public static final String DESCRIPCION_CONDICION_PARTICULAR=  "DESCRIPCION_CONDICION_PARTICULAR";
	public static final String FID_ENTREGABLE=   "FID_ENTREGABLE";
	public static final String FID_MODALIDAD=   "FID_MODALIDAD";
	public static final String DESCRIPCION_FORMA_PAGO=    "DESCRIPCION_FORMA_PAGO";
	public static final String CONFORMIDAD_PRESTACION=    "CONFORMIDAD_PRESTACION";
	public static final String CONFIDENCIALIDAD=    "CONFIDENCIALIDAD";
	public static final String PENALIDAD=    "PENALIDAD";
	public static final String PROPIEDAD_INTELECTUAL=    "PROPIEDAD_INTELECTUAL";
	public static final String RESPONSABILIDAD_VICIO_OCULTO=    "RESPONSABILIDAD_VICIO_OCULTO";
	public static final String OTRAS_CONDICIONES=    "OTRAS_CONDICIONES";
	public static final String FID_ANTICORRUPCION=   "FID_ANTICORRUPCION";
	public static final String PAGINA=   "PAGINA";
	public static final String CANTIDAD=   "CANTIDAD";
	public static final String COD_DOCUMENTO=    "COD_DOCUMENTO";
	public static final String FEC_INICIO=    "FEC_INICIO";
	public static final String FEC_FIN=    "FEC_FIN";
	public static final String ID_ALCANCE=   "ID_ALCANCE";
	public static final String ID_DETALLE_ENTREGABLE=   "ID_DETALLE_ENTREGABLE";
	public static final String ID_CODIGO=   "ID_CODIGO";
	public static final String NOMBRE_ANTICORRUPCION=    "NOMBRE_ANTICORRUPCION";
	public static final String CONTENIDO_ANTICORRUPCION=    "CONTENIDO_ANTICORRUPCION";
	public static final String DESCRIPCION=    "DESCRIPCION";
	public static final String FID_PROYECTO= "FID_PROYECTO";
	
	public static final String ID_PLATAFORMA= "ID_PLATAFORMA";
	public static final String ID_PUESTO= "ID_PUESTO";
	public static final String NOMBRE= "NOMBRE";
	public static final String ID_CONVERSACION= "ID_CONVERSACION";
	public static final String TIPO_CHAT= "TIPO_CHAT";
	public static final String ID_CONTACTO= "ID_CONTACTO";
	public static final String NOMBRE_CHAT= "NOMBRE_CHAT";
	public static final String COLOR= "COLOR";
	public static final String CID_SOCKET= "CID_SOCKET";
	public static final String CID_IP_CLIENTE= "CID_IP_CLIENTE";
	
	
	public static final String ID_PROYECTO= "ID_PROYECTO";
	public static final String ID_PADRE= "ID_PADRE";
	public static final String NIVEL= "NIVEL";
	
	
	public static final String COD_RESULTADO= "pResultado";
	public static final String MSG_RESULTADO = "pMensaje";
	public static final String TOTAL = "TOTAL";
	
	public static final String ERROR_EN_LA_BD = "Error en la BD";
	public static final String NO_EXISTEN_REGISTROS = "¡No existen registros!";
	public static final String ERROR_EN_LA_BASE_DE_DATOS = "Error en la Base de datos!.";
}
