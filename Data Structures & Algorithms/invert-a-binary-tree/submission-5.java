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


public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root; //If the Tree is empty return root/null

        TreeNode temp = root.left; // Store the value for swap
        root.left = root.right; // Swap right into left
        root.right = temp; // left into right 

        invertTree(root.left); // Repeat 
        invertTree(root.right);

        return root;
    }
}