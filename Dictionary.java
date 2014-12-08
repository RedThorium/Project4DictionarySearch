
import java.util.ArrayList;
import java.util.List;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * 
 * @author Alex Wong Modified from Lecture 6 files
 *
 */
public class Dictionary {
	//actual storage for the words
	private BST_Recursive<String> words;
	
	
	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary ( ) {
		words = new BST_Recursive() ;
	}
	
	/**
	 * Creates a new Dictionary object from this Dictionary object that 
	 * contains words of a specified size.
	 * @param size length of the words that should be included in the new 
	 * Dictionary object
	 * @return a new Dictionary object containing only the words of specified 
	 * size
	 */
	public Dictionary getWordsBySize ( int size ) {
		ArrayList <String> temp = new ArrayList<String> ();
		ArrayList <String> wordsBySize = new ArrayList<String> ();
		words.resetNext(temp);
		for (int i = 0; i < temp.size(); i++){
			if (temp.get(i).length() == size)
				wordsBySize.add(temp.get(i));
		}
		return new Dictionary (wordsBySize);
	}
	
	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		if (null == words) {
			words = new BST_Recursive() ;
		}

			for(int i = 0; i < listOfWords.size(); i++){
				words.insert(listOfWords.get(i));
				//Re-balances the tree every 50 inserts
				if(i % 50 == 0)
				{
					words = words.balance();
				}
		}
	}
	 
	/**
	 * Performs search in this Dictionary object for a given word.
	 * @param word  the word to look for in this Dictionary object. 
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	public boolean isWordInDictionary ( String word ) {
		return words.get(word) != null;
	}

	
	/**
	 * Performs (binary) search in this Dictionary object for a given prefix.
	 * @param prefix  the prefix to look for in this Dictionary object. 
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	public boolean isPrefixInDictionary (String prefix ) {
		return  words.containsPrefix(prefix);
		//return isPrefixInDictionaryRecursive (prefix, 0, words.size() - 1 );
	}	

}
