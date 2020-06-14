package ejercicio3.conPOyPFact;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNewClient
{
    String id;
    WebDriver driver;
    LoginPage loginPage;
    ManagerPage managerPage;
    NewCustomer newCustomer;
    DeleteCustomer afteradd;
    String esperado = "Welcome To Manager's Page of Guru99 Bank";

    @BeforeEach void init()
    {
        driver = new FirefoxDriver();
        loginPage = LoginPage.initFirstPage(driver);

    }

    @AfterEach void finish()
    {
        if (id != null)
        {

            afteradd = newCustomer.afterAdd();

            afteradd.deleteCustomer(id);

            afteradd.initAlert();
            afteradd.closeAlert();
            driver.close();

        }
        else
            driver.close();
    }

    @Test
    public void testTestNewClientOk()
    {
        String tituloPrincipal = loginPage.getPageTitle();
        assertTrue(tituloPrincipal.toLowerCase().contains("guru99 bank"));

        //2 -> Una vez sabemos que estamos en la pagina de log, relenamos los textBox de la pàgina
        managerPage = loginPage.login("mngr263739", "ApEtagA");
        assertTrue(managerPage.getHomePageDashboardUserName().toLowerCase().contains(esperado.toLowerCase()));

        newCustomer = managerPage.addcustomer();
        tituloPrincipal = newCustomer.getTituloPagina();
        assertTrue(tituloPrincipal.toLowerCase().contains("add new customer"));

        newCustomer.newCustomer("ssadasdsssasdsadaaaa","m","2020-08-01","direcon",
                                "ali", "spain",  "123456","666666666",
                                "alessdsadsadssJaaaa@uau.es","444444");


        assertTrue(newCustomer.getTituloPagina().toLowerCase().contains("successfully!!!"));

        id = newCustomer.getId();
    }

    @Test
    public void testTestNewClientDuplicate()
    {
        String tituloPrincipal = loginPage.getPageTitle();
        assertTrue(tituloPrincipal.toLowerCase().contains("guru99 bank"));

        //2 -> Una vez sabemos que estamos en la pagina de log, relenamos los textBox de la pàgina
        managerPage = loginPage.login("mngr263739", "ApEtagA");
        assertTrue(managerPage.getHomePageDashboardUserName().toLowerCase().contains(esperado.toLowerCase()));

        newCustomer = managerPage.addcustomer();
        tituloPrincipal = newCustomer.getTituloPagina();
        assertTrue(tituloPrincipal.toLowerCase().contains("add new customer"));

        newCustomer.newCustomer("a","m","2020-08-01","direcon",
                                "ali", "spain",  "123456","666666666",
                                "a@a.es","444444");

        newCustomer.initAlert();
        assertTrue(newCustomer.getStringAlert().toLowerCase().contains("email address already exist !!"));
        newCustomer.closeAlert();

    }
}
