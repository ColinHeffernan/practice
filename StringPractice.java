import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * StringPractice - A class to practice manipulating strings
 * 
 * @author Colin Heffernan
 */
public class StringPractice {

	/**
	 * generateWords - returns an array with all possible strings containing A-Z
	 * characters up to a specified length
	 */
	public static String[] generateWords(int maxLength) {
		ArrayDeque<String> wordsToAppend = new ArrayDeque<String>();
		int arraySize = 0;
		for(int i = 0; i <= maxLength; i++){
			arraySize += (int) Math.pow(26, i); // need to hold words of length up to maxLength
		}
		String[] words = new String[arraySize - 1];
		int index = 0;
		int numA = (int) ('A');
		String wordToAdd;
		/* Initialize the stack with all one-letter words */
		for (int i = 0; i < 26; i++) {
			wordToAdd = Character.toString((char) (numA + i));
			wordsToAppend.add(wordToAdd);
			words[index++] = wordToAdd;
			wordToAdd = "";
		}
		if (maxLength == 1) {
			return words;
		}
		String currentWord;
		while (!wordsToAppend.isEmpty()) { // until we have done every word of
											// the specified length
			currentWord = wordsToAppend.remove(); // remove word from the end of
													// the queue to append
													// letters to it
			for (int i = 0; i < 26; i++) {
				wordToAdd = currentWord + (char) (numA + i);
				if (wordToAdd.length() < maxLength) { // only add word if it is
														// 1 char short of max
					wordsToAppend.add(wordToAdd); // add word to the end of the
													// queue
				}
				words[index++] = wordToAdd;
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

	/**
	 * main - test the above methods and print results
	 */
	public static void main(String[] args) {
		String[] prefixWords = { "Charlie", "Chart", "Challenge", "Chat" };
		System.out.println(maxPrefix(prefixWords)); // should print "Cha"
		String palindrome = "a man a plan a canal panama"; // should print true
		System.out.println(isPalindrome(palindrome));
		String backwards = "!sdrawkcab saw gnirts sihT";
		System.out.println(reverse(backwards));
		String [] words = generateWords(3);
		for (String word: words){
			System.out.println(word);
		}
		System.out.println(anaPal("a** l323  amd  dl"));
	}

}