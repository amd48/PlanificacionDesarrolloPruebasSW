package ppss;

import Exceptions.ClienteWebServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class PremioTestEstado
{
    Premio sut;
    ClienteWebService stub;

    float generado;
    String consultaPremio;
    String esperado;
    String real;


    @BeforeEach void init()
    {
        sut = partialMockBuilder(Premio.class).addMockedMethod("generaNumero").createNiceMock();
        stub = createNiceMock(ClienteWebService.class);
    }

    @Test
    public void PremioTestEstado1()
    {
        generado = 0.07f;
        consultaPremio = "entrada final Champions";
        esperado = "Premiado con entrada final Champions";

        expect(sut.generaNumero()).andStubReturn(generado);
        assertDoesNotThrow(
                ()-> expect(stub.obtenerPremio()).andStubReturn(consultaPremio));

        sut.cliente = stub;

        replay(sut, stub);

        real = assertDoesNotThrow(()-> sut.compruebaPremio());

        assertEquals(esperado, real);
    }

    @Test
    public void PremioTestEstado2() throws ClienteWebServiceException
    {
        generado = 0.03f;
        esperado = "No se ha podido obtener el premio";
        expect(sut.generaNumero()).andStubReturn(generado);

        expect(stub.obtenerPremio()).andThrow(new ClienteWebServiceException(esperado));


        sut.cliente = stub;

        replay(sut, stub);

       real = assertDoesNotThrow(()-> sut.compruebaPremio());

       assertEquals(esperado, real);
    }

    @Test
    public void PremioTestEstado3() throws ClienteWebServiceException
    {
        generado = 0.3f;
        esperado = "Sin premio";

        expect(sut.generaNumero()).andStubReturn(generado);

        replay(sut);

       real = sut.compruebaPremio();

       assertEquals(esperado, real);
    }
}
