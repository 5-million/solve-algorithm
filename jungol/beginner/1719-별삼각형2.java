import java.util.Scanner;

/**
 * Beginner_Coder
 * 도형만들기2: 1719 - 별삼각형2
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=992&sca=2020
 */
public class Main {

    private static final String SPACE = " ";
    private static final String STAR = "*";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        if (n <= 0 || n > 100 || n % 2 == 0 || m <= 0 || m > 4) {
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
            case 3:
                printType3(n);
                break;
            default:
                printType4(n);
        }
    }

    private static void printType1(int n) {
        int maxStarCnt = (int) Math.ceil(n / 2.0);
        int starCnt = 1;

        StringBuilder sb = new StringBuilder();
        for (; starCnt <= maxStarCnt; starCnt++) {
            sb.append(STAR.repeat(starCnt)).append("\n");
        }
        starCnt -= 2;
        for (; starCnt > 0; starCnt--) {
            sb.append(STAR.repeat(starCnt)).append("\n");
        }

        System.out.println(sb);
    }

    private static void printType2(int n) {
        int maxSpCnt = n / 2;
        int spCnt = maxSpCnt;
        int starCnt = 1;

        StringBuilder sb = new StringBuilder();
        for (; spCnt >= 0; spCnt--) {
            sb.append(SPACE.repeat(spCnt)).append(STAR.repeat(starCnt++)).append("\n");
        }
        starCnt -= 2;
        spCnt += 2;
        for (; spCnt <= maxSpCnt; spCnt++) {
            sb.append(SPACE.repeat(spCnt)).append(STAR.repeat(starCnt--)).append("\n");
        }

        System.out.println(sb);
    }

    private static void printType3(int n) {
        int maxStarCnt = 2 * (int) Math.ceil(n / 2.0) - 1;
        int starCnt = maxStarCnt;
        int spCnt = 0;

        StringBuilder sb = new StringBuilder();
        for (; starCnt > 0; starCnt -= 2) {
            sb.append(SPACE.repeat(spCnt++)).append(STAR.repeat(starCnt)).append("\n");
        }
        starCnt = 3;
        spCnt -= 2;
        for (; starCnt <= maxStarCnt; starCnt += 2) {
            sb.append(SPACE.repeat(spCnt--)).append(STAR.repeat(starCnt)).append("\n");
        }

        System.out.println(sb);
    }

    private static void printType4(int n) {
        int maxStarCnt = (int) Math.ceil(n / 2.0);
        int starCnt = maxStarCnt;
        int spCnt = 0;

        StringBuilder sb = new StringBuilder();
        while (starCnt > 0) {
            sb.append(SPACE.repeat(spCnt++)).append(STAR.repeat(starCnt--)).append("\n");
        }

        starCnt = 2;
        spCnt--;

        while (starCnt <= maxStarCnt) {
            sb.append(SPACE.repeat(spCnt)).append(STAR.repeat(starCnt++)).append("\n");
        }

        System.out.println(sb);
    }
}