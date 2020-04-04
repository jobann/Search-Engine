import java.io.File;
import java.util.*;

import textprocessing.In;

/**
 * 
 * @author Jobanpreet Singh 110024321
 *
 */
public class SpellCheck {

	// Spell check for single keyword
	public static Boolean checkSpelling(String keyword) {
		// Directory path to get words list
		String directoryPath = "WebPages/text";
		// Getting words
		String[] words = getWordsFromDirectory(directoryPath);
		Set<String> uniqueWords = new HashSet<String>(Arrays.asList(words));
		int calculatedDist;
		int storedDistance = 0;
		boolean flag = false;
		ArrayList<String> arr = new ArrayList<String>();
		// Checking if the word exits or how many changes it took to transform to
		// suggestion word
		for (String keys : uniqueWords) {
			calculatedDist = EditDistance.minDist(keys, keyword);
			if (calculatedDist == 0) {
				flag = true;
				break;
			} else if (storedDistance == 0) {
				storedDistance = calculatedDist;
				arr.add(keys);
			} else if (calculatedDist < storedDistance) {
				storedDistance = calculatedDist;
				arr.clear();
				arr.add(keys.toLowerCase());

			} else if (calculatedDist == storedDistance) {
				if (!arr.contains(keys.toLowerCase())) {
					arr.add(keys.toLowerCase());
				}
			}
		}
		// If the input keyword does not match
		if (!flag) {
			System.out.print("\n*********  Sorry! Can't find the searched string '" + keyword
					+ "'. Displaying the possible words from the text  *********");
			String possibleWords = "";
			// Getting suggestion words
			if (arr.size() > 0) {
				for (int i = 0; i < arr.size(); i++) {
					if (possibleWords.length() > 0) {
						possibleWords += ",";
					}
					possibleWords += arr.get(i);
					if (i == 4) {
						break;
					}
				}
			}

			System.err.println("\nDid you mean" + " '" + possibleWords + "'?\n");
			return false;
		} else {
			return true;
		}
	}

	// Getting Words from directory
	static String[] getWordsFromDirectory(String directoryPath) {
		String[] files = getFilesFromDirectory(directoryPath);
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (String file : files) {
			String[] wordsPerFile = getWordsFromFile(directoryPath + "/" + file);
			for (String word : wordsPerFile) {
				if (map.containsKey(word)) {
					Integer value = map.get(word);
					map.put(word, value + 1);
				} else {
					map.put(word, 1);
				}
			}
		}

		Set<String> myKeys = map.keySet();
		String[] wordsInDirectory = myKeys.toArray(new String[myKeys.size()]);

		return wordsInDirectory;
	}

	// Getting words from directory
	static String[] getFilesFromDirectory(String directoryPath) {
		File fileObj = new File(directoryPath);
		String[] files = fileObj.list();
		return files;
	}

	// Getting words from file
	static String[] getWordsFromFile(String filePath) {
		In inputFile = new In(filePath);
		String[] words = cleanWords(inputFile.readAllStrings());
		return words;
	}

	// Cleaning words by removing special characters
	static String[] cleanWords(String[] words) {
		List<String> ls = new ArrayList<String>();
		for (String word : words) {

			String newWord = word;
			if (newWord.length() > 0) {
				char ch = newWord.charAt(newWord.length() - 1);
				while (newWord.length() > 0 && !Character.isLetterOrDigit(ch)) {
					newWord = newWord.substring(0, newWord.length() - 1);
					if (newWord.length() > 0) {
						ch = newWord.charAt(newWord.length() - 1);
					}
				}

				if (newWord.length() > 0) {
					ch = newWord.charAt(0);
				}
				while (newWord.length() > 0 && !Character.isLetterOrDigit(ch)) {
					newWord = newWord.substring(1);
					if (newWord.length() > 0) {
						ch = newWord.charAt(0);
					}
				}
			}
			if (newWord.length() > 0) {
				ls.add(newWord);
			}
		}
		String[] cleanWords = new String[ls.size()];
		int index = 0;
		for (String word : ls) {
			cleanWords[index++] = word.toLowerCase();
		}
		return cleanWords;
	}
}
