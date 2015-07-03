package selenium_maven_eclipse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class MultiScreenShot
{
	
 //el conteo de los screenshots para ponerselos en el nombre
 int screenShotNumber;

 //el conteo de los screenshots para ponerselos en el nombre en elementos
 int elementPart;

 //path de las carpetas de los screenshot 
 String screenShotPath=null;
 
 //path de las carpetas de las capturas parciales
 String elementPath=null;
 
 //el path screen shot mas nombre del archivo
 String screenShotFileName=null;

 //element file name
 String elementFileName=null;

 //nombre del metodo
 public static String metodo = "Elemento ";
 

 public MultiScreenShot(String path,String className)
 {

 //path para las capturas de elementos
 elementPath=path+className+"Elementos" + File.separator;
 
 //path para las capturas de pantallas completas
 screenShotPath=path+className+"Pantallas" + File.separator;
 

 //ruta y nombre de los archivos elementos
 elementFileName=elementPath+"Elemento ";


 //ruta y nombre de los archivos de capturas
 screenShotFileName = screenShotPath+"Capuras ";


 }

 //metodo para el screenshot de toda la pantalla
 public void multiScreenShot(WebDriver driver) throws IOException
 {
 //contador de numero de screenshot para el nombre
 screenShotNumber++;

 //captura 
 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

 //guardar en archivo
 FileUtils.copyFile(scrFile, new File(screenShotFileName+screenShotNumber+".jpg"));
 
 }

 //metodo para guardar el elemento
 public void elementScreenShot( WebDriver driver,WebElement element) throws IOException
 {
 //contador para los nombres
 elementPart++;

 //tomar la captura
 File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 //localizacion del elemento
 Point point = element.getLocation();

 //alto del elemento
 int width = element.getSize().getWidth();

 //ancho del elemento
 int height = element.getSize().getHeight();

 //leer la imagen
 BufferedImage img = ImageIO.read(screen);

 //cortar la imagen
 BufferedImage dest = img.getSubimage(point.getX(), point.getY(), width, height);


 ImageIO.write(dest, "png", screen);

 //guardar imagen cortada
 FileUtils.copyFile(screen, new File(elementFileName+elementPart+".png"));


 }

 //minimizar la pantalla
 public void minimize() throws AWTException
 {

 Robot rr=new Robot();

 //press the robot key with help of robot class
 rr.keyPress(KeyEvent.VK_WINDOWS);

 //press down arrow key
 rr.keyPress(KeyEvent.VK_DOWN);

 //realease the down arrow key
 rr.keyRelease(KeyEvent.VK_DOWN);

 //release the windows key
 rr.keyRelease(KeyEvent.VK_WINDOWS);
 }


}


