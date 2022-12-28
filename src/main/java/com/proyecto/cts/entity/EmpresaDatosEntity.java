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
@Table(name = "t225e_empresas_datos")
public class EmpresaDatosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_persona_juridica", nullable = false)
    @NotEmpty(message = "El nombre de persona juridica no debe estar vacío.")
    private String nombrePersonaJuridica;

    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "id_usuario_creacion")
    private Long idUsuarioCreacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "id_usuario_modificacion")
    private Long idUsuarioModificacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}


