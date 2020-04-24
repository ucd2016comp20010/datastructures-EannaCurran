// Practical 5 - Question 1 -  Linked Binary Tree - Eanna Curran

package projectCode20280.Practical5;

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


        /**
         * toString method for the node
         * @return Formatted Node
         */
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


    /**
     * The root of the binary tree
     */
    protected Node root = null;     // root of the tree


    /**
     * The number of nodes in the binary tree
     */
    private int size = 0;              // number of nodes in the tree


    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() { }      // constructs an empty binary tree


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


    /**
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Returns the root Position of the tree (or null if tree is empty).
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


    /**
     * Method to recursively add element to a node on the tree
     * @param position Node to check if the element is in the right position
     * @param element New element to be added
     * @return new Node added to the tree
     */
    private Node<E> addRecursive(Node<E> position, E element) {

        // Check if the current position is corret
        if(position == null) {
            position = new Node<>(element, null, null, null);
            return position;
        }

        // Moves to the left or right node depending on the value of the node
        int n = Integer.parseInt(element.toString());
        int m = Integer.parseInt(position.getElement().toString());

        if(n >= m){
            position.right = addRecursive(position.getRight(), element);
        } else {
            position.left = addRecursive(position.getLeft(), element);
        }

        return position;
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

        // Validates the position
        Node<E> parent = validate(p);

        // Checks if the node already has a left value
        if (parent.getLeft() != null) { throw new IllegalArgumentException("Parent Node already has a left child Node"); }

        // Creates left child node and adds it to the tree
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

        // Validates the position
        Node<E> parent = validate(p);

        // Checks if the node already has a right child
        if (parent.getRight() != null) { throw new IllegalArgumentException("Parent Node already has a left child Node"); }

        // Creates right child node and adds it to the tree
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

        // Validates the positions and sets its new value
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);

        // Returns old value
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

        // Validates the position
        Node<E> node = validate(p);

        // Checks if p is a leaf on the tree
        if(isInternal(p)){ throw new IllegalArgumentException("P is not a leaf"); }

        // Updates the size of the tree
        size += t1.size() + t2.size();

        // Merges the first tree
        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }

        // Merges the second tree
        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
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

        // Validates the position
        Node<E> n = validate(p);

        // Checks that the position doesn't have two children
        if(numChildren(n) == 2){ throw new IllegalArgumentException("Cannot remove node with two children"); }

        // Gets the child node
        Node<E> child;
        if(n.getLeft() != null){
            child = n.getLeft();
        } else {
            child = n.getRight();
        }

        // Sets the child new parent
        if(child != null){
            child.setParent(n.getParent());
        }

        // Removes the node
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


    /**
     * toString method for the Linked Binary Tree
     * @return Formatted string
     */
    public String toString() {
        return (positions().toString());
    }


    /**
     * Method to create a level ordered tree from a array of elements
     * @param arr Array of elements to create tree from
     * @return Tree in ordered form
     */
    public Node<E> createLevelOrder(E[] arr){

        // Loops through array and adds each element to the tree in the orderd position
        for(E i: arr){
            root = addRecursive(root, i);
            size++;
        }
        return root;
    }


    /*
     * Assignment 2 Code
     */

    /**
     * Method to check if a binary tree is symmetrical
     * @return Boolean result of check
     */
    public boolean isSymmetrical(){

        // Calls recursive helper method to check if tree is symmetrical
        return symmetricalChecker(root, root);
    }


    /**
     * Recursive helper method to check if two nodes are and their child nodes are symmetrical
     * @param node1 First node to check
     * @param node2 Second node to check
     * @return Boolean result of if two nodes are symmetrical
     */
    private boolean symmetricalChecker(Node node1, Node node2){

        // Base case when both nodes are null, returns true
        if(node1 == null && node2 == null){
            return true;
        }

        // Recursive call to check if the each child node of both nodes are symmetrical
        if(node1 != null && node2 != null){
            return symmetricalChecker(node1.getLeft(), node2.getRight()) && symmetricalChecker(node1.getRight(), node2.getLeft());
        }

        // Otherwise the nodes are not symmetrical since one of them is null and returns false
        return false;
    }


    /**
     * Method to convert a binary tree to its mirror image
     */
    public void mirror(){

        // Calls recursive helper method to flip the child nodes of each node
        root = mirrorNode(root);
    }


    /**
     * Recursive helper method that mirrors the child nodes of a given node
     * @param node Node to create mirror of
     * @return Mirrored node
     */
    private Node mirrorNode(Node node){

        // Base case when the given node is null
        if(node == null){
            return null;
        }

        // Recursive call to get the mirrors of the child node
        Node newRight = mirrorNode(node.getLeft());
        Node newLeft = mirrorNode(node.getRight());

        // Flips the two child nodes
        node.right = newRight;
        node.left = newLeft;

        // Returns the mirrored node
        return node;
    }


    /**
     * Method to find the shortest distance between two nodes on a binary tree
     * @param node1 First node to find distance between
     * @param node2 Second node to find distance between
     * @return Number of steps between the two nodes
     */
    public int dis(Node node1, Node node2){

        // Finds the distance from node 1 to the root
        int nodeOneToRoot = distanceFromRoot(node1);

        // Finds the distance from node 2 to the root
        int nodeTwoToRoot = distanceFromRoot(node2);

        // Finds the lowest common parent between node 1 and node 2
        Node lowestParent = lowestCommonParent(root, node1, node2);

        // Finds the distance between the parent and the root
        int parentToRoot = distanceFromRoot(lowestParent);

        // Calculates the distance between node 1 and node 2
        return nodeOneToRoot + nodeTwoToRoot - (2* parentToRoot);
    }

    /**
     * Method to count the distance between a given node and the root
     * @param node Node to find distance from root
     * @return Distance from node to root
     */
    private int distanceFromRoot(Node node){

        int count = 0;

        // Base case if the node is null or is the root
        if(node == null || node == root){
            return count;
        }

        // Counts the number of steps to parent nodes until the root node is reached
        for(Node parent = node.getParent(); parent != null; parent = parent.getParent()){
            count++;
        }

        // Returns the result
        return count;
    }

    /**
     * Recursive method to find the lowest common parent between two nodes
     * @param root Root node
     * @param node1 First node to find lowest common parent
     * @param node2 Second node to find lowest common parent
     * @return Lowest common parent of node 1 and 2
     */
    private Node lowestCommonParent(Node root, Node node1, Node node2){

        // Base case if the root is null
        if(root == null){
            return null;
        }

        // Second base case if either node is the root
        if(node1 == root || node2 == root){
            return root;
        }

        // Recursive call to find the lowest common parent of the nodes and the current roots children
        Node leftChild = lowestCommonParent(root.left, node1, node2);
        Node rightChild = lowestCommonParent(root.right, node1,node2);

        // Case when both children of the root are not null
        if(leftChild != null && rightChild != null){
            return root;
        }

        // If the left child is null, then the right child is returned and vise versa
        if(leftChild == null){
            return  rightChild;
        }
        else{
            return leftChild;
        }
    }


    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();

    }
} 

