import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double input = scanner.nextDouble();
        double result = Math.pow(input, 3) + Math.pow(input, 2) + input + 1;
        System.out.println(result);
        scanner.close();
    }
}