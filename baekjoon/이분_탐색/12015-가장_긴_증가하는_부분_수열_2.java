import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[] sequence;
    private static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        sequence = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int solution() {
        for (int elem : sequence) {
            if (lis.isEmpty() || lis.get(lis.size()-1) < elem)
                lis.add(elem);

            int idx = lowerBound(elem);
            lis.set(idx, elem);
        }

        return lis.size();
    }

    private static int lowerBound(int target) {
        int start = 0;
        int end = lis.size();

        while (start < end) {
            int mid = (start + end) / 2;
            if (target <= lis.get(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }
}