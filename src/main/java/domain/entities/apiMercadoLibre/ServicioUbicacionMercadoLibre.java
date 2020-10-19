package domain.entities.apiMercadoLibre;

import com.google.gson.internal.LinkedTreeMap;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    //-------------------------------------------------------------------------
                        //METODOS
    //-------------------------------------------------------------------------

    // Listar todos los Id de idPaises ok
    public List<String> listadoIdDePaises() throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<List<LinkedTreeMap>> pedidoPaises = ubicacionService.idPaises();
        Response<List<LinkedTreeMap>> responseProvinciasArgentinas = pedidoPaises.execute();
        return responseProvinciasArgentinas.body().stream().map(id -> id.get("id").toString()).collect(Collectors.toList());
    }

    // Listar un pais en especifico que contiene la lista de sus estados(Provincias) ok
    public Pais obtenerPais(String idPais) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<Pais> pedidoUnPais = ubicacionService.pais( idPais, "id, name, locale, currency_id, states");
        Response<Pais> respuestaPedidoPais = pedidoUnPais.execute();
        return respuestaPedidoPais.body();
    }

    // Listar todas las ciudades a partir de un estado ok
    public Estado obtenerUnEstado(String idEstado) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<Estado> pedidoCiudades = ubicacionService.estados(idEstado, "id, name, cities");
        Response<Estado> respuestaCiudades = pedidoCiudades.execute();
        return respuestaCiudades.body();
    }

    //listar una ciudad ok
    public Ciudad obtenerUnaCiudad(String idCiudad) throws IOException{
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<Ciudad> pedidoCiudad = ubicacionService.ciudad(idCiudad, "id, name");
        Response<Ciudad> respuestaCiudad = pedidoCiudad.execute();
        return respuestaCiudad.body();
    }
    // Listar todas las monedas ok
    public List<Monedas> listadoMonedas() throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<List<Monedas>> pedidoMonedas = ubicacionService.monedas();
        Response<List<Monedas>> respuestaMonedas = pedidoMonedas.execute();
        return respuestaMonedas.body();
    }

    //Listar tipo de moneda por pais
    public Monedas listarMonedaPorPais(String idPais) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<Monedas> pedidoMoneda = ubicacionService.monedas(idPais, "id, symbol, description, decimal_places");
        Response<Monedas> respuestaMoneda = pedidoMoneda.execute();
        return respuestaMoneda.body();
    }




}
