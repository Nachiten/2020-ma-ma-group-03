package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class Agropecuario extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(cumpleCondicionCon(12890000, 5)){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(cumpleCondicionCon(48480000, 10)){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(cumpleCondicionCon(345430000, 50)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(cumpleCondicionCon(547890000, 215)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }


        return tipoEmpresa;
    }
}