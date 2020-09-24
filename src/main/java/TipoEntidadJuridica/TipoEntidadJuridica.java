package TipoEntidadJuridica;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TipoEntidadJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "nombreFicticio")
    public String nombreFicticio;
}
