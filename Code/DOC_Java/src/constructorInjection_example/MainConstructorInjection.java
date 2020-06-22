package constructorInjection_example;

import constructorInjection_example.ConstructorInjection.LogAnalyzer;

public class MainConstructorInjection {
	private static String fileName = "C:\\Users\\Leptop-Pc\\source\\eclipse-workspace\\DependedOnComponent\\files\\test.docx";
	public static void main(String[] args) throws Exception {
		ConstructorInjection cIE = new ConstructorInjection();	
		LogAnalyzer logA = cIE.new LogAnalyzer();
		System.out.print(logA.IsValidLogFileName(fileName));

	}

}
