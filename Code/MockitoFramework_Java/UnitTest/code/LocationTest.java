package code;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class LocationTest {
	@Mock
	LocationService service;

	@BeforeEach
	void setUp() throws Exception {
		service = Mockito.mock(LocationService.class);
	}

	@Test
	void testLocate() {
		// Standard test
		Mockito.when(service.geoLocate(Mockito.any((LocationPoint.class)))).thenReturn(new LocationPoint(1, 1));
		Location a1 = new Location(service);
		LocationPoint expected1 = new LocationPoint(1, 1);
		LocationPoint result1 = a1.locate(1, 3); // will result 
		assertTrue(expected1.getX() == result1.getX());
		assertTrue(expected1.getY() == result1.getY());
		// cant test diffrently because it will always return this
		// like -1 -1 wanted to try verifiy
		expected1 = new LocationPoint(1, 1);
		result1 = a1.locate(-1, 3); // will result 
		assertTrue(expected1.getX() == result1.getX());
		assertTrue(expected1.getY() == result1.getY());
		//call time
		verify(service, times(2)).geoLocate(Mockito.any((LocationPoint.class)));
	}

	@Test
	void testPrintCalculationStatus() {
		Location underTest = new Location(service);
		Mockito.when(service.printStatus( Mockito.any((LocationPoint.class)) )).thenReturn("OK");
		//Standard test 
		try {
			underTest.printCalculationStatus(new LocationPoint(1,1) );
			}catch(NullPointerException e){
		fail();
	}System.out.println("Hey");
	// no point test
	Mockito.when(service.printStatus(Mockito.any((null)))).thenReturn("No point");try
	{
		underTest.printCalculationStatus(null);
		fail();
	}catch(
	NullPointerException e)
	{
		assertTrue(true);
	}
}

}
