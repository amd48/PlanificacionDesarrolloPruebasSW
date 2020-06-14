package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayParamTest
{

    static String m1 = "No hay elementos en la colección";
    static String m2 = "El valor a borrar debe ser > cero";
    static String m3 = "Colección vacía. Y el valor a borrar debe ser > cero";
    static String m4 = "Elemento no encontrado";

    @ParameterizedTest
    @MethodSource("casosDePruebaDelete")
    public void TestParamDelete(int[] arrayEsperado, int numElemEsperado,
                                int[] arrayReal, int numElemReal,
                                int elementoAborrar, int nPrueba) throws DataException
    {
        DataArray DAreal = new DataArray(arrayReal, numElemReal);
        DAreal.delete(elementoAborrar);

        assertAll("Prueba parametrizada delete numero "+nPrueba,
                ()-> assertArrayEquals(arrayEsperado, DAreal.getColeccion()),
                ()->assertEquals(numElemEsperado, DAreal.size()));
    }

    private static Stream<Arguments> casosDePruebaDelete()
    {
        int cont = 0;
        return Stream.of(
                Arguments.of(new int[] {1, 3, 7, 0, 0, 0, 0, 0, 0, 0}, 3,
                             new int[] {1, 3, 5, 7, 0, 0, 0, 0, 0, 0}, 4, 5, cont++),

                Arguments.of(new int[] {1, 3, 5, 7, 0, 0, 0, 0, 0, 0}, 4,
                             new int[] {1, 3, 3, 5, 7, 0, 0, 0, 0, 0}, 5, 3, cont++),

                Arguments.of(new int[] {1, 2, 3, 5, 6, 7, 8, 9, 10, 0}, 9,
                             new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10, 4, cont++)
        );
    }


    @ParameterizedTest
    @MethodSource("casosDePruebaDeleteExceptions")
    public void TestParamDeleteExceptions(String mensaje, int[] array, int numElem, int elem, int cont)
    {
        DataArray DAreal = new DataArray(array, numElem);
        Throwable excp = assertThrows(DataException.class, ()-> DAreal.delete(elem));
        assertEquals(mensaje, excp.getMessage());
    }

    private static Stream<Arguments> casosDePruebaDeleteExceptions()
    {
        int cont=0;
        return Stream.of(
                Arguments.of(m1, new int [] {0,0,0,0,0,0,0,0,0,0}, 0, 8, cont++),
                Arguments.of(m2, new int [] {1,3,5,7,0,0,0,0,0,0}, 4, -5, cont++),
                Arguments.of(m3, new int [] {0,0,0,0,0,0,0,0,0,0}, 0, 0, cont++),
                Arguments.of(m4, new int [] {1,3,5,7,0,0,0,0,0,0}, 4, 8, cont++)
        );
    }

}