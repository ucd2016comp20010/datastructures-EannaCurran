package projectCode20280.Practical5;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {


    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {


        E element;    // Element stored in the Node
        Node<E> parent;    // Parent Node of this Node
        Node<E> left;    // Left child Node of this Node
        Node<E> right;    // Right child node of this Node


        /**
         * Constructor for Node
         * @param e: Element stored in the Node
         * @param parent: Parent Node of this Node
         * @param left: Left child Node of this Node
         * @param right: Right child node of this Node
         */
        public Node(E e, Node<E> parent, Node<E> left, Node<E> right) {

          this.element = e;
          this.parent = parent;
          this.left = left;
          this.right = right;
        }


        /**
         * Getter method for element
         * @return Element stored in the Node
         */
        public E getElement() { return this.element; }


        /**
         * Getter method for parent
         * @return Parent Node of this Node
         */
        public Node<E> getParent() { return this.parent; }


        /**
         * Getter method for left
         * @return Left child Node of this Node
         */
        public Node<E> getLeft() { return this.left; }


        /**
         * Getter method for right
         * @return Right child Node of this Node
         */
        public Node<E> getRight() { return this.right; }


        /**
         * Setter method for element
         * @param e: New element to be set
         */
        public void setElement(E e){ this.element = e; }


        /**
         * Setter method for parent
         * @param parent: New parent to be set
         */
        public void setParent(Node<E> parent){ this.parent = parent; }


        /**
         * Setter method for left
         * @param left: New left Node child to be set
         */
        public void setLeft(Node<E> left){ this.left = left; }


        /**
         * Setter method for right
         * @param right: New right Node child to be set
         */
       public void setRight(Node<E> right){ this.right = right; }

       public String toString(){
           if(this.getElement() == null){
               return "null";
           }
           return this.getElement().toString();
       }


    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) { return new Node<>(e, parent, left, right); }

    // LinkedBinaryTree instance variables
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null;     // root of the tree

    /**
     * The number of nodes in the binary tree
     */
    private int size = 0;              // number of nodes in the tree

    // constructor

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    }      // constructs an empty binary tree

    // nonpublic utility

    /**
     * Verifies that a Position belongs to the appropriate class, and is
     * not one that has been previously removed. Note that our current
     * implementation does not actually verify that the position belongs
     * to this particular list instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            return null;
        Node<E> node = (Node<E>) p;       // safe cast
        if (node.getParent() == node)     // our convention for defunct node
           throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {

        // Checks that p is a Node in the Tree, is so it's Parent Node is returned
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {

        // Checks that p is a Node in the Tree, is so it's left child Node is returned
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {

        // Checks that p is a Node in the Tree, is so it's right child Node is returned
        Node<E> node = validate(p);
        return node.getRight();
    }

    // update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {

        // Checks that the list is not empty, if so IllegalStateException is thrown
        if (!isEmpty()) {

            throw new IllegalStateException("Cannot add root to non empty Tree");
        }

        // Sets root to a new Node with element e;
        root = createNode(e, null, null, null);

        // Increases the size of the Tree
        size++;

        // Returns the new root
        return root;
    }

    public void insert(E e) {
        //recursively add from root
        root = addRecursive(root, e);
        ++size;
    }

    //recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {

        if(p == null){
            return createNode(e, null, null, null);
        }

            if (e.compareTo(p.getElement()) < 0) {

                p.setLeft(addRecursive(p.getLeft(), e));
            } else {

                p.setRight(addRecursive(p.getRight(), e));
            }

        return p;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {

        Node<E> parent = validate(p);

        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("Parent Node already has a left child Node");
        }

        Node<E> leftChild = createNode(e, parent, null, null);

        parent.setLeft(leftChild);

        size++;

        return leftChild;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {

        Node<E> parent = validate(p);

        if (parent.getRight() != null) {
            throw new IllegalArgumentException("Parent Node already has a left child Node");
        }

        Node<E> rightChild = createNode(e, parent, null, null);

        parent.setRight(rightChild);

        size++;

        return rightChild;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {

        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return  temp;

    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        //TODO
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {

        Node<E> n = (Node<E>) p;

        if(numChildren(n) == 2){
            throw new IllegalArgumentException("Cannot remove node with two children");
        }

        Node<E> child;
        if(n.getLeft() != null){
            child = n.getLeft();
        } else {
            child = n.getRight();
        }

        if(child != null){
            child.setParent(n.getParent());
        }

        if(n == root){
            root = child;
        }else {
            Node<E> parent = n.getParent();
            if(n == parent.getLeft()){
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        size--;
        return n.getElement();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Position<E> p : positions()) {
            sb.append(p.getElement());
            sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();

        int[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
        for (int i : arr) {
            bt.insert(i);
        }

    }
} 

