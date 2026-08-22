class Solution {

    // Find Lowest Common Ancestor
    Node findLCA(Node root, int p, int q) {
        if (root == null) {
            return null;
        }

        if (root.data == p || root.data == q) {
            return root;
        }

        Node left = findLCA(root.left, p, q);
        Node right = findLCA(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    // Count turns from root to target
    int countTurns(Node root, int target, char prevDirection) {
        if (root == null) {
            return -1;
        }

        if (root.data == target) {
            return 0;
        }

        // Go left
        int left = countTurns(root.left, target, 'L');

        if (left != -1) {
            if (prevDirection == 'R') {
                return left + 1;
            }
            return left;
        }

        // Go right
        int right = countTurns(root.right, target, 'R');

        if (right != -1) {
            if (prevDirection == 'L') {
                return right + 1;
            }
            return right;
        }

        return -1;
    }

    public int numberOfTurns(Node root, int p, int q) {

        Node lca = findLCA(root, p, q);

        if (lca == null) {
            return -1;
        }

        // p is the LCA
        if (lca.data == p) {
            int turns = countTurns(lca, q, 'N');
            return turns == 0 ? -1 : turns;
        }

        // q is the LCA
        if (lca.data == q) {
            int turns = countTurns(lca, p, 'N');
            return turns == 0 ? -1 : turns;
        }

        int turnsFromP = countTurns(lca, p, 'N');
        int turnsFromQ = countTurns(lca, q, 'N');

        if (turnsFromP == -1 || turnsFromQ == -1) {
            return -1;
        }

        // One turn occurs at LCA because the path
        // enters through one side and leaves through the other.
        int answer = turnsFromP + turnsFromQ + 1;

        return answer == 0 ? -1 : answer;
    }
}