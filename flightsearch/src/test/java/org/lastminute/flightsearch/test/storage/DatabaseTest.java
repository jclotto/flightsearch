package org.lastminute.flightsearch.test.storage;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.flightsearch.domain.Airline;
import org.lastminute.flightsearch.domain.Airport;
import org.lastminute.flightsearch.domain.Flight;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.storage.Database;

public class DatabaseTest {

	@Test
	public void testInit() throws IOException, NotEnoughElements {
		Database.init("./test/");
	}

	@Test
	public void testGetAirports() throws IOException, NotEnoughElements {
		Database.init("./test/");
		assertEquals(2,Database.getAirports().dump().size());
		assertEquals("MAD,Madrid",Database.getAirports().dump().toArray(new Airport[Database.getAirports().dump().size()])[0].save());
		assertEquals("BCN,Barcelona",Database.getAirports().dump().toArray(new Airport[Database.getAirports().dump().size()])[1].save());
	}

	@Test
	public void testGetAirlines() throws IOException, NotEnoughElements {
		Database.init("./test/");
		assertEquals(1,Database.getAirlines().dump().size());
		assertEquals("IB,Iberia,19.9",Database.getAirlines().dump().toArray(new Airline[Database.getAirlines().dump().size()])[0].save());
	}

	@Test
	public void testGetFlights() throws IOException, NotEnoughElements {
		Database.init("./test/");
		assertEquals(2,Database.getFlights().dump().size());
		assertEquals("MAD,BCN,IB1234,100.0",Database.getFlights().dump().toArray(new Flight[Database.getFlights().dump().size()])[0].save());
		assertEquals("MAD,BCN,IB2345,200.0",Database.getFlights().dump().toArray(new Flight[Database.getFlights().dump().size()])[1].save());

	}

}
