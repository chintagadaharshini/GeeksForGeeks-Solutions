class Solution {
    public int minMoves(int[] arr) {
        int n = arr.length;

        int[] pos = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pos[arr[i]] = i;
        }

        int maxLen = 1;
        int len = 1;

        for (int value = 2; value <= n; value++) {
            if (pos[value] > pos[value - 1]) {
                len++;
            } else {
                len = 1;
            }

            maxLen = Math.max(maxLen, len);
        }

        return n - maxLen;
    }
}