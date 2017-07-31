/**
 * 
 */
package org.lastminute.flightsearch.storage;

import java.io.IOException;

import org.lastminute.flightsearch.domain.Airline;
import org.lastminute.flightsearch.domain.Airport;
import org.lastminute.flightsearch.domain.Flight;
import org.lastminute.flightsearch.domain.NotEnoughElements;

/**
 * Database class, contains references to all the tables, and not location of every csvFile
 * @author jclotto
 *
 */
public class Database {
	
	/**
	 * Tables
	 */
	private static MultipleIndexesMemoryTable<Airport> airports;
	private static MultipleIndexesMemoryTable<Airline> airlines;
	private static MultipleIndexesMemoryTable<Flight> flights;
	
	/**
	 * Initialize database's tables from a filesystem root point (i.e. ./test/)
	 * @param root filesystem root point
	 * @throws IOException problems with some files
	 * @throws NotEnoughElements
	 */
	public static void init(String root) throws IOException, NotEnoughElements {
	  airports = new MultipleIndexesMemoryTable<Airport>();
	  airlines = new MultipleIndexesMemoryTable<Airline>();
	  flights = new MultipleIndexesMemoryTable<Flight>();
	  
	  airports.loadFromFile(Airport.class, root+DataFileNames.airportDatabaseName);
	  airlines.loadFromFile(Airline.class, root+DataFileNames.airlineDatabaseName);
	  flights.loadFromFile(Flight.class, root+DataFileNames.flightDatabaseName);
	}
	
	/**
	 * @return Airport table
	 */
	public static Storage<Airport> getAirports() {
		return airports;
	}

	/**
	 * @return Airline table
	 */
	public static Storage<Airline> getAirlines() {
		return airlines;
	}
	
	/**
	 * @return Flight table
	 */
	public static Storage<Flight> getFlights() {
		return flights;
	}
}
