package dev.Zerphyis.auth.service;

import dev.Zerphyis.auth.Dtos.DadosUsuarios;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import dev.Zerphyis.auth.repositorios.RepositoryUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuarios {
    @Autowired
    RepositoryUsuarios repository;


    public Usuarios cadastrarUsuarios(DadosUsuarios dados){
        var novoUsuario= new Usuarios(dados);
       return repository.save(novoUsuario);
    }

    public Usuarios atualizarDados(String id, DadosUsuarios dados) {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id fornecido não encontrado"));

        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(dados.senha());

        return repository.save(usuario);
    }


    public void mudarSenha(String id, String novaSenha) {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id fornecido não encontrado"));

        usuario.setSenha(novaSenha);
        repository.save(usuario);
        System.out.println("Senha alterada com sucesso para o usuário: " + usuario.getNome());
    }

    public Boolean desativarConta(String id) {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id fornecido não encontrado"));

        usuario.desativarConta();
        repository.save(usuario);
       return usuario.getStatus();
    }

}
