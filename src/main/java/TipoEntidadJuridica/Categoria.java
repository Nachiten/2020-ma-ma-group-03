package TipoEntidadJuridica;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria extends EntidadPersistente {

    @Column(name= "nombre")
    private String nombre;

    @Column(name= "ventasAnualesMaximas")
    private int ventasAnualesMaximas;

    @Column(name= "cantidadPersonalMaximo")
    private int cantidadPersonalMaximo;

    @ManyToOne (optional = false)
    @JoinColumn(name = "sectorAsociado_id")
    private Sector sectorAsociado;

    //Agregué el constructor


    public Categoria() {
    }

    public Categoria(String nombre, int ventasAnualesMaximas, int cantidadPersonalMaximo) {
        this.nombre = nombre;
        this.ventasAnualesMaximas = ventasAnualesMaximas;
        this.cantidadPersonalMaximo = cantidadPersonalMaximo;
    }

    public boolean cumploConCategoria(int ventasAnuales, int cantidadPersonal){
        return ventasAnuales <= ventasAnualesMaximas && cantidadPersonal <= cantidadPersonalMaximo;
    }

    public String getNombre(){
        return nombre;
    }
}
