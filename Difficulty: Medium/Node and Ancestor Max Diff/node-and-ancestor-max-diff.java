class Solution {

    int maxDiff(Node root) {
        if (root == null) {
            return 0;
        }

        return solve(root)[1];
    }

    // returns {minimum value in subtree, maximum ancestor-descendant difference}
    int[] solve(Node node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }

        int[] left = solve(node.left);
        int[] right = solve(node.right);

        int minDescendant = Math.min(left[0], right[0]);

        int best = Math.max(left[1], right[1]);

        // Only calculate difference if a descendant actually exists
        if (minDescendant != Integer.MAX_VALUE) {
            best = Math.max(best, node.data - minDescendant);
        }

        int minValue = Math.min(node.data,
                Math.min(left[0], right[0]));

        return new int[]{minValue, best};
    }
}