package selenium_maven_eclipse;



import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

//import de seleniums
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Random;

//permite definir una espera o delay mientras carga un elemento
import java.util.concurrent.TimeUnit;










//Librería para apoyarnos en los logs para presentar los resultados de las pruebas
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;

/**Librería del driver de Internet Explorer,
 * import org.openqa.selenium.ie.InternetExplorerDriver;
 * import org.openqa.selenium.remote.DesiredCapabilities;
 * import org.openqa.selenium.support.ui.ExpectedConditions;
 * import org.openqa.selenium.support.ui.WebDriverWait;*/
//imports para manejo de assert con alertas










//para los time stamp
import java.util.Date;
//

//correr los casos en orden alfabetico
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Setup{
	
     //declaracion del webdriver y la direccion de la pagina
      public static WebDriver driver;
      public static String baseUrl;
      public static WebDriverWait wait;
      //declaracion del string de errores y para el random     

      private boolean acceptNextAlert = true;
      
      //usuario nuevo
      String newemail = "jondoeqa@gmail.com";
      String passemail = "synergy1234";
  	  String passooflow = "clave1234";

      
      //captura de pantalla aca ponemos la ruta y la clase que estamos probando
      MultiScreenShot mShot=new MultiScreenShot(Tester.ruta,Tester.filetimestamp + File.separator);
  	
      // declaracion de los loggers  
      public static final Logger logger = Logger.getLogger("WebdriverTest");
       
  	/**
  	 * 	verifica texto manda mensajes al log y error al junit
  	 * @param que texto a verificar
  	 * @param donde donde se encuentra por xpath
  	 */
  	public void verificatxt(String que , By by ){
  	
      try {
          assertEquals(que, driver.findElement(by).getText());
        } catch (Error e) {
            //MANDANDO EL ERROR AL LOG y al junit
          logger.error("ERROR: "+e.toString());
          assertEquals(que, driver.findElement(by).getText());
        }
  	}
  	public void verificatxt(String que , String donde){
  	  	
        try {
            assertEquals(que, driver.findElement(By.xpath(donde)).getText());
          } catch (Error e) {
              //MANDANDO EL ERROR AL LOG y al junit
            logger.error("ERROR: "+e.toString());
            assertEquals(que, driver.findElement(By.xpath(donde)).getText());
          }
    	}
  	public void verificavalue(String que , String donde){
  	  	
        try {
            assertEquals(que, driver.findElement(By.xpath(donde)).getAttribute("value"));
          } catch (Error e) {
              //MANDANDO EL ERROR AL LOG y al junit
            logger.error("ERROR: "+e.toString());
            assertEquals(que, driver.findElement(By.xpath(donde)).getAttribute("value"));
          }
    	}
  	
  	public void verificavalue(String que , By by ){
  	  	
        try {
            assertEquals(que, driver.findElement(by).getAttribute("value"));
          } catch (Error e) {
              //MANDANDO EL ERROR AL LOG y al junit
            logger.error("ERROR: "+e.toString());
            assertEquals(que, driver.findElement(by).getAttribute("value"));
          }
    	}	
  	
  	public void estapresente (By by){
  
        try {
        	assertTrue(isElementPresent(by));
          } catch (Error e) {
            logger.error("ERROR: Elemento no esta presente"+e.toString());
            assertTrue(isElementPresent(by));
          }
  		
  
  
  	}
   	public void estanopresente(By by){
  
   	 try {
         assertFalse(isElementPresent(by));
       } catch (Error e) {
         logger.error("Elemento si esta presente"+e.toString());
         assertFalse(isElementPresent(by));
       }
  	  
  
  	}
 	public void control(String cualmetodo){
		MultiScreenShot.metodo = cualmetodo;
 		logger.info("Entro en el metodo "+cualmetodo);
		
		}
 	
	public void captura (String path) throws IOException{

		mShot.elementScreenShot(driver, driver.findElement(By.xpath(path)));
	
	}	
	public void captura (By by) throws IOException{

		mShot.elementScreenShot(driver, driver.findElement(by));
	
	}	
	public void captura () throws IOException{
		
	mShot.multiScreenShot(driver);
	
	
	}
//para saber si esta presente un elemento
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }
//para saber si esta presente un alerta
	  private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }
	//para saber usar los alertas
		  private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
	  

		  public void verificaurl(String url){
			  	
		      try {
		          assertEquals(url, driver.getCurrentUrl());
		        } catch (Error e) {
		            //MANDANDO EL ERROR AL LOG y al junit
		          logger.error("ERROR: "+e.toString());
		          assertEquals(url, driver.getCurrentUrl());
		        }
		  	}		  
		  
		  
		  
		  
		  //la anotación BeforeClass son las cosas que se ejecutan antes de la prueba
	      @BeforeClass
	      public static void setUp() throws Exception {          
	          //<<<<<LOGS>>>>>
	          //carga una configuracion basica que imprime por consola
	          BasicConfigurator.configure();          
	          //para los archivos de los logs
	          FileAppender filelog = new FileAppender( new PatternLayout("%r [%t] %p %c %x - %m%n"), "LogsTest.log");
	          FileAppender wfilelog = new FileAppender( new SimpleLayout(), "LogsWebdriver.log");
	          /**si quiero darle una ruta especifica 
	           * ejemplo: Logger.addAppender(new FileAppender(logDir + File.separator + getLogName()));
	           * para que los loggers definan en donde guardan*/          
	          Logger root = Logger.getRootLogger();         
	          root.addAppender(filelog);
	          logger.addAppender(wfilelog);          
	         //esto es para asignar un nivel por codigo
	            logger.setLevel(Level.INFO);
	           
	        /** si quiero correr la prueba en el IEXplorer
	         * System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
	         * DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
	         * 
	         * Para saltar las zonas de seguridad de internet explorer:
	         * capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	         * capab.setCapability("ignoreProtectedModeSettings", true);
	         * 
	         * Se instancia driver:
	         * driver = new InternetExplorerDriver(capab);
	         * baseUrl = "https://login.live.com/";
	         * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
	         
	            
           
	            
	            //System.setProperty("webdriver.chrome.driver", "."+File.separator+File.separator+"chromedriver.exe");
	           // WebDriver driver = new ChromeDriver();
         
	            
	            
	          //<<<<<WebDriver>>>>>
	          //default para FIREFOX  
	          driver = new FirefoxDriver();
	          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	  driver.get(Textos.baseUrl);
	    	  driver.manage().window().maximize();
	    	  logger.info("fin del setup");
	      
	     
	      
	      }//cierre del setup

	      
		  //@Test    
	      public void A0verificasetup()throws Exception{
	    	 control("A0verificasetup");
	    	 verificaurl(Textos.loginURL);
	    	 logger.info("Setup correcto");
	         
	        
	      }
	      
    

	      
	      
	    //@AfterClass
	      public static void tearDown() throws Exception {
	    	    driver.quit();

	    	  }
	    
	      
	      
	  
	      
	      
	      
	      
	    


	}//cierre de la clase
