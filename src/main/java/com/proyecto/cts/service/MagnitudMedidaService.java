package com.proyecto.cts.service;

import com.proyecto.cts.entity.MagnitudMedidaEntity;

import java.util.List;

public interface MagnitudMedidaService {
    // Listar
    List<MagnitudMedidaEntity> list();

    // Insertar
    MagnitudMedidaEntity save(MagnitudMedidaEntity magnitudmedida) throws Exception;

    // Actualizar
    MagnitudMedidaEntity update(MagnitudMedidaEntity magnitudmedida, Long id) throws Exception;

    // Borrar
    MagnitudMedidaEntity deleteById(Long id) throws Exception;

    MagnitudMedidaEntity aMayusculas(MagnitudMedidaEntity magnitudmedida);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
