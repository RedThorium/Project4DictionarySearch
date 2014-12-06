import java.util.*;
import java.lang.*;

public class BinarySearchTree<T extends Comparable<T>> implements Iterator<T>{
	protected BSTNode<T> root;
	
	/**
	 * Adds a node to the tree at the leaves
	 * @param data - any type of obejct to be added to 
	 */
	public void add(T data){
		root = redAdd(root, data);
	}
	
	/**
	 * The recursive portion of the add method 
	 * @return
	 */
	private BSTNode<T> redAdd(BSTNode<T> node, T newData){
		if (node == null){
			BSTNode<T> newNode = new BSTNode<T>(newData);
			return newNode;
		}
		if (newData.compareTo(node.getData()) < 0){
			node.setLeft(redAdd(node.getLeft(), newData));
		}
		else{
			node.setRight(redAdd(node.getRight(), newData));
		}
		return node;
	}
	
	public void remove(){
		try {
			throw new NoSuchMethodException();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			System.err.println("This method does not exist");
		}
	}
	
	public BSTNode get(T item, BSTNode currentNode){
		if(currentNode == null){
			return null;
		}
		else if(item.compareTo((T) currentNode.getData()) < 0){
			return get(item, currentNode.getLeft());
		}
		else if(item.compareTo((T) currentNode.getData()) > 0){
			return get(item, currentNode.getRight());
		}
		else{
			return currentNode;
		}
	}
	
	public boolean contains(T item){
		return recContains(item, root);
	}
	
	private boolean recContains(T item, BSTNode currentNode){
		if(currentNode == null){
			return false;
		}
		else if(item.compareTo((T)currentNode.getData()) < 0){
			return recContains(item, currentNode.getLeft());
		}
		else if(item.compareTo((T)currentNode.getData()) > 0){
			return recContains(item, currentNode.getRight());
		}
		else{
			return true;
		}
	}
	
	public boolean containsPrefix(T prefix){
		return recContainsPrefix(prefix, root);
	}
	
	private boolean recContainsPrefix(T prefix, BSTNode currentNode){
		String word = (String)currentNode.getData();
		if(currentNode == null){
			return false;
		}
		else if(prefix.compareTo((T)currentNode.getData()) < 0){
			return recContains(prefix, currentNode.getLeft());
		}
		else if(prefix.compareTo((T)currentNode.getData()) > 0){
			return recContains(prefix, currentNode.getRight());
		}
		else if(word.startsWith((String)prefix)){
			return true;
		}
		else{
			return recContains(prefix, currentNode.getLeft()) || recContains(prefix, currentNode.getRight());
		}
	}
	
	public void iterator(BSTNode currentNode, ArrayList array){
		recIterator(currentNode, array);
	}
	
	public void recIterator(BSTNode currentNode, ArrayList array){
		if (currentNode != null){
			recIterator(currentNode.getLeft(), array);
			array.add(currentNode);
			recIterator(currentNode.getRight(), array);
		}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
}
