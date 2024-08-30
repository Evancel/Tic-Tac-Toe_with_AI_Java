package tictactoe;

import java.util.*;
public class Game {


    private static final Scanner scanner = new Scanner(System.in);

    protected int length;
    protected static char[][] field;
    protected static UserParameters menuChoice;
    protected static UserParameters firstPlayer;
    protected static UserParameters secondPlayer;

    Display display = new Display(scanner);

    public Game(int length){
        this.length = length;
        start();
    }

    private void start() {
//        System.out.println("Method: start");

        do {
            display.inputMenuChoice();
            game();
            Utility.printTheResult();
        }while(menuChoice != UserParameters.EXIT);
    }

    private void game() {
//        System.out.println("Method: game");
        if (Game.menuChoice.equals(UserParameters.START)) {
            Utility.createEmptyField(length);
            Utility.printField();
        }

        do {
            nextMove(firstPlayer, 'X');
            nextMove(secondPlayer, 'O');
        } while (Utility.isGameContinuing());
    }

    private static void nextMove(UserParameters player, char xOrO) {
//        System.out.println("Method: nextMove");
        if (Utility.isGameContinuing()) {
            switch (player) {
                case USER:
                    UserPlayer userPlayer = new UserPlayer(scanner);
                    userPlayer.move(xOrO);
                    break;
                case EASY:
                    EasyPlayer easy = new EasyPlayer(scanner);
                    easy.move(xOrO);
                    break;
                case MEDIUM:
                    MediumPlayer medium = new MediumPlayer(scanner);
                    medium.move(xOrO);
                    break;
                case HARD:
                    HardPlayer hard = new HardPlayer(scanner);
                    hard.move(xOrO);
                    break;
                default:
                    System.out.println("Nondefined behavior. Exit...");
                    System.exit(1);
                    break;
            }
        }
    }
}
