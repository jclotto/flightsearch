package org.lastminute.flightsearch.test.util;

import java.io.IOException;


import org.lastminute.flightsearch.domain.Flight;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;
import org.lastminute.flightsearch.storage.NotFound;
import org.lastminute.flightsearch.storage.DataFileNames;
import org.lastminute.flightsearch.storage.MultipleIndexesMemoryTable;

/**
 * Hello world!
 *
 */
public class CreateFlightCsvFile 
{
    public static void run() throws NotFound,NotEnoughElements, IOException, IATAIncorrectLength
    {
    	 MultipleIndexesMemoryTable<Flight> flightTable = new MultipleIndexesMemoryTable<Flight>();
    	 Flight first = new Flight();
    	 first.setOrigin("MAD");
    	 first.setDestination("BCN");
    	 first.setFlightCode("IB1234");
    	 first.setBasedPrice(9.30);
    	 flightTable.insert(first);
         flightTable.saveToFile( DataFileNames.flightDatabaseName);
    	
    	
    }
}
