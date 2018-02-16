package source;

import java.util.List;
import source.SearchPGsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.SeleniumBase;

public class HomePage extends SeleniumBase {
	
	SearchPGsPage searchPGsPage = new SearchPGsPage();

	By testAccountLink			= By.cssSelector("a[href='/profile']");
	By searchBar				= By.id("searchBar");
	By searchResults			= By.className("ui-menu-item-wrapper");
	By searchIcon				= By.cssSelector(".search-btn-text");
	

	public void navigateToHomePage(WebDriver driver) {
		searchPGsPage.goToHomePage(driver);
		verifyTestAccountLink(driver);
	}
	
	//This method is to verify user is logged in
	public boolean isUserLoggedIn(WebDriver driver) {
		if(findElement(driver, testAccountLink)!=null && findElement(driver, testAccountLink).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	//verify test account link visible 
	public void verifyTestAccountLink(WebDriver driver) {
		waitUntilVisible(driver, testAccountLink);
	}

	//This method is to enter the search term
	public void enterSearchTerm(WebDriver driver, String searchTerm) {
		enterText(driver, searchBar, searchTerm);
	}
	
	//This method is to select a given text result option
	public void selectingSearchResult(WebDriver driver, String searchResult) {
		waitUntilClickable(driver, searchResults);
		List<WebElement> elements = getElements(driver, searchResults);
		for(int i=0; i<elements.size(); i++) {
			System.out.println("Options is = "+elements.get(i).getText().trim());
			if(elements.get(i).getText().trim().equals(searchResult.trim())) {
				elements.get(i).click();
				break;
			}
		}
	}
	
	//This method is to click on search icon
	public void clickSearchIcon(WebDriver driver) {
		clickElement(driver, searchIcon);
	}
	
	//This method is to search a particular text
	public void searchWithTerm(WebDriver driver, String searchTerm, String searchResult) {
		enterSearchTerm(driver, searchTerm);
		selectingSearchResult(driver, searchResult);
		searchPGsPage.verifyHeaderUserNameVisible(driver);
	}
	
	
}
