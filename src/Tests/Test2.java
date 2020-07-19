package Tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test2 extends BaseDriver{
    /*
        https://app.hubspot.com/login

        Enter username and password
        Click on login button
        Click on contacts dropdown (on left top)
        Click on companies button
        Click on create companies button
        Enter the company domain
        Enter the name
        Click on Create company button
        Verify company domain as entered
        Click on actions dropdown
        Click on delete button
        Click on Delete contact button

     */

    @Test(priority = 1)
    @Parameters({"Domain", "Name"})
    void companies(String domain_expected, String fname_expected) {
        WebElement contacts = driver.findElement( By.cssSelector( "a[id='nav-primary-contacts-branch'][data-tracking='click hover']" ) );
        contacts.click();
        //        Click on companies button
        driver.findElement( By.xpath( "(//div[contains(text(),'Companies')])[1]" ) ).click();

        //        Click on create companies button
        driver.findElement( By.xpath( "//span[text()='Create company']" ) ).click();

//        Enter the company domain
        WebElement domainName = driver.findElement( By.cssSelector( "input[data-field='domain']" ) );
        domainName.sendKeys( domain_expected );

//        Enter the name
        WebElement name = driver.findElement( By.cssSelector( "input[data-field='name']" ) );
        name.clear();
        name.sendKeys( fname_expected );

//        Click on Create company button
        driver.findElement( By.cssSelector( "button[data-confirm-button=\"accept\"]" ) ).click();

        //        Verify company domain as entered
        String domain_actual = driver.findElement( By.xpath( "//div[@data-test-id='domain-input']/span/span/span" ) ).getText();
        verifyFunctionality( domain_actual, domain_expected );
    }

    public void verifyFunctionality(String actual, String expected){
        Assert.assertEquals(actual, expected);
    }
}
