package Scratch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class testScreenshot extends BaseTest{
	
	@BeforeMethod
	public void beforeTest(){
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("https://www.gmail.com");
	}
	
	@AfterMethod
	public void AfterTest(){
		tearDown();
	}
	

	
	@Test
	public void clickNext(){
/*		driver.findElement(By.xpath("//*[@id='identifierId']")).click();
		WebElement Nxt = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/span/span"));
		utilities.jsExecutor.drawBorder(Nxt, driver);
		Nxt.click();*/
		
		click("gmailTxt_XPATH");
		click("nxt_XPATH");
		String error = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div")).getText();
		Assert.assertTrue(error.contains("ABC"),"Warning is wrong...");
	}
	

}
