import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        solution();
    }

    private static void solution() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            int bae = 0;
            for (int j = 0; j < 4; j++) {
                if (sc.nextInt() == 0)
                    bae++;
            }

            if (bae == 0) System.out.println("E");
            else if (bae == 1) System.out.println("A");
            else if (bae == 2) System.out.println("B");
            else if (bae == 3) System.out.println("C");
            else System.out.println("D");
        }
    }
}