import java.io.*;
import java.util.*;

/**
 * G5
 * 2023 - 신기한 소수
 * https://www.acmicpc.net/problem/2023
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());

        Queue<String> primeNumber = new LinkedList<>(Arrays.asList("2", "3", "5", "7"));

        int l = 1;
        StringBuilder sb = new StringBuilder();
        while (l < len) {
            int qSize = primeNumber.size();
            l++;
            for (int i = 0; i < qSize; i++) {
                String now = primeNumber.poll();

                loop1: for (int j = 1; j < 10; j += 2) {
                    int temp = Integer.parseInt(now + j);
                    for (int k = 2; k * k <= temp; k++) {
                        if (temp % k == 0)
                            continue loop1;
                    }

                    if (l == len) {
                        sb.append(temp).append("\n");
                    } else {
                        primeNumber.add("" + temp);
                    }
                }
            }
        }

        if (len == 1) {
            for (String pn : primeNumber) {
                sb.append(pn).append("\n");
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}