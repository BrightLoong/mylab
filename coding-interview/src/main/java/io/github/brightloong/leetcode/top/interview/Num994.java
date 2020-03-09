package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/4 09:37
 * @description
 */
public class Num994 {
    public static int orangesRotting(int[][] grid) {
        int result = 0;
        while (true) {
            boolean jump = true;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 2) {
                        //上
                        if (i > 0 && grid[i-1][j] == 1) {
                            grid[i-1][j] =3;
                            jump =  false;
                        }
                        //下
                        if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                            grid[i+1][j] =3;
                            jump =  false;
                        }
                        //左
                        if (j > 0 && grid[i][j-1] == 1) {
                            grid[i][j-1] =3;
                            jump =  false;
                        }
                        //右
                        if (j < grid[i].length - 1&& grid[i][j+1] == 1) {
                            grid[i][j+1] =3;
                            jump =  false;
                        }
                    }
                }
            }


            if (jump) {
                break;
            } else {
                result++;
            }
            // 把所有3改成2
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 3) {
                        grid[i][j] = 2;
                    }
                }
            }
        }
        // 校验是否还有新鲜橘子
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{{2},{1}}));
    }
}
