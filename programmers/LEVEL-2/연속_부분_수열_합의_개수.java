import java.util.*;

class Solution {
    
    public int solution(int[] elements) {
		int n = elements.length;
		Set<Integer> set = new HashSet<>();
		int[] sum = new int[n];

		sum[0] = elements[0];
		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + elements[i];
		}

		for (int len = 1; len < n; len++) {
			for (int left = 0; left < n; left++) {
				int right = (left + len - 1) % n;
				if (left <= right) {
					if (left == 0) {
						set.add(sum[right]);
					} else {
						set.add(sum[right] - sum[left - 1]);
					}
				} else if (right < left) {
					set.add(sum[right] + sum[n - 1] - sum[left - 1]);
				}
			}
		}

		set.add(sum[n - 1]);
		return set.size();
	}
}