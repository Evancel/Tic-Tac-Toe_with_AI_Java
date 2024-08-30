package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class UserPlayer {
    private final Scanner scanner;
    private int[] coordinates;

    public UserPlayer(Scanner scanner){
        this.scanner = scanner;
    }

    public void move(char xOrO) {
        boolean moveMadeSuccessfully = false;
        int x = 0;
        int y = 0;

        do {
            System.out.println("Enter the coordinates: ");

            String s = scanner.nextLine();

            if (Objects.equals(s, null)) {
                System.out.println("You should enter numbers!");
                continue;
            }
            String[] stringArr = s.split(" ");

            if (stringArr.length != 2) {
                System.out.println("You should enter two numbers with a space!");
                continue;
            }

            coordinates = new int[2];

            for (int i = 0; i < coordinates.length; i++) {
                try {
                    coordinates[i] = Integer.parseInt(stringArr[i]);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            }

            if (coordinates[0] <= 0 || coordinates[0] > 3
                    || coordinates[1] <= 0 || coordinates[1] > 3) {
                System.out.println("Coordinates should be from 1 to 3!");

            } else if (Game.field[coordinates[0] - 1][coordinates[1] - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");

            } else {
                moveMadeSuccessfully = true;
//                System.0out.println("moveMadeSuccessfully");
                //уменьшаем значение координат на единицу
                --coordinates[0];
                --coordinates[1];
                Utility.upgradeTheField(coordinates, xOrO);
            }

        } while (!moveMadeSuccessfully);
    }
}
