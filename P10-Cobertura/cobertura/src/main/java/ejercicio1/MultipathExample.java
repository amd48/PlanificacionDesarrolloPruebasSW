package ejercicio1;

public class MultipathExample
{
    public int multiPath1(int a, int b, int c)
    {
        if (a > 5)
            c += a;

        if (b > 5)
            c += b;

        return c;
    }

    public int multiPath2(int a, int b, int c )
    {
        if ((a > 5) && (b < 5))
        {
            b += a;
        }
        if (c > 5)
        {
            c += b;
        }
        return c;
    }

    public int multiPath3(int a, int b, int c )
    {
        if ((a > 5) & (b < 5))
        {
            b += a;
        }
        if (c > 5)
        {
            c += b;
        }
        return c;
    }
}
    /*
    *              a > 5
    *               (1)
    *
    *  "false"                "true"
    *
    *               b > 5
    *               (2)
    *
    *  "false"                "true"
    *
    *               (3)

    //a1)
    //nodo a > b
    //nodo b > c
    //Condiciones + 1
    //CC = 3

    //Casos 1
    //a2) a = 6,  b= 6  -> 100% de lineas y 100% de condiciones

    //Es diferente porque CC es cota superior o maxima*/


