class Solution:
    def levelSort(self, arr):
        # code here

        ans = []
        i = 0
        level_size = 1
        n = len(arr)

        while i < n:
            level = arr[i:min(i + level_size, n)]
            level.sort()
            ans.append(level)

            i += level_size
            level_size *= 2

        return ans