package ppss;

import Exc.IsbnInvalido;
import Exc.JDBCException;
import Exc.ReservaException;
import Exc.SocioInvalido;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest
{
    IMocksControl ctrl;
    Reserva sutPartialMock;
    IOperacionBO strictMockIOper;
    FactoriaBOs strictMockFactBO;
    Factoria  factoria;

    static String re1 = "Error de permisos; ";
    static String re2 = "ISBN invalido:11111; ";
    static String re3 = "SOCIO invalido; ";
    static String re4 = "Conexion invalida; ";

    static String logAndPass = "ppss";
    static String socio = "Pepe";
    static String noSocio = "Luis";

    String login;
    String pass;
    String []isbns1 = {"11111"};
    String []isbns2 = {"22222"};
    String []isbns23 = {"22222", "33333"};



    @BeforeEach void init()
    {
        ctrl = createStrictControl();

        sutPartialMock = partialMockBuilder(Reserva.class)
                .addMockedMethod("compruebaPermisos").createMock(ctrl);

        strictMockFactBO   = ctrl.createMock(FactoriaBOs.class);
        strictMockIOper    = ctrl.createMock(IOperacionBO.class);

        factoria = new Factoria();
        factoria.setFactoriaBOs(strictMockFactBO);

    }

    @Test
    void TestReserva1() throws ReservaException
    {
        login = "xxxx";
        pass  = "xxxx";


        expect(sutPartialMock.compruebaPermisos(login, pass, Usuario.BIBLIOTECARIO)).andReturn(false);

        ctrl.replay();

        ReservaException realExcp = assertThrows(ReservaException.class,
            ()-> sutPartialMock.realizaReserva(login, pass, socio, isbns2));

        assertEquals(re1, realExcp.getMessage());

        ctrl.verify();
    }

    @Test
    void TestReserva2() throws SocioInvalido, JDBCException, IsbnInvalido
    {
        login = logAndPass;
        pass  = logAndPass;

        expect(sutPartialMock.compruebaPermisos(login,pass, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(strictMockFactBO.getOperacion()).andReturn(strictMockIOper);


        for(int i = 0; i < isbns23.length; i++)
        {
            int finalI = i;
            assertDoesNotThrow(
                    ()-> strictMockIOper.operacionReserva(socio, isbns23[finalI])
            );
        }

        ctrl.replay();

        assertDoesNotThrow(
                ()-> sutPartialMock.realizaReserva(login, pass, socio, isbns23)
        );


        ctrl.verify();
    }

    @Test
    void TestReserva3() throws SocioInvalido, JDBCException, IsbnInvalido
    {
        login = logAndPass;
        pass  = logAndPass;

        expect(sutPartialMock.compruebaPermisos(login,pass, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(strictMockFactBO.getOperacion()).andReturn(strictMockIOper);


        assertDoesNotThrow(
               ()-> strictMockIOper.operacionReserva(socio, isbns1[0])
        );
        expectLastCall().andThrow(new IsbnInvalido());

        ctrl.replay();

        ReservaException realExc = assertThrows(
                ReservaException.class,
                ()-> sutPartialMock.realizaReserva(login, pass, socio, isbns1)
        );

        assertEquals(re2, realExc.getMessage());

        ctrl.verify();
    }

    @Test
    void TestReserva4() throws SocioInvalido, JDBCException, IsbnInvalido
    {
        login = logAndPass;
        pass  = logAndPass;

        expect(sutPartialMock.compruebaPermisos(login,pass, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(strictMockFactBO.getOperacion()).andReturn(strictMockIOper);

        assertDoesNotThrow(
               ()-> strictMockIOper.operacionReserva(noSocio, isbns2[0])
        );
        expectLastCall().andThrow(new SocioInvalido());
        ctrl.replay();

        ReservaException realExc = assertThrows(ReservaException.class,
                ()-> sutPartialMock.realizaReserva(login, pass, noSocio, isbns2)
        );

        assertEquals(re3, realExc.getMessage());

        ctrl.verify();
    }

    @Test
    void TestReserva5() throws SocioInvalido, JDBCException, IsbnInvalido
    {
        login = logAndPass;
        pass  = logAndPass;

        expect(sutPartialMock.compruebaPermisos(login,pass, Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(strictMockFactBO.getOperacion()).andReturn(strictMockIOper);

        assertDoesNotThrow(
               ()-> strictMockIOper.operacionReserva(socio, isbns2[0])
        );
        expectLastCall().andThrow(new JDBCException());
        ctrl.replay();

        ReservaException realExc = assertThrows(ReservaException.class,
                ()-> sutPartialMock.realizaReserva(login, pass, socio, isbns2)
        );

        assertEquals(re4, realExc.getMessage());

        ctrl.verify();
    }

}