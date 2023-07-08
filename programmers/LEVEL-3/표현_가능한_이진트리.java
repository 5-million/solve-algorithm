class Solution {

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int i = 0;
        for (long number : numbers) {
            String binary = Long.toBinaryString(number);

            int h = 0;
            while (binary.length() > totalNodeCount(h)) {
                h++;
            }

            int nodeCount = totalNodeCount(h);
            String tree = "0".repeat((nodeCount - binary.length())) + binary;

            answer[i++] = isValid(tree) ? 1 : 0;
        }

        return answer;
    }

    private int totalNodeCount(int h) {
        return (int) Math.pow(2, h) - 1;
    }

    private boolean isValid(String tree) {
        if (tree.length() == 1) return true;

        int rootIdx = tree.length() / 2;
        char root = tree.charAt(rootIdx);

        if (root == '1') {
            return isValid(tree.substring(0, rootIdx)) && isValid(tree.substring(rootIdx + 1));
        } else {
            for (int i = 0; i < tree.length(); i++) {
                if (i == rootIdx) continue;

                if (tree.charAt(i) == '1') {
                    return false;
                }
            }

            return true;
        }
    }
}
