class Solution {
    public int minThrows(int n, int[] lad, int[] sn) {

        int total = n * n;

        // move[i] = destination if there is a snake/ladder at i
        int[] move = new int[total + 1];

        for (int i = 1; i <= total; i++) {
            move[i] = i;
        }

        // Ladders
        for (int i = 0; i < lad.length; i += 2) {
            move[lad[i]] = lad[i + 1];
        }

        // Snakes
        for (int i = 0; i < sn.length; i += 2) {
            move[sn[i]] = sn[i + 1];
        }

        // BFS
        boolean[] visited = new boolean[total + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        int throwsCount = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // All positions in this level require the same
            // number of dice throws.
            while (size-- > 0) {

                int current = queue.poll();

                if (current == total) {
                    return throwsCount;
                }

                // Try dice values 1 to 6
                for (int dice = 1; dice <= 6; dice++) {

                    int next = current + dice;

                    if (next > total) {
                        continue;
                    }

                    // Take snake or ladder immediately
                    next = move[next];

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }

            throwsCount++;
        }

        return -1;
    }
}