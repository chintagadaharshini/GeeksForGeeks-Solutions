class Solution:
    def countWays(self, n, sum):
        from functools import lru_cache

        @lru_cache(None)
        def dp(pos, rem):
            if pos == n:
                return 1 if rem == 0 else 0

            ans = 0

            if pos == 0:
                start = 1
            else:
                start = 0

            for digit in range(start, 10):
                if digit <= rem:
                    ans += dp(pos + 1, rem - digit)

            return ans

        ans = dp(0, sum)

        return ans if ans > 0 else -1