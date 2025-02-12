package dev.Zerphyis.auth.service;

import dev.Zerphyis.auth.Dtos.DadosRegistroLogin;
import dev.Zerphyis.auth.entidades.registroLogin.RegistroLogin;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import dev.Zerphyis.auth.repositorios.RepositoryRegistroLogin;
import dev.Zerphyis.auth.repositorios.RepositoryUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRegistroLogin {

    @Autowired
    RepositoryRegistroLogin repository;

    @Autowired
    RepositoryUsuarios repositoryUsuarios;

    public RegistroLogin registrarTentativa(String usuarioId, DadosRegistroLogin dados) {
        var usuario = repositoryUsuarios.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var registro = new RegistroLogin(dados, usuario);
        registro.incrementarContador();
        return repository.save(registro);
    }

    public List<RegistroLogin> buscarHistoricoPorUsuario(String usuarioId) {
        List<RegistroLogin> historico = repository.findByUsuarioId(usuarioId);

        if (historico.isEmpty()) {
            throw new RuntimeException("Nenhum histórico encontrado para o usuário com ID: " + usuarioId);
        }

        return historico;
    }
}
