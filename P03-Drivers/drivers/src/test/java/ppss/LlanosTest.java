package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Tag("NoParametrizados")
class LlanosTest
{
    Llanos llano = new Llanos();
    Tramo tramoReal  = new Tramo();
    Tramo tramoEsperado  = new Tramo();

    @Tag("TablaA")
    @Test
    void C1A_buscarTramoLlanoMasLargo()
    {
        tramoEsperado.setOrigen(0);
        tramoEsperado.setLongitud(0);

        ArrayList<Integer> lecturas = new ArrayList<>();
        lecturas.add(3);
        tramoReal = llano.buscarTramoLlanoMasLargo(lecturas);

        assertAll("GrupoTestC1A",
                ()-> assertEquals(tramoEsperado.getOrigen()   , tramoReal.getOrigen()),
                ()-> assertEquals(tramoEsperado.getLongitud() , tramoReal.getLongitud())
        );
    }

    @Tag("TablaA")
    @Test
    void C2A_buscarTramoLlanoMasLargo()
    {
        tramoEsperado.setOrigen(0);
        tramoEsperado.setLongitud(3);
        ArrayList<Integer> lecturas = new ArrayList<>();
        lecturas.add(100);lecturas.add(100);lecturas.add(100);lecturas.add(100);

        tramoReal = llano.buscarTramoLlanoMasLargo(lecturas);

        assertAll("GrupoTestC2A",
                ()-> assertEquals(tramoEsperado.getOrigen()   , tramoReal.getOrigen()),
                ()-> assertEquals(tramoEsperado.getLongitud() , tramoReal.getLongitud())
        );
    }

    @Tag("TablaA")
    @Test
    void C3A_buscarTramoLlanoMasLargo()
    {
        tramoEsperado.setOrigen(2);
        tramoEsperado.setLongitud(2);
        ArrayList<Integer> lecturas = new ArrayList<>();
        lecturas.add(120);lecturas.add(140);lecturas.add(180);lecturas.add(180);lecturas.add(180);

        tramoReal = llano.buscarTramoLlanoMasLargo(lecturas);

        assertAll("GrupoTestC3A",
                ()-> assertEquals(tramoEsperado.getOrigen()   , tramoReal.getOrigen()),
                ()-> assertEquals(tramoEsperado.getLongitud() , tramoReal.getLongitud())
        );
    }

    @Tag("TablaB")
    @Test
    void C1B_buscarTramoLlanoMasLargo()
    {
        tramoEsperado.setOrigen(0);
        tramoEsperado.setLongitud(0);

        ArrayList<Integer> lecturas = new ArrayList<>();
        lecturas.add(-1);
        tramoReal = llano.buscarTramoLlanoMasLargo(lecturas);

        assertAll("GrupoTestC1B",
                ()-> assertEquals(tramoEsperado.getOrigen()   , tramoReal.getOrigen()),
                ()-> assertEquals(tramoEsperado.getLongitud() , tramoReal.getLongitud())
        );
    }

    @Tag("TablaB")
    @Test
    void C2B_buscarTramoLlanoMasLargo()
    {
        tramoEsperado.setOrigen(0);
        tramoEsperado.setLongitud(3);
        ArrayList<Integer> lecturas = new ArrayList<>();
        lecturas.add(-1);lecturas.add(-1);lecturas.add(-1);lecturas.add(-1);

        tramoReal = llano.buscarTramoLlanoMasLargo(lecturas);

        assertAll("GrupoTestC2B",
                ()-> assertEquals(tramoEsperado.getOrigen()   , tramoReal.getOrigen()),
                ()-> assertEquals(tramoEsperado.getLongitud() , tramoReal.getLongitud())
        );
    }

    @Tag("TablaB")
    @Test
    void C3B_buscarTramoLlanoMasLargo()
    {
        tramoEsperado.setOrigen(2);
        tramoEsperado.setLongitud(2);
        ArrayList<Integer> lecturas = new ArrayList<>();
        lecturas.add(120);lecturas.add(140);lecturas.add(-10);lecturas.add(-10);lecturas.add(-10);

        tramoReal = llano.buscarTramoLlanoMasLargo(lecturas);

        assertAll("GrupoTestC3B",
                ()-> assertEquals(tramoEsperado.getOrigen()   , tramoReal.getOrigen()),
                ()-> assertEquals(tramoEsperado.getLongitud() , tramoReal.getLongitud())
        );
    }
}