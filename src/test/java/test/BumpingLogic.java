package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import ErrorCollectors.ErrorCollector;
import base.BaseTest;
import utilities.TestUtil;

public class BumpingLogic extends BaseTest {
	@BeforeMethod
	public void init() {
		
		startup();
	}
	@AfterMethod
	public void close() {
		System.out.print("done with single testcase...");
		tearDown();
	}
	
	@Test(priority = 1, dataProvider = "CombineData")
	public void TestLogic(Hashtable<String, String> data)
			throws AddressException, IOException, MessagingException, InterruptedException, AWTException {

// Login Page
		try {

		typeInt("AccID_CSS", Config.getProperty("AccID"));
		type("UserID_CSS", Config.getProperty("Username"));
		type("Password_CSS", Config.getProperty("Password"));
		click("Login_CSS");
		click("Accept_XPATH");
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(KeyEvent.VK_LEFT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
// Landing Page
		
			String Title = driver.getTitle();
			System.out.println(Title);
			Assert.assertTrue(Title.contains("Arbella Insurance Group"), "Error in the Landing Page...");
			System.out.println("Assert executed... No Error");
			
// Landing Page and selecting a Client for the creation of quote
		
		String ClientName = "Vinod";
			String BeforeX = "//*[@id='dgClients']/tbody/tr[";
			String AfterX = "]/td[2]";
			for (int i = 2; i <= 8; i++) {
				String name = driver.findElement(By.xpath(BeforeX + i + AfterX)).getText();
				if (name.contains(ClientName)) {
					int j = 1 + i;
					driver.findElement(By.xpath("//*[@id='dgClients_ctl0" + j + "_Select']")).click();
					break;
				}
			}

// First Screen ----- Select Companies
			click("NewHome_CSS");
			List<WebElement> checkBox = driver.findElements(By.xpath("//*[@id='rptrCompanies_ctl00_chkCompany']"));
			checkBox.get(0).click();
			System.out.println(checkBox.get(0).getAttribute("checked"));
			click("companiesNext_CSS");
			System.out.println("Select Companies screen executed...");

// Second Screen ------ General Info
			click("Today_CSS");
			select("Credit_CSS", data.get("Credit"));
			select("Coverage_CSS", data.get("Coverage"));
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			select("Company_CSS", data.get("Company"));
			click("GenralInfo_CSS");
			System.out.println("General Info screen executed...");
		
// Third Screen---- PropertyInfo
			
			typeInt("Built_CSS", data.get("Built"));
			select("Construction_CSS", data.get("Construction"));
			driver.findElement(By.cssSelector(OR.getProperty("Units_CSS"))).clear();
			typeInt("Units_CSS", data.get("Units"));
			type("RentalDate_CSS", data.get("RentalDate"));
			typeInt("Footage_CSS", data.get("Footage"));
			typeInt("Distance_CSS", data.get("Distance"));
			click("NxtPropertyInfo_CSS");
			System.out.println("PropertyInfo screen executed...");
		
// Fourth Screen ----- CoverageInfo
			
			selectIndex("DeductiblePLRater_CSS",data.get("DeductiblePLRater"));
			Thread.sleep(2000);
			
			select = new Select(driver.findElement(By.cssSelector(OR.getProperty("DeductiblePLRater_CSS"))));
			WebElement tmp1 = select.getFirstSelectedOption();
			String actual1 = tmp1.getText();
			System.out.println("Deductible is showing ==================:"+actual1);
			typeInt("Dwelling_CSS", data.get("Dwelling"));
			typeInt("PersonalProp_CSS", data.get("PersonalProp"));

			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);

			click("NxtCoverageInfo_CSS");
			System.out.println("CoverageInfo screen executed...");
// Fifth Screen ---------- AdditionalInfo
			click("NxtAddInfo_CSS");
			System.out.println("AdditionalInfo screen executed...");

// Sixth Screen ---------- LossInfo

			click("NxtLossInfo_CSS");
			System.out.println("AdditionalInfo screen executed...");
// Seventh Screen ----------- Co. Questions

			select("AutoRewards_CSS", data.get("AutoRewards"));
			Thread.sleep(1000);

			click("HeatSourcesTXT_XPATH");
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(500);
			click("Rate_CSS");
			System.out.println("Company Question screen executed...");

// Eighth Screen ------------- Rate
			Thread.sleep(30000);
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(OR.getProperty("ArbellaIMG_CSS")));
		//	wait.until(ExpectedConditions.presenceOfElementLocated(driver.findElement(By.cssSelector(OR.getProperty("ArbellaIMG_CSS")))));
			click("ArbellaIMG_CSS");
			winids = driver.getWindowHandles();
			iterate = winids.iterator();
			String first_winids = iterate.next();
			String eApp_winids1 = iterate.next();
			driver.switchTo().window(eApp_winids1);		
			System.out.println(" Rating is successful !!!!!!!!");

// eApp Agency/Applicant Information -- First Screen
			Thread.sleep(11000);
			click("popCont_CSS");
			selectIndex("AAdd_CSS", data.get("AgencyAddress"));
			Thread.sleep(500);
			click("PCode_CSS");

			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			selectIndex("LProd_CSS", data.get("LicensedProducer"));
			typeInt("YearOcc_CSS", data.get("CurrentOccupation"));
			typeInt("YearEmp_CSS", data.get("CurrentEmployer"));

			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("one with eApp's Agency/Applicant Information");

	
// eApp Applicant Information Continued- Second Screen
			Thread.sleep(2000);
			select("RecentApp_XPATH", "No");
			click("RiskZip_CSS");
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("Done with eApp's Applicant Information");

// eApp Dwelling Details- Third Screen
			Thread.sleep(1000);
			select("SmokeDetector_CSS", data.get("SmokeDetector"));
			select("Deadbolt_CSS", data.get("Deadbolt"));
			select("FireExti_CSS", data.get("FireExti"));
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("Done with  Dwelling Details!!!!!!!");

		
// eApp Dwelling Details Conti- Fourth Screen
			
			Thread.sleep(2000);
			select = new Select(driver.findElement(By.xpath(OR.getProperty("Deductible_XPATH"))));
			WebElement tmp = select.getFirstSelectedOption();
			String actual = tmp.getText();
			
			double dil = Double.parseDouble(data.get("Deductible"));
			int intVal = (int) dil;
			String stringIn = Integer.toString(intVal);
			
			System.out.println("-------------------------------------------------");
			System.out.println("Actual Value is :"+actual);
			System.out.println("eApp String expected value: "+stringIn);
			System.out.println("-------------------------------------------------");
			
			Assert.assertTrue(actual.contains(stringIn), "Error... Deductible is showing other value than expected...");
			System.out.println("Done with Dwelling details Assertion test case");
 
			click("Amps_CSS");
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("Done with Dwelling details operations...");

// eApp Endorsments - Fifth Screen

			click("Earthquake_CSS");
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("Done with execution of Endorsement Page!!!!!!!!");


// eApp Loss History - Sixth Screen

			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#continueButton")).click();
			System.out.println("Done with Loss History Screen ");

	
// eApp quote summary- Seventh Screen

		 Thread.sleep(1000);
			String head = driver.findElement(By.cssSelector(OR.getProperty("HeadingQC_CSS"))).getText();
			System.out.println("-------------------------------------------------");
			System.out.println(head);
			System.out.println("-------------------------------------------------");
			Assert.assertTrue(head.contains("Quote Summary"),"It is not conataining heading");
		} catch (Throwable t) {
			System.out.println("Done with quote!!!!!!!!");
			ErrorCollector.addVerificationFailure(t);
			t.printStackTrace();
		}

	}

	@DataProvider()
	public Object[][] getData() {
		return TestUtil.getData("LoginPage");
	}

	@DataProvider()
	public Object[][] getClient() {
		return TestUtil.getData("NewClient");
	}

	@DataProvider()
	public Object[][] GeneralInfo() {
		return TestUtil.getData("GeneralInfo");
	}

	@DataProvider()
	public Object[][] PersonalInfo() {
		return TestUtil.getData("PersonalInfo");
	}

	@DataProvider()
	public Object[][] CoverageInfo() {
		return TestUtil.getData("CoverageInfo");
	}

	@DataProvider()
	public Object[][] CoQuestions() {
		return TestUtil.getData("CoQuestions");
	}

	@DataProvider()
	public Object[][] eAppAAInfo() {
		return TestUtil.getData("eAppAAInfo");
	}

	@DataProvider()
	public Object[][] Dwelling() {
		return TestUtil.getData("Dwelling");
	}

	@DataProvider()
	public Object[][] DwellingDetails() {
		return TestUtil.getData("DwellingDetails");
	}

	@DataProvider()
	public Object[][] CombineData() {
		return TestUtil.getData("CombineData");
	}
}
