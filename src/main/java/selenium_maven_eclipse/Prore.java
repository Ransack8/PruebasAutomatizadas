package selenium_maven_eclipse;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.junit.FormatterElement;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTask;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import java.io.File;

public class Prore {

	static String ruta = "Resultados de pruebas"+File.separator+"Prore"+ File.separator;

	//este es el main
	public static void main(String[] args) throws Exception{ 
		final File reportDir = new File(".");
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
		JUnitTest test = new JUnitTest(PruebasProre.class.getName());// set Test.class.getname() 
		test.setTodir(reportDir); // set Location for your report
		task.addTest(test);         
		task.execute();
				

		//junit.run(PruebasProre.class);
		//Result result = JUnitCore.runClasses(PruebasProre.class);

	} 

















































}//cierre de la clase
