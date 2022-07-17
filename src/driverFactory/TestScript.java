package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayers.AddEmpPage;
import applicationLayers.LogOutPage;
import applicationLayers.LoginPage;

public class TestScript {
WebDriver driver;
@BeforeTest
public void setUp()throws Throwable
{
	driver = new FirefoxDriver();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void validateEmp()throws Throwable
{
	AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
	Thread.sleep(2000);
	boolean res = emp.verifyEmp("Akkineni", "Nageswar", "Rao");
	Reporter.log("emp creation"+res,true);
}
@AfterTest
public void tearDown()throws Throwable
{
	LogOutPage logOut = PageFactory.initElements(driver, LogOutPage.class);
	logOut.verifyLogOut();
	driver.close();
}
}
