package selenium_maven_eclipse;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;








import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;





import javax.mail.Flags;
import javax.mail.search.SubjectTerm;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebaStefano extends PruebasOoflow{

	String newemail = "jondoeqa@gmail.com";
	String passemail = "synergy1234";
	String passooflow = "clave1234";
	
	
	 @Test    
     public void A1verificarloginincorrecto()throws Exception{
   	 control("A1verificarloginincorrecto");
   	 verificaurl(Textos.loginURL);
                 
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
   	 verificatxt(Textos.termsandconditions, By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/label/span"));
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
   	 //acepto terminos
   	 driver.findElement(By.cssSelector("span.text-muted.text-small")).click();
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
   	 
   	 //caso correo ya registrado con sin aceptar terminos
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys("silfredomora@hotmail.com");
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).sendKeys("synergy1234");

   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
   	 estanopresente(By.xpath("//div[@class='form-group has-error']"));
   	 //ya registrado   	 
   	 verificatxt(Textos.userexists, By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));
   	 
   	  	 
   	 //regristro nuevo sin los terminos y luego correcto
     driver.findElement(By.cssSelector("span.text-muted.text-small")).click();
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
   	 //driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys(Tester.filetimestamp+RandomStringGenerator.generateRandomString(5,RandomStringGenerator.Mode.ALPHANUMERIC)+"@dominio.com");
   	 //driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).sendKeys(RandomStringGenerator.generateRandomString(10,RandomStringGenerator.Mode.ALPHANUMERIC));
 	 //cuando regristremos siempre la misma cuenta
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys(newemail);
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).sendKeys(passooflow);
   	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
   	 estanopresente(By.xpath("//div[@class='form-group has-error']"));
   	 verificatxt(Textos.errorsingup, By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));
   	 driver.findElement(By.cssSelector("span.text-muted.text-small")).click();
   	driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
   	Thread.sleep(500);
   	verificatxt(Textos.registroexitoso, By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));
   	 driver.findElement(By.xpath("//a[contains(text(),'Log in now')]")).click();
   	 Thread.sleep(500);
   	 verificaurl(Textos.loginURL);
   	 
     }
	
	

	  
    
    @Test   
    public void A4login () throws Exception{
  	 control("B2login");    	 
  		        
	    	driver.findElement(By.xpath("//input[@type='username']")).sendKeys(newemail);
	        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passooflow);
	        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	        Thread.sleep(1000);
	        verificaurl(Textos.dashboardURL);
	 	 	  	 
    }
	
	
	
	
	
	

}
