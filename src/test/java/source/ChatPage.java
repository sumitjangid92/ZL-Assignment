package source;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.SeleniumBase;

public class ChatPage extends SeleniumBase{
	
	By chatIFrame			= By.cssSelector("iframe[data-test-id='ChatWidgetWindow-iframe']");
	By chatIcon 			= By.cssSelector(".Icon--chat");
	By optionButton 		= By.cssSelector("button.settings");
	By endChatOption		= By.cssSelector("button.end_chat");
	By endChatConfirmation	= By.xpath("//button[text()='End']");
	By chatWindowMinimizeIcon = By.cssSelector("div[style='display: block;'] div.overlay");

	public void switchToIframe(WebDriver driver) {
		driver.switchTo().frame(findElement(driver, chatIFrame));
	}
	
	public boolean verifyChatOpen(WebDriver driver) {
		if(findElement(driver, chatWindowMinimizeIcon)!=null && findElement(driver, chatWindowMinimizeIcon).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void closeChatIfOpen(WebDriver driver) {
		clickElement(driver, chatWindowMinimizeIcon);
	}
}
