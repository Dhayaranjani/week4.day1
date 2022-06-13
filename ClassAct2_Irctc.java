package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
/*1. Launch https://www.irctc.co.in/nget/train-search
 *2. Accept the alert by clicking on ok
 *3. Click on Flights
 *4. Switch to new window4.
 *5. Print the Title
 *6. Close the current window*/
public class ClassAct2_Irctc {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		//To disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		//1. Launch https://www.irctc.co.in/nget/train-search
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		
		//2.Accept the alert by clicking on ok
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		
		//3. Click on Flights
		driver.findElement(By.linkText("FLIGHTS")).click();
		//Get the number of windows
		Set<String> window = driver.getWindowHandles();
		//Convert Set to List
		List<String> myWindow = new ArrayList<String>(window);
		
		//for (int i = 0; i < myWindow.size(); i++) {
		//	driver.switchTo().window(myWindow.get(i));
		//}
		//4. Switch to new window. Since we know the number is 1 we pass directly
		driver.switchTo().window(myWindow.get(1));
		
		//5. Print the Title
		System.out.println(driver.getTitle());
		
		//6.Close the current window alone
		driver.close();

	}

}
