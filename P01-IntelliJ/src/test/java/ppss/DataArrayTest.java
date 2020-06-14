package ppss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest
{
    int elem;
    int elemReales;
    int elemEsperados;

    int[] arrayEsperado;

    @Test
    void testAdd1()
    {
        elem = 24;

        elemEsperados = 1;
        arrayEsperado = new int[]{24, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        DataArray arrayReal = new DataArray();
        arrayReal.add(elem);
        elemReales = arrayReal.size();

        assertArrayEquals(arrayReal.getColeccion(), arrayEsperado);
        assertEquals(elemEsperados, elemReales);
    }

    @Test
    void testAdd2()
    {
        DataArray arrayReal = new DataArray();
        arrayReal.add(24);
        arrayReal.add(22);
        elem = 18;

        elemEsperados = 3;
        arrayEsperado = new int[]{24, 22, 18, 0, 0, 0, 0, 0, 0, 0};
        arrayReal.add(elem);

        elemReales = arrayReal.size();

        assertArrayEquals(arrayReal.getColeccion(), arrayEsperado);
        assertEquals(elemEsperados, elemReales);
    }
    @Test
    void testAdd3()
    {
        DataArray arrayReal = new DataArray();
        arrayReal.add(24);arrayReal.add(22);arrayReal.add(18);
        arrayReal.add(10);arrayReal.add(20);arrayReal.add(50);
        arrayReal.add(12);arrayReal.add(11);arrayReal.add(11);
        arrayReal.add(9);

        elem = 99;

        elemEsperados = 10;
        arrayEsperado = new int[]{24, 22, 18, 10, 20, 50, 12, 11, 11, 9};

        arrayReal.add(elem);
        elemReales = arrayReal.size();

        assertArrayEquals(arrayReal.getColeccion(), arrayEsperado);
        assertEquals(elemEsperados, elemReales);
    }
    @Test
    void testDelete1()
    {
        elem = 24;

        elemEsperados = 0;
        arrayEsperado = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        DataArray arrayReal = new DataArray();
        arrayReal.add(elem);
        arrayReal.delete(elem);
        elemReales = arrayReal.size();

        assertArrayEquals(arrayReal.getColeccion(), arrayEsperado);
        assertEquals(elemEsperados, elemReales);
    }

    @Test
    void testDelete2()
    {
        DataArray arrayReal = new DataArray();
        arrayReal.add(24);
        arrayReal.add(22);
        arrayReal.add(18);
        elem = 18;

        arrayReal.delete(elem);

        elemEsperados = 2;
        arrayEsperado = new int[]{24, 22, 0, 0, 0, 0, 0, 0, 0, 0};

        elemReales = arrayReal.size();

        assertArrayEquals(arrayReal.getColeccion(), arrayEsperado);
        assertEquals(elemEsperados, elemReales);
    }
    @Test
    void testDelete3()
    {
        DataArray arrayReal = new DataArray();
        arrayReal.add(24);arrayReal.add(22);arrayReal.add(18);
        arrayReal.add(10);arrayReal.add(20);arrayReal.add(50);
        arrayReal.add(12);arrayReal.add(11);arrayReal.add(11);
        arrayReal.add(9);

        elem = 24;
        arrayReal.delete(elem);
        elemEsperados = 9;
        arrayEsperado = new int[]{22, 18, 10, 20, 50, 12, 11, 11, 9, 0};

        elemReales = arrayReal.size();

        assertArrayEquals(arrayReal.getColeccion(), arrayEsperado);
        assertEquals(elemEsperados, elemReales);
    }
    @Test
    void testDelete4()
    {
        DataArray arrayReal = new DataArray();
        arrayReal.add(24);arrayReal.add(22);arrayReal.add(18);
        arrayReal.add(10);arrayReal.add(20);arrayReal.add(50);
        arrayReal.add(12);arrayReal.add(11);arrayReal.add(11);
        arrayReal.add(9);

        elem = 9;
        arrayReal.delete(elem);

        elemEsperados = 9;
        arrayEsperado = new int[]{24, 22, 18, 10, 20, 50, 12, 11, 11, 0};

        elemReales = arrayReal.size();

        assertArrayEquals(arrayReal.getColeccion(), arrayEsperado);
        assertEquals(elemEsperados, elemReales);
    }


}