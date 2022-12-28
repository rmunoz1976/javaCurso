package com.proyecto.cts.repository;

import com.proyecto.cts.entity.GrupoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoProductoRepository extends JpaRepository<GrupoProductoEntity, Long> {
    String sqlTabla = "t512m_grupo_productos";

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.codigo = ?1", nativeQuery = true)
    Long findByCodigoContar(String codigo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.descripcion = ?1 ", nativeQuery = true)
    Long findByValidarDuplicados(String descripcion);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.id = ?1", nativeQuery = true)
    Long searchById(Long id);
}
