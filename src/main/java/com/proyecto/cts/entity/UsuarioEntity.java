package com.proyecto.cts.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "t150t_usuarios")

public class UsuarioEntity {

    public UsuarioEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primer_nombre", nullable = false)
    @NotEmpty(message = "El primer nombre no debe estar vacío.")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido", nullable = false)
    @NotEmpty(message = "El primer apellido no debe estar vacío.")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "El nombre de usuario no debe estar vacío.")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){8,18}[a-zA-Z0-9]$",
            message = "El nombre de usuario debe constar de caracteres alfanuméricos minúsculas o mayúsculas (a-zA-Z0-9), se permite tambien los signos de punto (.), guión bajo (_) y guión (-).\n" +
                      "No debe empezar ni terminar con punto (.), guión bajo (_) o guión (-) y no deben aparecer de forma consecutiva, por ejemplo (Mi..usuario ó Mi._usuario).\n" +
                      "Los signos de punto (.), guión bajo (_) o guión (-) no deben aparecer de forma consecutiva, por ejemplo (Mi..usuario ó Mi._usuario).\n" +
                      "El número de caracteres mínimo es de 10.\n" +
                      "El número de caracteres maximo es de 20..")
    private String username;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "La clave no debe estar vacía.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{10,20}$",
            message = "La clave debe contener al menos un dígito [0-9].\n" +
                      "La clave debe contener al menos una letra en minúsculas [a-z].\n" +
                      "La clave debe contener al menos un una letra en mayúsculas [A-Z].\n" +
                      "La clave debe contener al menos un carácter especial como ! @ # & ( ).\n" +
                      "La clave debe contener una longitud de al menos 10 caracteres y un máximo de 20 caracteres.")
    private String password;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "El email no debe estar vacío.")
    @Email(regexp = "^[-_A-Za-z0-9.]+.([-_A-Za-z0-9.])+@[A-Za-z0-9]+(.[A-Za-z]{2,}+)")
    private String email;

    @Column(name = "id_empresa")
    private Long idEmpresa;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "t160t_usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<RolEntity> roles = new HashSet<>();

}

