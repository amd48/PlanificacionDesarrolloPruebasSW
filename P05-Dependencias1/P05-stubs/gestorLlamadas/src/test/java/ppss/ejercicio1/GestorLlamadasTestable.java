package ppss.ejercicio1;

public class GestorLlamadasTestable extends GestorLlamadas
{
    int hora;

    @Override
    public int getHoraActual()
    {
        return hora;

    }

    public void setHoraActual(int hora)
    {
        this.hora = hora;

    }


}
