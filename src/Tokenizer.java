import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Tokenizer {

	// this method converts input file into tokens, insert tokens to a
	// hashmap and perform query search
	public HashMap<String, Integer> tokenize(BufferedReader bufferedReader) {

		try {
			HashMap<String, Integer> map = new HashMap<String, Integer>();

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				StringTokenizer sTok = new StringTokenizer(line.toLowerCase(), " -.;\n\t/");
				while (sTok.hasMoreTokens()) {
					String key = sTok.nextToken();
					// put tokens to map
					if (map.containsKey(key)) {
						map.put(key, map.get(key) + 1);
					} else {
						map.put(key, 1);
					}
				}
			}
			bufferedReader.close();
			return map;
		} catch (IOException e) {
			return null;
		}
	}

}