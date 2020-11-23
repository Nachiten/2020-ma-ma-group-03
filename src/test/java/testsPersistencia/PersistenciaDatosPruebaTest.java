package testsPersistencia;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.apiMercadoLibre.Direccion;
import domain.entities.apiMercadoLibre.DireccionPostal;
import domain.entities.apiMercadoLibre.Estado;
import domain.entities.apiMercadoLibre.Pais;
import domain.entities.entidades.EntidadBase;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.*;
import domain.entities.tipoEntidadJuridica.Categoria;
import domain.entities.tipoEntidadJuridica.Empresa;
import domain.entities.tipoEntidadJuridica.Sector;
import domain.entities.tipoEntidadJuridica.TipoEntidadJuridica;
import domain.entities.usuarios.TipoUsuario;
import domain.entities.usuarios.Usuario;
import domain.entities.vendedor.Proveedor;
import org.junit.runners.MethodSorters;
import persistencia.db.EntityManagerHelper;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenciaDatosPruebaTest {


    //PAIS
    static private Pais argentina;
    static private Pais estadosUnidos;
    static private Pais mexico;

    //ESTADO
    static private Estado estado1;
    static private Estado estado2;
    static private Estado estado3;

    //DIRECCION
    static private Direccion direccion1;
    static private Direccion direccion2;
    static private Direccion direccion3;
    static private Direccion direccion4;

    //DIRECCION POSTAL

    static private DireccionPostal direccionPostal1;
    static private DireccionPostal direccionPostal2;
    static private DireccionPostal direccionPostal3;
    static private DireccionPostal direccionPostal4;

    //ENTIDAD JURIDICA
    static private EntidadJuridica entidadJuridicaEAAFBA;
    static private EntidadJuridica entidadJuridicaEAAFNY;
    static private EntidadJuridica entidadJuridicaEAAFM;
    static private EntidadJuridica entidadJuridicaSurcosCS;

    //ENTIDADES BASE
    static private EntidadBase entidadBaseAndhes;

    //SECTOR
    static  private Sector sectorConstruccion;
    static  private Sector sectorAlojamiento;

    //CATEGORIAS
    static private CategoriaCriterio categoriaFachada;
    static private CategoriaCriterio categoriaInterior;
    static private CategoriaCriterio categoriaHumedad;
    static private CategoriaCriterio categoriaServiciosGenerales;
    static private CategoriaCriterio categoriaMueblesYUtiles;
    static private CategoriaCriterio categoriaCoffeBreak;
    static private CategoriaCriterio categoriaElectronicos;
    static private CategoriaCriterio categoriaServiciosDeLuz;
    static private CategoriaCriterio categoriaServiciosDeGas;
    //static private CategoriaCriterio categoriaNecesario; TODO completar
    static private CategoriaCriterio categoriaGrande;
    static private CategoriaCriterio categoriaExterior;


    //LISTA DE CATEGORIAS PARA LOS CRITERIOS

    static private List<CategoriaCriterio> categoriasDeGastoMantenimiento;
    static private List<CategoriaCriterio> categoriasDeCausante;
    static private List<CategoriaCriterio> categoriasDeLugarDeAplicacion;
    static private List<CategoriaCriterio> categoriasDeGastosGenerales;
    static private List<CategoriaCriterio> categoriasDeElementosDeOficina;
    static private List<CategoriaCriterio> categoriasDeMomentoDeUtilizacion;
    static private List<CategoriaCriterio> categoriasDeTipoProducto;
    static private List<CategoriaCriterio> categoriasDeTamanioDelGasto;
    //static private List<CategoriaCriterio> categoriasDeElementosDeUsoInterno; TODO completar
    static private List<CategoriaCriterio> categoriasDeServicios;


    //CRITERIOS
    static private Criterio criterioGastosDeMantenimiento;
    static private Criterio criterioLugarDeAplicacion;
    static private Criterio criterioCausante;
    static private Criterio criterioGastosGenerales;
    static private Criterio criterioElementosDeOficina;
    static private Criterio criterioMomentoDeUtilizacion;
    static private Criterio criterioTipoDeProducto;
    static private Criterio criterioTamanioDelGasto;
    //static private Criterio criterioElementosDeUsoInterno; TODO completar
    static private Criterio criterioServicios;


    // LISTA DE CATEGORIAS  PARA LOS EGRESOS

    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso1 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso2 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso3 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso4 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso5 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso6 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso7;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso8 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso9 ;
    static private  List<CategoriaCriterio> listaCategoriaOperacionDeEgreso10 ;



    //EMPRESAS
    static  private TipoEntidadJuridica tipoEntidadJuridica1;
    static  private TipoEntidadJuridica tipoEntidadJuridica2;
    static  private TipoEntidadJuridica tipoEntidadJuridica3;
    static  private TipoEntidadJuridica tipoEntidadJuridica4;

    //USUARIOS
    static private Usuario usuarioA;
    static private Usuario usuarioB;
    static private Usuario usuarioC;
    static private Usuario usuarioAdmin;

    // MEDIO DE PAGO
    static private TipoMedioDePago tarjetaDeCredito;
    static private MedioDePago medioDePagoTarjetaDeCredito;

    static private TipoMedioDePago tarjetaDeDebito;
    static private MedioDePago medioDePagoTarjetaDebito;

    static private TipoMedioDePago efectivo;
    static private MedioDePago medioDePagoEfectivo;

    // PROVEEDOR
    static private Proveedor proveedorPintureriaSerrentino;
    static private Proveedor proveedorEdesur;
    static private Proveedor proveedorMetrogas;
    static private Proveedor proveedorMitoas;
    static private Proveedor proveedorIngenieriaComercial;
    static private Proveedor proveedorCorralonLaprida;
    static private Proveedor proveedorTelasZN;
    static private Proveedor proveedorPintureriasRex;
    static private Proveedor proveedorPintureriasSanJorge;
    static private Proveedor proveedorLaCasaDelAudio;
    static private Proveedor proveedorGarbarino;
    static private Proveedor proveedorCorralonSanJuan;

    //ITEMS OPERACION DE EGRESO
    static private Item item1Egreso;
    static private Item item2Egreso;
    static private Item item3Egreso;
    static private Item item4Egreso;
    static private Item item5Egreso;
    static private Item item6Egreso;
    static private Item item7Egreso;
    static private Item item8Egreso;
    static private Item item9Egreso;
    static private Item item10Egreso;
    static private Item item11Egreso;
    static private Item item12Egreso;
    static private Item item13Egreso;
    static private Item item14Egreso;
    static private Item item15Egreso;
    static private Item item16Egreso;

    //ITEMS PRESUPUESTOS .
    static private Item item1Presupuesto;
    static private Item item2Presupuesto;
    static private Item item3Presupuesto;
    static private Item item4Presupuesto;
    static private Item item5Presupuesto;
    static private Item item6Presupuesto;
    static private Item item7Presupuesto;
    static private Item item8Presupuesto;
    static private Item item9Presupuesto;
    static private Item item10Presupuesto;
    static private Item item11Presupuesto;
    static private Item item12Presupuesto;
    static private Item item13Presupuesto;
    static private Item item14Presupuesto;
    static private Item item15Presupuesto;
    static private Item item16Presupuesto;
    static private Item item17Presupuesto;
    static private Item item18Presupuesto;
    static private Item item19Presupuesto;
    static private Item item20Presupuesto;


    //OPERACIONES DE INGRESO
    static private OperacionDeIngreso ingresoDonacionDeTerceros;
    static private OperacionDeIngreso ingresoDonacionDeRimoliSA;
    static private OperacionDeIngreso ingresoDonacionGranImperio;
    static private OperacionDeIngreso ingresoDonacionGabinoSRL;

    //OPERACION DE EGRESO

    private static OperacionDeEgreso operacionDeEgreso1 ;
    private static OperacionDeEgreso  operacionDeEgreso2;
    private static OperacionDeEgreso operacionDeEgreso3;
    private static OperacionDeEgreso operacionDeEgreso4;
    private static OperacionDeEgreso operacionDeEgreso5;
    private static OperacionDeEgreso operacionDeEgreso6;
    private static OperacionDeEgreso operacionDeEgreso7;
    private static OperacionDeEgreso operacionDeEgreso8;
    private static OperacionDeEgreso operacionDeEgreso9;
    private static OperacionDeEgreso operacionDeEgreso10;

    //LISTA ITEMS OPERACION DE EGRESO
    private static List<Item> listaItemsOperacionDeEgreso1 ;
    private static List<Item> listaItemsOperacionDeEgreso2 ;
    private static List<Item> listaItemsOperacionDeEgreso3 ;
    private static List<Item> listaItemsOperacionDeEgreso4 ;
    private static List<Item> listaItemsOperacionDeEgreso5 ;
    private static List<Item> listaItemsOperacionDeEgreso6 ;
    private static List<Item> listaItemsOperacionDeEgreso7 ;
    private static List<Item> listaItemsOperacionDeEgreso8 ;
    private static List<Item> listaItemsOperacionDeEgreso9 ;
    private static List<Item> listaItemsOperacionDeEgreso10 ;

    //LISTA DE ITEMS PARA PRESUPUESTOS
    static private List<Item> listaDeItemsDePintureriasRex ;
    static private List<Item> listaDeItemsDePintureriasSanJorge;
    static private List<Item> listaDeItemsDePintureriasSerrentino;
    static private List<Item> listaDeItemsDeLaCasaDelAudio;
    static private List<Item> listaDeItemsDeGarbarino ;
    static private List<Item> listaDeItemsDeIngenieriaComercial ;
    static private List<Item> listaDeItemsDeCorralonSanJuan ;
    static private List<Item> listaDeItemsDeCorralonLaprida ;



    //PRESUPUESTOS
    static private Presupuesto presupuestoPintureriasRex;
    static private Presupuesto presupuestoPintureriasSanJorge;
    static private Presupuesto presupuestoPintureriasSerrentino;
    static private Presupuesto presupuestoLaCasaDelAudio;
    static private Presupuesto presupuestoGarbarino;
    static private Presupuesto presupuestoIngenieriaComercial;
    static private Presupuesto presupuestoCorralonSanJuan;
    static private Presupuesto presupuestoCorralonLaprida;





    @BeforeClass
    public static void init() {

        // PAIS

        //ESTO UNA VEZ QUE CARGO LO DE ML TRAERME LOS DATOS Y USAR ENTITY MANAGER , PAIS, ESTADO Y CIUDAD
       /* argentina ;
        estadosUnidos;
        mexico = new Pais("Mexico");
        // ESTADO
        estado1 = new Estado("BA");
        estado2 = new Estado("Nueva York");
        estado3 = new Estado("Ciudad de Mexico");

*/
       /*
        //DIRECCION
        direccion1 = new Direccion("Av Medrano",51,0,"0");
        direccion2 = new Direccion("Liberty Ave",720,0,"0");
        direccion3 = new Direccion("Roberto Gayol",55,0,"0");
        direccion4 = new Direccion("Jeronimo Salguero",51,0,"0");

        //DIRECCION POSTAL  //ARREGLAR
        direccionPostal1 = new DireccionPostal(direccion1,ciudad1,estado1, argentina);
        direccionPostal2 = new DireccionPostal(direccion2, ciudad2,estado2, estadosUnidos);
        direccionPostal3 = new DireccionPostal(direccion3, ciudad3,estado3, mexico);
        direccionPostal4 = new DireccionPostal(direccion4,ciudad4,estado1, argentina);
*/
        //SECTOR

        sectorConstruccion = new Sector("Construccion");
        sectorAlojamiento = new Sector("Alojamiento");

        Categoria microConstruccion = new Categoria("Micro", 19450000, 12);
        Categoria pequeniaConstruccion = new Categoria("Pequeña", 115370000, 45);
        Categoria medianaTramo1Construccion = new Categoria("MedianaTramo1", 643710000, 200);
        Categoria medianaTramo2Construccion = new Categoria("MedianaTramo2", 965460000, 590);


        //Asociar sector a categoria
        microConstruccion.setSectorAsociado(sectorConstruccion);
        pequeniaConstruccion.setSectorAsociado(sectorConstruccion);
        medianaTramo1Construccion.setSectorAsociado(sectorConstruccion);
        medianaTramo2Construccion.setSectorAsociado(sectorConstruccion);

        sectorConstruccion.addCategorias(microConstruccion, pequeniaConstruccion, medianaTramo1Construccion, medianaTramo2Construccion);


        // TIPOENTIDAD JURIDICA EMPRESA
        tipoEntidadJuridica1 = new Empresa(sectorConstruccion,600000000,150);
        tipoEntidadJuridica2 = new Empresa(sectorConstruccion,960000000,580);
        tipoEntidadJuridica3 = new Empresa(sectorConstruccion,643710000,240);
        tipoEntidadJuridica4 = new Empresa(sectorConstruccion,8000000,8);
        //ENTIDADES JURIDICAS

        entidadJuridicaEAAFBA = new EntidadJuridica("Equipo Argentino de Antropología Forense - EAAF","Oficina Central Buenos Aires" ,"EAAF BA","30-15269857-2",direccionPostal1,"ABCD-LO",tipoEntidadJuridica1);
        entidadJuridicaEAAFNY = new EntidadJuridica("Equipo Argentino de Antropología Forense - EAAF","Oficina Central Nueva York" ,"EAAF NY","30-15789655-7",direccionPostal2,"AKPQ-LO",tipoEntidadJuridica2);
        entidadJuridicaEAAFM = new EntidadJuridica("Equipo Argentino de Antropología Forense - EAAF","Oficina Central Mexico" ,"EAAF M","30-77896583-9",direccionPostal3,"DDN-TP",tipoEntidadJuridica3);
        entidadJuridicaSurcosCS = new EntidadJuridica("Colectivo de Derechos de Infancia y Adolescencia - CDIA","Surcos " ,"Surcos CS","30-25888897-8",direccionPostal4,"ABK-LO",tipoEntidadJuridica4);
        //ENTIDADES BASE
        entidadBaseAndhes = new EntidadBase("Colectivo de Derechos de Infancia y Adolescencia - CDIA","Andhes");

        //INSTANCIAS DE USUARIOS

        usuarioA = new Usuario(TipoUsuario.ESTANDAR, "aroco", "*_aroco20!-?", "Alejandro", "Roco", entidadJuridicaEAAFBA);
        usuarioB = new Usuario(TipoUsuario.ESTANDAR,"rrojas","*-_rrojas!?", "Rocio", "Rojas", entidadJuridicaEAAFBA);
        usuarioC = new Usuario(TipoUsuario.ESTANDAR,"jazul","!-*jazul_!?","Julieta","Azul",entidadJuridicaSurcosCS);
        usuarioAdmin = new Usuario(TipoUsuario.ADMIN, "Admin", "admin", "Admin", "Root");

        //INSTANCIAS DE MEDIOS DE PAGO
        tarjetaDeCredito = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 1);
        medioDePagoTarjetaDeCredito = new MedioDePago(tarjetaDeCredito,"4509 9535 6623 3709");

        tarjetaDeDebito = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 2);
        medioDePagoTarjetaDebito = new MedioDePago(tarjetaDeDebito,"5031 7557 3453 0604");

        efectivo = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 3);
        medioDePagoEfectivo = new MedioDePago(efectivo);

        //PROVEEDORES

        proveedorPintureriaSerrentino = new Proveedor("Pinturerías Serrentino","20-36975289-9");
        proveedorEdesur = new Proveedor("Edesur","26-36977895-2");
        proveedorMetrogas = new Proveedor("Metrogas","21-16357895-9");
        proveedorMitoas = new Proveedor("Mitoas SA","23-16357895-9");
        proveedorIngenieriaComercial = new Proveedor("Ingeniería Comercial SRL","26-16357895-9");
        proveedorCorralonLaprida = new Proveedor("Corralón Laprida SRL","17-12683657-1");
        proveedorTelasZN = new Proveedor("Telas ZN","19-12678922-1");
        proveedorPintureriasRex = new Proveedor("Pinturerias Rex","18-22678982-1");
        proveedorPintureriasSanJorge = new Proveedor("Pinturerias San Jorge","29-59632148-7");
        proveedorLaCasaDelAudio = new Proveedor("La casa del audio","14-78963254-1");
        proveedorGarbarino = new Proveedor("Garbarino","36-89652214-4");
        proveedorCorralonSanJuan = new Proveedor("Corralon San Juan","11-48696325-5");

         //ITEMS OPERACION DE EGRESO
        item1Egreso = new Item(TipoItem.PRODUCTO,"PINTURA Z10 LATEX SUPERCUBRITIVO 20L",9625,1);
        item2Egreso = new Item(TipoItem.PRODUCTO,"PINTURA LOXON FTES IMPERMEABILIZANTE 10L",6589.40f,1);
        item3Egreso = new Item(TipoItem.PRODUCTO,"PINTURA BRIKOL PISOS NEGRO 4L",3738.29f,1);
        item4Egreso = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE LUZ PERIODO JUNIO 2020",2100,1);
        item5Egreso = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE GAS PERIODO JUNIO 2020",3500,1);
        item6Egreso = new Item(TipoItem.PRODUCTO,"PAVA ELECTRICA SMARTLIFE 1,5 LTS 1850W",4500,3);
        item7Egreso = new Item(TipoItem.PRODUCTO,"CAFETERA SMARTLIFE 1095 ACERO INOX.",6300,2);
        item8Egreso = new Item(TipoItem.PRODUCTO,"TELEFONOS INALAMBRICOS MOTOROLA DUO BLANCO",8500,2);
        item9Egreso = new Item(TipoItem.PRODUCTO,"CEMENTO LOMA NEGRA",704.40f,10);
        item10Egreso = new Item(TipoItem.PRODUCTO,"ARENA FINA EN BOLSÓN X M30",3100,5);
        item11Egreso = new Item(TipoItem.PRODUCTO,"HIERRO ACINDAR",891,4);
        item12Egreso = new Item(TipoItem.PRODUCTO,"BLOQUE LADRILLO CEMENTO",227,800);
        item13Egreso = new Item(TipoItem.PRODUCTO,"BLOQUE LADRILLO CEMENTO",227,800);
        item14Egreso = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE LUZ PERIODO JUNIO 2020",1100,1);
        item15Egreso = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE GAS PERIODO JUNIO 2020",800,1);
        item16Egreso = new Item(TipoItem.PRODUCTO,"CORTINAS BLACKOUT VINILICO 2 PAÑOS",4200,5);


        //ITEMS PRESUPUESTOS
        item1Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA Z10 LATEX SUPERCUBRITIVO 20L",9900.80f,1);
        item2Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA LOXON FTES IMPERMEABILIZANTE 10L",7200,1);
        item3Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA BRIKOL PISOS NEGRO 4L",4350.80f,1);
        item4Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA Z10 LATEX SUPERCUBRITIVO 20L",4350.80f,1);
        item5Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA LOXON FTES IMPERMEABILIZANTE 10L",6590.30f,1);
        item6Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA BRIKOL PISOS NEGRO 4L",4100,1);
        item7Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA Z10 LATEX SUPERCUBRITIVO 20L",9625,1);
        item8Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA LOXON FTES IMPERMEABILIZANTE 10L",9625,1);
        item9Presupuesto = new Item(TipoItem.PRODUCTO,"PINTURA BRIKOL PISOS NEGRO 4L",3738.29f,1);
        item10Presupuesto = new Item(TipoItem.PRODUCTO,"TELEFONOS INALAMBRICOS MOTOROLA DUO BLANCO",8950,2);
        item11Presupuesto = new Item(TipoItem.PRODUCTO,"TELEFONOS INALAMBRICOS MOTOROLA DUO BLANCO",8830,2);
        item12Presupuesto = new Item(TipoItem.PRODUCTO,"TELEFONOS INALAMBRICOS MOTOROLA DUO BLANCO",8500,2);
        item13Presupuesto = new Item(TipoItem.PRODUCTO,"CEMENTO LOMA NEGRA",715,10);
        item14Presupuesto = new Item(TipoItem.PRODUCTO,"ARENA FINA EN BOLSÓN X M30",3150,5);
        item15Presupuesto = new Item(TipoItem.PRODUCTO,"HIERRO ACINDAR",880,4);
        item16Presupuesto = new Item(TipoItem.PRODUCTO,"BLOQUE LADRILLO CEMENTO",235,800);
        item17Presupuesto = new Item(TipoItem.PRODUCTO,"CEMENTO LOMA NEGRA",704.40f,10);
        item18Presupuesto = new Item(TipoItem.PRODUCTO,"ARENA FINA EN BOLSÓN X M30",3100,5);
        item19Presupuesto = new Item(TipoItem.PRODUCTO,"HIERRO ACINDAR",227,800);
        item20Presupuesto = new Item(TipoItem.PRODUCTO,"BLOQUE LADRILLO CEMENTO",227,800);

        // CATEGORIAS
        categoriaFachada = new CategoriaCriterio("Fachada");
        categoriaHumedad = new CategoriaCriterio("Humedad");
        categoriaServiciosGenerales = new CategoriaCriterio("Servicios Generales");
        categoriaMueblesYUtiles = new CategoriaCriterio("Muebles y utiles");
        categoriaCoffeBreak = new CategoriaCriterio("Coffe Break");
        categoriaElectronicos = new CategoriaCriterio("Electronicos");
        categoriaGrande = new CategoriaCriterio("Grande");
        categoriaExterior = new CategoriaCriterio("Exterior");
        categoriaInterior = new CategoriaCriterio("Interior");
        categoriaServiciosDeGas= new CategoriaCriterio("Servicio de Gas");
        categoriaServiciosDeLuz = new CategoriaCriterio("Servicio de Luz");

        //CATEGORIAS DE LOS CRITERIOS

        categoriasDeGastoMantenimiento = new ArrayList<>(Arrays.asList(categoriaFachada));
        categoriasDeCausante = new ArrayList<>(Arrays.asList(categoriaHumedad));
        categoriasDeGastosGenerales =  new ArrayList<>(Arrays.asList(categoriaServiciosGenerales));
        categoriasDeElementosDeOficina =  new ArrayList<>(Arrays.asList(categoriaMueblesYUtiles));
        categoriasDeMomentoDeUtilizacion =  new ArrayList<>(Arrays.asList(categoriaCoffeBreak));
        categoriasDeTipoProducto =  new ArrayList<>(Arrays.asList(categoriaElectronicos));
        categoriasDeTamanioDelGasto =  new ArrayList<>(Arrays.asList(categoriaGrande));
        categoriasDeLugarDeAplicacion = new ArrayList<>(Arrays.asList(categoriaExterior,categoriaInterior));
        categoriasDeServicios =  new ArrayList<>(Arrays.asList(categoriaServiciosDeLuz, categoriaServiciosDeGas));

        //CRITERIOS
        criterioGastosDeMantenimiento = new Criterio("Gastos de mantenimiento",categoriasDeGastoMantenimiento);
        criterioLugarDeAplicacion = new Criterio("LugarDeAplicacion",categoriasDeLugarDeAplicacion,criterioGastosDeMantenimiento);
        criterioCausante = new Criterio("Causante" , categoriasDeCausante);
        criterioGastosGenerales = new Criterio("Gastos generales",categoriasDeGastosGenerales);
        criterioElementosDeOficina = new Criterio("Elementos de oficina",categoriasDeElementosDeOficina);
        criterioMomentoDeUtilizacion = new Criterio("Momento de utilizacion",categoriasDeMomentoDeUtilizacion);
        criterioTipoDeProducto = new Criterio("Tipo de producto",categoriasDeTipoProducto);
        criterioTamanioDelGasto = new Criterio("Tipo tamanio",categoriasDeTamanioDelGasto);
        criterioServicios = new Criterio("Servicios",categoriasDeServicios);


         //LISTA DE ITEMS DE PRESUPUESTOS
        listaDeItemsDePintureriasRex = new ArrayList<>(Arrays.asList(item1Presupuesto, item2Presupuesto, item3Presupuesto));
        listaDeItemsDePintureriasSanJorge = new ArrayList<>(Arrays.asList(item4Presupuesto, item5Presupuesto, item6Presupuesto));
        listaDeItemsDePintureriasSerrentino = new ArrayList<>(Arrays.asList(item7Presupuesto, item8Presupuesto, item9Presupuesto));
        listaDeItemsDeLaCasaDelAudio = new ArrayList<>(Arrays.asList(item10Presupuesto));
        listaDeItemsDeGarbarino = new ArrayList<>(Arrays.asList(item11Presupuesto));
        listaDeItemsDeIngenieriaComercial = new ArrayList<>(Arrays.asList(item12Presupuesto));
        listaDeItemsDeCorralonSanJuan = new ArrayList<>(Arrays.asList(item13Presupuesto, item14Presupuesto, item15Presupuesto, item16Presupuesto));
        listaDeItemsDeCorralonLaprida = new ArrayList<>(Arrays.asList(item17Presupuesto,item18Presupuesto,item19Presupuesto,item20Presupuesto));

        //LISTA DE ITEMS DE OPERACIONES DE EGRESO

       listaItemsOperacionDeEgreso1 =new ArrayList<>(Arrays.asList(item1Egreso,item2Egreso,item3Egreso));
       listaItemsOperacionDeEgreso2 = new ArrayList<>(Arrays.asList(item4Egreso));
       listaItemsOperacionDeEgreso3 = new ArrayList<>(Arrays.asList(item5Egreso));
       listaItemsOperacionDeEgreso4 = new ArrayList<>(Arrays.asList(item6Egreso,item7Egreso));
       listaItemsOperacionDeEgreso5 = new ArrayList<>(Arrays.asList(item8Egreso));
       listaItemsOperacionDeEgreso6 = new ArrayList<>(Arrays.asList(item9Egreso,item10Egreso,item11Egreso,item12Egreso));
       listaItemsOperacionDeEgreso7 = new ArrayList<>(Arrays.asList(item13Egreso));
       listaItemsOperacionDeEgreso8 = new ArrayList<>(Arrays.asList(item14Egreso));
       listaItemsOperacionDeEgreso9 = new ArrayList<>(Arrays.asList(item15Egreso));
       listaItemsOperacionDeEgreso10 = new ArrayList<>(Arrays.asList(item16Egreso));



        //PRESUPUESTOS
        presupuestoPintureriasRex = new Presupuesto(21451.6f,listaDeItemsDePintureriasRex,null,LocalDate.of(2020,2,25),proveedorPintureriasRex);
        presupuestoPintureriasSanJorge = new Presupuesto(20300.8f,listaDeItemsDePintureriasRex,null,LocalDate.of(2020, 2,25),proveedorPintureriasRex);
        presupuestoPintureriasSerrentino = new Presupuesto(19952.69f,listaDeItemsDePintureriasSerrentino,null, LocalDate.of(2020, 2,27),proveedorPintureriaSerrentino);
        presupuestoLaCasaDelAudio = new Presupuesto(17900,listaDeItemsDeLaCasaDelAudio,null,LocalDate.of(2020,9,10),proveedorLaCasaDelAudio);
        presupuestoGarbarino = new Presupuesto(17660,  listaDeItemsDeGarbarino,null,LocalDate.of(2020,9,11),proveedorGarbarino);
        presupuestoIngenieriaComercial = new Presupuesto(17000, listaDeItemsDeIngenieriaComercial,null , LocalDate.of(2020,9,12),proveedorIngenieriaComercial);
        presupuestoCorralonSanJuan = new Presupuesto(214420,listaDeItemsDeCorralonSanJuan,null,LocalDate.of(2020,9,15),proveedorCorralonSanJuan);
        presupuestoCorralonLaprida = new Presupuesto(207708,listaDeItemsDeCorralonLaprida,null,LocalDate.of(2020,9,15),proveedorCorralonLaprida);



          /*
        //INGRESOS    //como agrego lo de la entidad que es un id?  //AGREGAR LO DE LA MONEDA DE ML
        ingresoDonacionDeTerceros = new OperacionDeIngreso("Donacion de terceros",20000, LocalDate.of(2020,02,25),LocalDate.of(2020,03,20));
        ingresoDonacionDeRimoliSA = new OperacionDeIngreso("Donacion de Rimoli SA",10000,LocalDate.of(2020,05,02),LocalDate.of(2020,08,03));
        ingresoDonacionGranImperio = new OperacionDeIngreso("Donacion de Gran Imperio",980000,LocalDate.of(2020,08,03),LocalDate.of(2020,10,01));
        ingresoDonacionGabinoSRL = new OperacionDeIngreso("Donacion Gabino SRL",10000,LocalDate.of(2020,05,01),LocalDate.of(2020,10,01));
*/
          //CATEGORIAS PARA OPERACIONES DE EGRESO
        listaCategoriaOperacionDeEgreso1 = new ArrayList<>(Arrays.asList(categoriaFachada,categoriaInterior,categoriaHumedad));
        listaCategoriaOperacionDeEgreso2 = new ArrayList<>(Arrays.asList(categoriaServiciosGenerales));
        listaCategoriaOperacionDeEgreso3 = new ArrayList<>(Arrays.asList(categoriaServiciosGenerales));
        listaCategoriaOperacionDeEgreso4 = new ArrayList<>(Arrays.asList(categoriaMueblesYUtiles,categoriaElectronicos));
        listaCategoriaOperacionDeEgreso5 = new ArrayList<>(Arrays.asList(categoriaMueblesYUtiles,categoriaElectronicos));
        listaCategoriaOperacionDeEgreso6 = new ArrayList<>(Arrays.asList(categoriaFachada,categoriaExterior,categoriaGrande));
        listaCategoriaOperacionDeEgreso7 = new ArrayList<>(Arrays.asList(categoriaFachada));
        listaCategoriaOperacionDeEgreso8 = new ArrayList<>(Arrays.asList(categoriaServiciosDeLuz));
        listaCategoriaOperacionDeEgreso9 = new ArrayList<>(Arrays.asList(categoriaServiciosDeGas));
        //listaCategoriaOperacionDeEgreso10 = new ArrayList<>(Arrays.asList(categoriaNecesario));



          //OPERACIONES DE EGRESO

        // public OperacionDeEgreso(LocalDate fecha, MedioDePago medio ,List<Item> items,int cantidadPresupuestos,float montoTotal, List<CategoriaCriterio> listaDeCategorias) {
   operacionDeEgreso1 = new OperacionDeEgreso(LocalDate.of(2020,3,10),medioDePagoTarjetaDeCredito,listaItemsOperacionDeEgreso1,3,19952.69f,listaCategoriaOperacionDeEgreso1,entidadJuridicaEAAFBA);
   operacionDeEgreso2 = new OperacionDeEgreso(LocalDate.of(2020,7,8),medioDePagoEfectivo,listaItemsOperacionDeEgreso2,0,2100,listaCategoriaOperacionDeEgreso2,entidadJuridicaEAAFBA);
   operacionDeEgreso3 = new OperacionDeEgreso(LocalDate.of(2020,7,9),medioDePagoTarjetaDeCredito,listaItemsOperacionDeEgreso3,0,3500,listaCategoriaOperacionDeEgreso3,entidadJuridicaEAAFBA);
   operacionDeEgreso4 = new OperacionDeEgreso(LocalDate.of(2020,8,3),medioDePagoTarjetaDebito,listaItemsOperacionDeEgreso4,0,26100,listaCategoriaOperacionDeEgreso4,entidadJuridicaEAAFBA);
   operacionDeEgreso5 = new OperacionDeEgreso(LocalDate.of(2020,9,27),medioDePagoEfectivo,listaItemsOperacionDeEgreso5,6,17000,listaCategoriaOperacionDeEgreso5,entidadJuridicaEAAFBA);
   operacionDeEgreso6 = new OperacionDeEgreso(LocalDate.of(2020,10,1),medioDePagoEfectivo,listaItemsOperacionDeEgreso6,4,207708,listaCategoriaOperacionDeEgreso6,entidadJuridicaEAAFBA);
   operacionDeEgreso7 = new OperacionDeEgreso(LocalDate.of(2020,10,5),medioDePagoEfectivo,listaItemsOperacionDeEgreso7,0,200000,listaCategoriaOperacionDeEgreso7,entidadJuridicaEAAFBA);
   operacionDeEgreso8 = new OperacionDeEgreso(LocalDate.of(2020,10,7),medioDePagoEfectivo,listaItemsOperacionDeEgreso8,0,1100,listaCategoriaOperacionDeEgreso8,entidadJuridicaSurcosCS);
   operacionDeEgreso9 = new OperacionDeEgreso(LocalDate.of(2020,10,7),medioDePagoEfectivo,listaItemsOperacionDeEgreso9,0,800,listaCategoriaOperacionDeEgreso9,entidadJuridicaSurcosCS);
   operacionDeEgreso10 = new OperacionDeEgreso(LocalDate.of(2020,9,25),medioDePagoEfectivo,listaItemsOperacionDeEgreso10,0,21000,listaCategoriaOperacionDeEgreso10,entidadJuridicaSurcosCS);


    }



    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unObjeto);
        EntityManagerHelper.commit();
    }

    //PERSISTENCIA DE USUARIOS
    @Test
    public void t1_persistirUsuarioA(){
        persistirUnObjeto(usuarioA);
    }

    @Test
    public void t2_persistirUsuarioB(){
        persistirUnObjeto(usuarioB);
    }

    //NO PROBAR ESTE
    @Test
    public void t3_persistirUsuarioC(){
        persistirUnObjeto(usuarioC);
    }

    @Test
    public void t4_persistirUsuarioAdmin(){
        persistirUnObjeto(usuarioAdmin);
    }

   @Test
    public void t5_persistirCategorias() {
        persistirUnObjeto(categoriaFachada);
       persistirUnObjeto(categoriaInterior);
       persistirUnObjeto(categoriaHumedad);
       persistirUnObjeto(categoriaServiciosGenerales);
       persistirUnObjeto(categoriaMueblesYUtiles);
       persistirUnObjeto(categoriaCoffeBreak);
       persistirUnObjeto(categoriaElectronicos);
       persistirUnObjeto(categoriaServiciosDeLuz);
       persistirUnObjeto(categoriaServiciosDeGas);
       //persistirUnObjeto(categoriaNecesario);
       persistirUnObjeto(categoriaGrande);
       persistirUnObjeto(categoriaExterior);


    }


    @Test
    public void t6_persistirCriterios() {
        persistirUnObjeto(criterioGastosDeMantenimiento);
        persistirUnObjeto(criterioLugarDeAplicacion);
        persistirUnObjeto(criterioCausante);
        persistirUnObjeto(criterioGastosGenerales);
        persistirUnObjeto(criterioElementosDeOficina);
        persistirUnObjeto(criterioMomentoDeUtilizacion);
        persistirUnObjeto(criterioTipoDeProducto);
        persistirUnObjeto(criterioTamanioDelGasto);
        persistirUnObjeto(criterioServicios);
    }


        @Test
    public void verificarUsuarioAPersistidoA(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
        TipoUsuario tipo = TipoUsuario.ESTANDAR;

        Assert.assertEquals("aroco", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());

    }

    @Test
    public void verificarUsuarioAPersistidoB(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 2);
        TipoUsuario tipo = TipoUsuario.ESTANDAR;

        Assert.assertEquals("rrojas", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());

    }

    @Test
    public void verificarUsuarioAPersistidoC(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 3);
        TipoUsuario tipo = TipoUsuario.ESTANDAR;

        Assert.assertEquals("jazul", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());

    }

    @Test
    public void verificarUsuarioAPersistidoAdmin(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 4);
        TipoUsuario tipo = TipoUsuario.ESTANDAR;

        Assert.assertEquals("Admin", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());

    }

}

