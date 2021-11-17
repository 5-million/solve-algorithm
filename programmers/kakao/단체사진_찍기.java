import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LEVEL-2
 * 2017 카카오코드 본선 > 단체사진 찍기
 */
public class Solution {

    List<List<Character>> all_cases = new ArrayList<>();

    public int solution(int n, String[] data) {
        Character[] elements = new Character[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        buildAllCase(Arrays.stream(elements).collect(Collectors.toList()), new ArrayList<>(), new boolean[8]);

        for (String condition : data)
            filter(condition);

        return all_cases.size();
    }

    private void buildAllCase(List<Character> elements, List<Character> c, boolean[] included) {
        if (c.size() == 8)
            all_cases.add(c);
        else {
            for (int i = 0; i < elements.size(); i++) {
                if (!included[i]) {
                    included[i] = true;
                    c.add(elements.get(i));

                    buildAllCase(elements, new ArrayList<>(c), included);

                    included[i] = false;
                    c.remove(elements.get(i));
                }
            }
        }
    }

    private void filter(String condition) {
        char target1 = condition.charAt(0);
        char target2 = condition.charAt(2);
        char range = condition.charAt(3);
        int distance = Integer.parseInt(Character.toString(condition.charAt(4)));

        List<List<Character>> temp = new ArrayList<>();
        for (int i = 0; i < all_cases.size(); i++) {
            int target1Idx = all_cases.get(i).indexOf(target1);
            int target2Idx = all_cases.get(i).indexOf(target2);

            int d = Math.abs(target2Idx - target1Idx);

            if (range == '=' && distance + 1 == d)
                temp.add(all_cases.get(i));
            else if (range == '>' && distance + 1 < d)
                temp.add(all_cases.get(i));
            else if (range == '<' && distance >= d)
                temp.add(all_cases.get(i));
        }

        all_cases = temp;
    }
}