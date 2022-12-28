package com.proyecto.cts.service;

import com.proyecto.cts.entity.MunicipioEntity;

import java.util.List;

public interface MunicipioService {
    // Listar
    List<MunicipioEntity> list();

    // Insertar
    MunicipioEntity save(MunicipioEntity municipio) throws Exception;

    // Actualizar
    MunicipioEntity update(MunicipioEntity municipio, Long id) throws Exception;

    // Borrar
    MunicipioEntity deleteById(Long id) throws Exception;

    MunicipioEntity aMayusculas(MunicipioEntity municipio);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicados(String nombreCompleto);
}
