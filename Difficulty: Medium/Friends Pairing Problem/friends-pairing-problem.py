class Solution:
    def countFriendsPairings(self, n):
        MOD = 10**9 + 7

        if n <= 2:
            return n

        dp = [0] * (n + 1)

        dp[1] = 1
        dp[2] = 2

        for i in range(3, n + 1):
            dp[i] = (dp[i - 1] + (i - 1) * dp[i - 2]) % MOD

        return dp[n]
        