package org.lastminute.flightsearch.domain;

import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;
import org.lastminute.flightsearch.domain.utils.IATAUtils;

/**
 * Bean class that defines a flight, is indexable and csv serializable, to be used
 * on storage class.
 *   Index: origin+destination
 * @author jclotto
 *
 */
public class Flight extends CsvSerialize {

	/**
	 * Airport of origin for the flight
	 */
	String origin;
	/**
	 * Airport of destination for the flight
	 */
	String destination;
	/**
	 * IATA Code for flight
	 */
	String flightCode;
	/**
	 * Based price of the flight for adults and childs
	 */
	Double basedPrice;
	
	
	
	public String getOrigin() {
		return origin;
	}

	/**
	 * 
	 * @param origin airport of origin
	 * @throws IATAIncorrectLength throw when size of airport is incorrect
	 */
	public void setOrigin(String origin) throws IATAIncorrectLength {
		this.origin = IATAUtils.getIATAAirportCode(origin);
	}

	public String getDestination() {
		return destination;
	}

	/**
	 * 
	 * @param origin airport of destination
	 * @throws IATAIncorrectLength throw when size of airport is incorrect
	 */
	public void setDestination(String destination) throws IATAIncorrectLength {
		this.destination = IATAUtils.getIATAAirportCode(destination);
	}

	public String getFlightCode() {
		return flightCode;
	}
	
	/**
	 * 
	 * @param Flight code (2 first digit airline code)
	 * @throws IATAIncorrectLength throw when size of flight code is incorrect
	 */
	public void setFlightCode(String flightCode) throws IATAIncorrectLength{
		this.flightCode = IATAUtils.getIATAFlightCode(flightCode);
	}

	public Double getBasedPrice() {
		return basedPrice;
	}

	public void setBasedPrice(Double basedPrice) {
		this.basedPrice = basedPrice;
	}

	public String[] getIndexes() {
		// Index formed by two airports join together
		return new String[] {this.getOrigin()+this.getDestination()};
	}
	
	public String toString() {
		return save(' ');
	}

	/**
	 * Transform the bean in a CSV String
	 * @param delimitator to be used on CSV String
	 * @return String contains the bean serialized to CSV String
	 */
	@Override
	public String save(Character delim) {
		
		return getOrigin() + delim + getDestination() + delim + getFlightCode() + delim + getBasedPrice();
	}

	/**
	 * Load the bean from a CSV String
	 * @param csvString, stores the csv line
	 * @param delimitator, to be used on CSV String
	 * @throws NotEnoughElements, String don't contains enough tokens to load
	 */
	@Override
	public void load(String csvString, Character delim) throws NotEnoughElements {
		String[] elements = CsvSerialize.split(csvString, delim);
		if (elements == null || elements.length < 4) throw new NotEnoughElements();
		try {
			this.setOrigin(elements[0]);
		} catch (IATAIncorrectLength e) {
			System.err.println("Incorrect IATA Origin Airport: " + elements[0]);
			e.printStackTrace();
		}
		try {
			this.setDestination(elements[1]);
		} catch (IATAIncorrectLength e) {
			System.err.println("Incorrect IATA Destination Airport: " + elements[1]);
			e.printStackTrace();
		}
		try {
			this.setFlightCode(elements[2]);
		} catch (IATAIncorrectLength e) {
			System.err.println("Incorrect IATA FlightCode: " + elements[2]);
			e.printStackTrace();
		}
		this.setBasedPrice(Double.valueOf(elements[3]));
	}

}
