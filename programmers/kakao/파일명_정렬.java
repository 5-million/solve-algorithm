import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LEVEL-2
 * 2018 KAKAO BLIND RECRUITMENT > [3차] 파일명 정렬
 */
public class Solution {

    private class File implements Comparable<File> {
        String fullName;
        String head;
        int number;
        String tail;

        public File(String fullName, String head, int number, String tail) {
            this.fullName = fullName;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        @Override
        public int compareTo(File o) {
            int comp = this.head.compareToIgnoreCase(o.head);

            if (comp == 0) {
                return this.number - o.number;
            } else return comp;
        }
    }

    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();

        for (String file : files) {
            char[] chars = file.toCharArray();
            int numberIndex = 0;
            while (!isNumber(chars[numberIndex])) numberIndex++;

            int tailIndex = numberIndex + 1;
            while (tailIndex < file.length() && isNumber(chars[tailIndex])) tailIndex++;

            String head = file.substring(0, numberIndex);
            int number = Integer.parseInt(file.substring(numberIndex, tailIndex));
            String tail = file.substring(tailIndex);

            fileList.add(new File(file, head, number, tail));
        }

        Collections.sort(fileList);

        return fileList.stream().map(f -> f.fullName).toArray(String[]::new);
    }

    private boolean isNumber(char c) {
        return 48 <= c && c <= 57;
    }
}
