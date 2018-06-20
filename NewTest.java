import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class NewTest {
	WebDriver driver;
	String mainWindow;
	String expectedWebpageUrl;
	WebElement element;
 
  @Test
  
  public void beforeClass() 
  {
	 System.setProperty("webdriver.chrome.driver", "C:\\My_java_prog\\chromedriver_win32\\chromedriver.exe");
      driver = new ChromeDriver();
      driver.get("http://10.0.1.86//tatoc");
      mainWindow = driver.getWindowHandle();
  }
  @Test ( dependsOnMethods = {"beforeClass"})
  public void basicCourse()
  {
	  Assert.assertEquals(driver.findElement(By.linkText("Basic Course")).isDisplayed(), true);
	  driver.findElement(By.linkText("Basic Course")).click();
	  
  }
  
  @Test ( dependsOnMethods = {"basicCourse"})
  public void greenBox()
  {
	  Assert.assertEquals(this.driver.findElement(By.className("greenbox")).isDisplayed(),true);
	  driver.findElement(By.className("greenbox")).click();
	  expectedWebpageUrl = "http://10.0.1.86/tatoc/basic/frame/dungeon";
	  Assert.assertEquals(expectedWebpageUrl, driver.getCurrentUrl(), "Not as expected webpage");
	  
   }
  @Test( dependsOnMethods = {"greenBox"})
  public void rePaint()
  {
	  driver.switchTo().frame(0);
	  String box1 = driver.findElement(By.id("answer")).getAttribute("class");
		System.out.println("box1");
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
	    expectedWebpageUrl="http://10.0.1.86/tatoc/basic/drag";
	    Assert.assertEquals(expectedWebpageUrl,driver.getCurrentUrl(),"Not As expected webpage URL");
		
    }
   @Test ( dependsOnMethods = {"rePaint"})
    public void dropBox1()
   {
	 Actions action=new Actions(driver);
	 WebElement drag=driver.findElement(By.id("dragbox"));
	 WebElement drop=driver.findElement(By.id("dropbox"));
	 (new Actions(driver)).dragAndDrop(drag, drop).perform();
	 driver.findElement(By.cssSelector("a")).click();
	 expectedWebpageUrl="http://10.0.1.86/tatoc/basic/windows";
	 Assert.assertEquals(expectedWebpageUrl,driver.getCurrentUrl(),"Not as expected webpage url");
	}
  
  /* @Test(dependsOnMethods = {"dropBox1"})
   public void launchPopUp() {
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
	    driver.findElement(By.id("name")).sendKeys("Alok");
	    driver.findElement(By.id("submit")).click();
	    
	    driver.switchTo().window(mainWindow);
	    driver.findElements(By.cssSelector("a")).get(1).click();
	    
	    expectedWebpageUrl = "http://10.0.1.86/tatoc/basic/cookie"; //url of next page
       Assert.assertEquals(expectedWebpageUrl, driver.getCurrentUrl(), "Not as expected webpage");

   }*/
	
   @Test //(dependsOnMethods = {"launchPopUp"})
   public void zookieAdd() {
	
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
   expectedWebpageUrl = "http://10.0.1.86/tatoc/end"; //url of next page
   Assert.assertEquals(expectedWebpageUrl, driver.getCurrentUrl(), "Not as expected webpage");
	}
	
}
  



  
  

   
