// Given a non-empty binary tree, find the maximum path sum.

// For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
// The path must contain at least one node and does not need to go through the root.

// Input: [1,2,3]

//       1
//      / \
//     2   3

// Output: 6

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int res;
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }
    public int helper(TreeNode root) {
        if (root == null) return 0;
        else {
            // drop the leaf that is negative
            
            int left = Math.max(helper(root.left), 0); 
            int right = Math.max(helper(root.right), 0);
            
            //res must be a global varible, otherwise it cannot be updated
            
            res = Math.max(res, left + right + root.val); 
            return Math.max(left, right) + root.val;
        }
    }
}
