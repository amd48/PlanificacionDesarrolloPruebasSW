package ppss;

public class DataArray
{
    private int[] coleccion;
    private int numElem;

    //Constructor
    public DataArray()
    {
        coleccion = new int[10];
        numElem=0;
    }

    //Constructor
    public DataArray(int[] datos, int contador)
    {
        coleccion = datos;
        numElem=contador;
    }

    public int size() {
        return numElem;
    }

    //getter
    public int[] getColeccion() {
        return coleccion;
    }

    //método para añadir un entero a la colección
    public void add(int elem)
    {
        if (numElem < (coleccion.length))
        {
            coleccion[numElem]= elem;
            numElem++;
            System.out.println("added "+elem +" ahora hay "+numElem+ " elementos");
        }
        else
        {
            System.out.println(elem +" ya no cabe. Ya has añadido "+numElem+" elementos");
        }
        /*if (numElem >= coleccion.length)
            System.out.println(elem +" ya no cabe. Ya has añadido "+numElem+" elementos");
        else if (elem < 1)
            System.out.println(elem +" Se espera numeros mayores que 0 ");
        else
        {
            coleccion[numElem]= elem;
            numElem++;
            System.out.println("added "+elem +" ahora hay "+numElem+ " elementos");
        }*/
    }
    public int[] delete(int elem) throws DataException
    {
        int i = 0;
        boolean encontrado = false;


        if ((numElem == 0) && (elem <= 0))
        {
            throw new DataException("Colección vacía. Y el valor a borrar debe ser > cero");
        }
        else if (numElem == 0)
        {
            throw new DataException("No hay elementos en la colección");
        }
        else if (elem <= 0)
        {
            throw new DataException("El valor a borrar debe ser > cero");

        }
        else
        {
            while (i < numElem && !encontrado)
            {
                if (coleccion[i] == elem)
                {
                    encontrado = true;
                }
                else
                {
                    i++;
                }
            }
            if (encontrado)
            {
                while (i < (numElem - 1) && coleccion[i] != 0)
                {
                    coleccion[i] = coleccion[i + 1];
                    i++;
                }
                if (i == (numElem - 1))
                {
                    coleccion[numElem - 1] = 0;
                }
                numElem--;
            }
            else
            {
                throw new DataException("Elemento no encontrado");
            }
        }
        return coleccion;
    }

}
