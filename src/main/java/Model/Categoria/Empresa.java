package Model.Categoria;

import Model.Rubros.Rubro;

public class Empresa extends Categoria {

    private Rubro rubro;
    private TipoEmpresa tipoEmpresa;

    public Empresa(Rubro rubro) {
        this.rubro = rubro;
    }

    private TipoEmpresa clasificacion(){
        return rubro.clasificacion();
    }
}