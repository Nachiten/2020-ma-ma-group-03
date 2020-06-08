package TipoEntidadJuridica;

public class Categoria {
    String nombre;
    int ventasAnualesMaximas;
    int cantidadPersonalMaximo;

    public boolean cumploConCategoria(int ventasAnuales, int cantidadPersonal){
        return ventasAnuales <= ventasAnualesMaximas && cantidadPersonal <= cantidadPersonalMaximo;
    }

    public String getNombre(){
        return nombre;
    }
}
