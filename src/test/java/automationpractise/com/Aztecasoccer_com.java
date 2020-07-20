package automationpractise.com;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
//import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import initial_configurations.Base_class;

public class Aztecasoccer_com  extends Base_class
{
	@BeforeClass
	public void load_file() 
	{
		//opening properties file to read locators path and test data
		try 
		{
			FileInputStream file = new FileInputStream("./config_files/test_cases.properties");
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

	//Check that the products displayed are related to what was searched for.	
	@Test(priority = 1)
	@Ignore
	public void TC_02() throws InterruptedException 
	{
		//closing the pop up
		driver.findElement(By.xpath(prop.getProperty("cross_icon"))).click();
		Thread.sleep(2000);


		//clicking on the search icon
		driver.findElement(By.xpath(prop.getProperty("search_icon"))).click();
		Thread.sleep(2000);


		//entering keyword in search bar
		driver.findElement(By.xpath(prop.getProperty("search_bar"))).sendKeys(prop.getProperty("keyword"));
		Thread.sleep(2000);

		//pressing enter
		driver.findElement(By.xpath(prop.getProperty("search_bar"))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		//It will get all the products of the page and store it in the elements variable
		List<WebElement> elements = driver.findElements(By.xpath(prop.getProperty("all_products")));

		//run loop with the size of total products visible on the page
		for (int i=1;i <= elements.size();i++)
		{


			//will click on details button of product
			driver.findElement(By.xpath("//*[@id=\"bc-sf-filter-products\"]/div["+ i +"]/div/a/div[2]")).click();
			Thread.sleep(2000); 

			//getting product title and storing it in text variable
			String text =driver.findElement(By.xpath(prop.getProperty("product_title"))).getText();


			//checking if the product details contain vitamin text 
			if(text.contains("adidas") && text.contains("Shoes") ) 
			{
				System.out.println("test case passed: correct product searched");
			}
			else
			{
				System.out.println("test case failed: wrong product searched");
			}

			driver.navigate().back();
			Thread.sleep(5000);
		}




	}




	//verify that price sorting option working correctly

	@Test(priority = 2)
	
	public void TC_03() throws InterruptedException 
	{
		//closing the pop up
		driver.findElement(By.xpath(prop.getProperty("cross_icon"))).click();
		Thread.sleep(2000);
		
		//clicking on the search icon
		driver.findElement(By.xpath(prop.getProperty("search_icon"))).click();
		Thread.sleep(2000);


		//entering keyword in search bar
		driver.findElement(By.xpath(prop.getProperty("search_bar"))).sendKeys(prop.getProperty("keyword"));
		Thread.sleep(2000);

		//pressing enter
		driver.findElement(By.xpath(prop.getProperty("search_bar"))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		//will click on price (under $50 option)
		driver.findElement(By.xpath(prop.getProperty("price_checkbox"))).click();


		//wait for the products to be load on page
		Thread.sleep(4000);

		//create an LinkedList 

		List<WebElement> products_Webelement = new LinkedList<WebElement>();
		
		
		
		//store the products (web elements) into the linked_list

		products_Webelement = driver.findElements(By.xpath(prop.getProperty("all_products_price")));
		

		//create another linked list of type string to store product price

		LinkedList<String> product_names =  new LinkedList<String>();

		//loop through all the elements of the product_webelement list get it title and store it into the product_names list

		for(int i=1;i<=products_Webelement.size();i++){

			WebElement ele =driver.findElement(By.xpath("//*[@id='bc-sf-filter-products']/div["+ i +"]/div/a/div[2]"));
			
			//Create object 'action' of an Actions class
			Actions action = new Actions(driver);
			
			//Mouse hover on an element
			action.moveToElement(ele).perform();
			
			String s = products_Webelement.get(i).getText();
			
			//after getting price it will only extract the digits from the price text
			String nums = s.replaceAll("[^0-9 ]", "").replaceAll(" +", " ").trim();
			String b=nums.substring(0,2);
			System.out.println(b);
			
			
			//System.out.println(s);
			product_names.add(b);

		}

		//send the list to chkalphabetical_order method to check if the elements in the list are in alphabetical order    

		boolean result = chkalphabetical_order(product_names);


		//print the result    
		System.out.println(result);
	}




	public static boolean chkalphabetical_order(LinkedList<String> product_names){


		for (final String current: product_names) {
		
			int i = Integer.parseInt(current); 
			
			if (i > 50)
				return false;

		}

		return true;

	}













}
