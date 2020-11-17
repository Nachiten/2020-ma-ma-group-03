package domain.controllers;

import domain.entities.apiMercadoLibre.Direccion;
import domain.entities.apiMercadoLibre.DireccionPostal;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.tipoEntidadJuridica.TipoEntidadJuridica;
import domain.entities.usuarios.Usuario;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class AltaEntidadJuridicaController {

    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;
    private Repositorio<EntidadJuridica> repoEntidadJuridica;

    public AltaEntidadJuridicaController(ModalAndViewController modalAndViewController, OperadorController operadorController) {
        this.modalAndViewController = modalAndViewController;
        this.repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        this.operadorController = operadorController;
    }

    private ModelAndView modalAndViewAltaEntidadJuridica(){
        return new ModelAndView(modalAndViewController.getParametros(),"altaEntidadJuridica.hbs");
    }

    public ModelAndView mostrarPaginaAltaEntidadJuridica(Request request, Response response) {
        modalAndViewController.cargarParametrosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAltaEntidadJuridica);
    }


    public ModelAndView guardarEntidadJuridica(Request request, Response response){
        String nombre = request.queryParams("nombre");
        String nombreFicticio = request.queryParams("nombreFicticio");
        String codigoInscripcion = request.queryParams("codigoInscripcionDefinitiva");
        String razonSocial = request.queryParams("razonSocial");
        String cuit_cuil = request.queryParams("cuit");
        String calle = request.queryParams("calle");
        String alturaString = request.queryParams("altura");
        String pisoString = request.queryParams("piso");

        int altura = Integer.parseInt(alturaString);
        int piso = Integer.parseInt(pisoString);

        Direccion direccion = new Direccion(calle, altura, piso, "A");

        DireccionPostal direccionPostal = new DireccionPostal(direccion);

        EntidadJuridica entidadJuridicaAGuardar = new EntidadJuridica(nombre, nombreFicticio, razonSocial,
                cuit_cuil, null, codigoInscripcion, null);

        if (operadorController.persistenciaNoValida(repoEntidadJuridica, entidadJuridicaAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se persistio correctamente la entidad juridica.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");

    }



}
