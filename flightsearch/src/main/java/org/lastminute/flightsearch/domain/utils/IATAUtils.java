package org.lastminute.flightsearch.domain.utils;

/**
 * IATAUtils, generate code from strings
 * @author jclotto
 *
 */
public class IATAUtils {
	
	private static final int AIRPORT_LENGTH = 3;
	private static final int AIRLINE_LENGTH = 2;
	private static final int FLIGHT_CODE_LENGTH = 6;

	private static final String getIATACode(String code, int length) throws IATAIncorrectLength {
		if(code==null || code.length()<length) throw new IATAIncorrectLength();
		return code.substring(0, length);
	}
	
	/**
	 * Gets a Airport code
	 * @param String contains the code
	 * @return code of fixed length
	 * @throws IATAIncorrectLength
	 */
	public static final String getIATAAirportCode(String code) throws IATAIncorrectLength{
	    return getIATACode(code,AIRPORT_LENGTH);	
	}
	
	/**
	 * Gets a Airline code
	 * @param String contains the code
	 * @return code of fixed length
	 * @throws IATAIncorrectLength
	 */
	public static final String getIATAAirlineCode(String code) throws IATAIncorrectLength{
	    return getIATACode(code,AIRLINE_LENGTH);	
	}
	
	/**
	 * Gets a flight code
	 * @param String contains the code
	 * @return code of fixed length
	 * @throws IATAIncorrectLength
	 */
	public static final String getIATAFlightCode(String code) throws IATAIncorrectLength{
	    return getIATACode(code,FLIGHT_CODE_LENGTH);	
	}
}
