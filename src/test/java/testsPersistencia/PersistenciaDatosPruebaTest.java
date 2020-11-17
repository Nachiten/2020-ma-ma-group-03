package testsPersistencia;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.apiMercadoLibre.Direccion;
import domain.entities.apiMercadoLibre.DireccionPostal;
import domain.entities.apiMercadoLibre.Estado;
import domain.entities.apiMercadoLibre.Pais;
import domain.entities.entidades.EntidadBase;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.Item;
import domain.entities.operaciones.MedioDePago;
import domain.entities.operaciones.TipoItem;
import domain.entities.operaciones.TipoMedioDePago;
import domain.entities.tipoEntidadJuridica.Empresa;
import domain.entities.tipoEntidadJuridica.Sector;
import domain.entities.tipoEntidadJuridica.TipoEntidadJuridica;
import domain.entities.usuarios.TipoUsuario;
import domain.entities.usuarios.Usuario;
import domain.entities.vendedor.Proveedor;
import org.junit.runners.MethodSorters;
import persistencia.db.EntityManagerHelper;
import org.junit.*;


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
    static private CategoriaCriterio categoriaServicioGenerales;
    static private CategoriaCriterio categoriaMueblesYUtiles;
    static private CategoriaCriterio categoriaCoffeBreak;
    static private CategoriaCriterio categoriaElectronicos;
    static private CategoriaCriterio categoriaServiciosDeLuz;
    static private CategoriaCriterio categoriaServiciosDeGas;
    static private CategoriaCriterio categoriaNecesario;
    static private CategoriaCriterio categoriaGrande;
    static private CategoriaCriterio categoriaExterior;


    //CRITERIOS
    static private Criterio criterioGastosDeMantenimiento;
    static private Criterio criterioLugarDeAplicacion;
    static private Criterio criterioCausante;
    static private Criterio criterioGastosGenerales;
    static private Criterio criterioElementosDeOficina;
    static private Criterio criterioMomentoDeUtilizacion;
    static private Criterio criterioTipoDeProducto;
    static private Criterio criterioTamanioDelGasto;





    //static private Criterio

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
    static private Proveedor proveedor1;
    static private Proveedor proveedor2;
    static private Proveedor proveedor3;
    static private Proveedor proveedor4;
    static private Proveedor proveedor5;
    static private Proveedor proveedor6;
    static private Proveedor proveedor7;

    //ITEMS
    static private Item item1;
    static private Item item2;
    static private Item item3;
    static private Item item4;
    static private Item item5;
    static private Item item6;
    static private Item item7;
    static private Item item8;
    static private Item item9;
    static private Item item10;
    static private Item item11;
    static private Item item12;
    static private Item item13;
    static private Item item14;
    static private Item item15;
    static private Item item16;


    @BeforeClass
    public static void init() {

        // PAIS

        argentina = new Pais("Argentina");
        estadosUnidos = new Pais("EEUU");
        mexico = new Pais("Mexico");
        // ESTADO
        estado1 = new Estado("BA");
        estado2 = new Estado("Nueva York");
        estado3 = new Estado("Ciudad de Mexico");


        //DIRECCION
        direccion1 = new Direccion("Av Medrano",51,0,"0");
        direccion2 = new Direccion("Liberty Ave",720,0,"0");
        direccion3 = new Direccion("Roberto Gayol",55,0,"0");
        direccion4 = new Direccion("Jeronimo Salguero",51,0,"0");

        //DIRECCION POSTAL
        direccionPostal1 = new DireccionPostal(direccion1,"Almagro",estado1, argentina);
        direccionPostal2 = new DireccionPostal(direccion2,"Brooklyn",estado2, estadosUnidos);
        direccionPostal3 = new DireccionPostal(direccion3,"",estado3, mexico);
        direccionPostal4 = new DireccionPostal(direccion4,"Palermo",estado1, argentina);

        //SECTOR

        sectorConstruccion = new Sector("Construccion");
        sectorAlojamiento = new Sector("Alojamiento");


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
        //me falta agregar la entidad juridica
        usuarioA = new Usuario(TipoUsuario.ESTANDAR, "aroco", "*_aroco20!-?", "Alejandro", "Roco", true, entidadJuridicaEAAFBA);
        usuarioB = new Usuario(TipoUsuario.ESTANDAR,"rrojas","*-_rrojas!?", "Rocio", "Rojas",false, entidadJuridicaEAAFBA);
        // usuarioC = new Usuario(TipoUsuario.ESTANDAR,"jazul","!-*jazul_!?","Julieta","Azul",1,);
        usuarioAdmin = new Usuario(TipoUsuario.ADMIN, "Admin", "admin", "Admin", "Root");

        //INSTANCIAS DE MEDIOS DE PAGO
        tarjetaDeCredito = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 1);
        medioDePagoTarjetaDeCredito = new MedioDePago(tarjetaDeCredito,"4509 9535 6623 3709");

        tarjetaDeDebito = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 2);
        medioDePagoTarjetaDebito = new MedioDePago(tarjetaDeDebito,"5031 7557 3453 0604");

        efectivo = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 3);
        medioDePagoEfectivo = new MedioDePago(tarjetaDeDebito,"5031 7557 3453 0604");

        //PROVEEDORES

        proveedor1 = new Proveedor("Pinturerías Serrentino","20-36975289-9");
        proveedor2 = new Proveedor("Edesur","26-36977895-2");
        proveedor3 = new Proveedor("Metrogas","21-16357895-9");
        proveedor4 = new Proveedor("Mitoas SA","23-16357895-9");
        proveedor5 = new Proveedor("Ingeniería Comercial SRL","26-16357895-9");
        proveedor6 = new Proveedor("Corralón Laprida SRL","17-12683657-1");
        proveedor7 = new Proveedor("Telas ZN","19-1267892-1");

         //ITEMS
        item1 = new Item(TipoItem.PRODUCTO,"PINTURA Z10 LATEX SUPERCUBRITIVO 20L",9625,1);
        item2 = new Item(TipoItem.PRODUCTO,"PINTURA LOXON FTES IMPERMEABILIZANTE 10L",6589.40f,1);
        item3 = new Item(TipoItem.PRODUCTO,"PINTURA BRIKOL PISOS NEGRO 4L",3738.29f,1);
        item4 = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE LUZ PERIODO JUNIO 2020",2100,1);
        item5 = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE GAS PERIODO JUNIO 2020",3500,1);
        item6 = new Item(TipoItem.PRODUCTO,"PAVA ELECTRICA SMARTLIFE 1,5 LTS 1850W",4500,3);
        item7 = new Item(TipoItem.PRODUCTO,"CAFETERA SMARTLIFE 1095 ACERO INOX.",6300,2);
        item8 = new Item(TipoItem.PRODUCTO,"TELEFONOS INALAMBRICOS MOTOROLA DUO BLANCO",8500,2);
        item9 = new Item(TipoItem.PRODUCTO,"CEMENTO LOMA NEGRA",704.40f,10);
        item10 = new Item(TipoItem.PRODUCTO,"ARENA FINA EN BOLSÓN X M30",3100,5);
        item11 = new Item(TipoItem.PRODUCTO,"HIERRO ACINDAR",891,4);
        item12 = new Item(TipoItem.PRODUCTO,"BLOQUE LADRILLO CEMENTO",227,800);
        item13 = new Item(TipoItem.PRODUCTO,"BLOQUE LADRILLO CEMENTO",227,800);
        item14 = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE LUZ PERIODO JUNIO 2020",1100,1);
        item15 = new Item(TipoItem.SERVICIO,"FACTURA SERVICIO DE GAS PERIODO JUNIO 2020",800,1);
        item16 = new Item(TipoItem.PRODUCTO,"CORTINAS BLACKOUT VINILICO 2 PAÑOS",4200,5);

      //CRITERIOS


    }
    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unObjeto);
        EntityManagerHelper.commit();
    }

    @Test
    public void t1_persistirUsuarioA(){
        persistirUnObjeto(usuarioA);
    }

    @Test
    public void t2_persistirUsuarioB(){
        persistirUnObjeto(usuarioB);
    }

    @Test
    public void t3_persistirUsuarioC(){
        persistirUnObjeto(usuarioC);
    }

    @Test
    public void t1_persistirUsuarioAdmin(){
        persistirUnObjeto(usuarioAdmin);
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

