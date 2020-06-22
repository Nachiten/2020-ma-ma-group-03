package Operaciones;

import CriterioOperacion.CategoriaCriterio;

import java.util.List;

public class Presupuesto {

    public float montoTotal;
    public List<Item> items;
    public DocumentoComercial documentoComercial;
    private List<CategoriaCriterio> listaCategoriaCriterio;


    public Presupuesto(float montoTotal, List<Item> items, DocumentoComercial documentoComercial) {
        this.montoTotal = montoTotal;
        this.items = items;
        this.documentoComercial = documentoComercial;
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

}