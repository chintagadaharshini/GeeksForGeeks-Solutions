from math import gcd

class Solution:
    def minOperations(self, b):
        MOD = 10**9 + 7
        n = len(b)

        visited = [False] * n
        ans = 1

        for i in range(n):
            if not visited[i]:
                length = 0
                j = i

                while not visited[j]:
                    visited[j] = True
                    j = b[j] - 1      # Convert to 0-based index
                    length += 1

                ans = (ans * length) // gcd(ans, length)

        return ans % MOD