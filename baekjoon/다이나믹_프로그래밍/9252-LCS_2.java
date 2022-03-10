import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 9252 - LCS 2
 * https://www.acmicpc.net/problem/9252
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        lcs(str1, str2);
    }

    private static void lcs(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        int[][] arr = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) arr[i][j] = arr[i - 1][j - 1] + 1;
                else arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = len1, j = len2;
        while (arr[i][j] != 0) {
            if (arr[i][j] == arr[i - 1][j]) i--;
            else if(arr[i][j] == arr[i][j - 1]) j--;
            else {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            }
        }

        System.out.println(arr[len1][len2]);
        System.out.println(sb.reverse());
    }
}