package ppss.ejercicio3;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest
{
    TipoCoche tp;
    LocalDate ld;
    int nd;

    CalendarioStub calendarioStub;
    Ticket ticketEsperado;
    AlquilaCochesTestable alquilerCocheTestable;
    ServicioStub servStub;

    @BeforeEach void init()
    {
        calendarioStub = new CalendarioStub();
        ticketEsperado = new Ticket();
        alquilerCocheTestable = new AlquilaCochesTestable();
        servStub = new ServicioStub();
    }

    @Test
    void TestcalcularPrecio1() throws MensajeException
    {
        tp = TipoCoche.TURISMO;
        ld = LocalDate.of(2020, Month.MAY, 18);
        nd = 10;
        ticketEsperado.setPrecio_final(75.0f);

        alquilerCocheTestable.setIser(servStub);
        alquilerCocheTestable.calendario = calendarioStub;
        assertEquals(ticketEsperado.getPrecio_final(),
                      alquilerCocheTestable.calcularPrecio(tp, ld, nd).getPrecio_final());
    }

    @Test
    void TestcalcularPrecio2() throws MensajeException
    {
        tp = TipoCoche.CARAVANA;
        ld = LocalDate.of(2020, Month.JUNE, 19);
        nd = 7;
        ticketEsperado.setPrecio_final(62.5f);

        calendarioStub.festivos.add(LocalDate.of(2020, Month.JUNE, 20));
        calendarioStub.festivos.add(LocalDate.of(2020, Month.JUNE, 24));


        alquilerCocheTestable.setIser(servStub);//Solución a dependencia Iservice serv = new Service()
        alquilerCocheTestable.calendario = calendarioStub;//Solución a dependencia calendario.esfestivo()


        assertEquals(ticketEsperado.getPrecio_final(),
                      alquilerCocheTestable.calcularPrecio(tp, ld, nd).getPrecio_final());
    }

    @Test
    void TestcalcularPrecio3() throws MensajeException
    {
        tp = TipoCoche.TURISMO;
        ld = LocalDate.of(2020, Month.APRIL, 17);
        nd = 8;

        calendarioStub.diasExcepciones.add(LocalDate.of(2020, Month.APRIL, 18));
        calendarioStub.diasExcepciones.add(LocalDate.of(2020, Month.APRIL, 21));
        calendarioStub.diasExcepciones.add(LocalDate.of(2020, Month.APRIL, 22));

        alquilerCocheTestable.setIser(servStub);//Solución a dependencia Iservice serv = new Service()
        alquilerCocheTestable.calendario = calendarioStub;//Solución a dependencia calendario.esfestivo()

        String mensajeEsperado = ("Error en dia: 2020-04-18; Error en dia: 2020-04-21; Error en dia: 2020-04-22; ");

        Exception excp = assertThrows(MensajeException.class,
                                      ()-> alquilerCocheTestable.calcularPrecio(tp, ld, nd));

        assertEquals(mensajeEsperado, excp.getMessage());
    }
}