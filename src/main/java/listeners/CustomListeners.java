package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			utilities.TestUtil.captureScreenshot();
			//TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		Reporter.log("<a target=\"_blank\" href="+TestUtil.fileName+">Screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.fileName+"><img src="+TestUtil.fileName+" height=200 width=200></a>");
		
		*/
		
		/*// This path is printing HTML reports with the screenshots but doesn't work for emailable report 
		Reporter.log("<a target=\"_blank\" href="+TestUtil.fileName+">Screenshot Link</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.fileName+"><img src="+TestUtil.fileName+" height=200 width=200></a>");*/
	
		//This program is displaying both
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\vishal.shaha\\workspace\\PLRating_eApp_Version4\\test-output\\html\\"+utilities.TestUtil.fileName+"\">Screenshot Link</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\vishal.shaha\\workspace\\PLRating_eApp_Version4\\test-output\\html\\"+utilities.TestUtil.fileName+"\"><img src=\"C:\\Users\\vishal.shaha\\workspace\\PLRating_eApp_Version4\\test-output\\html\\"+utilities.TestUtil.fileName+"\" height=200 width=200></a>");

	
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("Test case skipped"+" Test case name : "+ arg0.getName());
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		
		System.out.println("Test case pass"+" Test case name : "+ arg0.getName());

	
	}

}
