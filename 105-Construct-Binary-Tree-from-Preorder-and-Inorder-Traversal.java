// Given preorder and inorder traversal of a tree, construct the binary tree.

// Note:
// You may assume that duplicates do not exist in the tree.

// For example, given

// preorder = [3,9,20,15,7]
// inorder = [9,3,15,20,7]
// Return the following binary tree:

//     3
//    / \
//   9  20
//     /  \
//    15   7

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return helper(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }
    public TreeNode helper(int[] preorder, int[] inorder, int pre_start, int pre_end, int in_start, int in_end) {
        int val = preorder[pre_start];
        TreeNode root = new TreeNode(val);
        int index = find(inorder, val);
        int left = index - in_start; // number of left subtree
        int right = in_end - index; // number of right subtree
        if (left > 0) root.left = helper(preorder, inorder, pre_start + 1, pre_start + left, in_start, index - 1);
        if (right > 0) root.right = helper(preorder, inorder, pre_start + left + 1, pre_end, index + 1, in_end);
        return root;
    }
    public int find(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
