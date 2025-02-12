package dev.Zerphyis.auth.entidades.registroLogin;

import dev.Zerphyis.auth.Dtos.DadosRegistroLogin;
import dev.Zerphyis.auth.entidades.usuarios.Usuarios;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "tb_registro_login")
public class RegistroLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   private String id;
    private LocalDateTime dataHora;
    private String enderecoIp;
    private String dispositivo;
    private String navegador;
    private Boolean loginSucesso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    private Integer contadorTentativas;


    public RegistroLogin(){

    }
    public RegistroLogin(DadosRegistroLogin dados,Usuarios usuario ){
        this.dataHora=LocalDateTime.now();
        this.enderecoIp=gerarIpAleatorio();
        this.dispositivo= dados.dispositivo();
        this.navegador= dados.navegador();
        this.loginSucesso= Boolean.TRUE;
        this.contadorTentativas=1;
        this.usuario=usuario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    private String gerarIpAleatorio() {
        Random random = new Random();
        return random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256);
    }

    public Integer getContadorTentativas() {
        return contadorTentativas;
    }

    public void setContadorTentativas(Integer contadorTentativas) {
        this.contadorTentativas = contadorTentativas;
    }

    public void incrementarContador() {
        if (this.contadorTentativas == null) {
            this.contadorTentativas = 1;
        } else {
            this.contadorTentativas++;
        }
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getEnderecoIp() {
        return enderecoIp;
    }

    public void setEnderecoIp(String enderecoIp) {
        this.enderecoIp = enderecoIp;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public Boolean getLoginSucesso() {
        return loginSucesso;
    }

    public void setLoginSucesso(Boolean loginSucesso) {
        this.loginSucesso = loginSucesso;
    }
}
