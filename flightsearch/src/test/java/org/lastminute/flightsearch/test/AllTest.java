/**
 * 
 */
package org.lastminute.flightsearch.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.lastminute.flightsearch.test.storage.DatabaseTest;
import org.lastminute.flightsearch.test.storage.MultipleIndexesMemoryTableTest;
import org.lastminute.flightsearch.test.domain.AirlineTest;
import org.lastminute.flightsearch.test.domain.AirportTest;
import org.lastminute.flightsearch.test.domain.FlightTest;

/**
 * @author jclotto
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
        AirportTest.class,
        AirlineTest.class,
        FlightTest.class,
        MultipleIndexesMemoryTableTest.class,
        DatabaseTest.class})

public class AllTest {

}
