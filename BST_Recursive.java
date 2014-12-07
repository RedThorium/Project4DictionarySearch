import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Provides recursive implementation of a generic binary search tree (BST). 
 * @author Joanna Klukowska
 *
 * @param <T> any type that implements Comparable <T> 
 */
public class BST_Recursive<T extends Comparable<T>>  {

	private BSTNode<T> root;
	
	private int numOfElements;

	private Queue<T> queue;

	/**
	 * Creates an empty BST.
	 */
	public BST_Recursive() {
		this.root = null;
		numOfElements = 0;
	}

	/**
	 * Add an item to this BST.
	 * @param item a new element to be added to the tree (ignored if null)
	 */
	public void insert(T item) {
		if (item!=null)
			root = recInsert(item, root);

	}

	/*
	 * Recursively add an item to this BST.
	 * @param item item to be added
	 * @param tree root of the subtree into which the node will be added
	 * @return reference to this BST after the item was inserted
	 */
	private BSTNode<T> recInsert(T item, BSTNode<T> tree) {
		if (tree == null) {
			// Addition place found
			tree = new BSTNode<T>(item);
			numOfElements++;
		} else if (item.compareTo(tree.getData()) <= 0)
			tree.setLeft(recInsert(item, tree.getLeft())); // Add in left 
															// subtree
		else
			tree.setRight(recInsert(item, tree.getRight())); // Add in right
																// subtree
		return tree;
	}

	/**
	 * Remove the given item from this BST. If item is null or is not found
	 * in this BST, the three does not change.
	 * @param item an element to be removed.
	 */
	public void remove(T item) {
		if (item != null)
			root = recRemove(item, root);
	}

	/*
	 * Recursively remove an item from this BST.
	 * @param item  item to be removed
	 * @param tree  root of the subtree from which the item will be removed 
	 * @return reference to this BST after the item was removed 
	 */
	private BSTNode<T> recRemove(T item, BSTNode<T> tree) {

		if (tree == null)
			; // do nothing, item not found
		else if (item.compareTo(tree.getData()) < 0)
			tree.setLeft(recRemove(item, tree.getLeft()));
		else if (item.compareTo(tree.getData()) > 0)
			tree.setRight(recRemove(item, tree.getRight()));
		else {
			tree = removeNode(tree);
		}
		return tree;
	}

	/*
	 * Remove a particular node - the actual action depends on number of 
	 * childred that the node has. 
	 * @param tree the node to be removed 
	 * @return reference to this BST after node was removed 
	 */
	private BSTNode<T> removeNode(BSTNode<T> tree) {
		T data;


		if (tree.getLeft() == null) {
			numOfElements--;
			return tree.getRight();
		}
		else if (tree.getRight() == null) {
			numOfElements--;
			return tree.getLeft();
		}
		else {
			data = getPredecessor(tree.getLeft());
			tree.setData(data);
			tree.setLeft(recRemove(data, tree.getLeft()));
			return tree;
		}
	}

	/*
	 * Obtains the predecessor of a node (according to BST ordering). 
	 * @param tree node whose predecessor we are after
	 * @return the data contained in the predecessor node
	 */
	private T getPredecessor(BSTNode<T> tree) {
		while (tree.getRight() != null)
			tree = tree.getRight();
		return tree.getData();
	}

	/**
	 * Returns a reference to the item stored in this BST whose value is 
	 * equal to the value of the parameter.  
	 * @param item the value whose reference in the BST we are after
	 * @return null, if the node with value equal to item was not found, or a reference
	 * to that value if found
	 */
	public T get(T item) {
		return recGet(item, root);
	}

	/*
	 * Recursively obtain the reference to the item stored in this BST whose value
	 * is equal to the value of the parameter.  
	 * @param item the value whose reference in the BST we are after
	 * @param tree root of the current subtree in which we are looking for the item
	 * @return null, if the node with value equal to item was not found, or a reference
	 * to that value if found
	 */
	private T recGet(T item, BSTNode<T> tree) {
		if (tree == null)
			return null; // element is not found
		else if (item.compareTo(tree.getData()) < 0)
			return recGet(item, tree.getLeft()); // get from left subtree
		else if (item.compareTo(tree.getData()) > 0)
			return recGet(item, tree.getRight()); // get from right subtree
		else
			return tree.getData(); // element is found
	}

	/**
	 * Returns next element from this BST according to its inorder traversal. 
	 * This call can be made only after the call to resetNext() method was made. 
	 * @return a reference to the next element in the tree or null if the "end"
	 * of the tree is reached  or no call to resetNext() was made.
	 */
	public T getNext() {
		if (queue == null || queue.isEmpty())
			return null;
		else
			return queue.remove();
	}

	/**
	 * Resets the next item to the first item in this BST according to its 
	 * inorder traversal. 
	 */
	public void resetNext(ArrayList<T> temp) {
		inOrder(root, temp);

//		System.out.println(queue);
	}

	/*
	 * Performs inorder traversal of this BST and fills the queue with the 
	 * references to data in appropriate order. 
	 * @param tree root of the current subtree
	 */
	private void inOrder(BSTNode<T> tree, ArrayList<T> temp)
	// Initializes inOrderQueue with tree elements in inOrder order.
	{
		if (tree != null) {
			inOrder(tree.getLeft(), temp);
			temp.add(tree.getData());
			inOrder(tree.getRight(), temp);
		}
	}

	/**
	 * Returns the number of elements stored in this BST.
	 * @return number of elements in this BST
	 */
	public int size() {
		return numOfElements;
	}
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		//inOrderPrint(root, s);
		postOrderPrint(root, 0, s);
		return s.toString();
	}

	/*
	 * Computes a string representation of the this BST
	 * using its inorder traversal. 
	 * @param tree the root of the current subtree
	 * @param s the string that accumulated the string representation
	 * of this BST
	 */
	private void inOrderPrint(BSTNode<T> tree, StringBuilder s)
	// Initializes inOrderQueue with tree elements in inOrder order.
	{
		if (tree != null) {
			inOrderPrint(tree.getLeft(), s);
			s.append(tree.getData().toString() + "\n");
			inOrderPrint(tree.getRight(), s);
		}
	}
	

	
	/*
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree
	 * to determine the indentation of each item 
	 * @param output the string that accumulated the string representation
	 * of this BST
	 */
	private void postOrderPrint(BSTNode<T> tree, int level, StringBuilder output )
	{
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.getData());
			postOrderPrint(tree.getLeft(), level + 1, output);
			postOrderPrint(tree.getRight(), level + 1, output);
		}
	}
	
	/**
	 * This method determines if the BST has words with the prefix 
	 * @param prefix
	 * @return
	 */
	public boolean containsPrefix(T prefix){
		return recContainsPrefix(prefix, root);
	}
	
	/*
	 * The recursive step of the contains prefix method
	 */
	private boolean recContainsPrefix(T prefix, BSTNode currentNode){
		if(currentNode == null){
			return false;
		}
		else{
			//Creates a variable string to store the data of the node as a string
			String word = (String)(currentNode.getData());
			if(prefix.compareTo((T)currentNode.getData()) < 0){
				return recContainsPrefix(prefix, currentNode.getLeft());
			}
			else if(prefix.compareTo((T)currentNode.getData()) > 0){
				return recContainsPrefix(prefix, currentNode.getRight());
			}
			else if(word.startsWith((String)prefix)){
				return true;
			}
			else{
				return recContainsPrefix(prefix, currentNode.getLeft()) || recContainsPrefix(prefix, currentNode.getRight());
			}
		}
	}
}
