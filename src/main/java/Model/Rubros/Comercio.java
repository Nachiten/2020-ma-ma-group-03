package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class Comercio extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(cumpleCondicionCon(29740000, 7)){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(cumpleCondicionCon(178860000, 35)){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(cumpleCondicionCon(1502750000, 125)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(cumpleCondicionCon(2146810000, 345)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}
