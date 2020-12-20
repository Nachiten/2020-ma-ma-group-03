package domain.entities.tipoEntidadJuridica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tipoEntidadJuridicaOrganizacionSectorSocial")
public class OrganizacionSectorSocial extends TipoEntidadJuridica {

    @Column(name = "fechaDeCracion")
    private LocalDate fechaCreacion;

    public OrganizacionSectorSocial() {
        inicializar();
    }

    private void inicializar(){
        this.fechaCreacion = LocalDate.now();
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}