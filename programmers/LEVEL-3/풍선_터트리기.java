class Solution {
    
    public int solution(int[] a) {
        int len = a.length;

        int[] leftMin = new int[len];
        int[] rightMin = new int[len];

        leftMin[0] = a[0];
        rightMin[len - 1] = a[len - 1];

        for (int i = 1; i < len; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }

        for (int i = len - 2; i > 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (a[i] > leftMin[i] && a[i] > rightMin[i]) {
                ans++;
            }
        }

        return len - ans;
    }
}