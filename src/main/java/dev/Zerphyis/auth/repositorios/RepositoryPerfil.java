package dev.Zerphyis.auth.repositorios;

import dev.Zerphyis.auth.entidades.perfil.Perfil;
import dev.Zerphyis.auth.entidades.registroLogin.RegistroLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPerfil extends JpaRepository<Perfil,String> {
    List<Perfil> findByUsuarioId(String usuarioId);
}
