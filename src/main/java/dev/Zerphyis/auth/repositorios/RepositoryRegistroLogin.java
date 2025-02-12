package dev.Zerphyis.auth.repositorios;

import dev.Zerphyis.auth.entidades.registroLogin.RegistroLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepositoryRegistroLogin extends JpaRepository<RegistroLogin , String> {
    List<RegistroLogin> findByUsuarioId(String usuarioId);
}
