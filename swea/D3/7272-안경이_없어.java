import java.util.*;

/**
 * D3
 * 7272 - 안경이 없어!
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWl0ZQ8qn7UDFAXz
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            String str1 = sc.next();
            String str2 = sc.next();
            boolean result = isSame(str1, str2);
            System.out.printf("#%d %s\n", tc, result ? "SAME" : "DIFF");
        }
    }

    private static boolean isSame(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        for (int i = 0; i < str1.length(); i++) {
            if (!isSameChar(str1.charAt(i), str2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSameChar(char ch1, char ch2) {
        char[] oneHole = {'A', 'D', 'O', 'P', 'Q', 'R'};
        Set<Character> oneHoleSet = new HashSet<>();
        for (char ch : oneHole) {
            oneHoleSet.add(ch);
        }

        if (ch1 == 'B' || ch2 == 'B') return ch1 == ch2;
        if (oneHoleSet.contains(ch1) && oneHoleSet.contains(ch2)) return true;
        if (!oneHoleSet.contains(ch1) && !oneHoleSet.contains(ch2)) return true;
        return false;
    }
}

