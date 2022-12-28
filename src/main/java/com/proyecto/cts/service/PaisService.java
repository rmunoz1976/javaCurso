package com.proyecto.cts.service;

import com.proyecto.cts.entity.PaisEntity;

import java.util.List;

public interface PaisService {
    // Listar
    List<PaisEntity> list();

    // Insertar
    PaisEntity save(PaisEntity pais) throws Exception;

    // Actualizar
    PaisEntity update(PaisEntity pais, Long id) throws Exception;

    // Borrar
    PaisEntity deleteById(Long id) throws Exception;

    PaisEntity aMayusculas(PaisEntity pais);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
