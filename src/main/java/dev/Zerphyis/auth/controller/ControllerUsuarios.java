package dev.Zerphyis.auth.controller;

import dev.Zerphyis.auth.Dtos.*;
import dev.Zerphyis.auth.entidades.login.Login;
import dev.Zerphyis.auth.entidades.registroLogin.RegistroLogin;
import dev.Zerphyis.auth.repositorios.RepositoryLogin;
import dev.Zerphyis.auth.segurança.ServiceToken;
import dev.Zerphyis.auth.service.ServiceRegistroLogin;
import dev.Zerphyis.auth.service.ServiceUsuarios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ServiceToken tokenService;

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody  DadosUsuarios dados){
        service.cadastrarUsuarios(dados);

        return ResponseEntity.ok(dados);

    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosAutentica data) {

            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);  // Autentica o usuário

            // Gera o token JWT
            var token = tokenService.generateToken((Login) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResposta(token));  // Retorna o token gerado
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
