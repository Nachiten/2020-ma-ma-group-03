package Entidades;

import TipoEntidadJuridica.Categoria;
import TipoEntidadJuridica.Empresa;

public class Afip {

    public static Categoria clasificacion(Empresa empresa){
        return empresa.getSector().categoria(empresa);
    }
}