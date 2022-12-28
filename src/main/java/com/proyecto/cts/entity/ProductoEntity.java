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
@Table(name = "t518m_productos")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    @NotEmpty(message = "El código no debe estar vacío.")
    private String codigo;

    @Column(name = "nombre_corto", nullable = false)
    @NotEmpty(message = "El nombre corto no debe estar vacio.")
    private String nombreCorto;

    @Column(name = "nombre_largo", nullable = false)
    @NotEmpty(message = "El nombre corto no debe estar vacio.")
    private String nombreLargo;

    @Column(name = "ruta_imagen")
    private String rutaImagen;

    @Column(name = "nombre_imagen")
    private String nombreImagen;

    @Column(name = "id_subgrupo_producto")
    private Long idSubgrupoProducto;

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


