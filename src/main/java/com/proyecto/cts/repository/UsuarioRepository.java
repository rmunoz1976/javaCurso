package com.proyecto.cts.repository;

import com.proyecto.cts.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    String sqlTabla = "t150t_usuarios";

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.username = ?1", nativeQuery = true)
    Long findByLoginContar(String Username);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t" +
            " WHERE t.nombre_completo = ?1" +
            " OR t.username = ?2 OR t.password = ?3 OR t.email = ?4", nativeQuery = true)
    Long findByValidarDuplicados(String nombreCompleto, String username, String password, String email);

    @Query(value = "SELECT COUNT(*) FROM " + sqlTabla + " t WHERE t.id = ?1", nativeQuery = true)
    Long searchById(Long id);
    Optional<UsuarioEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
