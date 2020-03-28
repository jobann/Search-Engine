import java.util.ArrayList;
import java.util.List;

import textprocessing.BoyerMoore;
import textprocessing.In;

public class BoyerMooreSearch {
	public static void main(String[] args) {
		String pat = "java";
		
		In in = new In("WebPages/text/JavaTPoint1.txt");
		String txt = in.readAll();
		
		List<Integer> bmPositions = BoyerMooreAll(pat.toLowerCase(), txt.toLowerCase());
		
		System.out.println(bmPositions.size());
		
	}

	// Get Boyer Moore Matches
	public static List<Integer> BoyerMooreAll(String pattern, String text) {
		List<Integer> patt_pos = new ArrayList<Integer>();
		int offset_Pos = 0, search_Pos = 0;
		int patt_Length = pattern.length();
		String sub_String;
		BoyerMoore bm = new BoyerMoore(pattern);
		while ((offset_Pos <= (text.length() + patt_Length - 1))) {
			sub_String = text.substring(offset_Pos);
			search_Pos = bm.search(sub_String);
			if (search_Pos == sub_String.length())
				break;
			patt_pos.add(offset_Pos + search_Pos);
			offset_Pos = offset_Pos + search_Pos + patt_Length;
		}
		if (patt_pos.size() != 0)
			return patt_pos;
		else
			patt_pos.add(search_Pos);
		return patt_pos;
	}
}
