import java.util.*;

class Solution {
    public int solution(int n, int k) {
        String number = Integer.toString(n, k);
        List<String> numbers = new ArrayList<>();
        String temp = "";
        for (char ch : number.toCharArray()) {
            if (ch == '0') {
                numbers.add(temp);
                numbers.add(String.valueOf(ch));
                temp = "";
            } else {
                temp += ch;
            }
        }
        
        int size = numbers.size();
        int answer = 0;
        if (size == 1) {
            if (isPrimeNumber(numbers.get(0))) {
                answer++;
            }
        } else {
            if (isPrimeNumber(numbers.get(0)) && "0".equals(numbers.get(1))) {
                answer++;
            }
            
            if (isPrimeNumber(numbers.get(size - 1)) && "0".equals(numbers.get(size - 2))) {
                answer++;
            }
            
            for (int i = 1; i < size - 1; i++) {
                if (!isPrimeNumber(numbers.get(i))) {
                    continue;
                }
                
                if (!"0".equals(numbers.get(i - 1)) || !"0".equals(numbers.get(i + 1))) {
                    continue;
                }
                
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isPrimeNumber(String n) {
        int number = Integer.parseInt(n);
        int sqrt = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}