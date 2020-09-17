package Operaciones;

import Persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item extends EntidadPersistente {

    @Column (name = "descripcion")
    private String descripcion;
    @Column (name = "valor")
    private float valor;

    public Item(String descripcion, float valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }
}