package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		if (word == null || word.length() < 1 || isWord(word.toLowerCase()))
			return false;

		word = word.toLowerCase();
		TrieNode test = root;
		TrieNode prev = null;

		for (int i = 0; i < word.length(); i++) {
			prev = test;
			test = test.insert(word.charAt(i));

			if (test == null) {
				test = prev.getChild(word.charAt(i));
			}

			if (i == word.length() - 1) {
				test.setEndsWord(true);
			}
		}

		size++;
		return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		if(s == null || s.length() < 1)
			return false;

		String lowerWord = s.toLowerCase();
		TrieNode curr = root;
		for(int i = 0; i < lowerWord.length(); i++) {
			if(curr.getChild(lowerWord.charAt(i)) != null) {
				curr = curr.getChild(lowerWord.charAt(i));
			}
		}
		if(curr.getText().equals(lowerWord) && curr.endsWord())
			return true;
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 String lowerWord = prefix.toLowerCase();
    	 TrieNode curr = root;
    	 List<String> completions = new ArrayList<String>();
    	 
    	 for(int i = 0; i < lowerWord.length(); i++) {
  			if(curr.getChild(lowerWord.charAt(i)) != null) {
  				curr = curr.getChild(lowerWord.charAt(i));
  			} else {
  				return completions;
  			}
    	 }
    	 Queue<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.add(curr);
    	 
    	 int counter = 0;
    	 while(!queue.isEmpty() && counter < numCompletions) {
    		 TrieNode head = queue.remove();
    		 if(isWord(head.getText())) {
    			 completions.add(head.getText());
        		 counter++;
    		 }
    		 queue.addAll(getChildren(head));
    	 }
    	 //System.out.println(completions);
    	 return completions;
    	 
     }
     
     private Queue<TrieNode> getChildren(TrieNode node) {
    	 Set<Character> childrenVal =  node.getValidNextCharacters();
    	 Queue<TrieNode> queue = new LinkedList<TrieNode>();
    	 for(Character ch : childrenVal) {
    		 queue.add(node.getChild(ch));
    	 }
    	 return queue;
     }
     
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
	
}