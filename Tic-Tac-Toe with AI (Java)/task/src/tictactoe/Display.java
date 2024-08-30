package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class Display {
    private final Scanner scanner;
//
    public Display(Scanner scanner){
        this.scanner = scanner;
    }

    public void inputMenuChoice() {
//        System.out.println("Method: inputMenuChoice");
        boolean isInputCorrect = false;
        String[] userParamsArray = null;

        do {
            System.out.print("Input command: ");
            String userInput = scanner.nextLine();

            //проверка на NULL, чтобы выполнить split
            if (Objects.equals(userInput, null)) {
                System.out.println("Bad parameters!");
                continue;
            }
            userParamsArray = userInput.split(" ");

            try {
                Game.menuChoice = UserParameters.fromString(userParamsArray[0]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            if (Game.menuChoice == UserParameters.EXIT) {
                //TODO поднять код на уровень вверх
                scanner.close();
                System.exit(0);
            }

            if (userParamsArray.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            try {
                Game.firstPlayer = UserParameters.fromString(userParamsArray[1]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                Game.secondPlayer = UserParameters.fromString(userParamsArray[2]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            isInputCorrect = true;
        } while (!isInputCorrect);
    }
}
