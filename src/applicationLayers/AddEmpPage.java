package applicationLayers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
WebDriver driver;
public AddEmpPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//b[normalize-space()='PIM']")
WebElement pim;
@FindBy(name="btnAdd")
WebElement addbtn;
@FindBy(name="firstName")
WebElement fname;
@FindBy(name="middleName")
WebElement mname;
@FindBy(name="lastName")
WebElement lname;
@FindBy(name="employeeId")
WebElement beforeempid;
@FindBy(name="personal[txtEmployeeId]")
WebElement afterempid;
@FindBy(id="btnSave")
WebElement savebtn;
//method for verifyemp
public boolean verifyEmp(String firstname, String middlename,String lastname)throws Throwable
{
	Actions ac = new Actions(driver);
	Thread.sleep(5000);
	ac.moveToElement(pim).click().perform();
	Thread.sleep(2000);
	ac.moveToElement(addbtn).click().perform();
	Thread.sleep(2000);
	this.fname.sendKeys(firstname);
	this.mname.sendKeys(middlename);
	this.lname.sendKeys(lastname);
	String expected= this.beforeempid.getAttribute("value");
	Thread.sleep(2000);
	this.savebtn.click();
	Thread.sleep(2000);
	String actualid = this.afterempid.getAttribute("value");
	Thread.sleep(2000);
	if (expected.equals(actualid)) 
	{
		Reporter.log("emp sucess"+expected+"     "+actualid,true);
		return true;
	}
	else
	{
		Reporter.log("emp fail"+expected+"     "+actualid,true);
		return false;
	}
}


}
