package dev.Zerphyis.auth.entidades.senha;

import dev.Zerphyis.auth.Dtos.DadosSenha;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Entity
@Table(name = "tb_senha")
public class Senha {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String token;
    private Instant tempoToken;
    private Boolean tokenUsado;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nome")
    private Usuarios usuario;

    public Senha(){

    }
    public Senha(Usuarios dados) {
     this.usuario= dados;
     gerarNovoToken();
    }

    public void gerarNovoToken() {
        this.token = gerarTokenAleatorio();
        this.tempoToken = Instant.now().plusSeconds(30 * 60);
        this.tokenUsado = false;
    }

    private String gerarTokenAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder token = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            token.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        return token.toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getTempoToken() {
        return tempoToken;
    }

    public void setTempoToken(Instant tempoToken) {
        this.tempoToken = tempoToken;
    }

    public Boolean getTokenUsado() {
        return tokenUsado;
    }

    public void setTokenUsado(Boolean tokenUsado) {
        this.tokenUsado = tokenUsado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
