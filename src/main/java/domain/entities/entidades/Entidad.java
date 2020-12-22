package domain.entities.entidades;

import domain.entities.apiMercadoLibre.DireccionPostal;
import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.operaciones.OperacionDeIngreso;
import domain.entities.tipoEntidadJuridica.Empresa;
import domain.entities.tipoEntidadJuridica.OrganizacionSectorSocial;
import persistencia.EntidadPersistente;
import domain.entities.tipoEntidadJuridica.TipoEntidadJuridica;
import domain.entities.operaciones.OperacionDeEgreso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidades")
public class Entidad extends EntidadPersistente {

    @Column(name = "nombreFicticio")
    private String nombreFicticioEntidad;

    @Column(name = "razonSocial")
    private String razonSocialEntidad;

    @Column(name = "cuitEntidadJuridica")
    private String cuitEntidad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccionPostal_id")
    private DireccionPostal direccionPostalEntidad;

    @Column(name = "codigoInscripcionDefinitiva")
    private String codigoInscripcionDefinitiva;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "entidadJuridicaAsociada_id")
    private EntidadJuridica entidadJuridicaAsociada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipoEmpresa_id")
    private Empresa tipoEmpresa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipoOrganizacionSectorSocial_id")
    private OrganizacionSectorSocial tipoOrganizacionSectorSocial;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<OperacionDeEgreso> operacionesDeEgreso;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OperacionDeIngreso> operacionesDeIngreso;


    @Transient
    private List<Criterio> listaCriterio;

    @Column(name = "estoyHabilitado")
    private boolean estoyHabilitado;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Entidad() { inicializar(); }

    public Entidad( String nombreFicticioEntidad, String razonSocialEntidad, String cuitEntidad, DireccionPostal direccionPostalEntidad, String codigoInscripcionDefinitiva) {

        this.nombreFicticioEntidad = nombreFicticioEntidad;
        this.razonSocialEntidad = razonSocialEntidad;
        this.cuitEntidad = cuitEntidad;
        this.direccionPostalEntidad = direccionPostalEntidad;
        this.codigoInscripcionDefinitiva = codigoInscripcionDefinitiva;
        inicializar();
    }

    public Entidad(String nombreFicticioOrgSoc, String razonSocialOrgSoc) {
        this.nombreFicticioEntidad = nombreFicticioOrgSoc;
        this.razonSocialEntidad = razonSocialOrgSoc;
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

    public void agregarEntidadJuridicaAsociada(EntidadJuridica entidadJuridica){
        this.entidadJuridicaAsociada = entidadJuridica;
    }

    public void agregarTipoEmpresa(Empresa empresa){
        this.tipoEmpresa = empresa;
    }

    public void agregarTipoOrganizacionSectorSocial(OrganizacionSectorSocial organizacionSectorSocial){
        this.tipoOrganizacionSectorSocial = organizacionSectorSocial;
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

    public String getNombreFicticioEntidad() {
        return nombreFicticioEntidad;
    }

    public String getCuitEntidad() {
        return cuitEntidad;
    }

    public List<OperacionDeEgreso> getOperacionesDeEgreso() {
        return operacionesDeEgreso;
    }

    public List<OperacionDeIngreso> getOperacionesDeIngreso() {
        return operacionesDeIngreso;
    }

    public String getRazonSocialEntidad() {
        return razonSocialEntidad;
    }

    public boolean getEstoyHabilitado() {
        return estoyHabilitado;
    }

    public DireccionPostal getDireccionPostalEntidad() {
        return direccionPostalEntidad;
    }

    public String getCodigoInscripcionDefinitiva() {
        return codigoInscripcionDefinitiva;
    }

    public EntidadJuridica getEntidadJuridicaAsociada() {
        return entidadJuridicaAsociada;
    }

    public Empresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public OrganizacionSectorSocial getTipoOrganizacionSectorSocial() {
        return tipoOrganizacionSectorSocial;
    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------


    public void setOperacionesDeEgreso(List<OperacionDeEgreso> operacionesDeEgreso) {
        this.operacionesDeEgreso = operacionesDeEgreso;
    }

    public void setOperacionesDeIngreso(List<OperacionDeIngreso> operacionDeIngreso) {
        this.operacionesDeIngreso = operacionDeIngreso;
    }
}