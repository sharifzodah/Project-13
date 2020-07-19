package Tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 extends BaseDriver{

    /*
    "sharifzodah@gmail.com"
    "Qwertyu2020@@##"

        https://app.hubspot.com/login

        Enter username and password
        Click on login button
        Click on contacts dropdown (on left top)
        Click on contacts button
        Click on create contact button
        Enter email
        Enter First name
        Enter Last name
        Click on Create Contact button
        Verify email and first name as entered
        Click on actions dropdown
        Click on delete button
        Click on Delete contact button

     */


    @Test
    @Parameters({"Email", "FirstName", "LastName"})
    public void contacts(String email_expected, String fname_expected, String lname_expected){
        WebElement contacts = driver.findElement( By.cssSelector( "a[id='nav-primary-contacts-branch'][data-tracking='click hover']" ) );
        contacts.click();
        //        Click on contacts button
        driver.findElement( By.xpath( "(//div[contains(text(),'Contacts')])[1]" ) ).click();

        //        Click on create contact button
        WebElement createContacts = driver.findElement( By.xpath( "//span[text()='Create contact']" ) );
        createContacts.click();
//        Enter email
        WebElement emailInput = driver.findElement( By.cssSelector( "div[class='private-form__input-wrapper'] [data-field='email']" ) );
        emailInput.sendKeys( email_expected );
//        Enter First name
        WebElement firstNameInput = driver.findElement( By.xpath( "//input[@data-field='firstname']" ) );
        firstNameInput.sendKeys( fname_expected );
//        Enter Last name
        WebElement lastNameInput = driver.findElement( By.xpath( "//input[@data-field='lastname']" ) );
        lastNameInput.sendKeys( lname_expected );
//        Click on Create Contact button
        driver.findElement( By.cssSelector( "button[data-selenium-test='base-dialog-confirm-btn']" ) ).click();

        String email_actual = driver.findElement( By.xpath( "//div[@data-selenium-test='property-input-email']/span/span/span" ) ).getText();
        verifyFunctionality(email_actual, email_expected);
        String fname_actual = driver.findElement( By.xpath( "//input[@data-field='firstname']" ) ).getAttribute( "value" );
        verifyFunctionality(fname_actual, fname_expected);
    }

    public void verifyFunctionality(String actual, String expected){
        Assert.assertEquals(actual, expected);
    }

}
