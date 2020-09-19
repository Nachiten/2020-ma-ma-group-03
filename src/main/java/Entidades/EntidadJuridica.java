package Entidades;

import ApiMercadoLibre.DireccionPostal;
import CriterioOperacion.CategoriaCriterio;
import CriterioOperacion.Criterio;
import Operaciones.OperacionDeIngreso;
import Persistencia.EntidadPersistente;
import TipoEntidadJuridica.TipoEntidadJuridica;
import Operaciones.OperacionDeEgreso;

import javax.persistence.*;
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

    @Transient
    private List<OperacionDeEgreso> operacionesDeEgreso;

    @Transient
    private List<OperacionDeIngreso> operacionDeIngreso;

    @OneToMany(mappedBy = "entidadJuridicaAsociada", cascade = {CascadeType.ALL})
    private List<EntidadBase> entidadesBase; //entidades que puede tener o no!

    @Transient
    private List<Criterio> listaCriterio;


    public EntidadJuridica() {
    }

    public EntidadJuridica(String razonSocial, String cuit, DireccionPostal direccionPostal, String codigoInscripcionDefinitiva) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
        this.codigoInscripcionDefinitiva = codigoInscripcionDefinitiva;
    }

    public void agregarCriterio(Criterio criterio){ listaCriterio.add(criterio);}

    public void agregarCategoriaDeCriterio(CategoriaCriterio categoriaCriterio, Criterio criterio){
        //TODO preguntar si hace falta verificar si ya existe categoria. Lo mismo para criterio
        criterio.agregarCategoria(categoriaCriterio);
    }
    public List<OperacionDeEgreso> getOperacionesDeEgreso() {
        return operacionesDeEgreso;
    }
}