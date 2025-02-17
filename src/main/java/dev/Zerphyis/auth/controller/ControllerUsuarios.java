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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> login(@RequestBody DadosAutentica dadosAutentica) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dadosAutentica.email(), dadosAutentica.senha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Object principal = authentication.getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        String token = tokenService.generateToken(username);

        return ResponseEntity.ok(new LoginResposta(token));
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

        List<RegistroLogin> historico = serviceLogin.buscarHistoricoPorUsuario(usuarioId);
            return ResponseEntity.ok(historico);

    }


    @DeleteMapping("/desativar/{id}")
    public  ResponseEntity<Void> desativarConta(@PathVariable String id){
        service.desativarConta(id);
        return ResponseEntity.noContent().build();
    }
}
