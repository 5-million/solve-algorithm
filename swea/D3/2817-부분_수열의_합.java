import java.util.Scanner;

/**
 * D3
 * 2817 - 부분 수열의 합
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7IzvG6EksDFAXB
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] seq = new int[n];
            for (int i = 0; i < n; i++) {
                seq[i] = sc.nextInt();
            }

            System.out.printf("#%d %d\n", tc, subset(seq, new boolean[n], 0, k));
        }
    }

    private static int subset(int[] seq, boolean[] v, int idx, int k) {
        if (k < 0) return 0;
        if (k == 0) return 1;

        int ret = 0;
        for (int i = idx; i < seq.length; i++) {
            if (!v[i]) {
                v[i] = true;
                ret += subset(seq, v, i + 1, k - seq[i]);
                v[i] = false;
            }
        }
        return ret;
    }
}