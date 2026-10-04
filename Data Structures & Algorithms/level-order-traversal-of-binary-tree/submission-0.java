/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        help(0,root);
        return ans;
    }
    public void help(int level, TreeNode node){
        if(node == null) return;

        if(ans.size() == level){ans.add(new ArrayList<Integer>());}
        ans.get(level).add(Integer.valueOf(node.val));

        help(level + 1,node.left);
        help(level + 1, node.right);
    }
}
