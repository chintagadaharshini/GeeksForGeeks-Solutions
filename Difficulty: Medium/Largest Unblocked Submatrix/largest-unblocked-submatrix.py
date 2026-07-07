class Solution:
    def largestArea(self, n, m, arr):
        rows = [0, n + 1]
        cols = [0, m + 1]

        for r, c in arr:
            rows.append(r)
            cols.append(c)

        rows.sort()
        cols.sort()

        max_row = 0
        for i in range(1, len(rows)):
            max_row = max(max_row, rows[i] - rows[i - 1] - 1)

        max_col = 0
        for i in range(1, len(cols)):
            max_col = max(max_col, cols[i] - cols[i - 1] - 1)

        return max_row * max_col