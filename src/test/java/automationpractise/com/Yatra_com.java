package automationpractise.com;

import java.io.FileInputStream;
//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import initial_configurations.Base_class;

public class Yatra_com extends Base_class
{

	@BeforeClass
	public void load_file() 
	{
		//opening properties file to read locators path and test data
		try 
		{
			FileInputStream file = new FileInputStream("./config_files/Yatra_testcases.properties");
			prop.load(file);
			System.out.println("file found");


		} 
		catch (Exception e) 
		{
			System.out.println("file not found");
		}

	}

	@BeforeMethod
	public void TC_01() throws InterruptedException 
	{
		//opening  web site
		driver.get(prop.getProperty("website_url"));

		//wait for the elements to load completely
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		Thread.sleep(6000);

	}


	//
	@Test(priority = 1)
	public void TC_02() throws InterruptedException 
	{
		//click on bus icon on menu bar
		driver.findElement(By.xpath(prop.getProperty("buses_icon_menu"))).click();
		Thread.sleep(2000);

		//click on calendar option
		driver.findElement(By.xpath(prop.getProperty("select_calendar"))).click();
		Thread.sleep(2000);

		//will select date from calendar
		driver.findElement(By.xpath(prop.getProperty("select_date"))).click();
		Thread.sleep(2000);

		//will click on search button
		driver.findElement(By.xpath(prop.getProperty("search_buses_btn"))).click();
		Thread.sleep(2000);

		//will click on select seat button
		driver.findElement(By.xpath(prop.getProperty("select_seat_btn"))).click();
		Thread.sleep(2000);


		//will choose seat of the bus
		driver.findElement(By.xpath(prop.getProperty("select_seat_pos"))).click();
		Thread.sleep(2000);

		//select boarding point
		driver.findElement(By.xpath(prop.getProperty("select_boarding_point"))).click();
		driver.findElement(By.xpath(prop.getProperty("select_boarding_point_value"))).click();

		//select dropping pint
		driver.findElement(By.xpath(prop.getProperty("select_dropping_point"))).click();
		driver.findElement(By.xpath(prop.getProperty("select_dropping_point_value"))).click();

		//click on confirm seats button
		driver.findElement(By.xpath(prop.getProperty("confirm_seats_btn"))).click();
		Thread.sleep(2000);

		//entering email
		driver.findElement(By.xpath(prop.getProperty("email_field"))).sendKeys("talaleem@gmail.com");
		Thread.sleep(2000);

		//entering number
		driver.findElement(By.xpath(prop.getProperty("number"))).sendKeys("talaleem@gmail.com");
		Thread.sleep(2000);

		//entering name
		driver.findElement(By.xpath(prop.getProperty("name"))).sendKeys("talha");
		Thread.sleep(2000);

		//selecting gender
		Select a = new Select(driver.findElement(By.xpath(prop.getProperty("gender"))));
		a.selectByVisibleText("Male");

		//select age
		Select b = new Select(driver.findElement(By.xpath(prop.getProperty("gender"))));
		b.selectByValue("25");


		//click on proceed to payment button
		driver.findElement(By.xpath(prop.getProperty("proceed_to_payment_btn"))).click();
		Thread.sleep(2000);






	}



































}
