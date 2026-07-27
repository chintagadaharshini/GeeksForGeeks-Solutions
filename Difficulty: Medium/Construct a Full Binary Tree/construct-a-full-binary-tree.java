class Solution {
    int preIndex;

    public Node constructBinaryTree(int[] pre, int[] preMirror) {
        preIndex = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < preMirror.length; i++) {
            map.put(preMirror[i], i);
        }

        return build(pre, map, 0, preMirror.length - 1);
    }

    private Node build(int[] pre, HashMap<Integer, Integer> map, int l, int r) {
        if (preIndex >= pre.length || l > r)
            return null;

        Node root = new Node(pre[preIndex++]);

        if (l == r || preIndex >= pre.length)
            return root;

        int idx = map.get(pre[preIndex]);

        if (idx >= l && idx <= r) {
            root.left = build(pre, map, idx, r);
            root.right = build(pre, map, l + 1, idx - 1);
        }

        return root;
    }
}