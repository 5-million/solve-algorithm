class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // nums1의 인덱스
        int j = n - 1; // nums2의 인덱스
        int k = m + n - 1; // 결과를 저장하는 nums1의 인덱스

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}