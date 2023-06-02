import java.util.*;
import java.io.*;

public class Main {

    private static class Jewellery implements Comparable<Jewellery> {

        int totalWeight;
        int pricePerWeight;

        public Jewellery(int totalWeight, int pricePerWeight) {
            this.totalWeight = totalWeight;
            this.pricePerWeight = pricePerWeight;
        }

        @Override
        public int compareTo(Jewellery jewellery) {
            return jewellery.pricePerWeight - this.pricePerWeight;
        }
    }

    private static int W, N;
    private static PriorityQueue<Jewellery> pq = new PriorityQueue<>();

    public static void main(String args[]) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            pq.add(new Jewellery(w, n));
        }
    }
    
    private static void solution() {
        long answer = 0;
        while (W != 0 && !pq.isEmpty()) {
            Jewellery jewellery = pq.poll();

            // 귀금속의 전체 무게보다 가방의 남은 무게가 더 크면
            if (jewellery.totalWeight <= W) {
                W -= jewellery.totalWeight;
                answer += jewellery.totalWeight * jewellery.pricePerWeight;
            } else { // 가방의 남은 무게보다 귀금속의 전체 무게가 더 크면
                // 가방의 남은 무게를 현재 귀금속으로 전부 채움
                answer += W * jewellery.pricePerWeight;
                W = 0;
            }
        }

        System.out.println(answer);
    }
}