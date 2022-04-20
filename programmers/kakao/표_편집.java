import java.util.Stack;

/**
 * LEVEL-3
 * 2021 카카오 채용연계형 인턴십 > 표 편집
 * https://programmers.co.kr/learn/courses/30/lessons/81303
 */
public class Solution {

    private class Data {
        Data front;
        boolean exists;
        Data back;

        public Data(Data front) {
            this.front = front;
            this.exists = true;
        }

        public Data delete() {
            exists = false;

            if (front != null) front.back = back;
            if (back != null) {
                back.front = front;
                return back;
            } else {
                return front;
            }
        }

        public void restore() {
            exists = true;
            if (front != null) front.back = this;
            if (back != null) back.front = this;
        }
    }

    private class Table {
        Data[] data;
        Data selected;
        Stack<Data> deleted = new Stack<>();

        public Table(int n, int k) {
            data = new Data[n];
            data[0] = new Data(null);
            for (int i = 1; i < n; i++) {
                data[i] = new Data(data[i - 1]);
                data[i - 1].back = data[i];
            }

             selected = data[k];
        }

        public Data[] getData() {
            return data;
        }

        public void u(int x) {
            while (x-- > 0) {
                selected = selected.front;
            }
        }

        public void d(int x) {
            while (x-- > 0) {
                selected = selected.back;
            }
        }

        public void c() {
            deleted.push(selected);
            selected = selected.delete();
        }

        public void z() {
            deleted.pop().restore();
        }
    }

    public String solution(int n, int k, String[] command) {
        Table table = new Table(n, k);
        for (String cmd : command) {
            String[] s = cmd.split(" ");
            switch (s[0]) {
                case "U":
                    table.u(Integer.parseInt(s[1]));
                    break;
                case "D":
                    table.d(Integer.parseInt(s[1]));
                    break;
                case "C":
                    table.c();
                    break;
                default:
                    table.z();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Data data : table.getData()) {
            if (data.exists) sb.append("O");
            else sb.append("X");
        }
        return sb.toString();
    }
}