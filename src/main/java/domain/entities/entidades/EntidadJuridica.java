package domain.entities.entidades;

import domain.entities.apiMercadoLibre.DireccionPostal;
import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.operaciones.OperacionDeIngreso;
import persistencia.EntidadPersistente;
import domain.entities.tipoEntidadJuridica.TipoEntidadJuridica;
import domain.entities.operaciones.OperacionDeEgreso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidadJuridica")
public class EntidadJuridica extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombreEntidadJuridica;

    @Column(name = "nombreFicticio")
    private String nombreFicticioEntidadJuridica;

    @Column(name = "razonSocial")
    private String razonSocialEntidadJuridica;

    @Column(name = "cuitEntidadJuridica")
    private String cuitEntidadJuridica;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccionPostal_id")
    private DireccionPostal direccionPostalEntidadJuridica;

    @Column(name = "codigoInscripcionDefinitiva")
    private String codigoInscripcionDefinitiva;

    @ManyToOne(cascade = {CascadeType.ALL})
    private TipoEntidadJuridica tipoEntidadJuridica;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<OperacionDeEgreso> operacionesDeEgreso;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OperacionDeIngreso> operacionesDeIngreso;

    @OneToMany(mappedBy = "entidadJuridicaAsociada", cascade = {CascadeType.ALL})
    private List<EntidadBase> entidadesBase;

    @Transient
    private List<Criterio> listaCriterio;

    @Column(name = "estoyHabilitado")
    private boolean estoyHabilitado;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public EntidadJuridica() { inicializar(); }

    public EntidadJuridica(String nombreEntidadJuridica, String nombreFicticioEntidadJuridica, String razonSocialEntidadJuridica, String cuitEntidadJuridica, DireccionPostal direccionPostalEntidadJuridica, String codigoInscripcionDefinitiva, TipoEntidadJuridica tipoEntidadJuridica) {
        this.nombreEntidadJuridica = nombreEntidadJuridica;
        this.nombreFicticioEntidadJuridica = nombreFicticioEntidadJuridica;
        this.razonSocialEntidadJuridica = razonSocialEntidadJuridica;
        this.cuitEntidadJuridica = cuitEntidadJuridica;
        this.direccionPostalEntidadJuridica = direccionPostalEntidadJuridica;
        this.codigoInscripcionDefinitiva = codigoInscripcionDefinitiva;
        this.tipoEntidadJuridica = tipoEntidadJuridica;

        inicializar();
    }

    //-------------------------------------------------------------------------
                            //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.operacionesDeEgreso = new ArrayList<>();
        this.operacionesDeIngreso = new ArrayList<>();
        this.estoyHabilitado = true;
    }

    public void agregarCriterio(Criterio criterio){ listaCriterio.add(criterio);}

    public void agregarCategoriaDeCriterio(CategoriaCriterio categoriaCriterio, Criterio criterio){
        //TODO preguntar si hace falta verificar si ya existe categoria. Lo mismo para criterio
        criterio.agregarCategoria(categoriaCriterio);
    }

    public void agregarOperacionDeEgresoAsociada(OperacionDeEgreso operacionDeEgreso){
        operacionesDeEgreso.add(operacionDeEgreso);
    }

    public void agregarOperacionDeIngresoAsociada(OperacionDeIngreso operacionDeIngreso){
        operacionesDeIngreso.add(operacionDeIngreso);
    }

    public void cambiarAHabilitado(){
        this.estoyHabilitado = true;
    }

    public void cambiarAInhabilitado(){
        this.estoyHabilitado = false;
    }
    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------


    public String getNombreEntidadJuridica() {
        return nombreEntidadJuridica;
    }

    public String getNombreFicticioEntidadJuridica() {
        return nombreFicticioEntidadJuridica;
    }

    public String getCuitEntidadJuridica() {
        return cuitEntidadJuridica;
    }

    public List<OperacionDeEgreso> getOperacionesDeEgreso() {
        return operacionesDeEgreso;
    }

    public List<OperacionDeIngreso> getOperacionesDeIngreso() {
        return operacionesDeIngreso;
    }

    public String getRazonSocialEntidadJuridica() {
        return razonSocialEntidadJuridica;
    }

    public boolean estoyHabilitado() {
        return estoyHabilitado;
    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setTipoEntidadJuridica(TipoEntidadJuridica tipoEntidadJuridica) {
        this.tipoEntidadJuridica = tipoEntidadJuridica;
    }

    public void setOperacionesDeEgreso(List<OperacionDeEgreso> operacionesDeEgreso) {
        this.operacionesDeEgreso = operacionesDeEgreso;
    }

    public void setOperacionesDeIngreso(List<OperacionDeIngreso> operacionDeIngreso) {
        this.operacionesDeIngreso = operacionDeIngreso;
    }
}