package ppss;

import org.junit.jupiter.api.Test;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest
{
            /*Mensajes para las excepciones*/
    static String m1 = "No hay elementos en la colección";
    static String m2 = "El valor a borrar debe ser > cero";
    static String m3 = "Colección vacía. Y el valor a borrar debe ser > cero";
    static String m4 = "Elemento no encontrado";

    int numElementosEsperado;
    int numElementosReal;


    @Test
    void Test1Delete1() throws DataException
    {
        int [] arrayEsperado = {1, 3, 7, 0, 0, 0, 0, 0, 0, 0};
        numElementosEsperado = 3;

        int [] arrayReal = {1, 3, 5, 7, 0, 0, 0, 0, 0, 0};
        DataArray DAreal = new DataArray(arrayReal, 4);

        DAreal.delete(5);

        assertAll("TestDelete1",
                ()-> assertArrayEquals(arrayEsperado, DAreal.getColeccion()),
                ()-> assertEquals(numElementosEsperado, DAreal.size())
        );


    }

    @Test
    void Test1Delete2() throws DataException
    {
        int [] arrayEsperado = {1, 3, 5, 7, 0, 0, 0, 0, 0, 0};
        numElementosEsperado = 4;

        int [] arrayReal = {1, 3, 3, 5, 7, 0, 0, 0, 0, 0};
        DataArray DAreal = new DataArray(arrayReal, 5);

        DAreal.delete(3);

        assertAll("TestDelete2",
                ()-> assertArrayEquals(arrayEsperado, DAreal.getColeccion()),
                ()-> assertEquals(numElementosEsperado, DAreal.size())
        );


    }


    @Test
    void Test1Delete3() throws DataException
    {
        int [] arrayEsperado = {1, 2, 3, 5, 6, 7, 8, 9, 10, 0};
        numElementosEsperado = 9;

        int [] arrayReal = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        DataArray DAreal = new DataArray(arrayReal, 10);

        DAreal.delete(4);

        assertAll("TestDelete3",
                ()-> assertArrayEquals(arrayEsperado, DAreal.getColeccion()),
                ()-> assertEquals(numElementosEsperado, DAreal.size())
        );
    }

    @Test
    void Test1DeleteM1() throws DataException
    {
        DataArray DAreal = new DataArray();

        Throwable excp = assertThrows(DataException.class,
                ()-> DAreal.delete(8));
        assertEquals(m1, excp.getMessage());
    }

    @Test
    void Test1DeleteM2() throws DataException
    {

        int [] arrayReal = {1, 3, 5, 7, 0, 0, 0, 0, 0, 0};
        DataArray DAreal = new DataArray(arrayReal, 4);

        Throwable excp = assertThrows(DataException.class, ()-> DAreal.delete(-5));
        assertEquals(m2, excp.getMessage());
    }

    @Test
    void Test1DeleteM3() throws DataException
    {
        DataArray DAreal = new DataArray();

        Throwable excp = assertThrows(DataException.class, ()-> DAreal.delete(0));
        assertEquals(m3, excp.getMessage());
    }

    @Test
    void Test1DeleteM4() throws DataException
    {

        int [] arrayReal = {1, 3, 5, 7, 0, 0, 0, 0, 0, 0};
        DataArray DAreal = new DataArray(arrayReal, 4);

        Throwable excp = assertThrows(DataException.class, ()-> DAreal.delete(8));
        assertEquals(m4, excp.getMessage());
    }


}