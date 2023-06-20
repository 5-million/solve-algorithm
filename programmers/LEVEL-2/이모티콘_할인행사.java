class Solution {
    
    private int[][] users;
	private int[] emoticons;
	private int[] answer = new int[2];
	private int[] discountRate = {10, 20, 30, 40};

	public int[] solution(int[][] users, int[] emoticons) {
		this.users = users;
		this.emoticons = emoticons;

		dfs(new int[emoticons.length], 0);

		return answer;
	}

	private void dfs(int[] dr, int idx) {
		if (idx == dr.length) {
			int subscriber = 0; // 이모티콘 플러스 가입자 수
			int totalPrice = 0;
			for (int[] user : users) {
				int price = 0;
				for (int i = 0; i < dr.length; i++) {
					if (dr[i] >= user[0]) {
						price += getDiscountedPrice(emoticons[i], dr[i]);
					}
				}

				if (price >= user[1]) {
					subscriber++;
				} else {
					totalPrice += price;
				}
			}

			if (answer[0] < subscriber) {
				answer[0] = subscriber;
				answer[1] = totalPrice;
			} else if (answer[0] == subscriber && answer[1] < totalPrice) {
				answer[1] = totalPrice;
			}
		} else {
			for (int d : discountRate) {
				dr[idx] = d;
				dfs(dr, idx + 1);
			}
		}
	}

	private int getDiscountedPrice(int price, int discountRage) {
		return (int)(price * ((100 - discountRage) * 0.01));
	}
}