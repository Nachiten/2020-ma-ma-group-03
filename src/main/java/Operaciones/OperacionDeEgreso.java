package Operaciones;

import CriterioOperacion.CategoriaCriterio;
import Usuarios.Usuario;
import CriterioSeleccionProveedor.CriterioSeleccionProveedor;
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
    private List<Presupuesto> presupuestos;
    private ValidadorTransparencia validadorTransparencia;
    private List<Usuario> revisores;
    private CriterioSeleccionProveedor criterioSeleccionProveedor;
    private List<CategoriaCriterio> listaCategoriaCriterio;
    private List<OperacionDeIngreso> listaOperacionDeIngreso;//TODO preguntar si va una lista...

    public OperacionDeEgreso(Proveedor proveedor, Date fecha, float montoTotal, MedioDePago medioDePago, List<Item> items) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.items = items;
    }

    public OperacionDeEgreso(Proveedor proveedor, Date fecha, float montoTotal, MedioDePago medioDePago, DocumentoComercial documentoComercial, List<Item> items) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.documentoComercial = documentoComercial;
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

    public void asociarCategoriaCriterio(CategoriaCriterio categoriaCriterio){ listaCategoriaCriterio.add(categoriaCriterio);}

//-------------------------------------------------------------------------
                            //GETTERS
//-------------------------------------------------------------------------

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

    public CriterioSeleccionProveedor getCriterioSeleccionProveedor() {
        return criterioSeleccionProveedor;
    }

    public void setListaCategoriaCriterio(List<CategoriaCriterio> listaCategoriaCriterio) {
        this.listaCategoriaCriterio = listaCategoriaCriterio;
    }
}