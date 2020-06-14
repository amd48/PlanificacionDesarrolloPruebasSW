package ppss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaTest
{
    int edad;
    boolean familiaNumerosa;
    boolean repetidor;
    float resultadoReal, resultadoEsperado;
    Matricula mat= new Matricula();

    @Test
    public void testCalculaTasaMatricula()
    {
        edad = 23;
        familiaNumerosa = true;
        repetidor = true;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatricula2()
    {
        edad = 24;
        familiaNumerosa = false;
        repetidor = true;
        resultadoEsperado = 2000.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatricula3()
    {
        edad = 51;
        familiaNumerosa = true;
        repetidor = false;
        resultadoEsperado = 400.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);

        //Teniendo en cuenta que para una persona de 51 años no debe importar si es familia numerosa,
        //el resultado esperado segun la tabla es tasa - 100 (400), y el metodo que devuelve 150
        //  expected: <400.0> but was: <150.0>

        /*
           Tests run: 3, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.002 s <<< FAILURE!
           - in ppss.MatriculaTest
           testCalculaTasaMatricula3  Time elapsed: 0.001 s  <<< FAILURE!
        */
    }


    //Arreglado el fallo, el test pasa la prueba


    @Test
    public void testCalculaTasaMatricula4()
    {
        edad = 19;
        familiaNumerosa = false;
        repetidor = false;
        resultadoEsperado = 500.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatricula5()
    {
        edad = 30;
        familiaNumerosa = true;
        repetidor = false;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void testCalculaTasaMatricula6()
    {
        edad = 30;
        familiaNumerosa = true;
        repetidor = true;
        resultadoEsperado = 250.00f;
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }
}
