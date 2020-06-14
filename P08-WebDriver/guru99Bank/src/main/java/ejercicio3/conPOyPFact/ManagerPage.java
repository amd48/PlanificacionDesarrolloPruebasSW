package ejercicio3.conPOyPFact;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage
{
    //FindBY        HTML
    //className     class
    //name          name
    //linkText      href

    WebDriver driver;
    @FindBy(className="heading3") WebElement homePageUserName;
    @FindBy(linkText ="New Customer")WebElement newCustomer;

    public ManagerPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public String getHomePageDashboardUserName() {
        return homePageUserName.getText();

    }

    public NewCustomer addcustomer()
    {
        newCustomer.click();
        return PageFactory.initElements(driver, NewCustomer.class);
    }

}
