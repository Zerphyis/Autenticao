package dev.Zerphyis.auth.controller;

import dev.Zerphyis.auth.Dtos.DadosFoto;
import dev.Zerphyis.auth.Dtos.DadosPerfil;
import dev.Zerphyis.auth.entidades.perfil.Perfil;
import dev.Zerphyis.auth.service.ServicePerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class ControllerPerfil {

    @Autowired
     ServicePerfil servicePerfil;

    @PostMapping("/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable String id,
            @RequestBody DadosPerfil dados) {

            Perfil perfilAtualizado = servicePerfil.atualizarPerfil(id, dados);
            return ResponseEntity.ok(perfilAtualizado);
    }

    @PutMapping("/atualizarfoto/{id}")
    public ResponseEntity<String> atualizarFoto(
            @PathVariable String id,
            @RequestBody DadosFoto dados) {

        try {
            servicePerfil.atualizarFoto(id,dados.foto());
            return ResponseEntity.ok("Foto alterada com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado.");
        }
    }
}
