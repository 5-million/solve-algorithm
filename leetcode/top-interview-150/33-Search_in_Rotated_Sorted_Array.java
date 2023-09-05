// blog: https://velog.io/@eello/LeetCode-33.-Search-in-Rotated-Sorted-Array
class Solution {
    public int search(int[] nums, int target) {
		int pivot = getPivotIndex(nums);

		int targetIndex = findTargetIndex(nums, target, 0, pivot - 1);
		if (targetIndex != -1) {
			return targetIndex;
		}

		return findTargetIndex(nums, target, pivot, nums.length - 1);
	}

	public int getPivotIndex(int[] nums) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;

			if (nums[mid] > nums[right]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}

	public int findTargetIndex(int[] nums, int target, int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}