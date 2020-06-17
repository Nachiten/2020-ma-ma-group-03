package Entidades;

import CriterioOperacion.CategoriaCriterio;
import CriterioOperacion.Criterio;
import Operaciones.OperacionDeIngreso;
import Operaciones.Presupuesto;
import TipoEntidadJuridica.TipoEntidadJuridica;
import Operaciones.OperacionDeEgreso;
import Usuarios.Usuario;
import ValidadorTransparencia.ValidadorTransparencia;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EntidadJuridica {

    private String razonSocial;
    private String nombreFicticio;
    private String cuit;
    private String direccionPostal;
    private String codigoInscripcionDefinitiva;
    private TipoEntidadJuridica tipoEntidadJuridica;
    private List<EntidadBase> entidadesBase; //entidades que puede tener o no!
    private List<OperacionDeEgreso> operacionesDeEgreso;
    private List<OperacionDeIngreso> operacionDeIngreso;
    private List<Criterio> listaCriterio;


    public void realizarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso){
        if(operacionDeEgreso.validarEgreso())
            operacionDeEgreso.realizarCompra();
    }

    public void crearCriterio(Criterio criterio){ listaCriterio.add(criterio);}

    public void crearCategoria(CategoriaCriterio categoriaCriterio, Criterio criterio){
        //TODO preguntar si hace falta verificar si ya existe categoria. Lo mismo para criterio
        criterio.agregarCategoria(categoriaCriterio);
    }

    public void asociarCategoriaAOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso, CategoriaCriterio categoriaCriterio){
        operacionDeEgreso.asociarCategoriaCriterio(categoriaCriterio);
    }

    public void asociarCategoriaAPresupuesto(Presupuesto presupuesto, CategoriaCriterio categoriaCriterio){
        presupuesto.asociarCategoriaCriterio(categoriaCriterio);
    }

}