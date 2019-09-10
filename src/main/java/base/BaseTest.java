package base;

import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;



//import com.relevantcodes.extentreports.ExtentReports;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;
import utilities.TestConfig;
import listeners.WebEventListener;

public class BaseTest {

	/*
	 * Excel - done Logs - done Properties - done TestNG - done JavaMail - done
	 * ReportNG Database WebDriver - done Explicit and ImplicitWait - done
	 * Keywords - done Screenshots Maven - Build tool Jenkins
	 * 
	 */

	public static WebDriver driver;
	

	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	//public static final ExtentReports reports = ExtentReports.get(BaseTest.class);
	//public static final ExtentReports report = ExtentReports.get(BaseTest.class);
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	public static WebElement dropdown;
	public static WebElement clk;
	public static Robot robot;
	public static Set<String> winids;
	public static Iterator<String> iterate;
	public static Actions action;
	public static Select select; 
	@BeforeSuite
	public void setUp() {


		// Properties File
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.info("Config file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
			log.info("OR file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

/* _____________________________________________________________________________________________________________________________ */
// Database initialization
		try {
			DbManager.setMysqlDbConnection();
			log.info("DB Connection established !!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void startup() {

		log.info("Initiallaizing browser and other settings");
/* _____________________________________________________________________________________________________________________________ */
// Browser's Configuration

		if (Config.getProperty("browser").equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Firefox Launched");

		} else if (Config.getProperty("browser").equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome Launched");

		} else if (Config.getProperty("browser").equals("ie")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			log.info("IE Launched");
		}

/* ___________________________________________________________________________________________________________________________________________________ */
//Initializing object of EventFiringWebDriver

/*		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;*/
/* ___________________________________________________________________________________________________________________________________________________ */
// URL initialization, Implicit Wait & Explicit Wait

		driver.get(Config.getProperty("testsiteurl"));
		log.info("Navigated to : " + Config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
		
		winids = driver.getWindowHandles();
		iterate = winids.iterator();
		
		action = new Actions(driver);
	
		System.out.println(iterate.next());
	
		

	}

/* ________________________________________________________________________________________________________________________________ */
// Click

	public static void click(String locatorKey) {

	//	WebElement clk;

		if (locatorKey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
			
		//	clk.click();

		}

		else if (locatorKey.endsWith("_CSS")) {
			clk = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			clk.click();
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			clk = driver.findElement(By.id(locatorKey));
			clk.click();
		}
	//	utilities.jsExecutor.drawBorder(clk, driver);
		log.info("Clicking on an Element : " + locatorKey);

	}

/* __________________________________________________________________________________________________________________________________ */
// Type

	public static void type(String locatorKey, String value) throws IOException, AddressException, MessagingException {

		if (locatorKey.endsWith("_XPATH")) {
			clk = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
			clk.sendKeys(value);
		}

		else if (locatorKey.endsWith("_CSS")) {
			clk = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			clk.sendKeys(value);
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			clk =driver.findElement(By.id(locatorKey));
			clk.sendKeys(value);
		}
		utilities.jsExecutor.drawBorder(clk, driver);
		log.info("Typing in an Element : " + locatorKey + " entered the value as : " + value);

	}
	
	public static void typeInt(String locatorKey, String value) throws IOException, AddressException, MessagingException {
		//WebElement clk;
		double doub = Double.parseDouble(value);
		int intValue = (int) doub;
		String stringInt = Integer.toString(intValue);
		
		if (locatorKey.endsWith("_XPATH")) {
			clk = driver.findElement(By.xpath(OR.getProperty(locatorKey)));		
			clk.sendKeys(stringInt);
		}
		else if (locatorKey.endsWith("_CSS")) {
			clk =driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			clk.sendKeys(stringInt);
		
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			clk = driver.findElement(By.id(locatorKey));
			clk.sendKeys(stringInt);
		}
		utilities.jsExecutor.drawBorder(clk, driver);
		log.info("Typing in an Element : " + locatorKey + " entered the value as : " + stringInt);

	}

/* ____________________________________________________________________________________________________________________________________ */
// Select

	public static void select(String locatorKey, String value)
			throws IOException, AddressException, MessagingException {
		if (locatorKey.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
		}
		else if (locatorKey.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			dropdown = driver.findElement(By.id(locatorKey));
		}
		
		utilities.jsExecutor.drawBorder(dropdown, driver);
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		log.info("Typing in an Element : " + locatorKey + " entered the value as : " + value);

	}
	
	
	public static void selectIndex(String locatorKey, String indexNum)
			throws IOException, AddressException, MessagingException {
	
		double doub = Double.parseDouble(indexNum);
		int intValue = (int) doub;
		if (locatorKey.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
		}
		else if (locatorKey.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			dropdown = driver.findElement(By.id(locatorKey));
		}
		
		utilities.jsExecutor.drawBorder(dropdown, driver);
		Select select = new Select(dropdown);
		select.selectByIndex(intValue);

		log.info("Typing in an Element : " + locatorKey + " entered the value as : " + intValue);

	}
	
	
	
	

/* _________________________________________________________________________________________________________________________________________ */
// isElementPresent

	public static boolean isElementPresent(String locatorKey) {

		try {

			if (locatorKey.endsWith("_XPATH")) {

				driver.findElement(By.xpath(OR.getProperty(locatorKey)));

			}

			else if (locatorKey.endsWith("_CSS")) {

				driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));

			} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {

				driver.findElement(By.id(locatorKey));

			}

			log.info("Finding the Element : " + locatorKey);
			return true;
		} catch (Throwable t) {

			log.info("Error while finding an element : " + locatorKey + " exception message is : " + t.getMessage());
			return false;

		}

	}

/* ________________________________________________________________________________________________________________________________________ */	
// Quit

	public void tearDown() {

		driver.quit();
		log.info("Test Execution Completed !!!");

	}
	
/*__________________________________________________________________________________________________________________________________________*/
// Email	
	
	@AfterSuite
	public static void sendMail() throws AddressException, MessagingException {

		log.info("Sending Mail...");
		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody,
				TestConfig.attachmentPath, TestConfig.attachmentName);
		log.info("Test Report has emailed!!!");

	}

}
