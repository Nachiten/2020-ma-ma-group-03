package Operaciones;

import CriterioOperacion.CategoriaCriterio;
import Persistencia.EntidadPersistente;
import Vendedor.Proveedor;

import javax.persistence.*;
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
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "documentoComercial_id", referencedColumnName = "id")
    private DocumentoComercial documentoComercial;
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CategoriaCriterio> listaCategoriaCriterio;
    @ManyToOne (cascade = CascadeType.ALL)
    private Proveedor proveedorAsociado;

    public Presupuesto(float montoTotal, List<Item> items, OperacionDeEgreso operacionAsociada) {
        this.montoTotal = montoTotal;
        this.items = items;
        this.operacionAsociada = operacionAsociada;
    }

    public void asociarCategoriaCriterio(CategoriaCriterio categoriaCriterio){ listaCategoriaCriterio.add(categoriaCriterio);}

    public float getMontoTotal() {
        return montoTotal;
    }

    public List<Item> getItems() {
        return items;
    }

    public DocumentoComercial getDocumentoComercial() {
        return documentoComercial;
    }

    public void setDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }
}