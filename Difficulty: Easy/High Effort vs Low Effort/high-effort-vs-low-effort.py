class Solution:
    def maxTask(self, h, l):
        n = len(h)

        dp = [0] * (n + 1)

        for i in range(1, n + 1):
            # Option 1: Do nothing today
            dp[i] = dp[i - 1]

            # Option 2: Low-effort task today
            dp[i] = max(dp[i], dp[i - 1] + l[i - 1])

            # Option 3: High-effort task today
            # Yesterday must have had no task
            if i == 1:
                dp[i] = max(dp[i], h[i - 1])
            else:
                dp[i] = max(dp[i], dp[i - 2] + h[i - 1])

        return dp[n]
        