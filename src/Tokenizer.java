
import java.util.HashMap;
import java.util.StringTokenizer;
import textprocessing.*;


public class Tokenizer {

	//this method converts input file into tokens, insert tokens to a 
	//hashmap and perform query search
	public void tokenize(In file, String query) {
		// TODO Auto-generated method stub

		try {
			HashMap<String, Integer> map = new HashMap<String, Integer>();

			String txt = file.readAll();
			StringTokenizer sTok = new StringTokenizer(txt, " -.;\n\t/");
			while (sTok.hasMoreTokens()) {
				String key = sTok.nextToken();
				
				//put tokens to map
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1);
				}
			}

			// System.out.println(map);
			TST<Integer> st = new TST<Integer>();
			int x = 0;
			for (String i : map.keySet()) {
				st.put(i, x);
				x++;

			}

			// count number of occurrences of query entered by user
			int count = map.get(query);
			System.out.println("Count : " + count);
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			System.out.println("String not found");

		}

	}

}
