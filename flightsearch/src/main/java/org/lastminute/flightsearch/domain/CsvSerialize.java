/**
 * 
 */
package org.lastminute.flightsearch.domain;

/**
 * CsvSerialize is abstract class than implements indexable and provides
 * two methods for default CSV transform and define two abstract for 
 * load&save data from CSV Strings.
 * @author jclotto
 *
 */
public abstract class CsvSerialize implements Indexable{
	
	private static final char DEFAULT_CHARACTER = ',';
	/**
	 * Create a CSV String from a bean
	 * @param delim, character to be used as delim of CSV String
	 * @return CSV String generated
	 */
	public abstract String save(Character delim);

	/**
	 * Create a CSV String from a bean using default delim
	 * @return CSV String generated
	 */
	public String save() {
		return this.save(CsvSerialize.DEFAULT_CHARACTER);
	};
	
	/**
	 * Load the bean from a CSV String
	 * @param csvString, line of CSVString contains values of beans
	 * @param delim, character to be used as delim of CSV String
	 * @throw NotEnoughElements, not enough tokens on CSV Line
	 */
	public abstract void load(String csvString,Character delim) throws NotEnoughElements;
	
	public void load(String csvString) throws NotEnoughElements{
		this.load(csvString,CsvSerialize.DEFAULT_CHARACTER);
	}
	
	public static String[] split(String csvLine,Character delim) {
		return csvLine.split(""+delim);
	}
	
}

