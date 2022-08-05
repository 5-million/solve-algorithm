import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S2
 * 12891 - DNA 비밀번호
 * https://www.acmicpc.net/problem/12891
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        st = new StringTokenizer(br.readLine());
        int[] minCnt = {
                Integer.parseInt(st.nextToken()), // 'A'
                Integer.parseInt(st.nextToken()), // 'C'
                Integer.parseInt(st.nextToken()), // 'G'
                Integer.parseInt(st.nextToken()) // 'T'
        };
        int[] cnt = new int[4];

        int head = 0;
        int tail = p - 1;
        for (int i = head; i <= tail; i++) {
            cnt[getChIdx(str.charAt(i))]++;
        }
        int answer = isValid(minCnt, cnt) ? 1 : 0;

        while (tail < s - 1) {
            cnt[getChIdx(str.charAt(head++))]--;
            cnt[getChIdx(str.charAt(++tail))]++;
            if(isValid(minCnt, cnt)) answer++;
        }

        System.out.println(answer);
    }

    private static int getChIdx(char ch) {
        switch (ch) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            default:
                return 3;
        }
    }

    private static boolean isValid(int[] minCnt, int[] cnt) {
        for (int i = 0; i < 4; i++) {
            if (cnt[i] < minCnt[i])
                return false;
        }
        return true;
    }
}