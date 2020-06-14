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
    private void reorganizar(int indice, int[] array)
    {
        for (int i = indice; i < numElem-1 ; i++)
        {
            coleccion[i] = coleccion[i+1];
        }
        numElem--;
        coleccion[numElem] = 0;
    }
    //método para borrar un entero a la colección
    void delete(int elem)
    {
        if(elem < 1)
            System.out.println(elem +" Valor fuera de rango, no se permiten 0 en el array ");
        else
        {
            for (int i = 0; i < numElem; i++)
            {
                if(elem == coleccion[i])
                {
                    reorganizar(i, coleccion);
                    break;
                }
            }
        }
    }
    
}
