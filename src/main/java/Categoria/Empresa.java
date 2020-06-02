package Categoria;

import Operaciones.OperacionDeEgreso;
import Rubros.EstrategiaDeRubro;

public class Empresa extends Categoria {

    private EstrategiaDeRubro estrategiaDeRubro;
    private TipoEmpresa tipoEmpresa;

    public Empresa(EstrategiaDeRubro estrategiaDeRubro) {
        this.estrategiaDeRubro = estrategiaDeRubro;
    }

    private TipoEmpresa calcularTipo(){
        return estrategiaDeRubro.clasificacion();
    }

    public void realizarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso) {
        //TODO Hace cosas
    }
}