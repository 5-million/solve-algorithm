import java.util.Scanner;

/**
 * Beginner_Coder
 * 도형만들기2: 1523 - 별삼각형1
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=795&sca=2020
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        if (n <= 0 || n > 100 || m <= 0 || m > 3)  {
            System.out.println("INPUT ERROR!");
            return;
        }

        switch (m) {
            case 1:
                printType1(n);
                break;
            case 2:
                printType2(n);
                break;
            default:
                printType3(n);
        }
    }

    private static void printType1(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append("*".repeat(i)).append("\n");
        }
        System.out.println(sb);
    }

    private static void printType2(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            sb.append("*".repeat(i)).append("\n");
        }
        System.out.println(sb);
    }

    private static void printType3(int n) {
        StringBuilder sb = new StringBuilder();
        for (int space = n - 1, star = 1; space >= 0; space--, star+=2) {
            sb.append(" ".repeat(space)).append("*".repeat(star)).append("\n");
        }
        System.out.println(sb);
    }
}