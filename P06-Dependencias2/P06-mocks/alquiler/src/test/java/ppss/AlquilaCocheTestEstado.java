package ppss;

import Excp.CalendarioException;
import Excp.MensajeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlquilaCocheTestEstado
{
    private static float precioDia = 10.0f;

    AlquilaCoches sut;
    Servicio stubServ;
    Calendario stubCal;

    LocalDate date;
    TipoCoche tipo;
    Ticket esperado;
    Ticket real;
    int nDias;

    @BeforeEach void init()
    {
        sut = partialMockBuilder(AlquilaCoches.class).addMockedMethod("getServicio").createNiceMock();
        stubServ = createNiceMock(Servicio.class);
        stubCal= createNiceMock(Calendario.class);
        esperado = new Ticket();
        real = new Ticket();
    }

    @Test
    public void Test1EstadoAlquilaCoches() throws CalendarioException, MensajeException
    {
        tipo = TipoCoche.TURISMO;
        date = LocalDate.of(2020,05, 18);
        nDias = 10;
        esperado.setPrecio_final(75.f);

        expect(sut.getServicio()).andStubReturn(stubServ);
        expect(stubServ.consultaPrecio(tipo)).andStubReturn(precioDia);
        expect(stubCal.es_festivo(anyObject())).andStubReturn(false);

        replay(sut, stubCal, stubServ);
        sut.calendario = stubCal;


        real = sut.calculaPrecio(tipo,date,nDias);

        assertEquals(esperado.getPrecio_final(), real.getPrecio_final());

    }
}
