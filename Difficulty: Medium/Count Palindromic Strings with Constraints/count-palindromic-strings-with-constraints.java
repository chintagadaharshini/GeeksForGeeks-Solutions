class Solution {
    static final long MOD = 1000000007;

    public int palindromicStrings(int n, int k) {

        long ans = 0;

        // fact = number of ways to arrange m distinct characters
        long ways = 1;

        for (int m = 1; m <= k; m++) {

            ways = (ways * (k - m + 1)) % MOD;

            // Even length = 2 * m
            if (2 * m <= n) {
                ans = (ans + ways) % MOD;
            }

            // Odd length = 2 * m + 1
            if (2 * m + 1 <= n) {

                // Choose the middle character.
                // It must be different from the m paired characters.
                long oddWays = (ways * (k - m)) % MOD;

                ans = (ans + oddWays) % MOD;
            }
        }

        // Length 1 strings
        if (n >= 1) {
            ans = (ans + k) % MOD;
        }

        return (int) ans;
    }
}