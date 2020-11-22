package domain.controllers;

import domain.entities.apiMercadoLibre.*;
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
import java.util.List;
import java.util.Map;

public class AltaEntidadJuridicaController {

    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;
    private Repositorio<EntidadJuridica> repoEntidadJuridica;
    private Repositorio<Pais> respoPaises;
    private Repositorio<Estado> repoProvincias;
    private Repositorio<Ciudad> repoCiudades;

    public AltaEntidadJuridicaController(ModalAndViewController modalAndViewController, OperadorController operadorController) {
        this.modalAndViewController = modalAndViewController;
        this.repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        this.operadorController = operadorController;
        this.respoPaises = FactoryRepositorio.get(Pais.class);
        this.repoProvincias = FactoryRepositorio.get(Estado.class);
        this.repoCiudades =FactoryRepositorio.get(Ciudad.class);
    }

    private ModelAndView modalAndViewAltaEntidadJuridica(){
        List<Pais> listaPaisesEJ = respoPaises.buscarTodos();
        List<Estado> listaProvinciasEJ = repoProvincias.buscarTodos();
        List<Ciudad> listaCiudadesEJ = repoCiudades.buscarTodos();
        modalAndViewController.getParametros().put("listaPaisesEJ", listaPaisesEJ);
        modalAndViewController.getParametros().put("listaProvinciasEJ", listaProvinciasEJ);
        modalAndViewController.getParametros().put("listaCiudadesEJ", listaCiudadesEJ);
        return new ModelAndView(modalAndViewController.getParametros(),"altaEntidadJuridica.hbs");
    }

    public ModelAndView mostrarPaginaAltaEntidadJuridica(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAltaEntidadJuridica);
    }


    public ModelAndView guardarEntidadJuridica(Request request, Response response){
        String nombre = request.queryParams("nombre");
        String nombreFicticio = request.queryParams("nombreFicticio");
        String codigoInscripcion = request.queryParams("codigoInscripcionDefinitiva");
        String razonSocial = request.queryParams("razonSocial");
        String cuit_cuil = request.queryParams("cuit");

        DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);

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
