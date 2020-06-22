package constructorInjection_example;
import java.io.File;

//old
/*public class ConstructorInjection {
	public class LogAnalyzer
	{
		public boolean IsValidLogFileName(String fileName) throws Exception {
			if (! new File(fileName).exists()) {
			throw new Exception("No log file with that name exists");
			}
			if( !fileName.toLowerCase().endsWith(".docx")) {
				return false;
			}
			return true;			
		}		
	}	
}*/

public class ConstructorInjection { 
	
	public class LogAnalyzer{
		private IExtensionManager manager;
		
		public LogAnalyzer() {
			manager = new IsValidImp();
		}
		public LogAnalyzer(IExtensionManager manager) {
			this.manager = manager;
		}
		
		public boolean IsValidLogFileName(String fileName) throws Exception{
			return manager.isValid(fileName);
		}
	}
	
	public interface IExtensionManager{
		boolean isValid(String fileName) throws Exception;
	}
	
	public class IsValidImp implements IExtensionManager{
		@Override
		public boolean isValid(String fileName) throws Exception {
			if (! new File(fileName).exists()) {
			throw new Exception("No log file with that name exists");
			}
			if( !fileName.toLowerCase().endsWith(".docx")) {
				return false;
			}
			return true;			
		}		
	}
	
	public class StubExtensionManager implements IExtensionManager{
		@Override
		public boolean isValid(String fileName) throws Exception {
			// TODO Auto-generated method stub
			return true;
		}
	}
}
