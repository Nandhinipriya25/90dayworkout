package vanilascripts;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day8Pepperfry {

	public static void main(String[] args) throws IOException {

		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");   		 
		DesiredCapabilities capabilities = new DesiredCapabilities();  
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
		options.merge(capabilities);  

		//Go to https://www.pepperfry.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://www.pepperfry.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 30);

		//Mouseover on Furniture and click Office Chairs under Chairs
		driver.findElementByClassName("wewidgeticon we_close icon-large").click();
		WebElement selFur = driver.findElementByXPath("(//a[@class='level-top'])[1]");
		Actions builder=new Actions(driver);
		builder.moveToElement(selFur).perform();
		driver.findElementByLinkText("Office Chairs").click();


		//click Executive Chairs
		driver.findElementByXPath("//img[@alt='Executive Chairs']").click();

		//Change the minimum Height as 50 in under Dimensions
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").sendKeys(Keys.BACK_SPACE,"50",Keys.ENTER);


		//Add "Poise Executive Chair in Black Colour" chair to Wishlist
		driver.findElementByXPath("(//a[@id='clip_wishlist_'])[4]").click();

		//Mouseover on Homeware and Click Pressure Cookers under Cookware
		builder.moveToElement(driver.findElementByXPath("(//a[@class='level-top'])[8]")).perform();
		driver.findElementByLinkText("Pressure Cookers").click();

		//Select Prestige as Brand
		driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();

		//Select Capacity as 1-3 Ltr
		driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();

		//Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		driver.findElementByXPath("(//a[@id='clip_wishlist_'])[3]").click();

		//Verify the number of items in Wishlist
		String count = driver.findElementByXPath("//a[contains(@class,'pf-icon pf-icon-heart')]").getText();
		int wishlistItems=Integer.parseInt(count);
		if(wishlistItems==2) {
			System.out.println("Count has been matches");
		}
		else {
			System.out.println("Count has not been matched");
		}


		//Navigate to Wishlist
		driver.findElementByXPath("//a[contains(@class,'pf-icon pf-icon-heart')]").click();

		//Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("(//a[@class='addtocart_icon'])[2]").click();

		//Check for the availability for Pincode 600128
		driver.findElement(By.xpath("//div[@class='tabs']//a[contains(text(),'My Cart')]")).click(); 
		driver.findElement(By.xpath("//input[@class='srvc_pin_text']")).sendKeys("600073"); 
		driver.findElement(By.xpath("//a[text()='Check']")).click(); 

		//Click Proceed to Pay Securely 15 Click Proceed to Pay
		driver.findElement(By.xpath("(//a[text()='PLACE ORDER'])[1]")).click();
		driver.findElement(By.xpath("//span[text()='ORDER SUMMARY']"));

		//Capture the screenshot of the item under Order Item
		WebElement screenShotEle = driver.findElement(By.xpath("//div[@class='slick-track']//li")); 
		File src = screenShotEle.getScreenshotAs(OutputType.FILE); 
		File dest = new File("./screenshot/img.png"); 
		FileUtils.copyFile(src, dest); 

		//Close the browser
		driver.close(); 
	}

}
