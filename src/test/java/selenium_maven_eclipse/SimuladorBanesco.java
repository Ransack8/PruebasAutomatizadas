package selenium_maven_eclipse;

 
	import static org.junit.Assert.fail;
	import java.io.File;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.util.concurrent.TimeUnit;
	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.remote.CapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import io.appium.java_client.AppiumDriver;
	//import io.appium.java_client.android.AndroidDriver;


	public class SimuladorBanesco {
		WebDriver driver;
		
		@Test
		  public void setUp() throws MalformedURLException, InterruptedException {
	        // set up las capabilities del appium
	    	
			String apkpath = "C:\\Android\\banesco-movil-integrado-android-produccion.apk";
			File app = new File(apkpath);	
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability ("deviceName", "Primersimulador");
			capabilities.setCapability ("platformVersion", "4.3.1");
	        capabilities.setCapability ("app", app.getAbsolutePath());
	        capabilities.setCapability("appium-version", "1.1");
	    	//capabilities.setCapability ("appPackage", "selenium_maven_eclipse");
	    	//capabilities.setCapability ("appActivity", "");  
	    	driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);     
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);       
	    }
		

		 /* @Test
		  public void testbanesco() throws Exception {
		    //driver.get(baseUrl + "/site/secciones/opinion/");
		    //driver.findElement(By.linkText("Internacionales")).click();
		    //driver.findElement(By.linkText("Portada")).click();
		  }
		  
		  
		  @After
		  public void tearDown() throws Exception {
		    driver.quit();
		  }
		*/
	
	}
	


