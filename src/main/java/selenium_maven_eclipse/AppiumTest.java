package selenium_maven_eclipse;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
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


//para los time stamp
import java.util.Date;



//correr los casos en orden alfabetico
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppiumTest {
	static AppiumDriver driver;
	//logs de appium
	public static final Logger alogger = Logger.getLogger("AppiumTest");
	//ruta de los screenshots 
	//public String Screenshotpath = "C:\\Users\\Silfredo Mora\\workspace\\Capturas de pantalla y logs\\Banesco-movil\\";
	public String metodo = null;
	
	 //captura de pantalla
    String filetimestamp = new SimpleDateFormat("yyyyMMddhhmm ").format(new Date());
    MultiScreenShot mShot=new MultiScreenShot("C:\\Users\\Silfredo Mora\\workspace\\Capturas de pantalla y logs\\Banesco-movil\\",filetimestamp);
    
	//textos a verificar en strings
	
	String derechos = "CONDICIONES GENERALES DEL SERVICIO BANESCOMÓVIL PRIMERA: A los fines de una mejor comprensión de las presentes Condiciones Generales se establecen las siguientes definiciones, las cuales podrán ser utilizadas en singular o plural, mayúsculas o minúsculas, de acuerdo al contexto en el que se presenten:1. BANCO: se refiere a Banesco Banco Universal, C.A., plenamente identificado en el encabezado del presente documento.2. BANESCONLINE: Se refiere al Servicio de banca por internet mediante el cual los CLIENTES del BANCO pueden, a través de dispositivos electrónicos por intermedio de la Red Mundial de Información denominada Internet (World Wide Web), recibir información, realizar consultas y operaciones y enviar instrucciones y solicitudes, concernientes a las relaciones que mantuvieren con el BANCO, así como mantener, en general, comunicación por ese medio con el BANCO.3. CLIENTE: Se refiere a toda persona natural o jurídica que sea titular de una cuenta o cualquiera otro producto financiero en el BANCO y se afilie al SERVICIO.4. CUENTA: Se refiere a las cuentas financieras que mantenga el CLIENTE con el BANCO.5. IVR: Son las siglas en inglés de Robot de Voz Interactivo, y se refiere al sistema telefónico que es capaz de recibir una llamada e interactuar con el humano a través de grabaciones de voz y el reconocimiento de respuestas simples.6. TARJETA DE CRÉDITO: Son los plásticos personalizados e intransferibles, propiedad del BANCO y emitido por éste último a nombre del CLIENTE y que acredita una relación contractual entre el BANCO y el CLIENTE, en virtud del otorgamiento de un crédito a corto plazo o línea de crédito a favor de éste último, el cual podrá ser utilizado para la compra de bienes, servicios, cargos automáticos en cuenta u obtención de avances de dinero en efectivo, entre otros consumos realizados a través de los canales que disponga el BANCO para tal fin.   7. TRANSACCIONES FINANCIERAS: se refiere a todas aquellas operaciones que el CLIENTE podrá ejecutar a través del SERVICIO, tales como consultas, transferencias, pagos y/o retiros, con cargo a su CUENTA o TARJETA DE CRÉDITO, así como, cualquier otra transacción que el BANCO pudiera incorporar.SEGUNDA: El Servicio BANESCOMÓVIL (SERVICIO), es un servicio de pago móvil que tiene como objeto, la ejecución por parte del CLIENTE, de diversas transacciones financieras a través de: (i) la tecnología de telefonía móvil celular por intermedio de la Red Mundial de Información denominada Internet (World Wide Web) con o sin la instalación de un aplicativo en el dispositivo móvil; o, (ii) a través de mensajería de texto (SMS) desde un dispositivo móvil a través de una línea telefónica, contemplando el intercambio de mensajes de texto con la finalidad de solicitar y obtener información de sus productos financieros, así como, realizar transacciones financieras.TERCERA: El BANCO está de acuerdo en prestar y el CLIENTE en hacer uso del SERVICIO, para efectuar las transacciones financieras a través del dispositivo móvil, conforme a las Cláusulas de este Contrato. En virtud de lo anterior el CLIENTE deberá: (i) ser titular de CUENTAS y/o TARJETAS DE CRÉDITO en el BANCO; (ii) poseer un dispositivo móvil habilitado técnicamente para tener acceso al SERVICIO; y, (iii) cumplir con cualquier otro requisito que pudiera solicitar el BANCO para hacer uso del SERVICIO, todo de conformidad con lo establecido en las instrucciones que serán impartidas por el propio sistema. CUARTA: El CLIENTE podrá afiliarse al SERVICIO a través de los siguientes canales: (i) BANESCONLINE; (ii) Mensaje de Texto + IVR, así como cualquier otro canal que el BANCO pudiere habilitar para ello. Durante el proceso de afiliación el CLIENTE deberá aceptar las presentes Condiciones Generales, las cuales se encontrarán disponibles a través del propio sistema, así como en BANESCONLINE, en la página web del BANCO www.banesco.com, o a través de cualquier otro dominio que el BANCO disponga para tal fin, así como, en las oficinas del BANCO.QUINTA: El CLIENTE una vez afiliado al SERVICIO, podrá ejecutar las siguientes transacciones financieras, únicamente desde el número del dispositivo móvil previamente afiliado al SERVICIO: (i) Consulta de saldo en CUENTA; (ii) Consulta de últimos movimientos en CUENTA; (iii) Consulta de saldo de la Tarjeta de Crédito; (iv) Consulta de últimos movimientos de la Tarjeta de Crédito; (v) Consulta de saldo de servicios; (vi) Transferencia entre CUENTAS del mismo titular; (vii) Transferencia a CUENTAS de terceros previamente afiliados al SERVICIO; (viii) Pago de Tarjeta de Crédito; (xix) Pago de Tarjeta de Crédito de terceros previamente afiliados al SERVICIO; (xx) Pago de servicios; (xxi) Consulta Operaciones Cambiarias; (xxii) Retiros por Cajeros Automáticos (ATM), entre otras transacciones financieras que el BANCO pudiera incorporar a este SERVICIO, todo lo cual será debidamente informado a los CLIENTES a través del propio sistema, así como cualquier otro medio dispuesto por el BANCO para tales fines.SEXTA: El CLIENTE no podrá afiliar más de un número de dispositivo móvil al SERVICIO, en el entendido que, sólo podrá realizar transacciones financieras, desde el número del dispositivo móvil afiliado. SÉPTIMA: El CLIENTE podrá establecer los montos máximos diarios o mensuales de las transacciones financieras que ejecutará a través del SERVICIO de conformidad con lo dispuesto en la normativa aplicable, y en ningún caso dichos montos podrán ser superiores a los establecidos e informados por el BANCO.OCTAVA: Para el acceso o ejecución de las transacciones financieras, así como a determinados niveles o tipos de funciones, u operaciones, el sistema podrá requerir claves especiales. Las claves tendrán carácter personal, confidencial e intransferible, el BANCO no tendrá conocimiento alguno de las mismas, y su finalidad es garantizar el exclusivo acceso del CLIENTE y las personas por él autorizadas al sistema, en caso que resulte aplicable, siendo responsabilidad exclusiva de éste la guarda, cuido y custodia de sus claves, asumiendo el CLIENTE la obligación de mantener las mismas en estricta confidencialidad y reserva; y, en aquellos casos en los cuales voluntaria o involuntariamente considere que se ha violado la confidencialidad o reserva de las claves, deberá solicitar inmediatamente al BANCO el bloqueo de las mismas. El CLIENTE asumirá en forma exclusiva cualquier responsabilidad derivada o que pudiera derivarse directa o indirectamente de cambios en cualquiera de sus claves, así como del nivel de seguridad empleado por éste, tanto en la composición de las mismas, como en el manejo de sus claves para la realización de transacciones financiera. En virtud de lo establecido en la presente Cláusula, el BANCO no asumirá responsabilidad alguna por cualquier circunstancia derivada, directa o indirectamente, del conocimiento y eventual uso de cualesquiera de las claves del CLIENTE por parte de terceros, por cualquier motivo que ello ocurriere, asumiendo el CLIENTE cualquier responsabilidad al respecto. El CLIENTE se compromete hacer uso del SERVICIO con la debida diligencia, asumiendo los riesgos y responsabilidades de permitir a terceras personas la utilización o manipulación de los equipos telefónicos y sistemas a través de los cuales se acceda al SERVICIO, en consecuencia todas las consultas, transacciones financieras, instrucciones, solicitudes y comunicaciones enviadas a través del SERVICIO, debidamente realizadas a través del uso de las claves del CLIENTE, serán consideradas como emanadas de éste. NOVENA: El CLIENTE podrá limitar o suprimir su acceso a determinadas funciones u opciones del SERVICIO, en los casos en que ello fuere técnicamente permitido en función de las posibilidades del sistema y con las limitaciones que el BANCO tenga establecidas, siguiendo los procedimientos y normas que el BANCO señale a tales fines. El BANCO se reserva el derecho de limitar, restringir o suprimir el acceso del CLIENTE a determinadas funciones u opciones, todo lo cual será informado al CLIENTE a través del propio sistema.DÉCIMA: Los equipos telefónicos, sistemas, programas de navegación por Internet y cualquier otro programa requerido para poder disfrutar del SERVICIO, serán adquiridos y utilizados por el CLIENTE a su costo y riesgo, y deberán tener las características técnicas necesarias para permitir el acceso al SERVICIO. El BANCO no será responsable en ningún caso por el buen funcionamiento, idoneidad, capacidad y compatibilidad de los equipos telefónicos, sistemas y programas adquiridos o utilizados por el CLIENTE a los efectos de hacer uso del SERVICIO. DÉCIMA PRIMERA: El CLIENTE deberá ejecutar las transacciones financiera disponibles a través del SERVICIO de conformidad con lo establecido en las instrucciones contenidas en el sistema, en las presentes Condiciones Generales y en cualquier regulación aplicable a cada una de las operaciones que puedan ejecutarse a través del SERVICIO, en virtud de lo anterior, el BANCO queda exento de cualquier responsabilidad por el no procesamiento de cualesquier transacción financiera enviada a través del SERVICIO que no cumplan con las instrucciones impartidas.DÉCIMA SEGUNDA: En caso de ser necesario por razones de orden técnico, de seguridad, así como por mantenimiento del sistema, el BANCO se reserva el derecho de suspender temporalmente el SERVICIO. Asimismo, el BANCO no se hace responsable por la interrupción imprevista del SERVICIO, por dificultades en su funcionamiento, o por retardo en el tiempo de procesamiento de cualesquier transacción financiera causadas por fallas técnicas, fallas en el servicio eléctrico, en el servicio telefónico, congestionamiento en la Red, o por cualesquiera otras causas ajenas a la voluntad del BANCO.DÉCIMA TERCERA: Las tarifas y/o comisiones que se generen con motivo de la prestación del SERVICIO, así como, por las transacciones financieras que se realicen a través del mismo, serán establecidas por el BANCO de conformidad con la normativa legal vigente que regula la materia, y serán del conocimiento del CLIENTE a través de la información disponible en el propio sistema, así como, por cualquier otro medio que el BANCO determine a tales fines, de manera que permita al CLIENTE el conocimiento exhaustivo de las mismas. El BANCO queda autorizado para debitar o hacer debitar dichas tarifas y/o comisiones, en cualquier cuenta de depósito o inversión que el CLIENTE posea en el BANCO.DÉCIMA CUARTA: El valor del mensaje de texto genera un costo por parte del operador de telefonía móvil celular, a través del medio de pago escogido por el CLIENTE cuando contrató con éste. En virtud de lo anterior, el BANCO no tiene ninguna responsabilidad ni injerencia en la fijación del mismo. En tal sentido, el BANCO queda exonerado de cualquier tipo de responsabilidad derivada de tal circunstancia.DÉCIMA QUINTA: El BANCO podrá suspender en cualquier momento, la prestación del SERVICIO a aquellos CLIENTES que incumplan lo establecido en las presentes Condiciones Generales. Por su parte, el CLIENTE podrá requerir su desafiliación al SERVICIO cuando así lo decidiere, a cuyo efecto deberá manifestar por escrito al BANCO su decisión, quedando, no obstante, bajo la responsabilidad del CLIENTE, cualquier transacción financiera que se realice antes de su efectiva desafiliación al SERVICIO.DÉCIMA SEXTA: El CLIENTE es responsable tanto de  poseer activo el servicio de telefonía celular en el número indicado al momento de afiliarse al SERVICIO, así como de la custodia de su equipo móvil celular, para evitar que terceros no autorizados hagan uso indebido del SERVICIO. En este sentido, el CLIENTE es y será el único responsable por el resguardo del dispositivo móvil adscrito al SERVICIO, y por ende de la confidencialidad de la información que a través del mismo se reciba sobre las transacciones o cualquier otra información que constituya el objeto del SERVICIO. En caso de robo, hurto o extravío del dispositivo móvil cuyo número se encuentra afiliado al SERVICIO, el CLIENTE deberá notificarlo de manera inmediata a fin de proceder a la suspensión del SERVICIO, a través de los medios dispuestos por el BANCO para tal fin. En virtud de lo cual, el BANCO no asume ningún tipo de responsabilidad por el alcance que pudieran tener terceras personas a la información transmitida a través de este SERVICIO derivada del acceso al dispositivo móvil y del uso que hagan de la misma, así como, de la información que pudiera tener almacenada en dicho dispositivo. El CLIENTE se compromete actualizar la información necesaria en caso de efectuar algún cambio en el número telefónico afiliado al SERVICIO, así como cualquier información requerida para su uso.DÉCIMA SÉPTIMA: El BANCO no asumirá responsabilidad alguna por error en que incurriera el CLIENTE, al no indicar correctamente los datos requeridos para efectuar las transacciones financieras, en especial, no asumirá responsabilidad alguna si por esta circunstancia se imputara el pago o transferencia a una CUENTA o TARJETA DE CRÉDITO distinta a la que originalmente tenía previsto realizar la transacción. Asimismo, el BANCO no asumirá los costos adicionales en que incurriera el CLIENTE por el envió de los mensajes de texto con motivo de lo antes planteado.DÉCIMA OCTAVA: El BANCO, no concede ninguna licencia o autorización de uso de ninguna clase sobre sus derechos de propiedad intelectual o sobre cualquier otra propiedad o derecho relacionado con el SERVICIO.DÉCIMA NOVENA: El BANCO se reserva el derecho de modificar total o parcialmente las presentes Condiciones Generales, previa notificación a los CLIENTES con una antelación mínima de treinta (30) días continuos. Dentro del referido plazo el CLIENTE podrá rechazar, por escrito, las modificaciones incorporadas al presente documento por el BANCO, procediendo a su desafiliación automática.VIGÉSIMA: De conformidad con lo dispuesto en las \"Normas Relativas a la Protección de los Usuarios y Usuarias de los Servicios Financieros\" dictadas por la Superintendencia de las Instituciones del Sector Bancario, mediante Resolución N° 083.11 de fecha quince (15) de marzo de 2011, el BANCO hará entrega al CLIENTE con anterioridad de un (1) ejemplar de las presentes Condiciones Generales, en tal sentido, éste dispondrá del tiempo suficiente para examinar su contenido y comprender el exacto alcance, trascendencia y consecuencias jurídicas de todas y cada una de las Cláusulas que las conforman, las cuales aceptará sin reparo u objeción alguna, por constituir las mismas reflejo fiel y exacto de su voluntad.VIGÉSIMA PRIMERA: Para todos los efectos, derivados y consecuencias de éstas Condiciones Generales se elige como domicilio especial la ciudad del domicilio del CLIENTE, a la jurisdicción de cuyos Tribunales someten la resolución de las controversias que se pudiesen suscitar con ocasión del SERVICIO, sin perjuicio para el BANCO de ocurrir a otros Tribunales competentes de conformidad con la Ley.VIGÉSIMA SEGUNDA: Las presentes Condiciones Generales entran en vigencia a partir de la fecha de su autenticación, sustituyendo en todas y cada una de sus partes, a partir de esa fecha a las CONDICIONES GENERALES DEL SERVICIO BANESCOMÓVIL, contenidas en documento debidamente protocolizado ante el Registro Público del Segundo Circuito del Municipio Baruta Estado Miranda, en fecha veintiséis (26) de julio de 2010, bajo el N° 29, Tomo 11, Protocolo de Transcripción.Documento autenticado por ante la Notaría Tercera del Municipio Baruta del Estado Miranda, en fecha nueve (09) de septiembre de 2011, bajo el N° 44, Tomo 98, de los libros de autenticaciones llevados por dicha Notaría. ";
	String usuario = "pagomovi79";
	String pass = "0123456789";

	
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
	
	public void control(String cualmetodo){
		alogger.info("Entro en el metodo "+cualmetodo);
		metodo = null;
		metodo = cualmetodo;		
		}

	public void captura (String path) throws IOException{

		mShot.elementScreenShot(driver, driver.findElement(By.xpath(path)));
	
	}	
	
	public void captura () throws IOException{
		
	mShot.multiScreenShot(driver);
	
	
	}


	
	
	
	
	
	@BeforeClass
	  public static void setUp() throws MalformedURLException, InterruptedException, Exception {
        alogger.info("Entro en el setup");
    	// set up las capabilities del appium
		String apkpath = "C:\\Android\\banesco-movil-integrado-android-prod.apk";
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
		capabilities.setCapability ("deviceName", "Galaxy Nexus");
		capabilities.setCapability ("platformVersion", "4.3");
		//Huawei
		//capabilities.setCapability ("deviceName", "HUAWEI Y221-U03");
		//capabilities.setCapability ("platformVersion", "4.4.2");
		
        capabilities.setCapability ("app", app.getAbsolutePath());
        capabilities.setCapability("appium-version", "1.1");
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);        


		 

			/*
		    driver.swipe(349, 925, 349, 600, 500);       
		    driver.pinch(349, 925);      
		    driver.tap(1,520, 790,100);  
		    driver.hideKeyboard();
		    */
		            
    }//fin del setup

	@Test	
	public void A1aceptartestfairy() throws InterruptedException, IOException {
		control("A1aceptartestfairy");	
		
		WebElement botonsiguiente= driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']"));
		botonsiguiente.click();
		
		WebElement correosynergy= driver.findElement(By.xpath("//android.widget.TextView[@text='movilidadsynergy@gmail.com']"));
		correosynergy.click();
		captura ();
		    		
	}//fin A1aceptartestfairy
	
	@Test	
	public void A2verificartextoscontratos() throws InterruptedException, IOException {
		control("A2verificartextoscontratos");	
	
	    verificatxt("Contrato","//android.widget.TextView[@resource-id='android:id/alertTitle']" );
	    //verificar texto del contrato
	    verificatxt(derechos,"//android.widget.ScrollView/android.widget.TextView" );
	    driver.swipe(360, 1020, 360, 250, 300); 
		Thread.sleep(1000);
	    driver.swipe(360, 1020, 360, 250, 500); 
		Thread.sleep(1000);
	    driver.swipe(360, 1020, 360, 250, 800);
		Thread.sleep(1000);
	    driver.swipe(360, 1020, 360, 250, 500);
		Thread.sleep(1000);
	    driver.swipe(360, 1020, 360, 250, 400);
		Thread.sleep(1000);
	    driver.swipe(360, 1020, 360, 250, 500);
		Thread.sleep(1000);
	    driver.swipe(360, 1020, 360, 250, 300);
	    driver.swipe(360, 1020, 360, 250, 300);
	    driver.swipe(360, 1020, 360, 250, 300);
	    driver.swipe(360, 1020, 360, 250, 300);
	    driver.swipe(360, 1020, 360, 250, 300);

	    
	    captura ("//android.widget.Button[@resource-id='android:id/button1']");
		WebElement botonaceptar= driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']"));
		botonaceptar.click();
		      
	}//fin verificartextosloging
	
	
	@Test	
	public void A3inciarbanescomovil() throws InterruptedException, IOException {
		control("A3inciarbanescomovil");		
		WebElement botonbanescomovil= driver.findElement(By.xpath("//android.widget.GridView/android.widget.RelativeLayout[@index='0']/android.widget.LinearLayout[@index='1']"));
		botonbanescomovil.click();
		captura ();
  	    
	}//fin A3inciarbanescomovil
	
	@Test	
	public void A4verificartextosloging() throws InterruptedException, IOException {
		control("A4verificartextosloging");
				captura ();
				//Chequeo textos de pantallas
			    verificatxt("Ingresa tus datos","//android.widget.TextView[@resource-id='android:id/action_bar_title']"); 
			    verificatxt("Usuario:","//android.widget.TextView[@resource-id='com.synergygb.banesco.banescomovil:id/username_text']"); 
			    verificatxt("Clave:","//android.widget.TextView[@resource-id='com.synergygb.banesco.banescomovil:id/password_text']"); 
			    verificatxt("Aceptar","//android.widget.Button[@resource-id='com.synergygb.banesco.banescomovil:id/login_button']"); 
			    verificatxt("Limpiar","//android.widget.Button[@resource-id='com.synergygb.banesco.banescomovil:id/cancel_button']"); 
			    verificatxt("Banesco Banco Universal, C.A. RIF: J-07013380-5 © 2014.\nTodos los Derechos Powered by Synergy-GB","//android.widget.TextView[@resource-id='com.synergygb.banesco.banescomovil:id/about_text']"); 
			    
				WebElement username= driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.synergygb.banesco.banescomovil:id/txt_username']"));
			    username.sendKeys(usuario);
			    captura ();
			    driver.hideKeyboard();
			    Thread.sleep(1500);			    
				WebElement password= driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.synergygb.banesco.banescomovil:id/txt_password']"));
				password.sendKeys(pass);
				driver.hideKeyboard();
				
				WebElement botonbanescomovil= driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.synergygb.banesco.banescomovil:id/login_button']"));
				botonbanescomovil.click();
				captura ();
				

				}//fin de loging correcto
	
	
	

	
	
		
		
		
		
		
		
		
	
	
	
	//@Test
	public void zcerrarsesion() throws InterruptedException {
		control("zcerrarsesion");		
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
		

		
		
			

		
		
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	  /*
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }
	*/
}




	
	
	
	
	
	

	
		
		
		
		


