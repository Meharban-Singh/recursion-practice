//abc => abc, acb, bac, bca, ...

import java.util.ArrayList;

public class Permutations {
	public static void main(String[] args) {
		permute("ABC");
	}
	
	public static void permute(String str) {
		ArrayList<Character> chars = new ArrayList<>();
		for(char c : str.toCharArray())
			chars.add(c);
		permuteHelper(chars, "");
	}
	
	public static void permuteHelper(ArrayList<Character> str, String chosen) {
		//base case
		if(str.isEmpty())
			System.out.println(chosen);
		else {
			for(int i = 0; i < str.size(); i++) {
				char rem = str.remove(i); //choose
				chosen += rem;

				permuteHelper(str, chosen); //explore
				
				str.add(0, rem);
				chosen = chosen.substring(0, chosen.length() - 1); //un-choose
			}
		}
	}
}
