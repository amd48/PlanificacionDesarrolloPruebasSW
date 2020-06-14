package ppss;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class MatriculaParamTest
{
    Matricula mat = new Matricula();

    @ParameterizedTest
    @MethodSource("casosDePrueba")
    void testParametrizado(float expected, int edad, boolean familiaNumerosa, boolean repetidor)
    {
        assertEquals(expected, mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor));
    }

    private static Stream<Arguments> casosDePrueba()
    {

        return Stream.of(
                Arguments.of(2000.00f, 19, false, true),
                Arguments.of(250.00f, 68, false, true),
                Arguments.of(250.00f, 19, true, true),
                Arguments.of(500.00f, 19, false, false),
                Arguments.of(400.00f, 61, false, false),
                Arguments.of(400.00f, 60, true, true)
        );
    }
}
