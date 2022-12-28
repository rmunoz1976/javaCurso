package com.proyecto.cts.zgeneral;

public enum EnumDias {

    LUNES(1, "Lunes", "Monday"),
    MARTES(2, "Martes", "Tuesday"),
    MIERCOLES(3, "Miércoles", "Wednesday"),
    JUEVES(4, "Jueves", "Thursday"),
    VIERNES(5, "Viernes", "Friday"),
    SABADO(6, "Sábado", "Saturday"),
    DOMINGO(7, "Domingo", "Sunday");

    private final int diaNumero;
    private final String nombreEsp;
    private final String nombreIng;

    private EnumDias(int diaNumero, String nombreEsp, String nombreIng) {
        this.diaNumero = diaNumero;
        this.nombreEsp = nombreEsp;
        this.nombreIng = nombreIng;
    }

    public int getDiaNumero() {
        return diaNumero;
    }

    public String getNombreEsp() {
        return nombreEsp;
    }

    public String getNombreIng() {
        return nombreIng;
    }
}
