package domain.entities.operaciones;

import domain.entities.apiMercadoLibre.Moneda;
import domain.entities.entidades.EntidadJuridica;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "operacionDeIngreso")
public class OperacionDeIngreso  {

    @Id
    @GeneratedValue
    private int id;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "montoTotal")
    private float montoTotal;

    @ManyToOne (cascade = CascadeType.ALL)
    private Moneda moneda;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column (name = "periodoAceptacion")
    private LocalDate periodoAceptacion;

    @ManyToOne
    private EntidadJuridica entidadJuridicaAsociada;

    @OneToMany (cascade = CascadeType.ALL)
    private List<OperacionDeEgreso> operacionesDeEgresoVinculadas;

    @Column (name = "montoSinVincular")
    private float montoSinVincular;


    @Transient
    private LocalDate fechaMinima;

    @Transient
    private LocalDate fechaMaxima;

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public OperacionDeIngreso(){
        inicializar();
    }

    public OperacionDeIngreso(String descripcion, float montoTotal, LocalDate fecha, Moneda moneda) {
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
        this.fecha = fecha;
        this.moneda = moneda;
        inicializar();
    }

    public OperacionDeIngreso(String descripcion, float montoTotal, LocalDate fecha) {
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
        this.fecha = fecha;
        inicializar();
    }


    private void inicializar(){
        this.montoSinVincular = montoTotal;
        this.operacionesDeEgresoVinculadas = new ArrayList<>();

    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setFechaMinima(LocalDate fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public void setFechaMaxima(LocalDate fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public void setEntidadJuridicaAsociada(EntidadJuridica entidadJuridicaAsociada) {
        this.entidadJuridicaAsociada = entidadJuridicaAsociada;
        this.entidadJuridicaAsociada.agregarOperacionDeIngresoAsociada(this);
    }

    public void setPeriodoAceptacion(LocalDate periodoAceptacion) {
        this.periodoAceptacion = periodoAceptacion;
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public LocalDate getFecha() {
        return fecha;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public int getId(){
        return id;
    }

    public EntidadJuridica getEntidadJuridicaAsociada() {
        return entidadJuridicaAsociada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getPeriodoAceptacion() {
        return periodoAceptacion;
    }
}
