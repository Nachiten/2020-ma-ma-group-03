package Operaciones;

import Usuarios.Usuario;
import Vendedor.Vendedor;

import java.util.Date;
import java.util.List;

public class OperacionDeEgreso {

    private Vendedor vendedor;
    private Date fecha;
    private float valorTotal;
    private MedioDePago medioDePago;
    private DocumentoComercial documentoComercial;
    private List<Item> items;
    private List<Usuario> revisores;

    public OperacionDeEgreso(Vendedor vendedor, Date fecha, float valorTotal, MedioDePago medioDePago, List<Item> items, String tipo) {
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.medioDePago = medioDePago;
        this.items = items;
    }

    public OperacionDeEgreso(Vendedor vendedor, Date fecha, float valorTotal, MedioDePago medioDePago, DocumentoComercial documentoComercial, List<Item> items) {
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.medioDePago = medioDePago;
        this.documentoComercial = documentoComercial;
        this.items = items;
    }

    public void adjuntarDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }
}