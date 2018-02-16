package source;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import base.SeleniumBase;
import utility.HelperFunctions;

public class PgDetailPage extends SeleniumBase {

	HelperFunctions helperFunctions = new HelperFunctions();
	
	By pgDetailPageTitle	= By.cssSelector("h1.pageTitle");
	By scheduleVisitLink	= By.linkText("Schedule a Visit");
	By visitDate			= By.name("visitDate");
	By caretSign			= By.cssSelector(".caret.caret-extra");
	By visitTime			= By.cssSelector("select[ng-model='visit.time']");
	By scheduleAVisitButton	= By.xpath("//div[text()='Schedule aVisit']");
	
	By leftNavVisitIcon		= By.cssSelector("a[href='/visits'] span.fa-street-view");

	public void verifyPgDetailPageTitle(WebDriver driver, String text) {
		waitUntilTextPresent(driver, pgDetailPageTitle, text);
	}

	public void clickScheduleVisitLink(WebDriver driver) {
		clickElement(driver, scheduleVisitLink);
		waitUntilVisible(driver, visitDate);
	}
	
	public void openDatePickerForVisitDate(WebDriver driver) {
		clickElement(driver, caretSign);
	}

	public void setVisitDateForFuture(WebDriver driver) {
		findElement(driver, visitDate).sendKeys(Keys.ARROW_UP);
		findElement(driver, visitDate).sendKeys(Keys.ARROW_RIGHT);
		findElement(driver, visitDate).sendKeys(Keys.ARROW_UP);
		System.out.println("Visit text value = "+ findElement(driver, visitDate).getText());
		idleWait(15);
	}

	public void setTimeSlotForVisit(WebDriver driver, String slotValue) {
		waitUntilVisible(driver, visitTime);
		Select select = new Select(findElement(driver, visitTime));
		select.selectByVisibleText(slotValue);
	}
	
	public void clickScheduleVisit(WebDriver driver) {
		clickElement(driver, scheduleAVisitButton);
	}
}
