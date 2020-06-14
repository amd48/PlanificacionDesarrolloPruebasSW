package ppss.ejercicio3;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarioStub extends Calendario
{
    ArrayList<LocalDate> festivos = new ArrayList<LocalDate>();
    ArrayList<LocalDate> diasExcepciones = new ArrayList<LocalDate>();

    public void setFestivos(ArrayList<LocalDate> festivos)
    {
        this.festivos = festivos;
    }

    public ArrayList<LocalDate> getFestivos()
    {
        return festivos;
    }

    @Override
    public boolean es_festivo(LocalDate ld) throws CalendarioException
    {
        if((festivos.isEmpty() || festivos == null) && (diasExcepciones.isEmpty() || diasExcepciones == null))
            return false;

        else if (diasExcepciones.contains(ld))
        {
            throw new CalendarioException();
        }

        else if (festivos.contains(ld))
            return true;

        else
            return false;
    }
}
