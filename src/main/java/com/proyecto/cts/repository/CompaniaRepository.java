package com.proyecto.cts.repository;

import com.proyecto.cts.entity.CompaniaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniaRepository extends JpaRepository<CompaniaEntity, Long> {
    String sqlTabla = "t040t_compania";

    @Query(value = "SELECT c.codigo FROM " + sqlTabla + " t WHERE t.codigo = ?1", nativeQuery = true)
    String findByCodigo(String codigo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.codigo = ?1", nativeQuery = true)
    Long findByCodigoContar(String codigo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t " +
            " WHERE (t.codigo = ?1 OR t.nit = ?2 OR t.nombre_corto = ?3 " +
            " OR t.nombre_largo = ?4 OR t.sigla = ?5)", nativeQuery = true)
    Long findByValidarDuplicados(String codigo, Long nit, String nombreCorto, String nombreLargo, String sigla);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.id = ?1", nativeQuery = true)
    Long searchById(Long id);
}
