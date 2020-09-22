package testsPersistencia;

import Operaciones.TipoDocumentoComercial;
import Operaciones.TipoMedioDePago;
import Persistencia.db.EntityManagerHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

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
    public void persistirtipoDeMedioDePago(){
        persistirUnObjeto(tarjetaDeCredito);
        persistirUnObjeto(tarjetaDeDebito);
        persistirUnObjeto(ticket);
        persistirUnObjeto(atm);
        persistirUnObjeto(efectivo);
    }

    @Test
    public void persistirTipoDocumentoComercial(){
        persistirUnObjeto(factura);
        persistirUnObjeto(notaDeCredito);
        persistirUnObjeto(notaDeDebito);
        persistirUnObjeto(recibo);
        persistirUnObjeto(cheque);
        persistirUnObjeto(pagare);
        persistirUnObjeto(ordenDeCompra);
    }

}
