import java.util.*;

class Solution {
    static final int MOD = 1000000007;

    public int countSubsets(int[] arr) {

        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

        int[] freq = new int[31];
        for (int x : arr) freq[x]++;

        long[] dp = new long[1 << 10];
        dp[0] = 1;

        for (int num = 2; num <= 30; num++) {

            if (freq[num] == 0) continue;

            int mask = 0;
            boolean valid = true;

            for (int i = 0; i < 10; i++) {
                int p = primes[i];

                if (num % (p * p) == 0) {
                    valid = false;
                    break;
                }

                if (num % p == 0)
                    mask |= (1 << i);
            }

            if (!valid) continue;

            long[] next = dp.clone();

            for (int state = 0; state < (1 << 10); state++) {

                if ((state & mask) != 0) continue;

                next[state | mask] =
                        (next[state | mask] +
                        dp[state] * freq[num]) % MOD;
            }

            dp = next;
        }

        long ans = 0;

        for (int mask = 1; mask < (1 << 10); mask++)
            ans = (ans + dp[mask]) % MOD;

        long pow = 1;
        for (int i = 0; i < freq[1]; i++)
            pow = (pow * 2) % MOD;

        ans = (ans * pow) % MOD;

        return (int) ans;
    }
}