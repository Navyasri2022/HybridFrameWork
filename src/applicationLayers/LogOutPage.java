package applicationLayers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage {
@FindBy(id="welcome")
WebElement welcomebtn;
@FindBy(xpath="//a[normalize-space()='Logout']")
WebElement logoutbtn;
//method for logout
public void verifyLogOut()throws Throwable
{
	welcomebtn.click();
	Thread.sleep(2000);
	logoutbtn.click();
	Thread.sleep(2000);
}
}
