package com.proyecto.cts.repository;

import com.proyecto.cts.entity.RolEntity;
import com.proyecto.cts.zgeneral.ERoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository  extends JpaRepository<RolEntity, Long>  {
    String sqlTabla = "t155t_roles";

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.codigo = ?1", nativeQuery = true)
    Long findByCodigoContar(String codigo);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.descripcion = ?1 ", nativeQuery = true)
    Long findByValidarDuplicados(String descripcion);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.id = ?1", nativeQuery = true)
    Long searchById(Long id);

    Optional<RolEntity> findByName(ERoles name);

}
