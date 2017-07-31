package org.lastminute.flightsearch.test.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.flightsearch.domain.Airline;
import org.lastminute.flightsearch.domain.Airport;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;

public class AirlineTest {
	private static Airline airline;

	@Before
	public void setUp() throws Exception {
		airline = new Airline();
		airline.setIATACode("IB");
		airline.setInfantPrice(1.1);
		airline.setName("Iberia");
	}

	@Test
	public void testSaveCharacter() {
		String saveString = airline.save('%');
		assertEquals("IB%Iberia%1.1",saveString);
	}

	@Test
	public void testLoadStringCharacter() {
		String loadString = "LT%Lotto Airline%2.2";
		try {
			airline.load(loadString, '%');
		} catch (NotEnoughElements e) {
			fail("Not enought elements failure:"+e);
			e.printStackTrace();
		}
		assertEquals(airline.getIATACode(),"LT");
		assertEquals(airline.getName(),"Lotto Airline");
		assertEquals((double)airline.getInfantPrice(), 2.2,0);
	}

	@Test(expected = IATAIncorrectLength.class)
	public void testSetIATACode() throws IATAIncorrectLength {
			airline.setIATACode("TooLong");
			assertEquals("To",airline.getIATACode());
			airline.setIATACode(null);
	}

	@Test
	public void testGetIndexes() {
		assertNotNull(airline.getIndexes());
		assertEquals(1, airline.getIndexes().length);
		assertEquals("IB",airline.getIndexes()[0]);
	}

	@Test
	public void testToString() {
		assertEquals("IB Iberia 1.1€",airline.toString());
	}

	@Test
	public void testSave() {
		String saveString = airline.save();
		assertEquals("IB,Iberia,1.1",saveString);
	}

	@Test
	public void testLoadString() {
		String loadString = "LT,Lotto Airline,2.2";
		try {
			airline.load(loadString);
		} catch (NotEnoughElements e) {
			fail("Not enought elements failure:"+e);
			e.printStackTrace();
		}
		assertEquals(airline.getIATACode(),"LT");
		assertEquals(airline.getName(),"Lotto Airline");
		assertEquals((double)airline.getInfantPrice(), 2.2,0);
	}

}
