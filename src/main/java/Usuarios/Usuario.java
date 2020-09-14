package Usuarios;

import Entidades.EntidadJuridica;
import Operaciones.OperacionDeEgreso;
import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {

    @Column (name = "TipoUsuario")
    @Enumerated(value = EnumType.STRING)
    private TipoUsuario tipo;

    @Column (name = "nombreUsuario")
    private String nombreUsuario;
    @Column (name = "Contrasenia")
    private String contrasenia;

    // @Transient sirve para que no se persista este atributo
    @Transient
    private EntidadJuridica entidadJuridica;

    @Transient
    private BandejaDeMensajes bandejaDeMensajes;

    @OneToMany(mappedBy = "usuarioAsociado", cascade = {CascadeType.ALL})
    private List<ContraAnterior> contraseniasAnteriores;

    @Column (name = "TiempoUltimaContrasenia")
    private LocalDate tiempoUltimaContrasenia;

    @Transient
    private List<OperacionDeEgreso> operacionesRevisadas;

    public Usuario(TipoUsuario tipo, String nombreUsuario, String contrasenia) {
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.contraseniasAnteriores = new ArrayList<>();
    }

    public BandejaDeMensajes getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }

    public void setBandejaDeMensajes(BandejaDeMensajes bandejaDeMensajes) {
        this.bandejaDeMensajes = bandejaDeMensajes;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void actualizarRevisor(OperacionDeEgreso operacionDeEgreso){
        operacionesRevisadas.add(operacionDeEgreso);
    }

    public void publicarMensajeEnBandejaDeMensajes(String identificacion, Boolean resultadoValidacion){
        bandejaDeMensajes.publicarMensaje(resultadoValidacion, identificacion);
    }

    public void agregarContraAnterior(ContraAnterior unaContraAnterior){
        this.contraseniasAnteriores.add(unaContraAnterior);
    }

    public void setTiempoUltimaContrasenia(LocalDate tiempoUltimaContrasenia) {
        this.tiempoUltimaContrasenia = tiempoUltimaContrasenia;
    }
}