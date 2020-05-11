package vanilascripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day12CarWale {

	public static void main(String[] args) throws InterruptedException {
		//Go to https://www.carwale.com/
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");   		 
		DesiredCapabilities capabilities = new DesiredCapabilities();  
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS); 
		options.merge(capabilities);  

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver(options);
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions builder=new Actions(driver);

		//mouse over on  Shop by Category 

		//Click on Used
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click();

		//Select the City as Chennai
		WebDriverWait wait=new WebDriverWait(driver,10); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class='used-cars-search']"))); 
		driver.findElementByXPath("//div[@class='used-cars-search']/input").click(); 
		driver.findElementByXPath("//input[@id='usedCarsList']").sendKeys("Chennai"); 
		Thread.sleep(1000); 
		driver.findElementByXPath("//input[@id='usedCarsList']").sendKeys(Keys.ENTER); 

		//Select budget min (8L) and max(12L) and Click Search
		JavascriptExecutor click =(JavascriptExecutor) driver; 
		click.executeScript("arguments[0].click();", driver.findElementByXPath("//span[text()='Choose your budget']")); 
		click.executeScript("arguments[0].click();", driver.findElementByXPath("//li[text()='8 Lakh']")); 

		//Select Cars with Photos under Only Show Cars With
		driver.findElementByXPath("//input[@id='placesQuery']").sendKeys("Chennai"); 
		driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click(); 

		driver.findElementByName("CarsWithPhotos").click(); 
		if(driver.findElementByXPath("//span[@id='filterbyadditional']/span[text()=' Cars with Photos ']").isDisplayed()) 
		{ 
			System.out.println("Cars with photos"); 
		} 


		//Select Manufacturer as "Hyundai" --> Creta
		click.executeScript("arguments[0].click();", driver.findElementByXPath("//li[@data-manufacture-en='Hyundai']")); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Creta']"))); 
		click.executeScript("arguments[0].click();", driver.findElementByXPath("//span[text()='Creta']")); 
		System.out.println("Hyundai creta model selected"); 

		//Select Fuel Type as Petrol
		click.executeScript("arguments[0].click();", driver.findElementByXPath("//h3[contains(text(),'Fuel Type')]")); 
		Thread.sleep(2000); 
		click.executeScript("arguments[0].click();", driver.findElementByXPath("//span[text()='Petrol']/parent::li")); 

		//Select Best Match as "KM: Low to High"
		driver.findElementById("sort").click(); 
		Select eleSort=new Select(driver.findElementById("sort")); 
		eleSort.selectByVisibleText("KM: Low to High"); 
		if(driver.findElementByXPath("//option[text()='KM: Low to High']").isSelected()) 
		{ 
			System.out.println("KM Low to High sort is selected"); 
		} 


		//Validate the Cars are listed with KMs Low to High
		List<WebElement> lst_ele_KMS = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']"); 
		List<Integer> lst_intKMS = getNumberFromList_Of_WebElements(lst_ele_KMS); 
		Collections.sort(lst_intKMS); 

		System.out.println("Sorted - "+lst_intKMS); 
		List<Integer> lst_intKMS_WithoutSort = getNumberFromList_Of_WebElements(lst_ele_KMS); 
		System.out.println("Without Sorted - "+lst_intKMS_WithoutSort); 
		if(lst_intKMS_WithoutSort.equals(lst_intKMS)==true) 
		{ 
			System.out.println("Cars KMS are sorted in a order"); 
		} 
		else 
		{ 
			System.out.println("Cars KMS are not sorted in a order"); 
		} 


		//Add the least KM ran car to Wishlist
		for(int i=0;i<lst_intKMS_WithoutSort.size();i++) 
		{ 
			if(lst_intKMS_WithoutSort.get(i).equals(lst_intKMS.get(0))) 
			{ 
				WebElement element = driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]"); 
				builder.moveToElement(element).perform(); 
				executor.executeScript("arguments[0].click();", element); 
				//action.moveToElement(driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]")).click().build().perform(); 
				Thread.sleep(2000); 
				//driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]").click(); 
				break; 
			} 
		} 

		//Go to Wishlist and Click on More Details
		driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']").click();
		driver.findElementByXPath("//a[text()='More details »']").click();

		//Print all the details under Overview
		Map<String,String> carDeets=new LinkedHashMap<String,String>(); 
		List<WebElement> eleDesc = driver.findElementsByXPath("//div[contains(@class,'overview-list')]//div[contains(@class,'text-light-grey')]"); 
		List<WebElement> eleValue = driver.findElementsByXPath("//div[contains(@class,'dark-text')]"); 
		for(int i=0;i<eleDesc.size();i++) 
		{ 
			String strDeet = eleDesc.get(i).getText(); 
			String strDeetValue = eleValue.get(i).getText(); 
			carDeets.put(strDeet, strDeetValue); 
		} 
		System.out.println("Overview details of "+carWithLeastKM+" are as follows:"); 
		for (Entry<String, String> eachDeet : carDeets.entrySet()) 
		{ 
			System.out.println(eachDeet.getKey()+" -> "+eachDeet.getValue()); 
		} 

		//Close the browser.
		driver.quit();

	}

}
