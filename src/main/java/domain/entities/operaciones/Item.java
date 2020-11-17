package domain.entities.operaciones;

import domain.entities.usuarios.TipoUsuario;
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
    @Column (name = "valor")
    private float valor;
    @Column (name = "cantidad")
    private float cantidad;

    //-------------------------------------------------------------------------
                                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Item() { }

    public Item(TipoItem tipo,String descripcion, float valor, int cantidad) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    //para mantener antiguos tests
    public Item(String descripcion, float valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getValor() {
        return valor;
    }

    public boolean soyIgualA(Item unItem){
        return this.getDescripcion().equals(unItem.getDescripcion()) &&
               this.getValor() == unItem.getValor();
    }
}