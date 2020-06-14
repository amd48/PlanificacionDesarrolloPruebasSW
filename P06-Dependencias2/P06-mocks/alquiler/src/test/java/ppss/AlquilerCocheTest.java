package ppss;

import Excp.CalendarioException;
import Excp.MensajeException;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlquilerCocheTest
{
    private static float precioDia = 10.0f;

    AlquilaCoches sutAlquilaCoche;
    IMocksControl ctrl;
    Servicio strictMockServ;
    Calendario strictMockCal;

    LocalDate date;
    TipoCoche tipo;
    Ticket esperado;
    Ticket real;
    int nDias;


    @BeforeEach void init()
    {
        ctrl = createStrictControl();

        sutAlquilaCoche  = partialMockBuilder(AlquilaCoches.class)
                        .addMockedMethod("getServicio")
                        .createMock(ctrl);

        strictMockServ = ctrl.createMock(Servicio.class);
        strictMockCal  = ctrl.createMock(Calendario.class);

        real = new Ticket();
        esperado = new Ticket();
    }

    @Test
    public void TestAlquilerCoche1() throws CalendarioException, MensajeException
    {
        tipo = TipoCoche.TURISMO;
        date =  LocalDate.of(2020,5 ,18);
        nDias = 10;
        esperado.setPrecio_final(75.f);

        //Primero se llama a getServicio
        expect(sutAlquilaCoche.getServicio()).andReturn(strictMockServ);

        //Despues consultamos precio con consultaPrecio
        expect(strictMockServ.consultaPrecio(tipo)).andReturn(precioDia);

        //Es festivo se llama n dias con dates distintos
        for(int i = 0; i < nDias; i++)
        {
             LocalDate dia = date.plusDays(i);

             Assertions.assertDoesNotThrow
            (
                 ()-> expect(strictMockCal.es_festivo(dia)).andReturn(false)
            );
        }

        sutAlquilaCoche.calendario = strictMockCal;

        ctrl.replay();
        real  = sutAlquilaCoche.calculaPrecio(tipo,date, nDias);

        assertEquals(esperado.getPrecio_final(), real.getPrecio_final());
        ctrl.verify();

    }

    @Test
    public void TestAlquilerCoche2() throws CalendarioException, MensajeException
    {
        //Datos de entrada
        tipo = TipoCoche.CARAVANA;
        date =  LocalDate.of(2020,6 ,19);
        nDias = 7;
        esperado.setPrecio_final(62.5f);

        expect(sutAlquilaCoche.getServicio()).andReturn(strictMockServ);
        expect(strictMockServ.consultaPrecio(tipo)).andReturn(precioDia);

        assertDoesNotThrow
        (
            ()->  expect(strictMockCal.es_festivo(anyObject()))
                    .andReturn(false)
                    .andReturn(true)
                    .andReturn(false).times(3)
                    .andReturn(true)
                    .andReturn(false)
        );

        ctrl.replay();
        sutAlquilaCoche.calendario = strictMockCal;
        real  = sutAlquilaCoche.calculaPrecio(tipo,date, nDias);
        assertEquals(esperado.getPrecio_final(), real.getPrecio_final());
        ctrl.verify();
    }

   @Test
    public void TestAlquilerCoche3() throws CalendarioException, MensajeException
    {

        String msgExcp1 = "Error en día: 2020-04-18; ";
        String msgExcp2 = "Error en día: 2020-04-21; ";
        String msgExcp3 = "Error en día: 2020-04-22; ";

        String sperado = msgExcp1+msgExcp2+msgExcp3;

        LocalDate dateExcp1 =  LocalDate.of(2020,4 ,18);
        LocalDate dateExcp2 =  LocalDate.of(2020,4 ,21);
        LocalDate dateExcp3  = LocalDate.of(2020,4 ,22);

        ArrayList<LocalDate> fechasExcp = new ArrayList<>();
        fechasExcp.add(dateExcp1);fechasExcp.add(dateExcp2);fechasExcp.add(dateExcp3);

        //Datos de entrada
        tipo = TipoCoche.TURISMO;
        date =  LocalDate.of(2020,4 ,17);
        nDias = 8;

        expect(sutAlquilaCoche.getServicio()).andReturn(strictMockServ);
        expect(strictMockServ.consultaPrecio(tipo)).andReturn(precioDia);

        for (int i = 0; i < nDias; i++)
        {
            LocalDate dia = date.plusDays(i);

            if(fechasExcp.contains(dia))
            {
                expect(strictMockCal.es_festivo(dia))
                       .andThrow(new CalendarioException());
            }
            else
            {
                assertDoesNotThrow
                (
                    ()-> expect(strictMockCal.es_festivo(dia)).andReturn(false)
                );
            }
        }

        sutAlquilaCoche.calendario = strictMockCal;

        ctrl.replay();

        MensajeException realExcp
                = assertThrows(MensajeException.class,
                ()-> sutAlquilaCoche.calculaPrecio(tipo,date, nDias));

        assertEquals(sperado, realExcp.getMessage());
        ctrl.verify();
    }
}
