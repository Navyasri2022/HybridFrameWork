package applicationLayers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
@FindBy(name="txtUsername")
WebElement ObjUser;
@FindBy(name="txtPassword")
WebElement ObjPass;
@FindBy(xpath="//input[@id='btnLogin']")
WebElement ObjLoginBtn;
	
public void verifyLogin(String Username,String Password)throws Throwable
{
	ObjUser.sendKeys(Username);
	ObjPass.sendKeys(Password);
	Thread.sleep(3000);
	ObjLoginBtn.click();
	Thread.sleep(3000);
}


}
