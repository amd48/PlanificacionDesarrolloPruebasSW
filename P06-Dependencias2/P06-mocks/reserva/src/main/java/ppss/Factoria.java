package ppss;

public class Factoria
{
    private static FactoriaBOs fb = null;

    public static FactoriaBOs create()
    {
        if(fb != null)
            return fb;
        else
            return new FactoriaBOs();
    }

     static void setFactoriaBOs(FactoriaBOs facBO)
    {
        fb = facBO;
    }
}
