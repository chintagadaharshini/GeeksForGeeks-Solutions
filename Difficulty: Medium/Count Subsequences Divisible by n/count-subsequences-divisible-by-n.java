class Solution {
    public int countSubsequences(String s, int n) {

        long MOD = 1000000007;
        long[] dp = new long[n];

        for (char ch : s.toCharArray()) {

            int digit = ch - '0';

            // Copy old dp because we need the values
            // BEFORE processing this digit
            long[] newDp = dp.clone();

            // Start a new subsequence containing only this digit
            newDp[digit % n]++;

            // Add this digit to every existing subsequence
            for (int r = 0; r < n; r++) {

                int newRemainder = (r * 10 + digit) % n;

                newDp[newRemainder] =
                    (newDp[newRemainder] + dp[r]) % MOD;
            }

            dp = newDp;
        }

        return (int) dp[0];
    }
}