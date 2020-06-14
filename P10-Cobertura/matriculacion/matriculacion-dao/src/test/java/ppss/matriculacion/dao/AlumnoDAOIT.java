package ppss.matriculacion.dao;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.matriculacion.dao.DAOException;
import ppss.matriculacion.dao.FactoriaDAO;
import ppss.matriculacion.dao.IAlumnoDAO;
import ppss.matriculacion.dao.JDBCAlumnoDAO;
import ppss.matriculacion.to.AlumnoTO;

import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlumnoDAOIT
{
    private IAlumnoDAO ialumDAO;
    private FactoriaDAO factoria; //SUT
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;
    private AlumnoTO alumnoEntrada;

    String excpEsperada = "Error al conectar con BD";

   @BeforeEach void init() throws Exception
    {
        String cadenaConexion = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false& " +
                "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + TimeZone.getDefault().getID();

        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
            cadenaConexion, "root", "ppss");

        //obtenemos la conexción con la BD
        connection = databaseTester.getConnection();

        //para evitar el warning al acceder a la BD
        connection.getConfig().setProperty("http://www.dbunit.org/properties/datatypeFactory",
                    new MySqlDataTypeFactory());

        alumnoEntrada = new AlumnoTO();
    }

    @Test
    public void testA1() throws Exception
    {
        //Preparación alumno de entrada

        alumnoEntrada.setNif("33333333C");
        alumnoEntrada.setNombre("Elena Aguirre Juarez");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1985);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumnoEntrada.setFechaNacimiento(cal.getTime());



        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);

        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        Assertions.assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumnoEntrada));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);

    }

    @Test
    public void testA2() throws Exception
    {
        //Preparación alumno de entrada

        alumnoEntrada.setNif("11111111A");
        alumnoEntrada.setNombre("Alfonso Ramirez Ruiz");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumnoEntrada.setFechaNacimiento(cal.getTime());



        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);

        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT

        DAOException excp = assertThrows(DAOException.class,
                ()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumnoEntrada));

        //assertEquals(excpEsperada, excp.getMessage());
    }

    @Test
    public void testA3() throws Exception
    {

        //Preparación alumno de entrada

        alumnoEntrada.setNif("44444444D");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumnoEntrada.setFechaNacimiento(cal.getTime());



        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);

        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT

        DAOException excp = assertThrows(DAOException.class,
                ()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumnoEntrada));

        //assertEquals(excpEsperada, excp.getMessage());
    }

    @Test
    public void testA4() throws Exception
    {


        DAOException excp = assertThrows(DAOException.class,
                ()-> new FactoriaDAO().getAlumnoDAO().addAlumno(null));

        //assertEquals(excpEsperada, excp.getMessage());
    }

    @Test
    public void testA5() throws Exception
    {

        //Preparación alumno de entrada
        alumnoEntrada.setNombre("Pedro Garcia Lpoez");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumnoEntrada.setFechaNacimiento(cal.getTime());



        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);

        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT

        DAOException excp = assertThrows(DAOException.class,
                ()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumnoEntrada));

        //assertEquals(excpEsperada, excp.getMessage());
    }

    @Test
    public void testB1() throws Exception
    {
        //Preparación alumno de entrada
        String nifEntrada= "11111111A";

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);

        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
         Assertions.assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().delAlumno(nifEntrada));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testB2() throws Exception
    {
        //Preparación alumno de entrada
        String nifEntrada= "33333333A";
        excpEsperada = "No se ha borrado ningun alumno";

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);

        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        DAOException excp = assertThrows(DAOException.class,
                ()-> new FactoriaDAO().getAlumnoDAO().delAlumno(nifEntrada));

        //assertEquals(excpEsperada, excp.getMessage());
    }
}
