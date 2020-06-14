package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import static ppss.ReservaTest.isbns;
import static ppss.ReservaTest.userLuis;

public class OperacionStub extends Operacion
{
    private boolean conexionInvalida;

    public void setConexionInvalida(boolean conexionInvalida)
    {
        this.conexionInvalida = conexionInvalida;
    }
    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException
    {
        if(isbns[0] != isbn && isbns[1] != isbn)
            throw new IsbnInvalidoException();

        else if (socio != userLuis)
            throw new SocioInvalidoException();

        else if(conexionInvalida)
            throw new JDBCException();
    }

}
