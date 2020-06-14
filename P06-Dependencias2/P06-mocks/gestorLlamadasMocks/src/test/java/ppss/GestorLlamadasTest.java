package ppss;


import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorLlamadasTest
{
    GestorLlamadas partialMock;
    Calendario strictMock;

    int minutos;
    int hora;
    double esperado;
    double real;
    IMocksControl ctrl;

    @BeforeEach void init()
    {
        ctrl = EasyMock.createStrictControl();

        partialMock = partialMockBuilder(GestorLlamadas.class)
                      .addMockedMethod("getCalendario")
                      .createMock(ctrl);

         strictMock = ctrl.createMock(Calendario.class);
    }

    @Test
    void TestCalculaConsumo1()
    {
        //Datos de entrada
        minutos = 22;
        hora = 10;
        esperado = 457.6;

        //Expectativas¡
        expect(partialMock.getCalendario()).andReturn(strictMock);
        expect(strictMock.getHoraActual()).andReturn(hora);



        //Record Mode -> Replay Mode  (Siempre antes de ejecutar el mock)
        ctrl.replay();

        //Ejecucion unidad a probar utilizandpo el mock creado
        real = partialMock.calculaConsumo(minutos);

        ctrl.verify();
        //Verificamos que se ha invocado el mock desde el SUT
        assertEquals(esperado, real);

    }

     @Test
    void TestCalculaConsumo2()
    {
        //Datos de entrada
        minutos = 13;
        hora = 21;
        esperado = 136.5;

        //Expectativas¡
        expect(partialMock.getCalendario()).andReturn(strictMock);
        expect(strictMock.getHoraActual()).andReturn(hora);

        //Record Mode -> Replay Mode  (Siempre antes de ejecutar el mock)
       ctrl.replay();

        //Ejecucion unidad a probar utilizandpo el mock creado
        real = partialMock.calculaConsumo(minutos);

        //Verificamos que se ha invocado el mock desde el SUT
        assertEquals(esperado, real);
        ctrl.verify();
    }


}