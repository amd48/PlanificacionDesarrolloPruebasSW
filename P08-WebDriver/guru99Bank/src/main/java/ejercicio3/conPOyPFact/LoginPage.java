package ejercicio3.conPOyPFact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    Alert alert;
    WebDriver driver;

    @FindBy(name="uid") WebElement userID;
    @FindBy(name="password") WebElement password;
    @FindBy(name="btnLogin") WebElement login;
    @FindBy(className="barone") WebElement pTitle;

    static public LoginPage initFirstPage(WebDriver driver)
    {
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.driver.get("http://demo.guru99.com/V5");
    }

    public ManagerPage login(String user, String pass)
    {
        userID.sendKeys(user);
        password.sendKeys(pass);
        login.click();
        try
        {
            initAlert();
            return null;
        }
        catch (Exception e)
        {
            return PageFactory.initElements(driver, ManagerPage.class);
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
