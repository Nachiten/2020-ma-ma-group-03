package Entidades;

public class EntidadBase {

    public String nombreFicticio;
    private String descripcion;
    private EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase(String nombreFicticio, String descripcion, EntidadJuridica entidadJuridicaAsociada) {
        this.nombreFicticio = nombreFicticio;
        this.descripcion = descripcion;
        this.entidadJuridicaAsociada = entidadJuridicaAsociada;
    }

}
