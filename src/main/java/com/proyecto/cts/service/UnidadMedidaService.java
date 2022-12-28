package com.proyecto.cts.service;

import com.proyecto.cts.entity.UnidadMedidaEntity;

import java.util.List;

public interface UnidadMedidaService {
    // Listar
    List<UnidadMedidaEntity> list();

    // Insertar
    UnidadMedidaEntity save(UnidadMedidaEntity unidadmedida) throws Exception;

    // Actualizar
    UnidadMedidaEntity update(UnidadMedidaEntity unidadmedida, Long id) throws Exception;

    // Borrar
    UnidadMedidaEntity deleteById(Long id) throws Exception;

    UnidadMedidaEntity aMayusculas(UnidadMedidaEntity unidadmedida);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
