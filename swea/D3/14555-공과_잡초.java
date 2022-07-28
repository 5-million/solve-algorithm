import java.util.Scanner;

/**
 * D3
 * 14555 - 공과 잡초
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AYGtoa3qARcDFARC&categoryId=AYGtoa3qARcDFARC&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=1
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            String field = sc.next();
            int ball = 0;
            for (int i = 0; i < field.length(); i++) {
                if (field.charAt(i) == '(') {
                    ball++;
                } else if (field.charAt(i) == ')' && field.charAt(i - 1) == '|') {
                    ball++;
                }
            }

            System.out.printf("#%d %d\n", tc, ball);
        }
    }
}