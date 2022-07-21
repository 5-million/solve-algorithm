import java.util.Scanner;

/**
 * Beginner_Coder
 * 도형만들기2: 1329 - 별삼각형3
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=608&sca=2020
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n % 2 == 0 || n > 100) {
            System.out.println("INPUT ERROR!");
            return;
        }
        
        int space = 0, star = 1;
        StringBuilder sb = new StringBuilder();

        for (; star < n; space++, star += 2) {
            sb.append(buildLine(space, star));
        }

        for (; star > 0; space--, star -= 2) {
            sb.append(buildLine(space, star));
        }

        System.out.println(sb);
    }

    private static String buildLine(int spaceCnt, int starCnt) {
        String space = " ";
        String star = "*";
        return space.repeat(spaceCnt) + star.repeat(starCnt) + "\n";
    }
}