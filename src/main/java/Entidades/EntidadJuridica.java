package Entidades;

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
    private List<EntidadBase> entidadesBase; //entidades que puede tener o no!
    private List<OperacionDeEgreso> operacionesDeEgreso;


    public void realizarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso){
    }
}