package Excp;

import java.time.LocalDate;

public class CalendarioException extends Exception
{
    public CalendarioException(String s)
    {
        super(s);
    }

    public CalendarioException()
    {
        super();
    }
}
