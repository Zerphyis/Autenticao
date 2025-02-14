package dev.Zerphyis.auth.repositorios;

import dev.Zerphyis.auth.entidades.senha.Senha;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RepositorySenha extends JpaRepository<Senha,String> {
    Optional<Senha> findByUsuario(Usuarios usuario);
    Optional<Senha> findByUsuarioNome(String nome);
}
