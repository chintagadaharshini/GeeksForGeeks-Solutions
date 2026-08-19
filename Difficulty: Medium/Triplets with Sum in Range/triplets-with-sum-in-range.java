import java.util.*;

class Solution {
    public long countTriplets(int[] arr, int l, int r) {
        Arrays.sort(arr);

        long ans = 0;

        // Count triplets with sum <= r
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum <= r) {
                    ans += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        // Count triplets with sum < l
        long smaller = 0;

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum < l) {
                    smaller += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return ans - smaller;
    }
}