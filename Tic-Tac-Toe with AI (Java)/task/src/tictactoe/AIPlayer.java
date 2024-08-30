package tictactoe;

import java.util.Scanner;

public abstract class AIPlayer {
    private final Scanner scanner;

    public AIPlayer(Scanner scanner){
        this.scanner = scanner;
    }

    public abstract void move(char xOrO);
}
