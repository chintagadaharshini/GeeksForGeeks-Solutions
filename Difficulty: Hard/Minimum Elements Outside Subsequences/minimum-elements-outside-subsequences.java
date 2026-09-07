import java.util.*;

class Solution {
    public int minCount(int[] arr) {
        int n = arr.length;

        int[][][] dp = new int[n + 1][n + 1][n + 1];

        // dp[i][j][k] = maximum elements selected
        // after processing first i elements,
        // where j is last index of increasing sequence
        // and k is last index of decreasing sequence.

        for (int i = n - 1; i >= 0; i--) {
            for (int j = -1; j < n; j++) {
                for (int k = -1; k < n; k++) {

                    int jj = j + 1;
                    int kk = k + 1;

                    // Skip current element
                    dp[i][jj][kk] = dp[i + 1][jj][kk];

                    // Put in increasing subsequence
                    if (j == -1 || arr[i] > arr[j]) {
                        dp[i][jj][kk] = Math.max(
                            dp[i][jj][kk],
                            1 + dp[i + 1][i + 1][kk]
                        );
                    }

                    // Put in decreasing subsequence
                    if (k == -1 || arr[i] < arr[k]) {
                        dp[i][jj][kk] = Math.max(
                            dp[i][jj][kk],
                            1 + dp[i + 1][jj][i + 1]
                        );
                    }
                }
            }
        }

        return n - dp[0][0][0];
    }
}