package com.proyecto.cts.zgeneral;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GeneralValidation {
    public String fechaActual(Integer tipoFecha) throws ParseException {
        return switch (tipoFecha) {
            case 1 -> LocalDate.now().toString();
            case 2 -> LocalDateTime.now().toString();
            case 3 -> LocalTime.now().toString();
            default -> null;
        };
    }
}
