package ppss;


public class ReservaTestable extends Reserva
{
    private boolean conexionInvalida;

    public void setConexionInvalida(boolean conexionInvalida)
    {
        this.conexionInvalida = conexionInvalida;
    }

    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu)
    {
        if(login != ReservaTest.log || password != ReservaTest.password || tipoUsu != Usuario.BIBLIOTECARIO)
            return false;
        else
            return true;
    }

    @Override
    public Operacion getOperacion()
    {
        OperacionStub o =  new OperacionStub();
        o.setConexionInvalida(conexionInvalida);
        return o;
    }
}
