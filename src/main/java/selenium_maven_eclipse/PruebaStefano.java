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

import org.junit.Test;
import org.openqa.selenium.By;



public class PruebaStefano extends PruebasOoflow{

	String newemail = "jondoeqa@gmail.com";
	String passemail = "synergy1234";
	String passooflow = "clave1234";
	
	

	
	

    //@Test    
    public void B1signup()throws Exception{
  	 control("B1signup");     	 
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
  	 driver.findElement(By.xpath("//a[contains(@href, '#/pages/signup')]")).click();
  	 Thread.sleep(500);
  	 
  	 
  	 //regristro correo nuevo
  	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).clear();
  	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).clear();
  	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div/input")).sendKeys("newemail@dd.com");
  	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[2]/input")).sendKeys("passooflow");
  	 driver.findElement(By.xpath("//section[@id='content']/div/div[2]/div/div/section[2]/form/div[3]/a")).click();
  	 estanopresente(By.xpath("//div[@class='form-group has-error']"));
  	 verificatxt(Textos.registroexitoso, By.xpath("//section[@id='content']/div/div[2]/div/div/section/div"));  	
  	 driver.findElement(By.xpath("//a[contains(text(),'Log in now')]")).click();
  	 
  	 
    }
	
    
	  //@Test    
      public void B3verificarloginincorrecto()throws Exception{
    	 control("B3verificarloginincorrecto");
    	 verificaurl(Textos.loginURL);
       	 verificatxt(Textos.forgotpassword,"//section[@id='content']/div/div[2]/div/div/section[2]/p/a");
       	 verificatxt(Textos.donthaveaccount,"//section[@id='content']/div/div[2]/div/div/section[2]/p[2]");            
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
	    public void B4activateaccout () throws Exception{
	  	 control("B4Activateaccout");    	 
	  	 
	  	 
	  	 //defino protocolo
	  	Properties props = System.getProperties();
	  	props.setProperty("mail.store.protocol", "imaps");
	  	 
	  	 //defino sesion
	  	//Session session = Session.getInstance(props, null);
	  	Session session = Session.getInstance(props);
	  	
	  	//acceso email por store class
	  	Store store = session.getStore();
	  	store.connect("imap.gmail.com", newemail, passemail);
	  	Folder inbox = store.getFolder("INBOX");
	  	inbox.open(Folder.READ_ONLY);
	  	 	           
            
	  	 
            System.out.println("Total Message:" + inbox.getMessageCount());
            System.out.println("Unread Message:"+ inbox.getUnreadMessageCount());
            
            Message[] messages = null;
            boolean isMailFound = false;
            Message mailFromGod= null;

            //Search correo de ooflow
            for (int i = 0; i < 5; i++) {
                messages = inbox.search(new SubjectTerm("Confirm your account on ooflow"),inbox.getMessages());
                //Wait for 10 seconds
                if (messages.length == 0) {
                    Thread.sleep(10000);
                }
            }

            //Search for unread mail from God
            //This is to avoid using the mail for which 
            //Registration is already done
            for (Message mail : messages) {
                if (!mail.isSet(Flags.Flag.SEEN)) {
                    mailFromGod = mail;
                    System.out.println("Message Count is: "+ mailFromGod.getMessageNumber());
                    isMailFound = true;
                }
            }

            //Test fails if no unread mail was found from ooflow
            if (!isMailFound) {
                throw new Exception(
                        "No se encontro email de regristro sin leer");
            
            //Read the content of mail and launch registration URL                
            } else {
                String line;
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                System.out.println(buffer);

                //Your logic to split the message and get the Registration URL goes here
                String registrationURL = buffer.toString().split("&amp;gt;https://ooflow.herokuapp.com/#/pages/confirm?")[0].split("href=")[1];
                System.out.println(registrationURL);                            
            }
    

	  	 

	  	 
	  	 
	  	 
	  	 
	  	 
		 	 	  	 
	    }

	  
    
    @Test   
    public void B2login () throws Exception{
  	 control("B2login");    	 
  		        
	    	driver.findElement(By.xpath("//input[@type='username']")).sendKeys(newemail);
	        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passooflow);
	        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	        Thread.sleep(1000);
	        verificaurl(Textos.dashboardURL);
	 	 	  	 
    }
	
	
	
	
	
	

}
