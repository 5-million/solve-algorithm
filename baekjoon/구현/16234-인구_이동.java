import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G5
 * 16234 - 인구 이동
 * https://www.acmicpc.net/problem/16234
 */
public class Main {

    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Union {
        List<Pos> country = new ArrayList<>();
        int population;

        public int getAvg() {
            return population / country.size();
        }

        public void add(Pos country, int population) {
            this.country.add(country);
            this.population += population;
        }
    }

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int n, l, r;
    private static int[][] population;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        population = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            boolean[][] v = new boolean[n][n];
            Queue<Union> unions = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!v[i][j]) {
                        Union uni = bfs(v, new Pos(i, j));
                        if (uni != null) {
                            unions.offer(uni);
                        }
                    }
                }
            }

            if (unions.isEmpty())
                break;

            day++;
            while (!unions.isEmpty()) {
                Union uni = unions.poll();
                int popAvg = uni.getAvg();
                for (Pos pos : uni.country) {
                    population[pos.y][pos.x] = popAvg;
                }
            }
        }

        System.out.println(day);
    }

    public static Union bfs(boolean[][] v, Pos start) {
        Union union = new Union();
        Queue<Pos> q = new LinkedList<>();

        union.add(start, population[start.y][start.x]);
        q.add(start);
        v[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Pos country = q.poll();
            for (int[] d : dir) {
                int ny = country.y + d[0];
                int nx = country.x + d[1];

                if (checkRange(ny, nx) && checkPopulation(country, ny, nx) && !v[ny][nx]) {
                    Pos uni = new Pos(ny, nx);
                    union.add(uni, population[ny][nx]);
                    q.add(uni);
                    v[ny][nx] = true;
                }
            }
        }

        return union.country.size() > 1 ? union : null;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    private static boolean checkPopulation(Pos c1, int ny, int nx) {
        int diff = Math.abs(population[c1.y][c1.x] - population[ny][nx]);
        return l <= diff && diff <= r;
    }
}