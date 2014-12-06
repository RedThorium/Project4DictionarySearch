
import java.util.ArrayList;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * 
 * @author Joanna Klukowska
 *
 */
public class Dictionary {
	//actual storage for the words
	private BinarySearchTree words;
	
	
	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary ( ) {
		words = new BinarySearchTree() ;
	}
	
	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		for(int i = 0; i < listOfWords.size(); i++){
			words.add(listOfWords.get(i));
		}
		if (null == words) {
			words = new BinarySearchTree() ;
		}
	}
	 
	/**
	 * Performs search in this Dictionary object for a given word.
	 * @param word  the word to look for in this Dictionary object. 
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	public boolean isWordInDictionary ( String word ) {
		return words.contains(word);
	}

	
	/**
	 * Performs (binary) search in this Dictionary object for a given prefix.
	 * @param prefix  the prefix to look for in this Dictionary object. 
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	public boolean isPrefixInDictionary (String prefix ) {
		
		
		//return isPrefixInDictionaryRecursive (prefix, 0, words.size() - 1 );
	}

	/*
	 * The actual method providing recursive implementation of the binary search
	 * for the prefix. 
	 * @param prefix the prefix to look for in this Dictionary object.
	 * @param begin start of the range for the current iteration
	 * @param end end of the range for the current iteration
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise'
	 * 
	 */
	private boolean isPrefixInDictionaryRecursive(String prefix, int begin, int end) {
		if (begin > end )
			return false;
				
		int half = (begin+end+1) / 2;
		int comparison = words.get(half).compareToIgnoreCase(prefix);
		boolean isPrefix = words.get(half).startsWith(prefix);
		if (isPrefix) 
			return true;
		
		if (comparison < 0 )
			return isPrefixInDictionaryRecursive( prefix, half + 1, end );
		else if ( comparison > 0 )
			return isPrefixInDictionaryRecursive( prefix, begin, half - 1);
		else  //this case should never happen
			return true;
	}
	
	
	
}
