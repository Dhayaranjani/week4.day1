package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign2_Frames {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//1. Load the URL https://chercher.tech/practice/frames-example-selenium-webdriver
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Enter into the Frame1
		driver.switchTo().frame("frame1");
		System.out.println("1. Title:" + driver.getTitle() + "\n");
		
		//3. Enter the Input for the Topic  
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Selenium Frames");
		Thread.sleep(5000);
		
		//4. Click on the checkbox inside the frame
		driver.switchTo().frame("frame3");
		//driver.findElement(By.id("a")).click();
		//boolean selected = driver.findElement(By.id("a")).isSelected();
		driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']/following-sibling::input")).click();
		Thread.sleep(5000);
		
		//5. Print it if is selected
		boolean selected = driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']/following-sibling::input")).isSelected();
		if(selected==true) { 
			System.out.println("2. Inner Frame Check box is Selected" + "\n");	
		}else
		{
			System.out.println("2. Inner Frame Check box is Not Selected" + "\n");
		}
		
		//6. Now come out of frames
		driver.switchTo().defaultContent();
		System.out.println("3. Came out of all the frames" + "\n");
		
		//7. Now Switch to Frame2 where the dropdown is present
		driver.switchTo().frame("frame2");
		//Identify the dropdrown element (Select tag)
		WebElement eleSelect = driver.findElement(By.id("animals"));
		//pass the element to the constructor
		Select ddAnimals = new Select(eleSelect);
		ddAnimals.selectByVisibleText("Big Baby Cat");
		
		//String selText = driver.findElement(By.id("animals")).getText();
		System.out.println("4. The Dropdown Selection is : Big Baby Cat");
		Thread.sleep(5000);
		driver.close();
	}

}
