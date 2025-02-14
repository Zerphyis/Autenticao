package dev.Zerphyis.auth.repositorios;

import dev.Zerphyis.auth.entidades.perfil.Perfil;
import dev.Zerphyis.auth.entidades.registroLogin.RegistroLogin;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryPerfil extends JpaRepository<Perfil,String> {
    Optional<Usuarios> findByUsuarioId(String usuarioId);
}
