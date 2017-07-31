package org.lastminute.flightsearch.domain;

/**
 * Indexable interface, is the main contract between domain beans, and storage classes
 * to define indexes defined on the beans
 * tokens on String provided to consume.
 * @author jclotto
 *
 */
public interface Indexable {

	/**
	 * Method to return list of indexes values defined on the beans
	 * all objects of same class must have same number of indexes.
	 * @return Array of fixed length, with values of indexes for this object
	 */
	public String[] getIndexes();
	
}
