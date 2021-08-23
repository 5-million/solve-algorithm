import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int wireCount;
    private static List<Integer> sequence = new ArrayList<>();

    public static void main(String[] args) {
        init();
        System.out.println(wireCount - lis());
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        wireCount = sc.nextInt();
        int[] connect = new int[501];

        for (int i = 0; i < wireCount; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            connect[a] = b;
        }

        for (int dest : connect)
            if (dest != 0)
                sequence.add(dest);
    }

    private static int lis() {
        int[] dp = new int[wireCount];

        int lisCount = 1;
        for (int i = 0; i < wireCount; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sequence.get(j) < sequence.get(i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    lisCount = Math.max(lisCount, dp[i]);
                }
            }
        }

        return lisCount;
    }
}