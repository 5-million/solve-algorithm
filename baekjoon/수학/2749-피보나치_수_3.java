import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 2749 - 피보나치 수 3
 * https://www.acmicpc.net/problem/2749
 */
public class Main {

    private static final int MOD = 1000000;
    private static final List<Long> fibonacci = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long n = Long.parseLong(br.readLine());
        int period = pisanoPeriod();
        System.out.println(fibonacci.get((int) (n % period) - 1));
    }

    private static int pisanoPeriod() {
        fibonacci.add(1L);
        fibonacci.add(1L);
        int i = 2;
        while (true) {
            fibonacci.add((fibonacci.get(i - 1) + fibonacci.get(i - 2)) % MOD);
            if (fibonacci.get(i - 1) == 1 && fibonacci.get(i) == 1)
                return i - 1;

            i++;
        }
    }
}

