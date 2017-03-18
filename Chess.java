package samp;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Shah on 3/9/2017.
 */
public class Chess {

	public static void main(String[] args) throws IOException, NullPointerException {

		Board br = new Board();
		Game game = new Game();
		Scanner sc = new Scanner(System.in);
		String input = "";
		boolean draw = false;
		boolean whiteTurn = true;
		boolean moveValue;
		br.toString();
		String endGame = "";
		while (true) {
			if (whiteTurn == true) {
				// System.out.print("White's move: ");
				System.out.printf("White's move:");
			} else {
				// System.out.print("Blacks's move: ");
				System.out.printf("Black's move:");
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
			} 
			else if(input.equals("draw")&& draw ==true){
				endGame = "Draw";
				break;
			}
			else {
				if(game.friendlyCheck(br, input, whiteTurn).equals("invalid")){
					System.out.println("Invalid move try again");
					continue;
				}
				else if(game.friendlyCheck(br, input, whiteTurn).equals("inFriendlyCheck")){
					System.out.println("Invalid move try again");
					continue;
				}
				else {
						moveValue = game.move(br, input, whiteTurn);
						if(game.checkMate(br, input, whiteTurn)==true){
							endGame = "CheckMate";
							break;
						}
						else if(game.enemyCheck(br, input, whiteTurn).equals("enemyCheck")){
							System.out.println("Check");
						}
						resetEpos(br,whiteTurn);
					}
					whiteTurn = changeTurn(whiteTurn);
					br.toString();
				}
			}
			if(endGame.equals("CheckMate")){
				System.out.println("CheckMate! " + whiteTurn + " Wins!");
			}
			else if(endGame.equals("Draw")){
				System.out.println("Game Ended---> Draw");
			}
			else if(endGame.equals("Resign")){
				System.out.println("Game Ended---> " + whiteTurn + " resigned!");
			}
		}
	


	public static boolean changeTurn(boolean whiteTurn) {
		if (whiteTurn == true) {
			return false;
		}
		return true;
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
