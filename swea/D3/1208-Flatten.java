import java.util.Scanner;

/**
 * D3
 * 1208 - Flatten
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            int dumpCount = sc.nextInt();
            int[] height = new int[100];

            for (int i = 0; i < 100; i++) {
                height[i] = sc.nextInt();
            }

            flatten(height, dumpCount);

            int maxValue = 0, minValue = 100;
            for (int i = 0; i < 100; i++) {
                if (maxValue < height[i]) {
                    maxValue = height[i];
                }

                if (minValue > height[i]) {
                    minValue = height[i];
                }
            }

            System.out.printf("#%d %d\n", tc, maxValue - minValue);
        }
    }

    private static void flatten(int[] height, int dumpCount) {
        for (int i = 0; i < dumpCount; i++) {
            dump(height);
        }
    }

    private static void dump(int[] height) {
        int maxValue = 0, minValue = 100;
        int maxIdx = 0, minIdx = 0;
        for (int i = 0; i < 100; i++) {
            if (maxValue < height[i]) {
                maxIdx = i;
                maxValue = height[i];
            }

            if (minValue > height[i]) {
                minIdx = i;
                minValue = height[i];
            }
        }

        height[minIdx] += 1;
        height[maxIdx] -= 1;
    }
}
