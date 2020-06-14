package ppss.ejercicio3;

import java.time.LocalDate;

public class AlquilaCochesTestable extends AlquilaCoches
{
    IService iser;

    @Override
    public IService getService()
    {
        return iser;
    }

    public void setIser(IService iser)
    {
        this.iser = iser;
    }
}
