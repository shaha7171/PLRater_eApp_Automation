package Scratch;

import org.apache.log4j.chainsaw.Main;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;

import ErrorCollectors.ErrorCollector;

public class work {

	@Test
	public void testData() {
		//final ExtentReports extet = ExtentReports.get(Main.class);
		// In SoftAssert Interface code stops execution of remaining command after the failure in loop but it keeps executiong remaing loops. ANd 
		//Don't use softAssert in try-catch block else it won't show failure in the result 
		 try{
			 SoftAssert softAssert = new SoftAssert();
		 
		System.out.println("------------------------------------------------------------------------");
		String A = "B";
		String B = "A";
		System.out.println(A + " " + B);
		 softAssert.assertEquals(A, B);
		//Assert.assertEquals(A, B);
		System.out.println(A + " " + B);

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++===");
		 softAssert.assertAll();
	}catch(Throwable t){
		
	}

	}

	@Test
	public void tesData() {
		try {
			// SoftAssert softAssert1 = new SoftAssert();
			System.out.println("------------------------------------------------------------------------");
			String A = "B";
			String B = "A";
			System.out.println(A + " " + B);
			// softAssert1.assertEquals(A, B);
			Assert.assertEquals(A, B);
			System.out.println(A + " " + B);
			// softAssert1.assertAll();

			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++===");
		} catch (Throwable t) {
			ErrorCollector.addVerificationFailure(t);
		}

	}

}
