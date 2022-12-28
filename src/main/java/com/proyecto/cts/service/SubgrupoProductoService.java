package com.proyecto.cts.service;

import com.proyecto.cts.entity.SubgrupoProductoEntity;

import java.util.List;

public interface SubgrupoProductoService {
    // Listar
    List<SubgrupoProductoEntity> list();

    // Insertar
    SubgrupoProductoEntity save(SubgrupoProductoEntity subgrupoproducto) throws Exception;

    // Actualizar
    SubgrupoProductoEntity update(SubgrupoProductoEntity subgrupoproducto, Long id) throws Exception;

    // Borrar
    SubgrupoProductoEntity deleteById(Long id) throws Exception;

    SubgrupoProductoEntity aMayusculas(SubgrupoProductoEntity subgrupoproducto);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
