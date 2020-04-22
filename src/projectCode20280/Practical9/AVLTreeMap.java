package projectCode20280.Practical9;

import projectCode20280.Practical5.Position;
import projectCode20280.Practical6.Entry;
import projectCode20280.Practical8.BinaryTreePrinter;
import projectCode20280.Practical8.TreeMap;

import java.util.Comparator;

/**
 * An implementation of a sorted map using an AVL tree.
 */

public class AVLTreeMap<K, V> extends TreeMap<K, V> {


	protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();


	/** Constructs an empty map using the natural ordering of keys. */
	public AVLTreeMap() { super(); }

	/**
	 * Constructs an empty map using the given comparator to order keys.
	 *
	 * @param comp comparator defining the order of keys in the map
	 */
	public AVLTreeMap(Comparator<K> comp) { super(comp); }

	/** Returns the height of the given tree position. */
	protected int height(Position<Entry<K, V>> position) { return tree.getAux(position); }

	/**
	 * Recomputes the height of the given position based on its children's heights.
	 */
	protected void recomputeHeight(Position<Entry<K, V>> position) { tree.setAux(position, 1 + Math.max(height(left(position)), height(right(position)))); }

	/** Returns whether a position has balance factor between -1 and 1 inclusive. */
	protected boolean isBalanced(Position<Entry<K, V>> position) { return Math.abs(height(left(position)) - height(right(position))) <= 1; }

	/** Returns a child of p with height no smaller than that of the other child. */
	protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> position) {

		if(height(left(position)) > height(right(position))){
			return left(position);
		}

		else if(height(left(position)) < height(right(position))){
			return right(position);
		}

		else if(isRoot(position)){
			return  left(position);
		}

		else if(position == left(parent(position))){
			return left(position);
		}

		else{
			return  right(position);
		}
	}

	/**
	 * Utility used to rebalance after an insert or removal operation. This
	 * traverses the path upward from p, performing a trinode restructuring when
	 * imbalance is found, continuing until balance is restored.
	 */
	protected void rebalance(Position<Entry<K, V>> position) {

		int oldHeight;
		int newHeight;

		do{

			oldHeight = height(position);

			if(!isBalanced(position)){

				position = restructure(tallerChild(tallerChild(position)));
				recomputeHeight(left(position));
				recomputeHeight(right(position));
			}

			recomputeHeight(position);
			newHeight = height(position);
			position = parent(position);

		} while (position != null && oldHeight != newHeight);
	}

	/** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	@Override
	protected void rebalanceInsert(Position<Entry<K, V>> position) {
		rebalance(position);
	}

	/** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	@Override
	protected void rebalanceDelete(Position<Entry<K, V>> position) {
		if(!isRoot(position)){
			rebalance(parent(position));
		}
	}

	/** Ensure that current tree structure is valid AVL (for debug use only). */
	private boolean sanityCheck() {
		for (Position<Entry<K, V>> p : tree.positions()) {
			if (isInternal(p)) {
				if (p.getElement() == null)
					System.out.println("VIOLATION: Internal node has null entry");
				else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
					System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
					dump();
					return false;
				}
			}
		}
		return true;
	}

	public Position<Entry<K, V>> restructure(Position<Entry<K, V>> position) {

		Position<Entry<K, V>> parentPosition = parent(position);
		Position<Entry<K, V>> grandparentPosition = parent(parentPosition);

		if((position == right(parentPosition)) == (parentPosition == right(grandparentPosition))){

			rotate(parentPosition);
			return  parentPosition;
		}

		else{
			rotate(position);
			rotate(position);
			return  position;
		}
	}



	public static void main(String [] args) {
		AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();
		Integer[] arr = new Integer[] { 44, 17, 88, 8, 32, 65, 97, 28, 54, 82, 93 , 76, 80 };
		for (Integer i : arr) {
			avl.put(i, i);
		}
		BinaryTreePrinter< Entry<Integer, Integer> > printer = new BinaryTreePrinter<>(avl.tree);

		System.out.println(printer.print());

		avl.remove(arr[0]);

		System.out.println("avl: " + avl);
	}
}