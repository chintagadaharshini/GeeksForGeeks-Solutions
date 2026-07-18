class Solution {
    static final int MOD = 1_000_000_007;

    public int findWays(int[][] matrix, int k) {
        int R = matrix.length;
        int C = (R > 0) ? matrix[0].length : 0;
        if (R == 0 || C == 0 || k <= 0) return 0;

        // Prefix sums for O(1) rectangle sum queries
        long[][] pre = new long[R + 1][C + 1];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                pre[i + 1][j + 1] = pre[i][j + 1] + pre[i + 1][j] - pre[i][j] + matrix[i][j];
            }
        }

        long totalOnes = pre[R][C];
        if (totalOnes < k) return 0;

        // rowHasOneFrom[r][left] = row r has a 1 somewhere in columns [left, C-1]
        boolean[][] rowHasOneFrom = new boolean[R][C + 1];
        for (int r = 0; r < R; r++) {
            rowHasOneFrom[r][C] = false;
            for (int left = C - 1; left >= 0; left--) {
                rowHasOneFrom[r][left] = (matrix[r][left] == 1) || rowHasOneFrom[r][left + 1];
            }
        }

        // firstRowWithOne[r][left] = smallest r' >= r with rowHasOneFrom[r'][left] true, else R (sentinel)
        int[][] firstRowWithOne = new int[R + 1][C];
        for (int left = 0; left < C; left++) firstRowWithOne[R][left] = R;
        for (int r = R - 1; r >= 0; r--) {
            for (int left = 0; left < C; left++) {
                firstRowWithOne[r][left] = rowHasOneFrom[r][left] ? r : firstRowWithOne[r + 1][left];
            }
        }

        // colHasOneFrom[top][c] = column c has a 1 somewhere in rows [top, R-1]
        boolean[][] colHasOneFrom = new boolean[R + 1][C];
        for (int top = R - 1; top >= 0; top--) {
            for (int c = 0; c < C; c++) {
                colHasOneFrom[top][c] = (matrix[top][c] == 1) || colHasOneFrom[top + 1][c];
            }
        }

        // firstColWithOne[top][c] = smallest c' >= c with colHasOneFrom[top][c'] true, else C (sentinel)
        int[][] firstColWithOne = new int[R][C + 1];
        for (int top = 0; top < R; top++) {
            firstColWithOne[top][C] = C;
            for (int c = C - 1; c >= 0; c--) {
                firstColWithOne[top][c] = colHasOneFrom[top][c] ? c : firstColWithOne[top][c + 1];
            }
        }

        // Layer for pieces = 1
        long[][] prev = new long[R][C];
        for (int top = 0; top < R; top++) {
            for (int left = 0; left < C; left++) {
                prev[top][left] = (rectSum(pre, top, R - 1, left, C - 1) > 0) ? 1 : 0;
            }
        }

        if (k == 1) return (int) (prev[0][0] % MOD);

        long[][] suffixColSum = new long[R + 1][C]; // sum_{rr=r}^{R-1} prev[rr][left]
        long[][] suffixRowSum = new long[R][C + 1]; // sum_{cc=c}^{C-1} prev[top][cc]

        for (int pieces = 2; pieces <= k; pieces++) {
            for (int left = 0; left < C; left++) suffixColSum[R][left] = 0;
            for (int r = R - 1; r >= 0; r--) {
                for (int left = 0; left < C; left++) {
                    suffixColSum[r][left] = (suffixColSum[r + 1][left] + prev[r][left]) % MOD;
                }
            }
            for (int top = 0; top < R; top++) {
                suffixRowSum[top][C] = 0;
                for (int c = C - 1; c >= 0; c--) {
                    suffixRowSum[top][c] = (suffixRowSum[top][c + 1] + prev[top][c]) % MOD;
                }
            }

            long[][] curr = new long[R][C];
            for (int top = 0; top < R; top++) {
                for (int left = 0; left < C; left++) {
                    long total = rectSum(pre, top, R - 1, left, C - 1);
                    if (total < pieces) continue;

                    long ways = 0;

                    int R0 = firstRowWithOne[top][left];
                    if (R0 <= R - 2) {
                        ways += suffixColSum[R0 + 1][left];
                    }

                    int C0 = firstColWithOne[top][left];
                    if (C0 <= C - 2) {
                        ways += suffixRowSum[top][C0 + 1];
                    }

                    curr[top][left] = ways % MOD;
                }
            }
            prev = curr;
        }

        return (int) (prev[0][0] % MOD);
    }

    private long rectSum(long[][] pre, int t, int b, int l, int r) {
        if (t > b || l > r) return 0;
        return pre[b + 1][r + 1] - pre[t][r + 1] - pre[b + 1][l] + pre[t][l];
    }
}