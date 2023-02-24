package ppack;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

//  Extent report url : https://www.extentreports.com/docs/versions/3/java/#basic-example

public class DemoTest {

	WebDriver driver;
	ExtentReports extent;

	@BeforeMethod
	public void configuration() {
		
		String reportPath = System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		reporter.config().setReportName("Omayo Test Report");
		reporter.config().setDocumentTitle("Omayo Test Report Title");
		
		 extent = new ExtentReports(); 
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating System", "Windows 10");
		extent.setSystemInfo("Testing By", "Jeeva");
		

	}

	@Test
	public void testOne() {
		
		ExtentTest etest = extent.createTest("Test One Created");  // Starting of the report
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		etest.info("Chrome browser launched");
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://omayo.blogspot.com/");
		etest.info("Navigated to omayo home page");

		String actualText = driver.findElement(By.id("pah")).getText();

		Assert.assertEquals(actualText, "PracticeAutomationHere");

	}

	@AfterMethod
	public void tearDown() {

		driver.close();
		
		extent.flush();    //Ending of the report

	}

}
