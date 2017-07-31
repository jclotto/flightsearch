package org.lastminute.flightsearch.test.storage;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.flightsearch.domain.Airport;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.storage.MultipleIndexesMemoryTable;
import org.lastminute.flightsearch.storage.NotFound;

public class MultipleIndexesMemoryTableTest {
	
	private static MultipleIndexesMemoryTable<Airport> table;

	@Before
	public void setUp() throws Exception {
		table = new MultipleIndexesMemoryTable<Airport>();
		Airport madrid = new Airport();
		madrid.load("MAD,Madrid");
		table.insert(madrid);
		Airport barcelona = new Airport();
		barcelona.load("BCN,Barcelona");
		table.insert(barcelona);
	}
	
	@Test
	public void testDump() {
		Collection<Airport> elements = table.dump();
		assertEquals(2,elements.size());
		assertEquals("MAD,Madrid",elements.toArray(new Airport[elements.size()])[0].save());
		assertEquals("BCN,Barcelona",elements.toArray(new Airport[elements.size()])[1].save());
	}
	
	@Test
	public void testInsert() throws NotEnoughElements {
		Airport airport = new Airport();
		airport.load("FKA,FakeAirport");
		table.insert(airport);
		Airport airport2 = new Airport();
		airport2.load("MAD,FakeAirport2");
		table.insert(airport2);
		Collection<Airport> elements = table.dump();
		assertEquals(4,elements.size());
		assertEquals("MAD,Madrid",elements.toArray(new Airport[elements.size()])[0].save());
		assertEquals("MAD,FakeAirport2",elements.toArray(new Airport[elements.size()])[1].save());
		assertEquals("BCN,Barcelona",elements.toArray(new Airport[elements.size()])[2].save());
		assertEquals("FKA,FakeAirport",elements.toArray(new Airport[elements.size()])[3].save());
	}
	
	@Test
	public void testSearch() throws NotFound, NotEnoughElements {
		Airport airport = new Airport();
		airport.load("MAD,FakeAirport");
		table.insert(airport);
		List<Airport> list = table.search(0, "BCN");
		assertEquals(1,list.size());
		assertEquals("BCN",list.get(0).getIATACode());
		assertEquals("Barcelona",list.get(0).getName());
		list = table.search(1, "Barcelona");
		assertEquals(1,list.size());
		assertEquals("BCN",list.get(0).getIATACode());
		assertEquals("Barcelona",list.get(0).getName());
		list = table.search(0, "MAD");
		assertEquals(2,list.size());
		for (Airport airport2 : list) {
			assertEquals("MAD",airport2.getIATACode());
		}
		assertEquals("FakeAirport",list.get(1).getName());
		
	}
	
	@Test(expected = NotFound.class)
	public void testSearchNotFound() throws NotFound {
		List<Airport> list = table.search(0, "NotExist");
	}

	@Test
	public void testLoadFromFile() throws IOException, NotEnoughElements {
		table = new MultipleIndexesMemoryTable<Airport>();
		table.loadFromFile(Airport.class, "./data/airport.csv");
		assertEquals(9,table.dump().size());
		assertEquals("MAD,Madrid",table.dump().toArray(new Airport[table.dump().size()])[0].save());
		assertEquals("BCN,Barcelona",table.dump().toArray(new Airport[table.dump().size()])[8].save());
	}

	@Test
	public void testSaveToFile() throws IOException, NotEnoughElements {
		table.saveToFile("./test/data/dummy.remove");
		table = new MultipleIndexesMemoryTable<Airport>();
		table.loadFromFile(Airport.class, "./test/data/dummy.remove");
		assertEquals(2,table.dump().size());
		assertEquals("MAD,Madrid",table.dump().toArray(new Airport[table.dump().size()])[0].save());
		assertEquals("BCN,Barcelona",table.dump().toArray(new Airport[table.dump().size()])[1].save());
		Files.deleteIfExists(Paths.get("./data/dummy.remove"));
	}

}
