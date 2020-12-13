//2=> 12, 13, 14, 15, 16, 21, 22....66

public class DiceRoll {
	public static void main(String[] args) {
		diceRoll(3);
	}
	
	private static void diceRoll(int numberOfDice) {
		diceRollHelper(numberOfDice, "");
	}
	
	private static void diceRollHelper(int n, String pre) {
		if(n == 0) {
			System.out.println(pre);
		}
		else {
			for(int i = 1; i <= 6; i++) {
				pre += i; //choose
				diceRollHelper(n - 1, pre); //explore
				pre = pre.substring(0, pre.length() - 1);//un-choose
			}
		}
	}
}
