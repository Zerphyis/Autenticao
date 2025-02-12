package dev.Zerphyis.auth.repositorios;

import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsuarios extends JpaRepository<Usuarios,String> {
}
