package selenium_maven_eclipse;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;





//Librer�a para apoyarnos en los logs para presentar, los resultados de las pruebas*/
import org.apache.log4j.Logger;


public class AppiumTest {
	AppiumDriver driver;
	// Configurar el log donde se visualizaran los mensajes	  
	  
	@Test
	  public void setUp() throws MalformedURLException, InterruptedException {
        
		
	      
	    

		  		
		// set up las capabilities del appium

		String apkpath = "C:\\Android\\banesco-movil-integrado-android-produccion.apk";
		File app = new File(apkpath);	
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability ("deviceName", "Galaxy Nexus");
		capabilities.setCapability ("platformVersion", "4.3");
        capabilities.setCapability ("app", app.getAbsolutePath());
        capabilities.setCapability("appium-version", "1.1");
    	//capabilities.setCapability ("appPackage", "selenium_maven_eclipse");
    	//capabilities.setCapability ("appActivity", "");  
    	driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);     
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS); 
        
        
               
      driver.swipe(349, 925, 349, 600, 500);        
         
      driver.swipe(349, 925, 349, 600, 100);        
      driver.pinch(349, 925);        
      driver.swipe(349, 925, 349, 600, 500);
      driver.swipe(200, 900, 700, 200, 5000);
      driver.swipe(300, 500, 300, 300, 5000);
               
      driver.swipe(349, 925, 349, 600, 500);        
               
      driver.swipe(349, 925, 349, 600, 500);        
             
      driver.swipe(349, 925, 349, 600, 500);
      driver.swipe(200, 900, 700, 200, 5000);
      driver.swipe(300, 500, 300, 300, 100);
      driver.swipe(300, 500, 300, 300, 3000);
      
      //acepto
      driver.tap(1,520, 1120,100);
      
      
		WebdriverTest.alogger.info("entro en el setup del appium5454");
		WebdriverTest.alogger.debug("entro en el setup del appium ASAS2");
      
        
        
        
        
        
    }

	
	

     
    
	
    
		  void loginTest() throws InterruptedException {
		 System.out.println("acepto terminos");
		 WebElement element1= driver.findElement(By.xpath("android:id/button1"));
		 element1.click();
		 WebElement element2= driver.findElement(By.tagName("android:id/button1"));
		 element2.click();
		 WebElement element3= driver.findElement(By.tagName("Acepto"));
		 element3.click();
		 
	    //driver.get(baseUrl + "/site/secciones/opinion/");
	    //driver.findElement(By.linkText("Internacionales")).click();
	    //driver.findElement(By.linkText("Portada")).click();
	  }
		 
	  /*
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }
	*/
}




	
	
	


		
		
		
		


