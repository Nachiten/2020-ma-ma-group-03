package Operaciones;

import CriterioOperacion.CategoriaCriterio;
import Usuarios.Usuario;
import CriterioSeleccionProveedor.CriterioSeleccionProveedor;
import ValidadorTransparencia.ValidadorTransparencia;

import java.util.Date;
import java.util.List;

public class OperacionDeEgreso {

    private Usuario usuario;
    private final Date fecha;
    private final float montoTotal;
    private final MedioDePago medioDePago;
    private DocumentoComercial documentoComercial;
    private final List<Item> items;
    private List<Presupuesto> presupuestos;
    private ValidadorTransparencia validadorTransparencia;
    private List<Usuario> revisores;
    private CriterioSeleccionProveedor criterioSeleccionProveedor;
    private List<CategoriaCriterio> listaCategoriaCriterio;
    private OperacionDeIngreso operacionDeIngreso;
    private int cantidadPresupuestosRequerida;
    private Boolean soyValida = false;
    private int cantidadDeVecesValidada = 0;

    public OperacionDeEgreso(Date fecha, float montoTotal, MedioDePago medioDePago, List<Item> items) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.items = items;
    }

    public void adjuntarDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public boolean validarEgreso() {
        return validadorTransparencia.validarEgreso(this);
    }

    public void asociarCategoriaCriterio(CategoriaCriterio categoriaCriterio){ listaCategoriaCriterio.add(categoriaCriterio);}

    public void asociarOperacionDeIngreso(OperacionDeIngreso unaOperacionDeIngreso){ this.operacionDeIngreso = unaOperacionDeIngreso; }


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
        presupuestos.add(unPresupuesto);
    }

    public void soyValida(){
        this.soyValida = true;
    }

    public void fuiValidada(){
        this.cantidadDeVecesValidada ++;
    }

//-------------------------------------------------------------------------
                            //GETTERS
//-------------------------------------------------------------------------


    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

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

    public Boolean esValida(){
        return soyValida;
    }

    public int getCantidadDeVecesValidada() {
        return cantidadDeVecesValidada;
    }
}