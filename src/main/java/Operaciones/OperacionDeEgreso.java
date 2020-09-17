package Operaciones;

import CriterioOperacion.CategoriaCriterio;
import Usuarios.Usuario;
import CriterioSeleccionProveedor.CriterioSeleccionProveedor;
import ValidadorTransparencia.ValidadorTransparencia;
import Vendedor.Proveedor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "operacionDeEgreso")
public class OperacionDeEgreso implements GestorDeRevisores {

    @Id
    @GeneratedValue
    private int IDOperacion;

    @ManyToOne (cascade = {CascadeType.ALL})
    private Usuario usuario;

    @Column (name = "fecha")
    private final Date fecha;

    @Column (name = "montoTotal")
    private final float montoTotal;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "medioDePago_id")
    private final MedioDePago medioDePago;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "documentoComercial_id")
    private DocumentoComercial documentoComercial;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Item> items;

    // Error | Genera una tabla intermedia como si fuera many to many
    @OneToMany (mappedBy = "operacionAsociada", cascade = CascadeType.ALL)
    private List<Presupuesto> presupuestos;

    @Transient // No se persiste
    private ValidadorTransparencia validadorTransparencia;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Usuario> revisores;

    @Transient // No se persiste
    private CriterioSeleccionProveedor criterioSeleccionProveedor;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<CategoriaCriterio> listaCategoriaCriterio;

    @ManyToOne (cascade = CascadeType.ALL)
    private OperacionDeIngreso operacionDeIngreso;

    @Column (name = "cantidadPresupuestosRequerida")
    private int cantidadPresupuestosRequerida;

    @Transient // No se persiste
    private boolean soyValida = false;

    @Transient // No se persiste
    private int cantidadDeVecesValidada = 0;

    @ManyToOne
    private Proveedor proveedorAsociado;

    public OperacionDeEgreso(Date fecha, float montoTotal, MedioDePago medioDePago, List<Item> items) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.items = items;

        this.revisores = new ArrayList<>();
        this.presupuestos = new ArrayList<>();
    }

    public void adjuntarDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public boolean validarEgreso() {
        return validadorTransparencia.validarEgreso(this);
    }

    public void asociarCategoriaCriterio(CategoriaCriterio categoriaCriterio){ listaCategoriaCriterio.add(categoriaCriterio);}

    public void asociarOperacionDeIngreso(OperacionDeIngreso unaOperacionDeIngreso){ this.operacionDeIngreso = unaOperacionDeIngreso; }

    public void agregarRevisor(Usuario revisor){
        revisores.add(revisor);
    }

    public void removerRevisor(Usuario revisor) {
        int i = revisores.indexOf(revisor);
        if (i >= 0) {
            revisores.remove(i);
        }
    }

    public void notificarRevisor() {
        for (Object revisores : revisores) {
            Revisor revisor = (Revisor) revisores;
            revisor.actualizarRevisor(this);
        }
    }

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

    public void setCantidadPresupuestosRequerida(int cantidadPresupuestosRequerida) { this.cantidadPresupuestosRequerida = cantidadPresupuestosRequerida; }

    //-------------------------------------------------------------------------
                            //GETTERS
//-------------------------------------------------------------------------


    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public Date getFecha() { return fecha;}

    public int getIDOperacion() { return IDOperacion; }

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