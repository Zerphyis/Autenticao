package dev.Zerphyis.auth.controller;

import dev.Zerphyis.auth.Dtos.DadosSenha;
import dev.Zerphyis.auth.Dtos.DadosUsuarios;
import dev.Zerphyis.auth.service.ServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ControllerUsuarios {
    @Autowired
    ServiceUsuarios service;

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody  DadosUsuarios dados){
        service.cadastrarUsuarios(dados);
        return ResponseEntity.ok(dados);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable String id,@RequestBody DadosUsuarios dados){
        service.atualizarDados(id,dados);
        return ResponseEntity.ok(dados);

    }
    @PutMapping("/Senha/{id}")
    public ResponseEntity<Void> mudarSenha(@PathVariable String id, @RequestBody DadosSenha dadosSenha) {
        service.mudarSenha(id, dadosSenha.senha());
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/desativar/{id}")
    public  ResponseEntity<Void> desativarConta(@PathVariable String id){
        service.desativarConta(id);
        return ResponseEntity.noContent().build();
    }
}
