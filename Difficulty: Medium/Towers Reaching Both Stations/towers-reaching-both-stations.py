class Solution:
    def countCoordinates(self, mat):
        n = len(mat)
        m = len(mat[0])

        p = [[False] * m for _ in range(n)]
        q = [[False] * m for _ in range(n)]

        def dfs(i, j, vis):
            vis[i][j] = True

            for dx, dy in [(-1,0), (1,0), (0,-1), (0,1)]:
                ni = i + dx
                nj = j + dy

                if (0 <= ni < n and 0 <= nj < m and
                    not vis[ni][nj] and
                    mat[ni][nj] >= mat[i][j]):
                    dfs(ni, nj, vis)

        # Station P (Top Row)
        for j in range(m):
            dfs(0, j, p)

        # Station P (Left Column)
        for i in range(n):
            dfs(i, 0, p)

        # Station Q (Bottom Row)
        for j in range(m):
            dfs(n - 1, j, q)

        # Station Q (Right Column)
        for i in range(n):
            dfs(i, m - 1, q)

        ans = 0

        for i in range(n):
            for j in range(m):
                if p[i][j] and q[i][j]:
                    ans += 1

        return ans