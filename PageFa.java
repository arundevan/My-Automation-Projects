package pagefactorypackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.WiniumDriver;

public class PageFa {
	
	WiniumDriver objdriver;
	public PageFa(WiniumDriver driver)
	{
		this.objdriver=driver;
			
	}
	
	public void timelog_delete() throws AWTException
	{
		
		   
		      objdriver.findElement(By.name("Time Log")).click();
		      objdriver.findElement(By.name("Activity Row 3")).click();
			  Robot robot = new Robot();
			  
			 
	          robot.keyPress(KeyEvent.VK_CONTROL);
	          robot.keyPress(KeyEvent.VK_D);
			 
			  objdriver.findElement(By.name("No")).click();
			 
			  robot.keyPress(KeyEvent.VK_CONTROL);
			  robot.keyPress(KeyEvent.VK_D);
			  objdriver.findElement(By.name("Yes")).click();
		  
		  
		
	}

}
