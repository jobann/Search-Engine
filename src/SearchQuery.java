import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

/**
 * 
 * @author Mehak Taluja
 *
 */

public class SearchQuery {

	/**
	 * 
	 * @param query to search
	 * @return list of urls with associated average frequency
	 */
	public List<Entry<String, Float>> searchQuery(String query) {

		List<String> queries = new ArrayList<String>();
		Tokenizer tokenizer = new Tokenizer();
		HashMap<String, Float> resultMap = new HashMap<String, Float>();

		// Tokenize the inputed string
		StringTokenizer stringTokenizer = new StringTokenizer(query);
		while (stringTokenizer.hasMoreTokens()) {
			String tmpQuery = stringTokenizer.nextToken();
			// If the spelling is incorrect skip the loop
			if (!SpellCheck.checkSpelling(tmpQuery)) {
				continue;
			}
			queries.add(tmpQuery);
		}

		for (int k = 0; k < Spider.urlNames.length; k++) {
			for (int i = 1; i <= getFilesCount(Spider.urlNames[k]); i++) {
				try {
					File file = new File("WebPages/" + "text/" + Spider.urlNames[0] + "" + i + ".txt");
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					String url = bufferedReader.readLine();

					float average = 0;
					for (int j = 0; j < queries.size(); j++) {

						bufferedReader = new BufferedReader(new FileReader(file));
						String tempQuery = queries.get(j);
						Integer count = tokenizer.tokenize(bufferedReader).get(tempQuery);
						if (count != null) {
							average += count;
						}
					}
					average = average / queries.size();

					if (average != 0) {
						resultMap.put(url, average);
					}

					// bufferedReader.close();
				} catch (IOException e) {
					//System.err.println("Message: " + e.getMessage());
				}
			}
		}

		// Sorting result HashMap
		Comparator<Entry<String, Float>> valueComparator = new Comparator<Entry<String, Float>>() {
			@Override
			public int compare(Entry<String, Float> e1, Entry<String, Float> e2) {
				Float v1 = e1.getValue();
				Float v2 = e2.getValue();
				return v2.compareTo(v1);
			}
		};

		Set<Entry<String, Float>> entries = resultMap.entrySet();
		List<Entry<String, Float>> listOfEntries = new ArrayList<Entry<String, Float>>(entries);
		Collections.sort(listOfEntries, valueComparator);

		return listOfEntries;
	}

	// get number of files in directory
	static int getFilesCount(String fileName) {
		int count = 0;
		String[] files = new File("WebPages/text/").list();
		for (String file : files) {
			if (file.toLowerCase().contains(fileName.toLowerCase())) {
				count++;
			}
		}
		return count;
	}
}
