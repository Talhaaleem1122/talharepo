package initial_configurations;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_class
{
	public static WebDriver driver;
	public static Properties prop = new Properties();

	@BeforeSuite
	public  void setup() throws InterruptedException 
	{
		//initializing web_driver manager for chrome browser
		WebDriverManager.chromedriver().version("83.0").setup();
		//logger =extent.startTest("Chrome Initialize");
		driver= new ChromeDriver();
		//maximizing chrome browser
		driver.manage().window().maximize();
	}
}
