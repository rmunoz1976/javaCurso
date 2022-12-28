package com.proyecto.cts.service;

import com.proyecto.cts.entity.EmpresaEntity;

import java.util.List;

public interface EmpresaService {
    // Listar
    List<EmpresaEntity> list();

    // Insertar
    EmpresaEntity save(EmpresaEntity empresa) throws Exception;

    // Actualizar
    EmpresaEntity update(EmpresaEntity empresa, Long id) throws Exception;

    // Borrar
    EmpresaEntity deleteById(Long id) throws Exception;

    // Convertir a Mayusculas
    EmpresaEntity aMayusculas(EmpresaEntity empresa);

    String findByCodigo(String name);

    Long findByCodigoContar(String name);

    Long findByValidarDuplicados(String codigo, Long nit, String nombreCorto, String nombreLargo, String sigla);

}
