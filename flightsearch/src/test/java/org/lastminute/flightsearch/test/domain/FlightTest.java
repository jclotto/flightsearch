package org.lastminute.flightsearch.test.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.flightsearch.domain.Flight;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;

public class FlightTest {

	private static Flight flight;

	@Before
	public void setUp() throws Exception {
		flight = new Flight();
		flight.setFlightCode("IB1234");
		flight.setBasedPrice(1.1);
		flight.setOrigin("MAD");
		flight.setDestination("BCN");
	}

	@Test
	public void testSaveCharacter() {
		String saveString = flight.save('%');
		assertEquals("MAD%BCN%IB1234%1.1",saveString);
	}

	@Test
	public void testLoadStringCharacter() {
		String loadString = "BCN%MAD%AC2345%2.2";
		try {
			flight.load(loadString, '%');
		} catch (NotEnoughElements e) {
			fail("Not enought elements failure:"+e);
			e.printStackTrace();
		}
		assertEquals(flight.getOrigin(),"BCN");
		assertEquals(flight.getDestination(),"MAD");
		assertEquals(flight.getFlightCode(),"AC2345");
		assertEquals((double)flight.getBasedPrice(), 2.2,0);
	}

	@Test(expected = IATAIncorrectLength.class)
	public void testSetFlightCode() throws IATAIncorrectLength {
			flight.setFlightCode("TooLong");
			assertEquals("TooLon",flight.getFlightCode());
			flight.setFlightCode("AA");
	}

	@Test
	public void testGetIndexes() {
		assertNotNull(flight.getIndexes());
		assertEquals(1, flight.getIndexes().length);
		assertEquals("MADBCN",flight.getIndexes()[0]);
	}

	@Test
	public void testToString() {
		assertEquals("MAD BCN IB1234 1.1",flight.toString());
	}

	@Test
	public void testSave() {
		String saveString = flight.save();
		assertEquals("MAD,BCN,IB1234,1.1",saveString);
	}

	@Test
	public void testLoadString() {
		String loadString = "BCN,MAD,AC2345,2.2";
		try {
			flight.load(loadString);
		} catch (NotEnoughElements e) {
			fail("Not enought elements failure:"+e);
			e.printStackTrace();
		}
		assertEquals(flight.getFlightCode(),"AC2345");
		assertEquals(flight.getOrigin(),"BCN");
		assertEquals(flight.getDestination(),"MAD");
		assertEquals((double)flight.getBasedPrice(), 2.2,0);
	}


}
