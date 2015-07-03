package selenium_maven_eclipse;

import org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
//import de seleniums
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;
//permite definir una espera o delay mientras carga un elemento
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.openqa.selenium.support.ui.Select;









//para los time stamp
import java.util.Date;

//correr los casos en orden alfabetico
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Prore {

 	
    //declaracion del webdriver y la direccion de la pagina
      public static WebDriver driver;
      public static String baseUrl;
      public static WebDriverWait wait;
      //declaracion del string de errores y para el random     

      private boolean acceptNextAlert = true;
    //para el timestamp si lo queremos
      String filetimestamp = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
      
      //captura de pantalla aca ponemos la tura y la clase que estamos probando
      MultiScreenShot mShot=new MultiScreenShot("Resultados de pruebas"+File.separator+"Prore"+ File.separator,filetimestamp + File.separator);
  	
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
	         
	          //<<<<<WebDriver>>>>>
	          //default para FIREFOX  
	          driver = new FirefoxDriver();
	          baseUrl = "https://www.google.co.ve/";
	          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	  driver.get(baseUrl);    	  
	    	  logger.info("fin del setup");
	      
	     
	      
	      }//cierre del setup

	      //este es el main
	      public static void main(String[] args){ 
	          
	          //JUnitCore junit = new JUnitCore();          
	          //junit.run(Socialplus.class);
	     	  
	          Result result = JUnitCore.runClasses(Prore.class);
	          
	  	    for (Failure failure : result.getFailures()) {
	            logger.error(failure.toString());
	         }
	        	logger.error(result.wasSuccessful());
	        	
	        	logger.info("se ejecutaron " + result.getRunCount()+" casos de prueba");
	        	logger.info("fallaron " + result.getFailureCount()+" casos de prueba");
	        	
	      	
	   
	      }
	     
	    @Test    
	      public void A1verificartextosesplogin()throws Exception{
	    	 control("A1verificartextosesplogin");
	    	 captura();
	         //maximze the window
	         driver.manage().window().maximize();
	    	 Thread.sleep(2000);
	    	    	 
	    	//reviso imagenes
	     	estapresente(By.cssSelector(Textos.imagenlogo)); 
	     	estapresente(By.cssSelector(Textos.imagencentrallogin));
	     	estapresente(By.cssSelector(Textos.imagenventures));
	       	//capturas de logos
	     	captura(By.cssSelector(Textos.imagenlogo));
	     	captura(By.cssSelector(Textos.imagencentrallogin));
	     	captura(By.cssSelector(Textos.imagenventures));

	     	//reviso textos esp     	
	   	 	verificatxt(Textos.ingreseasucuenta, By.xpath("//h4"));
	   	 	verificatxt(Textos.idioma, By.xpath("//a[@id='dropdown-label']"));
	   	 	verificatxt(Textos.ayuda, By.xpath("//a[contains(@href, 'https://confluence.synergy-gb.com/display/SOCIALPLUS/Manual+de+Usuario')]"));
	   	 	verificatxt(Textos.olvidocontraseña, By.xpath("//a[contains(@href, '/resetting/request')]"));
	   	 	verificatxt(Textos.copyright, By.cssSelector("p.copyright"));
	   	 	verificatxt(Textos.entrar, By.xpath("//form[@id='loginForm']/button"));
	   	 	
	   	 	captura();
	     
	      }//fin del A1verificartextosesplogin
	      
	         
	     @Test    
	     public void A2recordarcontraseña()throws Exception{
	   	 control("A2A2recordarcontraseñainvalida");     	 
	   	 
		 	
	    // driver.findElement(By.xpath("//input[@id='usernameFake']")).sendKeys("asd");
	     //driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
	    // driver.findElement(By.xpath("//button[@id='_submit']")).click();
		//	estapresente(By.xpath("//form[@id='loginForm']/div[3]"));      
	           
	              }

	     
	     @Test    
	     public void A3logininvalido()throws Exception{
	   	 control("A3logininvalido");     	 
	   	 
		 	
	    driver.findElement(By.xpath("//input[@id='usernameFake']")).sendKeys("asd");
	    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
	    driver.findElement(By.xpath("//button[@id='_submit']")).click();
		estapresente(By.xpath("//form[@id='loginForm']/div[3]")); 
		verificatxt(Textos.advertenciausuarioclaveinvalida, By.xpath("//form[@id='loginForm']/div[3]"));  
		captura();
	    }
	     
	     @Test    
	     public void A4comparacionfallida()throws Exception{
	   	 control("A4comparacionfallida"); 
	   	captura();
	   	 verificatxt("Esto es un error de comparacion", By.xpath("//form[@id='loginForm']/div[3]")); 
	   	
	     }
	     
	     @Test    
	     public void A5asertfallido()throws Exception{
	   	 control("A5asertfallido"); 
	   	captura();
	   	 estanopresente(By.xpath("//form[@id='loginForm']/div[3]"));  
	   	
	     }
	     
	     @Test    
	     public void A6asertfallido()throws Exception{
	   	 control("A6asertfallido");  
	   	captura();
	   	 estanopresente(By.xpath("//form[@id='loginForm']/div[3]"));      
	     }
	     
	     @Test    
	     public void A7noencuentraelemento()throws Exception{
	   	 control("A7asertfallido");  
	   	captura();
	   	driver.findElement(By.xpath("7"));   
	     }

	     
	     @Test    
	     public void A8ultimosifunciona()throws Exception{
	   	 control("A8ultimosifunciona");  
	   	verificatxt(Textos.advertenciausuarioclaveinvalida, By.xpath("//form[@id='loginForm']/div[3]"));
	  
	     }
	     
	     
	      
	     
	     
	     
	     @AfterClass
	      public static void tearDown() throws Exception {
	    	    driver.quit();
	            


	    	  }
	    
	      
	      
	  
	      
	      
	      
	      
	    


	}//cierre de la clase
