package selenium_maven_eclipse;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class StandarOoflow extends PruebasOoflow {


	
	 
         

     

     

     
     
      

      
      
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

      
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
