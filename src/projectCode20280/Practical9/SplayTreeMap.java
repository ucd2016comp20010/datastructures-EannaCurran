// Practical 9 - Question 2 - Splay Tree Map - Eanna Curran

package projectCode20280.Practical9;
import projectCode20280.Practical5.Position;
import projectCode20280.Practical6.Entry;
import projectCode20280.Practical8.TreeMap;

import java.util.Comparator;

public class SplayTreeMap<K,V> extends TreeMap<K,V> {


	protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();


	/** Constructs an empty map using the natural ordering of keys. */
	public SplayTreeMap() { super(); }


	/**
	 * Constructs an empty map using the given comparator to order keys.
	 * @param comp comparator defining the order of keys in the map
	 */
	public SplayTreeMap(Comparator<K> comp) { super(comp); }


	/** Utility used to rebalance after a map operation. */
	private void splay(Position<Entry<K,V>> position) {

		while(!isRoot(position)){

			Position<Entry<K, V>> parent = parent(position);
			Position<Entry<K, V>> grandParent = parent(parent);

			if(grandParent == null){
				rotate(position);
			}

			else if((parent == left(grandParent)) == (position == left((parent)))){
				rotate(parent);
				rotate(position);
			}

			else{
				rotate(position);
				rotate(position);
			}
		}
	}


	/** Overrides the TreeMap rebalancing hook that is called after a node access. */
	@Override
	protected void rebalanceAccess(Position<Entry<K,V>> position) {

		if(isExternal(position)){
			position = parent(position);
		}

		if(position != null){
			splay(position);
		}
	}


	/** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	@Override
	protected void rebalanceInsert(Position<Entry<K,V>> position) {

		splay(position);

	}


	/** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	@Override
	protected void rebalanceDelete(Position<Entry<K,V>> position) {

		if(!isRoot(position)){
			splay(parent(position));
		}
	}
}