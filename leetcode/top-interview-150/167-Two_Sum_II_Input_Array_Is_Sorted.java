// blog: https://velog.io/@eello/LeetCode-167.-Two-Sum-II-Input-Array-Is-Sorted
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (numbers[left] + numbers[right] != target) {
            if (numbers[left] + numbers[right] < target) {
                left++;
            } else right--;
        }

        return new int[] {left + 1, right + 1};
    }
}