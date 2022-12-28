package com.proyecto.cts.entity;

import com.proyecto.cts.zgeneral.ERoles;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "t155t_roles")

public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    @NotEmpty(message = "El código no debe estar vacío.")
    private String codigo;

    @Column(name = "descripcion", nullable = false)
    @NotEmpty(message = "La descripción no debe estar vacía.")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;

}
