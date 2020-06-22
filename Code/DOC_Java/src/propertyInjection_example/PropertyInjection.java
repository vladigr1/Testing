package propertyInjection_example;

import java.io.File;

public class PropertyInjection {
	/*
	 * public class LogAnalyzer { public boolean IsValidLogFileName(String fileName,
	 * String extName) throws Exception { if (! new File(fileName+extName).exists())
	 * { //it was (1) throw new Exception("No log file with that name exists"); }
	 * //old
	 * 
	 * if( !fileName.toLowerCase().endsWith(".pdf")) { return false; }
	 * 
	 * return true; } }
	 */
	
	public class LogAnalyzer {
		private IExtensionManager manager;
		public LogAnalyzer() {
			manager = new isValidImp();
		}
		public boolean IsValidLogFileName(String fileName, String extName) throws Exception {
			return manager.isValid(fileName, extName);
		}
		public IExtensionManager getManager() {
			return manager;
		}
		public void setManager(IExtensionManager manager) {
			this.manager = manager;
		}
		
	}
	
	public interface IExtensionManager{
		boolean isValid(String fileName, String extName) throws Exception;
	}
	
	public class isValidImp implements IExtensionManager{
		@Override
		public boolean isValid(String fileName, String extName) throws Exception {
			if (!new File(fileName).exists()) { // it was (1)
				throw new Exception("No log file with that name exists");
			}
			if( !fileName.toLowerCase().endsWith(extName)) { return false; }
			return true;
		}
	}
	
	public class StubExtensionManager implements IExtensionManager{
		@Override
		public boolean isValid(String fileName, String extName) throws Exception {
			// TODO Auto-generated method stub
			return false;
		}
	}

}
