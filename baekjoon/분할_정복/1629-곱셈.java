import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S1
 * 1629 - 곱셈
 * https://www.acmicpc.net/problem/1629
 */
public class Main {
	
	private static int mod;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		mod = Integer.parseInt(st.nextToken());
		
		System.out.println(pow(a, b));
	}
	
	// return a^b
	private static long pow(int a, int b) {
		if (b == 1) return a % mod;
		
		int half = b / 2;
		long ret = 1;
		long result = pow(a, half) % mod;
		ret = (result * result) % mod;
		
		if (b % 2 == 1) {
			ret *= a;
			ret %= mod;
		}
		
		return ret;
	}

}
