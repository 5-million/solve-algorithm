import java.util.*;
import java.io.*;


public class Main {

    private static int h, w;
    private static char[][] map;
    private static boolean[][] visit;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // UP, RIGHT, DOWN, LEFT
    private static char[] dirChar = {'^', '>', 'v', '<'};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        visit = new boolean[h][w];

        for (int y = 0; y < h; y++) {
            String input = br.readLine();
            for (int x = 0; x < w; x++) {
                map[y][x] = input.charAt(x);
            }
        }

        int[] start = {0, 0};
        char direction = dirChar[0];
        String commands = "FAIL";
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (map[y][x] == '.') {
                    continue;
                }

                map[y][x] = '.';
                visit[y][x] = true;
                
                for (int d = 0; d < 4; d++) {
                    String result = dfs(d, y, x, new Stack<>());
                    if (!"FAIL".equals(result)) {
                        if ("FAIL".equals(commands)) {
                            start[0] = y;
                            start[1] = x;
                            direction = dirChar[d];
                            commands = result;
                        } else if (commands.length() > result.length()) {
                            start[0] = y;
                            start[1] = x;
                            direction = dirChar[d];
                            commands = result;
                        }
                    }
                }

                map[y][x] = '#';
                visit[y][x] = false;
            }
        }

        System.out.println((start[0] + 1) + " " + (start[1] + 1));
        System.out.println(direction);
        System.out.println(commands);
    }

    private static String dfs(int d, int y, int x, Stack<String> commands) {
        if (isComplete()) {
            String command = "";
            for (String cmd : commands) {
                command += cmd;
            }

            return command;
        }

        String ret = "FAIL";
        for (int i = 0; i < 4; i++) {
            int nd = (d + i) % 4;
            int[] ndir = dir[nd];

            if (canGo(nd, y, x)) {
                if (i == 1) {
                    commands.push("R");
                } else if (i == 2) {
                    commands.push("R");
                    commands.push("R");
                } else if (i == 3) {
                    commands.push("L");
                }
                commands.push("A");

                map[y + ndir[0]][x + ndir[1]] = '.';
                visit[y + ndir[0]][x + ndir[1]] = true;
                map[y + ndir[0] * 2][x + ndir[1] * 2] = '.';
                visit[y + ndir[0] * 2][x + ndir[1] * 2] = true;

                String result = dfs(nd, y + ndir[0] * 2, x + ndir[1] * 2, commands);
                if (!"FAIL".equals(result)) {
                    if ("FAIL".equals(ret)) {
                        ret = result;
                    } else if (ret.length() > result.length()) {
                        ret = result;
                    }
                }

                if (i == 1 || i == 3) {
                    commands.pop();
                } else if (i == 2) {
                    commands.pop();
                    commands.pop();
                }
                commands.pop();

                map[y + ndir[0]][x + ndir[1]] = '#';
                map[y + ndir[0] * 2][x + ndir[1] * 2] = '#';
                visit[y + ndir[0]][x + ndir[1]] = false;
                visit[y + ndir[0] * 2][x + ndir[1] * 2] = false;
            }
        }

        return ret;
    }

    private static boolean isComplete() {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (map[y][x] == '#') {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean canGo(int d, int y, int x) {
        for (int i = 1; i <= 2; i++) {
            int ny = y + dir[d][0] * i;
            int nx = x + dir[d][1] * i;

            if (!checkRange(ny, nx) || visit[ny][nx] || map[ny][nx] == '.') {
                return false;
            }
        }

        return true;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}