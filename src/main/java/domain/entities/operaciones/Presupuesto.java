package domain.entities.operaciones;

import criterioOperacion.CategoriaCriterio;
import domain.entities.entidades.EntidadJuridica;
import persistencia.EntidadPersistente;
import domain.entities.vendedor.Proveedor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "presupuesto")
public class Presupuesto extends EntidadPersistente {

    @ManyToOne
    private OperacionDeEgreso operacionAsociada;

    @Column (name = "montoTotal")
    private float montoTotal;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

    @Column (name = "fecha")
    private LocalDate fecha;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "documentoComercial_id", referencedColumnName = "id")
    private DocumentoComercial documentoComercial;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CategoriaCriterio> listaCategoriaCriterio;

    @ManyToOne (cascade = CascadeType.ALL)
    private Proveedor proveedorAsociado;

    @ManyToOne(cascade = CascadeType.ALL)
    private EntidadJuridica entidadJuridica;
    //-------------------------------------------------------------------------
                                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Presupuesto() {
    }

    public Presupuesto(float montoTotal, List<Item> items, OperacionDeEgreso operacionAsociada) {
        this.montoTotal = montoTotal;
        this.items = items;
        this.operacionAsociada = operacionAsociada;
    }

    public Presupuesto(float montoTotal, List<Item> items, OperacionDeEgreso operacionAsociada, LocalDate fecha, Proveedor proveedorAsociado, EntidadJuridica entidadJuridica) {
        this.montoTotal = montoTotal;
        this.items = items;
        this.fecha = fecha;
        this.proveedorAsociado = proveedorAsociado;
        this.operacionAsociada = operacionAsociada;
        this.entidadJuridica = entidadJuridica;
    }

    //-------------------------------------------------------------------------
                                    //METODOS
    //-------------------------------------------------------------------------

    public void asociarCategoriaCriterio(CategoriaCriterio categoriaCriterio){ listaCategoriaCriterio.add(categoriaCriterio);}

    //-------------------------------------------------------------------------
                                    //GETTERS
    //-------------------------------------------------------------------------

    public float getMontoTotal() {
        return montoTotal;
    }

    public List<Item> getItems() {
        return items;
    }

    public DocumentoComercial getDocumentoComercial() {
        return documentoComercial;
    }

    public Proveedor getProveedorAsociado() {
        return proveedorAsociado;
    }

    public List<CategoriaCriterio> getListaCategoriaCriterio() {
        return listaCategoriaCriterio;
    }
//-------------------------------------------------------------------------
                                    //SETTERS
    //-------------------------------------------------------------------------


    public void setOperacionAsociada(OperacionDeEgreso operacionAsociada) {
        this.operacionAsociada = operacionAsociada;
    }

    public void setDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setListaCategoriaCriterio(List<CategoriaCriterio> listaCategoriaCriterio) {
        this.listaCategoriaCriterio = listaCategoriaCriterio;
    }
}