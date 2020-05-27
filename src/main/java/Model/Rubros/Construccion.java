package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class Construccion extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(cumpleCondicionCon(15230000, 12)){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(cumpleCondicionCon(90310000, 45)){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(cumpleCondicionCon(503880000, 200)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(cumpleCondicionCon(755740000, 590)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}
