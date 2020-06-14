package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@Tag("Parametrizados")
public class LlanosParamTest
{
    Llanos llanos = new Llanos();
    Tramo tramoReal = new Tramo();


    @ParameterizedTest
    @MethodSource("casosDePruebaLlanosP")
    void testParametrizado(Tramo tramo, ArrayList<Integer> lecturas, int cont)
    {
        tramoReal = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertAll("TestParametrizado " + cont,
                ()-> assertEquals(tramo.getOrigen(), tramoReal.getOrigen()),
                ()-> assertEquals(tramo.getLongitud(), tramoReal.getLongitud()));

    }

    private static Stream<Arguments> casosDePruebaLlanosP()
    {
        int cont = 0;
        return Stream.of(
                Arguments.of((new Tramo(0,0)),
                             (new ArrayList<Integer>(Arrays.asList(3))), cont++),

                Arguments.of((new Tramo(0,3)),
                             (new ArrayList<Integer>(Arrays.asList(100, 100, 100, 100))), cont++),

                Arguments.of((new Tramo(2,2)),
                             (new ArrayList<Integer>(Arrays.asList(120, 140, 180, 180, 180))), cont++),

                Arguments.of((new Tramo(0,0)),
                             (new ArrayList<Integer>(Arrays.asList(-1))), cont++),

                Arguments.of((new Tramo(0,3)),
                             (new ArrayList<Integer>(Arrays.asList(-1, -1, -1, -1))), cont++),

                Arguments.of((new Tramo(2,2)),
                             (new ArrayList<Integer>(Arrays.asList(120, 140, -10, -10, -10))), cont++)
        );
    }
}
