package selenium_maven_eclipse;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Runner;
import org.junit.runners.MethodSorters;
//import de seleniums
import org.openqa.selenium.By;
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
  	public void verificatxt(String que , String donde ){
  	
      try {
          assertEquals(que, driver.findElement(By.xpath(donde)).getText());
        } catch (Error e) {
            //MANDANDO EL ERROR AL LOG y al junit
          logger.error("ERROR: "+e.toString());
          assertEquals(que, driver.findElement(By.xpath(donde)).getText());
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
          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	  driver.get(baseUrl);    	  
    	  logger.info("fin del setup");
      }//cierre del setup
      
//metodo de random
public String randomString( int len ) 
{
   StringBuilder sb = new StringBuilder( len );
   for( int i = 0; i < len; i++ ) 
      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
   return sb.toString();
}
  
      //este es el main
      public static void main(String[] args){ 
          
          JUnitCore junit = new JUnitCore();          
          junit.run(Flowbanc.class);
      }

   
      
     @Test    
      public void A1datoslogsvacios()throws Exception{
    	 control("A1datoslogsvacios");
          
          logger.debug("Inicio del caso EmailInvalido");
          
          
                   
            driver.findElement(By.xpath("//input[@type='username']")).clear();
            driver.findElement(By.xpath("//input[@type='username']")).sendKeys("");
            driver.findElement(By.xpath("//input[@type='password']")).clear();
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345678");
            driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
            
            
            
            
            /**ACA ES COMO LO DEBERIAMOS HACER   TRY Y ASSERT*/
            try {
                assertEquals("×\nClose\nInvalid username or password.", driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")).getText());
              } catch (Error e) {
                //MANDANDO EL ERROR AL LOG
                logger.error("ERROR: "+e.toString());
                //repito la misma asert para interrumpir la prueba y mandar el error al junit
                assertEquals("×\nClose\nInvalid username or password.", driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")).getText());
              }
            
            

        
            
          //por log completamente
            /*para guardar un texto de la pagina en un string
            String validar = driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")).getText();
            para escribir un texto al log de una variable guardada
            logger.info("EL mensaje de usuario incorrecto que salio es "+validar);
            comparar una variable string con otro texto
            if(validar.equals("×Close Invalid username or password.")){logger.info("msg correcto");}else {logger.info("msg incorrecto");}*/
            
            
            /**aca validas si son iguales tipo junit, todo lo demas del assert
             * cuando no es verdadero no se ejecuta revisar verify*/
            assertEquals("×\nClose\nInvalid username or password.", driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")).getText());
            
            
            
            
            //cuando quieres enviar un error al junit con un mensaje cuando falla un test
            //fail("ejemplo de fail de prueba");
            
             
            
      }//fin del logingincorrecto
      
      
      
      
      
      

     // @Test    
      public void logingincorrecto()throws Exception{

    	  
    	  
          driver.get(baseUrl);
          
          logger.debug("se cargo la pagina flobanc");
          
              driver.findElement(By.xpath("//input[@type='username']")).clear();
            driver.findElement(By.xpath("//input[@type='username']")).sendKeys("correo.incorrecto@syngergy-gb.com");
            driver.findElement(By.xpath("//input[@type='password']")).clear();
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("prueba");
            
           //CAPTURAS
          //maximze the window
            driver.manage().window().maximize();
            //full screenshot
            mShot.multiScreenShot(driver);
          //take element screenshot using MultiScreenShot class
            mShot.elementScreenShot(driver, driver.findElement(By.xpath("//input[@type='username']")));
            
            driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
            
            //esta es la pausa hasta que cargue el elemento
            wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")));
            
            /**ACA ES CON VERIFY*/
            try {
                assertEquals("×Close Invalid username or password.", driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")).getText());
              } catch (Error e) {
                verificationErrors.append(e.toString());
                //MANDANDO EL ERROR AL LOG
                logger.error("ERROR: "+e.toString());


              }
            
            
   
        
            
          //por log completamente
            /*
            String validar = driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")).getText();
            logger.info("EL mensaje de usuario incorrecto que salio es "+validar);
            if(validar.equals("×Close Invalid username or password.")){logger.info("msg correcto");}else {logger.info("msg incorrecto");}*/
            
            
            /**aca validas si son iguales tipo junit, todo lo demas del assert
             * cuando no es verdadero no se ejecuta revisar verify*/
            assertEquals("×\nClose\nInvalid username or password.", driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section/div")).getText());
            
            
            
            
            //cuando quieres enviar un error al junit con un mensaje cuando falla un test
            //fail("ejemplo de fail de prueba");
            
             
            
      }//fin del logingincorrecto
      
     // @Test    
      public void logcorrecto()throws Exception{

    	  
      
          driver.get(baseUrl);
            logger.debug("se cargo la pagina segundo caso");
            
            driver.findElement(By.xpath("//input[@type='username']")).clear();
            driver.findElement(By.xpath("//input[@type='username']")).sendKeys("juan.rodriguez@synergy-gb.com");
            driver.findElement(By.xpath("//input[@type='password']")).clear();
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("prueba");
            driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
            

            
            //estos assert no sirven porque comparamos nada con textos de imagen ojo...
            assertEquals("", driver.findElement(By.cssSelector("img.small-logo")).getText());
            assertEquals("", driver.findElement(By.cssSelector("i.fa.fa-bars")).getText());
            assertEquals("", driver.findElement(By.cssSelector("img.img-circle.img30_30")).getText());
            assertEquals("Flowbanc", driver.getTitle());
        //otro tipo de mensaje
     
        logger.info("log correcto");
      
        //veryfy de que entraste
        try {
            assertEquals("Statistics", driver.findElement(By.cssSelector("h4.hidden-xs")).getText());
          } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.error("ERROR: "+e.toString());
          }
          try {
            assertEquals("Accounts Receivable", driver.findElement(By.xpath("//section[@id='content']/div/div/div[2]/div/h4")).getText());
          } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.error("ERROR: "+e.toString());
          }
          try {
            assertEquals("Accounts Payable", driver.findElement(By.xpath("//section[@id='content']/div/div/div[3]/div/h4")).getText());
          } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.error("ERROR: "+e.toString());
          }
        

        
          
          
          
      }//fin del logcorrecto
      
      
    //@After
      public void tearDown() throws Exception {
    	    driver.quit();
    	    
    	    //ACA ES PARA DAR EL ERROR CON TODOS LOS VERIFY
    	    //String verificationErrorString = verificationErrors.toString();
    	    //if (!"".equals(verificationErrorString)) {
    	    //  fail(verificationErrorString);*/
    	   // }
    	  }
    
      
      
      /*
       * driver.get(baseUrl + "chrome://web-developer/content/generated/view-color-information.html");
 
assertEquals("#a1513c", driver.findElement(By.xpath("//div[@id='content']/div/div[11]/span")).getText());
  
assertTrue(isElementPresent(By.xpath("//input[@type='username']")));


assertEquals("", driver.findElement(By.xpath("//input[")).getAttribute("type='password']"));
  }

  
                <form class="form-horizontal ng-valid ng-dirty">
                    <fieldset>
                        <div class="form-group" data-ng-class="{'has-error':invalidEmail}">
                            <span class="glyphicon glyphicon-envelope"></span>
                            <input style="" class="form-control input-lg input-round text-center ng-valid ng-dirty" placeholder="Email" data-ng-model="username" data-ng-keyup="$event.keyCode == 13 &amp;&amp; signin(username, password)" type="username">
                        </div>
                        <div class="form-group has-error" data-ng-class="{'has-error':invalidPassword}">
                            <span class="glyphicon glyphicon-lock"></span>
                            <input class="form-control input-lg input-round text-center ng-pristine ng-valid" placeholder="Password" data-ng-model="password" data-ng-keyup="$event.keyCode == 13 &amp;&amp; signin(username, password)" type="password">
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-primary btn-lg btn-round btn-block text-center" data-ng-click="signin(username, password)">Log in</button>
                        </div>
                    </fieldset>
                </form>
                
                
*/
      
      
      
      
      
      
    


}//cierre de la clase
