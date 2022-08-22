import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G5
 * 14719 - 빗물
 * https://www.acmicpc.net/problem/14719
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] height = new int[w];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int right = 1; right < w; right++) {
            if (height[right - 1] < height[right]) {
                int bottom = height[right - 1];

                for (int i = right - 2; i >= 0; i--) {
                    if (bottom < height[i]) {
                        answer += (right - i - 1) * (Math.min(height[right], height[i]) - bottom);
                        bottom = height[i];
                    }

                    if (height[right] <= height[i]) break;
                }
            }
        }

        System.out.println(answer);
    }
}