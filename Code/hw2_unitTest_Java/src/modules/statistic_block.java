package modules;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

import interfaces.IConnection;
import interfaces.ISpearmansCorrelation;

public class statistic_block {
	public static double Threshold = 0.8;

	// the default construct purpose is to keep the functionality of the code the
	// same
	public statistic_block() {
		// anonymous class for ISpearmansCorrelation
		isc = new ISpearmansCorrelation() {
			SpearmansCorrelation sc = new SpearmansCorrelation();

			@Override
			public double correlation(double[] d1, double[] d2) {
				return sc.correlation(d1, d2);
			}

		};
		// anonymous class for IConnection
		iconn = new IConnection() {
			private Connection conn;
			
			{
				try {
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				} catch (Exception ex) {
					/* handle the error */}

				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST", "root",
							"1234");
					System.out.println("SQL connection succeed");
				} catch (SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			}

			@Override
			public double[] takeData(String ID, int year) {
				double d[] = new double[20];
				try {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT statistics.feature FROM test.statistics WHERE ID= \"" + ID
							+ "\" AND year=" + year + ";");
					for (int i = 0; i < 20; i++) {
						rs.next();
						d[i] = rs.getFloat(1);
					}
					;
					rs.close();

				} catch (SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
				return d;
			}
		};
	}
	
	//custructor Injection
	public statistic_block(IConnection iconn,ISpearmansCorrelation isc) {
		this.iconn = iconn;
		this.isc = isc;
	}
	

	/*
	 * old: public static Connection conn;
	 */
	private IConnection iconn;
	private double[] d1, d2;

	private ISpearmansCorrelation isc;

	public static void main(String[] args) {

		/* old: 
		 * try { Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); } catch
		 * (Exception ex) { handle the error}
		 * 
		 * try {
		 * conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST",
		 * "root","1234"); System.out.println("SQL connection succeed"); double
		 * res=safeValue("CompanyX", 2020);
		 */
		statistic_block sb = new statistic_block();
		double res = sb.safeValue("CompanyX", 2020);
		System.out.println("Safe value for CompanyX is " + res);

		/*old:
		 * }catch(
		 * 
		 * SQLException ex) { System.out.println("SQLException: " + ex.getMessage()); }
		 */
	}

	/*
	 * we must inject to the class that why we work with static method public static
	 * double safeValue(String ID, int year)
	 */
	public double safeValue(String ID, int year) {
		/*old:
		 * d1 = takeData(ID, year);
		 * d2 = takeData(ID, year - 1);
		 * old: SpearmansCorrelation sc=new SpearmansCorrelation(); double
		 * res=sc.correlation(d1, d2);
		 */
		d1 = iconn.takeData(ID, year);
		d2 = iconn.takeData(ID, year - 1);
		double res = isc.correlation(d1, d2);
		Arrays.sort(d1);

		if (res < Threshold) {
			return d1[19] - (std(d1) + std(d2)) / 2;
		} else {
			return d1[19] - std(d1);
		}
	}

	public static double std(double numArray[]) {
		double sum = 0.0, standardDeviation = 0.0;
		int length = numArray.length;

		for (double num : numArray) {
			sum += num;
		}

		double mean = sum / length;

		for (double num : numArray) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation / length);
	}

//old:
//	public static double[] takeData(String ID, int year)
//	{
//		double d[]=new double[20];
//		try 
//		{   
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT statistics.feature FROM test.statistics WHERE ID= \""+ ID +"\" AND year="+ year +";");
//	 		for (int i=0; i<20; i++)
//	 		{	rs.next();
//	 		    d[i]=rs.getFloat(1);
//	 		};	 			 
//			rs.close();			
//		} catch (SQLException e) {e.printStackTrace();}
//		return d;
//	}

}
