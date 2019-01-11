package assignment_1;
import java.util.Random;
import java.util.Scanner;

public class pig_v2 {
	
	public static void main(String[] args) {
		boolean winner = run_game();
		if (winner) System.out.println("You win!"); else System.out.println("Computer won...");
	}
	
	static Random generator = new Random(System.currentTimeMillis());
	static Scanner scan = new Scanner(System.in);
	
	public static int rand_num() {
		return generator.nextInt(6) + 1; 
	}
	
	public static boolean rand_choice() {
		return generator.nextInt(2) == 1 ? true : false;
	}
	
	public static boolean run_game() {
		int user_score = 0;
		int cmp_score = 0;
		boolean turn = true;
		int round = 1;
		do {		
			int turn_score = start_round(round, user_score, cmp_score, turn);
			if (turn) user_score = turn_score + user_score; else cmp_score = cmp_score + turn_score;
			round = round+1;
			turn = !turn;
		} while (user_score < 100 & cmp_score < 100);
		boolean winner = user_score >= 100 ? true : false; 
		return winner;
	}
	
	public static int start_round(int round, int user_score, int cmp_score, boolean type) {
		System.out.println("\n-- Round " + round + " --");
		System.out.println("User: " + user_score + " - " + "Computer: " + cmp_score );
		if (type) System.out.println("\nYour turn:\n"); else System.out.println("\nComputer turn: \n");

		int turn_result = type ? turn(0, user_score, type) : turn(0, cmp_score, type);
		return turn_result;
	}
	
	public static int turn(int turn_score, int game_score, boolean type) {
		int roll_1 = rand_num();
		int roll_2 = rand_num();
		int sum = roll_1 + roll_2;
		System.out.println("Roll: " + roll_1 + " and " + roll_2);
		
		if (turn_score >= 100) {
			return turn_score;
		} else if (roll_1 == 1 & roll_2 == 1) {
			turn_score = turn_score + 25;
			return turn(turn_score, game_score, type);
		} else if (roll_1 == 1 ^ roll_2 == 1) {
			return 0;
		} else if (roll_1 == roll_2) {
			turn_score = turn_score + 2*(sum);
			return turn(turn_score, game_score, type);
		} else {
			turn_score = turn_score + sum;
			boolean choice = type ? choice(turn_score, game_score) : rand_choice();
			return choice ? turn_score : turn(turn_score, game_score, type);
		}
	}
	
	public static boolean choice(int turn_score, int game_score) {
		System.out.println("Game: " + game_score + " - Turn: " + turn_score + ". roll again? type y (yes) or other (no).");
		String choice = scan.nextLine();
		return !choice.equals("y");
	}
	
}
