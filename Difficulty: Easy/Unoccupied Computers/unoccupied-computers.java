class Solution {
    public int solve(int n, String s) {
        boolean[] using = new boolean[26];
        boolean[] rejected = new boolean[26];

        int available = n;
        int ans = 0;

        for (char ch : s.toCharArray()) {
            int idx = ch - 'A';

            // Arrival
            if (!using[idx] && !rejected[idx]) {

                if (available > 0) {
                    using[idx] = true;
                    available--;
                } else {
                    rejected[idx] = true;
                    ans++;
                }
            }

            // Departure
            else if (using[idx]) {
                using[idx] = false;
                available++;
            }

            // If rejected, simply ignore departure
            else {
                rejected[idx] = false;
            }
        }

        return ans;
    }
}