import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * greedy
 * 19598 - 최소 회의실 개수(G5)
 * https://www.acmicpc.net/problem/19598
 */
public class Main {

    private static class Conference {
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    private static final PriorityQueue<Conference> conferences = new PriorityQueue<>(Comparator.comparingInt(Conference::getStart));
    private static final PriorityQueue<Conference> conferenceRoom = new PriorityQueue<>(Comparator.comparingInt(Conference::getEnd));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            conferences.add(new Conference(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }

        System.out.println(getMinConferenceRoom());
    }

    private static int getMinConferenceRoom() {
        int cnt = 0;
        while (!conferences.isEmpty()) {
            if (conferenceRoom.isEmpty() || conferences.peek().start < conferenceRoom.peek().end) {
                conferenceRoom.add(conferences.poll());
            } else if (conferences.peek().start >= conferenceRoom.peek().end) {
                conferenceRoom.poll();
                conferenceRoom.add(conferences.poll());
            }

            cnt = Math.max(cnt, conferenceRoom.size());
        }
        return cnt;
    }
}
