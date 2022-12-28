package com.proyecto.cts.service;

import com.proyecto.cts.entity.DepartamentoEntity;

import java.util.List;

public interface DepartamentoService {
    // Listar
    List<DepartamentoEntity> list();

    // Insertar
    DepartamentoEntity save(DepartamentoEntity departamento) throws Exception;

    // Actualizar
    DepartamentoEntity update(DepartamentoEntity departamento, Long id) throws Exception;

    // Borrar
    DepartamentoEntity deleteById(Long id) throws Exception;

    DepartamentoEntity aMayusculas(DepartamentoEntity departamento);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
