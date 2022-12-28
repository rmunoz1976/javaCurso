package com.proyecto.cts.service;

import com.proyecto.cts.entity.TipoTerceroEntity;

import java.util.List;

public interface TipoTerceroService {
    // Listar
    List<TipoTerceroEntity> list();

    // Insertar
    TipoTerceroEntity save(TipoTerceroEntity tipotercero) throws Exception;

    // Actualizar
    TipoTerceroEntity update(TipoTerceroEntity tipotercero, Long id) throws Exception;

    // Borrar
    TipoTerceroEntity deleteById(Long id) throws Exception;

    TipoTerceroEntity aMayusculas(TipoTerceroEntity tipotercero);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
