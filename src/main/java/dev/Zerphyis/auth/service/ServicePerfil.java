package dev.Zerphyis.auth.service;

import dev.Zerphyis.auth.Dtos.DadosPerfil;
import dev.Zerphyis.auth.entidades.perfil.Perfil;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import dev.Zerphyis.auth.repositorios.RepositoryPerfil;
import dev.Zerphyis.auth.repositorios.RepositoryUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePerfil {
    @Autowired
    RepositoryPerfil repository;

    @Autowired
    RepositoryUsuarios repositoryUsuarios;

    public Perfil atualizarPerfil(String usuarioId, DadosPerfil dados){
        var usuario = repositoryUsuarios.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        var perfilExistente = repository.findByUsuarioId(usuarioId).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado para o usuário"));
        perfilExistente.setTelefone(dados.telefone());
        perfilExistente.setEndereco(dados.endereco());
        perfilExistente.setFoto(dados.foto());
        perfilExistente.setDataNascimento(dados.dataNascimento());

        return repository.save(perfilExistente);
    }


    public void atualizarFoto(String id ,String foto){
        var perfil = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil com id fornecido não encontrado"));

        perfil.setFoto(foto);
        repository.save(perfil);

        System.out.println("Foto alterada com sucesso para o usuário " +perfil.getUsuario().getNome());
    }
}
