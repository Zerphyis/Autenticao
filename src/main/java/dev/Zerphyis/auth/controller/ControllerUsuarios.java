package dev.Zerphyis.auth.controller;

import dev.Zerphyis.auth.Dtos.DadosRegistroLogin;
import dev.Zerphyis.auth.Dtos.DadosSenha;
import dev.Zerphyis.auth.Dtos.DadosUsuarios;
import dev.Zerphyis.auth.entidades.registroLogin.RegistroLogin;
import dev.Zerphyis.auth.service.ServiceRegistroLogin;
import dev.Zerphyis.auth.service.ServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class ControllerUsuarios {
    @Autowired
    ServiceUsuarios service;
    @Autowired
    ServiceRegistroLogin serviceLogin;

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
    @PostMapping("/login/{usuarioId}")
    public ResponseEntity registrarTentativa(@PathVariable String usuarioId, @RequestBody DadosRegistroLogin dados) {
        serviceLogin.registrarTentativa(usuarioId, dados);
        return ResponseEntity.ok("Tentativa de login registrada");
    }

    @GetMapping("/historico/{usuarioId}")
    public ResponseEntity<List<RegistroLogin>> buscarHistorico(@PathVariable String usuarioId) {
        try {
            List<RegistroLogin> historico = serviceLogin.buscarHistoricoPorUsuario(usuarioId);
            return ResponseEntity.ok(historico);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }


    @DeleteMapping("/desativar/{id}")
    public  ResponseEntity<Void> desativarConta(@PathVariable String id){
        service.desativarConta(id);
        return ResponseEntity.noContent().build();
    }
}
