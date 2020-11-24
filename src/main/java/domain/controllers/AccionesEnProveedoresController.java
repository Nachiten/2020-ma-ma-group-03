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

public class AccionesEnProveedoresController {
    private Repositorio<Proveedor> repoProveedor;
    private Repositorio<Pais> respoPaises;
    private Repositorio<Estado> repoProvincias;
    private Repositorio<Ciudad> repoCiudades;
    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;
    private List<Pais> listaPaises;
    private List<Estado> listaProvincias;
    private List<Ciudad> listaCiudades;

    public AccionesEnProveedoresController(ModalAndViewController modalAndViewController, OperadorController operadorController) {

        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
        this.respoPaises = FactoryRepositorio.get(Pais.class);
        this.repoProvincias = FactoryRepositorio.get(Estado.class);
        this.repoCiudades =FactoryRepositorio.get(Ciudad.class);
        this.listaPaises = new ArrayList<>();
        this.listaProvincias = new ArrayList<>();
        this.listaCiudades = new ArrayList<>();
    }

    private ModelAndView modalAndViewAccionesProveedores() {
        return new ModelAndView(modalAndViewController.getParametros(), "accionesProveedores.hbs");
    }

    public ModelAndView mostrarPaginaAccionesPRoveedores(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAccionesProveedores);
    }

    private ModelAndView modelAndViewNuevoProveedor(){
        listaPaises = respoPaises.buscarTodos();
        listaProvincias = repoProvincias.buscarTodos();
        listaCiudades = repoCiudades.buscarTodos();
        modalAndViewController.getParametros().put("listaPaises", listaPaises);
        modalAndViewController.getParametros().put("listaProvincias", listaProvincias);
        modalAndViewController.getParametros().put("listaCiudades", listaCiudades);
        return new ModelAndView(modalAndViewController.getParametros(),"modalNuevoProveedor.hbs");
    }
    public ModelAndView mostrarModalNuevoPRoveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modelAndViewNuevoProveedor);
    }

    public ModelAndView guardarNuevoProveedor(Request request, Response response) {
        String nombreIngresado = request.queryParams("nombre");
        String apellidoIngresado = request.queryParams("apellido");
        String razonSocialIngresado = request.queryParams("razonSocial");
        String cuit_cuilIngresado = request.queryParams("cuit_cuil");

        DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);
        Proveedor proveedorAGuardar = new Proveedor(nombreIngresado, apellidoIngresado, cuit_cuilIngresado, direccionPostal, razonSocialIngresado);

        if (operadorController.persistenciaNoValida(repoProveedor, proveedorAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se se creó exitosamente el nuevo proveedor.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
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

/*
    public ModelAndView guardarProveedor(Request request, Response response){



        int cuit_cuil = Integer.parseInt(cuit_cuilString);

        DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);

        Proveedor proveedorAGuardar = new Proveedor(nombre, apellido, cuit_cuil, direccionPostal, razonSocial);

        if (operadorController.persistenciaNoValida(repoProveedor, proveedorAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se persistio correctamente el proveedor.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }*/


}
