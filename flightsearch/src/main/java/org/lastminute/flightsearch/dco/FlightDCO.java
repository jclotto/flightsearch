package org.lastminute.flightsearch.dco;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.lastminute.flightsearch.domain.Airline;
import org.lastminute.flightsearch.domain.Airport;
import org.lastminute.flightsearch.domain.Flight;
import org.lastminute.flightsearch.domain.FlightSearch;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.storage.Database;
import org.lastminute.flightsearch.storage.NotFound;

public class FlightDCO {
	
	/**
	 * Create a FlightDCO: initialize database with data from root
	 * @throws IOException
	 * @throws NotEnoughElements
	 */
	public FlightDCO() throws IOException, NotEnoughElements {
		Database.init("");
	}
	
	/**
	 * Modifiers of price from far dates to close dates
	 * 0.- 80%
	 * 1.- 100%
	 * 2.- 120%
	 * 3.- 150%
	 */
	private static final Double[] MODIFIERS_PRICE = {0.8,1.0,1.2,1.5};
	/**
	 * Child discount 67%
	 */
	private static final double CHILD_DISCOUNT = 0.67;

	/**
	 * Given days to departure, return double with discount rates
	 * @param daysToDeparture:
	 * @return discount rate
	 */
	private double getModifiersPrice(int daysToDeparture) {
		if(daysToDeparture>30) {
			return MODIFIERS_PRICE[0];
		} else if (daysToDeparture>16) {
			return MODIFIERS_PRICE[1];
		} else if (daysToDeparture>3) {
			return MODIFIERS_PRICE[2];
		}
		return MODIFIERS_PRICE[3];
	}
	
	/**
	 * Given parameters, return list of search results with calculated price inside 
	 * @param origin Name of airport
	 * @param destination Name of airport
	 * @param daysToDeparture days pending to departure
	 * @param adult number of adults
	 * @param child number of childs
	 * @param infant number of infants
	 * @return list of flightSearchs
	 * @throws NotFound flight, or airport, or airline, not found
	 */
	public List<FlightSearch> searchFlights(String origin, String destination, int daysToDeparture,int adult, int child, int infant) throws NotFound{
		if(daysToDeparture <= 0) throw new NotFound();
		double modifiersPrice = getModifiersPrice(daysToDeparture);
		LinkedList<FlightSearch> list = new LinkedList<FlightSearch>();
		List<Airport> origins = Database.getAirports().search(1, origin);
		List<Airport> destinations = Database.getAirports().search(1, destination);
		for(Flight flight: Database.getFlights().search(0, origins.get(0).getIATACode()+destinations.get(0).getIATACode())) {
		  Airline airline = Database.getAirlines().search(0, flight.getFlightCode().substring(0,2)).get(0);
		  Double price = (adult * modifiersPrice * flight.getBasedPrice()) + (child * CHILD_DISCOUNT * flight.getBasedPrice() * modifiersPrice )+ (infant  * airline.getInfantPrice());
		  
		  FlightSearch search = new FlightSearch();
		  search.setFlight(flight);
		  search.setAirline(airline);
		  search.setTotalPrice(price);
		  
		  list.add(search);
		}
		
		return list;
	}

}
