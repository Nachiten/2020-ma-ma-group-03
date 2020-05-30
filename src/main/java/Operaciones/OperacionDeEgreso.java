package Operaciones;

import Categoria.OrganizacionSectorSocial;
import Vendedor.Vendedor;

import java.util.Date;
import java.util.List;

public class OperacionDeEgreso {

    private Vendedor vendedor;
    private Date fechaOperacion;
    private float valorTotal;
    private MedioDePago medioDePago;
    private DocumentoComercial documentoComercial;
    private List<Item> itemsOP;

    public OperacionDeEgreso(Vendedor vendedor, Date fechaOperacion, float valorTotal, MedioDePago medioDePago, List<Item> itemsOP, String tipo) {
        this.vendedor = vendedor;
        this.fechaOperacion = fechaOperacion;
        this.valorTotal = valorTotal;
        this.medioDePago = medioDePago;
        this.itemsOP = itemsOP;
    }

    public OperacionDeEgreso(Vendedor vendedor, Date fechaOperacion, float valorTotal, MedioDePago medioDePago, DocumentoComercial documentoComercial, List<Item> itemsOP) {
        this.vendedor = vendedor;
        this.fechaOperacion = fechaOperacion;
        this.valorTotal = valorTotal;
        this.medioDePago = medioDePago;
        this.documentoComercial = documentoComercial;
        this.itemsOP = itemsOP;
    }

    public void adjuntarDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

}