import java.util.HashMap;
import java.util.Map;

/**
 * LEVEL-3
 * 2021 Dev-Matching: 웹 백앤드 개발자 > 다단계 칫솔 판매
 */
public class Solution {

    Map<String, Integer> enroll = new HashMap<>();

    private class Salesman {
        Salesman referral;
        int income = 0;

        public Salesman(String referral) {
            if (referral == null) this.referral = null;
            else if (referral.equals("-")) this.referral = center;
            else this.referral = salesmen[enroll.get(referral)];
        }

        private void addIncome(int revenue) {
            int commission = Math.floorDiv(revenue, 10);
            this.income += revenue - commission;
            if (this.referral != null && commission > 0)
                this.referral.addIncome(commission);
        }

        public void sale(int amount) {
            addIncome(amount * 100);
        }
    }

    private Salesman center = new Salesman(null);
    private Salesman[] salesmen;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        init(enroll, referral);

        for (int i = 0; i < seller.length; i++)
            salesmen[this.enroll.get(seller[i])].sale(amount[i]);

        for (int i = 0; i < answer.length; i++)
            answer[i] = salesmen[i].income;

        return answer;
    }

    private void init(String[] enroll, String[] referral) {
        salesmen = new Salesman[enroll.length];

        for (int i = 0; i < enroll.length; i++)
            this.enroll.put(enroll[i], i);

        for (int i = 0; i < referral.length; i++)
            this.salesmen[i] = new Salesman(referral[i]);
    }
}
