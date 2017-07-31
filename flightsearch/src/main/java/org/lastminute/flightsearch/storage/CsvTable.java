package org.lastminute.flightsearch.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.lastminute.flightsearch.domain.CsvSerialize;
import org.lastminute.flightsearch.domain.NotEnoughElements;

/**
 * Abstract class than prepare a table, to be loadable and storable as CSV table
 * @author jclotto
 *
 * @param <T> Bean that needs to be CsvSerializable, and indexable
 */
public abstract class CsvTable<T extends CsvSerialize> {
	
	/**
	 * Load a table from a csv file
	 * @param type type of bean contained on table
	 * @param fileName file constains csv table
	 * @throws IOException problems loading the file
	 * @throws NotEnoughElements some csv line dont contains enough elements
	 */
	public void loadFromFile(Class type, String fileName) throws IOException, NotEnoughElements {
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		while ((line = reader.readLine()) != null) {
			internalStorage(type,line);
            
        }
		reader.close();
	}
	
	/**
	 * Save data from the table in a CSV File
	 * @param fileName filename where data going to be stored
	 * @throws IOException problems loading the file
	 */
	public void saveToFile(String fileName) throws IOException {
		
		FileWriter writer = new FileWriter(fileName);
		
		Collection<T> elements = this.internalElements();
		boolean first = true;
		for (CsvSerialize csvSerialize : elements) {
			if (first==true)
				first=false;
			else
				writer.append('\n');
			writer.append(csvSerialize.save());
			
		}
		writer.close();
	}
	
	/**
	 * Internal used, to load data on specific structure of the table
	 * @param type type of bean to be created
	 * @param element csvString contains the data
	 * @throws NotEnoughElements bean cannot be created, not enough elements on csv string
	 */
	abstract void internalStorage(Class type,String element) throws NotEnoughElements;
	/**
	 * Internal used, returns all elements of the table
	 * @return all elements of type T contained on the table
	 */
	abstract Collection<T> internalElements();

}
