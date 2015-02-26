package selenium_maven_eclipse;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AndroidKeyCode;
import io.appium.java_client.AppiumDriver;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
//Librer�a para apoyarnos en los logs para presentar, los resultados de las pruebas*/
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

//screenshots appium
import org.openqa.selenium.remote.Augmenter;



//correr los casos en orden alfabetico
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppiumTest {
	static AppiumDriver driver;
	//logs de appium
	public static final Logger alogger = Logger.getLogger("AppiumTest");
	//ruta de los screenshots 
	public String Screenshotpath = "C:\\Users\\Silfredo Mora\\workspace\\Capturas de pantalla y logs\\Pagoflash\\";
	
	WebDriver scrdriver = new Augmenter().augment(driver);
	


	

	
	

	//textos a verificar en strings
	String derechos = "Todos los derechos reservados ©SYNERGY-GB,LLC 2015";
	String version = "V 1.0";
	String botoningresartexto = "Ingresar";
	String textoetiquetatutorial = "Tutorial";
	String textoetiquesiguiente = "Siguiente";
	String cambiarfoto = "Cambiar foto";
	String nombreusuario = "Due Sarah";
	String pagarid = "PAGAR ID FLASH";
	String registrarid = "REGISTRAR ID FLASH";
	String configurarid ="CONFIGURAR ID FLASH";
	String cerrarsesion = "CERRAR SESION";
	String resumenflash = "RESUMEN FLASH";
	String confirmacionsalir = "¿Está seguro de que desea salir?";
	String aceptar = "Aceptar";
	String cancelar = "Cancelar";
	String confirmacion = "Confirmación";
	
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
        alogger.error("ERROR: "+e.toString());
        assertEquals(que, driver.findElement(By.xpath(donde)).getText());
      }
	}
	
	@BeforeClass
	  public static void setUp() throws MalformedURLException, InterruptedException, Exception {
        alogger.info("Entro en el setup");
    	// set up las capabilities del appium
		String apkpath = "C:\\Android\\app-debug.apk";
		File app = new File(apkpath);	
		//logs de appium
		BasicConfigurator.configure();
		Logger rootlooger = Logger.getRootLogger();
		FileAppender filelog = new FileAppender( new PatternLayout("%r [%t] %p %c %x - %m%n"), "LogsTest.log");		
		FileAppender afilelog = new FileAppender(new PatternLayout("%r [%t] %-5p %c %x - %m%n"),"LogsAppium.log");
		rootlooger.addAppender(filelog);
		alogger.addAppender(afilelog);
		alogger.setLevel(Level.INFO);
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		//nexus
		//capabilities.setCapability ("deviceName", "Galaxy Nexus");
		//capabilities.setCapability ("platformVersion", "4.3");
		//Huawei
		capabilities.setCapability ("deviceName", "HUAWEI Y221-U03");
		capabilities.setCapability ("platformVersion", "4.4.2");
		
        capabilities.setCapability ("app", app.getAbsolutePath());
        capabilities.setCapability("appium-version", "1.1");
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);        

		/*//por xpath atributo texto no usar
		 WebElement element1= driver.findElement(By.xpath("//android.widget.EditText[@text='usuario1']"));		 
		 element1.clear();
		 element1.sendKeys("intento1");		 
		 //por xpath atributo id  USAR ESTE
		 WebElement element5= driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.synergygb.pagoflash.view:id/fragment_login_user_field']"));
		 element5.clear();
		 element5.sendKeys("intento5");
		 */
		 		
		/* //por id
		 WebElement element6= driver.findElement(By.id("com.synergygb.pagoflash.view:id/fragment_login_user_field"));
		 element6.sendKeys("intento6");*/
        
      //path absoluto
      //verificatxt(cambiarfoto,"/hierarchy/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.view.View/android.widget.ListView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
 
		 /*
			driver.tap(1,520, 790,100);
		 */
		 
			/*
		    driver.swipe(349, 925, 349, 600, 500);       
		    driver.pinch(349, 925);        
		    */
		            
    }//fin del setup

	
	//@Test	
	public void A1verificartextosloging() throws InterruptedException {
		alogger.info("inicio de A1verificartextosloging");	
		//verificar textos derechos
	    verificatxt(derechos,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/copyright_text']" );
	    //verificar texto version
	    verificatxt(version,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/version_text']" );
		//verificar texto boton ingresar
	    verificatxt(botoningresartexto,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/text_login_button']");
		//verificar texto etiquetatutorial
	    verificatxt(textoetiquetatutorial,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/text_tutorial']");       
	}//fin verificartextosloging
	
	
	//@Test	
	public void A2verificartutorial() throws InterruptedException {
		alogger.info("A2verificartutorial");		
		WebElement botontutorial= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.synergygb.pagoflash.view:id/tutorial_login']"));
		botontutorial.click();
		//verificar textos titulo tutorial
	    verificatxt(textoetiquetatutorial,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");  
		//verificar textos siguiente
	    verificatxt(textoetiquesiguiente,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/text_to_resume']"); 
        
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
 	    
		WebElement botonsiguiente= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.synergygb.pagoflash.view:id/to_resume']"));
		botonsiguiente.click();
		botontutorial.click();		
		WebElement botonback= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='android:id/up']"));
		botonback.click();
	}//fin verificartutorial
	
	@Test	
	public void A3loginCorrecto() throws InterruptedException, IOException {
		alogger.debug("inicio de A2loginCorrecto");
		//Thread.sleep(10000);
				
				//usuario  class android.widget.EditText  package com.synergygb.pagoflash.view  resource-id  com.synergygb.pagoflash.view:id/fragment_login_user_field  [204,657][586,725]
				//password class android.widget.EditText  package com.synergygb.pagoflash.view  resource-id  com.synergygb.pagoflash.view:id/fragment_login_password_field  [204,765][586,833]
				//ingresar class android.widget.ImageView package com.synergygb.pagoflash.view  resource-id com.synergygb.pagoflash.view:id/fragment_login_enter_button  [60,883][660,935]
				//piedepagina class android.widget.TextView package com.synergygb.pagoflash.view resource-id com.synergygb.pagoflash.view:id/copyright_text  [83,1118][636,1148]
				//version class android.widget.TextView package com.synergygb.pagoflash.view resource-id com.synergygb.pagoflash.view:id/version_text [14,1118][63,1148]
				//tutorial class android.widget.ImageView package com.synergygb.pagoflash.view resource-id com.synergygb.pagoflash.view:id/tutorial_login [147,350][173,380]
          		//texto  Todos los derechos reservados ©SYNERGY-GB,LLC 2015
				//V 1.0
				
				//driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.synergygb.pagoflash.view:id/fragment_login_user_field']")).clear();
				//Thread.sleep(5000);
				//driver.hideKeyboard();
				//driver.findElement(By.id("com.synergygb.pagoflash.view:id/fragment_login_password_field")).clear();
	
		
		
				WebElement usuario= driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.synergygb.pagoflash.view:id/fragment_login_user_field']"));
				usuario.sendKeys("usuario1");
				WebElement password= driver.findElement(By.id("com.synergygb.pagoflash.view:id/fragment_login_password_field"));
				password.sendKeys("123456");
				//Thread.sleep(5000);
				//driver.hideKeyboard();
				WebElement botoningresar= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.synergygb.pagoflash.view:id/fragment_login_enter_button']"));
				botoningresar.click();
				
				//captura de pantalla
				File scrfile  = ((TakesScreenshot)scrdriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrfile, new File(Screenshotpath+"Appiumtest.jpg"));	
				//Chequeo que entre al tutorial
			    verificatxt(textoetiquetatutorial,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']"); 

				
				  
				/* ESTO ES CON LAS ALERTAS
				public boolean isDialogPresent() {
					    UiObject alertDialog = new UiObject(new UiSelector().packageName("com.android.systemui"));
					    return alertDialog.exists();
					  }
					  
					  driver.findElement(By.name("OK")).click()
					  
					  To wait for the alert to appear:
					  wait.until(ExpectedConditions.alertIsPresent());
					  
					  
					  To wait for the alert to disappear:
					  wait.until(!ExpectedConditions.alertIsPresent());
					  
					  Appium uses UIAutomation's "defaultButton()" and "cancelButton()" method on alert dialogs to choose what to hit. accept == default, cancel == cancel.
					  
					  */
				}//fin de loging correcto
	
	//@Test
	public void A4verificartutorialinterno() throws InterruptedException {
		alogger.info("A4verificartutorialinterno");		
		//verificar textos titulo tutorial
		verificatxt(textoetiquetatutorial,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");
		//verificar textos siguiente
		verificatxt(textoetiquesiguiente,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/text_to_resume']");
        
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
	    driver.swipe(300, 240, 50, 240, 600); 
 
		WebElement menu= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='android:id/up']"));
		menu.click();
	    
	    //verifico los textos del menu
		verificatxt(cambiarfoto,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/change_photo']");		
		//verificatxt(nombreusuario,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/menu_user_name']");		
		verificatxt(pagarid,"//android.widget.ListView/android.widget.LinearLayout[@index='1']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt(registrarid,"//android.widget.ListView/android.widget.LinearLayout[@index='2']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt(configurarid,"//android.widget.ListView/android.widget.LinearLayout[@index='3']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt("TUTORIAL","//android.widget.ListView/android.widget.LinearLayout[@index='4']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt(cerrarsesion,"//android.widget.ListView/android.widget.LinearLayout[@index='5']/android.widget.LinearLayout/android.widget.TextView");
		
		menu.click();
		WebElement siguiente= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.synergygb.pagoflash.view:id/to_resume']"));
		siguiente.click();
		
        }//fin verificartutorial
	
	@Test
	public void A5verificarmenu() throws InterruptedException {
		alogger.info("A5verificarmenu");		
		WebElement menu= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='android:id/up']"));
		menu.click();
		
		
	    //verifico los textos del menu
		verificatxt(cambiarfoto,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/change_photo']");		
		//verificatxt(nombreusuario,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/menu_user_name']");		
		verificatxt(pagarid,"//android.widget.ListView/android.widget.LinearLayout[@index='1']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt(registrarid,"//android.widget.ListView/android.widget.LinearLayout[@index='2']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt(configurarid,"//android.widget.ListView/android.widget.LinearLayout[@index='3']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt("TUTORIAL","//android.widget.ListView/android.widget.LinearLayout[@index='4']/android.widget.LinearLayout/android.widget.TextView");
		verificatxt(cerrarsesion,"//android.widget.ListView/android.widget.LinearLayout[@index='5']/android.widget.LinearLayout/android.widget.TextView");
				
		/*
		//reviso navegacion toques
		driver.tap(1,100, 260,100);
		verificatxt(pagarid,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");
		menu.click();
		Thread.sleep(1500);
		driver.tap(1,100, 310,100);
		verificatxt (registrarid,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");
		menu.click();
		Thread.sleep(1500);
		driver.tap(1,100, 358,100);
		verificatxt(configurarid,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");		
		menu.click();
		Thread.sleep(1500);
		driver.tap(1,100, 404,100);
		verificatxt(textoetiquetatutorial,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");		
		driver.navigate().back();*/
		
		//reviso navegacion con clicks
		//menu.click();
		//Thread.sleep(2500);
		WebElement w2= driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[@index='1']"));
		w2.click();
		verificatxt(pagarid,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");			
		menu.click();	
		WebElement w3= driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[@index='2']"));
		w3.click();
		verificatxt (registrarid,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");
		menu.click();
		WebElement w4= driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[@index='3']"));
		w4.click();
		verificatxt(configurarid,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");
		menu.click();
		WebElement w5= driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[@index='4']"));
		w5.click();
		verificatxt(textoetiquetatutorial,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");
		driver.navigate().back();
		
		
		}//fin verificarmenu
	
	//@Test
	public void A6verificarresumenflash() throws InterruptedException {
		alogger.info("A6verificarresumenflash");		
	    //verifico los textos del menu
		verificatxt(resumenflash,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/action_bar_text']");		
		

        }//fin verificarmenu	
	
	
	//@Test
	public void zcerrarsesion() throws InterruptedException {
		alogger.info("cerrarcesion");		
	    //cierro sesion
		WebElement menu= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='android:id/up']"));
		menu.click();
		Thread.sleep(1500);
		driver.tap(1,100, 451,100);

		/*
		verificatxt(confirmacion,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/alert_dialog_header_confirmation']");	
		verificatxt(confirmacionsalir,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/alert_dialog_confirmation_textview']");
		verificatxt(aceptar,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/alert_dialog_confirmation_btn_yes']");	
		verificatxt(cancelar,"//android.widget.TextView[@resource-id='com.synergygb.pagoflash.view:id/alert_dialog_confirmation_btn_cancel']");	
		*/
		//wait.until(ExpectedConditions.alertIsPresent());
		
		Thread.sleep(1500);
		driver.tap(1,223, 318,100);
		menu.click();
		Thread.sleep(1500);
		driver.tap(1,100, 451,100);
		Thread.sleep(1500);
		driver.tap(1,97, 318,100);
		
		
		
		//WebElement cancel= driver.findElement(By.xpath("android.widget.Button[@resource-id='com.synergygb.pagoflash.view:id/alert_dialog_confirmation_btn_cancel']"));
		//cancel.click();
		//driver.findElement(By.name("OK")).click();


        }//fin verificarmenu	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@Test
	public void A4prueba() throws InterruptedException {
		alogger.info("A3");
		
		WebElement usuario= driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.synergygb.pagoflash.view:id/fragment_login_user_field']"));
		usuario.sendKeys("bla bla");
				
		
		WebElement password= driver.findElement(By.id("com.synergygb.pagoflash.view:id/fragment_login_password_field"));
		password.sendKeys("123456");
		
		WebElement botoningresar= driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.synergygb.pagoflash.view:id/fragment_login_enter_button']"));
		botoningresar.click();
		
		//reset del app
		//driver.resetApp();

		

		
		
			
		/*/ scroll to
		WebElement element = driver.findElement(By.name("Element Name"));
		HashMap<String, String> arguments = new HashMap<String, String>();
		arguments.put("element", element.getId());
		(JavascriptExecutor)driver.executeScript("mobile: scrollTo", arguments);
*/
		
		
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	  /*
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }
	*/
}




	
		
		
		
		


