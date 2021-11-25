/**
 * LEVEL-2
 * 깊이/너비 우선 탐색 > 타겟 넘버
 */
public class Solution {

    int[] numbers;
    int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;

        return dfs(0, 0);
    }

    private int dfs(int index, int hap) {
        if (index == numbers.length) {
            if (hap == target) return 1;
            else return 0;
        } else {
            int ret = 0;
            ret += dfs(index + 1, hap + numbers[index]);
            ret += dfs(index + 1, hap - numbers[index]);

            return ret;
        }
    }
}
