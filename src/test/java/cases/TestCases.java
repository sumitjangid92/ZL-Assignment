package cases;

import static org.testng.Assert.assertEquals;

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
	public void login_to_website() {
		
		// 1. Open "http://52.201.90.154:9002/" >>
		loginPage.loginWithUser(driver, config.getProperty("user_phone_number"), config.getProperty("user_password"));
		homePage.searchWithTerm(driver, "Electronic", "Electronic City Phase II, Electronic City, Bengaluru, Karnataka, India");
	
		// 2. Verify the search result >>
		searchPGsPage.setBudgetFilter(driver, "5000 - 8000");
		searchPGsPage.setSharingPreferencesFilter(driver, "2 Sharing");
		searchPGsPage.setPgTypeFilter(driver, "Men");
		searchPGsPage.clickFilterLink(driver);
		searchPGsPage.openPgDetails(driver, "Zolo Goodfellas for Men");
		pgDetailPage.verifyPgDetailPageTitle(driver,"Zolo Goodfellas for Men");
		
		//3. Complete schedule a visit flow >>
		pgDetailPage.clickScheduleVisitLink(driver);
		pgDetailPage.setVisitDateForFuture(driver);
		pgDetailPage.setTimeSlotForVisit(driver, "9 am - 10 am");
		pgDetailPage.clickScheduleVisit(driver);
		
		seleniumBase.idleWait(15);
		System.out.println("Test Case is Pass");
	}
}
