/**
 * 
 */
package org.lastminute.flightsearch.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.lastminute.flightsearch.domain.CsvSerialize;
import org.lastminute.flightsearch.domain.NotEnoughElements;

/**
 * Main class of project, this class allow the user to multiple indexed storage,
 * and also allow export/import to CSV File
 * @author jclotto
 * @param T a CsvSerialize&Indexed bean
 * 
 */
public class MultipleIndexesMemoryTable<T extends CsvSerialize> extends CsvTable<T> implements Storage<T> {

	/**
	 * Internal storage: a List of hashMap(one hashmap per index), inside hashmap,
	 * a key, list of values set. 
	 */
	java.util.ArrayList<java.util.HashMap<String,LinkedList<T>>> internalStorage = null;
	
	/**
	 * Given a key, and a value of index, return a list of beans in storage 
	 * @param index type of index to be used, i.e. Airport:0->iata 1->name
	 * @param key value of index for the search. i.e. MAD, or Madrid
	 * @return list of values match the search
	 * @throws NotFound key is not found in storage
	 */
	public List<T> search(int index,String key) throws NotFound {
		List<T> value = null;
		if (internalStorage != null && index<internalStorage.size())
		  value = internalStorage.get(index).get(key);
		if (value == null) throw new NotFound();
		return value;
	}

	/**
	 * Insert on storage the value of the bean provided. The bean is inserted for 
	 * all indexed provided by definition
	 * @param object. Value to be inserted on storage.
	 */
	public void insert(T object) {
	  createInternalStorage(object.getIndexes().length);
	  for(int i=0;i<object.getIndexes().length;i++) {
		LinkedList<T> value = internalStorage.get(i).get(object.getIndexes()[i]);
		if (value == null) {
		  value = new LinkedList<T>();
		  internalStorage.get(i).put(object.getIndexes()[i], value);
		}
		value.add(object);
	  }
		
	}
	
	/**
	 * Create internal storage, if is not already created
	 * @param length
	 */
	private void createInternalStorage(int length) {
		if(internalStorage == null) {
			internalStorage = new java.util.ArrayList<java.util.HashMap<String,LinkedList<T>>> ();
			for(int i=0;i<length;i++)
				internalStorage.add(new HashMap<String,LinkedList<T>>());
		}
		
	}

	/**
	 * Create a instance of specific type (used to populate a bean from a
	 * CSV String
	 * @param type
	 * @return
	 */
	private T createInternalT(Class type) {
		try {
		  return (T)(type.newInstance());
		} catch( Exception e ) {
		  return null;
		}
	}

	/**
	 * Internal use (CsvSerialize) to populate a bean from a CSV String
	 */
	@Override
	void internalStorage(Class type,String line) throws NotEnoughElements {
	   T t = createInternalT(type);
	   t.load(line);
	   insert(t);
	}

	/**
	 * Internal use (CsvSerialize) to return all beans on storage.
	 * STORAGE is not SORTED
	 */
	@Override
	Collection<T> internalElements() {
		Collection<T> values = new LinkedList<T>();
		
		for(LinkedList<T> list : internalStorage.get(0).values()) {
		  values.addAll(list);	
		}
		return values;
	}
	
	/**
	 * a dump of storage
	 * @return A non-sorted collection of all elements of storage
	 */
	public Collection<T> dump() {
		return internalElements();
	}


}
