import java.util.*;

class Solution {
    public int solution(int n, int k) {
        String number = Integer.toString(n, k);
        
        int answer = 0;
        for (String num : number.split("0")) {
            if (!"".equals(num) && isPrimeNumber(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isPrimeNumber(String n) {
        long number = Long.parseLong(n);
        
        if (number <= 1) {
            return false;
        }
        
        long sqrt = (long) Math.sqrt(number);
        for (long i = 2; i <= sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}