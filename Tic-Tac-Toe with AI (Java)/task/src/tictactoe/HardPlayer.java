package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HardPlayer extends AIPlayer{
    public HardPlayer(Scanner scanner){
        super(scanner);        
    }
    
    @Override
    public void move(char xOrO){
        //        System.out.println("Method: hardMakingMove");
        if (xOrO == 'X' && Game.firstPlayer == UserParameters.HARD
                || xOrO == 'O' && Game.secondPlayer == UserParameters.HARD) {
            System.out.println("Making move level \"hard\"");
        }

        //max depth = 9, 9 cells
        int[] result = minimax(9, xOrO, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int[] newMove = new int[]{result[1], result[2]};
        Utility.upgradeTheField(newMove, xOrO);
    }

    private int[] minimax(int depth, char xOrO, int alpha, int beta) {
        List<int[]> emptyCells = generateMoves();

        int score;
        int bestRow = -1;
        int bestCol = -1;

        if (emptyCells.isEmpty() || depth == 0) {

            // Gameover or depth reached, evaluate score
            score = evaluate();
            return new int[]{score, bestRow, bestCol};
        } else {
            for (int[] move : emptyCells) {
                Game.field[move[0]][move[1]] = xOrO;
                if (xOrO == 'X') {  // computer is maximizing player
                    score = minimax(depth - 1, 'O', alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else {  // oppSeed is minimizing player
                    score = minimax(depth - 1, 'X', alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                // Undo move
                Game.field[move[0]][move[1]] = '_';
                if (alpha >= beta) {
                    break;
                }
            }
            return new int[]{(xOrO == 'X') ? alpha : beta, bestRow, bestCol};
        }
    }

    /**
     * The heuristic evaluation function for the current board
     *
     * @Return +100, +10, +1 for EACH 3-, 2-, 1-in-a-line for computer.
     * -100, -10, -1 for EACH 3-, 2-, 1-in-a-line for opponent.
     * 0 otherwise
     */
    private int evaluate() {
        int score = 0;
        // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
        score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
        score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
        score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
        score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
        score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
        score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
        score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
        score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate
        return score;
    }

    /**
     * The heuristic evaluation function for the given line of 3 cells
     *
     * @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
     * -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
     * 0 otherwise
     */
    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;

        // First cell
        if (Game.field[row1][col1] == 'X') {
            score = 1;
        } else if (Game.field[row1][col1] == 'O') {
            score = -1;
        }

        // Second cell
        if (Game.field[row2][col2] == 'X') {
            if (score == 1) {   // cell1 is mySeed
                score = 10;
            } else if (score == -1) {  // cell1 is oppSeed
                return 0;
            } else {  // cell1 is empty
                score = 1;
            }
        } else if (Game.field[row2][col2] == 'O') {
            if (score == -1) { // cell1 is oppSeed
                score = -10;
            } else if (score == 1) { // cell1 is mySeed
                return 0;
            } else {  // cell1 is empty
                score = -1;
            }
        }

        // Third cell
        if (Game.field[row3][col3] == 'X') {
            if (score > 0) {  // cell1 and/or cell2 is mySeed
                score *= 10;
            } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = 1;
            }
        } else if (Game.field[row3][col3] == 'O') {
            if (score < 0) {  // cell1 and/or cell2 is oppSeed
                score *= 10;
            } else if (score > 1) {  // cell1 and/or cell2 is mySeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = -1;
            }
        }
        return score;
    }

    private List<int[]> generateMoves() {
//        System.out.println("EmptyIndexes is working...");
        List<int[]> emptyCells = new ArrayList<int[]>();

        // If gameover, i.e., no next move
        if (!Utility.isGameContinuing()) {
            return emptyCells;
        }

        for (int i = 0; i < Game.field.length; i++) {
            for (int j = 0; j < Game.field[i].length; j++) {
                if (Game.field[i][j] == '_') {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells;
    }
}
