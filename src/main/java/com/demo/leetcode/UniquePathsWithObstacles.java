package com.demo.leetcode;

/**
 * https://leetcode-cn.com/problems/unique-paths-ii/description/
 */
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] arr = new int[obstacleGrid.length][obstacleGrid[0].length];

        arr[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 0; i < obstacleGrid.length; i ++) {
            for (int j = 0; j < obstacleGrid[i].length; j ++) {
                if (obstacleGrid[i][j] == 1) {
                    arr[i][j] = 0;
                    continue;
                }

                if (i > 0 && j > 0) {
                    arr[i][j] = arr[i-1][j] + arr[i][j-1];
                } else if (i > 0) {
                    arr[i][j] = obstacleGrid[i-1][j] == 1 ? 0 : arr[i-1][j];
                } else if (j > 0) {
                    arr[i][j] = obstacleGrid[i][j-1] == 1 ? 0 : arr[i][j-1];
                }
            }
        }

        return arr[arr.length - 1][obstacleGrid[0].length - 1];
    }

    public static void main(String[] args) {
        System.out.println((new UniquePathsWithObstacles()).uniquePathsWithObstacles(null));
    }
}
