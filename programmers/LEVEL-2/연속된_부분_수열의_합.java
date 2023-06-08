class Solution {
    
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int left = n - 1, right = n - 1;
        int sum = sequence[n - 1];
        
        while (sum != k) {
            if (sum > k) {
                if (left == right) {
                    sum += sequence[--left];
                }
                
                sum -= sequence[right--];
            } else {
                sum += sequence[--left];
            }
        }
        
        while (left > 0 && sequence[left - 1] == sequence[right]) {
            left--;
            right--;
        }
        
        return new int[]{left, right};
    }
}