import java.util.*;
import java.io.*;


public class Main {

    private static Map<Character, int[]> table = new HashMap<>();
    private static char[][] chipherTable = new char[5][5];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String plainText = br.readLine();
        String key = br.readLine();

        makeChipherTable(key);
        List<String> pairs = makePair(plainText);
        System.out.println(encrypt(pairs));
    }

    private static void makeChipherTable(String key) {
        int arrange = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch == 'J') {
                ch = 'I';
            }
            
            if (!table.containsKey(ch)) {
                int y = arrange / 5;
                int x = arrange % 5;
                chipherTable[y][x] = ch;
                table.put(ch, new int[] {y, x});
                arrange++;
            }
        }

        for (int i = 'A'; i <= 'Z'; i++) {
            char ch = (char) i;
            if (ch == 'J') {
                continue;
            }

            if (!table.containsKey(ch)) {
                int y = arrange / 5;
                int x = arrange % 5;
                chipherTable[y][x] = ch;
                table.put(ch, new int[] {y, x});
                arrange++;
            }
        }
    }

    private static List<String> makePair(String plainText) {
        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i++) {
            char ch1 = plainText.charAt(i);

            if (i + 1 == plainText.length()) {
                pairs.add("" + ch1 + 'X');
                break;
            }
            char ch2 = plainText.charAt(i + 1);

            if (ch1 == ch2) {
                if (ch1 != 'X') {
                    pairs.add("" + ch1 + 'X');
                } else {
                    pairs.add("" + ch1 + 'Q');
                }
            } else {
                pairs.add("" + ch1 + ch2);
                i++;
            }
        }

        return pairs;
    }

    private static String encrypt(List<String> pairs) {
        String result = "";
        for (String pair : pairs) {
            int[] p1 = table.get(pair.charAt(0));
            int[] p2 = table.get(pair.charAt(1));

            if (p1[0] == p2[0]) { // 두 글자가 같은 행에 존재
                result += chipherTable[p1[0]][(p1[1] + 1) % 5];
                result += chipherTable[p1[0]][(p2[1] + 1) % 5];
            } else if (p1[1] == p2[1]) { // 두 글자가 같은 열에 존재
                result += chipherTable[(p1[0] + 1) % 5][p1[1]];
                result += chipherTable[(p2[0] + 1) % 5][p1[1]];
            } else {
                result += chipherTable[p1[0]][p2[1]];
                result += chipherTable[p2[0]][p1[1]];
            }
        }

        return result;
    }
}