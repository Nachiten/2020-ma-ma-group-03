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

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "cuit")
    private String cuit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccionPostal_id")
    private DireccionPostal direccionPostal;

    @Column(name = "codigoInscripcionDefinitiva")
    private String codigoInscripcionDefinitiva;

    @ManyToOne(cascade = {CascadeType.ALL})
    private TipoEntidadJuridica tipoEntidadJuridica;

    @OneToMany(mappedBy = "entidadJuridicaAsociada", cascade = {CascadeType.ALL})
    private List<OperacionDeEgreso> operacionesDeEgreso;

    @Transient
    private List<OperacionDeIngreso> operacionDeIngreso;

    @OneToMany(mappedBy = "entidadJuridicaAsociada", cascade = {CascadeType.ALL})
    private List<EntidadBase> entidadesBase; //domain.entities.entidades que puede tener o no!

    @Transient
    private List<Criterio> listaCriterio;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public EntidadJuridica() { inicializar(); }

    public EntidadJuridica(String razonSocial, String cuit, DireccionPostal direccionPostal, String codigoInscripcionDefinitiva) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
        this.codigoInscripcionDefinitiva = codigoInscripcionDefinitiva;

        inicializar();
    }

    //-------------------------------------------------------------------------
                            //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.operacionesDeEgreso = new ArrayList<>();
    }

    public void agregarCriterio(Criterio criterio){ listaCriterio.add(criterio);}

    public void agregarCategoriaDeCriterio(CategoriaCriterio categoriaCriterio, Criterio criterio){
        //TODO preguntar si hace falta verificar si ya existe categoria. Lo mismo para criterio
        criterio.agregarCategoria(categoriaCriterio);
    }

    public void agregarOperacionDeEgresoAsociada(OperacionDeEgreso operacionDeEgreso){
        operacionesDeEgreso.add(operacionDeEgreso);
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public List<OperacionDeEgreso> getOperacionesDeEgreso() {
        return operacionesDeEgreso;
    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setTipoEntidadJuridica(TipoEntidadJuridica tipoEntidadJuridica) {
        this.tipoEntidadJuridica = tipoEntidadJuridica;
    }
}