package com.proyecto.cts.service;

import com.proyecto.cts.entity.ProductoEntity;

import java.util.List;

public interface ProductoService {
    // Listar
    List<ProductoEntity> list();

    // Insertar
    ProductoEntity save(ProductoEntity producto) throws Exception;

    // Actualizar
    ProductoEntity update(ProductoEntity producto, Long id) throws Exception;

    // Borrar
    ProductoEntity deleteById(Long id) throws Exception;

    ProductoEntity aMayusculas(ProductoEntity producto);

    Long findByCodigoContar(String codigo);

    Long findByValidarDuplicadosCorto(String nombreCorto);
    Long findByValidarDuplicadosLargo(String nombreLargo);
}
