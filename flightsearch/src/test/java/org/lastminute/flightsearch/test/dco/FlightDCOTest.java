package org.lastminute.flightsearch.test.dco;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lastminute.flightsearch.dco.FlightDCO;
import org.lastminute.flightsearch.domain.FlightSearch;
import org.lastminute.flightsearch.storage.NotFound;

public class FlightDCOTest {

	private static FlightDCO flightDCO;
	@Before
	public void setUp() throws Exception {
		flightDCO = new FlightDCO();
	}

	@Test
	public void testSearchFlights() throws NotFound {
		List<FlightSearch> result = flightDCO.searchFlights("London", "Istanbul", 30, 2, 2, 2);
		assertEquals(2,result.size());
		assertEquals("LHR,IST,TK8891,250.0",result.get(0).getFlight().save());
		//250*2 adults + 250*2*67% child + 2*5 infants = 845,00€
		assertEquals("845,00",result.get(0).getTotalPrice());
		assertEquals("LHR,IST,LH1085,148.0",result.get(1).getFlight().save());
		//148*2 adults + 148*2*67% + 2*7 = 508,32€
		assertEquals("508,32",result.get(1).getTotalPrice());
		result = flightDCO.searchFlights("London", "Istanbul", 31, 1, 1, 1);
		assertEquals(2,result.size());
		assertEquals("LHR,IST,TK8891,250.0",result.get(0).getFlight().save());
		//250*1*80% adults + 250*1*67%*80% child + 1*5 infants = 339,00€
		assertEquals("339,00",result.get(0).getTotalPrice());
		assertEquals("LHR,IST,LH1085,148.0",result.get(1).getFlight().save());
		//148*1*80% adults + 148*1*80%*67% + 1*7 = 204,73€
		assertEquals("204,73",result.get(1).getTotalPrice());
		
	}

}
 	