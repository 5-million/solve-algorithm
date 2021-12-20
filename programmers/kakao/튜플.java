import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2019 카카오 개발자 겨울 인턴십 > 튜플
 */
public class Solution {

    List<Integer> tuple = new ArrayList<>();

    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        List<String> expr = Arrays.stream(s.split("\\},\\{"))
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        for (String set : expr) {
            for (String elem : set.split(",")) {
                int element = Integer.parseInt(elem);
                if (!tuple.contains(element))
                    tuple.add(element);
            }
        }

        return tuple.stream().mapToInt(Integer::intValue).toArray();
    }
}