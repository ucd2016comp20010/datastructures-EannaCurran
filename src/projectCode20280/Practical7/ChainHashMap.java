package projectCode20280.Practical7;

import projectCode20280.Practical6.Entry;

import java.util.ArrayList;

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
	@SuppressWarnings({ "unchecked" })
	protected void createTable() { table =  (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity]; }

	/**
	 * Returns value associated with key k in bucket with hash value h. If no such
	 * entry exists, returns null.
	 *
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return associate value (or null, if no such entry)
	 */
	@Override
	protected V bucketGet(int h, K k) {

		UnsortedTableMap<K,V> bucket = table[h];

		if(bucket == null){
			return null;
		}

		return bucket.get(k);
	}

	/**
	 * Associates key k with value v in bucket with hash value h, returning the
	 * previously associated value, if any.
	 *
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @param v the value to be associated
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketPut(int h, K k, V v) {

		UnsortedTableMap<K,V> bucket = table[h];

		if(bucket == null){
			bucket = new UnsortedTableMap<K, V>();
			table[h] = bucket;
		}

		int previousSize = bucket.size();
		V oldValue = bucket.put(k, v);
		n += (bucket.size() - previousSize);

		return oldValue;
	}

	/**
	 * Removes entry having key k from bucket with hash value h, returning the
	 * previously associated value, if found.
	 *
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketRemove(int h, K k) {

		UnsortedTableMap<K, V> bucket = table[h];

		if(bucket == null){
			return null;
		}

		int previousSize = bucket.size();
		V oldValue = bucket.remove(k);
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

		ArrayList<Entry<K, V>> entryList = new ArrayList<Entry<K, V>>();
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

	public String toString(){

		return entrySet().toString();
	}

	public static void main(String[] args) {
		//HashMap<Integer, String> m = new HashMap<Integer, String>();
		ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
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