import heapq

class Solution:
    def maxAmount(self, arr, k):
        MOD = 10**9 + 7

        heap = []

        for x in arr:
            heapq.heappush(heap, -x)

        ans = 0

        while k > 0 and heap:
            curr = -heapq.heappop(heap)

            ans = (ans + curr) % MOD

            curr -= 1

            if curr > 0:
                heapq.heappush(heap, -curr)

            k -= 1

        return ans