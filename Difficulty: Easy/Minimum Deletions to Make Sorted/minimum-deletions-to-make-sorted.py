from bisect import bisect_left

class Solution:
    def minDeletions(self, arr):
        tails = []

        for num in arr:

            pos = bisect_left(tails, num)

            if pos == len(tails):
                tails.append(num)
            else:
                tails[pos] = num

        return len(arr) - len(tails)