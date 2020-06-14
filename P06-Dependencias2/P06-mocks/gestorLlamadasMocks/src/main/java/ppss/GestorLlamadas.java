package ppss;

import org.omg.CORBA.PUBLIC_MEMBER;

public class GestorLlamadas
{
    static double TARIFA_NOCTURNA = 10.5;
    static double TARIFA_DIURNA = 20.8;


    public Calendario getCalendario()
    {
        Calendario c;
        return c = new Calendario();
    }

    public double calculaConsumo(int minutos)
    {
        Calendario c = getCalendario();
        int hora = c.getHoraActual();

        if( hora < 8  || hora > 20)
            return minutos*TARIFA_NOCTURNA;
        else
            return  minutos*TARIFA_DIURNA;
    }
}
