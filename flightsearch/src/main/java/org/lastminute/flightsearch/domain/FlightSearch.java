package org.lastminute.flightsearch.domain;

/**
 * Not storage bean, used on return of DCO, provides all data necessary on presentation
 * layer
 * @author jclotto
 *
 */
public class FlightSearch {

	/**
	 * Flight found on search
	 */
	Flight flight;
	/**
	 * Airline associated with flight
	 */
	Airline airline;
	/**
	 * Final price, included discounts for this flight and passengers
	 */
	double totalPrice;
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	/**
	 * @return Price in String format, prepared for presentation purpose with 2 decimals
	 */
	public String getTotalPrice() {
		return String.format("%.2f", totalPrice);
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
