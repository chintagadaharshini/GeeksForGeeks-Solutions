class Solution:
    def maxIndexDifference(self, s):
        # code here
        n = len(s)

        # best[c] = maximum reachable end index for character c
        best = [-1] * 26
        reach = [0] * n

        for i in range(n - 1, -1, -1):
            ch = ord(s[i]) - ord('a')

            if ch == 25:          # 'z'
                reach[i] = i
            elif best[ch + 1] != -1:
                reach[i] = best[ch + 1]
            else:
                reach[i] = i

            best[ch] = max(best[ch], reach[i])

        ans = -1
        found_a = False

        for i in range(n):
            if s[i] == 'a':
                found_a = True
                ans = max(ans, reach[i] - i)

        return ans if found_a else -1