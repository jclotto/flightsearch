package org.lastminute.flightsearch.test.util;

import java.io.IOException;

import org.lastminute.flightsearch.domain.Airport;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;
import org.lastminute.flightsearch.storage.NotFound;
import org.lastminute.flightsearch.storage.DataFileNames;
import org.lastminute.flightsearch.storage.MultipleIndexesMemoryTable;

/**
 * Hello world!
 *
 */
public class CreateAirportCsvFile 
{
    public static void run() throws NotFound,NotEnoughElements, IOException, IATAIncorrectLength
    {
    	 MultipleIndexesMemoryTable<Airport> airportTable = new MultipleIndexesMemoryTable<Airport>();
    	 Airport madrid = new Airport();
    	 madrid.setIATACode("MAD");
    	 madrid.setName("Madrid");
    	 Airport barcelona = new Airport();
    	 barcelona.setIATACode("BCN");
    	 barcelona.setName("Barcelona");
    	 Airport london = new Airport();
    	 london.setIATACode("LHR");
    	 london.setName("London");
    	 Airport paris = new Airport();
    	 paris.setIATACode("CDG");
    	 paris.setName("Paris");
    	 Airport frankfurt = new Airport();
    	 frankfurt.setIATACode("FRA");
    	 frankfurt.setName("Frankfurt");
    	 Airport istanbul = new Airport();
    	 istanbul.setIATACode("IST");
    	 istanbul.setName("Istanbul");
    	 Airport amsterdam = new Airport();
    	 amsterdam.setIATACode("AMS");
    	 amsterdam.setName("Amsterdam");
    	 Airport rome = new Airport();
    	 rome.setIATACode("FCO");
    	 rome.setName("Rome");
    	 Airport copenhagen = new Airport();
    	 copenhagen.setIATACode("CPH");
    	 copenhagen.setName("Copenhagen");
         airportTable.insert(madrid);
         airportTable.insert(barcelona);
         airportTable.insert(london);
         airportTable.insert(paris);
         airportTable.insert(frankfurt);
         airportTable.insert(istanbul);
         airportTable.insert(amsterdam);
         airportTable.insert(rome);
         airportTable.insert(copenhagen);
         airportTable.saveToFile( DataFileNames.airportDatabaseName);
    	
    	
    }
}
