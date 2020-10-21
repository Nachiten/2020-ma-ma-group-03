package domain.controllers;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import persistencia.db.EntityManagerHelper;
import spark.Request;
import spark.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OperacionEgresoController {

    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;

    public OperacionEgresoController() {
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
    }

    public Response guardarOperacionDeEgreso(Request request, Response response){

        // TODO | Ver en que formato se pasa la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String fechaString = request.queryParams("fecha");
        String montoTotalString = request.queryParams("montoTotal");

        // Convierto de string a localDate
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        // Convierto de string a float
        float montoTotal = Float.parseFloat(montoTotalString);

        OperacionDeEgreso operacionAGuardar = new OperacionDeEgreso(fecha, montoTotal);

        EntidadJuridica entidadJuridica = EntityManagerHelper.getEntityManager().find(EntidadJuridica.class, 1);
        Usuario miUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);

        operacionAGuardar.setEntidadJuridicaAsociada(entidadJuridica);
        //operacionAGuardar.setUsuario(miUsuario);

        try{
            repoOperacionEgreso.agregar(operacionAGuardar);
        }catch (Exception e) {
            String mensajeError = e.getMessage();
            System.out.println("EXCEPCION: " + mensajeError);
            response.redirect("/error?codError=" + mensajeError);
            return response;
        }

        response.redirect("/principal");

        return response;
    }
}
