package ppss.ejercicio3;

public class ServicioStub extends Servicio
{
    float precio = 10.0f;
    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return 10.0f;
    }//El 10 lo indica la práctica
}
