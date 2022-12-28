package com.proyecto.cts.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "t040t_compania")

public class CompaniaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    @NotEmpty(message = "El código no debe estar vacío.")
    @Pattern(regexp = "^[0-9]*", message = "Solo se permiten digitar números en el código.")
    private String codigo;

    @Column(name = "nit")
    @NotNull(message = "El dígito de verificación no debe estar vacío.")
    @Positive(message = "El dígito de verificación deber tener un valor mayor a 0.")
    private Long nit;

    @Column(name = "dv")
    @NotNull(message = "El dígito de verificación no debe estar vacío.")
    @PositiveOrZero(message = "El dígito de verificación deber tener un valor entre 0 y 9.")
    @Max(value = 9, message = "El dígito de verificación deber tener un valor entre 0 y 9.")
    private Long dv;

    @Column(name = "nombre_corto", nullable = false)
    @NotEmpty(message = "El nombre corto no debe estar vacío.")
    private String nombreCorto;

    @Column(name = "nombre_largo", nullable = false)
    @NotEmpty(message = "El nombre largo no debe estar vacío.")
    private String nombreLargo;

    @Column(name = "sigla", nullable = false)
    @NotEmpty(message = "La sigla no debe estar vacía.")
    private String sigla;

    @Column(name = "fecha")
    private LocalDate fecha;

}
