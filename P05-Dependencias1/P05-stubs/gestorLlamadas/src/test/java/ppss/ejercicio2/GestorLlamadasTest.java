package ppss.ejercicio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest
{
    private int minutos;
    private int hora;
    private double consumoEsperado;
    GestorLlamadasTestable gestorLlamadas;
    CalendarioStub cstub;

    @BeforeEach
    void inicializarObjeto()
    {
        gestorLlamadas = new GestorLlamadasTestable();
        cstub = new CalendarioStub();
    }

    @Test
    void TestcalculaConsumo1()
    {
        minutos = 10;
        hora = 15;
        cstub.setHora(hora);
        consumoEsperado = 208;
        gestorLlamadas.setCalendario(cstub);

        double consumoReal = gestorLlamadas.calculaConsumo(minutos);
        assertEquals(consumoEsperado, consumoReal);

    }

    @Test
    void TestcalculaConsumo2()
    {
       minutos = 10;
        hora = 22;
        cstub.setHora(hora);
        consumoEsperado = 105;
        gestorLlamadas.setCalendario(cstub);
        double consumoReal = gestorLlamadas.calculaConsumo(minutos);
        assertEquals(consumoEsperado, consumoReal);
    }
}