// Given a binary tree

// struct TreeLinkNode {
//   TreeLinkNode *left;
//   TreeLinkNode *right;
//   TreeLinkNode *next;
// }
// Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

// Initially, all next pointers are set to NULL.

// Note:

// You may only use constant extra space.
// Recursive approach is fine, implicit stack space does not count as extra space for this problem.
// You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
// Example:

// Given the following perfect binary tree,

//      1
//    /  \
//   2    3
//  / \  / \
// 4  5  6  7
// After calling your function, the tree should look like:

//      1 -> NULL
//    /  \
//    2 -> 3 -> NULL
//   / \  / \
// 4->5->6->7 -> NULL

public class Solution {
     public void connect(TreeLinkNode root) {
         if (root == null) return;
         Queue<TreeLinkNode> queue = new LinkedList<>();
         queue.offer(root);
         while (!queue.isEmpty()) {
             List<TreeLinkNode> temp = new ArrayList<>();
             while (!queue.isEmpty()) temp.add(queue.poll());
             for (int i = 0; i < temp.size(); i++) {
                 TreeLinkNode node = temp.get(i);
                 if (i != temp.size()-1) node.next = temp.get(i+1);
                 else node.next = null;
                 if (node.left != null) queue.offer(node.left);
                 if (node.right != null) queue.offer(node.right);
             }   
         }
     }
 }
