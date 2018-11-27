package winpackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import pagefactorypackage.PageFa;


public class WinClass extends PageFactory {
	
	ExtentReports extent;
	ExtentTest logger;
	public WiniumDriver objdriver;
	
	

	@BeforeClass 
	public void Configuration()throws MalformedURLException, InterruptedException
	{
		
		   DesktopOptions option = new DesktopOptions();
		   option.setApplicationPath("D:\\Debug\\DailyWorkLog.exe");
		 
		   objdriver = new WiniumDriver(new URL("http://localhost:9999"), option);
		   Thread.sleep(5000);
	}
	  
	 @BeforeTest

		public void StartTest() throws Exception
		{
			extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/WorklogWindows_Report.html", true);
			extent.addSystemInfo("Host Name","Automation Testing")
			.addSystemInfo("Environment","IT Test")
			.addSystemInfo("User Name","Arunn");

			extent.loadConfig(new File(System.getProperty("User dir")+"\\WorklogWindows_Report.xml"));
		}
	 
	 
	@Test(priority =1)
	public void BeforePunchInNegativeCase()
	{
		   logger = extent.startTest("BeforePunchInNegativeCase");
		   objdriver.findElement(By.name("Chart")).click();
		   objdriver.findElement(By.name("OK")).click();
		   logger.log(LogStatus.PASS,"Trying to access the application before PunchIn");
		   	
	}

	@Test(priority =2)
	public void Punchin()
	{   
		 logger = extent.startTest("Punchin");
		 objdriver.findElement(By.name("PUNCH IN")).click();
		 logger.log(LogStatus.PASS," Application Should be Punchin");
	}
	
	
	@Test(priority =3)
	public void TaskLogVerification() throws InterruptedException
	{   
		 logger = extent.startTest("TaskLogVerification");
		 objdriver.findElement(By.name("StartTime Row 3")).click();
		 objdriver.findElement(By.name("StartTime Row 4")).click();
		 objdriver.findElement(By.name("StartTime Row 5")).click();
		 objdriver.findElement(By.name("Vertical Scroll Bar")).click();
		 objdriver.findElement(By.id("rtxtNotes")).sendKeys("testing");
		 objdriver.findElement(By.id("btnStart")).click();
		 
		   Actions action = new Actions(objdriver);
		   WebElement element=objdriver.findElement(By.name("DailyWorkLog"));
		   //Double click
		   action.doubleClick(element).perform();
		 logger.log(LogStatus.PASS,"TaskLogVerification clicking the Row and Vertical Scroll Bar - Given Task Notes");
	}
	
	
	
	@Test(priority =4)
	public void Worklogchartview() throws InterruptedException
	{
		 logger = extent.startTest("Worklogchartview");
		 Thread.sleep(5000);
		 objdriver.findElement(By.name("Chart")).click();
		 Thread.sleep(2000);
		 logger.log(LogStatus.PASS,"Click the Worklog Chart View Menu");
		
	}
	
	@Test(priority =5)
	public void WeeklyReportFiltered() throws InterruptedException
	{
		   
		   logger = extent.startTest("WeeklyReportFiltered");
		   objdriver.findElement(By.name("Weekly Report")).click();
		   objdriver.findElement(By.id("picExportExcel")).click();
		   
		   objdriver.findElement(By.name("OK")).click();
		   objdriver.findElement(By.name("Submit")).click();
		   objdriver.findElement(By.id("lblStartDate")).click();
		   objdriver.findElement(By.id("dtpstartDate")).sendKeys("2017");
		   objdriver.findElement(By.id("dtpEndDate")).sendKeys("2018");
		   
		   objdriver.findElement(By.name("Submit")).click();
		   
		   logger.log(LogStatus.PASS,"Click the Weekly Report Menu Filtered the Report ");
		
	}


	@Test(priority =6)
	public void ExportWeeklyReport() throws InterruptedException, AWTException
	{
		 logger = extent.startTest("ExportWeeklyReport");
		 objdriver.findElement(By.id("picExportExcel")).click();
		 objdriver.findElement(By.name("File name:")).sendKeys("Test file");
		 objdriver.findElement(By.name("Save")).click();
		 Thread.sleep(19000);
		 objdriver.findElement(By.name("OK")).click();   
		 
		 logger.log(LogStatus.PASS,"Export the Yearly and Weekly Report");
		
	}
		   
	@Test(priority =7)
	public void EntryLogView() throws InterruptedException
	{
		  logger = extent.startTest("EntryLogView");
		  objdriver.findElement(By.name("Entry Log")).click();
		  Thread.sleep(2000);	
		  logger.log(LogStatus.PASS,"Cliking the EntryLog View Menu");
	}
	
	
	
	
	@Test(priority =8)
	public void TimelogView() throws InterruptedException, AWTException
	{
		  logger = extent.startTest("TimelogView");
		  objdriver.findElement(By.name("Time Log")).click();
		  Thread.sleep(2000);
		  logger.log(LogStatus.PASS,"Clicking the TimelogView Menu ");
	}   	
	

	
	
	@Test(priority =9)
	public void OtherTask() throws InterruptedException, AWTException
	{
		  logger = extent.startTest("OtherTask");
		  objdriver.findElement(By.name("Other Task")).click();
		        Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_F11);
		  objdriver.findElement(By.name("Submit")).click();
		  objdriver.findElement(By.name("OK")).click();
		  objdriver.findElement(By.name("Notes")).sendKeys(" teststetset stetstestes67testeststuy");
		  objdriver.findElement(By.name("Submit")).click();
		  objdriver.findElement(By.name("OK")).click();
		  objdriver.findElement(By.name("Clear")).click();
		  objdriver.findElement(By.id("cmbOtherTaskType")).click();
		  objdriver.findElement(By.name("Break")).click();
		  objdriver.findElement(By.id("cmbBreakType")).click();
		  objdriver.findElement(By.name("Other")).click();
		  objdriver.findElement(By.name("Notes")).sendKeys(" teststetset ");
		  objdriver.findElement(By.name("Submit")).click();
		  objdriver.findElement(By.name("OK")).click();
		  objdriver.findElement(By.id("cmbOtherTaskType")).click();
		  objdriver.findElement(By.name("Scrum Meeting")).click();
		  objdriver.findElement(By.id("cmbOtherTaskType")).click();
		  objdriver.findElement(By.name("ITSupportTask")).click();
		  objdriver.findElement(By.id("cmbOtherTaskType")).click();
		  objdriver.findElement(By.name("Other Meeting")).click();
		  objdriver.findElement(By.name("Submit")).click();
		  objdriver.findElement(By.name("OK")).click();
		  objdriver.findElement(By.name("Notes")).sendKeys("Minutes of meeting");
		  objdriver.findElement(By.name("Submit")).click();
		  objdriver.findElement(By.name("OK")).click();
		  objdriver.findElement(By.id("cmbOtherTaskType")).click();
		  objdriver.findElement(By.name("R & R Meeting")).click();
		  objdriver.findElement(By.id("cmbOtherTaskType")).click();
		  objdriver.findElement(By.name("CodeReview")).click();
		  objdriver.findElement(By.id("cmbOtherTaskType")).click();
		  objdriver.findElement(By.name("Discussion")).click();
		  objdriver.findElement(By.id("cmbOtherDepartment")).click();
		  objdriver.findElement(By.name("Analytics & IT")).click();
		  objdriver.findElement(By.id("cmbOtherProject")).click();
		  objdriver.findElement(By.name("POC")).click();
		  objdriver.findElement(By.name("Notes")).sendKeys("Testing");
		  objdriver.findElement(By.name("Submit")).click();
		  objdriver.findElement(By.name("OK")).click();
		  objdriver.findElement(By.id("dtpStartTime")).click();
		  objdriver.findElement(By.id("dtpEndTime")).click();
		  objdriver.findElement(By.name("Submit")).click();
		  objdriver.findElement(By.name("OK")).click();
		  objdriver.findElement(By.id("dgvTimeIntervals")).click();
		  objdriver.findElement(By.name("Forward")).click();
		  objdriver.findElement(By.name("Backward")).click();
		  
		  objdriver.findElement(By.id("lblPress")).click(); 
		  objdriver.findElement(By.name("Notes")).clear();
		  logger.log(LogStatus.PASS,"Clicking the Other Task Menu and Picking the Random Task Types and checking the validations ");
		  	  
		
	} 
	
	@Test(priority = 10)
	public void OtherBreakIdleTimeaded() throws AWTException
	{
		
		   logger = extent.startTest("Other Break Idle Timeadded");
		   objdriver.findElement(By.name("Other Task")).click();
		   Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_F11);
		   Actions action = new Actions(objdriver);
		   WebElement element=objdriver.findElement(By.name("EndTime Row 0"));
		   //Double click
		   action.doubleClick(element).perform();
		   objdriver.findElement(By.id("cmbOtherTaskType")).click();
		   objdriver.findElement(By.name("Break")).click();
		   objdriver.findElement(By.id("cmbBreakType")).click();
		   objdriver.findElement(By.name("Other")).click();
		   objdriver.findElement(By.name("Submit")).click();
		   logger.log(LogStatus.PASS,"Other Break IdleTime Added ");
		
		
	}
	
	
	
	@Test(priority = 11)
	public void TimeLogDeleted() throws AWTException
	{
		  
		 logger = extent.startTest("TimeLog Deleted");
		   
	       PageFa login = new PageFa(objdriver);
	       login.timelog_delete();
		logger.log(LogStatus.PASS,"Other Break Idle Time Deleted in TimeLog Page ");
		
	}
	
	@Test(priority =12)
	public void TeaBreakIdleTimeAdded() throws InterruptedException, AWTException
	{
		 logger = extent.startTest("TeaBreakIdleTimeAdded");
		 objdriver.findElement(By.name("Other Task")).click();
		 Robot robot = new Robot();
			
	     robot.keyPress(KeyEvent.VK_F11);
		   Actions action21 = new Actions(objdriver);
		   WebElement element21=objdriver.findElement(By.name("EndTime Row 0"));
		   action21.doubleClick(element21).perform();
		   objdriver.findElement(By.id("cmbOtherTaskType")).click();
		   objdriver.findElement(By.name("Break")).click();
		   objdriver.findElement(By.id("cmbBreakType")).click();
		   objdriver.findElement(By.name("Tea Break")).click();
		   objdriver.findElement(By.name("Submit")).click();
		   PageFa login = new PageFa(objdriver);
	       login.timelog_delete();
		   
		 logger.log(LogStatus.PASS,"TeaBreak IdleTime Added and Deleted that Tea Break Time In TimeLog Page ");
	}
	
	@Test(priority =13)
	public void LunchBreakIdleTimeAdded() throws InterruptedException, AWTException
	{
		logger = extent.startTest("LunchBreakIdleTimeAdded");
		objdriver.findElement(By.name("Other Task")).click();
	    Robot robot = new Robot();
		
	    robot.keyPress(KeyEvent.VK_F11);
	    Actions action21 = new Actions(objdriver);
	    WebElement element21=objdriver.findElement(By.name("EndTime Row 0"));
	    action21.doubleClick(element21).perform();
	    objdriver.findElement(By.id("cmbOtherTaskType")).click();
	    objdriver.findElement(By.name("Break")).click();
	    objdriver.findElement(By.id("cmbBreakType")).click();
	    objdriver.findElement(By.name("Lunch Break")).click();
	    objdriver.findElement(By.name("Submit")).click();
	    PageFa login = new PageFa(objdriver);
        login.timelog_delete(); 
		 
		logger.log(LogStatus.PASS,"LunchBreak IdleTime Added and Deleted that Tea Break Time In Time Log Page");
	}

	@Test(priority =14)
	public void DiscussionIdleTimeAdded() throws AWTException, InterruptedException
	{
		
		   logger = extent.startTest("Discussion Idle Time Added");
		   Thread.sleep(3000);
		   objdriver.findElement(By.name("Other Task")).click();
		   Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_F11);
		    Actions action = new Actions(objdriver);
		    WebElement element=objdriver.findElement(By.name("EndTime Row 0"));
		    //Double click
		    action.doubleClick(element).perform();
		   
		    objdriver.findElement(By.id("cmbOtherTaskType")).click();
			objdriver.findElement(By.name("Discussion")).click();
		    objdriver.findElement(By.id("cmbOtherDepartment")).click();
			Thread.sleep(2000);
			objdriver.findElement(By.name("Analytics & IT")).click();
			objdriver.findElement(By.id("cmbOtherProject")).click();
			objdriver.findElement(By.name("Automation Testing")).click();
			objdriver.findElement(By.name("Notes")).sendKeys("Discuss with team Testting Testting Testting Testting Testting Testting Testting Testting Testting Testting ");
			objdriver.findElement(By.name("Submit")).click();
			      
		   logger.log(LogStatus.PASS,"Discussion Idle Time Added in Other Task");
		
		
	}
	
	@Test(priority = 15)
	public void DiscussionIdleTimeDeleted() throws AWTException
	{
		
		logger = extent.startTest("DiscussionIdleTimeDeleted");
		PageFa login = new PageFa(objdriver);
	    login.timelog_delete();
		logger.log(LogStatus.PASS,"Discussion Idle Time Deleted in Time log Page ");	
		
		
	}
	
	
	@Test(priority = 16)
	public void OtherSupportTaskIdleTimeAdded() throws AWTException
	{
		
		   logger = extent.startTest("OtherSupportTaskIdleTimeAdded");
		   
		   objdriver.findElement(By.name("Other Task")).click();
		   objdriver.findElement(By.name("Submit")).click();
		   objdriver.findElement(By.name("OK")).click();
		   Robot robot = new Robot();
		   robot.keyPress(KeyEvent.VK_F11);
		   Actions action = new Actions(objdriver);
		   WebElement element=objdriver.findElement(By.name("EndTime Row 0"));
		   //Double click
		   action.doubleClick(element).perform();
		   objdriver.findElement(By.id("cmbOtherTaskType")).click();
		   objdriver.findElement(By.name("OtherSupportTask")).click();
		   objdriver.findElement(By.name("Submit")).click();
		   objdriver.findElement(By.name("OK")).click();
		   objdriver.findElement(By.id("cmbOtherDepartment")).click();
		   objdriver.findElement(By.name("Analytics & IT")).click();
		   objdriver.findElement(By.name("Submit")).click();
		   objdriver.findElement(By.name("OK")).click();
		   objdriver.findElement(By.id("cmbOtherProject")).click();
		   objdriver.findElement(By.name("Automation Testing")).click();
		   objdriver.findElement(By.name("Submit")).click();
		   objdriver.findElement(By.name("OK")).click();
		   objdriver.findElement(By.name("Notes")).sendKeys("Windows Automation Testing Testting Testting Testting Testting Testting Testting Testting Testting Testting ");
		   objdriver.findElement(By.name("Submit")).click();
			    
			  
		   logger.log(LogStatus.PASS,"Other Support Task Idle Time Added ");
		
		
	}
	
	@Test(priority = 17)
	public void OtherSupportTaskIdleTimeDeleted() throws AWTException
	{
		logger = extent.startTest("OtherSupportTaskIdleTimeDeleted");
		PageFa login = new PageFa(objdriver);
	    login.timelog_delete();
	    logger.log(LogStatus.PASS,"Other Support Task IdleTime Deleted in Time Log Page ");	

	}
	
	
	@Test(priority = 18)
	public void ITSupportTaskIdleTimeAdded() throws AWTException
	{
		
		   logger = extent.startTest("ITSupportTaskIdleTimeAdded");
		   objdriver.findElement(By.name("Other Task")).click();
		   Robot robot = new Robot();
		   robot.keyPress(KeyEvent.VK_F11);
		   Actions action = new Actions(objdriver);
		   WebElement element=objdriver.findElement(By.name("EndTime Row 0"));
		    //Double click
		   action.doubleClick(element).perform();
		   objdriver.findElement(By.id("cmbOtherTaskType")).click();
		   objdriver.findElement(By.name("ITSupportTask")).click();
		   objdriver.findElement(By.name("Notes")).sendKeys("Windows Automation Testing Testting Testting Testting Testting Testting Testting Testting Testting Testting ");
		   objdriver.findElement(By.name("Submit")).click();
			      
		   logger.log(LogStatus.PASS,"ITSupport Task IdleTime Added ");
		
		
	}
	
	@Test(priority = 19)
	public void ITSupportTaskIdleTimeDeleted() throws AWTException
	{
		logger = extent.startTest("ITSupportTaskIdleTimeDeleted");
		PageFa login = new PageFa(objdriver);
	    login.timelog_delete();
	    logger.log(LogStatus.PASS,"ITSupport Task IdleTime Deleted In Time Log Page");	

	}
	
	
	@Test(priority = 20)
	public void CodeReviewIdleTimeAdded() throws AWTException, InterruptedException
	{
		
		   logger = extent.startTest("CodeReviewIdleTimeAdded");
		   objdriver.findElement(By.name("Other Task")).click();
		   Robot robot = new Robot();
		   robot.keyPress(KeyEvent.VK_F11);
		   Actions action = new Actions(objdriver);
		   WebElement element=objdriver.findElement(By.name("EndTime Row 0"));
		    //Double click
		   action.doubleClick(element).perform();
		    
		      objdriver.findElement(By.id("cmbOtherTaskType")).click();
			  objdriver.findElement(By.name("CodeReview")).click();
			  objdriver.findElement(By.name("Submit")).click();
			  objdriver.findElement(By.name("OK")).click();
			  objdriver.findElement(By.id("cmbReviewer")).click();
			  objdriver.findElement(By.name("Suresh[SURV2E06699]")).click();
			  objdriver.findElement(By.name("Submit")).click();
			  objdriver.findElement(By.name("OK")).click();
			  objdriver.findElement(By.id("cmbOtherDepartment")).click();	  
		      objdriver.findElement(By.name("Human Resources")).click();
			  objdriver.findElement(By.name("Submit")).click();
			  objdriver.findElement(By.name("OK")).click();
			  objdriver.findElement(By.id("cmbOtherProject")).click();
			  objdriver.findElement(By.name("HRPortal")).click(); 
			  objdriver.findElement(By.name("Notes")).sendKeys("Windows Automation Testing Testting Testting Testting Testting Testting Testting Testting Testting Testting ");
			  objdriver.findElement(By.name("Submit")).click();  
		   logger.log(LogStatus.PASS,"CodeReview IdleTime Added ");
		
		
	}
	
	@Test(priority = 21)
	public void CodeReviewIdleTimeDeleted() throws AWTException
	{
		logger = extent.startTest("CodeReviewIdleTimeDeleted");
		PageFa login = new PageFa(objdriver);
	    login.timelog_delete();
	    logger.log(LogStatus.PASS,"CodeReview IdleTime Deleted In Time Log Page ");	

	}
	
	@Test(priority = 22)
	public void ApplicationMinimize()
	{
		objdriver.close();
	}
	
	@Test(priority = 23)
	public void ReOpenApplicationAddDiffTask() throws MalformedURLException, InterruptedException
	{
		logger = extent.startTest("ReOpen the Application");
		   DesktopOptions option = new DesktopOptions();
		   option.setApplicationPath("D:\\Debug\\DailyWorkLog.exe");
		   objdriver = new WiniumDriver(new URL("http://localhost:9999"), option);
		   Thread.sleep(5000);
		   objdriver.findElement(By.name("PUNCH IN")).click();
		   Thread.sleep(3000);
		   logger.log(LogStatus.PASS,"Application Should be Reopened");	
	}
	
	
	@Test(priority = 24)
	public void CurrentTask() throws InterruptedException
	{
		   logger = extent.startTest("Current Task");
		   Actions action = new Actions(objdriver);
		   objdriver.findElement(By.name("Minimize")).click();
			
		   WebElement element1=objdriver.findElement(By.name("DailyWorkLog"));
		
		   action.contextClick(element1).perform();
		   
		   objdriver.findElement(By.name("Hide Task Time")).click();
		   
		   WebElement element2=objdriver.findElement(By.name("DailyWorkLog"));
			
		   action.contextClick(element2).perform();
		   
		   objdriver.findElement(By.name("Show Task Time")).click();
		   	   
		   WebElement element3=objdriver.findElement(By.name("DailyWorkLog"));
			
		   action.contextClick(element3).perform();
		  
		   objdriver.findElement(By.name("Show / Hide Main Form")).click();
		   
		   WebElement element4=objdriver.findElement(By.name("DailyWorkLog"));
			
		   action.contextClick(element4).perform();
		   objdriver.findElement(By.name("Show / Hide Main Form")).click();
		   
		   WebElement element5=objdriver.findElement(By.name("DailyWorkLog"));
			
		   action.contextClick(element5).perform();
		   objdriver.findElement(By.name("Show / Hide Performace Chart")).click();
		   WebElement element6=objdriver.findElement(By.name("DailyWorkLog"));
			
		   action.contextClick(element6).perform();
		   objdriver.findElement(By.name("Show / Hide Performace Chart")).click();
		   WebElement element=objdriver.findElement(By.name("DailyWorkLog"));
		   //Double click
		   action.doubleClick(element).perform(); 
		   
		   
		   Thread.sleep(5000);
		   objdriver.findElement(By.name("Current Task")).click();
		  
		   objdriver.findElement(By.id("cmbVTSID")).click();
		   objdriver.findElement(By.name("IT9309 - HireMee")).click(); 
		   
		   objdriver.findElement(By.id("cmbVTSID")).click();
		   objdriver.findElement(By.name("IT9375 - HireMee")).click(); 
		   
		   objdriver.findElement(By.id("btnStart")).click();
		   Thread.sleep(4000);
		   objdriver.findElement(By.name("OK")).click(); 
		  
		   Thread.sleep(6000); 
		   
		   objdriver.findElement(By.id("mtxtTimeEstimate")).sendKeys("0000");
		   objdriver.findElement(By.id("btnAdd")).click();
		   objdriver.findElement(By.name("OK")).click();
		   objdriver.findElement(By.id("rtxtAdditionalHours")).sendKeys("Testing");
		   objdriver.findElement(By.id("btnAdd")).click();
		   objdriver.findElement(By.name("OK")).click();
		   objdriver.findElement(By.id("mtxtTimeEstimate")).clear();
		   objdriver.findElement(By.id("mtxtTimeEstimate")).sendKeys("0100");
		   objdriver.findElement(By.id("rtxtAdditionalHours")).sendKeys("Testing teststs dfsfs fsfsdfsdfdsfdsfsdffsfsfds");
		   objdriver.findElement(By.id("btnAdd")).click();
		   objdriver.findElement(By.name("OK")).click(); 
		   logger.log(LogStatus.PASS,"CurrentTask Should bve started and Adding Additional hours");
	}
	
	
	@Test(priority = 25)
	public void ApplicationClosed()
	{
		logger = extent.startTest("Application Closed");
		objdriver.close();
	   logger.log(LogStatus.PASS,"Application Should be closed");
	}
		

	
	@AfterMethod
	public void getReport(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE) {
			
			/*capture Screenshot
			TakesScreenshot ts=(TakesScreenshot)objdriver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./screenshot/"+result.getName()+".png")); */
			//log
			logger.log(LogStatus.FAIL, "Test Case Failed is "+ result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+ result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+ result.getThrowable());
			String screenshotPath = WinClass.getScreenshot(objdriver, result.getName()); 
            logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
			}else if(result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.FAIL, "Test Case Failed is "+ result.getName());
			}
		extent.endTest(logger);
		}
	  public static String getScreenshot(WiniumDriver objdriver, String screenshotName) throws Exception {
	         String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	         TakesScreenshot ts = (TakesScreenshot) objdriver;
	         File source = ts.getScreenshotAs(OutputType.FILE);
	                 //after execution, you could see a folder "FailedTestsScreenshots" under src folder
	         String destination = System.getProperty("user.dir") + "/screenshot/"+screenshotName+dateName+".png";
	         File finalDestination = new File(destination);
	         FileUtils.copyFile(source, finalDestination);
	         return destination;
	     }

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}
	


}
