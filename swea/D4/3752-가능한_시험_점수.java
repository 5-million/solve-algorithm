import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * D4
 * 3752 - 가능한 시험 점수
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWHPkqBqAEsDFAUn
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            Set<Integer> set = new HashSet<>();
            set.add(0);

            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int score = Integer.parseInt(st.nextToken());
                List<Integer> list = new ArrayList<>();
                for (Integer s : set) {
                    int temp = s + score;
                    if (!set.contains(temp)) {
                        list.add(temp);
                    }
                }
                set.addAll(list);
            }

            System.out.printf("#%d %d\n", tc, set.size());
        }
    }
}