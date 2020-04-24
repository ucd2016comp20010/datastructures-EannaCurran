package projectCode20280.Practical7;

import projectCode20280.Practical6.Entry;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {

	// a fixed capacity array of UnsortedTableMap that serve as buckets
	private UnsortedTableMap<K, V>[] table; // initialized within createTable

	/** Creates a hash table with capacity 11 and prime factor 109345121. */
	public ChainHashMap() {
		super();
	}

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public ChainHashMap(int cap) {
		super(cap);
	}

	/** Creates a hash table with the given capacity and prime factor. */
	public ChainHashMap(int cap, int p) {
		super(cap, p);
	}

	/** Creates an empty table having length equal to current capacity. */
	@Override
	protected void createTable() { table =  (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity]; }

	/**
	 * Returns value associated with key key in bucket with hash value hash. If no such
	 * entry exists, returns null.
	 *
	 * @param hash the hash value of the relevant bucket
	 * @param key the key of interest
	 * @return associate value (or null, if no such entry)
	 */
	@Override
	protected V bucketGet(int hash, K key) {

		// Gets the bucket of the hash
		UnsortedTableMap<K,V> bucket = table[hash];

		// Check for a null bucket
		if(bucket == null){ return null; }

		// Returns the value in the bucket with given key
		return bucket.get(key);
	}

	/**
	 * Associates key key with value value in bucket with hash value hash, returning the
	 * previously associated value, if any.
	 *
	 * @param hash the hash value of the relevant bucket
	 * @param key the key of interest
	 * @param value the value to be associated
	 * @return previous value associated with key (or null, if no such entry)
	 */
	@Override
	protected V bucketPut(int hash, K key, V value) {

		// Gets table
		UnsortedTableMap<K,V> bucket = table[hash];

		// Checks for the bucket as null, if so create a new bucket
		if(bucket == null){
			bucket = new UnsortedTableMap<>();
			table[hash] = bucket;
		}

		// Puts value into the bucket hash of given key
		int previousSize = bucket.size();
		V oldValue = bucket.put(key, value);
		n += (bucket.size() - previousSize);

		return oldValue;
	}


	/**
	 * Removes entry having key key from bucket with hash value hash, returning the
	 * previously associated value, if found.
	 *
	 * @param hash the hash value of the relevant bucket
	 * @param key the key of interest
	 * @return previous value associated with key (or null, if no such entry)
	 */
	@Override
	protected V bucketRemove(int hash, K key) {

		// Gets bucket of given hash
		UnsortedTableMap<K, V> bucket = table[hash];

		// Check for null bucket
		if(bucket == null){ return null; }

		// Removes key from bucket hash
		int previousSize = bucket.size();
		V oldValue = bucket.remove(key);
		n += (bucket.size() - previousSize);

		return oldValue;
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {

		ArrayList<Entry<K, V>> entryList = new ArrayList<>();
		for(int i = 0; i <  capacity; i++){
			UnsortedTableMap<K, V> bucket = table[i];
			if(bucket != null){
				for(Entry<K, V> entry: bucket.entrySet()){
					entryList.add(entry);
				}
			}
		}
		return entryList;
	}


	/**
	 * toString method for Chain Hash Map
	 * @return String representation
	 */
	public String toString(){

		Iterator<Entry<K, V>> entry = entrySet().iterator();
		StringBuilder string = new StringBuilder();
		string.append("[");

		while(entry.hasNext()){
			Entry<K,V> temp = entry.next();
			string.append(" <").append(temp.getKey()).append(", ").append(temp.getValue()).append("> ");
		}

		string.append("]");
		return string.toString();
	}

	public static void main(String[] args) {

		ChainHashMap<Integer, String> m = new ChainHashMap<>();
		m.put(1, "One");
		m.put(2, "Two");
		m.put(10, "Ten");
		m.put(11, "Eleven");
		m.put(20, "Twenty");

		System.out.println("m: " + m);

		m.remove(11);
		System.out.println("m: " + m);
	}
}