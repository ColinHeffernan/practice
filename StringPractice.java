import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * StringPractice - A class to practice manipulating strings
 * 
 * @author Colin Heffernan
 */
public class StringPractice {

	/**
	 * generateWords - returns an collection with all possible strings containing A-Z
	 * characters up to a specified length
	 * this is very similar to getPossibleWords but with a set alphabet and the collection
	 *  contains all strings up to maxLength, not just of one length
	 * @param maxLength
	 * @return the collection of the strings
	 */
	public static Collection<String> generateWords(int maxLength) {
		Collection<String> words = new ArrayList<String>();
		if (maxLength == 1){
			for(int i = 0; i < 26; i++){
				words.add(Character.toString((char) ((int) 'A' + i)));
			}
		}
		else if (maxLength > 1){
			Collection<String> prev = generateWords(maxLength - 1);
			Iterator<String> iter = prev.iterator();
			String current;
			words.addAll(prev);
			while(iter.hasNext()){
				current = iter.next();
				for(int i = 0; i < 26; i ++){
					words.add(current + (char) ((int) 'A' + i));
				}
			}
		}
		return words;
	}

	/**
	 * getPossibleWords - creates a collection of all possible words of a specified length 
	 * 	with characters from the specified alphabet
	 * @param length, alphabet
	 * @return
	 */
	public static Collection<String> getPossibleWords(int length, char [] alphabet){
		/* empty collection will be returned with erroneous input of length < 1 */
		Collection<String> words = new ArrayList<String>();
		if (length == 1){ /* base case */
			for (char c: alphabet){
				words.add(Character.toString(c));
			}
		}
		else if (length > 1){
			Collection<String> shorter = getPossibleWords(length - 1, alphabet);
			Iterator<String> iter = shorter.iterator();
			String current;
			while(iter.hasNext()){
				current = iter.next();
				for(char c: alphabet){
					words.add(current + c);
				}
			}
		}
		return words;
	}
	
	
	/**
	 * reverse - Returns the reverse of a given string
	 */
	public static String reverse(String in) {
		int length = in.length();
		String rev = "";
		for (int i = length - 1; i >= 0; i--) {
			rev += in.charAt(i);
		}
		return rev;
	}

	public static String reverseSentence(String sentence){
		String rev = "";
	    String [] words = sentence.split(" ");
	    System.out.println(Arrays.toString(words));
	    for (int i = words.length - 1; i > 0; i--){
	    	rev += words[i] + " ";
	    }
	    rev += words[0];
	    return rev;
	}
	/**
	 * maxPrefix - finds the longest common prefix of an array strings
	 */
	public static String maxPrefix(String[] words) {
		int numWords = words.length;
		if (numWords <= 0) {
			return "";
		}
		int index = 1;
		String prefix = words[0];
		String iter;
		while (index < numWords && prefix.length() > 0) {
			iter = words[index];
			String common = commonPrefix(words[index], prefix);
			if (common.length() < prefix.length()) {
				prefix = common;
			}
			index++;
		}
		return prefix;
	}

	/**
	 * commonPrefix - returns the common prefix (if any) between two strings
	 * used as a helper method to maxPrefix
	 */
	public static String commonPrefix(String a, String b) {
		int maxIndex = Math.min(a.length(), b.length());
		String prefix = "";
		int index = 0;
		Boolean matching; // whether or not the current letters are matching
		while (index < maxIndex) {
			matching = a.charAt(index) == b.charAt(index);
			if (matching) {
				prefix += a.charAt(index); // add the common character to the
											// prefix
			} else {
				return prefix;
			}
			index++;
		}
		return prefix; // should never be reached
	}

	/**
	 * isPalindrome - Returns whether or not a string passed is a palindrome
	 * ignores non-alphabetic characters in the comparison for a more
	 * comprehensive result
	 */
	public static boolean isPalindrome(String word) {
		int leftIndex = 0, rightIndex = word.length() - 1;
		while (rightIndex > leftIndex) {
			char leftChar = word.charAt(leftIndex), rightChar = word
					.charAt(rightIndex);
			while (!Character.isLetter(leftChar) && leftIndex < rightIndex) {
				leftChar = word.charAt(++leftIndex);
			}
			while (!Character.isLetter(rightChar) && rightIndex > leftIndex) {
				rightChar = word.charAt(--rightIndex);
			}
			if (Character.isLetter(rightChar) && Character.isLetter(leftChar)
					&& (rightChar != leftChar)) {
				return false;
			}
			leftIndex++;
			rightIndex--;
		}
		return true;
	}

	/**
	 * anaPal - Determines whether or not a string is an anagram of a palindrome
	 */
	public static boolean anaPal(String S) {
		String comp = S.toLowerCase(); // string used for comparison
		int[] frequencies = new int[26]; // frequency of each letter
		int oddCount = 0; // number of odd numbers encountered
		for (int i = 0; i < comp.length(); i++) {
			if (Character.isLetter(comp.charAt(i))) {
				int charIndex = (int) (comp.charAt(i) - 'a'); // a = 0, b = 1,
																// ... , z = 26
				int frequency = ++frequencies[charIndex]; // increment the
															// frequency of each
															// character we
															// encounter
				if (frequency % 2 == 1) {
					oddCount++; // increment oddCount when a frequency turns odd
				} else if (frequency > 0) {
					oddCount--; // decrement when encountering a frequency turns
								// even
				}
			}
		}
		/*
		 * palindromes have at most one character occuring an add number of
		 * times
		 */
		return (oddCount <= 1);
	}
}