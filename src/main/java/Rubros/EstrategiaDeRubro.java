package Rubros;

import Categoria.TipoEmpresa;

public class EstrategiaDeRubro {

    public int promedioVentasAnuales;
    public int cantPersonal;

    public TipoEmpresa clasificacion(){
        return null;
    }

    public int tipoDeEmpresaPorVentasAnuales(int promedioVentasAnuales){return 0;}

    public int tipoDeEmpresaPorCantidadPersonal(int cantidadPersonal){return 0;}

    public TipoEmpresa tipoDeEmpresa(int promedioVentasAnuales, int cantidadPersonal){
        int enumNumber = Math.max(tipoDeEmpresaPorVentasAnuales(promedioVentasAnuales), tipoDeEmpresaPorCantidadPersonal(cantidadPersonal));
        return TipoEmpresa.valueOf(Integer.toString(enumNumber));
    }

}