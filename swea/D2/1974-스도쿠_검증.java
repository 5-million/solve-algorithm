import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * D2
 * 1974 - 스도쿠 검증
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Psz16AYEDFAUq
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int c = 1; c <= t; c++) {
            int[][] sudoku = new int[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = sc.nextInt();
                }
            }

            System.out.printf("#%d %d\n", c, isValid(sudoku) ? 1 : 0);
        }
    }

    private static boolean isValid(int[][] sudoku) {
        Set[] col = new Set[9];
        Set[] row = new Set[9];
        Set[][] square = new Set[9][9];

        for (int i = 0; i < 9; i++) {
            if (col[i] == null)
                col[i] = new HashSet<Integer>();

            for (int j = 0; j < 9; j++) {
                if (row[j] == null)
                    row[j] = new HashSet<Integer>();

                int y = i / 3;
                int x = j / 3;
                if (square[y][x] == null)
                    square[y][x] = new HashSet<Integer>();

                int num = sudoku[i][j];
                if (col[i].contains(num) || row[j].contains(num) || square[y][x].contains(num))
                    return false;

                col[i].add(num);
                row[j].add(num);
                square[y][x].add(num);
            }
        }

        return true;
    }
}