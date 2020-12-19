package domain.entities.operaciones;

import criterioOperacion.CategoriaCriterio;
import criterioSeleccionProveedor.CriterioProveedorMenorValor;
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
    private int idOperacion;

    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "montoTotal")
    private float montoTotal;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "medioDePago_id")
    private MedioDePago medioDePago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documentoComercial_id")
    private DocumentoComercial documentoComercial;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

    // Error | Genera una tabla intermedia como si fuera many to many
    @OneToMany(cascade = CascadeType.ALL)
    private List<Presupuesto> presupuestos;

    @Transient // No se persiste
    private ValidadorTransparencia validadorTransparencia;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Usuario> revisores;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "criterioProveedor_id")
    private CriterioProveedorMenorValor criterioProveedorMenorValor;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<CategoriaCriterio> listaCategoriaCriterio;

    @ManyToOne(cascade = CascadeType.ALL)
    private OperacionDeIngreso operacionDeIngreso;

    @Column(name = "cantidadPresupuestosRequerida")
    private int cantidadPresupuestosRequerida;

    @Transient // No se persiste
    private boolean soyValida;

    @Transient // No se persiste
    private int cantidadDeVecesValidada = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    private Proveedor proveedorAsociado;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "entidadJuridicaAsociada_id")
    private EntidadJuridica entidadJuridicaAsociada;

    @Column (name = "hayDocumentoGuardado")
    private boolean hayDocumentoGuardado;

    @Transient
    private int operacionDeIngresoId;

    @Transient
    private boolean fueVinculada;

    //-------------------------------------------------------------------------
    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public OperacionDeEgreso() {
        inicializar();
    }

    public OperacionDeEgreso(LocalDate fecha, float montoTotal, MedioDePago medioDePago, List<Item> items) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.medioDePago = medioDePago;
        this.items = items;

        inicializar();
    }

    public OperacionDeEgreso(int idOperacion, LocalDate fecha, float montoTotal) {
        this.idOperacion = idOperacion;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        inicializar();
    }

    public OperacionDeEgreso(LocalDate fecha, float montoTotal) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        inicializar();
    }

    public OperacionDeEgreso(int idOperacion, LocalDate fecha, float montoTotal, OperacionDeIngreso operacionDeIngreso, boolean fueVinculada) {
        this.idOperacion = idOperacion;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.operacionDeIngreso = operacionDeIngreso;
        this.operacionDeIngresoId = operacionDeIngreso.getId();
        this.fueVinculada = fueVinculada;
        inicializar();
    }

    public OperacionDeEgreso(int idOperacion, LocalDate fecha, float montoTotal, int idOperacionDeIngreso, boolean fueVinculada) {
        this.idOperacion = idOperacion;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.operacionDeIngresoId = idOperacionDeIngreso;
        this.fueVinculada = fueVinculada;
        inicializar();
    }

    public OperacionDeEgreso(int idOperacion, LocalDate fecha, float montoTotal, boolean fueVinculada) {
        this.idOperacion = idOperacion;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.fueVinculada = fueVinculada;
        inicializar();
    }

    public OperacionDeEgreso(LocalDate fecha, MedioDePago medio, List<Item> items, int cantidadPresupuestos, float montoTotal, List<CategoriaCriterio> listaDeCategorias, EntidadJuridica entidadJuridicaAsociada) {
        this.fecha = fecha;
        this.medioDePago = medio;
        this.items = items;
        this.cantidadPresupuestosRequerida = cantidadPresupuestos;
        this.montoTotal = montoTotal;
        this.listaCategoriaCriterio = listaDeCategorias;
        this.entidadJuridicaAsociada = entidadJuridicaAsociada;
        inicializar();
    }

    public OperacionDeEgreso(Usuario usuario, LocalDate fecha, MedioDePago medio ,List<Item> items,int cantidadPresupuestos,float montoTotal, List<CategoriaCriterio> listaDeCategorias,EntidadJuridica entidadJuridicaAsociada,Proveedor proveedor) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.medioDePago = medio;
        this.items = items;
        this.cantidadPresupuestosRequerida = cantidadPresupuestos;
        this.montoTotal = montoTotal;
        this.listaCategoriaCriterio = listaDeCategorias;
        this.entidadJuridicaAsociada = entidadJuridicaAsociada;
        this.proveedorAsociado = proveedor;
        inicializar();
    }



    //-------------------------------------------------------------------------
                                //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.soyValida = false;
        this.hayDocumentoGuardado = false;
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

    public void agregarPresupuesto(Presupuesto unPresupuesto){
        presupuestos.add(unPresupuesto);
        unPresupuesto.setOperacionAsociada(this);
    }

    public void soyValida(){ this.soyValida = true; }

    public void fuiValidada(){ this.cantidadDeVecesValidada ++; }


    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------


    public void setHayDocumentoGuardado(boolean hayDocumentoGuardado) {
        this.hayDocumentoGuardado = hayDocumentoGuardado;
    }

    public void setCantidadPresupuestosRequerida(int cantidadPresupuestosRequerida) { this.cantidadPresupuestosRequerida = cantidadPresupuestosRequerida; }

    public void setValidadorTransparencia(ValidadorTransparencia validadorTransparencia) {
        this.validadorTransparencia = validadorTransparencia;
    }

    public void setCriterioSeleccionProveedor(CriterioProveedorMenorValor criterioProveedorMenorValor) {
        this.criterioProveedorMenorValor = criterioProveedorMenorValor;
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

    public void setOperacionDeIngresoId(int operacionDeIngresoId) { this.operacionDeIngresoId = operacionDeIngresoId; }

    public void setFueVinculada(boolean fueVinculada) {
        this.fueVinculada = fueVinculada;
    }

    public void setOperacionDeIngreso(OperacionDeIngreso operacionDeIngreso) {
        this.operacionDeIngreso = operacionDeIngreso;
    }
//-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public boolean getHayDocumentoGuardado(){
        return hayDocumentoGuardado;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public LocalDate getFecha() { return fecha;}

    public int getIdOperacion() { return idOperacion; }

    public int getCantidadPresupuestosRequerida() { return cantidadPresupuestosRequerida; }

    public float getMontoTotal() { return montoTotal; }

    public List<Item> getItems() { return items; }

    public DocumentoComercial getDocumentoComercial() { return documentoComercial; }

    public Usuario getUsuario() { return usuario; }

    public List<Usuario> getRevisores() { return revisores; }

    public CriterioSeleccionProveedor getCriterioProveedorMenorValor() { return criterioProveedorMenorValor; }

    public MedioDePago getMedioDePago() { return medioDePago; }

    public Boolean esValida(){ return soyValida; }

    public int getCantidadDeVecesValidada() { return cantidadDeVecesValidada; }

    public int getEntidadJuridicaAsociada_id(){
        return entidadJuridicaAsociada.getId();
    }

    public Boolean fueVinculada(){
        return fueVinculada;
    }

    public int getOperacionDeIngreso_id(){
        return operacionDeIngresoId;
    }

    public OperacionDeIngreso getOperacionDeIngreso() {
        return operacionDeIngreso;
    }

    public List<CategoriaCriterio> getListaCategoriaCriterio() {
        return listaCategoriaCriterio;
    }

    public Proveedor getProveedorAsociado() {
        return proveedorAsociado;
    }

    public boolean getFueVinculada() {
        return fueVinculada;
    }
}