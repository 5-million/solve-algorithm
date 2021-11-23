/**
 * LEVEL-2
 * Summer/Winter Coding(2019) > 멀쩡한 사각형
 */
public class solution {

    public long solution(int w, int h) {
        long gcd = gcd(w, h);
        return (long) w * h - w - h + gcd;
    }

    private long gcd(int a, int b) {
        int bigger = Math.max(a, b);
        int smaller = Math.min(a, b);

        if (bigger % smaller == 0) return smaller;
        else return gcd(smaller, bigger % smaller);
    }
}