
	package selenium_maven_eclipse;

	import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.junit.FormatterElement;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTask;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.junit.runner.JUnitCore;

	import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

	public class Tester {

		
	    //para el timestamp si lo queremos
	    public static String filetimestamp = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
	    static String ruta = "Resultados de pruebas"+File.separator+"PayGorilla"+ File.separator;

		//este es el main
		public static void main(String[] args) throws Exception{ 
			
			File resultadosDir = new File("Resultados de pruebas");		
			resultadosDir.mkdir();
			

			//pruebas silfredo
			Class [] clases ={Setup.class, PayGorilla.class};
			
			//pruebas stefano
			//Class [] clases ={PruebasOoflow.class};
			
			for( Class claseactual : clases )
			{
				Project project = new Project();
				JUnitTask task = new JUnitTask();
				project.setProperty("java.io.tmpdir","."); //set temporary directory
				task.setProject(project);
				JUnitTask.SummaryAttribute sa = new JUnitTask.SummaryAttribute();
				sa.setValue("withOutAndErr");
				task.setFork(false);
				task.setPrintsummary(sa);
				FormatterElement formater = new FormatterElement();         
				FormatterElement.TypeAttribute type = new FormatterElement.TypeAttribute();
				type.setValue("xml");
				formater.setType(type);
				task.addFormatter(formater);
				JUnitTest test = new JUnitTest(claseactual.getName());
				test.setTodir(resultadosDir); // set Location for your report
				task.addTest(test);         
				task.execute();
						
				
			}
			

			

			
			
			

		
			
			
					
		} 
























































}//cierre de la clase
