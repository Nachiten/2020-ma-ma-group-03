package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioUbicacionMercadoLibre {

    private static ServicioUbicacionMercadoLibre instancia = null;
    private Retrofit retrofit;

    //Es un singleton, lo convierte en una unica instancia
    public static ServicioUbicacionMercadoLibre instancia(){
        if(instancia== null){
            instancia = new ServicioUbicacionMercadoLibre();
        }
        return instancia;
    }

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    private ServicioUbicacionMercadoLibre() {
        //instanciando la biblioteca de retrofit
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    // Listar todos los paises
    public List<Pais> listadoDePaises() throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<List<Pais>> pedidoPaises = ubicacionService.paises();
        Response<List<Pais>> responseProvinciasArgentinas = pedidoPaises.execute();
        return responseProvinciasArgentinas.body();
    }

    // Listar un pais en especifico que contiene la lista de sus estados(Provincias)
    public InfoPais listadoEstadosDePais(String idPais) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<InfoPais> pedidoUnPais = ubicacionService.provincias( idPais, "id, name, locale, states");
        Response<InfoPais> respuestaPedidoPais = pedidoUnPais.execute();
        return respuestaPedidoPais.body();
    }

    // Listar todas las ciudades a partir de un estado
    public InfoEstado listadoCiudadesDeEstado(String idEstado) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<InfoEstado> pedidoCiudades = ubicacionService.estados(idEstado, "id, name, cities");
        Response<InfoEstado> respuestaCiudades = pedidoCiudades.execute();
        return respuestaCiudades.body();
    }

    //Listar tipo de moneda por pais
    public InfoMoneda listarMonedaPorPais(String idPais) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<InfoMoneda> pedidoMoneda = ubicacionService.monedas(idPais, "id, symbol, description, decimal_places");
        Response<InfoMoneda> respuestaMoneda = pedidoMoneda.execute();
        return respuestaMoneda.body();
    }

    //listar ciudad
    public InfoCiudad listarCiudad(String idCiudad) throws IOException{
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<InfoCiudad> pedidoCiudad = ubicacionService.ciudad(idCiudad, "id, name");
        Response<InfoCiudad> respuestaCiudad = pedidoCiudad.execute();
        return respuestaCiudad.body();
    }

    // Listar todas las monedas
    public List<InfoMoneda> listadoMonedas() throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<List<InfoMoneda>> pedidoMonedas = ubicacionService.monedas();
        Response<List<InfoMoneda>> respuestaMonedas = pedidoMonedas.execute();
        return respuestaMonedas.body();
    }


}
