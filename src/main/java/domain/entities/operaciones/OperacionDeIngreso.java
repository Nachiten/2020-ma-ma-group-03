package domain.entities.operaciones;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "operacionDeIngreso")
public class OperacionDeIngreso  {

    @Id
    private int id;

    @Column (name = "descripcion")
    private String descripcion;
    @Column (name = "montoTotal")
    private float montoTotal;

    @Transient
    private LocalDate fecha;
    @Transient
    private List<OperacionDeEgreso> operacionesDeEgresoVinculadas;
    private float montoSinVincular;
    private LocalDate fechaMinima;
    private LocalDate fechaMaxima;


    public OperacionDeIngreso(){

    }

    public OperacionDeIngreso(String descripcion, float montoTotal) {
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
    }

    public OperacionDeIngreso(int id, String descripcion, LocalDate fecha, float montoTotal) {
        this.id = id;
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
        this.fecha = fecha;
        this.montoSinVincular = montoTotal;
        this.operacionesDeEgresoVinculadas = new ArrayList<>();
    }

    public void setFechaMinima(LocalDate fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public void setFechaMaxima(LocalDate fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public float getMontoTotal() {
        return montoTotal;
    }
    public int getId(){
        return id;
    }

}
