package ppss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class GestorLlamadasTestEstado
{
    int minutos;
    int hora;
    double esperado;
    double real;
    GestorLlamadas sut;
    Calendario stub;

    @BeforeEach void init()
    {
        sut = partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").createNiceMock();
        stub = createNiceMock(Calendario.class);
    }
    @Test
    void GestorLlamadasTestEstado1()
    {
        //Datos de entrada
        minutos = 22;
        hora = 10;
        esperado = 457.6;

        expect(sut.getCalendario()).andStubReturn(stub);
        expect(stub.getHoraActual()).andStubReturn(hora);
        replay(sut, stub);

        real = sut.calculaConsumo(minutos);

        assertEquals(esperado, real);
    }

     @Test
    void GestorLlamadasTestEstado2()
    {
        //Datos de entrada
        minutos = 13;
        hora = 21;
        esperado = 136.5;

        expect(sut.getCalendario()).andStubReturn(stub);
        expect(stub.getHoraActual()).andStubReturn(hora);
        replay(sut, stub);

        real = sut.calculaConsumo(minutos);

        assertEquals(esperado, real);
    }

}