package dev.Zerphyis.auth.Dtos;

import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record DadosPerfil(String telefone,
         String endereco,
         String foto,
         LocalDateTime dataNascimento) {
}
