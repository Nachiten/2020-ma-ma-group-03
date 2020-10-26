package domain.entities.usuarios;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.OperacionDeEgreso;
import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {

    @Column (name = "tipoUsuario")
    @Enumerated(value = EnumType.STRING)
    private TipoUsuario tipo;

    @Column (name = "nombreUsuario")
    private String nombreUsuario;

    @Column (name = "contrasenia")
    private String contrasenia;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    // @Transient sirve para que no se persista este atributo
    @ManyToOne (cascade = CascadeType.ALL)
    private EntidadJuridica entidadJuridica;

    @OneToMany(mappedBy = "usuarioAsociado", cascade = {CascadeType.ALL})
    private List<Mensaje> bandejaDeMensajes;

    @OneToMany(mappedBy = "usuarioAsociado", cascade = {CascadeType.ALL})
    private List<ContraAnterior> contraseniasAnteriores;

    @Column (name = "tiempoUltimaContrasenia")
    private LocalDate tiempoUltimaContrasenia;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<OperacionDeEgreso> operacionesRevisadas;

    //-------------------------------------------------------------------------
                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Usuario(){
        inicializar();
    }

    public Usuario(TipoUsuario tipo, String nombreUsuario, String contrasenia, String nombre, String apellido) {
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        inicializar();
    }

    //-------------------------------------------------------------------------
                        //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.operacionesRevisadas = new ArrayList<>();
        this.contraseniasAnteriores = new ArrayList<>();
        this.bandejaDeMensajes = new ArrayList<>();
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

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setTiempoUltimaContrasenia(LocalDate tiempoUltimaContrasenia) {
        this.tiempoUltimaContrasenia = tiempoUltimaContrasenia;
    }

    public void agregarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso){
        operacionesRevisadas.add(operacionDeEgreso);
    }

    public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
        this.entidadJuridica = entidadJuridica;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setBandejaDeMensajes(List<Mensaje> bandejaDeMensajes) {
        this.bandejaDeMensajes = bandejaDeMensajes;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public String getContrasenia() { return contrasenia; }

    public List<Mensaje> getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }

    public TipoUsuario getTipo() { return tipo; }

    public String getNombreUsuario() { return nombreUsuario; }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public EntidadJuridica getEntidadJuridica() {
        return entidadJuridica;
    }
}