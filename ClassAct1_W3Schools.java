package week4.day1;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*ClassRoom:
1. Launch https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt
2. Click on Try it
3. Enter your name and click on ok
4. verify your name*/
public class ClassAct1_W3Schools {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//1. Launch https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");
		driver.manage().window().maximize();
		//Switch to Frame
		driver.switchTo().frame("iframeResult");
		
		//2. Click on Try it
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		
		Alert alert = driver.switchTo().alert();
		//3. Enter your name and click on ok
		
		alert.sendKeys("DhayaRanjani");
		alert.accept();

		String myText = driver.findElement(By.id("demo")).getText();

 		//4. verify your name*/
		if(myText.contains("DhayaRanjani")){
			System.out.println("The Name is verified:");
			System.out.println("--------------------");
			System.out.println("Text is: "+ myText);
		}else
			System.out.println("The Name is not present");
		
		driver.close();
		
	}

}
