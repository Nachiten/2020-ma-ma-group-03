package domain.entities.operaciones;

import org.hibernate.engine.transaction.synchronization.internal.SynchronizationCallbackCoordinatorNonTrackingImpl;
import persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item extends EntidadPersistente {

    @Column (name = "tipoItem")
    @Enumerated(value = EnumType.STRING)
    private TipoItem tipo;
    @Column (name = "descripcion")
    private String descripcion;
    @Column (name = "precioUnitario")
    private float precioUnitario;

    @Column (name = "precioTotal")
    private float precioTotal;
    @Column (name = "cantidad")
    private int cantidad;

    //-------------------------------------------------------------------------
                                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Item() { }

    public Item(TipoItem tipo,String descripcion, float precioUnitario, int cantidad) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.precioTotal = cantidad * precioUnitario;
    }

    //para mantener antiguos tests
    public Item(String descripcion, float valor) {
        this.descripcion = descripcion;
        this.precioUnitario = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public boolean soyIgualA(Item unItem){
        return this.getDescripcion().equals(unItem.getDescripcion()) &&
               this.getPrecioUnitario() == unItem.getPrecioUnitario();
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }
}