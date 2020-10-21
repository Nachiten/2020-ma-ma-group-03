package domain.entities.operaciones;

import criterioOperacion.CategoriaCriterio;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.usuarios.Usuario;
import criterioSeleccionProveedor.CriterioSeleccionProveedor;
import validadorTransparencia.ValidadorTransparencia;
import domain.entities.vendedor.Proveedor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "operacionDeEgreso")
public class OperacionDeEgreso implements GestorDeRevisores {

    @Id
    @GeneratedValue
    private int IDOperacion;

    @ManyToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column (name = "fecha")
    private LocalDate fecha;

    @Column (name = "montoTotal")
    private float montoTotal;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "medioDePago_id")
    private MedioDePago medioDePago;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "documentoComercial_id")
    private DocumentoComercial documentoComercial;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

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
    private boolean soyValida;

    @Transient // No se persiste
    private int cantidadDeVecesValidada = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    private Proveedor proveedorAsociado;

    @ManyToOne (optional = false)
    @JoinColumn(name = "entidadJuridicaAsociada_id")
    private EntidadJuridica entidadJuridicaAsociada;

    @Transient
    private int operacioDeIngresoId;

    @Transient
    private boolean fueVinculada;



    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public OperacionDeEgreso() { inicializar();}

    public OperacionDeEgreso(LocalDate fecha, float montoTotal, MedioDePago medioDePago, List<Item> items) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.items = items;

        inicializar();
    }

    public OperacionDeEgreso(int idOperacion, LocalDate fecha, float montoTotal) {
        this.IDOperacion = idOperacion;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        inicializar();
    }

    public OperacionDeEgreso(LocalDate fecha, float montoTotal) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        inicializar();
    }


    //-------------------------------------------------------------------------
                                //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.soyValida = false;
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
        revisor.agregarOperacionDeEgreso(this);
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

    public void agregarPresupuesto(Presupuesto unPresupuesto){ presupuestos.add(unPresupuesto); }

    public void soyValida(){ this.soyValida = true; }

    public void fuiValidada(){ this.cantidadDeVecesValidada ++; }


    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setCantidadPresupuestosRequerida(int cantidadPresupuestosRequerida) { this.cantidadPresupuestosRequerida = cantidadPresupuestosRequerida; }

    public void setValidadorTransparencia(ValidadorTransparencia validadorTransparencia) {
        this.validadorTransparencia = validadorTransparencia;
    }

    public void setCriterioSeleccionProveedor(CriterioSeleccionProveedor criterioSeleccionProveedor) {
        this.criterioSeleccionProveedor = criterioSeleccionProveedor;
    }

    public void setListaCategoriaCriterio(List<CategoriaCriterio> listaCategoriaCriterio) {
        this.listaCategoriaCriterio = listaCategoriaCriterio;
    }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public void setDocumentoComercial(DocumentoComercial documentoComercial) { this.documentoComercial = documentoComercial; }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setPresupuestos(List<Presupuesto> presupuestos) {
        this.presupuestos = presupuestos;
    }

    public void setEntidadJuridicaAsociada(EntidadJuridica entidadJuridicaAsociada) { this.entidadJuridicaAsociada = entidadJuridicaAsociada; }

    public void setProveedorAsociado(Proveedor proveedorAsociado) {
        this.proveedorAsociado = proveedorAsociado;
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public List<Presupuesto> getPresupuestos() { return presupuestos; }

    public LocalDate getFecha() { return fecha;}

    public int getIDOperacion() { return IDOperacion; }

    public int getCantidadPresupuestosRequerida() { return cantidadPresupuestosRequerida; }

    public float getMontoTotal() { return montoTotal; }

    public List<Item> getItems() { return items; }

    public DocumentoComercial getDocumentoComercial() { return documentoComercial; }

    public Usuario getUsuario() { return usuario; }

    public List<Usuario> getRevisores() { return revisores; }

    public CriterioSeleccionProveedor getCriterioSeleccionProveedor() { return criterioSeleccionProveedor; }

    public Boolean esValida(){ return soyValida; }

    public int getCantidadDeVecesValidada() { return cantidadDeVecesValidada; }
}