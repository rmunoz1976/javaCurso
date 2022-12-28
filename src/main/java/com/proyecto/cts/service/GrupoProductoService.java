package com.proyecto.cts.service;

import com.proyecto.cts.entity.GrupoProductoEntity;

import java.util.List;

public interface GrupoProductoService {
    // Listar
    List<GrupoProductoEntity> list();

    // Insertar
    GrupoProductoEntity save(GrupoProductoEntity grupoproducto) throws Exception;

    // Actualizar
    GrupoProductoEntity update(GrupoProductoEntity grupoproducto, Long id) throws Exception;

    // Borrar
    GrupoProductoEntity deleteById(Long id) throws Exception;

    GrupoProductoEntity aMayusculas(GrupoProductoEntity grupoproducto);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
