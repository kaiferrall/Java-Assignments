package assignment_1;
import java.util.Random;
import java.util.Scanner;

public class game_of_pig {
	
	static Random generator = new Random(System.currentTimeMillis());
	static Scanner scan = new Scanner(System.in);
	
	static int user_score = 0;
	static int cmp_score = 0;
	
	public static void main(String[] args) {
			String turn = "user";
			do {		
				if (turn.equals("user")) {
					System.out.println("You:");
					user_score = user_score + turn(0, user_score);
					System.out.println("Score: " + user_score + "\n");
					turn = "cmp";
				} else {
					System.out.println("Computer:");
					cmp_score = cmp_score + turn(0, cmp_score);
					System.out.println("Score: " + cmp_score + "\n");
					turn = "user";
				}
				
			} while (user_score < 100 & cmp_score < 100);
			
			if (user_score >= 100) {
				System.out.println("You won.");
			} else {
				System.out.println("The computer won.");
			}
	    } 
	
	public static int rand_num() {
		return generator.nextInt(5) + 1; 
	}
	
	public static int turn(int turn_score, int current_score) {
		int roll_1 = rand_num();
		int roll_2 = rand_num();
		int sum = roll_1 + roll_2;
		
		System.out.println("Roll: " + roll_1 + " and " + roll_2);
		
		if (roll_1 == 1 & roll_2 == 1) {
			turn_score = turn_score + 25;
			current_score = current_score + turn_score;
			if (current_score >= 100) return current_score;
			return turn(turn_score, current_score);
		} else if (roll_1 == 1 ^ roll_2 == 1) {
			return 0;
		} else if (roll_1 == roll_2){
			turn_score = 2*(sum);
			current_score = current_score + turn_score;
			if (current_score >= 100) return current_score;
			return turn(turn_score, current_score);
		} else {
			turn_score = turn_score + sum;
			current_score = current_score + turn_score;
			if (current_score >= 100) return current_score;
			System.out.println("Your at: " + current_score + ". Type h to roll again, type p to end your turn.");
			String choice = scan.nextLine();
			if (choice.equals("h")) {
				return turn(turn_score, current_score);
			} else {
				return turn_score;
			}
		}
		
	}
	
}
