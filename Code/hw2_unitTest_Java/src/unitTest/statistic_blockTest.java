package unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.IConnection;
import interfaces.ISpearmansCorrelation;
import modules.statistic_block;

class statistic_blockTest {
	private double[] stubResult_TakeData20;
	private double[] stubResult_TakeData19;
	private double stubResult_correlation;
	private statistic_block underTest; 
	int year = 2020;	//cur year and year -1 = 2019
	
	
	public class StubIConnection implements IConnection{
		@Override
		public double[] takeData(String ID, int year) {
			// TODO Auto-generated method stub
			if(year == 2020) {
				return stubResult_TakeData20;
			}
			return stubResult_TakeData19;	//all other years (for 2019)
		}
	}
	
	public class StubISpearmansCorrelation implements ISpearmansCorrelation{

		@Override
		public double correlation(double[] d1, double[] d2) {
			// TODO Auto-generated method stub
			return stubResult_correlation;
		}
		
	}
	@BeforeEach
	void setUp() throws Exception {
		underTest = new statistic_block(new StubIConnection(), new StubISpearmansCorrelation());
	}
	
	//Possible Test:
	//correlation>Threshold
	//correlation<Threshold
	//negative values
	//array size less 20
	//array size more 20
	//zero array
	
	@Test
	void testSafeValueCorrHigherThreshold() {
		//correlation>Threshold
		stubResult_TakeData20 = new double[20];
		Arrays.fill(stubResult_TakeData20, 2);
		stubResult_TakeData19 = new double[20];
		Arrays.fill(stubResult_TakeData19, 1);
		stubResult_correlation = statistic_block.Threshold +0.1;
		double expected = 2 - 
				( statistic_block.std(stubResult_TakeData19)
						+statistic_block.std(stubResult_TakeData20) )/2;
		double actual = underTest.safeValue("1", year);
		assertEquals(expected, actual);
	}

}
