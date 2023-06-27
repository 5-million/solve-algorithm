import java.util.*;

class Solution {
    
    private static final String IN = "IN";
	private Map<Integer, Integer> in = new HashMap<>();
	private TreeMap<Integer, Integer> parkingTime = new TreeMap<>(Comparator.naturalOrder());

	public int[] solution(int[] fees, String[] records) {
		for (String record : records) {
			StringTokenizer st = new StringTokenizer(record);
			int time = parseTime(st.nextToken());
			int carNumber = Integer.parseInt(st.nextToken());
			String type = st.nextToken();

			if (IN.equals(type)) {
				in.put(carNumber, time);
			} else {
				parkingTime.put(
					carNumber,
					parkingTime.getOrDefault(carNumber, 0) + time - in.get(carNumber)
				);

				in.remove(carNumber);
			}
		}

		int outTime = parseTime("23:59");
		for (Map.Entry<Integer, Integer> entry : in.entrySet()) {
			int carNumber = entry.getKey();
			parkingTime.put(
				carNumber,
				parkingTime.getOrDefault(carNumber, 0) + outTime - entry.getValue()
			);
		}

		int[] answer = new int[parkingTime.size()];
		int idx = 0;
		for (Integer pt : parkingTime.values()) {
			answer[idx++] = calculateFee(fees, pt);
		}
		return answer;
	}

	private int parseTime(String time) {
		String[] split = time.split(":");
		return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
	}

	private int calculateFee(int[] fees, int parkingTime) {
		int fee = fees[1];

		if (parkingTime <= fees[0]) {
			return fee;
		}

		int overtime = parkingTime - fees[0];
		fee += overtime / fees[2] * fees[3];
		return overtime % fees[2] == 0 ? fee : fee + fees[3];
	}
}