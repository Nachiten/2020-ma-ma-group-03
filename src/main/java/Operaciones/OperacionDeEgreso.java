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
    private final Date fecha;
    private final float montoTotal;
    private final MedioDePago medioDePago;
    private DocumentoComercial documentoComercial;
    private final List<Item> items;
    private List<Presupuesto> listaPresupuestos;
    private ValidadorTransparencia validadorTransparencia;
    private List<Usuario> revisores;
    private CriterioSeleccionProveedor criterioSeleccionProveedor;
    private List<CategoriaCriterio> listaCategoriaCriterio;
    private OperacionDeIngreso operacionDeIngreso;
    private int cantidadPresupuestosRequerida;

    public OperacionDeEgreso( Date fecha, float montoTotal, MedioDePago medioDePago, List<Item> items) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.items = items;
    }

    public OperacionDeEgreso( Date fecha, float montoTotal, MedioDePago medioDePago, DocumentoComercial documentoComercial, List<Item> items) {
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

    public void asociarCategoriaCriterio(CategoriaCriterio categoriaCriterio){ listaCategoriaCriterio.add(categoriaCriterio);}

//-------------------------------------------------------------------------
                            //SETTERS
//-------------------------------------------------------------------------

    public void setValidadorTransparencia(ValidadorTransparencia validadorTransparencia) {
        this.validadorTransparencia = validadorTransparencia;
    }

    public void setCriterioSeleccionProveedor(CriterioSeleccionProveedor criterioSeleccionProveedor) {
        this.criterioSeleccionProveedor = criterioSeleccionProveedor;
    }

    public void setListaCategoriaCriterio(List<CategoriaCriterio> listaCategoriaCriterio) {
        this.listaCategoriaCriterio = listaCategoriaCriterio;
    }

    public void agregarPresupuesto(Presupuesto unPresupuesto){
        listaPresupuestos.add(unPresupuesto);
    }

//-------------------------------------------------------------------------
                            //GETTERS
//-------------------------------------------------------------------------

    public List<Presupuesto> getListaPresupuestos() { return listaPresupuestos; }

    public int getCantidadPresupuestosRequerida() { return cantidadPresupuestosRequerida; }

    public float getMontoTotal() {
        return montoTotal;
    }

    public List<Item> getItems() {
        return items;
    }

    public DocumentoComercial getDocumentoComercial() {
        return documentoComercial;
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

}