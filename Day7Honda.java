package vanilascripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day7Honda {

	public static void main(String[] args) throws InterruptedException {

		//Go to https://www.honda2wheelersindia.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 30);

		//Click on scooters and click dio

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("link_Scooter"))); 
		driver.findElementById("link_Scooter").click(); 
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='owl-wrapper-outer']//a[contains(@href,'dio')]"))); 
		driver.findElementByXPath("//div[@class='owl-wrapper-outer']//a[contains(@href,'dio')]").click(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 


		//Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).perform();

		//Get Displacement value
		String strDispDyo = driver.findElementByXPath("//span[text()='109.51cc']").getText();
		System.out.println("Displacement value is" +" " +strDispDyo);
		double dbDispDyo = Double.parseDouble(strDispDyo.trim());

		//Go to Scooters and click Activa 125
		driver.findElementById("link_Scooter").click(); 
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-6g-icon.png']").click();

		//Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Actions builder1=new Actions(driver);
		builder1.moveToElement(driver.findElementByXPath("//a[text()='Engine']")).perform();

		//Get Displacement value
		String strDispAct = driver.findElementByXPath("//span[text()='109.51 cc']").getText();
		System.out.println("Displacement value is" +" " +strDispAct);
		double dbDispAct = Double.parseDouble(strDispAct.trim());

		//Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if(dbDispDyo>dbDispAct) 
		{ 
			System.out.println("Dio has better Displacement"); 
		} 
		else 
		{ 
			System.out.println("Activa125 has better Displacement"); 
		} 

		//Click FAQ from Menu
		driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();

		//Click Activa 125 BS-VI under Browse By Product
		driver.findElementByXPath("//a[text()='Activa 125 BS-VI']").click();

		//Click Vehicle Price
		driver.findElementByXPath("//a[@href='#6a']").click();

		//Make sure Activa 125 BS-VI selected and click submit
		driver.findElementById("submit1").click();

		//click the price link
		driver.findElementByXPath("//a[@href='https://www.honda2wheelersindia.com/activa125-BS-VI/price']").click();

		//Go to the new Window and select the state as Tamil Nadu and city as Chennai
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listWindows=new ArrayList<String>(windowHandles);
		driver.switchTo().window(listWindows.get(1));

		WebElement state = driver.findElementByXPath("(//select[@class='form-control'])[1]");
		Select selState=new Select(state);
		selState.selectByVisibleText("Tamil Nadu");

		WebElement city = driver.findElementByXPath("(//select[@class='form-control'])[2]");
		Select selcity=new Select(city);
		selcity.selectByVisibleText("Chennai");

		//Click Search
		driver.findElementByXPath("//button[text()='Search']").click();

		//Print all the 3 models and their prices
		List<WebElement> rows = driver.findElementsByXPath("//tbody[@style='background-color:white']//tr");

		for(int i = 0; i < rows.size(); i++) {
			WebElement eachRow = rows.get(i);
			List<WebElement> cols = eachRow.findElements(By.xpath("//tbody[@style='background-color:white']//tr["+(i+1)+"]//td"));
			if(cols.size()==3) {
				for(int j=0 ; j < cols.size()-1;j++) {
					System.out.print(driver.findElementByXPath("//tbody[@style='background-color:white']//tr["+(i+1)+"]//td["+(j+2)+"]").getText());
					System.out.print(" ");				
				}
			}
			else {
				for(int j=0 ; j < cols.size();j++) {
					System.out.print(driver.findElementByXPath("//tbody[@style='background-color:white']//tr["+(i+1)+"]//td["+(j+1)+"]").getText());
					System.out.print("\t");
				}
			}
			System.out.println();

			//Close the Browser
			driver.quit(); 
		}
		}

	}
