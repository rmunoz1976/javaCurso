package com.proyecto.cts.service;

import com.proyecto.cts.entity.TipoDocumentoEntity;

import java.util.List;

public interface TipoDocumentoService {
    // Listar
    List<TipoDocumentoEntity> list();

    // Insertar
    TipoDocumentoEntity save(TipoDocumentoEntity tipodocumento) throws Exception;

    // Actualizar
    TipoDocumentoEntity update(TipoDocumentoEntity tipodocumento, Long id) throws Exception;

    // Borrar
    TipoDocumentoEntity deleteById(Long id) throws Exception;

    TipoDocumentoEntity aMayusculas(TipoDocumentoEntity tipodocumento);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);

    Long searchById(Long id);
}
