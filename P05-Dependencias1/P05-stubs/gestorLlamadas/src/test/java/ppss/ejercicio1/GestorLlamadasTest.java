package ppss.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest
{
    private int minutos;
    private int hora;
    private double esperado;
    GestorLlamadasTestable gestorLlamadasTestable;

    @BeforeEach void inicializarObjeto()
    {
        gestorLlamadasTestable = new GestorLlamadasTestable();
    }

    @Test
    void TestcalculaConsumo1()
    {
        minutos = 10;
        hora = 15;
        esperado = 208;
        gestorLlamadasTestable.setHoraActual(hora);

        double real = gestorLlamadasTestable.calculaConsumo(minutos);
        assertEquals(esperado, real);

    }

    @Test
    void TestcalculaConsumo2()
    {
        int minutos = 10;
        int hora = 22;
        double esperado = 105;
        gestorLlamadasTestable.setHoraActual(hora);
        double real = gestorLlamadasTestable.calculaConsumo(minutos);
        assertEquals(esperado, real);
    }
}