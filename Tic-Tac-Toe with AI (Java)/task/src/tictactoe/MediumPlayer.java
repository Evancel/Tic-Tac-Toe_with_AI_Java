package tictactoe;

import java.util.Scanner;

public class MediumPlayer extends EasyPlayer {
    public MediumPlayer(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void move(char xOrO) {
//        System.out.println("Method: mediumMakingMove");
        if (xOrO == 'X' && Game.firstPlayer == UserParameters.MEDIUM
                || xOrO == 'O' && Game.secondPlayer == UserParameters.MEDIUM) {
            System.out.println("Making move level \"medium\"");
        }

        //WIN_STRATEGY
        int[] nextMove = {-1, -1};

        nextMove = twoCharactersAreTheSameInARow(xOrO);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        nextMove = twoCharactersAreTheSameInAColumn(xOrO);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        nextMove = twoCharactersAreTheSameInTheDirectDiagonal(xOrO);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        nextMove = twoCharactersAreTheSameInTheReverseDiagonal(xOrO);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        //BLOCK_OPPONENT_STRATEGY
        char opponentChar = xOrO == 'X' ? 'O' : 'X';
        nextMove = twoCharactersAreTheSameInARow(opponentChar);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        nextMove = twoCharactersAreTheSameInAColumn(opponentChar);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        nextMove = twoCharactersAreTheSameInTheDirectDiagonal(opponentChar);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        nextMove = twoCharactersAreTheSameInTheReverseDiagonal(opponentChar);
        if (nextMove[0] != -1) {
            Utility.upgradeTheField(nextMove, xOrO);
            return;
        }

        super.move(xOrO);
    }

    private int[] twoCharactersAreTheSameInARow(char xOrO) {
//        System.out.println("Method: twoCharactersAreTheSameInARow");
        int[] nextMoveCoordinates = {-1, -1};
        int expSum = xOrO * 2 + '_';

        for (int i = 0; i < Game.field.length; i++) {
            int sumOfChars = 0;
            for (int j = 0; j < Game.field[i].length; j++) {
                sumOfChars += Game.field[i][j];
            }

            if (sumOfChars == expSum) {
                nextMoveCoordinates[0] = i;

                for (int j = 0; j < Game.field[i].length; j++) {
                    if (Game.field[i][j] == '_') {
                        nextMoveCoordinates[1] = j;
                    }
                }
            }
        }
        return nextMoveCoordinates;
    }

    private int[] twoCharactersAreTheSameInAColumn(char c) {
//        System.out.println("Method: twoCharactersAreTheSameInAColumn");
        int[] nextMoveCoordinates = {-1, -1};
        int expSum = c * 2 + '_';

        for (int j = 0; j < Game.field.length; j++) {
            int sumOfChars = 0;
            for (int i = 0; i < Game.field.length; i++) {
                sumOfChars += Game.field[i][j];
            }

            if ((double) sumOfChars == (double) expSum) {
                //значение столбца
                nextMoveCoordinates[1] = j;

                for (int i = 0; i < Game.field.length; i++) {
                    if (Game.field[i][j] == '_') {
                        nextMoveCoordinates[0] = i;
                    }
                }
            }
        }
        return nextMoveCoordinates;
    }

    private int[] twoCharactersAreTheSameInTheDirectDiagonal(char c) {
//        System.out.println("Method: twoCharactersAreTheSameInTheDirectDiagonal");
        int[] nextMoveCoordinates = {-1, -1};
        int expSum = c * 2 + '_';
        int sumOfChars = 0;

        for (int i = 0; i < Game.field.length; i++) {
            sumOfChars += Game.field[i][i];
        }
        if ((double) sumOfChars == (double) expSum) {
            for (int i = 0; i < Game.field.length; i++) {
                if (Game.field[i][i] == '_') {
                    nextMoveCoordinates[0] = i;
                    nextMoveCoordinates[1] = i;
                }
            }
        }
        return nextMoveCoordinates;
    }

    private int[] twoCharactersAreTheSameInTheReverseDiagonal(char c) {
//        System.out.println("Method: twoCharactersAreTheSameInTheReverseDiagonal");
        int[] nextMoveCoordinates = {-1, -1};
        int expSum = c * 2 + '_';
        int sumOfChars = 0;

        for (int i = 0, j = Game.field[i].length - 1; i < Game.field.length && j >= 0; i++, j--) {
            sumOfChars += Game.field[i][j];
        }
        if ((double) sumOfChars == (double) expSum) {
            for (int i = 0, j = Game.field[i].length - 1; i < Game.field.length && j >= 0; i++, j--) {
                if (Game.field[i][j] == '_') {
                    nextMoveCoordinates[0] = i;
                    nextMoveCoordinates[1] = j;
                }
            }
        }
        return nextMoveCoordinates;
    }


}
