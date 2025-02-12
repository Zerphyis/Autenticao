package dev.Zerphyis.auth.entidades.usuarios;

import dev.Zerphyis.auth.Dtos.DadosUsuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;
    @NotBlank
    private  String nome;
    @NotBlank
    private  String senha;

    private LocalDateTime dataCriada;

    private LocalDateTime ultimoAcesso;
    private Boolean status;


    public Usuarios(){

    }

    public Usuarios(DadosUsuarios dados){
        this.nome= dados.nome();
        this.senha= dados.senha();
        this.dataCriada=LocalDateTime.now();
        this.status=true;

    }

    public Boolean desativarConta() {
        this.status = false;
        return this.status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCriada() {
        return dataCriada;
    }

    public void setDataCriada(LocalDateTime dataCriada) {
        this.dataCriada = dataCriada;
    }

    public LocalDateTime getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(LocalDateTime ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
