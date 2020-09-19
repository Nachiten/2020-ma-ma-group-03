package Usuarios;

import Entidades.EntidadJuridica;
import Operaciones.OperacionDeEgreso;
import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "usuarioAsociado", cascade = {CascadeType.ALL})
    private List<Mensaje> bandejaDeMensajes;

    @OneToMany(mappedBy = "usuarioAsociado", cascade = {CascadeType.ALL})
    private List<ContraAnterior> contraseniasAnteriores;

    @Column (name = "TiempoUltimaContrasenia")
    private LocalDate tiempoUltimaContrasenia;

    //@OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
    @Transient
    private List<OperacionDeEgreso> operacionesRevisadas;

    //CONSTRUCTOR
    public Usuario(){

    }

    public Usuario(TipoUsuario tipo, String nombreUsuario, String contrasenia) {
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.contraseniasAnteriores = new ArrayList<>();
        this.bandejaDeMensajes = new ArrayList<>();
    }

    //METHOD'S
    public void actualizarRevisor(OperacionDeEgreso operacionDeEgreso){
        operacionesRevisadas.add(operacionDeEgreso);
    }

    public void publicarMensajeEnBandejaDeMensajes(String identificacion, Boolean resultadoValidacion){
        new Publicador().publicarMensaje(resultadoValidacion, identificacion, this);
    }

    public void agregarContraAnterior(ContraAnterior unaContraAnterior){
        this.contraseniasAnteriores.add(unaContraAnterior);
    }

    public void asociarMensaje(Mensaje mensaje) {
        bandejaDeMensajes.add(mensaje);
    }

    //SETTERS
    public void setTiempoUltimaContrasenia(LocalDate tiempoUltimaContrasenia) {
        this.tiempoUltimaContrasenia = tiempoUltimaContrasenia;
    }

    //GETTERS
    public String getContrasenia() { return contrasenia; }

    public List<Mensaje> getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }
}