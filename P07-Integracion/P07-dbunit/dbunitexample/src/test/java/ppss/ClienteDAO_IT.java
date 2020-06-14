package ppss;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

//import org.apache.log4j.BasicConfigurator;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.jupiter.api.*;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* IMPORTANTE:
    Dado que prácticamente todos los métodos de dBUnit lanzan una excepción,
    vamos a usar "throws Esception" en los métodos, para que el código quede más
    legible sin necesidad de usar un try..catch o envolver cada sentencia dbUnit 
    con un assertDoesNotThrow()
*/
public class ClienteDAO_IT {

  private ClienteDAO clienteDAO; //SUT
  private IDatabaseTester databaseTester;
  private IDatabaseConnection connection;

  @BeforeEach
  public void setUp() throws Exception {

    String cadena_conexionDB = "jdbc:mysql://localhost:3306/DBUNIT?serverTimezone=UTC&useSSL=false";
    databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
            cadena_conexionDB, "root", "ppss");
    //obtenemos la conexción con la BD
    connection = databaseTester.getConnection();

    //para evitar el warning al acceder a la BD
    connection.getConfig()
            .setProperty("http://www.dbunit.org/properties/datatypeFactory",
                    new MySqlDataTypeFactory());

    clienteDAO = new ClienteDAO();
  }

  @Test
  public void testInsert() throws Exception {
    Cliente cliente = new Cliente(1, "Juan", "Gomez");
    cliente.setDireccion("Mi calle");
    cliente.setCiudad("Alicante");

    //Inicializamos el dataSet con los datos iniciales de la tabla cliente
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    //Inyectamos el dataset en el objeto databaseTester
    databaseTester.setDataSet(dataSet);
    //inicializamos la base de datos con los contenidos del dataset
    databaseTester.onSetup();
    //invocamos a nuestro SUT
    Assertions.assertDoesNotThrow(() -> clienteDAO.insert(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    //Recuperamos los datos de la tabla cliente
    ITable actualTable = databaseDataSet.getTable("alumnos");

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    ITable expectedTable = expectedDataSet.getTable("alumnos");

    Assertion.assertEquals(expectedTable, actualTable);

  }

  @Test
  public void testDelete() throws Exception {
    Cliente cliente = new Cliente(1, "Juan", "Gomez");
    cliente.setDireccion("Mi calle");
    cliente.setCiudad("Alicante");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a nuestro SUT
    Assertions.assertDoesNotThrow(() -> clienteDAO.delete(cliente));

    IDataSet databaseDataSet = connection.createDataSet();
    int rowCount = databaseDataSet.getTable("cliente").getRowCount();

    Assertions.assertEquals(0, rowCount);
  }

  @Test
  public void testUpdate() throws Exception {
    Cliente cliente = new Cliente(1, "Juan", "Gomez");
    cliente.setDireccion("Mi calle2");
    cliente.setCiudad("AlicanteCity");

    //Inicializamos el dataSet con los datos iniciales de la tabla cliente
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");

    //Inyectamos el dataset en el objeto databaseTester
    databaseTester.setDataSet(dataSet);
    //inicializamos la base de datos con los contenidos del dataset
    databaseTester.onSetup();
    //invocamos a nuestro SUT
    Assertions.assertDoesNotThrow(() -> clienteDAO.update(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    //Recuperamos los datos de la tabla cliente
    ITable actualTable = databaseDataSet.getTable("cliente");

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado2.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

   @Test
  public void testRetrieve() throws Exception {
    Cliente cliente = new Cliente(1, "Juan", "Gomez");
    cliente.setDireccion("Mi calle");
    cliente.setCiudad("Alicante");

    //Inicializamos el dataSet con los datos iniciales de la tabla cliente
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");

    //Inyectamos el dataset en el objeto databaseTester
    databaseTester.setDataSet(dataSet);
    //inicializamos la base de datos con los contenidos del dataset
    databaseTester.onSetup();
    //invocamos a nuestro SUT
     Cliente real =
    Assertions.assertDoesNotThrow(() -> clienteDAO.retrieve(cliente.getId()));

    assertAll(
            ()-> assertEquals(cliente.getId(), real.getId()),
            ()-> assertEquals(cliente.getNombre(), real.getNombre()),
            ()-> assertEquals(cliente.getApellido(), real.getApellido()),
            ()-> assertEquals(cliente.getDireccion(), real.getDireccion()),
            ()-> assertEquals(cliente.getCiudad(), real.getCiudad())
    );
  }
}
