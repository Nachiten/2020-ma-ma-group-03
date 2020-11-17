package domain.entities.tipoEntidadJuridica;

import domain.entities.entidades.Afip;

import javax.persistence.*;

@Entity
@Table(name = "tipoEntidadJuridicaEmpresa")
public class Empresa extends TipoEntidadJuridica {

    @Column(name = "promedioVentasAnuales")
    private int promedioVentasAnuales;

    @Column(name = "cantidadPersonal")
    private int cantidadPersonal;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Categoria categoria;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Sector sector;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Empresa() { }

    public Empresa(Sector sector,String nombreFicticio, int promedioVentasAnuales, int cantidadPersonal) {
        this.sector = sector;
        this.promedioVentasAnuales = promedioVentasAnuales;
        this.cantidadPersonal = cantidadPersonal;
        super.nombreFicticio = nombreFicticio;
        calcularCategoria();
    }

    public Empresa(Sector sector, int promedioVentasAnuales, int cantidadPersonal) {
        this.sector = sector;
        this.promedioVentasAnuales = promedioVentasAnuales;
        this.cantidadPersonal = cantidadPersonal;
        calcularCategoria();
    }

    private void calcularCategoria(){
        this.categoria = Afip.clasificacion(this);
    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setCantidadPersonal(int cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;

    }

    public void setPromedioVentasAnuales(int promedioVentasAnuales) {
        this.promedioVentasAnuales = promedioVentasAnuales;

    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public int getCantidadPersonal() {
        return cantidadPersonal;
    }

    public int getPromedioVentasAnuales() {
        return promedioVentasAnuales;
    }

    public Sector getSector() { return sector; }

    public Categoria getCategoria() { return categoria; }
}