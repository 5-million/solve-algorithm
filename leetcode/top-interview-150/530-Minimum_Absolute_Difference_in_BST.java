class Solution {

    private int answer = Integer.MAX_VALUE;
    private TreeNode prev;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return answer;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);

        if (prev != null) {
            answer = Math.min(answer, node.val - prev.val);
        }
        prev = node;

        inorder(node.right);
    }
}