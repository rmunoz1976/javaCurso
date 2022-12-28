package com.proyecto.cts.service;

import com.proyecto.cts.entity.CompaniaEntity;

import java.util.List;

public interface CompaniaService {
    // Listar
    List<CompaniaEntity> list();

    // Insertar
    CompaniaEntity save(CompaniaEntity compania) throws Exception;

    // Actualizar
    CompaniaEntity update(CompaniaEntity compania, Long id) throws Exception;

    // Borrar
    CompaniaEntity deleteById(Long id) throws Exception;

    // Convertir a Mayusculas
    CompaniaEntity aMayusculas(CompaniaEntity compania);

    String findByCodigo(String name);

    Long findByCodigoContar(String name);

    Long findByValidarDuplicados(String codigo, Long nit, String nombreCorto, String nombreLargo, String sigla);

}
