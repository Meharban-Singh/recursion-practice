//abc=> a, b, c, ab, ac, bc, abc

import java.util.ArrayList;

public class Combinations {
	public static void main(String[] args) {
		combinations("ABC");
	}
	
	private static void combinations(String x) {
		ArrayList<Character> chars = new ArrayList<>();
		
		for(char c : x.toCharArray())
			chars.add(c);
		combinationsHelper(chars, "");
	}
	
	private static void combinationsHelper(ArrayList<Character> str, String chosen) {
		//Understanding...
		
		//base case - nothing in str, print all selected
		if(str.isEmpty())
			System.out.println(chosen);
		//Recursive case
		else {
			char first = str.remove(0); //choose one value
			
			//explore
			chosen += first; //include that value 
			combinationsHelper(str, chosen);
			
			chosen = chosen.substring(0, chosen.length() - 1); //exclude that value
			combinationsHelper(str, chosen);
			
			str.add(0, first); //un-choose value
		}
	}
	
	private static String spaces(int num) {
		String spaces = "";
		for(int i = 0; i < num; i++) {
			spaces += "--";
		}
		
		return spaces;
	}
}
