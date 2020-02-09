package admintests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Helpers;

public class TestBase {
	public static WebDriver driver;
	
	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional ("chrome")String browserName)
	{
		if (browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("http://localhost/fci/Login.php");
	}
	@AfterSuite
	public void stopDriver()
	{
		driver.quit();
	}
	
	//take screenshot when test case fail and add it in the screenshot folder
	@AfterMethod
	public void screenshotOnFailure(ITestResult result)
	{
		if (result.getStatus()==ITestResult.FAILURE) 
		{
			System.out.println("failed");
			System.out.println("taking screenshot");
			Helpers.captureScreenshot(driver, result.getName());
			
		}
	}


}
