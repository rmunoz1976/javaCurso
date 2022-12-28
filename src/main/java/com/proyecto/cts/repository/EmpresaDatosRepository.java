package com.proyecto.cts.repository;

import com.proyecto.cts.entity.EmpresaDatosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaDatosRepository extends JpaRepository<EmpresaDatosEntity, Long> {
    String sqlTabla = "t225e_empresas_datos";

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.codigo = ?1", nativeQuery = true)
    Long findByCodigoContar(String codigo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.nombre_persona_juridica = ?1 ", nativeQuery = true)
    Long findByValidarDuplicados(String nombrePersonaJuridica);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.id = ?1", nativeQuery = true)
    Long searchById(Long id);
}
