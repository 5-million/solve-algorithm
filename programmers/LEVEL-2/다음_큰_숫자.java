/**
 * LEVEL-2
 * 연습문제 > 다음 큰 숫자
 */
public class Solution {

    public int solution(int n) {
        int nOneCount = oneCount(Integer.toBinaryString(n));
        n++;
        while (nOneCount != oneCount(Integer.toBinaryString(n)))
            n++;

        return n;
    }

    private int oneCount(String binary) {
        int count = 0;
        for (char c : binary.toCharArray())
            if (c == 49)
                count++;

        return count;
    }
}