package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class EasyPlayer extends AIPlayer {

    public EasyPlayer(Scanner scanner){
        super(scanner);
    }

    @Override
    public void move(char xOrO) {
//        System.out.println("Method: computer easyMakingMove");

        if (xOrO == 'X' && Game.firstPlayer == UserParameters.EASY
                || xOrO == 'O' && Game.secondPlayer == UserParameters.EASY) {
            System.out.println("Making move level \"easy\"");
        }

        int[] coordinates = {0, 0};

        boolean cellIsOccupied = false;

        Random rand = new Random();

        do {
            coordinates[0] = rand.nextInt(3);
            coordinates[1] = rand.nextInt(3);

            cellIsOccupied = Game.field[coordinates[0]][coordinates[1]] != '_';
        } while (cellIsOccupied);

        Utility.upgradeTheField(coordinates, xOrO);
    }
}
