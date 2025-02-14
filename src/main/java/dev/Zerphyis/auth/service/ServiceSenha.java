package dev.Zerphyis.auth.service;

import dev.Zerphyis.auth.entidades.senha.Senha;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import dev.Zerphyis.auth.repositorios.RepositorySenha;
import dev.Zerphyis.auth.repositorios.RepositoryUsuarios;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ServiceSenha {
    @Autowired
    RepositorySenha repository;
    @Autowired
    RepositoryUsuarios repositoryUsuarios;

    public Senha gerarTokenRecuperacao(String nomeUsuario) {
        Usuarios usuario = repositoryUsuarios.findByNome(nomeUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Senha senha = new Senha(usuario);
        repository.save(senha);
        System.out.println("Token de recuperação: "+senha.getToken());
        return senha;

    }

    public boolean validarToken(String nomeUsuario, String token) {
        Optional<Senha> senhaOpt = repository.findByUsuarioNome(nomeUsuario);
        if (senhaOpt.isEmpty()) {
            return false;
        }

        Senha senha = senhaOpt.get();
        return !senha.getTokenUsado() && senha.getToken().equals(token) && senha.getTempoToken().isAfter(Instant.now());
    }

    public void alterarSenha(String nomeUsuario, String token, String novaSenha) {
        Usuarios usuario = repositoryUsuarios.findByNome(nomeUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Optional<Senha> senhaOpt = repository.findByUsuario(usuario);
        if (senhaOpt.isEmpty()) {
            throw new IllegalArgumentException("Token inválido ou expirado");
        }

        Senha senha = senhaOpt.get();
        if (!senha.getToken().equals(token) || senha.getTokenUsado() || senha.getTempoToken().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Token inválido ou expirado");
        }

        usuario.setSenha(novaSenha);
        repositoryUsuarios.save(usuario);

        senha.setTokenUsado(true);
        repository.save(senha);
    }
}
