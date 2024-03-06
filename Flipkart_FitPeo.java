package December;
import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Flipkart_FitPeo {
	public static void main(String[] args) throws InterruptedException {
		// Set the path to chromedriver.exe (Make sure you have chromedriver installed and in your PATH)
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\eclipse-workspace\\Selenium_testng practice\\Drivers\\chromedriver.exe");

		// Initialize Chrome WebDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);

		// Open Flipkart website
		driver.get("https://www.flipkart.com");

		// Verify that the homepage loads successfully
		assert driver.getTitle().contains("Flipkart");

		// Search for "laptop" and press Enter
		WebElement search_bar = driver.findElement(By.xpath("//input[@name='q']"));
		search_bar.sendKeys("laptop");
		search_bar.sendKeys(Keys.ENTER);

		// Wait for search results to load and click on the first product
		Thread.sleep(4000);


		// WebElement first_product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_1AtVbE")));
		WebElement laptop= driver.findElement(By.xpath("//div[text()='CHUWI Intel Celeron Dual Core 11th Gen N4020 - (8 GB/256 GB SSD/Windows 11 Home) HeroBook Plus Laptop']"));
		//check the details of laptop specifically
		laptop.click();
            	//Getting laptop element text	
		String Text = laptop.getText();
		//Switching windows to go to another window
		Set<String> windowHandles = driver.getWindowHandles();

		//Iterate through each window handle
		for (String handle : windowHandles) {
			// Switch to the window
			driver.switchTo().window(handle);
		}
		// Add the selected laptop to the shopping cart
		Thread.sleep(2000);
		//Pincode adding for addcart enabling
		WebElement pincode=driver.findElement(By.id("pincodeInputId"));
		pincode.sendKeys("500081");
		pincode.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//Clicking on Gotcartbutton to add the laptop into cart
		WebElement Gotocartbutton = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
		Gotocartbutton.click();
		
		// Text to be checked for the laptop added in the cart and inititial laptop selection comparision
		String expectedText = "CHUWI Intel Celeron Dual Core 11th Gen N4020 - (8 GB/25";

		// Check if the expected laptop is present page or not
		if (Text.contains(expectedText)) 
		{
			System.out.println("laptop '" + expectedText + "' is present in the cart.");
		} 
		else 
		{
			System.out.println("laptop '" + expectedText + "' is not present in the cart.");
		}

		Thread.sleep(3000);

		// Click on the "Proceed to Checkout" button
		WebElement placeorder = driver.findElement(By.xpath("//span[text()='Place Order']"));
		placeorder.click();

		
		// Assuming there is a login page, replace 'username' and 'password' with valid credentials
		//In Flipkart Login function works by entering Dynamica OTP received by the mobile number

		driver.navigate().refresh();
		Thread.sleep(2000);
		WebElement username = driver.findElement(By.xpath("//input[@type='text']"));
		//Entering mobile Number
		//NOTE:FLIPKART ALLOWS THIS OTP BASED AUTHENTICATION AND IT DOESNT SEND OTPs if we USE AUTOMATION TOOLS
		//FROM HERE THE CODE WILL NOT WORK DUE OTP RESTRICTION OF OTPs
		username.sendKeys("8142190877");
		WebElement continuebtn = driver.findElement(By.xpath("//span[normalize-space()='CONTINUE']"));
		continuebtn.click();
		Thread.sleep(3000);
		WebElement Otpfield=driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh _17N0em']"));
		Thread.sleep(5000);
		WebElement Login=driver.findElement(By.xpath("//span[normalize-space()='Login']"));
		Login.click();

		WebElement verify=driver.findElement(By.xpath("//span[@class='npYOZI']")) ;
		String Text2=verify.getText();
		// Text to be checked
		String expectedText2 = "Praveen Kumar Bugatha";
		//verifying login success or not
		assertEquals(Text2, expectedText2);


		// Verify that the user is successfully logged in
		//  wait.until(ExpectedConditions.urlContains("checkout"));

		// Enter shipping information
		//Adding name,mobile,pincode and addresses in the fields
		WebElement name = driver.findElement(By.name("name"));
		name.sendKeys("praveen");
		WebElement mobile = driver.findElement(By.name("phone"));
		mobile.sendKeys("8142190877");
		WebElement pin = driver.findElement(By.name("pincode"));
		pin.sendKeys("500081");
		WebElement locality = driver.findElement(By.name("addressLine2"));
		locality.sendKeys("vizag");
		WebElement address = driver.findElement(By.name("addressLine1"));
		locality.sendKeys("BJ PURAM,JIYYAMMVALASA\r\n"
				+ "VIZIANAGARAM");

		WebElement city = driver.findElement(By.name("city"));
		city.sendKeys("vizag");

		//dropdown for state selection
		WebElement dropdown=driver.findElement(By.xpath("//select[@name='state']"));
		Select state=new Select(dropdown);
		state.selectByValue("Andhra Pradesh");
		//clicking radio button for delivary type
		driver.findElement(By.xpath("//label[@for='HOME']//div[@class='_1XFPmK']")).click();

		// completion of adress and going to next step
		driver.findElement(By.xpath("//button[text()='Save and Deliver Here']")).click();


		//VERIFYING THE ORDER SUMMARY VALUES IN THE CART AND SUMMARY
		WebElement cartorrder=driver.findElement(By.xpath("//span[@class='_2-ut7f _1WpvJ7']"));
		String ordervalue=cartorrder.getText();
		Thread.sleep(3000);
		WebElement totalvalue=driver.findElement(By.xpath("//div[text()='₹45,990'])"));
		String summary= totalvalue.getText();

		assertEquals(ordervalue, summary);



		driver.findElement(By.xpath("//button[@class='_2KpZ6l _1seccl _3AWRsL']")).click();

		driver.findElement(By.xpath("//button[text()='Accept & Continue']")).click();
		Thread.sleep(3000);

		//Selecting payment method
		driver.findElement(By.xpath("//span[text()='Credit / Debit / ATM Card']")).click();

	}
}
