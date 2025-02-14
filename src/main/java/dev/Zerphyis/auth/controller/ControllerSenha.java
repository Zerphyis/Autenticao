package dev.Zerphyis.auth.controller;

import dev.Zerphyis.auth.entidades.senha.Senha;
import dev.Zerphyis.auth.service.ServiceSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Senha")
public class ControllerSenha {
    @Autowired
    ServiceSenha service;

    @PostMapping("/gerar")
    public ResponseEntity<Senha> gerarTokenRecuperacao(@RequestParam String nomeUsuario) {
        Senha senha = service.gerarTokenRecuperacao(nomeUsuario);
        return ResponseEntity.ok(senha);
    }

    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarToken(@RequestParam String nomeUsuario, @RequestParam String token) {
        boolean valido = service.validarToken(nomeUsuario, token);
        return ResponseEntity.ok(valido);
    }

    @PostMapping("/alterar")
    public ResponseEntity<String> alterarSenha(@RequestParam String nomeUsuario, @RequestParam String token, @RequestParam String novaSenha) {
        try {
            service.alterarSenha(nomeUsuario, token, novaSenha);
            return ResponseEntity.ok("Senha alterada com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}

