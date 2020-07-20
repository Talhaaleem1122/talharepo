package automationpractise.com;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import initial_configurations.Base_class;

public class Homepage extends Base_class
{

	@BeforeClass
	public void load_file() 
	{
		//opening properties file to read locators path and test data
		try 
		{
			FileInputStream file = new FileInputStream("./config_files/testcases.properties");
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


	//Verify that searched products are according to the search keyword or not
	@Test(priority = 1)
	public void TC_02() throws InterruptedException 
	{
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
			driver.findElement(By.xpath("//*[@id=\"product-listing-container\"]/form[2]/ul/li["+ i +"]/article/div/div[1]/a[1]")).click();
			Thread.sleep(2000); 

			//getting product details and storing it in text variable
			String text =driver.findElement(By.xpath(prop.getProperty("product_details"))).getText();


			//checking if the product details contain vitamin text 
			if(text.contains("vitamin")) 
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



	//verify that products are sorted alphabetically or not

	@Test(priority = 2)
	public void TC_03() throws InterruptedException 
	{
		//clicking on the search icon
		driver.findElement(By.xpath(prop.getProperty("search_icon"))).click();
		Thread.sleep(2000);


		//entering keyword in search bar
		driver.findElement(By.xpath(prop.getProperty("search_bar"))).sendKeys(prop.getProperty("keyword"));
		Thread.sleep(2000);

		//pressing enter
		driver.findElement(By.xpath(prop.getProperty("search_bar"))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		//driver.findElement(By.xpath(prop.getProperty("sort_by"))).click();

		Select sortyby = new Select(driver.findElement(By.xpath(prop.getProperty("sort_by"))));
		sortyby.selectByVisibleText("A to Z");

		//wait for the products to be sorted on page
		Thread.sleep(5000);

		//create an LinkedList instead of array_list because it preserves insertion order

		List<WebElement> products_Webelement = new LinkedList<WebElement>();

		//store the products (web elements) into the linked_list

		products_Webelement = driver.findElements(By.xpath(prop.getProperty("all_products1")));

		//create another linked list of type string to store product title

		LinkedList<String> product_names =  new LinkedList<String>();

		//loop through all the elements of the product_webelement list get it title and store it into the product_names list

		for(int i=0;i<products_Webelement.size();i++){

			String s = products_Webelement.get(i).getText();
			//System.out.println(s);
			product_names.add(s);

		}

		//send the list to chkalphabetical_order method to check if the elements in the list are in alphabetical order    

		boolean result = chkalphabetical_order(product_names);


		//print the result    
		System.out.println(result);
	}




	public static boolean chkalphabetical_order(LinkedList<String> product_names){

		String previous = ""; // empty string

		for (final String current: product_names) {
			//The Java String compareTo() method is used for comparing two strings lexicographically. 
			//Each character of both the strings is converted into a Unicode value for comparison. 
			//If both the strings are equal then this method returns 0 else it returns positive or 
			//negative value. The result is positive if the first string is lexicographically greater 
			//than the second string else the result would be negative

			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}

		return true;

	}

	//verify that products are being added to the cart or not
	@Test(priority = 3)
	@Ignore
	public void TC_04() throws InterruptedException 
	{
		//click on products option in menu_bar
		driver.findElement(By.xpath(prop.getProperty("products_menu"))).click();
		
		//will click on vitamins
		driver.findElement(By.xpath(prop.getProperty("vitamins_option"))).click();
		Thread.sleep(2000);
		
		//will click on purchase btn
		driver.findElement(By.xpath(prop.getProperty("purchase_btn"))).click();
		Thread.sleep(2000);
		
		
		
		//will get the text of product and stored in actual_res
		//String actual_res = driver.findElement(By.xpath(prop.getProperty("Add_to_cart_btn"))).getText();
		
		//will click on add_tocart_btn
		driver.findElement(By.xpath(prop.getProperty("Add_to_cart_btn"))).click();
		
		
		
		
		

	}







}
