package Operaciones;

import java.util.List;

public class Presupuesto {

    public float montoTotal;
    public List<Item> items;
    public DocumentoComercial documentoComercial;


    public Presupuesto(float montoTotal, List<Item> items, DocumentoComercial docCome) {
        this.montoTotal = montoTotal;
        this.items = items;
        this.documentoComercial = docCome;
    }

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