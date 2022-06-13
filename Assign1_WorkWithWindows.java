package week4.day1;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign1_WorkWithWindows {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//1. Load the URL http://www.leafground.com/pages/Window.html
		driver.get("http://www.leafground.com/pages/Window.html");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//1. Click button to open home page in New Window
		driver.findElement(By.id("home")).click();

		//Get the number of windows
		Set<String> windowHandles = driver.getWindowHandles();
		//Convert Set to List  
		List<String> myListofWindows = new ArrayList<String>(windowHandles);
		
		String parentWindow = myListofWindows.get(0);
		String homePage = myListofWindows.get(1);
		driver.switchTo().window(homePage);
		String title = driver.switchTo().window(homePage).getTitle();
		//driver.manage().window().maximize();
		System.out.println("1. Home Page Opened in New Window" + "\n" + "Title :" + title + "\n" );
		driver.close();
		//Switch back to Parent Window
		driver.switchTo().window(parentWindow);

		//2. Find the number of opened windows
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> myListofWin1 = new ArrayList<String>(windowHandles1);
		
		int countOfOpenedWindows = myListofWin1.size();
		String myParentWin = myListofWin1.get(0);
		String childWin1 = myListofWin1.get(1);
		String childWin2 = myListofWin1.get(2);
		System.out.println("2. Number of opened windows :" + countOfOpenedWindows + "\n");	

		//3. Close all the Windows except the Parent window
		driver.switchTo().window(childWin1);
		System.out.println("3. Close all the Windows except the Parent window" + "\n");
		System.out.println("Switch to Child window1 : Title : " + driver.getTitle() + "\n");
		Thread.sleep(2000);
		driver.close();
		System.out.println("1st Child window Closed:" + "\n");
		
		driver.switchTo().window(childWin2);
		System.out.println("Switch to Child window2 : Title : " + driver.getTitle() + "\n");
		Thread.sleep(2000);
		driver.close();
		System.out.println("2nd Child window Closed:" + "\n");
	
		driver.switchTo().window(myParentWin);
		System.out.println("Now in Parent window: Title :" + "\n" + driver.getTitle()+ "\n");
		
		Thread.sleep(5000);
		//4. Wait for 2 new Windows to open
		System.out.println("4: Now wait for 5 seconds to open 2 New windows" + "\n");
		driver.findElement(By.id("color")).click();
		Thread.sleep(10000);
		
		//5.Close all the windows
		System.out.println("5: Now Close all the windows");
		Thread.sleep(5000);
		driver.quit();
	}

}
