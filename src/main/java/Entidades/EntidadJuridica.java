package Entidades;

import CriterioOperacion.CategoriaCriterio;
import CriterioOperacion.Criterio;
import Operaciones.OperacionDeIngreso;
import Operaciones.Presupuesto;
import TipoEntidadJuridica.TipoEntidadJuridica;
import Operaciones.OperacionDeEgreso;

import java.util.List;

public class EntidadJuridica {

    private String razonSocial;
    private String nombreFicticio;
    private String cuit;
    private String direccionPostal;
    private String codigoInscripcionDefinitiva;
    private TipoEntidadJuridica tipoEntidadJuridica;
    private List<OperacionDeEgreso> operacionesDeEgreso;
    private List<OperacionDeIngreso> operacionDeIngreso;
    private List<EntidadBase> entidadesBase; //entidades que puede tener o no!
    private List<Criterio> listaCriterio;

    public EntidadJuridica(String razonSocial, String nombreFicticio, String cuit, String direccionPostal, String codigoInscripcionDefinitiva,
                           List<EntidadBase> entidadesBase, List<OperacionDeEgreso> operacionesDeEgreso, List<OperacionDeIngreso> operacionDeIngreso,
                           List<Criterio> listaCriterio) {
        this.razonSocial = razonSocial;
        this.nombreFicticio = nombreFicticio;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
        this.codigoInscripcionDefinitiva = codigoInscripcionDefinitiva;
        this.entidadesBase = entidadesBase;
        this.operacionesDeEgreso = operacionesDeEgreso;
        this.operacionDeIngreso = operacionDeIngreso;
        this.listaCriterio = listaCriterio;
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