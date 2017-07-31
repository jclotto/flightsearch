package org.lastminute.flightsearch.domain;

import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;
import org.lastminute.flightsearch.domain.utils.IATAUtils;

/**
 * Airport bean, indexable and csvSerialize: two indexes are defined, name and IATA Code
 * @author jclotto
 *
 */
public class Airport extends CsvSerialize {

	/**
	 * IATA Code of the airport
	 */
	String IATACode;
	
	/**
	 * Name of the airport
	 */
	String name;
	
	public String getIATACode() {
		return IATACode;
	}

	/**
	 * 
	 * @param IATA airport code
	 * @throws IATAIncorrectLength throw when size of airport is incorrect
	 */
	public void setIATACode(String iATACode) throws IATAIncorrectLength {
		IATACode = IATAUtils.getIATAAirportCode(iATACode);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getIndexes() {
		// First Index of Airport is IATA Name, second normal name
		return new String[] {this.getIATACode(),this.getName()};
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
		
		return getIATACode() + delim + getName();
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
		if (elements == null || elements.length < 2) throw new NotEnoughElements();
		try {
			this.setIATACode(elements[0]);
		} catch (IATAIncorrectLength e) {
			System.err.println("Incorrect IATA Airport code:" + elements[0]);
			e.printStackTrace();
		}
		this.setName(elements[1]);
	}

}
