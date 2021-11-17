import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LEVEL-2
 * 위클리 챌린지 > 모음사전
 */
public class Solution {

    Set<String> allWords = new HashSet<>();
    String[] alpha = new String[]{"A", "E", "I", "O", "U"};

    public int solution(String word) {
        buildAllWords();
        List<String> result = allWords.stream().sorted().collect(Collectors.toList());

        return result.indexOf(word) + 1;
    }

    private void buildAllWords() {
        for (int i = 1; i < 6; i++) {
            buildWord("", i);
        }
    }

    private void buildWord(String data, int size) {
        if (data.length() == size)
            allWords.add(data);
        else {
            for (String ch : alpha) {
                buildWord(data + ch, size);
            }
        }
    }
}