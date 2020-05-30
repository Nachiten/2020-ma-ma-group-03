package Entidades;

import Categoria.Categoria;
import Operaciones.OperacionDeEgreso;

import java.util.List;

public class EntidadJuridica {

    private String razonSocial;
    private String nombreFicticio;
    private String cuit;
    private String direccionPostal;
    private String codigoInscripcionDefinitiva;
    private Categoria categoria;

    //en vez de tener una lista general con todas las operaciones... Tenemos una por cada operacion debido a lo planteado en el enunciado:
    //"Se debe llevar registro de todas las operaciones de egresos de fondos a través de diversos medios de pagos."

    private List<OperacionDeEgreso> operacionesesDeEgreso;

    private List<EntidadJuridica> entidadesJuridicas; //con estas entidades va a realizar su comportamiento.
    private List<EntidadBase> entidadesBase; //entidades que puede tener o no!

    public void realizarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso) {
        categoria.realizarOperacionDeEgreso(operacionDeEgreso);
    }

}