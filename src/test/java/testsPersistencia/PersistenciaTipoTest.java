package testsPersistencia;

import domain.entities.operaciones.TipoDocumentoComercial;
import domain.entities.operaciones.TipoMedioDePago;
import persistencia.db.EntityManagerHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenciaTipoTest {

    static private TipoMedioDePago tarjetaDeCredito;
    static private TipoMedioDePago tarjetaDeDebito;
    static private TipoMedioDePago ticket;
    static private TipoMedioDePago atm;
    static private TipoMedioDePago efectivo;

    static private TipoDocumentoComercial factura;
    static private TipoDocumentoComercial notaDeDebito;
    static private TipoDocumentoComercial notaDeCredito;
    static private TipoDocumentoComercial recibo;
    static private TipoDocumentoComercial cheque;
    static private TipoDocumentoComercial pagare;
    static private TipoDocumentoComercial ordenDeCompra;

    @BeforeClass
    public static void init() throws IOException {

        tarjetaDeCredito = new TipoMedioDePago("Tarjeta de credito");
        tarjetaDeDebito = new TipoMedioDePago("Tarjeta de debito");
        ticket = new TipoMedioDePago("Ticket");
        atm = new TipoMedioDePago("ATM");
        efectivo = new TipoMedioDePago("Efectivo");

        factura = new TipoDocumentoComercial("Factura");
        notaDeDebito = new TipoDocumentoComercial("Nota de debito");
        notaDeCredito = new TipoDocumentoComercial("Nota de credito");
        recibo = new TipoDocumentoComercial("Recibo");
        cheque = new TipoDocumentoComercial("Cheque");
        pagare = new TipoDocumentoComercial("Pagare");
        ordenDeCompra = new TipoDocumentoComercial("Orden de compra");
    }

    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void t1_persistirtipoDeMedioDePago(){
        persistirUnObjeto(tarjetaDeCredito);
        persistirUnObjeto(tarjetaDeDebito);
        persistirUnObjeto(ticket);
        persistirUnObjeto(atm);
        persistirUnObjeto(efectivo);
    }

    @Test
    public void t2_persistirTipoDocumentoComercial(){
        persistirUnObjeto(factura);
        persistirUnObjeto(notaDeCredito);
        persistirUnObjeto(notaDeDebito);
        persistirUnObjeto(recibo);
        persistirUnObjeto(cheque);
        persistirUnObjeto(pagare);
        persistirUnObjeto(ordenDeCompra);
    }

    @Test
    public void t3_verificarSiSePersistioTiposDeMedioDePago(){

        TipoMedioDePago unaTarjetaDeCredito = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 1);
        TipoMedioDePago unaTarjetaDeDebito = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 2);
        TipoMedioDePago unTicket = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 3);
        TipoMedioDePago unATM = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 4);
        TipoMedioDePago enEfectivo = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 5);

        Assert.assertEquals("Tarjeta de credito", unaTarjetaDeCredito.getTipoPago());
        Assert.assertEquals("Tarjeta de debito", unaTarjetaDeDebito.getTipoPago());
        Assert.assertEquals("Ticket", unTicket.getTipoPago());
        Assert.assertEquals("ATM", unATM.getTipoPago());
        Assert.assertEquals("Efectivo", enEfectivo.getTipoPago());

    }

    @Test
    public void t4_verificarSiSePersistioTiposDeDocumentoComercial(){

        TipoDocumentoComercial unaFactura = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 1);
        TipoDocumentoComercial unaNotaDeCredito = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 2);
        TipoDocumentoComercial unaNotaDeDebito = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 3);
        TipoDocumentoComercial unRecibo = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 4);
        TipoDocumentoComercial unCheque = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 5);
        TipoDocumentoComercial unPagare = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 6);
        TipoDocumentoComercial unaOrdenDeCompra = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 7);

        Assert.assertEquals("Factura", unaFactura.getNombre());
        Assert.assertEquals("Nota de credito", unaNotaDeCredito.getNombre());
        Assert.assertEquals("Nota de debito", unaNotaDeDebito.getNombre());
        Assert.assertEquals("Recibo", unRecibo.getNombre());
        Assert.assertEquals("Cheque", unCheque.getNombre());
        Assert.assertEquals("Pagare", unPagare.getNombre());
        Assert.assertEquals("Orden de compra", unaOrdenDeCompra.getNombre());
    }

}
