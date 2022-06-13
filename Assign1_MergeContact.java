package week4.day1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
/*Assignment 1:Window Handling:
 *=============================
 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
 * 2. Enter UserName and Password Using Id Locator
 * 3. Click on Login Button using Class Locator
 * 4. Click on CRM/SFA Link
 * 5. Click on contacts Button
 * 6. Click on Merge Contacts using Xpath Locator
 * 7. Click on Widget of From Contact
 * 8. Click on First Resulting Contact
 * 9. Click on Widget of To Contact
 * 10. Click on Second Resulting Contact
 * 11. Click on Merge button using Xpath Locator
 * 12. Accept the Alert
 * 13. Verify the title of the page*/
public class Assign1_MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		//To disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		//1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		//driver.manage().window().maximize();
		
		//2. Enter UserName and Password Using Id Locator
		//Identify WebElement
		WebElement eleUserName = driver.findElement(By.id("username"));
		eleUserName.sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		//3. Click on Login Button using Class Locator 
		driver.findElement(By.className("decorativeSubmit")).click();
		
		//4. Click on CRM/SFA Link		
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//5. Click on contacts Button		
		driver.findElement(By.linkText("Contacts")).click();
		
		//6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		
		//7. Click on Widget of From Contact
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']/following-sibling::a")).click();
		
		//Now We need to switch to child window. So get the number of windows 
		Set<String> Setwindow1 = driver.getWindowHandles();
		//Now convert Set to List
		List<String> myWinFromList = new ArrayList<String>(Setwindow1);
		//Get the Parent window
		String myParentWindow = myWinFromList.get(0);
		//Get the Child window		
		String myFromWindow = myWinFromList.get(1);
		
		//Now switch to child window
		driver.switchTo().window(myFromWindow);
		//System.out.println(driver.getTitle());
		Thread.sleep(5000);
		
		//8. Click on First Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[1]")).click();

		//Switch back to Parent window
		driver.switchTo().window(myParentWindow);
		//System.out.println(driver.getTitle());
		
		Thread.sleep(3000);
		//9. Click on Widget of To Contact
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdTo']/following-sibling::a")).click();

		//Now We need to switch to child window. So get the number of windows  
		Set<String> Setwindow2 = driver.getWindowHandles();
		//Now convert Set to List
		List<String> myWinToList = new ArrayList<String>(Setwindow2);
		//Get the child window
		String myToWindow = myWinToList.get(1);
		
		//Now switch to child window
		driver.switchTo().window(myToWindow);
		//System.out.println(driver.getTitle());
		
		Thread.sleep(5000);
		//10. Click on Second Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[2]")).click();

		//Switch back to Parent window
		driver.switchTo().window(myParentWindow);
		//System.out.println(driver.getTitle());

		Thread.sleep(5000);		
		//11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		//Switch to Alert
		Alert alert = driver.switchTo().alert();
		//12. Accept the Alert
		alert.accept();
		Thread.sleep(5000);
		
		//Switch back to Parent window
		driver.switchTo().window(myParentWindow);
		//System.out.println(driver.getTitle());
		
		//13. Verify the title of the page*/
		if(driver.getTitle().contains("View Contact | opentaps CRM")){
			System.out.println("The title of the page is verified ");
			System.out.println("----------------------------------");
			System.out.println("Title : " + driver.getTitle());
		}else {
			System.out.println("The title is null");
		} 
		
	}

}
