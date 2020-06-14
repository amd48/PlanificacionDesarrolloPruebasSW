package ppss;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest
{
    static String log = "ppss";
    static String NOlog = "xxxx";

    static String password = "ppss";
    static String NOpassword = "xxxx";

    static String userLuis = "Luis";
    static String NOuserPepe = "Pepe";

    static String isbn1 = "11111";
    static String isbn2 = "22222";
    static String NOisbn = "33333";

    static String [] isbns = {isbn1,isbn2};
    static String [] NOisbns = {NOisbn};

    static String ReservaException1 = "ERROR de permisos; ";
    static String ReservaException2 = "ISBN invalido:33333; ";
    static String ReservaException3 = "SOCIO invalido; ";
    static String ReservaException4 = "CONEXION invalida; ";

    private String login;
    private String pass ;
    private String user;

    ReservaTestable restest;

    @BeforeEach void init()
    {
        restest = new ReservaTestable();
    }

    @Test
    void TestRealizaReserva1()
    {
        login = NOlog;
        pass  = NOpassword;
        user = userLuis;
        String [] isbnInput ={isbns[0]};
        restest.setConexionInvalida (false);

        ReservaException excp = assertThrows(ReservaException.class,
                                             ()-> restest.realizaReserva(login, pass,user,isbnInput));
        assertEquals(ReservaException1, excp.getMessage());
    }

    @Test
    void TestRealizaReserva2()
    {
        login = log;
        pass  = password;
        user = userLuis;
        String [] isbnInput = isbns;
        restest.setConexionInvalida (false);

        assertDoesNotThrow(()-> restest.realizaReserva(login, pass,user, isbnInput));
    }

    @Test
    void TestRealizaReserva3()
    {
        login = log;
        pass  = password;
        user = userLuis;
        String [] isbnInput = NOisbns;
        restest.setConexionInvalida (false);

        ReservaException excp = assertThrows(ReservaException.class,
                                             ()-> restest.realizaReserva(login, pass,user,isbnInput));
        assertEquals(ReservaException2, excp.getMessage());
    }

    @Test
    void TestRealizaReserva4()
    {
        login = log;
        pass  = password;
        user = NOuserPepe;
        String [] isbnInput ={isbns[0]};
        restest.setConexionInvalida (false);

        ReservaException excp = assertThrows(ReservaException.class,
                                             ()-> restest.realizaReserva(login, pass,user,isbnInput));
        assertEquals(ReservaException3, excp.getMessage());
    }

    @Test
    void TestRealizaReserva5()
    {
        login = log;
        pass  = password;
        user = userLuis;
        String [] isbnInput ={isbns[0]};
        restest.setConexionInvalida (true);

        ReservaException excp = assertThrows(ReservaException.class,
                                             ()-> restest.realizaReserva(login, pass,user,isbnInput));
        assertEquals(ReservaException4, excp.getMessage());
    }
}