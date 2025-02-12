package dev.Zerphyis.auth.entidades.perfil;

import dev.Zerphyis.auth.Dtos.DadosPerfil;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String telefone;
    private String endereco;
    private String foto;
    @PastOrPresent
    private LocalDateTime dataNascimento;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    public Perfil(){

    }
    public  Perfil(DadosPerfil dados , Usuarios usuario){
        this.telefone= dados.telefone();
        this.dataNascimento=dados.dataNascimento();
        this.endereco= dados.endereco();
        this.foto= dados.foto();
        this.usuario=usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
