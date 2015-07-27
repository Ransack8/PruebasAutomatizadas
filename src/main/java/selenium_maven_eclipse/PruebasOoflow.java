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

//correr los casos en orden alfabetico
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasOoflow {
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
	  

		  public void verificaurl(String currenturl , String url){
			  	
		      try {
		          assertEquals(currenturl, driver.getCurrentUrl());
		        } catch (Error e) {
		            //MANDANDO EL ERROR AL LOG y al junit
		          logger.error("ERROR: "+e.toString());
		          assertEquals(currenturl, driver.getCurrentUrl());
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

	      
	      
	      
	      //@Test    
	      public void A1verificarloginincorrecto()throws Exception{
	    	 control("A1verificarloginincorrecto");
	    	 verificaurl(driver.getCurrentUrl(),Textos.loginURL);
	         
	         
	              
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
	      
	         
	     //@Test    
	     public void A2recordarclave()throws Exception{
	   	 control("A2recordarclave");
	   	 verificaurl(driver.getCurrentUrl(),Textos.loginURL);
	   	 verificatxt(Textos.forgotpassword,"//section[@id='content']/div/div[2]/div/div/section[2]/p/a");
	   	 verificatxt(Textos.donthaveaccount,"//section[@id='content']/div/div[2]/div/div/section[2]/p[2]");
	   	 driver.findElement(By.linkText(Textos.forgotpassword)).click();
		 Thread.sleep(300);
		 verificaurl(driver.getCurrentUrl(),Textos.forgotpassURL);
		 verificatxt(Textos.introducecorreo,By.xpath("//section[@id='content']"));
		 verificatxt(Textos.reset,"//section[@id='content']/div/div[3]/form/div[2]/a");
	   	 verificatxt(Textos.instruccionesrecuperacion,"//section[@id='content']/div/div[2]/p");
	   	 
		 //datos invalidos
	     driver.findElement(By.xpath("//input[@type='email']")).sendKeys("correoinvalido@dominio.com");
	     driver.findElement(By.linkText("Reset")).click();
	     Thread.sleep(1000);
	     verificatxt(Textos.errorrecoverpassword, By.xpath("//section[@id='content']/div/div[3]/section/div"));        
	      
	   	 verificaurl(driver.getCurrentUrl(),Textos.forgotpassURL);
	     //datos validos
	     driver.findElement(By.xpath("//input[@type='email']")).clear();
	     driver.findElement(By.xpath("//input[@type='email']")).sendKeys("movilidadsynergy@gmail.com");
	     driver.findElement(By.linkText("Reset")).click();
	     Thread.sleep(500);
	     verificatxt(Textos.emailsent, By.xpath("//section[@id='content']/div/div[3]/section/div"));
         Thread.sleep(5000);
	     
	     
	     }
	     
	     
  
	     
	     //@Test    
	      public void A3signup()throws Exception{
	    	 control("A3signup");     	 
	    	 verificaurl(driver.getCurrentUrl(),Textos.loginURL);
	    	 driver.findElement(By.xpath("//a[contains(@href, '#/pages/signup')]")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(driver.getCurrentUrl(),Textos.signupURL);
	    	 verificatxt(Textos.termsandconditions, By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/p"));
	    	 verificatxt(Textos.haveaccount, By.xpath("//section[@id='content']/div/div[2]/div/div/section[3]/p"));
	    	 driver.findElement(By.xpath("//a[contains(text(),'Log in now')]")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(driver.getCurrentUrl(),Textos.loginURL);
	    	 Thread.sleep(500);    	
	    	 driver.findElement(By.xpath("//a[contains(@href, '#/pages/signup')]")).click();
	    	 verificaurl(driver.getCurrentUrl(),Textos.signupURL);
	    	 
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
	    	 verificaurl(driver.getCurrentUrl(),Textos.loginURL);
	    	 
	    	 
	      }
	     
	     
	      
	      @Test   
	      public void A4loginexitoso () throws Exception{
	    	 control("A4loginexitoso");    	 
	    		        
		    	driver.findElement(By.xpath("//input[@type='username']")).sendKeys("silfredomora@gmail.com");
		        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("synergy1234");
		        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		        verificaurl(driver.getCurrentUrl(),Textos.dashboardURL);
		 	 	  	 
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
	      public void A6myprofile() throws Exception{
	    	 control("A6myprofile"); 
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/a")).click();
	    	 Thread.sleep(500);
	    	 driver.findElement(By.xpath("//section[@id='header']/header/div[3]/ul/li[2]/ul/li/a")).click();
	    	 Thread.sleep(500);
	    	 verificaurl(driver.getCurrentUrl(),Textos.myprofileURL);
	    	 verificatxt(Textos.empresadeusuario, By.xpath("//section[@id='content']/div/div/div/div/div/h3"));
	    	 captura("//section[@id='content']/div/div/div/div/div/img");
	    	 verificatxt(Textos.generalinfo, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div/strong"));
	    	 verificatxt(Textos.logo, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/form/ul/li[2]/div/div/div/div"));
	    	 verificatxt(Textos.savechanges, By.xpath("//section[@id='content']/div/div/div/div[2]/div/div/div[2]/div/div/div/form/ul/li[4]/button"));
	    	 verificatxt(Textos.addresses, By.xpath("//section[@id='content']/div/div/div/div[3]/div/div/div/strong"));
	    	 verificatxt(Textos.phonenumbers, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div/strong"));
	    	 verificatxt(Textos.addaddress, By.xpath("//section[@id='content']/div/div/div/div[3]/div/div/div[2]/div/div/div/div/div/button"));
	    	 verificatxt(Textos.addphone, By.xpath("//section[@id='content']/div/div/div/div[3]/div[2]/div/div[2]/div/div/div/div/div/button"));
	    	 verificatxt(Textos.quickbooksintegration, By.xpath("//section[@id='content']/div/div[2]/div/section/div/strong/span[2]"));
	    	 
	    	 
	    	 verificatxt(Textos.addresses, By.xpath("//ul[@id='nav']/li[3]/a/span"));
	    	 verificatxt(Textos.addresses, By.xpath("//ul[@id='nav']/li[3]/a/span"));
	    	 verificatxt(Textos.addresses, By.xpath("//ul[@id='nav']/li[3]/a/span"));
	    	 verificatxt(Textos.addresses, By.xpath("//ul[@id='nav']/li[3]/a/span"));
	    	 

	    	 
	    	 
	    	
	    	 
	      }
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	      

	      
	      
	    //@After
	      public void tearDown() throws Exception {
	    	    driver.quit();

	    	  }
	    
	      
	      
	  
	      
	      
	      
	      
	    


	}//cierre de la clase
