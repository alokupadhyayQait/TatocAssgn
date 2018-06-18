package firstSE;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
 public class Tatoc { 
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\My_java_prog\\chromedriver_win32\\chromedriver.exe");

	       WebDriver driver = new ChromeDriver();
	driver.get("http://10.0.1.86//tatoc");
	
	//main window for popup retrival
	String mainWindow = driver.getWindowHandle();	
		
	driver.findElement(By.linkText("Basic Course")).click();
	driver.findElement(By.className("greenbox")).click();
	
	
	driver.switchTo().frame(0);
	String box1 = driver.findElement(By.id("answer")).getAttribute("class");
	
	while(true){
	driver.findElement(By.cssSelector("a")).click();
	driver.switchTo().frame("child");
	String box2 = driver.findElement(By.id("answer")).getAttribute("class");
	driver.switchTo().parentFrame();
	if(box1.equals(box2))
	{
	driver.findElements(By.cssSelector("a")).get(1).click();
	break;
	}
	}
	
	//handling drag and drop
	
	WebElement element = driver.findElement(By.id("dragbox")); 

	WebElement target = driver.findElement(By.id("dropbox"));

	(new Actions(driver)).dragAndDrop(element, target).perform();
	driver.findElement(By.cssSelector("a")).click();
	
	driver.findElement(By.linkText("Launch Popup Window")).click();
	
	
	String SecWindow = null;
	 // getting other (ALL) windows
    Set<String> handles = driver.getWindowHandles();
    System.out.println(handles);
    
    Iterator<String> iterator = handles.iterator();
    while (iterator.hasNext()){
    	
    	SecWindow = iterator.next();
    }
 // switch to popup window
    driver.switchTo().window(SecWindow); 
    driver.findElement(By.id("name")).sendKeys("Aditya");
    driver.findElement(By.id("submit")).click();
    
    driver.switchTo().window(mainWindow);
    driver.findElements(By.cssSelector("a")).get(1).click();

	//driver.findElement(By.linkText("Proceed")).click();
	
    //generating token
    driver.findElement(By.cssSelector("a")).click();
    
    //String value = driver.findElement(By.id("token")).getText().split("Token: ")[1];    
    
    
    String Tokenvalue = driver.findElement(By.id("token")).getText();
     Tokenvalue = (Tokenvalue.substring(7));
    
    System.out.println(Tokenvalue);
    
    //adding cookie
    Cookie ck = new Cookie("Token", Tokenvalue);
    driver.manage().addCookie(ck);
    
	
    
    //driver.manage().addCookie( new Cookie("Token", Tokenvalue, "/")); 
    driver.findElements(By.cssSelector("a")).get(1).click(); 
	
	}
}



