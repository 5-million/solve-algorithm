import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int i = 1;
        while (i <= t) {
            int l1 = sc.nextInt();
            int l2 = sc.nextInt();

            int[] arr1 = new int[l1];
            for (int j = 0; j < l1; j++) {
                arr1[j] = sc.nextInt();
            }

            int[] arr2 = new int[l2];
            for (int j = 0; j < l2; j++) {
                arr2[j] = sc.nextInt();
            }

            if (l1 > l2) {
                int[] temp = arr1;
                arr1 = arr2;
                arr2 = temp;
            }

            System.out.printf("#%d %d\n", i, solution(arr1, arr2));

            i++;
        }
    }

    private static int solution(int[] arr1, int[] arr2) {
        int ret = 0;

        int l1 = arr1.length, l2 = arr2.length;
        for (int i = 0; i <= l2 - l1; i++) {
            int hap = 0;
            for (int j = 0; j < l1; j++) {
                hap += arr1[j] * arr2[i + j];
            }
            ret = Math.max(ret, hap);
        }

        return ret;
    }
}