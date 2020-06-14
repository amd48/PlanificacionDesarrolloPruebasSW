package ppss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest
{
    int edad;
    boolean familiaNumerosa;
    boolean repetidor;
    float tasaEsperada;
    float tasaReal;
    Matricula matricula = new Matricula();

    @Test
    void C1_calculaTasaMatricula()
    {
        edad = 19;
        familiaNumerosa = false;
        repetidor = true;

        tasaEsperada = 2000.00f;

        tasaReal = matricula.calculaTasaMatricula(edad, familiaNumerosa, repetidor);

        assertEquals(tasaEsperada, tasaReal);
    }
    @Test
    void C2_calculaTasaMatricula()
    {
        edad = 68;
        familiaNumerosa = false;
        repetidor = true;

        tasaEsperada = 250.00f;

        tasaReal = matricula.calculaTasaMatricula(edad, familiaNumerosa, repetidor);

        assertEquals(tasaEsperada, tasaReal);
    }

    @Test
    void C3_calculaTasaMatricula()
    {
        edad = 19;
        familiaNumerosa = true;
        repetidor = true;

        tasaEsperada = 250.00f;

        tasaReal = matricula.calculaTasaMatricula(edad, familiaNumerosa, repetidor);

        assertEquals(tasaEsperada, tasaReal);
    }

    @Test
    void C4_calculaTasaMatricula()
    {
        edad = 19;
        familiaNumerosa = false;
        repetidor = false;

        tasaEsperada = 500.00f;

        tasaReal = matricula.calculaTasaMatricula(edad, familiaNumerosa, repetidor);

        assertEquals(tasaEsperada, tasaReal);
    }

    @Test
    void C5_calculaTasaMatricula()
    {
        edad = 61;
        familiaNumerosa = false;
        repetidor = false;

        tasaEsperada = 400.00f;

        tasaReal = matricula.calculaTasaMatricula(edad, familiaNumerosa, repetidor);

        assertEquals(tasaEsperada, tasaReal);
    }
}