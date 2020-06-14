package ppss;

import java.util.ArrayList;

public class Llanos
{
    static int centinel = 99999999;

    public Tramo buscarTramoLlanoMasLargo(ArrayList<Integer> lecturas)
    {
        int lectura_anterior = centinel;
        int longitud_tramo =  0,   longitudMax_tramo = 0;
        int origen_tramo   = -1,   origen_tramoMax   = -1;

        Tramo resultado = new Tramo(); //el origen y la longitud es CERO

        for(Integer dato : lecturas)
        {
            if (lectura_anterior == dato)
            {
                //detectamos un llano
                longitud_tramo ++;
                if (origen_tramo == -1 )
                {//marcamos su origen
                    origen_tramo = lecturas.indexOf(dato);
                }
            }
            else
            { //no es un llano o se termina el tramo llano
                longitud_tramo=0;
                origen_tramo=-1;
            }

            //actualizamos la longitud máxima del llano detectado
            if (longitud_tramo > longitudMax_tramo)
            {
                longitudMax_tramo = longitud_tramo;
                origen_tramoMax = origen_tramo;
            }
            lectura_anterior = dato;
        }
        switch (longitudMax_tramo)
        {
            case 0: break;
            default: resultado.setOrigen(origen_tramoMax);
                     resultado.setLongitud(longitudMax_tramo);
        }

        return resultado;
    }
}
