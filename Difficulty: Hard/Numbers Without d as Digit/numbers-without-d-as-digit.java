class Solution {
    public int countWithout(int n, int d) {
        if (n == 0) {
            return 0;
        }

        char[] digits = String.valueOf(n).toCharArray();

        Integer[][][] dp = new Integer[digits.length][2][2];

        return solve(digits, 0, 1, 0, d, dp);
    }

    private int solve(char[] digits, int pos, int tight,
                      int started, int d, Integer[][][] dp) {

        // All digits processed
        if (pos == digits.length) {
            return started == 1 ? 1 : 0;
        }

        if (dp[pos][tight][started] != null) {
            return dp[pos][tight][started];
        }

        int limit;

        if (tight == 1) {
            limit = digits[pos] - '0';
        } else {
            limit = 9;
        }

        int count = 0;

        for (int digit = 0; digit <= limit; digit++) {

            int newStarted = started;

            // Number has started
            if (digit != 0 || started == 1) {
                newStarted = 1;
            }

            // If the actual number has started and digit == d,
            // this number is invalid.
            if (newStarted == 1 && digit == d) {
                continue;
            }

            int newTight = 0;

            if (tight == 1 && digit == digits[pos] - '0') {
                newTight = 1;
            }

            count += solve(
                digits,
                pos + 1,
                newTight,
                newStarted,
                d,
                dp
            );
        }

        return dp[pos][tight][started] = count;
    }
}