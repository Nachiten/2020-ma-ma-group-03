package Operaciones;

import CriterioOperacion.CategoriaCriterio;
import Vendedor.Proveedor;

import java.util.List;

public class Presupuesto {

    private float montoTotal;
    private List<Item> items;
    private DocumentoComercial documentoComercial;
    private List<CategoriaCriterio> listaCategoriaCriterio;
    private Proveedor proveedorAsociado;

    public Presupuesto(float montoTotal, List<Item> items) {
        this.montoTotal = montoTotal;
        this.items = items;
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