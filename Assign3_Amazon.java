package week4.day1;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign3_Amazon {
	/*Assignment 3: Amazon
	======================
	1. Load the URL https://www.amazon.in/
	2. Search as oneplus 9 pro 
	3. Get the price of the first product
	4. Print the number of customer ratings for the first displayed product
	5. Click the first text link of the first image
	6. Take a screen shot of the product displayed
	7. Click 'Add to Cart' button
	8. Get the cart subtotal and verify if it is correct.
	9. Close the browser */
	
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();

		//To disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		//1. Load the URL https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//2.Search as oneplus 9 pro 
		WebElement findElement = driver.findElement(By.id("twotabsearchtextbox"));
		findElement.sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//3. Get the price of the first product
		System.out.println("The Price of the First Product is:");
		System.out.println("----------------------------------");
		String FirstProduct = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Rs." + FirstProduct + "\n" );
		
		Thread.sleep(5000);
		//4. Print the number of customer ratings for the first displayed product
		String noOfcustRate = driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]")).getText();
		System.out.println("No of customers rated is: " + noOfcustRate + "\n");
		
		//5. Click the first text link of the first image
		driver.findElement(By.xpath("(//div[@id='search']//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		String firstText = driver.findElement(By.xpath("(//div[@id='search']//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).getText();
		System.out.println("First Text of the Image is :");
		System.out.println("----------------------------");
		System.out.println(firstText + "\n");
		
		Thread.sleep(5000);
		Set<String> setWindow = driver.getWindowHandles();
		List<String> lstWindow = new ArrayList<String>(setWindow);
		String ImgWindow = lstWindow.get(1);
		driver.switchTo().window(ImgWindow);
		
		//6. Take a screen shot of the product displayed
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./scrnShotImg.png");
		FileUtils.copyFile(source, destination);
		//Thread.sleep(5000);
		
		//7. Click 'Add to Cart' button
		//driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']/following-sibling::span")).click();
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(5000);
		
		//8. Get the cart subtotal and verify if it is correct.
		String cartTitle = driver.findElement(By.id("attach-accessory-cart-total-string")).getText();
		String cartSubTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		System.out.println(cartTitle + cartSubTotal + "\n");
		
		if(cartSubTotal.contains(FirstProduct)) {
			System.out.println("It is verified and correct" + "\n");
		}else
			System.out.println("It is verified and not correct" + "\n");
		
		//9. Close all the browser
		driver.quit();
	}

}
