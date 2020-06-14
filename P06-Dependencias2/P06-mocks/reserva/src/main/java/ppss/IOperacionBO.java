package ppss;

import Exc.IsbnInvalido;
import Exc.JDBCException;
import Exc.SocioInvalido;

public interface IOperacionBO
{

    void operacionReserva(String socio, String isbn) throws IsbnInvalido, SocioInvalido, JDBCException;
}
