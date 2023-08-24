// blog: https://velog.io/@eello/LeetCode-80.-Remove-Duplicates-from-Sorted-Array-II
class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[k - 2] != nums[i]) {
                nums[k++] = nums[i];
            }
        }

        return k;
    }
}