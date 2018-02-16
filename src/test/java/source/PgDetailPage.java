package source;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import base.SeleniumBase;
import utility.HelperFunctions;

public class PgDetailPage extends SeleniumBase {

	HelperFunctions helperFunctions = new HelperFunctions();

	By leftNavVisitIcon				= By.cssSelector("a[href='/visits'] span.fa-street-view");
	By notyMessageNotification		= By.cssSelector(".noty_message span.noty_text");
	By pgDetailPageTitle			= By.cssSelector("h1.pageTitle");

	//Schedule a visit
	By scheduleVisitLink			= By.linkText("Schedule a Visit");
	By visitDate					= By.name("visitDate");
	By caretSign					= By.cssSelector(".caret.caret-extra");
	By visitTime					= By.cssSelector("select[ng-model='visit.time']");
	By scheduleAVisitButton			= By.xpath("//div[text()='Schedule aVisit']");
	By visitConfirmationWindow		= By.cssSelector(".offer.offer-success");

	//Request a bed
	By requestABedLink				= By.linkText("Request a Bed");
	By joinWaitListButton			= By.xpath("//button[text()='Join Waitlist'][not(contains(@class,'navbar'))]");
	By twoSharingOfferContent		= By.xpath("//div[@class='offer-content']//strong[text()='2 Sharing']");
	By bookingDate					= By.cssSelector("input[ng-model='booking.date']");

	By proceedToPayButton			= By.xpath("//button[text()='Proceed to Pay'][not(contains(@class,'navbar'))]");
	By userNameTextBox				= By.name("fullname");
	By userEmailTextBox				= By.xpath("//input[@name='email']");
	By userNumberTextBox			= By.xpath("//input[@name='name']");
	By havePromoCodeChkBox			= By.cssSelector("[ng-model='havePromo']");
	By promoCodeInput				= By.cssSelector("[name='promoCode']");
	By applyPromo					= By.cssSelector("//button[text()='Apply Code']");

	By makePayment					= By.xpath("//div[text()='Make Payment']");
	By payByCreditOption			= By.xpath("//div[contains(@ng-show,'razorpay')][not(@class='ng-hide')]");

	By merchantName					= By.id("merchant-name");
	By contactInput					= By.id("contact");
	By netBankingOption				= By.cssSelector(".payment-option.item[tab='netbanking']");

	By walletOption					= By.cssSelector(".payment-option.item[tab='wallet']");
	By walletOlaMoney				= By.xpath("//span[@class='title'][text()='Ola Money']");
	By walletPayZapp				= By.xpath("//span[@class='title'][text()='PayZapp']");
	By payButton					= By.cssSelector("button[type='submit']");

	By successButton				= By.cssSelector("button.success");
	By returnHomeLink				= By.linkText("RETURN HOME");
	By checkOutIframe				= By.cssSelector(".razorpay-checkout-frame");

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
	}

	public void setTimeSlotForVisit(WebDriver driver, String slotValue) {
		waitUntilVisible(driver, visitTime);
		Select select = new Select(findElement(driver, visitTime));
		select.selectByVisibleText(slotValue);
	}

	public void clickScheduleVisit(WebDriver driver) {
		clickElement(driver, scheduleAVisitButton);
		waitUntilVisible(driver, notyMessageNotification);
		if(findElement(driver, notyMessageNotification).getText().contains("You are already scheduled")) {
			System.out.println("You already have schduled visit with this pg");
		} else {
			waitUntilVisible(driver, visitConfirmationWindow);
			System.out.println("Visit is scheduled successfully");
		}
	}

	public void openVisitsTab(WebDriver driver) {
		clickElement(driver, leftNavVisitIcon);
		waitUntilVisible(driver, notyMessageNotification);
		waitUntilInvisible(driver, notyMessageNotification);
	}	

	public void verifyingVisitInVisitTab(WebDriver driver) {
		openVisitsTab(driver);
		waitUntilVisible(driver, visitConfirmationWindow);
	}

	public void clickRequestABedLink(WebDriver driver) {
		clickElement(driver, requestABedLink);
		waitUntilVisible(driver, twoSharingOfferContent);
		waitUntilVisible(driver, joinWaitListButton);
	}

	public void selectTwoSharing(WebDriver driver) {
		clickElement(driver, twoSharingOfferContent);
	}

	public void setBookingDateForFuture(WebDriver driver) {
		waitUntilVisible(driver, bookingDate);
		findElement(driver, bookingDate).sendKeys(Keys.ARROW_UP);
		findElement(driver, bookingDate).sendKeys(Keys.ARROW_RIGHT);
		findElement(driver, bookingDate).sendKeys(Keys.ARROW_UP);
	}

	public void clickProceedToPay(WebDriver driver) {
		idleWait(2);
		clickElement(driver, proceedToPayButton);
	}

	public void verifyDetailsOnPaymentPage(WebDriver driver, String userName, String number, String email) {
		waitUntilVisible(driver, userNameTextBox);
		waitUntilVisible(driver, userEmailTextBox);
		waitUntilVisible(driver, userNumberTextBox);
		assertEquals(findElement(driver, userNameTextBox).getAttribute("value").trim(), userName.trim());
		assertEquals(findElement(driver, userNumberTextBox).getAttribute("value").trim(), number.trim());
		assertEquals(findElement(driver, userEmailTextBox).getAttribute("value").trim(), email.trim());
	}

	public void makePayment(WebDriver driver) {
		waitUntilInvisible(driver, notyMessageNotification);
		clickElement(driver, makePayment);
	}

	public boolean selectNetBankingOption(WebDriver driver) {
		clickElement(driver, payByCreditOption );
		if(findElement(driver, notyMessageNotification)!=null && findElement(driver, notyMessageNotification).isDisplayed() && findElement(driver, notyMessageNotification).getText().contains("User has already pre-booked")) {
			System.out.println("PG is already booked");
			return false;
		} else {
			System.out.println("Going to book the pg");
			switchToIframe(driver, checkOutIframe);
			waitUntilVisible(driver, merchantName);
			waitUntilTextPresent(driver, merchantName, "Zolo");
			return true;
		}
	}

	public void enterContactNumber(WebDriver driver, String text) {
		enterText(driver, contactInput, text);
	}

	public void selectWalletPaymentOption(WebDriver driver) {
		clickElement(driver, walletOption);
		waitUntilVisible(driver, walletPayZapp);
	}

	public void selectPayByPayZapp(WebDriver driver) {
		clickElement(driver, walletPayZapp);
	}

	public void clickPay(WebDriver driver) {
		waitUntilVisible(driver, payButton);
		clickElement(driver, payButton);
		idleWait(3);
	}

	public void clickSuccess(WebDriver driver) {
		switchToTab(driver, 2);
		clickElement(driver, successButton);
		switchToTab(driver, 1);
		waitUntilVisible(driver, returnHomeLink);		
	}


}
