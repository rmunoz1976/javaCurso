package com.proyecto.cts.service;

import com.proyecto.cts.entity.EmpresaDatosEntity;

import java.util.List;

public interface EmpresaDatosService {
    // Listar
    List<EmpresaDatosEntity> list();

    // Insertar
    EmpresaDatosEntity save(EmpresaDatosEntity empresadatos) throws Exception;

    // Actualizar
    EmpresaDatosEntity update(EmpresaDatosEntity empresadatos, Long id) throws Exception;

    // Borrar
    EmpresaDatosEntity deleteById(Long id) throws Exception;

    EmpresaDatosEntity aMayusculas(EmpresaDatosEntity empresadatos);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombrePersonaJuridica);
}
