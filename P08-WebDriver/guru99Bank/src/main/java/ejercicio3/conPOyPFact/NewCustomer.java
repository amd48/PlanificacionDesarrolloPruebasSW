package ejercicio3.conPOyPFact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCustomer
{
    Alert alert;
    WebDriver driver;

    @FindBy(className="heading3") WebElement homePageUserName;
    @FindBy(name = "name") WebElement nommbreUsuario;
    @FindBy(css="input[value='f']")WebElement generoFem;
    @FindBy(css="input[value='m']")WebElement generoMasc;
    @FindBy(name = "dob") WebElement date;
    @FindBy(name = "city") WebElement city;
    @FindBy(name = "state") WebElement state;
    @FindBy(name = "addr") WebElement dir;
    @FindBy(name = "pinno") WebElement pin;
    @FindBy(name = "telephoneno") WebElement tlf;
    @FindBy(name = "emailid") WebElement email;
    @FindBy(name = "password") WebElement passw;
    @FindBy(name = "sub") WebElement submit;
    @FindBy(linkText = "Delete Customer") WebElement dropCustomer;
    @FindBy(xpath="//table//tbody//tr//td//table//tbody//tr[4]//td[2]")  WebElement userId;


    public NewCustomer(WebDriver driver)
    {
        this.driver = driver;
    }


    public void newCustomer(String nombre, String genero, String date,
                            String dir,String ciudad, String estado, String pin, String tlf,
                            String email, String passw)
    {
        nommbreUsuario.sendKeys(nombre);
        this.date.sendKeys(date);
        (genero == "m" || genero == "M" ? generoMasc : generoFem).click();
        this.dir.sendKeys(dir);
        city.sendKeys(ciudad);
        state.sendKeys(estado);
        this.pin.sendKeys(pin);
        this.tlf.sendKeys(tlf);
        this.email.sendKeys(email);
        this.passw.sendKeys(passw);
        submit.click();
    }

    public String getTituloPagina()
    {
        return homePageUserName.getText();
    }

    public String getId()
    {
        return userId.getText();
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

    public DeleteCustomer afterAdd()
    {
        dropCustomer.click();
        return PageFactory.initElements(driver, DeleteCustomer.class);
    }

}
