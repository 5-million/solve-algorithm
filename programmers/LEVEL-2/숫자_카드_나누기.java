class Solution {

    public int solution(int[] arrayA, int[] arrayB) {
		int len = arrayA.length;

		int gcdA = len == 1 ? arrayA[0] : gcd(arrayA[0], arrayA[1]);
		int gcdB = len == 1 ? arrayB[0] : gcd(arrayB[0], arrayB[1]);
		for (int i = 2; i < len; i++) {
			gcdA = gcd(gcdA, arrayA[i]);
			gcdB = gcd(gcdB, arrayB[i]);
		}

		boolean fA = false, fB = false;
		for (int i = 0; i < len; i++) {
			if (arrayA[i] % gcdB == 0) {
				fB = true;
			}

			if (arrayB[i] % gcdA == 0) {
				fA = true;
			}

			if (fA && fB) {
				break;
			}
		}

		if (!fA && !fB) {
			return Math.max(gcdA, gcdB);
		} else if (!fA) {
			return gcdA;
		} else if (!fB) {
			return gcdB;
		} else return 0;
	}

	private int gcd(int a, int b) {
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}

		while (b != 0) {
			int temp = a;
			a = b;
			b = temp % b;
		}

		return a;
	}
}