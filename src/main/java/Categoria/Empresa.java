package Categoria;

import Operaciones.OperacionDeEgreso;
import Rubros.Rubro;

public class Empresa extends Categoria {

    private Rubro rubro;
    private TipoEmpresa tipoEmpresa;

    public Empresa(Rubro rubro) {
        this.rubro = rubro;
    }

    private TipoEmpresa obtenerTipo(){
        return rubro.clasificacion();
    }

    public void realizarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso) {
        //TODO Hace cosas
    }
}