package ejercicio2.conPO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage
{
    WebDriver driver;

    WebElement userID;
    WebElement password;
    WebElement login;
    WebElement pTitle;
    Alert alert;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.driver.get("http://demo.guru99.com/V5");
        userID = driver.findElement(By.name("uid"));
        password =
        driver.findElement(By.name("password"));
        login =
        driver.findElement(By.name("btnLogin"));
        pTitle =
        driver.findElement(By.className("barone"));
    }

    public ManagerPage login(String user, String pass)
    {
        userID.sendKeys(user);
        password.sendKeys(pass);
        login.click();
        try
        {
            initAlert();
            return null;//Si se inicia la alarma
        }
        catch(Exception e)
        {
            return new ManagerPage(driver);
        }
    }


    public void initAlert()
    {
        alert = driver.switchTo().alert();
    }

    public void closeAlert()
    {
        alert.accept();
    }

    public String getStringAlert()
    {
        return alert.getText();
    }

    public String getPageTitle()
    {
        return pTitle.getText();
    }
}
