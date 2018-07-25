package com.demo.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode-cn.com/problems/maximum-width-of-binary-tree/description/
 */
public class WidthOfBinaryTree {

    public static int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int max = 0;
        root.val = 1;
        queue.add(root);
        while (! queue.isEmpty()) {
            int size = queue.size();
            int left = 0;
            int right = 0;
            for (int i = 0; i < size; i ++) {
                TreeNode node = queue.remove();

                if (i == 0) {
                    left = node.val;
                }

                if (i == size - 1) {
                    right = node.val;
                }

                if (node.left != null) {
                    node.left.val = node.val * 2;
                    queue.add(node.left);
                }

                if (node.right != null) {
                    node.right.val = node.val * 2 + 1;
                    queue.add(node.right);
                }

            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        System.out.println(widthOfBinaryTree(node));
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
