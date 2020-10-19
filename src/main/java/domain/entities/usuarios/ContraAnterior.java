package domain.entities.usuarios;

import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contraAnterior")
public class ContraAnterior extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuarioAsociado;
    @Column (name = "contraAnterior")
    private String contraAnterior;
    @Column (name = "tiempoContrasenia")
    private LocalDate tiempoContrasenia;

    public ContraAnterior(){
        // Para que se pueda persistir
    }

    public ContraAnterior(Usuario usuarioAsociado, String contraAnterior, LocalDate tiempoContrasenia){
        this.usuarioAsociado = usuarioAsociado;
        this.contraAnterior = contraAnterior;
        this.tiempoContrasenia = tiempoContrasenia;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public void setContraAnterior(String contraAnterior) {
        this.contraAnterior = contraAnterior;
    }

    public void setTiempoContrasenia(LocalDate tiempoContrasenia) {
        this.tiempoContrasenia = tiempoContrasenia;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public LocalDate getTiempoContrasenia() {
        return tiempoContrasenia;
    }

    public String getContraAnterior() {
        return contraAnterior;
    }
}

