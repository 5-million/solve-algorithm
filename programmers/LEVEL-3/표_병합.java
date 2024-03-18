import java.util.*;

class Solution {
    
    private static final String EMPTY = "EMPTY";
    private static final int TABLE_SIZE = 50;
    
    private static class Cell {
        private String value;
        private List<int[]> pos; // 이 cell 을 가리키고 있는 좌표들
        
        public Cell(int r, int c) {
            value = EMPTY;
            pos = new ArrayList<>();
            addPos(new int[] {r, c});
        }
        
        public String getValue() {
            return value;
        }
        
        public List<int[]> getPos() {
            return pos;
        }
        
        public void update(String newValue) {
            this.value = newValue;
        }
        
        public void addPos(int[] newPos) {
            pos.add(newPos);
        }
    }
    
    private Cell[][] table = new Cell[TABLE_SIZE + 1][TABLE_SIZE + 1];
    
    public String[] solution(String[] commands) {
        init();
        
        List<String> answerList = new ArrayList<>();
        for (String cmd : commands) {
            StringTokenizer st = new StringTokenizer(cmd);
            String cmdType = st.nextToken();
            
            if ("UPDATE".equals(cmdType)) {
                if (st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    update(r, c, value);
                } else {
                    String targetValue = st.nextToken();
                    String newValue = st.nextToken();
                    update(targetValue, newValue);
                }
            } else if ("MERGE".equals(cmdType)) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                merge(r1, c1, r2, c2);
            } else if ("UNMERGE".equals(cmdType)) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                unmerge(r, c);
            } else { // PRINT
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                answerList.add(print(r, c));
            }
        }
        
        String[] ans = new String[answerList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = answerList.get(i);
        }
        
        return ans;
    }
    
    private void init() {
        for (int r = 1; r <= TABLE_SIZE; r++) {
            for (int c = 1; c <= TABLE_SIZE; c++) {
                table[r][c] = new Cell(r, c);
            }
        }
    }
    
    private void update(int r, int c, String value) {
        table[r][c].update(value);
    }
    
    private void update(String targetValue, String newValue) {
        for (int r = 1; r <= TABLE_SIZE; r++) {
            for (int c = 1; c <= TABLE_SIZE; c++) {
                Cell cell = table[r][c];
                if (cell.getValue().equals(targetValue)) {
                    cell.update(newValue);
                }
            }
        }
    }
    
    private void merge(int r1, int c1, int r2, int c2) {
        if (isSameCell(r1, c1, r2, c2)) { // 같은 셀인 경우 무시
            return;
        }
        
        if ((table[r1][c1].getValue() != EMPTY) || 
            (table[r1][c1].getValue() == EMPTY && table[r2][c2].getValue() == EMPTY)) {
            // (r1, c1)의 값을 갖는 경우 (r2, c2) -> (r1, c1)
            for (int[] pos : table[r2][c2].getPos()) {
                table[r1][c1].addPos(pos);
                table[pos[0]][pos[1]] = table[r1][c1];
            }
        } else {
            // (r2, c2)의 값을 갖는 경우 (r1, c1) -> (r2, c2)
            for (int[] pos : table[r1][c1].getPos()) {
                table[r2][c2].addPos(pos);
                table[pos[0]][pos[1]] = table[r2][c2];
            }
        }
    }
    
    private boolean isSameCell(int r1, int c1, int r2, int c2) {
        for (int[] pos : table[r1][c1].getPos()) {
            if (pos[0] == r2 && pos[1] == c2) {
                return true;
            }
        }
        
        return false;
    }
    
    private void unmerge(int r, int c) {
        for (int[] pos : table[r][c].getPos()) {
            if (pos[0] == r && pos[1] == c) {
                continue;
            }
            
            table[pos[0]][pos[1]] = new Cell(pos[0], pos[1]);
        }
        
        table[r][c].getPos().clear();
        table[r][c].addPos(new int[] {r, c});
    }
    
    private String print(int r, int c) {
        return table[r][c].getValue();
    }
}