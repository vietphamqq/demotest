import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.ArrayList;
import java.util.List;


public class DemoTest {

    static WebElement username, customerCare;
    static WebElement password;
    static WebElement loginBtn;
    static WebDriver driver;


    public static void main(String[] args) throws InterruptedException {
        InternetExplorerDriverManager.getInstance().arch32().setup();

        InternetExplorerOptions options = new InternetExplorerOptions();
        options.introduceFlakinessByIgnoringSecurityDomains();
        driver = new InternetExplorerDriver(options);

        // Open Webpage
        driver.get("https://asu-eebrowse.nz.hsntech.int:11022");
        //Certificate
        Thread.sleep(2000);
        ByPassCertificate();

        // Find Login Element
        username = driver.findElement(By.name("j_username"));
        password = driver.findElement(By.name("j_password"));
        loginBtn = driver.findElement(By.name("submitbutton"));

        // Login with user name and password
        LoginToHomePage("energyop", "password");

        // Open window customer
        customerCare = driver.findElement(By.linkText("Customer Care"));
        customerCare.click();
        Thread.sleep(4000);


        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        Thread.sleep(2000);
        WebElement searchButton = driver.findElement(By.xpath(".//a[./img[@title='Advanced Search']]"));
        searchButton.click();


        Thread.sleep(4000);
        driver.quit();
    }

    public static void ByPassCertificate() {
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        //if(pageTitle == "This site isn’t secure") {
        driver.findElement(By.linkText("More information")).click();
        driver.findElement(By.partialLinkText("Go on to the webpage")).click();
        //}
    }

    public static void LoginToHomePage(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }


}

