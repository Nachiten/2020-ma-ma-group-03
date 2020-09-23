package Operaciones;

import Persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "operacionDeIngreso")
public class OperacionDeIngreso extends EntidadPersistente {
    @Column (name = "descripcion")
    private String descripcion;
    @Column (name = "montoTotal")
    private float montoTotal;

    public OperacionDeIngreso(){

    }

    public OperacionDeIngreso(String descripcion, float montoTotal) {
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
    }
}
