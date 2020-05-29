package Entidades;

import Categoria.Categoria;
import Operaciones.OperacionDeEgreso;

import java.util.List;

public class EntidadJuridica extends Entidad {

    private String razonSocial;
    private String cuit;
    private String direccionPostal;
    private String codigoInscripcionDefinitiva;
    private Categoria categoria;

//en vez de tener una lista general con todas las operaciones... Tenemos una por cada operacion debido a lo planteado en el enunciado:
//"Se debe llevar registro de todas las operaciones de egresos de fondos a través de diversos medios de pagos."

    private List<OperacionDeEgreso> operacionesConEfectivo;
    private List<OperacionDeEgreso> operacionesConTarjetaDeCredito;
    private List<OperacionDeEgreso> operacionesConTarjetaDeDebito;
    private List<OperacionDeEgreso> operacionesConCheque;

    private List<EntidadJuridica> entidadesJuridica; //con estas entidades va a realizar su comportamiento.
    private List<EntidadBase> entidadesBase; //entidades que puede tener o no!

}