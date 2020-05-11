package vanilascripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day11SnapDeal {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");   		 
		DesiredCapabilities capabilities = new DesiredCapabilities();  
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
		options.merge(capabilities);  

		//Go to https://www.snapdeal.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions builder=new Actions(driver);
		WebDriverWait wait =new WebDriverWait(driver,30);


		// Mouse over on Toys, Kids' Fashion & more and click on Toys
		builder.moveToElement(driver.findElementByXPath("//div[@id='leftNavMenuRevamp']/div[1]/div[2]/ul[1]/li[9]/a[1]/span[1]")).perform();
		builder.click(driver.findElementByXPath("//span[text()='Toys']")).perform();

		//Click Educational Toys in Toys & Games
		driver.findElementByXPath("(//i[contains(@class,'sd-icon sd-icon-like-outline')])[1]").click();

		//Click the Customer Rating 4 star and Up
		driver.findElementByXPath("//label[@for='avgRating-4.0']").click();

		//Click the offer as 40-50
		driver.findElementByXPath("//label[@for='discount-40%20-%2050']").click();

		//Check the availability for the pincode
		driver.findElementByXPath("(//input[@class='sd-input'])[2]").sendKeys("600119");
		driver.findElementByClassName("pincode-check").click();
		Thread.sleep(2000);

		//Click the Quick View of the first product
		driver.findElementByXPath("(//div[contains(@class,'center quick-view-bar')])[1]").click();

		//Click on View Details
		driver.findElementByLinkText("view details").click();

		//Capture the Price of the Product and Delivery Charge
		String prodPrice = driver.findElementByClassName("payBlkBig").getText();
		int price=Integer.parseInt(prodPrice.replaceAll("\\D", ""));
		System.out.println(price);
		String delCharge = driver.findElementByXPath("(//span[@class='availCharges'])[1]").getText();
		int deliverycharge=Integer.parseInt(delCharge.replaceAll("\\D", ""));
		System.out.println(deliverycharge);

		//Validate the You Pay amount matches the sum of (price+deliver charge)
		int totalAmt=price+deliverycharge;
		if(totalAmt==648) {
			System.out.println("Amount has been Matched");
		}
		else {
			System.out.println("Amount has been Mismatched");
		}

		//Search for Sanitizer
		driver.findElementById("inputValEnter").sendKeys("Sanitizer",Keys.ENTER);

		//Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		driver.findElementByXPath("(//img[@class='product-image '])[1]").click();
		Set<String> windowHandles = driver.getWindowHandles(); 
		List<String> lstwindowHandles = new ArrayList<String>(windowHandles); 
		int windowSize_AfterClicking = lstwindowHandles.size(); 
		driver.switchTo().window(lstwindowHandles.get(windowSize_AfterClicking-1)); 

		//Capture the Price and Delivery Charge
		String price1 = driver.findElementByXPath("//span[@itemprop='price']").getText(); 
		int priceSanitizer=Integer.parseInt(price1.replaceAll("\\D", ""));
		String del = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		int deliverycost=Integer.parseInt(del.replaceAll("\\D", ""));
		int totCostSanitize=priceSanitizer+deliverycost;
		int costOf2Items=totalAmt+totCostSanitize;

		//Click on Add to Cart
		driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();

		//Click on Cart
		driver.findElementByXPath("//i[@class='sd-icon sd-icon-cart-icon-white-2']").click();

		//Validate the Proceed to Pay matches the total amount of both the products
		String proceedToPay = driver.findElementByXPath("//input[@type='button']").getText();
		int payableAmt=Integer.parseInt(proceedToPay.replaceAll("\\D", ""));
		if(payableAmt==costOf2Items) {
			System.out.println("Amount has been matched");
		}
		else {
			System.out.println("Amount has been mismatched ");
		}
		//Close all the windows
		driver.quit();
	}

}
