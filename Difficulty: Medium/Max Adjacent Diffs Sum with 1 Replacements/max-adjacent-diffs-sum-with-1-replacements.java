class Solution {
    public long maxDiffSum(int[] arr) {

        long keep = 0;
        long change = 0;

        for (int i = 1; i < arr.length; i++) {

            long newKeep = Math.max(
                keep + Math.abs(arr[i] - arr[i - 1]),
                change + Math.abs(arr[i] - 1)
            );

            long newChange = Math.max(
                keep + Math.abs(arr[i - 1] - 1),
                change
            );

            keep = newKeep;
            change = newChange;
        }

        return Math.max(keep, change);
    }
}