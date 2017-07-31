package org.lastminute.flightsearch.test.util;

import java.io.IOException;

import org.lastminute.flightsearch.domain.Airline;
import org.lastminute.flightsearch.domain.NotEnoughElements;
import org.lastminute.flightsearch.domain.utils.IATAIncorrectLength;
import org.lastminute.flightsearch.storage.NotFound;
import org.lastminute.flightsearch.storage.DataFileNames;
import org.lastminute.flightsearch.storage.MultipleIndexesMemoryTable;

/**
 * Hello world!
 *
 */
public class CreateAirlinesCsvFile 
{
    public static void run() throws NotFound,NotEnoughElements, IOException, IATAIncorrectLength
    {
    	 MultipleIndexesMemoryTable<Airline> airlineTable = new MultipleIndexesMemoryTable<Airline>();
         Airline iberia = new Airline();
         iberia.setIATACode("IB");
         iberia.setInfantPrice(19.90);
         iberia.setName("Iberia");
         
         Airline british = new Airline();
         british.setIATACode("BA");
         british.setInfantPrice(15.0);
         british.setName("British Airways");
         
         Airline lufthansa = new Airline();
         lufthansa.setIATACode("LH");
         lufthansa.setInfantPrice(7.0);
         lufthansa.setName("Lufthansa");
         
         Airline ryanair = new Airline();
         ryanair.setIATACode("FR");
         ryanair.setInfantPrice(20.0);
         ryanair.setName("Ryanair");
         
         Airline vueling = new Airline();
         vueling.setIATACode("VY");
         vueling.setInfantPrice(10.0);
         vueling.setName("Vueling");
         
         Airline turkish = new Airline();
         turkish.setIATACode("TK");
         turkish.setInfantPrice(5.0);
         turkish.setName("Turkish Airlines");
         
         Airline easyJet = new Airline();
         easyJet.setIATACode("U2");
         easyJet.setInfantPrice(19.90);
         easyJet.setName("EasyJet");
         
         airlineTable.insert(iberia);
         airlineTable.insert(british);
         airlineTable.insert(lufthansa);
         airlineTable.insert(ryanair);
         airlineTable.insert(vueling);
         airlineTable.insert(turkish);
         airlineTable.insert(easyJet);
         airlineTable.saveToFile( DataFileNames.airlineDatabaseName);
    	
    	
    }
}
