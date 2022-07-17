package commonFunctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for login
	public static boolean verifyLogin(String username,String password)throws Throwable{
	// locate elements
	driver.findElement(By.xpath(config.getProperty("ObjUsername"))).sendKeys(username);
	Thread.sleep(3000);
	driver.findElement(By.xpath(config.getProperty("ObjPassword"))).sendKeys(password);
	Thread.sleep(3000);
	driver.findElement(By.xpath(config.getProperty("ObjLoginBtn"))).sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	String expected = "adminflow";
	String actual=driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Login Success"+actual+"    "+expected,true);
		return true;
	}
	else 
	{
		Reporter.log("Login Fail"+actual+"     "+expected,true);
		return false;
	}
	}
public void clickBranch()
{
	driver.findElement(By.xpath(config.getProperty("ObjBranchesBtn"))).click();
}
public static boolean verifyLogout() throws Throwable
{
	
	driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
	Thread.sleep(2000);
	if(driver.findElement(By.xpath(config.getProperty("ObjLoginBtn"))).isDisplayed())
	{
		Reporter.log("LogOut Success",true);
		return true;
	}else
	{
		Reporter.log("LogOut Fail",true);
		return false;	
		}
	}
public static boolean verifyBranchCreation(String branchname,String add1, String Address2, String Address3, String area,String ZipCode,String Country,String State,
		String City) throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjBranchesBtn"))).click();
driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(branchname);
driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(add1);
driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(area);
driver.findElement(By.xpath(config.getProperty("ObjZipcode"))).sendKeys(ZipCode);
new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(Country);
new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(State);
new Select(driver.findElement(By.xpath(config.getProperty("ObjCity")))).selectByVisibleText(City);
driver.findElement(By.xpath(config.getProperty("ObjSubmitBtn"))).click();
Thread.sleep(3000);
//capture alertmesage
String expectedalertbranch =driver.switchTo().alert().getText();
Thread.sleep(3000);
driver.switchTo().alert().accept();
String actualalertbranch ="New Branch with id";
if(expectedalertbranch.toLowerCase().contains(actualalertbranch.toLowerCase()))
{
	Reporter.log(expectedalertbranch,true);
	return true;
}
else
{
	Reporter.log("Branch Creation Fail",true);
	return false;
}
}
public static boolean verifyBranchUpdate(String branch,String address,String Area)throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjBranchesBtn"))).click();
driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
Thread.sleep(3000);
WebElement element = driver.findElement(By.xpath(config.getProperty("ObjBranch")));
element.clear();
element.sendKeys(branch);
WebElement element1 = driver.findElement(By.xpath(config.getProperty("ObjAdd1")));
element1.clear();
element1.sendKeys(address);
WebElement element2 = driver.findElement(By.xpath(config.getProperty("ObjArea1")));
element2.clear();
element2.sendKeys(Area);


Thread.sleep(3000);
driver.findElement(By.xpath(config.getProperty("ObjUpdateBtn"))).click();
Thread.sleep(3000);
String expectedbupdate =driver.switchTo().alert().getText();
Thread.sleep(3000);
driver.switchTo().alert().accept();
Thread.sleep(3000);

String actualBupdate ="Branch updated ";
if(expectedbupdate.toLowerCase().contains(actualBupdate.toLowerCase()))
{
	Reporter.log("Branch sucess",true);
	return true;
}
else
{
	Reporter.log("Branch update Fails",true); 
	return false;
	}
}
public static String genarateDate()
{
	Date date= new Date();
	DateFormat df= new SimpleDateFormat("YYYY_MM_DD");
	return df.format(date);
}


}