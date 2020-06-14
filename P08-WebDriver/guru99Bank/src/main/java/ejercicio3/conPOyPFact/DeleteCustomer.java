package ejercicio3.conPOyPFact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteCustomer
{
    WebDriver driver;
    Alert alert;

    @FindBy(className = "heading3")
    WebElement titulo;

    @FindBy(name = "cusid")
    WebElement id;

    @FindBy(name = "AccSubmit")
    WebElement submit;

    public DeleteCustomer(WebDriver driver){this.driver = driver;}

    public void deleteCustomer(String id)
    {
        this.id.sendKeys(id);
        submit.click();
    }

    public String getTitulo()
    {
        return titulo.getText();
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


}
