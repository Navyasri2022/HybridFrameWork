package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayers.AddEmpPage;
import applicationLayers.LogOutPage;
import applicationLayers.LoginPage;
import utilities.ExelFileUtil;

public class POMscript {
WebDriver driver;
String input = "D:\\NAVYA_AUTOMATIONTESTING\\Hybrid_FrameWork\\TestInput\\EmployeeData.xlsx";
String output="D:\\NAVYA_AUTOMATIONTESTING\\Hybrid_FrameWork\\TestOutput\\HybridPOM.xlsx";
ExtentReports report;
ExtentTest test;
@BeforeTest
public void adminLogin()throws Throwable
{
	report=new ExtentReports(input);
	driver= new FirefoxDriver();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void empCreation()throws Throwable
{
ExelFileUtil xl = new ExelFileUtil(input);
int rc= xl.rowCount("EmpData");
Reporter.log("no.of rows"+rc,true);
for(int i=1;i<=rc;i++)
{
	test=report.startTest("Validate Emp");
	String para1 = xl.getcelldata("EmpData", i, 0);
	String para2 = xl.getcelldata("EmpData", i, 1);
	String para3 = xl.getcelldata("EmpData", i, 2);
	AddEmpPage emp =PageFactory.initElements(driver, AddEmpPage.class);
	boolean res = emp.verifyEmp(para1, para2, para3);
	if(res)
	{
		xl.setcelldata("EmpData", i, 3, "Pass", output);
		test.log(LogStatus.PASS, "Emp Creation Success");
	}
	else
	{
		xl.setcelldata("EmpData", i, 3, "Fail", output);
		test.log(LogStatus.FAIL, "Emp Creation Fail");
	}
 }
}
@AfterTest
public void adminLogOut()throws Throwable
{
	report.endTest(test);
	report.flush();
	LogOutPage logout = PageFactory.initElements(driver, LogOutPage.class);
	logout.verifyLogOut();
	driver.close();
}



}
















