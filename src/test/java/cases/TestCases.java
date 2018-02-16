package cases;

import org.testng.annotations.Test;
import base.SeleniumBase;
import base.TestBase;
import source.HomePage;
import source.LoginPage;
import source.PgDetailPage;
import source.SearchPGsPage;

public class TestCases extends TestBase {

	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	SeleniumBase seleniumBase = new SeleniumBase();
	SearchPGsPage searchPGsPage = new SearchPGsPage(); 
	PgDetailPage pgDetailPage = new PgDetailPage();
	
	// Part A
	@Test(priority=0)
	public void schedule_a_visit() {
		
		// 1. Open "http://52.201.90.154:9002/" >>
		loginPage.loginWithUser(driver, config.getProperty("user_phone_number"), config.getProperty("user_password"));
		homePage.searchWithTerm(driver, "Electronic", "Electronic City Phase II, Electronic City, Bengaluru, Karnataka, India");
	
		// 2. Verify the search result >>
		searchPGsPage.setBudgetFilter(driver, "5000 - 8000");
		searchPGsPage.setSharingPreferencesFilter(driver, "2 Sharing");
		searchPGsPage.setPgTypeFilter(driver, "Men");
		searchPGsPage.clickFilterLink(driver);
		searchPGsPage.openPgDetails(driver, config.getProperty("pg_name").trim());
		pgDetailPage.verifyPgDetailPageTitle(driver,config.getProperty("pg_name").trim());
		
		//3. Complete schedule a visit flow >>
		pgDetailPage.clickScheduleVisitLink(driver);
		pgDetailPage.setVisitDateForFuture(driver);
		pgDetailPage.setTimeSlotForVisit(driver, "9 am - 10 am");
		pgDetailPage.clickScheduleVisit(driver);
		pgDetailPage.verifyingVisitInVisitTab(driver);
		
		System.out.println("Test Case is Pass");
	}
	
	
	// Part B
	@Test(priority=1)
	public void booking_a_room() {
		
		// 1. Open "http://52.201.90.154:9002/" >>
		loginPage.loginWithUser(driver, config.getProperty("user_phone_number"), config.getProperty("user_password"));
		homePage.searchWithTerm(driver, "Electronic", "Electronic City Phase II, Electronic City, Bengaluru, Karnataka, India");
	
		// 2. Verify the search result >>
		searchPGsPage.setBudgetFilter(driver, "5000 - 8000");
		searchPGsPage.setSharingPreferencesFilter(driver, "2 Sharing");
		searchPGsPage.setPgTypeFilter(driver, "Men");
		searchPGsPage.clickFilterLink(driver);
		searchPGsPage.openPgDetails(driver, config.getProperty("pg_name").trim());
		pgDetailPage.verifyPgDetailPageTitle(driver,config.getProperty("pg_name").trim());
		
		//3. booking a pg >>
		pgDetailPage.clickRequestABedLink(driver);
		pgDetailPage.setBookingDateForFuture(driver);
		pgDetailPage.selectTwoSharing(driver);
		pgDetailPage.clickProceedToPay(driver);
		pgDetailPage.verifyDetailsOnPaymentPage(driver, config.getProperty("user_name"), config.getProperty("user_phone_number"), config.getProperty("user_email_id"));
		pgDetailPage.makePayment(driver);
		if(pgDetailPage.selectNetBankingOption(driver)) {
			pgDetailPage.enterContactNumber(driver, config.getProperty("user_phone_number"));
			pgDetailPage.selectWalletPaymentOption(driver);
			pgDetailPage.selectPayByPayZapp(driver);
			pgDetailPage.clickPay(driver);
			pgDetailPage.clickSuccess(driver);
		} else {
			System.out.println("booking is already being done");
		}
		System.out.println("Test Case is Pass");
		
	}
	
}
