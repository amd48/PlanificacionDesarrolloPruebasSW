package ppss;

import Exceptions.ClienteWebServiceException;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PremioTest
{
    IMocksControl ctrl;
    ClienteWebService strictMock;
    Premio sutPartialMock;
    String real;
    String consultaPremio;
    String esperado;
    float generado;


    @BeforeEach void init()
    {
        ctrl = EasyMock.createStrictControl();

        sutPartialMock = partialMockBuilder(Premio.class)
                      .addMockedMethod("generaNumero")
                      .createMock(ctrl);

        strictMock = ctrl.createMock(ClienteWebService.class);
    }

    @Test
    void TestcompruebaPremio1() throws ClienteWebServiceException
    {
        //Datos de entrada
        generado = 0.07f;
        consultaPremio = "entrada final Champions";
        esperado = "Premiado con entrada final Champions";

        //Expectativas
        expect(sutPartialMock.generaNumero()).andReturn(generado);
        expect(strictMock.obtenerPremio()).andReturn(consultaPremio);

        ctrl.replay();

        sutPartialMock.cliente = strictMock;
        real = sutPartialMock.compruebaPremio();

        assertEquals(esperado, real);
        ctrl.verify();
    }

    @Test
    void TestcompruebaPremio2() throws ClienteWebServiceException
    {
        //Datos de entrada
        generado = 0.03f;
        esperado = "No se ha podido obtener el premio";

        //Expectativas
        expect(sutPartialMock.generaNumero()).andReturn(generado);
        expect(strictMock.obtenerPremio()).andThrow(new ClienteWebServiceException());

        ctrl.replay();


        sutPartialMock.cliente = strictMock;

        real = sutPartialMock.compruebaPremio();
        assertEquals(esperado, real);
        ctrl.verify();
    }

    @Test
    void TestcompruebaPremio3() throws ClienteWebServiceException
    {
        //Datos de entrada
        generado = 0.3f;
        esperado = "Sin premio";

        //Expectativas
        expect(sutPartialMock.generaNumero()).andReturn(generado);

        ctrl.replay();

        real = sutPartialMock.compruebaPremio();
        assertEquals(esperado, real);
        ctrl.verify();
    }
}
