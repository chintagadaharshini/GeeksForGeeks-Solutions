import java.util.*;

class Solution {
    public int longestSubseq(int[] arr) {

        HashMap<Integer, Integer> dp = new HashMap<>();
        int ans = 1;

        for (int x : arr) {

            int best = 1;

            if (dp.containsKey(x - 1)) {
                best = Math.max(best, dp.get(x - 1) + 1);
            }

            if (dp.containsKey(x + 1)) {
                best = Math.max(best, dp.get(x + 1) + 1);
            }

            dp.put(x, Math.max(dp.getOrDefault(x, 0), best));

            ans = Math.max(ans, dp.get(x));
        }

        return ans;
    }
}