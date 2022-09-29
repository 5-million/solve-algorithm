import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * G5
 * 2011 - 암호코드
 * https://www.acmicpc.net/problem/2011
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        long[] dp = new long[input.length() + 1];
        dp[0] = 1;
        dp[1] = input.charAt(0) == '0' ? 0 : 1;

        if (dp[1] == 0) {
            System.out.println(0);
            return;
        }

        int mod = 1000000;
        for (int i = 2; i <= input.length(); i++) {
            if (input.charAt(i - 1) != '0') {
                dp[i] = (dp[i] + dp[i - 1]) % mod;
            } else if (input.charAt(i - 2) == '0') {
                break;
            }

            int temp = Integer.parseInt(input.substring(i - 2, i));
            if (10 <= temp && temp <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % mod;
            }
        }

        System.out.println(dp[input.length()]);
    }
}