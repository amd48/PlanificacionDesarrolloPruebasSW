package ppss;

public class Tramo
{
    int origen;
    int longitud;

    public Tramo()
    {
        origen   = 0;
        longitud = 0;
    }

    public Tramo(int origen, int longitud)
    {
        this.origen   = origen;
        this.longitud = longitud;
    }


    ////////////////////////////////////////////////////////////////////////////////////////
    /*GETTERS && SETTERS*/
    /*GET Y SET DE ORIGEN*/
    public  int getOrigen(){return origen;}

    public void setOrigen(int origen)
    {
        this.origen = origen;
    }

    /*GET Y SET DE LONGITUD*/
    public  int getLongitud(){return longitud;}

    public void setLongitud(int longitud)
    {
        this.longitud = longitud;
    }
}

