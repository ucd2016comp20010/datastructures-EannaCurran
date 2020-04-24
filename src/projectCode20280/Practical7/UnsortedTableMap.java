package projectCode20280.Practical7;

import projectCode20280.Practical6.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 */

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
	/** Underlying storage for the map of entries. */
	private final ArrayList<MapEntry<K, V>> table = new ArrayList<>();

	/** Constructs an initially empty map. */
	public UnsortedTableMap() {
	}

	// private utility
	/** Returns the index of an entry with equal key, or -1 if none found. */
	private int findIndex(K key) {

		for(int i = 0; i < table.size(); i++){

			if(table.get(i).getKey().equals(key)){

				return i;
			}
		}

		return -1;
	}

	// public methods
	/**
	 * Returns the number of entries in the map.
	 *
	 * @return number of entries in the map
	 */
	@Override
	public int size() {
		return table.size();
	}

	/**
	 * Returns the value associated with the specified key, or null if no such entry
	 * exists.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the associated value, or null if no such entry exists
	 */
	@Override
	public V get(K key) {

		int index = findIndex(key);

		if(index == -1){
			return null;
		}

		return table.get(index).getValue();

	}

	/**
	 * Associates the given value with the given key. If an entry with the key was
	 * already in the map, this replaced the previous value with the new one and
	 * returns the old value. Otherwise, a new entry is added and null is returned.
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with the key (or null, if no such
	 *         entry)
	 */
	@Override
	public V put(K key, V value) {

		int index = findIndex(key);

		if(index == -1){
			table.add(new MapEntry<>(key, value) {
				@Override
				public int compareTo(Entry<K, V> o) {
					return 0;
				}
			});
			index = findIndex(key);
		}

		table.get(index).setValue(value);
		return table.get(index).getValue();

	}

	/**
	 * Removes the entry with the specified key, if present, and returns its value.
	 * Otherwise does nothing and returns null.
	 *
	 * @param key the key whose entry is to be removed from the map
	 * @return the previous value associated with the removed key, or null if no
	 *         such entry exists
	 */
	@Override
	public V remove(K key) {

		int index = findIndex(key);

		if(index == -1){
			return null;
		}

		V temp = table.get(index).getValue();

		if(index != table.size()-1){
			table.set(index, table.get(table.size() -1));
		}

		table.remove(table.size() -1);
		return temp;
	}

	// ---------------- nested EntryIterator class ----------------
	private class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0;

		public boolean hasNext() {
			return j < table.size();
		}

		public Entry<K, V> next() {
			if (j == table.size())
				throw new NoSuchElementException("No further entries");
			return table.get(j++);
		}

		public void remove() {
			throw new UnsupportedOperationException("remove not supported");
		}
	} // ----------- end of nested EntryIterator class -----------

	// ---------------- nested EntryIterable class ----------------
	private class EntryIterable implements Iterable<Entry<K, V>> {
		public Iterator<Entry<K, V>> iterator() { return new EntryIterator(); }
	} // ----------- end of nested EntryIterable class -----------

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}


	/**
	 * toString method for Unsorted Table Map
	 * @return String of Unsorted Table Map
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
}