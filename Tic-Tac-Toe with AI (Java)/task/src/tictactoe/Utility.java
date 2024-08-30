package tictactoe;

public class Utility {

    protected static void createEmptyField(int length) {
//        System.out.println("Method: createEmptyGrid");
        Game.field = new char[length][length];

        for (int i = 0, k = 0; i < Game.field.length; i++) {
            for (int j = 0; j < Game.field[i].length; j++) {
                Game.field[i][j] = '_';
            }
        }
    }

    protected static void printField() {
//        System.out.println("Method: printGrid");
        System.out.println("---------");

        for (int i = 0; i < Game.field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < Game.field[i].length; j++) {
                System.out.print(Game.field[i][j]);
                if (j != Game.field[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    protected static void printTheResult() {
//        System.out.println("Method: printTheResult");
        char result = analyzeTheCurrentState();

        switch (result) {
            case 'C':
                System.out.println("Game is not finished");
                break;
            case 'D':
                System.out.println("Draw");
                break;
            case 'X':
                System.out.println("X wins");
                break;
            case 'O':
                System.out.println("O wins");
                break;
            default:
                System.out.println("Unexpected result");
                break;
        }
    }

    protected static boolean isGameContinuing() {
//        System.out.println("Method: isGameContinuing");
        return analyzeTheCurrentState() == 'C';
    }

    protected static void upgradeTheField(int[] coordinates, char xOrO) {
//        System.out.println("Method: upgradeTheGrid");
        Game.field[coordinates[0]][coordinates[1]] = xOrO;
        printField();
    }

    private static char analyzeTheCurrentState() {
//        System.out.println("Method: analyzeTheCurrentState");
        boolean isXWin = false;
        boolean isOWin = false;

        char resultInARow = allCharactersAreTheSameInARow();
        char resultInTheDirectDiagonal = allCharactersAreTheSameInTheDirectDiagonal();
        char resultInTheReverseDiagonal = allCharactersAreTheSameInTheReverseDiagonal();
        char resultInAColumn = allCharactersAreTheSameInAColumn();

        if (resultInARow == 'X'
                || resultInTheDirectDiagonal == 'X'
                || resultInTheReverseDiagonal == 'X'
                || resultInAColumn == 'X'
        ) {
            return 'X';
        }

        if (resultInARow == 'O'
                || resultInTheDirectDiagonal == 'O'
                || resultInTheReverseDiagonal == 'O'
                || resultInAColumn == 'O'
        ) {
            return 'O';
        }

        if (isEmptyCells()) {
            //the game is continuing
            return 'C';
        }

        if (!isXWin && !isOWin && !isEmptyCells()) {
            //the game is finished, noone wins
            return 'D';
        }

        return 'U';
    }

    private static boolean isEmptyCells() {
//        System.out.println("Method: isEmptyCells");
        int countEmptyCells = 0;

        for (int i = 0; i < Game.field.length && countEmptyCells < 1; i++) {
            for (int j = 0; j < Game.field[i].length; j++) {
                if (Game.field[i][j] == '_') {
                    ++countEmptyCells;
                    break;
                }
            }
        }

        return countEmptyCells > 0;
    }

    private static char allCharactersAreTheSameInARow() {
//        System.out.println("Method: allCharactersAreTheSameInARow");
        //W - noone wins; X - X wins; O - O wins;
        char winner = 'W';

        for (int i = 0; i < Game.field.length; i++) {
            //char X = 88; char O = 79;
            int sumOfChars = 0;
            for (int j = 0; j < Game.field[i].length; j++) {
                sumOfChars += Game.field[i][j];
            }

            if ((double) sumOfChars == (double) Game.field[i][0] * Game.field.length && Game.field[i][0] != '_') {
                winner = Game.field[i][0];
                break;
            }
        }

        return winner;
    }

    private static char allCharactersAreTheSameInAColumn() {
//        System.out.println("Method: allCharactersAreTheSameInAColumn");
        //W - noone wins; I - there are X-win and O-win; X - X wins; O - O wins;
        char winner = 'W';

        //j - порядковый номер в строке
        for (int j = 0; j < Game.field[0].length; j++) {
            int sumOfChars = 0;
            //i - порядковый номер в стобце
            for (int i = 0; i < Game.field.length; i++) {
                sumOfChars += Game.field[i][j];
            }

            if ((double) sumOfChars == (double) Game.field[0][j] * Game.field.length && Game.field[0][j] != '_') {
                winner = Game.field[0][j];
                break;
            }
        }

        return winner;
    }

    private static char allCharactersAreTheSameInTheDirectDiagonal() {
//        System.out.println("Method: allCharactersAreTheSameInTheDirectDiagonal");
        //W - noone wins; X - X wins; O - O wins;
        char winner = 'W';

        int sumOfChars = 0;
        for (int i = 0; i < Game.field.length; i++) {
            sumOfChars += Game.field[i][i];
        }

        if ((double) sumOfChars == (double) Game.field[0][0] * Game.field.length && Game.field[0][0] != '_') {
            winner = Game.field[0][0];
        }

        return winner;
    }

    private static char allCharactersAreTheSameInTheReverseDiagonal() {
//        System.out.println("Method: allCharactersAreTheSameInTheReverseDiagonal");
        //W - noone wins; X - X wins; O - O wins;
        char winner = 'W';
        int sumOfChars = 0;
        for (int i = 0, j = Game.field[i].length - 1; i < Game.field.length && j >= 0; i++, j--) {
            sumOfChars += Game.field[i][j];
        }

        if ((double) sumOfChars == (double) Game.field[0][Game.field.length - 1] * Game.field.length
                && Game.field[0][Game.field.length - 1] != '_') {
            winner = Game.field[0][Game.field.length - 1];
        }

        return winner;
    }
}
