// Practical 8 - Question 1 - Tree Map using a Balanceable Binary Tree - Eanna Curran

package projectCode20280.Practical8;

import projectCode20280.Practical5.Position;
import projectCode20280.Practical6.Entry;
import projectCode20280.Practical9.BalanceableBinaryTree;

import java.util.ArrayList;
import java.util.Comparator;

public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

	// Binary tree to represent tree of tree map
	protected BalanceableBinaryTree <K, V> tree = new BalanceableBinaryTree<>();


	/** Constructs an empty map using the natural ordering of keys. */
	public TreeMap() {
		super(); // the AbstractSortedMap constructor
		tree.addRoot(null); // create a sentinel leaf as root
	}


	/** Constructs an map using a given comparator */
	public TreeMap(Comparator<K> comp) {
		super(comp);              // the AbstractSortedMap constructor
		tree.addRoot(null);       // create a sentinel leaf as root
	}


	/**
	 * Returns the number of entries in the map.
	 *
	 * @return number of entries in the map
	 */
	@Override
	public int size() { return (tree.size() - 1) / 2; }


	/** Utility used when inserting a new entry at a leaf of the tree */
	private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
		tree.set(p, entry);
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}


	// Some notational shorthands for brevity (yet not efficiency)
	protected Position<Entry<K, V>> root() { return tree.root(); }

	protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) { return tree.parent(p); }

	protected Position<Entry<K, V>> left(Position<Entry<K, V>> p) { return tree.left(p); }

	protected Position<Entry<K, V>> right(Position<Entry<K, V>> p) { return tree.right(p); }

	protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) { return tree.sibling(p); }

	protected boolean isRoot(Position<Entry<K, V>> p) { return tree.isRoot(p); }

	protected boolean isExternal(Position<Entry<K, V>> p) { return tree.isExternal(p); }

	protected boolean isInternal(Position<Entry<K, V>> p) { return tree.isInternal(p); }

	protected void set(Position<Entry<K, V>> p, Entry<K, V> e) { tree.set(p, e); }

	protected Entry<K, V> remove(Position<Entry<K, V>> p) { return tree.remove(p); }

	protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) { return tree.restructure(x); }


	/**
	 * Returns the position in currentPosition's subtree having the given key (or else the
	 * terminal leaf).
	 *
	 * @param key a target key
	 * @param currentPosition a position of the tree serving as root of a subtree
	 * @return Position holding key, or last node reached during search
	 */
	private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> currentPosition, K key) {

		// Checks if the current position if it is an external position
		if(isExternal(currentPosition)){ return currentPosition; }

		// Compares the value of the key and the current element
		int comp = compare(key,currentPosition.getElement());

		// Recursively moves through the tree until an external leaf is reached
		if(comp == 0){
			return currentPosition;
		} else if(comp < 0){
			return treeSearch(left(currentPosition), key);
		} else {
			return treeSearch(right(currentPosition), key);
		}
	}


	/**
	 * Returns position with the minimal key in the subtree rooted at Position currentPosition.
	 *
	 * @param currentPosition a Position of the tree serving as root of a subtree
	 * @return Position with minimal key in subtree
	 */
	protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> currentPosition) {

		// Loops until the external leaf of the minimal key is reached
		while(isInternal(currentPosition)){ currentPosition = left(currentPosition); }

		// Returns the minimum position
		return  parent(currentPosition);
	}


	/**
	 * Returns the position with the maximum key in the subtree rooted at p.
	 *
	 * @param currentPosition a Position of the tree serving as root of a subtree
	 * @return Position with maximum key in subtree
	 */
	protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> currentPosition) {

		// Loops until the external leaf of the maximum key is reached
		while (isInternal(currentPosition)){ currentPosition = right(currentPosition); }

		// Returns the maximum position
		return  parent(currentPosition);
	}


	/**
	 * Returns the value associated with the specified key, or null if no such entry
	 * exists.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the associated value, or null if no such entry exists
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {

		// Checks the key is valid
		checkKey(key);

		// Gets the position of given key and rebalances the tree
		Position<Entry<K, V>> currentPosition = treeSearch(root(), key);
		rebalanceAccess(currentPosition);

		// Checks the position is external
		if(isExternal((currentPosition))){ return null; }

		// Returns the value of the position with given key
		return currentPosition.getElement().getValue();
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
	public V put(K key, V value) throws IllegalArgumentException {

		checkKey(key);
		Entry<K, V> entry = new MapEntry<>(key, value);
		Position<Entry <K, V>> position = treeSearch(root(), key);

		if(isExternal(position)){

			expandExternal(position, entry);
			rebalanceInsert(position);

			return null;
		} else {

			V old = position.getElement().getValue();
			set(position, entry);
			rebalanceAccess(position);

			return old;
		}
	}

	/**
	 * Removes the entry with the specified key, if present, and returns its
	 * associated value. Otherwise does nothing and returns null.
	 *
	 * @param key the key whose entry is to be removed from the map
	 * @return the previous value associated with the removed key, or null if no
	 *         such entry exists
	 */
	@Override
	public V remove(K key) throws IllegalArgumentException {

		checkKey(key);
		Position<Entry<K, V>> position = treeSearch(root(), key);

		if(isExternal(position)){

			rebalanceAccess(position);
			return null;

		} else {

			V old = position.getElement().getValue();

			if(isInternal(left(position)) && isInternal(right(position))){

				Position<Entry<K, V >> replacement = treeMax(left(position));
				set(position, replacement.getElement());
				position = replacement;
			}

			Position<Entry<K, V>> leaf;

			if(isExternal(left(position))){
				leaf = left(position);
			} else {
				leaf = right(position);
			}

			Position<Entry<K, V>> sib = sibling(leaf);
			remove(leaf);
			remove(position);
			rebalanceDelete(sib);
			return old;
		}
	}

	/**
	 * Returns the entry having the least key (or null if map is empty).
	 *
	 * @return entry with least key (or null if map is empty)
	 */
	@Override
	public Entry<K, V> firstEntry() {

		if (isEmpty()) { return null; }
		
		// Returns the element of the smallest key
		return treeMin(root()).getElement();
	}


	/**
	 * Returns the entry having the greatest key (or null if map is empty).
	 *
	 * @return entry with greatest key (or null if map is empty)
	 */
	@Override
	public Entry<K, V> lastEntry() {

		if (isEmpty()){ return null; }
		
		// Returns the element of the greatest key
		return treeMax(root()).getElement();
	}

	/**
	 * Returns the entry with least key greater than or equal to given key (or null
	 * if no such key exists).
	 *
	 * @return entry with least key greater than or equal to given (or null if no
	 *         such entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {

		// Checks the key is valid and finds the position of the key
		checkKey(key);
		Position<Entry<K, V>> p = treeSearch(root(), key);

		// If the key is internal its element is returned
		if(isInternal(p)){ return p.getElement(); }

		// Loops until the key smallest key greater than the given key is reached and returns its element
		while(!isRoot(p)){
			if(p == left(parent(p))){
				return parent(p).getElement();
			} else{
				p = parent(p);
			}
		}

		return null;
	}


	/**
	 * Returns the entry with greatest key less than or equal to given key (or null
	 * if no such key exists).
	 *
	 * @return entry with greatest key less than or equal to given (or null if no
	 *         such entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
		
		// Checks the key is valid and finds the position of the key
		checkKey(key);
		Position<Entry<K, V>> position = treeSearch(root(), key);
		
		// If the key is internal its element is returned
		if(isInternal(position)){ return position.getElement(); }
		
		// Loops until the key greatest key less than the given key is reached and returns its element
		while(!isRoot(position)){
			if(position == right(parent(position))){
				return parent(position).getElement();
			} else{
				position = parent(position);
			}
		}

		return null;
	}


	/**
	 * Returns the entry with greatest key strictly less than given key (or null if
	 * no such key exists).
	 *
	 * @return entry with greatest key strictly less than given (or null if no such
	 *         entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {

		// Checks the key is valid and gets the position of the given key
		checkKey(key);
		Position<Entry<K, V>> position = treeSearch(root(), key);

		// Gets the max value of the left child if the position has one
		if(isInternal(position) && isInternal(left(position))){ return treeMax(left(position)).getElement(); }

		// Loops until the parent of the current position is reach and returns it
		while(!isRoot(position)){
			if(position == right(parent(position))){
				return parent(position).getElement();
			}
			else{
				position = parent(position);
			}
		}

		return null;
	}

	/**
	 * Returns the entry with least key strictly greater than given key (or null if
	 * no such key exists).
	 *
	 * @return entry with least key strictly greater than given (or null if no such
	 *         entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {

		// Checks the key is valid and gets the position of the given key
		checkKey(key);
		Position<Entry<K, V>> position = treeSearch(root(), key);

		// Gets the max value of the left child if the position has one
		if(isInternal(position) && isInternal(left(position))){ return treeMax(left(position)).getElement(); }

		// Loops until the left of parent is reached
		while(!isRoot(position)){

			if(position == left(parent(position))){
				// Returns the element of the positions parent
				return parent(position).getElement();
			}
			else{
				position = parent(position);
			}
		}

		return null;

	}


	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {

		// Declares entry set
		ArrayList<Entry<K, V>> entry = new ArrayList<>(size());

		// Adds each entry in tree to the entry set
		for(Position<Entry<K, V>> positions: tree.inorder()){
			if(isInternal(positions)){
				entry.add(positions.getElement());
			}
		}

		// Returns the entry set
		return entry;
	}


	/**
	 * Returns an iterable containing all entries with keys in the range from
	 * fromKey inclusive to toKey exclusive.
	 *
	 * @return iterable with keys in desired range
	 * @throws IllegalArgumentException if fromKey or toKey is not compatible with the map
	 */
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {

		// Declares buffer to hold each entries
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();

		// Check that range of sub map is valid, if so generates sub map
		if(compare(fromKey, toKey) < 0){ subMapRecursive(fromKey, toKey, root(), buffer); }

		// Returns buffer
		return buffer;
	}


	/**
	 * Method to recursively generate a sub map of the Tree Map
	 * @param fromKey Key to start sub map at
	 * @param toKey Key to end sub map at
	 * @param p Current position in the tree
	 * @param buffer Buffer to add entries to
	 */
	private void subMapRecursive(K fromKey, K toKey, Position<Entry<K, V>> p, ArrayList<Entry<K, V>> buffer){

		// Checks that the current position is on the tree amp
		if(isInternal(p)){

			// Moves to the right of the tree if the starting key has not been reached
		 	if(compare(p.getElement(), fromKey) < 0){
		 		subMapRecursive(fromKey, toKey, right(p), buffer);
		    }

		 	// Moves to the left of the tree if the start key has not been reached
		 	else{
		 		subMapRecursive(fromKey, toKey, left(p), buffer);

		 		// Adds the element in the current position to the buffer and moves to the right of the tree
		 		if(compare(p.getElement(), toKey) < 0){
		 			buffer.add(p.getElement());
		 			subMapRecursive(fromKey, toKey, right(p), buffer);
			    }
		    }
		 }

	}


	/** Prints textual representation of tree structure (for debug purpose only). */
	protected void dump() {
		dumpRecurse(root(), 0);
	}


	/** This exists for debugging only */
	private void dumpRecurse(Position<Entry<K, V>> p, int depth) {
		String indent = (depth == 0 ? "" : String.format("%" + (2 * depth) + "s", ""));
		if (isExternal(p))
			System.out.println(indent + "leaf");
		else {
			System.out.println(indent + p.getElement());
			dumpRecurse(left(p), depth + 1);
			dumpRecurse(right(p), depth + 1);
		}
	}


	/**
	 * toString method for the Tree Map
	 * @return Formatted string of Tree Map
	 */
	public String toString(){

		StringBuilder string = new StringBuilder();
		string.append("[");

		for(Entry<K, V> entry: entrySet()){ string.append(entry.getValue()).append(", "); }

		string.setLength(string.length() - 2);
		string.append("]");

		return string.toString();
	}


	/** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	protected void rebalanceInsert(Position<Entry<K, V>> position) { }


	/** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	protected void rebalanceDelete(Position<Entry<K, V>> position) { }


	/** Overrides the TreeMap rebalancing hook that is called after a node access. */
	protected void rebalanceAccess(Position<Entry<K, V>> position) { }


	/**
	 * Method to rotate the tree from a given position
	 * @param p Position to rotate from
	 */
	protected void rotate(Position<Entry<K, V>> p) { tree.rotate(p); }


	public static void main(String[] args) {
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();

		Integer[] arr = new Integer[]{35,26,88,8,32,56,97,54,82,93,21,29,76,88};

		for(Integer i : arr){
			treeMap.put(i, i);
		}

		BinaryTreePrinter<Entry<Integer, Integer>> printer= new BinaryTreePrinter<>(treeMap.tree);
		System.out.println(printer.toString());
		treeMap.remove(8);
		printer= new BinaryTreePrinter<>(treeMap.tree);
		System.out.println(printer.print());
		System.out.println(treeMap.get(17));
	}
}
