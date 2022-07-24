import java.util.Scanner;

/**
 * S3
 * 17413 - 단어 뒤집기 2
 * https://www.acmicpc.net/problem/17413
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String answer = "";
        boolean inTag = false;
        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (ch == '<') {
                inTag = true;
                answer += sb.reverse();
                clearStringBuilder(sb);
                sb.append(ch);
            } else if (ch == '>') {
                inTag = false;
                sb.append(ch);
                answer += sb;
                clearStringBuilder(sb);
            } else if (ch == ' ') {
                if (inTag) {
                    sb.append(ch);
                } else {
                    answer += sb.reverse();
                    answer += " ";
                    clearStringBuilder(sb);
                }
            } else {
                sb.append(ch);
            }
        }
        answer += sb.reverse();
        System.out.println(answer);
    }

    private static void clearStringBuilder(StringBuilder sb) {
        sb.setLength(0);
    }
}