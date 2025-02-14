package dev.Zerphyis.auth.repositorios;

import dev.Zerphyis.auth.entidades.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryLogin extends JpaRepository<Login,String> {
    UserDetails findByEmail(String email);
}
