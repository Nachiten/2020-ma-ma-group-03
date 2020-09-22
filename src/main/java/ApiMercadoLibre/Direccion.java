package ApiMercadoLibre;


import Persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "direccion")
public class Direccion extends EntidadPersistente {
    @Column (name = "calle")
    String calle;
    @Column (name = "numero")
    int numero;
    @Column (name = "piso")
    int piso;
    @Column (name = "dpto")
    String dpto;

    public Direccion(){

    }

    public Direccion(String calle, int numero, int piso, String dpto){
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.dpto = dpto;
    }
}
