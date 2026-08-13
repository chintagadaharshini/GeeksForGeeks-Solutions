class Solution:
    def maxDistance(self, V, src, edges):
        # Adjacency list
        adj = [[] for _ in range(V)]
        indegree = [0] * V

        for u, v, w in edges:
            adj[u].append((v, w))
            indegree[v] += 1

        # Topological Sort
        queue = []

        for i in range(V):
            if indegree[i] == 0:
                queue.append(i)

        topo = []

        while queue:
            u = queue.pop(0)
            topo.append(u)

            for v, w in adj[u]:
                indegree[v] -= 1

                if indegree[v] == 0:
                    queue.append(v)

        # INT_MIN
        INT_MIN = -2147483648

        # Distance array
        dist = [INT_MIN] * V
        dist[src] = 0

        # Longest path in DAG
        for u in topo:

            # Skip unreachable vertices
            if dist[u] == INT_MIN:
                continue

            for v, w in adj[u]:
                dist[v] = max(dist[v], dist[u] + w)

        return dist
