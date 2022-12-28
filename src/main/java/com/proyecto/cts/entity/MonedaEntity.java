package com.proyecto.cts.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "t130t_monedas")
public class MonedaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    @NotEmpty(message = "El código no debe estar vacío.")
    private String codigo;

    @Column(name = "descripcion", nullable = false)
    @NotEmpty(message = "La descripción no debe estar vacía.")
    private String descripcion;

    @Column(name = "simbolo", nullable = false)
    @NotEmpty(message = "El símbolo no debe estar vacío.")
    private String simbolo;

    @Column(name = "id_estado_registro")
    private Long idEstadoRegistro;

    @Column(name = "id_usuario_creacion")
    private Long idUsuarioCreacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "id_usuario_modificacion")
    private Long idUsuarioModificacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}


