import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 11723 - 집합
 * https://www.acmicpc.net/problem/11723
 */
public class Main {

    private static int s = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (m > 0) {
            String[] command = br.readLine().split(" ");

            switch (command[0]) {
                case "add":
                    add(Integer.parseInt(command[1]) - 1);
                    break;
                case "remove":
                    remove(Integer.parseInt(command[1]) - 1);
                    break;
                case "check":
                    if (check(Integer.parseInt(command[1]) - 1)) sb.append(1);
                    else sb.append(0);
                    sb.append("\n");
                    break;
                case "toggle":
                    toggle(Integer.parseInt(command[1]) - 1);
                    break;
                case "all":
                    all();
                    break;
                default:
                    empty();
            }

            m--;
        }

        System.out.println(sb);
    }

    private static void add(int x) {
        s = s | (1 << x);
    }

    private static void remove(int x) {
        if (check(x)) s -= (1 << x);
    }

    private static boolean check(int x) {
        return (s & (1 << x)) != 0;
    }

    private static void toggle(int x) {
        if (check(x)) remove(x);
        else add(x);
    }

    private static void all() {
        s = (1 << 20) - 1;
    }

    private static void empty() {
        s = 0;
    }
}

