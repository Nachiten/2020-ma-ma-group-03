package TipoEntidadJuridica;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TipoEntidadJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "nombreFicticio")
    private String nombreFicticio;
}
