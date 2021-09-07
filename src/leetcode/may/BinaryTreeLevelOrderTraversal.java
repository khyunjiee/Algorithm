package leetcode.may;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
}

class SolutionBinaryTreeLevelOrderTraversal {
    List<List<Integer>> result;

    public List<List<Integer>> levelOrder(TreeNode root) {
         result = new ArrayList<>();
         dfs(root, 0);

         return result;
    }

    private void dfs(TreeNode node, int index) {
        if (node == null) return;

        if (result.size() <= index) result.add(new ArrayList<>());
        result.get(index).add(node.val);

        dfs(node.left, index + 1);
        dfs(node.right, index + 1);
    }
}