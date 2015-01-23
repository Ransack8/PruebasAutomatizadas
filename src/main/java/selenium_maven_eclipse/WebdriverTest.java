package selenium_maven_eclipse;

//import de seleniums
import org.junit.*;
//import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.JUnitCore;
import org.

junit.runner.Runner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;


/*permite definir una espera o delay mientras carga un elemento*/
import java.util.concurrent.TimeUnit;


//Librer�a para apoyarnos en los logs para presentar, los resultados de las pruebas*/
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;


//Librer�a del driver de Internet Explorer,
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverTest {
		
	//declaracion del webdriver y la direccion de la pagina
	  public static WebDriver driver;
	  public static String baseUrl;
	  public static WebDriverWait wait;
	
	  // declaracion de los loggers  
	  public static final Logger logger = Logger.getLogger("WebdriverTest");
	  public static final Logger alogger = Logger.getLogger("AppiumTest");
	
	  
//la etiqueta before son las cosas que se ejecutan antes de la prueba
	  @Before
	  public void setUp() throws Exception {
	      //<<<<<LOGS>>>>>
		  //carga una configuracion basica que imprime por consola
		  BasicConfigurator.configure();
		  //para los archivos de los logs
		  FileAppender filelog = new FileAppender( new PatternLayout("%r [%t] %p %c %x - %m%n"), "LogsTest.log");
		  FileAppender wfilelog = new FileAppender( new SimpleLayout(), "LogsWebdriver.log");
		  FileAppender afilelog = new FileAppender(new PatternLayout("%r [%t] %-5p %c %x - %m%n"),"LogsAppium.log");
		  //si quiero darle una ruta especifica
		  // ejemplo: Logger.addAppender(new FileAppender(logDir + File.separator + getLogName()));
		  //para los loggers definan en donde guardan
		  Logger root = Logger.getRootLogger();		 
		  root.addAppender(filelog);
		  logger.addAppender(wfilelog);
		  alogger.addAppender(afilelog);
		  
		 //esto es para asignar un nivel por codigo
		    logger.setLevel(Level.INFO);
		    alogger.setLevel(Level.INFO);
				  
		//<<<<<WebDriver>>>>>
		//default para FIREFOX  
		driver = new FirefoxDriver();
	    baseUrl = "https://login.live.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
		/*IEXplorer
		  System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		  DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
		// Para saltar las zonas de seguridad de internet explorer
		  capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		  capab.setCapability("ignoreProtectedModeSettings", true);
		 se instancia driver
		  driver = new InternetExplorerDriver(capab);
		  baseUrl = "https://login.live.com/";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
	   
		  logger.info("se realizo el setup del WebDriver");
	  }//cierre del setup
	  

	  //este es el main
	  public static void main(String[] args){
		  
		  JUnitCore junit = new JUnitCore();		  
		  junit.run(WebdriverTest.class);
	  }

	  @Test	  
	  public void main() throws Exception { 
		  
		    
		  
		  //test con appium
		 AppiumTest bar = new AppiumTest();
		 bar.setUp();
				  
	    driver.get(baseUrl + "/login.srf?wa=wsignin1.0&rpsnv=12&ct=1419017228&rver=6.4.6456.0&wp=MBI_SSL_SHARED&wreply=https:%2F%2Fmail.live.com%2Fdefault.aspx&lc=3082&id=64855&mkt=es-es&cbcxt=mai");
	    driver.findElement(By.id("i0116")).clear();
	    driver.findElement(By.id("i0116")).sendKeys("silfredomora@hotmail.com");
	    driver.findElement(By.id("i0118")).clear();
	    driver.findElement(By.id("i0118")).sendKeys("syndra1881");
	    driver.findElement(By.id("i0118")).sendKeys(Keys.RETURN);
	    //otro tipo de mensaje
	    logger.info("se termino en main");
	    logger.info("se hizo el logging!!!");
	    
	        
	    	
	  }//cierre del main

	  /*
	 @After
	  public void tearDown() throws Exception {
		    driver.quit();
	
	  }
	
	*/


}//cierre de la clase




