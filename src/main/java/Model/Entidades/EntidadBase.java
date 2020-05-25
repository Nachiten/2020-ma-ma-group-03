package Model.Entidades;

public class EntidadBase extends Entidad {

    private String descripcion;
    private EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase(String nombreFicticio, String descripcion, EntidadJuridica entidadJuridicaAsociada) {
        this.nombreFicticio = nombreFicticio;
        this.descripcion = descripcion;
        this.entidadJuridicaAsociada = entidadJuridicaAsociada;
    }

}
