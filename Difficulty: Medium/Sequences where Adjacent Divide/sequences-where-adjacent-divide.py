class Solution:
    def count(self, n: int, m: int) -> int:
        MOD = 10**9 + 7

        # adj[x] stores all numbers y such that x divides y or y divides x
        adj = [[] for _ in range(m + 1)]

        for i in range(1, m + 1):
            # Multiples of i
            for j in range(i, m + 1, i):
                adj[i].append(j)
                if i != j:
                    adj[j].append(i)

        # Base case: Arrays of length 1
        dp = [1] * (m + 1)

        # Build arrays of length 2 to n
        for _ in range(2, n + 1):
            new = [0] * (m + 1)

            for x in range(1, m + 1):
                for y in adj[x]:
                    new[x] = (new[x] + dp[y]) % MOD

            dp = new

        return sum(dp[1:]) % MOD