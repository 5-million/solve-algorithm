import java.util.Arrays;
import java.util.Scanner;

/**
 * D3
 * 14361 - 숫자가 같은 배수
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYCnY9Kqu6YDFARx&categoryId=AYCnY9Kqu6YDFARx&categoryType=CODE&problemTitle=14361&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int input = sc.nextInt();
            String inputAsStr = Integer.toString(input);

            int[] usingNumber = new int[10];
            for (char ch : inputAsStr.toCharArray()) {
                usingNumber[ch - '0']++;
            }

            int k = 2;
            boolean flag = false;
            while (inputAsStr.length() == Integer.toString(k * input).length()) {
                int[] count = Arrays.copyOf(usingNumber, 10);
                for (char ch : Integer.toString(k * input).toCharArray()) {
                    count[ch - '0']--;
                }

                if (isValid(count)) {
                    flag = true;
                    break;
                }

                k++;
            }

            if (flag) {
                System.out.printf("#%d possible\n", tc);
            } else System.out.printf("#%d impossible\n", tc);
        }
    }

    private static boolean isValid(int[] count) {
        for (int cnt : count) {
            if (cnt != 0) return false;
        }
        return true;
    }
}