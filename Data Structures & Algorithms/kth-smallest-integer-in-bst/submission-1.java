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
    ArrayList<Integer> lst = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        search(root);
    
        lst.sort(null);
        return lst.get(k-1);

    }
    public void search(TreeNode node){
        if(node == null) return;
        if(!(lst.contains(node.val))){
            lst.add(node.val);
        }
        search(node.left);
        search(node.right);
    }
}
