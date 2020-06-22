package DOCTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructorInjection_example.ConstructorInjection;
import mock_example.Mock_Example;
import propertyInjection_example.PropertyInjection;

class DOCTest {
	private String fileName;
	private String startFileName;
	private String extName;
	private ConstructorInjection con;
	private PropertyInjection pro;
	private Mock_Example mok;
	
	@BeforeEach
	void setUp() throws Exception {
		fileName  = "C:\\Users\\Leptop-Pc\\source\\eclipse-workspace\\DependedOnComponent\\files\\test.docx";
		extName = ".docx";
		con = new ConstructorInjection();
		pro = new PropertyInjection();
		mok = new Mock_Example();
	}

	@Test
	void ConstructorIsValidLogFileNameTest() throws Exception {
		ConstructorInjection.LogAnalyzer logA = con.new  LogAnalyzer(con.new  StubExtensionManager());
		assertTrue(logA.IsValidLogFileName(fileName));
		assertTrue(logA.IsValidLogFileName(""));
	}
	@Test
	void PropertyIsValidLogFileNameTest() throws Exception {
		PropertyInjection.LogAnalyzer logA = pro.new  LogAnalyzer();
		logA.setManager(pro.new StubExtensionManager());
		assertFalse(logA.IsValidLogFileName(fileName, extName));
		assertFalse(logA.IsValidLogFileName("", extName));
		assertFalse(logA.IsValidLogFileName(fileName, ""));
	}
	@Test
	void Mock_ExampleTest() throws Exception{
		Mock_Example.MockWebService mws =  mok.new MockWebService();
		Mock_Example.LogAnalyzer logA = mok.new LogAnalyzer(mws);
		logA.Analyze(fileName);
		String expected = "File name is OK: " + fileName;
		//System.out.println(mws.LastError);
		assertEquals(expected, mws.LastError);
		
	}
}
