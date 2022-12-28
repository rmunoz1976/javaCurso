package com.proyecto.cts.service;

import com.proyecto.cts.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    // Listar
    List<UsuarioEntity> list();

    // Insertar
    UsuarioEntity save(UsuarioEntity usuario) throws Exception;

    // Actualizar
    UsuarioEntity update(UsuarioEntity usuario, Long id) throws Exception;

    // Borrar
    UsuarioEntity deleteById(Long id) throws Exception;

    UsuarioEntity aMayusculas(UsuarioEntity usuario);

    Long findByLoginContar(String login);

    Long findByValidarDuplicados(String nombreCompleto, String login, String password, String email);

    Long searchById(Long id);

}
