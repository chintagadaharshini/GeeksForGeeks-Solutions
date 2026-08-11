class Solution:
    def largestSquare(self, mat: list[list[int]], queries: list[list[int]], k: int) -> list[int]:
        n = len(mat)
        m = len(mat[0])

        # Build 2D prefix sum
        prefix = [[0] * (m + 1) for _ in range(n + 1)]

        for i in range(n):
            for j in range(m):
                prefix[i + 1][j + 1] = (
                    mat[i][j]
                    + prefix[i][j + 1]
                    + prefix[i + 1][j]
                    - prefix[i][j]
                )

        # Function to count 1s in a square/rectangle
        def get_sum(top, left, bottom, right):
            return (
                prefix[bottom + 1][right + 1]
                - prefix[top][right + 1]
                - prefix[bottom + 1][left]
                + prefix[top][left]
            )

        result = []

        for i, j in queries:

            # Maximum possible radius around (i, j)
            max_radius = min(
                i,
                j,
                n - 1 - i,
                m - 1 - j
            )

            low = 0
            high = max_radius
            best = -1

            # Binary search for largest valid square
            while low <= high:
                radius = (low + high) // 2

                top = i - radius
                bottom = i + radius
                left = j - radius
                right = j + radius

                ones = get_sum(top, left, bottom, right)

                if ones <= k:
                    best = radius
                    low = radius + 1
                else:
                    high = radius - 1

            if best == -1:
                result.append(-1)
            else:
                result.append(2 * best + 1)

        return result