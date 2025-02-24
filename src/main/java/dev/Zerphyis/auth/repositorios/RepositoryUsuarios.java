package dev.Zerphyis.auth.repositorios;


import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUsuarios extends JpaRepository<Usuarios,String> {
    Optional<Usuarios> findById(String id);
    Optional<Usuarios> findByNome(String nome);
}

