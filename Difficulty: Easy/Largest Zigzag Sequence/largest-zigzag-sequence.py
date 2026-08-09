class Solution:
    def zigzagSequence(self, mat):
        n = len(mat)

        # dp[j] = maximum sum ending at column j
        dp = mat[0][:]

        for i in range(1, n):
            new = [0] * n

            for j in range(n):
                best = 0

                for k in range(n):
                    if k != j:
                        best = max(best, dp[k])

                new[j] = mat[i][j] + best

            dp = new

        return max(dp)
        