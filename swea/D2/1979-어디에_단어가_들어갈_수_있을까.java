import java.util.Scanner;
 
/**
 * D2
 * 1979 - 어디에 단어가 들어갈 수 있을까
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PuPq6AaQDFAUq
 */
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int t = sc.nextInt();
        int c = 1;
        while (c <= t) {
            int answer = 0;
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[][] board = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
 
            for (int i = 0; i < n; i++) {
                int a = 0, b = 0;
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 1) a++;
                    else {
                        if (a == k) answer++;
                        a = 0;
                    }
 
                    if (board[j][i] == 1) b++;
                    else {
                        if (b == k) answer++;
                        b = 0;
                    }
                }
 
                if (a == k) answer++;
                if (b == k) answer++;
            }
 
            System.out.printf("#%d %d\n", c, answer);
            c++;
        }
    }
}