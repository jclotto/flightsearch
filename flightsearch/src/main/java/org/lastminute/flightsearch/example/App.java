/**
 * 
 */
package org.lastminute.flightsearch.example;

import java.io.IOException;
import java.util.List;

import org.lastminute.flightsearch.dco.FlightDCO;
import org.lastminute.flightsearch.domain.FlightSearch;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.storage.NotFound;

/**
 * Example to test application from console
 * @author jclotto
 *
 */
public class App {

	/**
	 * @param args
	 * @throws NotEnoughElements 
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException, NotEnoughElements {
		FlightDCO dco = new FlightDCO();
		String origin = args[0];
		String destination = args[1];
		
		int daysToDeparture=0;
		try {
			daysToDeparture=Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.err.println("Incorrect days to departure,bigger than 0: "+args[2]);
			System.exit(-1);
		}
		if(daysToDeparture<=0) {
			System.err.println("Incorrect days to departure,bigger than 0: "+daysToDeparture);
			System.exit(-1);
		}
		Integer adult=0;
		try {
			adult= Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			System.err.println("Incorrect adults number, not negative: "+ args[3]);
			System.exit(-1);
		}
		if(adult<0) {
			System.err.println("Incorrect adult number, not negative: "+ adult);
			System.exit(-1);
		}
		Integer child=0;
		try {
			child= Integer.parseInt(args[4]);
		} catch (NumberFormatException e) {
			System.err.println("Incorrect child number, not negative: "+ args[4]);
			System.exit(-1);
		}
		if(child<0) {
			System.err.println("Incorrect child number, not negative: "+ child);
			System.exit(-1);
		}
		Integer infant=0;
		try {
			infant= Integer.parseInt(args[5]);
		} catch (NumberFormatException e) {
			System.err.println("Incorrect infant number, not negative: "+ args[5]);
			System.exit(-1);
		}
		if(infant<0) {
			System.err.println("Incorrect infant number, not negative: "+ infant);
			System.exit(-1);
		}

		try {
			List<FlightSearch> list = dco.searchFlights(origin,destination, daysToDeparture,adult,child,infant);
			for (FlightSearch flightSearch : list) {
				System.out.println("" + flightSearch.getFlight().getFlightCode() + " " +flightSearch.getTotalPrice() + "€");
				
			}
		} catch (NotFound e) {
			System.out.println("no flights available");
		}
       
	}

}
