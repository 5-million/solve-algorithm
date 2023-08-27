// blog: https://velog.io/@eello/LeetCode-209.-Minimum-Size-Subarray-Sum
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
		int left = 0, right = 0;
		int sum = nums[0];
		int answer = Integer.MAX_VALUE;
		while(true) {
			if (sum >= target) {
				answer = Math.min(answer, right - left + 1);
				sum -= nums[left++];
			} else {
				if (right == nums.length - 1) {
					break;
				} else {
					sum += nums[++right];
				}
			}
		}

		return answer == Integer.MAX_VALUE ? 0 : answer;
	}
}