package Model.Operaciones;

public class MedioDePago {

    public TipoMedioPago tipo;
    public int numero;

    //Preguntar que tan bueno esta hacer multiples constructores.

    public MedioDePago() {
        this.tipo = TipoMedioPago.EFECTIVO;
    }

    public MedioDePago(TipoMedioPago tipo, int numero) {
        this.tipo = tipo;
        this.numero = numero;
    }


    public void registrarPago() {
        // TODO implement here
    }

}