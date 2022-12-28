package com.proyecto.cts.service;

import com.proyecto.cts.entity.RolEntity;

import java.util.List;

public interface RolService {
    // Listar
    List<RolEntity> list();

    // Insertar
    RolEntity save(RolEntity rol) throws Exception;

    // Actualizar
    RolEntity update(RolEntity rol, Long id) throws Exception;

    // Borrar
    RolEntity deleteById(Long id) throws Exception;

    RolEntity aMayusculas(RolEntity rol);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);

    Long searchById(Long id);    
}
