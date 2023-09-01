// blog: https://velog.io/@eello/LeetCode-219.-Contains-Duplicate-II
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> indexs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer recentIndex = indexs.put(nums[i], i);
            if (recentIndex != null && i - recentIndex <= k) {
                return true;
            }
        }
        return false;
    }
}