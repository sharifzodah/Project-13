package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseDriver {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void login(){
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @BeforeMethod
    @Parameters({"UserName", "Password"})
    public void signIn(String user, String pass){
        driver.get("https://app.hubspot.com/login");
        loginFunctionality(wait, user, pass);
    }

    @AfterMethod
    public void deleteMethod(){
        driver.findElement( By.xpath( "//span[text()='Actions']" ) ).click();
        wait.until( ExpectedConditions.visibilityOf(driver.findElement( By.xpath( "//i18n-string[text()='Delete']" )) )).click();
        wait.until( ExpectedConditions.visibilityOf( driver.findElement( By.xpath( "//button[@data-confirm-button='accept']" ) ) )).click();

    }

    @AfterClass
    public void quit(){
        driver.quit();
    }

    private void loginFunctionality(WebDriverWait wait, String user, String pass){

        By userEmail = By.id("username");
        wait.until(ExpectedConditions.visibilityOfElementLocated(userEmail));

        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("loginBtn")).click();
    }
}
