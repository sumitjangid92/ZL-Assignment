package base;

import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	
	public static WebDriver driver = null;
	public static Properties config = new Properties();
		
	@BeforeSuite
	public void defaultSetup() {
		config = loadConfig("config.properties");
		initializeDriver();
		openWebSite();
	}
	
	
	//open website
	public void openWebSite() {
		driver.get(config.getProperty("zolo_website_url"));
	}

	// This method is to initialize driver
	public void initializeDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\chromedrivers\\chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments(new String[] { "--start-maximized" });
		driver = new ChromeDriver(options);
	}
	
	//This method is to load properties files
	public Properties loadConfig(String fileName) {
		try {
			Properties config = new Properties();
			ClassLoader classLoader = TestBase.class.getClassLoader();
			InputStream fileData = classLoader.getResourceAsStream(fileName);
			config.load(fileData);
			return config;

		} catch (Exception e) {
			System.out.println("Exception " +e.getMessage());
			return null;
		}
	}
	
	
	//@AfterSuite()
	public void quitDriver()
	{
		if(driver!=null) {
			driver.quit();
		}
	}
	
	
}
