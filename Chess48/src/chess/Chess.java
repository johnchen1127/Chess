package chess;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Shah on 3/9/2017.
 */
public class Chess {

	public static void main(String[] args) throws IOException, NullPointerException {

		Board br = new Board();
		Game game = new Game();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input = "";
		boolean draw = false;
		boolean whiteTurn = true;
		br.toString();
		String endGame = "";
		String friendly = "";
		while (true) {
			if (whiteTurn == true) {
				System.out.printf("White's move: ");
			} 
			else {
				System.out.printf("Black's move: ");
			}
			input = sc.nextLine();
			if (input.toLowerCase().equals("resign")) {
				endGame = "Resign";
				break;
			} 
			else if (input.length() == 11) {
				if (input.substring(6, 11).equals("draw?")) {
					draw = true;
				}
				whiteTurn = changeTurn(whiteTurn);
			} 
			else if (input.length() > 5|| (input.length()<5&& !input.toLowerCase().equals("draw"))) 
			{
				System.out.println("Invalid move try again");
				continue;
			}
			else if(input.equals("draw")&& draw ==true){
				endGame = "Draw";
				break;
			}
			else if (input.charAt(2) != ' '){
				System.out.println("Invalid move try again");
				continue;
			}
			else {
				if(friendly.equals("invalid")||friendly.equals("inFriendlyCheck")){
					System.out.println("Illegal move, try again");
					continue;
				}
				else {
						if(game.move(br, input, whiteTurn)!=true){
							System.out.println("Illegal move, try again");
							continue;
						}
						else if(game.checkMate(br, input, whiteTurn)==true){
							endGame = "CheckMate";
							break;
						}
						else if(game.enemyCheck(br, input, whiteTurn).equals("enemyCheck")){
							System.out.println("Check");
						}
						resetEpos(br,whiteTurn);
					}
					whiteTurn = changeTurn(whiteTurn);
					System.out.println();
					br.toString();
				}
			}
			if(endGame.equals("CheckMate")){
				br.toString();
				System.out.println("CheckMate! " + turnColor(whiteTurn) + " Wins!");
			}
			else if(endGame.equals("Draw")){
				System.out.println("Game Ended---> Draw");
			}
			else if(endGame.equals("Resign")){
				System.out.println("Game Ended---> " + turnColor(whiteTurn) + " resigned!");
			}
		}
	


	public static boolean changeTurn(boolean whiteTurn) {
		if (whiteTurn == true) {
			return false;
		}
		return true;
	}
	public static String turnColor(boolean whiteTurn){
		if (whiteTurn == true) {
			return "White";
		}
		return "Black";
	}
	public static void resetEpos(Board br, boolean whiteTurn){
		if (whiteTurn == true){
			for (int i = 0; i < 8; i++) {
				if (br.board[3][i].getClass().isInstance(new Pawn())) {
					br.board[3][i].ePos = false;
				}
			}
		}
		else{
			for (int i = 0; i < 8; i++) {
				if (br.board[4][i].getClass().isInstance(new Pawn())) {
					br.board[4][i].ePos = false;
				}
			}
		}
	}
}