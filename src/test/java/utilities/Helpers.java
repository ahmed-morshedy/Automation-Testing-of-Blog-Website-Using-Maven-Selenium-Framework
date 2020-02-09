package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helpers {
	
	//Method to take screenshot when test case fail
	public static void captureScreenshot(WebDriver driver,String screenshotname)
	{
		Path des=Paths.get("./Screenshots", screenshotname+".png");
		try {
			Files.createDirectories(des.getParent());
			FileOutputStream out=new FileOutputStream(des.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot"+e.getMessage());	
		}		
	}

}

 