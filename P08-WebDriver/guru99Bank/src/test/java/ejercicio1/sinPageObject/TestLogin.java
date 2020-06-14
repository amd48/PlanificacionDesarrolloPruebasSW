package ejercicio1.sinPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogin
{
    WebDriver driver;
        String alertaEsperada = "User is not valid";
        String esperado = "Welcome To Manager's Page of Guru99 Bank";

    @BeforeEach void init()
    {
        driver = new FirefoxDriver();
    }
    @Test
    public void test_Login_Correct()
    {

        driver.get("http://demo.guru99.com/V5");

        WebElement user = driver.findElement(By.name("uid"));
        WebElement passw = driver.findElement(By.name("password"));
        WebElement click = driver.findElement(By.name("btnLogin"));

        user.sendKeys("mngr263739");
        passw.sendKeys("ApEtagA");
        click.click();

        WebElement welcome = driver.findElement(By.className("heading3"));

        assertEquals(esperado, welcome.getText());
    }

    @Test
    public void test_Login_Incorrect()
    {
        String alertReal;

        driver.get("http://demo.guru99.com/V5");

        WebElement user = driver.findElement(By.name("uid"));
        WebElement passw = driver.findElement(By.name("password"));
        WebElement click = driver.findElement(By.name("btnLogin"));

        user.sendKeys("mnr263739");
        passw.sendKeys("ApEtagA");
        click.click();

        Alert alert = driver.switchTo().alert();
        alertReal = alert.getText();
        alert.accept();

        assertEquals(alertaEsperada, alertReal);
    }

    @AfterEach public void finish()
    {
        driver.close();
    }
}
