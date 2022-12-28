package com.proyecto.cts.repository;

import com.proyecto.cts.entity.UnidadMedidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedidaEntity, Long> {
    String sqlTabla = "t133t_unidades_medidas";

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.codigo = ?1", nativeQuery = true)
    Long findByCodigoContar(String codigo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.descripcion = ?1 ", nativeQuery = true)
    Long findByValidarDuplicados(String descripcion);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.id = ?1", nativeQuery = true)
    Long searchById(Long id);
}
