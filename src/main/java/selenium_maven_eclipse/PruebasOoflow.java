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
public class PruebasOoflow{
	static String ruta = "Resultados de pruebas"+File.separator+"Ooflow"+ File.separator;
 	
    //declaracion del webdriver y la direccion de la pagina
      public static WebDriver driver;
      public static String baseUrl;
      public static WebDriverWait wait;
      //declaracion del string de errores y para el random     

      private boolean acceptNextAlert = true;

      
      //captura de pantalla aca ponemos la tura y la clase que estamos probando
      MultiScreenShot mShot=new MultiScreenShot(ruta,Tester.filetimestamp + File.separator);
  	
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
	          baseUrl = "https://ooflow.herokuapp.com";
	          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	  driver.get(baseUrl);
	    	  driver.manage().window().maximize();
	    	  logger.info("fin del setup");
	      
	     
	      
	      }//cierre del setup

	      
	      
	      
	      @Test    
	      public void A1verificarloginincorrecto()throws Exception{
	    	 control("A1verificarloginincorrecto");
	    	 verificaurl(Textos.loginURL);
	         captura();
	         
	              
	        // caso ambos llenos pero incorrectos
	    	estanopresente(By.xpath("//div[@class='form-group has-error']")); 
	        driver.findElement(By.xpath("//input[@type='username']")).sendKeys("asd");
	        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345678");
	        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	 	 	estanopresente(By.xpath("//div[@class='form-group has-error']"));
	 	 	verificatxt(Textos.usuariooclaveinvalido, By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));
	 	 		 	
	    	
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
	        
	      }
	      
	         
	     @Test    
	     public void A2recordarclave()throws Exception{
	   	 control("A2recordarclave");
	   	 verificaurl(Textos.loginURL);
	   	 verificatxt(Textos.forgotpassword,"//section[@id='content']/div/div[2]/div/div/section[2]/p/a");
	   	 verificatxt(Textos.donthaveaccount,"//section[@id='content']/div/div[2]/div/div/section[2]/p[2]");
	   	 driver.findElement(By.linkText(Textos.forgotpassword)).click();
		 Thread.sleep(300);
		 verificaurl(Textos.forgotpassURL);
		 verificatxt(Textos.introducecorreo,By.xpath("//section[@id='content']"));
		 verificatxt(Textos.reset,"//section[@id='content']/div/div[3]/form/div[2]/a");
	   	 verificatxt(Textos.instruccionesrecuperacion,"//section[@id='content']/div/div[2]/p");
	   	 
		 //datos invalidos
	     driver.findElement(By.xpath("//input[@type='email']")).sendKeys("correoinvalido@dominio.com");
	     driver.findElement(By.linkText("Reset")).click();
	     Thread.sleep(1000);
	     verificatxt(Textos.errorrecoverpassword, By.xpath("//section[@id='content']/div/div[3]/section/div"));        
	      
	   	 verificaurl(Textos.forgotpassURL);
	     //datos validos
	     driver.findElement(By.xpath("//input[@type='email']")).clear();
	     driver.findElement(By.xpath("//input[@type='email']")).sendKeys("movilidadsynergy@gmail.com");
	     driver.findElement(By.linkText("Reset")).click();
	     Thread.sleep(1000);
	     verificatxt(Textos.emailsent, By.xpath("//section[@id='content']/div/div[3]/section/div"));
         Thread.sleep(5000);
	     
	     
	     }
	     
	     
  
	     
	     @Test    
	      public void A3signup()throws Exception{
	    	 control("A3signup");     	 
	    	 verificaurl(Textos.loginURL);
	    	 driver.findElement(By.xpath("//a[contains(@href, '#/pages/signup')]")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(Textos.signupURL);
	    	 verificatxt(Textos.termsandconditions, By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/p"));
	    	 verificatxt(Textos.haveaccount, By.xpath("//section[@id='content']/div/div[2]/div/div/section[3]/p"));
	    	 driver.findElement(By.xpath("//a[contains(text(),'Log in now')]")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(Textos.loginURL);
	    	 Thread.sleep(500);    	
	    	 driver.findElement(By.xpath("//a[contains(@href, '#/pages/signup')]")).click();
	    	 verificaurl(Textos.signupURL);
	    	 
	    	 //casos de prueba de singup
	    	 //casos posibles correos ya registrados, correos invalidos, correo vacio y registro exitoso
	    	 
	    	 //caso correo invalido esta medio malo porque no reconoce la estructura de email por ahora
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys("silfre");
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).sendKeys("synergy1234");
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
	    	 estapresente(By.xpath("//div[@class='form-group has-error']"));
	    	 
	    	 //caso clave vacia no tiene sentido invalida?
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys("silfredomora@hotmail.com");
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
	    	 estapresente(By.xpath("//div[@class='form-group has-error']"));
	    	 
	    	 //caso ambas vacias
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
	    	 estapresente(By.xpath("//div[@class='form-group has-error']"));
	    	 
	    	 //regristro correo random
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys(Tester.filetimestamp+RandomStringGenerator.generateRandomString(5,RandomStringGenerator.Mode.ALPHANUMERIC)+"@dominio.com");
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).sendKeys(RandomStringGenerator.generateRandomString(10,RandomStringGenerator.Mode.ALPHANUMERIC));
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
	    	 estanopresente(By.xpath("//div[@class='form-group has-error']"));
	    	 verificatxt(Textos.registroexitoso, By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));
	    	 
	    	 //caso correo ya registrado
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys("silfredomora@hotmail.com");
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).sendKeys("synergy1234");
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
	    	 estanopresente(By.xpath("//div[@class='form-group has-error']"));
	    	 verificatxt(Textos.userexists, By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));
	    	 driver.findElement(By.xpath("//a[contains(text(),'Log in now')]")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(Textos.loginURL);
	    	 
	    	 
	      }
	     
	     
	      
	      @Test   
	      public void A4loginexitoso () throws Exception{
	    	 control("A4loginexitoso");    	 
	    		        
		    	driver.findElement(By.xpath("//input[@type='username']")).sendKeys("silfredomora@gmail.com");
		        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("synergy1234");
		        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		        Thread.sleep(1000);
		        verificaurl(Textos.dashboardURL);
		 	 	  	 
	      }
	      
	      
	      @Test   
	      public void A5dashboard () throws Exception{
	    	 control("A5dashboard"); 
	    	 Thread.sleep(4000);
	    	 verificatxt(Textos.cuentausuario, By.cssSelector("span.ng-binding"));
	    	 verificatxt(Textos.statisticsbar, By.xpath("//section[@id='content']/div/div/div/div/h4"));
	    	 verificatxt(Textos.accoutreceivable, By.xpath("//section[@id='content']/div/div/div[2]/div/h4"));
	    	 verificatxt(Textos.accoutpayable, By.xpath("//section[@id='content']/div/div/div[3]/div/h4"));
	    	 verificatxt(Textos.dashboardmenu, By.xpath("//ul[@id='nav']/li/a/span"));
	    	 verificatxt(Textos.invoicesmenu, By.xpath("//ul[@id='nav']/li[2]/a/span"));
	    	 driver.findElement(By.xpath("//ul[@id='nav']/li[2]/a/span")).click();
	    	 Thread.sleep(500);
	    	 verificatxt(Textos.newinvoicemenu, By.xpath("//ul[@id='nav']/li[2]/ul/li/a/span"));
	    	 verificatxt(Textos.searchmenu, By.xpath("//ul[@id='nav']/li[2]/ul/li[2]/a/span"));
	    	 verificatxt(Textos.draftsmenu, By.xpath("//ul[@id='nav']/li[2]/ul/li[3]/a/span"));
	    	 verificatxt(Textos.connectionsmenu, By.xpath("//ul[@id='nav']/li[3]/a/span"));
	    	 captura();
	    	 verificatxt(Textos.currentinvoicesbox, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div/div[2]/div/div/p[2]/span"));
	    	 verificatxt(Textos.overduebox, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div/div[3]/div/div/p[2]/span"));
	    	 verificatxt(Textos.totalreceivablesbox, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div[3]/div/div/div/p[2]/span"));
	    	 verificatxt(Textos.totalpayablesbox, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div[3]/div[2]/div/div/p[2]/span"));
	    	 verificatxt(Textos.acceptedinvoucebox, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div[3]/div[3]/div/div/p[2]/span"));
	    	 verificatxt(Textos.disputedbox, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div[3]/div[4]/div/div/p[2]/span"));
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 Thread.sleep(1000);
	    	 verificatxt(Textos.myprofilebar, By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li/a/span"));
	    	 verificatxt(Textos.changepassbar, By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li[2]/a/span"));
	    	 verificatxt(Textos.logoutbar, By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li[3]/a/span"));
	    	 verificatxt(Textos.grafica1titulo, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div[2]/div/div/div"));
	    	 verificatxt(Textos.grafica2titulo, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div[2]/div[2]/div/div"));
		 	 
		 	
		 	//chequeo el redimencionamiento del menu lateral con el primer elemento
		 	 Dimension dashboardmenugrande= driver.findElement(By.xpath("//ul[@id='nav']/li/a/span")).getSize();		 	
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li/a")).click();
	    	 Thread.sleep(1000);	    	 
	    	 Dimension dashboardmenupequeño= driver.findElement(By.xpath("//ul[@id='nav']/li/a")).getSize();
	    	 logger.info("menu lateral grande "+ dashboardmenugrande);
	    	 logger.info("menu lateral pequeño "+ dashboardmenupequeño);
	    	 assertTrue("Al reducir menu lateral no se redimenciono correctamente",dashboardmenugrande.width >= dashboardmenupequeño.width && dashboardmenugrande.height <= dashboardmenupequeño.height);
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li/a")).click();
	    	     	 
	    	 
	      }
	      
	      
	      @Test   
	      public void A6myprofilegeneralinfo() throws Exception{
	    	 control("A6myprofilegeneralinfo"); 
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 Thread.sleep(300);
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li/a")).click();
	    	 Thread.sleep(300);
	    	 verificaurl(Textos.myprofileURL);
	    	 verificatxt(Textos.empresadeusuario.toUpperCase(), By.xpath("//section[@id='content']/div/div/div/div/div/h3"));
	    	 captura("//section[@id='content']/div/div/div/div/div/img");
	    	 verificatxt(Textos.generalinfo, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div/strong"));
	    	 verificatxt(Textos.logo, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/form/ul/li[2]/div/div/div/div"));
	    	 verificatxt(Textos.savechanges, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[4]/button"));
	    	 verificatxt(Textos.addresses, By.xpath("//section[@id='content']/div/div/div/div[3]/div/div/div/strong"));
	    	 verificatxt(Textos.phonenumbers, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div/strong"));
	    	 verificatxt(Textos.addaddress, By.xpath("//section[@id='content']/div/div/div/div[3]/div/div/div[2]/div/div/div/div/div/button"));
	    	 verificatxt(Textos.addphone, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button"));
	    	 verificatxt(Textos.quickbooksintegration, By.xpath("//section[@id='content']/div/div[2]/div/section/div/strong/span[2]"));
	    	 
	    	 //revision de los valores default
	    	 verificavalue(Textos.empresadeusuario, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li/div/div[2]/input"));
	    	 verificavalue(Textos.correoeusuario, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input"));
	    	 verificavalue(Textos.cuentausuario, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 verificavalue(Textos.quehaceusuario, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/form/ul/li/div/div[2]/textarea"));

	    	 WebElement empresaimput = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li/div/div[2]/input"));
	    	 WebElement cuentaimput = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 WebElement quehaceimput = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/form/ul/li/div/div[2]/textarea"));
	    	 WebElement guardargeneralinfo = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[4]/button"));
	    	 String empresarandom = RandomStringGenerator.generateRandomString(10,RandomStringGenerator.Mode.ALPHANUMERIC);
	    	 String cuentarandom = RandomStringGenerator.generateRandomString(10,RandomStringGenerator.Mode.ALPHANUMERIC);
	    	 String quehacerandom = RandomStringGenerator.generateRandomString(60,RandomStringGenerator.Mode.ALPHANUMERIC);
	    	 
	    	 //verifico si se pueden guardar vacio ojo con esto
	    	 empresaimput.clear();
	    	 cuentaimput.clear();
	    	 quehaceimput.clear();
	    	 guardargeneralinfo.click();

	    	 //modificacion de los valores por otros
	    	 empresaimput.sendKeys(empresarandom);
	    	 cuentaimput.sendKeys(cuentarandom);
	    	 quehaceimput.sendKeys(quehacerandom);
	    	 guardargeneralinfo.click();
	    	 
	    	 //se verifica que se modificaran el nombre y titulo y se vea en el resto de la pagina
	    	 verificatxt(empresarandom.toUpperCase(), By.xpath("//section[@id='content']/div/div/div/div/div/h3"));
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div/a/img")).click();
	    	 Thread.sleep(500);
	    	 verificatxt(cuentarandom, By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a/span/span"));
	    	 verificatxt(empresarandom, By.xpath("//section[@id='content']/div/div[2]/div/div/ng-include/div/div/div/div/div[2]/p"));
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 Thread.sleep(500);
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li/a")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(Textos.myprofileURL);
	    	 
	    	 //SE COLOCAN LOS VALORES DEFAULT DE NUEVO
	    	 WebElement empresaimput1 = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li/div/div[2]/input"));
	    	 WebElement cuentaimput1 = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 WebElement quehaceimput1 = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/form/ul/li/div/div[2]/textarea"));
	    	 WebElement guardargeneralinfo1 = driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[4]/button"));
	    	 empresaimput1.clear();
	    	 cuentaimput1.clear();
	    	 quehaceimput1.clear();
	    	 empresaimput1.sendKeys(Textos.empresadeusuario);
	    	 cuentaimput1.sendKeys(Textos.cuentausuario);
	    	 quehaceimput1.sendKeys(Textos.quehaceusuario);
	    	 guardargeneralinfo1.click();
	    	 
	    	
	    	
	      }
	     
	      @Ignore("Esto no se va implementar por ahora, se van a desarrollar cambios")
	      @Test   
	      public void A7myprofileadress() throws Exception{
	    	 control("A7myprofileadress"); 	    	 
	    	 driver.get("https://ooflow.herokuapp.com/#/dashboard");
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 Thread.sleep(500);
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li/a")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(Textos.myprofileURL);
	    	 
	    	 verificavalue(Textos.direccion, By.xpath("//section[@id='content']/div/div/div/div[3]/div/div/div[2]/div/div/div/form/ul/li/div/div[2]"));
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div/div/div[2]/div/div/div/div/div/button")).click();
	    	
	    	 
	    	
	    	 
	    	
	    	
	      }     
	     @Ignore ("se tiene que reparar esta funcionalidad para que la prueba corra")
	      @Test   
	      public void A8myprofiletelefono() throws Exception{
	    	 control("A8myprofiletelefono"); 	
	    	 String telefonorandom2 = RandomStringGenerator.generateRandomString(11,RandomStringGenerator.Mode.NUMERIC);
	    	 String telefonorandom3 = RandomStringGenerator.generateRandomString(20,RandomStringGenerator.Mode.ALPHANUMERIC);
	    	 String telefonorandom4 = RandomStringGenerator.generateRandomString(60,RandomStringGenerator.Mode.ALPHANUMERIC);
	    	 
	    	 driver.get("https://ooflow.herokuapp.com/#/dashboard");
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 Thread.sleep(500);
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li/a")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(Textos.myprofileURL);
	    	 
	    	 //reviso condicion inicial
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li/div/div[2]/input"));
	    	 verificavalue(Textos.telefono, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li/div/div[2]/input"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[4]/div/div[2]/input"));
	    	 
	    	 //añado telefono 2
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button")).click();
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li/div/div[2]/input)"));
	    	 verificavalue(Textos.telefono, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li/div/div[2]/input"));
	    	 verificavalue("", By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)"));
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)")).sendKeys(telefonorandom2);
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form[2]/ul/li/div/div[3]/div/span")).click();
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[4]/div/div[2]/input"));
	    	 verificavalue(telefonorandom2, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)"));
	    	 
	    	 //añado telefono 3
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button")).click();
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li/div/div[2]/input)"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 verificavalue(Textos.telefono, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li/div/div[2]/input"));
	    	 verificavalue(telefonorandom2, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)"));
	    	 verificavalue("", By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input")).sendKeys(telefonorandom3);
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form[2]/ul/li/div/div[3]/div/span")).click();
	    	 verificavalue(telefonorandom3, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[4]/div/div[2]/input"));
	      
	    	 //pretendo añadir varias filas de telefono sin llenarlas
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button")).click();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button")).click();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button")).click();
	    	 driver.findElement(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button")).click();
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[2]/div/div[2]/input)"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li/div/div[2]/input)"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[4]/div/div[2]/input"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[5]/div/div[2]/input"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[6]/div/div[2]/input"));
	    	 
	    	 
	    	 verificavalue(telefonorandom3, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[3]/div/div[2]/input"));
	    	 estanopresente(By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/form/ul/li[4]/div/div[2]/input"));
	      
	      
	      
	      }     
	     
	     
	      
	      @Test   
	      public void A9myconeections() throws Exception{
	    	 control("A9myconeections"); 	    	 
	    	 //driver.get("https://ooflow.herokuapp.com/#/dashboard");
	    	 	  
	    	 driver.findElement(By.xpath("//ul[@id='nav']/li[3]/a")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(Textos.myconnectionURL);
	    	 //leo textos
	    	 verificatxt(Textos.myconnections.toUpperCase(), By.xpath("//div[2]/div/section/div"));
	    	 verificatxt(Textos.ooflownetwork.toUpperCase(), By.xpath("//div[2]/div[2]/section/div"));
	    	 verificatxt(Textos.connectionsactual, By.xpath("//section[@id='content']/div/div[2]/div/section/div[2]/ul/li/a"));
	    	 verificatxt(Textos.addcontac, By.xpath("//section[@id='content']/div/div[2]/div/section/div[3]/button"));
	    	 
	    	 
	    	 //buscador
	    	 estanopresente(By.xpath("//section/ul/li"));
	    	 estanopresente(By.xpath("//section/ul/li[2]"));
	    	 estanopresente(By.xpath("//section/ul/li[3]"));
	    	 estapresente(By.xpath("//div[2]/ul/li"));
	    	 driver.findElement(By.xpath("//input[@id='search']")).sendKeys("s");
	    	 Thread.sleep(500);
	    	 estapresente(By.xpath("//section[@id='content']/div/div[2]/div[2]/section/ul/li"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div[2]/div[2]/section/ul/li[2]"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div[2]/div[2]/section/ul/li[3]"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div[2]/div[2]/section/ul/li[4]"));
	    	 estapresente(By.xpath("//div[2]/ul/li"));
	    	 driver.findElement(By.xpath("//input[@id='search']")).sendKeys("ynergy");
	    	 Thread.sleep(500);
	    	 estanopresente(By.xpath("//div[2]/ul/li"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div[2]/div[2]/section/ul/li"));
	    	 estanopresente(By.xpath("//section/ul/li[2]"));
	    	 estanopresente(By.xpath("//section/ul/li[3]"));
	    	 driver.findElement(By.xpath("//section/ul/li/span")).click();
	    	 estanopresente(By.xpath("//div[2]/ul/li"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div[2]/div[2]/section/ul/li"));
	    	 estanopresente(By.xpath("//section/ul/li[2]"));
	    	 //agrego de una busqueda
	    	 driver.findElement(By.xpath("//input[@id='search']")).clear();
	    	 driver.findElement(By.xpath("//input[@id='search']")).sendKeys("ooflow");
	    	 estanopresente(By.xpath("//div[2]/ul/li"));
	    	 estapresente(By.xpath("//section[@id='content']/div/div[2]/div[2]/section/ul/li"));
	    	 //estanopresente(By.xpath("//section/ul/li[2]"));
	    	 driver.findElement(By.xpath("//section/ul/li/span")).click();
	    	     	 
	    	 
	    	 
	    	 
	    	 //esto deberia de arreglarlo saul se necesita click 2 veces una de las veces solo cambia el url y no la pagina
	    	 driver.findElement(By.xpath("//div[3]/button")).click();
	    	 verificaurl(Textos.myconnectionaddURL);
	    	 driver.findElement(By.xpath("//div[3]/button")).click();
	    	 
	    	 
	    	 //restauro situacion inicial
	    	 //if(driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/section/div[2]/ul/li")).getAttribute("value")=="ooflow, Inc.")
	    	 { driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/section/div[2]/ul/li/span")).click();
	    	 Thread.sleep(500);
	    	 driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
	    	 
	    	 }
	    	 
	    	 //verificaurl(Textos.myprofileURL);
	    	 
	    	 //verificavalue(Textos.direccion, By.xpath("//section[@id='content']/div/div/div/div[3]/div/div/div[2]/div/div/div/form/ul/li/div/div[2]"));
	    	 
	    	
	    	 
	    	
	    	 
	    	
	    	 
	    	
	    	
	      }  

	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	     
	     
	     
	     
	     
	     
	     
	     
	     
	      

	      
	      
	    //@AfterClass
	      public static void tearDown() throws Exception {
	    	    driver.quit();

	    	  }
	    
	      
	      
	  
	      
	      
	      
	      
	    


	}//cierre de la clase
