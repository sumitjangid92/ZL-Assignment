package source;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.SeleniumBase;

public class LoginPage extends SeleniumBase{
	
	HomePage homePage = new HomePage();
	
	By loginLink			= By.cssSelector("a[href='/login?signIn=true']");
	By mobileNumberTextBox	= By.cssSelector("#signin [phone][type='Number']");
	By userPasswordTextBox	= By.cssSelector("#signin [type='password']");
	By userSignInButton		= By.cssSelector("#signin [type='submit']");
	
	public void clickLoginLink(WebDriver driver) {
		clickElement(driver, loginLink);
		waitUntilVisible(driver, mobileNumberTextBox);
		waitUntilVisible(driver, userPasswordTextBox);
	}
	
	public void enterUserNumber(WebDriver driver, String userMobileNumber) {
		enterText(driver, mobileNumberTextBox, userMobileNumber);
	}

	public void enterUserPassword(WebDriver driver, String userPassword) {
		enterText(driver, userPasswordTextBox, userPassword);
	}
	
	public void clickSignInButton(WebDriver driver) {
		clickElement(driver, userSignInButton);
	}
	
	public void loginWithUser(WebDriver driver, String userMobileNumber, String userPassword) {
		clickLoginLink(driver);
		enterUserNumber(driver, userMobileNumber);
		enterUserPassword(driver, userPassword);
		clickSignInButton(driver);
		assertEquals(homePage.isUserLoggedIn(driver), true);
	}
}
