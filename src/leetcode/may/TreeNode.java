package leetcode.may;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class SolutionTreeNode {
    int answer = 0;

    public int minCameraCover(TreeNode root) {
        return (dfs(root) == Monitoring.REQUIRED) ? answer + 1: answer;
    }

    private Monitoring dfs(TreeNode node) {
        if (node == null) return Monitoring.NONE;
        Monitoring leftType = dfs(node.left);
        Monitoring rightType = dfs(node.right);

        if (leftType == Monitoring.NONE && rightType == Monitoring.NONE) return Monitoring.REQUIRED;
        else if ((leftType == Monitoring.MONITORING && rightType != Monitoring.REQUIRED) || (rightType == Monitoring.MONITORING && leftType != Monitoring.REQUIRED))
            return Monitoring.NONE;
        answer++;
        return Monitoring.MONITORING;
    }
}

enum Monitoring {
    NONE, MONITORING, REQUIRED
}
