class Solution {
    public int solution(int[] topping) {
		int[] cheolsu = new int[10001];
		cheolsu[topping[0]]++;
		int cheolsuTopping = 1;

		int[] brother = new int[10001];
		int brotherTopping = 0;
		for (int i = 1; i < topping.length; i++) {
			int t = topping[i];
			if (brother[t] == 0) {
				brotherTopping++;
			}

			brother[t]++;
		}

		int answer = 0;
		for (int i = 1; i < topping.length - 1; i++) {
			int t = topping[i];
			if (cheolsu[t] == 0) {
				cheolsuTopping++;
			}
			cheolsu[t]++;
			
			brother[t]--;
			if (brother[t] == 0) {
				brotherTopping--;
			}

			if (cheolsuTopping == brotherTopping) {
				answer++;
			}
		}
		
		return answer;
	}
}