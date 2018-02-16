package source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.SeleniumBase;

public class SearchPGsPage extends SeleniumBase{
	
	By headerUserName 				= By.cssSelector(".headerUserName");
	By budgetSelect   				= By.cssSelector("select[ng-model='budget']");
	By sharingPreferencesSelect   	= By.cssSelector("select[ng-model='preference']");
	By pgTypeSelect   				= By.id("gender-drpdwn");
	By pgNamesHeading				= By.cssSelector("h2.text-left.ng-binding");
	By filterLink					= By.cssSelector("a.handleFilter");
	
	public void verifyHeaderUserNameVisible(WebDriver driver) {
		waitUntilVisible(driver, headerUserName);
	}
	
	public void setBudgetFilter(WebDriver driver, String textValue) {
		setFilter(driver, textValue, budgetSelect);
	}

	public void setSharingPreferencesFilter(WebDriver driver, String textValue) {
		setFilter(driver, textValue, sharingPreferencesSelect);
	}

	public void setPgTypeFilter(WebDriver driver, String textValue) {
		setFilter(driver, textValue, pgTypeSelect);
	}
	
	public void setFilter(WebDriver driver, String textValue, By locator) {
		waitUntilVisible(driver, locator);
		Select select = new Select(findElement(driver, locator));
		select.selectByVisibleText(textValue);
	}
	
	public void clickFilterLink(WebDriver driver) {
		clickElement(driver, filterLink);
		waitUntilVisible(driver, pgNamesHeading);
	}
	
	public void openPgDetails(WebDriver driver, String pgName) {
		waitUntilVisible(driver, pgNamesHeading);
		idleWait(2);
		List<WebElement> elements = getElements(driver, pgNamesHeading);
		for(int i=0; i<elements.size(); i++) {
			System.out.println("Names are = "+elements.get(i).getText());
			if(elements.get(i).getText().trim().equals(pgName.trim())) {
				elements.get(i).click();
				break;
			}
		}
	} 
}
