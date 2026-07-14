class Solution:
    def find(self, arr):
        x = 0

        for num in arr[::-1]:
            x = (x + num + 1) // 2

        return x