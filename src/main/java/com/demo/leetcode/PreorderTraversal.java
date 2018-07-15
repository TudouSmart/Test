package com.demo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/description/
 */
public class PreorderTraversal {

    /**
     * 递归方式
     */

    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        help(root, list);
        return list;
    }

    private void help(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            help(root.left, list);
            help(root.right, list);
        }
    }

    /**
     * 非递归方式
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (! stack.isEmpty()) {
            TreeNode node = null;
            while (node == null && ! stack.isEmpty()) {
                node = stack.pop();
            }

            if (node == null) {
                break;
            }

            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
