package domain.entities.usuarios;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.OperacionDeEgreso;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static domain.entities.usuarios.TipoUsuario.ESTANDAR;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {

    @Column (name = "tipoUsuario")
    @Enumerated(value = EnumType.STRING)
    private TipoUsuario tipo;

    @Column (name = "nombreUsuario")
    private String nombreUsuario;

    @Column (name = "contraseniaEncriptada")
    private String contraseniaEncriptada;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @ManyToOne
    private EntidadJuridica entidadJuridica;

    @OneToMany(mappedBy = "usuarioAsociado", cascade = {CascadeType.ALL})
    private List<Mensaje> bandejaDeMensajes;

    @OneToMany(mappedBy = "usuarioAsociado", cascade = {CascadeType.ALL})
    private List<ContraAnterior> contraseniasAnteriores;

    @Column (name = "tiempoUltimaContrasenia")
    private LocalDate tiempoUltimaContrasenia;

    // TODO | Sacar el cascade para correr el server | (cascade=CascadeType.ALL)
    @ManyToMany
    private List<OperacionDeEgreso> operacionesRevisadas;

    @Column(name="estoyHabilitado")
    private boolean estoyHabilitado ;

    //-------------------------------------------------------------------------
                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Usuario(){
        inicializar();
    }

    public Usuario(TipoUsuario tipo, String nombreUsuario, String contrasenia, String nombre, String apellido) {
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.contraseniaEncriptada = encriptarContrasenia(contrasenia);
        this.nombre = nombre;
        this.apellido = apellido;
        inicializar();
    }

    public String encriptarContrasenia(String contrasenia) {
        return DigestUtils.md5Hex(contrasenia);
    }

    //-------------------------------------------------------------------------
                        //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.operacionesRevisadas = new ArrayList<>();
        this.contraseniasAnteriores = new ArrayList<>();
        this.bandejaDeMensajes = new ArrayList<>();
        this.estoyHabilitado = true;
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

    public void cambiarAHabilitado(){
        this.setEstoyHabilitado(true);
    }

    public void cambiarAInhabilitado(){
        this.setEstoyHabilitado(false);
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
        this.contraseniaEncriptada = contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    //
    public void setEstoyHabilitado(Boolean estoyHabilitado){this.estoyHabilitado= estoyHabilitado;}

    public void setTipoUsuario(TipoUsuario tipoUsuario){
        this.tipo = tipoUsuario;
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public String getContrasenia() { return contraseniaEncriptada; }

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

    public boolean getEstoyHabilitado(){return estoyHabilitado;}
}