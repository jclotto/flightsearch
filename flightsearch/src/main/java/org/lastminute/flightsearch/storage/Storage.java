/**
 * 
 */
package org.lastminute.flightsearch.storage;

import java.util.Collection;
import java.util.List;

/**
 * Abstract class of a Storage. Main used on public database definition
 * 
 * @author jclotto
 * @param T a indexable bean (no need to be CsvSerialize)
 */
public interface Storage<T extends org.lastminute.flightsearch.domain.Indexable> {
	
	/**
	 * Given a key(index,value. i,.e. [0,MAD] [1,Madrid]), return a list of beans matched with that key
	 * @param index (index position)
	 * @param key (index value)
	 * @return list of beans that matched that search
	 * @throws NotFound. No beans are found.
	 */
	public List<T> search(int index,String key) throws NotFound;
	
	/**
	 * Insert object on Storage. index are extracted from bean
	 * @param object
	 */
	public void insert(T object);
	
	/**
	 * Return a collection not sorted of all beans on storage
	 * @return
	 */
	public Collection<T> dump();

}
