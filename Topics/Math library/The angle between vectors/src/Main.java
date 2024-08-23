import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int u1 = sc.nextInt();
        int u2 = sc.nextInt();
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        double moduleU = Math.sqrt(
                Math.pow(u1,2) + Math.pow(u2,2));

        double moduleV = Math.sqrt(
                Math.pow(v1,2) + Math.pow(v2,2));

        //u*v
        double uMultv = u1*v1 + u2*v2;

        double angle = Math.toDegrees(Math.acos(uMultv / (moduleU*moduleV)));
        System.out.println(angle);

        sc.close();

        //System.out.println(Math.toDegrees(Math.acos(-1.0)));
    }
}