package source;
import org.openqa.selenium.By;

import base.SeleniumBase;

public class ChatPage extends SeleniumBase{
	
	By chatIcon 			= By.cssSelector(".Icon--chat");
	By optionButton 		= By.cssSelector("button.settings");
	By endChatOption		= By.cssSelector("button.end_chat");
	By endChatConfirmation	= By.xpath("//button[text()='End']");
	By chatWindowMinimizeIcon = By.cssSelector("div.overlay");

	public void openChat() {
		
	}
}
