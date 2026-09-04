import java.util.*;

class Solution {
    public int maxFruits(ArrayList<Integer> arr, int m) {
        int n = arr.size();

        if (m >= n) {
            int total = 0;

            for (int x : arr) {
                total += x;
            }

            return total;
        }

        int sum = 0;

        // First window
        for (int i = 0; i < m; i++) {
            sum += arr.get(i);
        }

        int max = sum;

        // Normal windows
        for (int i = m; i < n; i++) {
            sum += arr.get(i);
            sum -= arr.get(i - m);

            max = Math.max(max, sum);
        }

        // Wrap-around windows
        for (int i = 0; i < m - 1; i++) {
            sum += arr.get(i);
            sum -= arr.get(n - m + i);

            max = Math.max(max, sum);
        }

        return max;
    }
}