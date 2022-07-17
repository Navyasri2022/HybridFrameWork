package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExelFileUtil;

public class HyBridDataScript extends AppUtil {
	String inputpath = "D:\\NAVYA_AUTOMATIONTESTING\\Hybrid_FrameWork\\TestInput\\DataEngine.xlsx";
	String outputpath="D:\\NAVYA_AUTOMATIONTESTING\\Hybrid_FrameWork\\TestOutput\\HybridFramework.xlsx";
	ExtentReports reports;
	ExtentTest test;
String TCSheet = "TestCases";
String TSSheet = "TestSteps";
@Test
public void startTest() throws Throwable
{
	reports = new ExtentReports("./Reports/"+FunctionLibrary.genarateDate()+"    "+"HydridReports.html");
	boolean res = false;
	String tcres="";
	ExelFileUtil xl = new ExelFileUtil(inputpath);
	//count no of rows
	int TCCount = xl.rowCount(TCSheet);
	int TSCount =xl.rowCount(TSSheet);
	//count no of cells
	int TCcells = xl.cellCount(TCSheet);
	int TScells = xl.cellCount(TSSheet);
	Reporter.log(TCCount+"    "+TSCount+"    "+TCcells+"      "+TScells,true);
	//iterate all rows 
	for(int i=1;i<=TCCount;i++)
	{
		//read module name
		String modulename =xl.getcelldata(TCSheet, i, 1);
		test= reports.startTest(modulename);
		//read status cell
		String statuscell = xl.getcelldata(TCSheet, i, 2);
		if(statuscell.equalsIgnoreCase("Y"))
		{
			String tcid = xl.getcelldata(TCSheet, i, 0);
			//iterate rows in TSSheets
			for(int j=1;j<=TSCount;j++)
			{
				//read description cell
				String Description=xl.getcelldata(TSSheet, j, 2);
				//read tsid
				String tsid = xl.getcelldata(TSSheet, i, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					//read keyword cell
					String keyword = xl.getcelldata(TSSheet, j, 4);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String para1 = xl.getcelldata(TSSheet, j, 5);
						String para2 = xl.getcelldata(TSSheet, j, 6);
						res= FunctionLibrary.verifyLogin(para1, para2);
						test.log(LogStatus.INFO,Description );
						
					}
					else if (keyword.equalsIgnoreCase("BranchCreation")) 
					{
						String para1 = xl.getcelldata(TSSheet, j, 5);
						String para2 = xl.getcelldata(TSSheet, j, 6);
						String para3 = xl.getcelldata(TSSheet, j, 7);
						String para4 = xl.getcelldata(TSSheet, j, 8);
						String para5 = xl.getcelldata(TSSheet, j, 9);
						String para6 = xl.getcelldata(TSSheet, j, 10);
						String para7 = xl.getcelldata(TSSheet, j, 11);
						String para8 = xl.getcelldata(TSSheet, j, 12);
						String para9 = xl.getcelldata(TSSheet, j, 13);
						res = FunctionLibrary.verifyBranchCreation(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						test.log(LogStatus.INFO,Description );
						
					}
					else if(keyword.equalsIgnoreCase("BranchUpdate"))
					{
						String para1 = xl.getcelldata(TSSheet, j, 5);
						String para2 = xl.getcelldata(TSSheet, j, 6);
						String para5 = xl.getcelldata(TSSheet, j, 9);
						res = FunctionLibrary.verifyBranchUpdate(para1, para2, para5);
						test.log(LogStatus.INFO,Description );
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						res = FunctionLibrary.verifyLogout();
						test.log(LogStatus.INFO,Description );
					}
				String tsres="";
				if(res)
				{
					//write as pass in tssheet
					tsres="Pass";
					xl.setcelldata(TSSheet, j, 3, tsres, outputpath);
					test.log(LogStatus.INFO,Description );
				}
				else
				{
					//write as fail in tssheet
					tsres="Fail";
					xl.setcelldata(TSSheet, j, 3, tsres, outputpath);
					test.log(LogStatus.INFO,Description );
				}
				tcres=tsres;
				}
			}
			//write tcres into tc sheet
			xl.setcelldata(TCSheet, i, 3, tcres, outputpath);
			reports.endTest(test);
			reports.flush();
		}
		else
		{
			//write as blocked into which is flagged as N
			xl.setcelldata(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
}
}




























