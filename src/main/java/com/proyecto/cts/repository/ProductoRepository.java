package com.proyecto.cts.repository;

import com.proyecto.cts.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    String sqlTabla = "t518m_productos";

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.codigo = ?1", nativeQuery = true)
    Long findByCodigoContar(String codigo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.nombre_corto = ?1 ", nativeQuery = true)
    Long findByValidarDuplicadosCorto(String nombreCorto);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.nombre_largo = ?1 ", nativeQuery = true)
    Long findByValidarDuplicadosLargo(String nombreLargo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.id = ?1", nativeQuery = true)
    Long searchById(Long id);
}
