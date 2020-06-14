package ejercicio1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMultiPath
{




    MultipathExample multipathExample;
    int a;
    int b;
    int c;
    int esperado;
    int real;

    @BeforeEach
    void init()
    {
        multipathExample = new MultipathExample();
    }


/*
    @Test
    public void test100x100lineasYcondicion()
    {
        a = 6;
        b = 6;
        c = 0;
        esperado = 12;

        real = multipathExample.multiPath1(a, b, c);

        assertEquals(esperado, real);

    }


    @Test
    public void test362()
    {
        a = 3;
        b = 6;
        c = 2;
        esperado = 8;

        real = multipathExample.multiPath1(a, b, c);

        assertEquals(esperado, real);

        /*
            //Si el test falla NO SE GENERA el:
               --> failsafe reports (INTEGRACION)
               --> maven archiver
               --> site -> jacoco -> informe
               --> el .jar
    }
            */

    @ParameterizedTest
    @MethodSource("casostestMultiPath3")
    public void testMultiPath3(int a, int b, int c, int esperado)
    {
        esperado = multipathExample.multiPath3(a, b,c);
    }

    private static Stream<Arguments> casostestMultiPath3()
    {
        return Stream.of(
                Arguments.of(6,4,6,18),

                Arguments.of(3,6,0,6)
        );
    }

    @ParameterizedTest
    @MethodSource("casostestMultiPath2")
    public void testMultiPath2(int a, int b, int c, int esperado)
    {
        esperado = multipathExample.multiPath2(a, b,c);
    }

    private static Stream<Arguments> casostestMultiPath2()
    {
        return Stream.of(
                Arguments.of(6,4,6,16),

                Arguments.of(6,8,3,11),

                Arguments.of(1,0,0,0)
        );
    }

    @ParameterizedTest
    @MethodSource("casostestMultiPath1")
    public void testMultiPath1(int a, int b, int c, int esperado)
    {
        esperado = multipathExample.multiPath1(a, b,c);
    }

    private static Stream<Arguments> casostestMultiPath1()
    {
        return Stream.of(
                Arguments.of(6,6,0,12 ),

                Arguments.of(0,0,0,0)
        );
    }




}
