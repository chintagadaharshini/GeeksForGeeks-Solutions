class Solution:
    def findWays(self, grid):
        n = len(grid)
        MOD = 10**9 + 7

        paths = [[0] * n for _ in range(n)]
        adventure = [[0] * n for _ in range(n)]
        paths[0][0] = 1
        adventure[0][0] = grid[0][0]

        for i in range(n):
            for j in range(n):

                if i == 0 and j == 0:
                    continue
                if i > 0 and grid[i - 1][j] in (2, 3):
                    paths[i][j] += paths[i - 1][j]
                    adventure[i][j] = max(
                        adventure[i][j],
                        adventure[i - 1][j]
                    )
                if j > 0 and grid[i][j - 1] in (1, 3):
                    paths[i][j] += paths[i][j - 1]
                    adventure[i][j] = max(
                        adventure[i][j],
                        adventure[i][j - 1]
                    )

                paths[i][j] %= MOD
                if paths[i][j] > 0:
                    adventure[i][j] += grid[i][j]

        return [paths[n - 1][n - 1], adventure[n - 1][n - 1]]