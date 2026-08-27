import java.util.*;

class Solution {
    public int maxArea(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[] height = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {

            // Build heights using ORIGINAL column positions
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            // Sort a copy, not height itself
            int[] sorted = height.clone();
            Arrays.sort(sorted);

            // Largest heights get the largest widths
            for (int j = 0; j < m; j++) {
                int width = m - j;
                int area = sorted[j] * width;

                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}