package domain.controllers;

import domain.entities.apiMercadoLibre.*;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProveedorController {
    private Repositorio<Proveedor> repoProveedor;
    private Repositorio<Pais> respoPaises;
    private Repositorio<Estado> repoProvincias;
    private Repositorio<Ciudad> repoCiudades;
    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;


    public ProveedorController(ModalAndViewController modalAndViewController, OperadorController operadorController) {

        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
        this.respoPaises = FactoryRepositorio.get(Pais.class);
        this.repoProvincias = FactoryRepositorio.get(Estado.class);
        this.repoCiudades =FactoryRepositorio.get(Ciudad.class);

    }

    private ModelAndView modalAndViewListarProveedores(){

        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Proveedor> proveedoresHabilitados = proveedores.stream().filter(Proveedor::getEstoyHabilitado).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listadoProveedores", proveedoresHabilitados);
        return new ModelAndView(modalAndViewController.getParametros(), "bajaProveedor.hbs");

    }

    public ModelAndView listarProveedores(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewListarProveedores);
    }

    public ModelAndView eliminar(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Proveedor proveedorBuscado = this.repoProveedor.buscar(idBuscado);
        proveedorBuscado.cambiarAInhabilitado();
        this.repoProveedor.modificar(proveedorBuscado);
        modalAndViewController.getParametros().put("mensaje","El proveedor se dio de baja correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs") ;
    }

    private ModelAndView modelAndViewAltaProveedor(){
        List<Pais> listaPaises = respoPaises.buscarTodos();
        List<Estado> listaProvincias = repoProvincias.buscarTodos();
        List<Ciudad> listaCiudades = repoCiudades.buscarTodos();
        modalAndViewController.getParametros().put("listaPaises", listaPaises);
        modalAndViewController.getParametros().put("listaProvincias", listaProvincias);
        modalAndViewController.getParametros().put("listaCiudades", listaCiudades);
        return new ModelAndView(modalAndViewController.getParametros(),"altaProveedor.hbs");
    }
    public ModelAndView altaProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modelAndViewAltaProveedor);
    }

    public ModelAndView guardarProveedor(Request request, Response response){

        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String razonSocial = request.queryParams("razonSocial");
        String cuit_cuilString = request.queryParams("cuit_cuil");

        int cuit_cuil = Integer.parseInt(cuit_cuilString);

        DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);

        // TODO | Guardar datos de pais ciudad, provincia

        Proveedor proveedorAGuardar = new Proveedor(nombre, apellido, cuit_cuil, direccionPostal, razonSocial);

        if (operadorController.persistenciaNoValida(repoProveedor, proveedorAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se persistio correctamente el proveedor.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

}
