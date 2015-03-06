package selenium_maven_eclipse;

import org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Runner;
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
public class Flowbanc{
 	
    //declaracion del webdriver y la direccion de la pagina
      public static WebDriver driver;
      public static String baseUrl;
      public static WebDriverWait wait;
      //declaracion del string de errores y para el random     
      private StringBuffer verificationErrors = new StringBuffer();
      static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      static Random rnd = new Random();
      private boolean acceptNextAlert = true;
     //captura de pantalla
      String filetimestamp = new SimpleDateFormat("yyyyMMddhhmm ").format(new Date());
      MultiScreenShot mShot=new MultiScreenShot("C:\\Users\\Silfredo Mora\\workspace\\Capturas de pantalla y logs\\Flowbanc\\",filetimestamp);
  		public String metodo = null;
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
		logger.info("Entro en el metodo "+cualmetodo);
		metodo = null;
		metodo = cualmetodo;		
		}
	
	public void captura (String path) throws IOException{

		mShot.elementScreenShot(driver, driver.findElement(By.xpath(path)));
	
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
	  
	  
	//metodo de random
	  public String randomString( int len ) 
	  {
	     StringBuilder sb = new StringBuilder( len );
	     for( int i = 0; i < len; i++ ) 
	        sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	     return sb.toString();
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
          baseUrl = "https://flowbanc.herokuapp.com/#/pages/signin";
          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	  driver.get(baseUrl);    	  
    	  logger.info("fin del setup");
      }//cierre del setup
      

      //este es el main
      public static void main(String[] args){ 
          
          JUnitCore junit = new JUnitCore();          
          junit.run(Flowbanc.class);
      }

   
      
     //@Test    
      public void A1verificarloging()throws Exception{
    	 control("A1verificarloging");
         //maximze the window
         driver.manage().window().maximize();
                 
        //ambos llenos pero incorrectos
    	estanopresente(By.xpath("//div[@class='form-group has-error']")); 
        driver.findElement(By.xpath("//input[@type='username']")).sendKeys("asd");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345678");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
 	 	estanopresente(By.xpath("//div[@class='form-group has-error']"));
 	 	verificatxt("×\nClose\nInvalid username or password.", By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));
 	 	//falta el que leer el mensaje
    	
 	 	//usuario vacio
 		estanopresente(By.xpath("//div[@class='form-group has-error']")); 
 	 	driver.findElement(By.xpath("//input[@type='username']")).clear();
        driver.findElement(By.xpath("//input[@type='password']")).clear();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345678");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        estapresente(By.xpath("//div[@class='form-group has-error']"));

 	 	//pass vacio
 	 	driver.findElement(By.xpath("//input[@type='username']")).clear();
        driver.findElement(By.xpath("//input[@type='username']")).sendKeys("asd");
        driver.findElement(By.xpath("//input[@type='password']")).clear();
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        estapresente(By.xpath("//div[@class='form-group has-error']"));

   	    //ambos vacios
        driver.findElement(By.xpath("//input[@type='username']")).clear();
        driver.findElement(By.xpath("//input[@type='password']")).clear();
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        estapresente(By.xpath("//div[@class='form-group has-error']"));
                    
      }//fin del A1verificarloging
      
         
     @Test    
     public void A2recordarcontraseña()throws Exception{
   	 control("A2recordarcontraseña");     	 
   	 driver.findElement(By.linkText("Forgot your password?")).click();
	 Thread.sleep(1000);
   	 verificatxt("Enter your email address that you used to register. We'll send you an email with your username and a link to reset your password.",By.cssSelector("p.text-small"));
	 verificatxt("Reset",By.linkText("Reset"));


           driver.findElement(By.xpath("//input[@type='email']")).clear();
           driver.findElement(By.xpath("//input[@type='email']")).sendKeys("juan.rodriguez@synergy-gb.com");            
           driver.findElement(By.linkText("Reset")).click();
           captura();

           
           
     }//fin del A2recordarcontraseña
     
     
     
     
     
     //@Test    
      public void A3logcorrecto()throws Exception{
    	 control("A3logcorrecto");     	 
    	 driver.get(baseUrl);
    	 
    	 verificatxt("Log in","(//button[@type='button'])[2]");
    	 verificatxt("Don't have an account yet? Sign up","//section[@id='content']/div/div[2]/div/div/section[2]/p[2]");
    	 verificatxt("Forgot your password?",By.linkText("Forgot your password?"));


          
            driver.findElement(By.xpath("//input[@type='username']")).clear();
            driver.findElement(By.xpath("//input[@type='username']")).sendKeys("juan.rodriguez@synergy-gb.com");
            driver.findElement(By.xpath("//input[@type='password']")).clear();
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("prueba");            
            driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

            
            
      }//fin del A3logcorrecto
      

      
      
    //@After
      public void tearDown() throws Exception {
    	    driver.quit();

    	  }
    
      
      
  
      
      
      
      
    


}//cierre de la clase
