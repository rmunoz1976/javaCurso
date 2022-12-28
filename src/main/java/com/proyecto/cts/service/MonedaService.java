package com.proyecto.cts.service;

import com.proyecto.cts.entity.MonedaEntity;

import java.util.List;

public interface MonedaService {
    // Listar
    List<MonedaEntity> list();

    // Insertar
    MonedaEntity save(MonedaEntity moneda) throws Exception;

    // Actualizar
    MonedaEntity update(MonedaEntity moneda, Long id) throws Exception;

    // Borrar
    MonedaEntity deleteById(Long id) throws Exception;

    MonedaEntity aMayusculas(MonedaEntity moneda);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
