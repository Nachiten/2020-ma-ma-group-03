package Model.Operaciones;

import java.util.List;

public class Presupuesto {

    public int montoTotal;
    public List<Item> items;
    public DocumentoComercial docCome;


    public Presupuesto(int montoTotal, List<Item> items, DocumentoComercial docCome) {
        this.montoTotal = montoTotal;
        this.items = items;
        this.docCome = docCome;
    }
}