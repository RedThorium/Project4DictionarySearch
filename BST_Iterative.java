
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class provides representation of a generic binary search tree. 
 * The all the methods are implemented iteratively, so there is no risk
 * of stack overflow with very "skinny" trees. 
 * 
 * @author Joanna Klukowska
 *
 * @param <T> generic type of data that is stored in nodes of the tree; needs to
 * implement Comparable<T> interface
 */
public class BST_Iterative<T extends Comparable<T>> {
	
	//root of the tree
	private BSTNode<T> root;
	//current number of nodes in the tree
	private int numOfElements;

	//queue used for implementation of some of the methods below
	private Queue<T> queue;

	/**
	 * Default constructor that creates an empty tree. 
	 */
	public BST_Iterative() {
		this.root = null;
		numOfElements = 0;
	}

	/**
	 * Add the given data item to the tree. 
	 * If item is null, the tree does not change.
	 * @param item the new element to be added to the tree
	 */
	public void insert(T item) {
		if (item == null)
			return;
		if (root == null)
			// create a new node and make it the root the tree 
			root = new BSTNode<T>(item); 
		else {
			// Locate the parent node for the new node
			BSTNode<T> parent = null;
			BSTNode<T> current = root;
			// go down one of the branches of the tree until the
			// proper place for the new node is found
			while (current != null) {
				if (item.compareTo(current.getData()) < 0) {
					parent = current;
					current = current.getLeft();
				} else {
					parent = current;
					current = current.getRight();
				}
			}
			// Create the new node and attach it to the parent node
			if (item.compareTo(parent.getData()) < 0)
				parent.setLeft(new BSTNode<T>(item));
			else
				parent.setRight(new BSTNode<T>(item));
		}
		numOfElements++;
	}

	/**
	 * Remove the item from the tree. 
	 * If item is null the tree remains unchanged. 
	 * If item is not found in the tree, the tree remains unchanged.  
	 * @param item
	 */
	public void remove(T item) {
		if (item == null)
			return;
		// Locate the node to be deleted and also locate its parent node
		BSTNode<T> parent = null;
		BSTNode<T> current = root;
		while (current != null) {
			if (item.compareTo(current.getData()) < 0) {
				parent = current;
				current = current.getLeft();
			} else if (item.compareTo(current.getData()) > 0) {
				parent = current;
				current = current.getRight();
			} else
				break; // Element is in the tree pointed at by current
		}

		if (current == null)
			return; // Element is not in the tree

		// Case 1: current has no left children
		if (current.getLeft() == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				root = current.getRight();
			} else {
				if (item.compareTo(parent.getData()) < 0)
					parent.setLeft(current.getRight());
				else
					parent.setRight(current.getRight());
			}
		} else {
			// Case 2: The current node has a left child
			// Locate the rightmost node in the left subtree of
			// the current node and also its parent
			BSTNode<T> parentOfRightMost = current;
			BSTNode<T> rightMost = current.getLeft();

			while (rightMost.getRight() != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.getRight(); // Keep going to the right
			}

			// Replace the item in current by the item in rightMost
			current.setData(rightMost.getData());

			// Eliminate rightmost node
			if (parentOfRightMost.getRight() == rightMost)
				parentOfRightMost.setRight(rightMost.getLeft());
			else
				// Special case: parentOfRightMost == current
				parentOfRightMost.setLeft(rightMost.getLeft());
		}

		numOfElements--;
	}
	
	/**
	 * Returns a reference to the item stored in this BST whose value is 
	 * equal to the value of the parameter.  
	 * @param item the value whose reference in the BST we are after
	 * @return null, if the node with value equal to item was not found, or a reference
	 * to that value if found
	 */
	public T get(T item) {
		BSTNode<T> current = root; // Start from the root
		if (item == null) return null;
		while (current != null) {
			if (item.compareTo(current.getData()) < 0) {
				current = current.getLeft();
			} else if (item.compareTo(current.getData()) > 0) {
				current = current.getRight();
			} else
				// element matches current.element
				return current.getData(); // Element is found
		}

		return null;
	}


	/**
	 * Performs an inorder traversal of this BST and prints the results
	 * to standard output.  
	 */
	public void inOrder()
	{
		if (root != null) {
			Stack<BSTNode<T>> tmpStack = new Stack<BSTNode<T>>();
			queue = new LinkedList<T>();
			BSTNode<T> current = root;
			boolean done = false;
			int iterCounter = 0;

			System.out.printf(
					"iter: %2d current: %4s stack: %-12s processed: %s\n",
					iterCounter, current, tmpStack, queue);

			while (!done) {
				iterCounter++;
				if (current != null) {
					tmpStack.add(current);
					current = current.getLeft();
					System.out
							.printf("iter: %2d current: %4s stack: %-12s processed: %s\n",
									iterCounter, current, tmpStack, queue);
				} else if (!tmpStack.empty()) {
					current = tmpStack.pop();
					System.out
							.printf("iter: %2d current: %4s stack: %-12s processed: %s\n",
									iterCounter, current, tmpStack, queue);

					queue.add(current.getData());
					current = current.getRight();
					System.out
							.printf("iter: %2d current: %4s stack: %-12s processed: %s\n",
									iterCounter, current, tmpStack, queue);
				} else
					done = true;
			}
		}
	}

	/**
	 * Determines the number of elements stored in this BST. 
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
		inOrderPrint(root, s);
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
	private void postOrderPrint(BSTNode<T> tree, int level, StringBuilder output)
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
	 * Produces tree like string representation of this BST.
	 * @return string containing tree-like representation of this BST. 
	 */
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();

		postOrderPrint(root, 0, s);
		return s.toString();
	}

}
