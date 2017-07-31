package org.lastminute.flightsearch.domain;

import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;
import org.lastminute.flightsearch.domain.utils.IATAUtils;

/**
 * Airline bean, indexable and csvSerialize: one index defined IATA Code
 * @author jclotto
 *
 */
public class Airline extends CsvSerialize {

	/**
	 * IATA Code of the airline
	 */
	String IATACode;
	/**
	 * Name of the airline
	 */
	String name;
	/**
	 * infant fixed price for this airline
	 */
	Double infantPrice;
	
	public String getIATACode() {
		return IATACode;
	}

	/**
	 * 
	 * @param IATA airline code
	 * @throws IATAIncorrectLength throw when size of airline is incorrect
	 */
	public void setIATACode(String iATACode) throws IATAIncorrectLength {
		IATACode = IATAUtils.getIATAAirlineCode(iATACode);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(Double infantPrice) {
		this.infantPrice = infantPrice;
	}

	public String[] getIndexes() {
		// Index of Airline is IATACode
		return new String[] {this.getIATACode()};
	}
	
	public String toString() {
		return save(' ') + "€";
	}

	/**
	 * Transform the bean in a CSV String
	 * @param delimitator to be used on CSV String
	 * @return String contains the bean serialized to CSV String
	 */
	@Override
	public String save(Character delim) {
		
		return getIATACode() + delim + getName() + delim + getInfantPrice();
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
		if (elements == null || elements.length < 3) throw new NotEnoughElements();
		try {
			this.setIATACode(elements[0]);
		} catch (IATAIncorrectLength e) {
			System.err.println("Incorrect IATA Airline code:" + elements[0]);
			e.printStackTrace();
		}
		this.setName(elements[1]);
	    this.setInfantPrice(Double.valueOf(elements[2]));
		  
		
	}

}
