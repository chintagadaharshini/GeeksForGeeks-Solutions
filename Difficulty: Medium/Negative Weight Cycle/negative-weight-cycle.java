class Solution {
    public boolean isNegativeWeightCycle(int V, int[][] edges) {
        long[] dist = new long[V];

        // Treat every vertex as initially reachable.
        // This allows detection in disconnected components.
        for (int i = 0; i < V; i++) {
            dist[i] = 0;
        }

        // Relax all edges V times.
        for (int i = 0; i < V; i++) {
            boolean updated = false;

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    updated = true;

                    // If relaxation happens on the V-th iteration,
                    // a negative weight cycle exists.
                    if (i == V - 1) {
                        return true;
                    }
                }
            }

            // No changes means no negative cycle.
            if (!updated) {
                return false;
            }
        }

        return false;
    }
}