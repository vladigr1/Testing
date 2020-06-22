package propertyInjection_example;

import propertyInjection_example.PropertyInjection.LogAnalyzer;

public class MainPropertyInjection {
	public static void main(String[] args) {
		PropertyInjection pI = new PropertyInjection();
		LogAnalyzer logA = pI.new LogAnalyzer();		
		//String fileName = "k:\\test.docx";
		String fileName = "C:\\Users\\Leptop-Pc\\source\\eclipse-workspace\\DependedOnComponent\\files\\test.docx";
		String extension = ".docx";
		try {
			System.out.print(logA.IsValidLogFileName(fileName, extension));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
