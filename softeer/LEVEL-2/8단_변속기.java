import java.util.*;
import java.io.*;

public class Main {

    private static final String ASC = "ascending";
    private static final String DESC = "descending";
    private static final String MIX = "mixed";

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] seq = br.readLine().split(" ");

        String answer;
        if ("1".equals(seq[0])) { // 시작이 1로 시작하면 ascending
            answer = ASC;
        } else if ("8".equals(seq[0])) { // 시작이 8로 시작하면 descending
            answer = DESC;
        } else { // 둘다 아니라면 mixed
            answer = MIX;
            System.out.println(answer);
            return;
        }

        int pre = Integer.parseInt(seq[0]);
        int len = seq.length;
        for (int i = 1; i < len; i++) {
            int cur = Integer.parseInt(seq[i]);

            if (Math.abs(cur - pre) != 1) { // 이전 숫자와 현재 숫자의 차가 1이 아니면
                answer = MIX; // 정답은 mixed로 반복문 종료
                break;
            }

            pre = cur; // 이전 숫자 값을 현재 숫자 값으로 변경
        }

        System.out.println(answer);
    }
}