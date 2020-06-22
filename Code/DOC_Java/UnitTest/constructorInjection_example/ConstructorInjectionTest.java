package constructorInjection_example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructorInjection_example.ConstructorInjection.IExtensionManager;
import constructorInjection_example.ConstructorInjection.LogAnalyzer;
import constructorInjection_example.ConstructorInjection.StubExtensionManager;
import mock_example.Mock_Example;
import propertyInjection_example.PropertyInjection;

class ConstructorInjectionTest {
	public class StubExtensionManager implements IExtensionManager{
		@Override
		public boolean isValid(String fileName) throws Exception {
			// TODO Auto-generated method stub
			return condtion;
		}
	}
	private String fileName;
	private String startFileName;
	private String extName;
	private ConstructorInjection con;
	public static boolean condtion;
	@BeforeEach
	void setUp() throws Exception {
		fileName  = "C:\\Users\\Leptop-Pc\\source\\eclipse-workspace\\DependedOnComponent\\files\\test.docx";
		extName = ".docx";
		con = new ConstructorInjection();
	}

	@Test
	void ConstructorIsValidLogFileNameTest() throws Exception {
		ConstructorInjection.LogAnalyzer logA = con.new  LogAnalyzer(new StubExtensionManager());
		condtion = true;
		assertTrue(logA.IsValidLogFileName(fileName));
		condtion = false;
		assertFalse(logA.IsValidLogFileName(fileName));
	}

}
