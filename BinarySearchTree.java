import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements Iterator<T>{
	protected BSTNode<T> root;
	
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
	
	public T remove(){
		
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
	
	
	public ArrayList<T> iterator(){
		
	}
}
