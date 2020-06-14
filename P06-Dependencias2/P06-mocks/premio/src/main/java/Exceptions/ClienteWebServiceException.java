package Exceptions;

public class ClienteWebServiceException extends Exception
{
    public ClienteWebServiceException()
    {
        super();
    }

    public ClienteWebServiceException(String esperado)
    {
        super(esperado);
    }
}
