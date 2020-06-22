package mock_example;

import java.io.File;

public class Mock_Example {
	public class LogAnalyzer
	{		
		private IWebService service; 
		/* WebService service = new WebService(); */
		public LogAnalyzer(IWebService service) {
			this.service = service;
		}
		public void Analyze(String fileName) throws Exception {
			/*if (new File(fileName).length() < 8) {*/
			if (fileName.length() < 8) {
				service.LogError("File name too short: "+fileName);
			}  else {
				service.LogError("File name is OK: "+fileName);
			}
		}		
	}
	
	public interface IWebService{
		public void LogError(String message);
	}
	
	/*
	 * public class WebService{ public void LogError(String message) {
	 * System.out.print(message); } }
	 */
	
	public class MockWebService implements IWebService{
		public String LastError;
		@Override
		public void LogError(String message) {
			LastError = message;
		}
		
	}
}
