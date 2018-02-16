package base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumBase {
	
	int timeOutInSecs = 35;
	
	public WebElement findElement(WebDriver driver, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return null;
		}
	}
	
	
	public void waitUntilTextPresent(WebDriver driver, By locator, String text){
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
		} catch (Exception e){
			System.out.print(e.getMessage());
			Assert.fail();
		}
	}
	public void waitUntilVisible(WebDriver driver, By locator){
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e){
			System.out.print(e.getMessage());
			Assert.fail();
		}
	}

	public void waitUntilInvisible(WebDriver driver, By locator){
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e){
			System.out.print(e.getMessage());
			Assert.fail();
		}
	}

	public void waitUntilClickable(WebDriver driver, By locator){
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e){
			System.out.print(e.getMessage());
			Assert.fail();
		}
	}
	
	public void enterText(WebDriver driver, By locator, String text) {
		try {
			WebElement element = findElement(driver, locator);
			element.click();
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			Assert.fail();
		}
	}
	
	//This method is to get the list of elements found by locator
	public List<WebElement> getElements(WebDriver driver, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			return wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			System.out.print(e.getMessage());
			Assert.fail();
			return null;
		}
	}

	//this method is to click on element when locator is given
	public void clickElement(WebDriver driver, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			Assert.fail();
		}
	}
	
	//this method is to click on element when element is given
	public void clickElement(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			Assert.fail();
		}
	}

	//This method is to scroll for a particular element to be visible
	public void scrollToElementVisible(WebDriver driver, By locator) 
	{
		//Scrolling till the button got visible 
		Coordinates coordinate = ((Locatable)findElement(driver, locator)).getCoordinates(); 
		coordinate.inViewPort();
		waitUntilVisible(driver, locator);
	}
	
	public void idleWait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (Exception e) {
			System.out.println("Couldn't wait due to error = "+e);
		}
	}

	
	
	
	public void switchToTab(WebDriver driver, int tabNumber){
		try{
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("sales force tabs are " +tabs);
			if(tabNumber<=tabs.size())
			{
				// change focus to new tab
				driver.switchTo().window(tabs.get(tabNumber-1));
			}
			else
			{
				System.out.println("No such tab exists");
				return;
			}
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}
	


}
