import java.util.*;

class Solution {
    public int numberOfCells(int r, int c, int u, int d, char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        if (mat[r][c] == '#')
            return 0;

        // dist[i][j] = minimum number of UP moves
        // required to reach (i, j)
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Deque<int[]> dq = new ArrayDeque<>();

        dist[r][c] = 0;
        dq.offerFirst(new int[]{r, c});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();

            int x = curr[0];
            int y = curr[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dr[k];
                int ny = y + dc[k];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (mat[nx][ny] == '#')
                    continue;

                // Moving UP costs 1.
                // Down, left and right cost 0.
                int cost = (nx < x) ? 1 : 0;

                int newUp = dist[x][y] + cost;

                if (newUp < dist[nx][ny]) {
                    dist[nx][ny] = newUp;

                    if (cost == 0)
                        dq.offerFirst(new int[]{nx, ny});
                    else
                        dq.offerLast(new int[]{nx, ny});
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (dist[i][j] == Integer.MAX_VALUE)
                    continue;

                int upMoves = dist[i][j];

                // From:
                // row - startingRow = down - up
                //
                // therefore:
                // down = up + row - startingRow
                int downMoves = upMoves + (i - r);

                if (upMoves <= u && downMoves <= d) {
                    ans++;
                }
            }
        }

        return ans;
    }
}