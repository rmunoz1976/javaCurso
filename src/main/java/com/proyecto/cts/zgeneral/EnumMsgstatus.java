package com.proyecto.cts.zgeneral;

public enum EnumMsgstatus {
    ERR0001("ERR0001", "ERR0001 - Los datos se guardaron correctamente."),
    ERR0002("ERR0002", "ERR0002 - No se guardaron los datos."),
    ERR1001("ERR1001", "ERR1001 - El código ya existe. Por favor intente de nuevo."),
    ERR1002("ERR1002", "ERR1002 - La descripción ya existe. Por favor intente de nuevo."),
    ERR1003("ERR1003", "ERR1003 - El nombre de usuario ya existe. Por favor intente de nuevo."),

    ERR1004("ERR1004", "ERR1004 - El nombre corto ya existe. Por favor intente de nuevo."),
    ERR1005("ERR1005", "ERR1005 - El nombre largo ya existe. Por favor intente de nuevo."),

    ERR1006("ERR1006", "ERR1006 - El nombre de la persona jurídica ya existe. Por favor intente de nuevo."),

    ERR1501("ERR1501", "ERR1501 - El código no existe. Por favor intente de nuevo."),
    ERR1701("ERR1701", "ERR1701 - El registro se eliminó correctamente."),
    ERR1998("ERR1998", "ERR1998 - El id enviado no existe en la tabla."),
    ERR1999("ERR1999", "ERR1999 - Existen datos duplicados."),

    ERR7900("ERR7900", "ERR7900 - No se pudo ejecutar la sentecia SQL."),
    ERR7901("ERR7901", "ERR7901 - No se puede agregar o actualizar: Error en restricción de clave foranea. Tabla:"),
    ERR7902("ERR7902", "ERR7902 - Se excede la longitud del dato para la columna:"),
    ERR7903("ERR7903", "ERR7903 - No se puede eliminar o actualizar: Error en restricción de clave foranea. Tabla");
    private final String errorNumero;
    private final String errorDescripcion;
    private EnumMsgstatus(String errorNumero, String errorDescripcion) {
        this.errorNumero = errorNumero;
        this.errorDescripcion = errorDescripcion;
    }

    public String getErrorNumero() {
        return errorNumero;
    }

    public String getErrorDescripcion() {
        return errorDescripcion;
    }

}
