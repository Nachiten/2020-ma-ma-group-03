package Operaciones;

import Usuarios.Usuario;
import ValidadorTransparencia.ValidadorTransparencia;
import Vendedor.Proveedor;

import java.util.Date;
import java.util.List;

public class OperacionDeEgreso {

    private Usuario usuario;
    private Proveedor proveedor;
    private Date fecha;
    private float montoTotal;
    private MedioDePago medioDePago;
    private DocumentoComercial documentoComercial;
    private List<Item> items;
    private int presupuestosNecesarios;
    private List<Presupuesto> presupuestos;
    private ValidadorTransparencia validadorTransparencia;
    private List<Usuario> revisores;

    public OperacionDeEgreso(Proveedor proveedor, Date fecha, float montoTotal, MedioDePago medioDePago, List<Item> items, String tipo, int presupuestosNecesarios) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.items = items;
        this.presupuestosNecesarios = presupuestosNecesarios;
    }

    public OperacionDeEgreso(Proveedor proveedor, Date fecha, float montoTotal, MedioDePago medioDePago, DocumentoComercial documentoComercial, int presupuestosNecesarios, List<Item> items) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.documentoComercial = documentoComercial;
        this.presupuestosNecesarios = presupuestosNecesarios;
        this.items = items;
    }

    public void adjuntarDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public boolean validarEgreso() {
        return validadorTransparencia.validarEgreso(this);
    }

    public void realizarCompra(){
        revisores.add(usuario);
    }

    public void agregarPresupuesto(Presupuesto presupuesto){
        presupuestos.add(presupuesto);
    }

//-------------------------------------------------------------------------
                            //GETTERS
//-------------------------------------------------------------------------

    public int getPresupuestosNecesarios() {
        return presupuestosNecesarios;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getRevisores() {
        return revisores;
    }
}