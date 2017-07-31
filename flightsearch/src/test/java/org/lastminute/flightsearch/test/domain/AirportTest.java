package org.lastminute.flightsearch.test.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lastminute.flightsearch.domain.Airport;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;

public class AirportTest {
	private static Airport airport;

	@Before
	public void setUp() throws Exception {
		airport = new Airport();
		airport.setIATACode("MAD");
		airport.setName("Madrid Barajas");
	}

	@Test(expected = IATAIncorrectLength.class)
	public void testSetIATACode() throws IATAIncorrectLength {
			airport.setIATACode("TooLong");
			assertEquals("Too",airport.getIATACode());
			airport.setIATACode("AA");
	}

	@Test
	public void testSaveCharacter() {
		String saveString = airport.save('%');
		assertEquals(saveString,"MAD%Madrid Barajas");
	}

	@Test
	public void testLoadStringCharacter() {
		String loadString = "BCN%Barcelona";
		try {
			airport.load(loadString, '%');
		} catch (NotEnoughElements e) {
			fail("Not enought elements failure:"+e);
			e.printStackTrace();
		}
		assertEquals(airport.getIATACode(),"BCN");
		assertEquals(airport.getName(),"Barcelona");
		
		
	}

	@Test
	public void testGetIndexes() {
		assertNotNull(airport.getIndexes());
		assertEquals(2, airport.getIndexes().length);
		assertEquals("MAD",airport.getIndexes()[0]);
		assertEquals("Madrid Barajas",airport.getIndexes()[1]);
	}

	@Test
	public void testToString() {
		assertEquals("MAD Madrid Barajas",airport.toString());
	}

	@Test
	public void testSave() {
		String saveString = airport.save(',');
		assertEquals(saveString,"MAD,Madrid Barajas");
	}

	@Test
	public void testLoadString() {
		String loadString = "BCN,Barcelona";
		try {
			airport.load(loadString);
		} catch (NotEnoughElements e) {
			fail("Not enought elements failure:"+e);
			e.printStackTrace();
		}
		assertEquals(airport.getIATACode(),"BCN");
		assertEquals(airport.getName(),"Barcelona");
	}

}
