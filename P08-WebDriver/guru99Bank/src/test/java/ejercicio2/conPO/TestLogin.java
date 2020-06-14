package ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin
{

    WebDriver driver;
    LoginPage poLogin;
    ManagerPage poManagerPage;

    String esperado = "Welcome To Manager's Page of Guru99 Bank";
    String alertaEsperada = "User is not valid";

    @BeforeEach
    public void setup()
    {
        driver = new FirefoxDriver();
        poLogin = new LoginPage(driver);
    }

    @Test
    public void test_Login_Correct()
    {
        String loginPageTitle = poLogin.getPageTitle();
        assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
        poManagerPage = poLogin.login("mngr263739", "ApEtagA");
        assertTrue(poManagerPage.getHomePageDashboardUserName().toLowerCase().contains(esperado.toLowerCase()));

    }

    @Test
    public void test_Login_Incorrect()
    {
        String alertaReal;
        String loginPageTitle = poLogin.getPageTitle();
        assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

        poLogin.login("NOuser", "NOpass");
        alertaReal = poLogin.getStringAlert();
        assertEquals(alertaEsperada, alertaReal);
        poLogin.closeAlert();
    }

    @AfterEach
    public void tearDown()
    {
        driver.close();
    }

}
