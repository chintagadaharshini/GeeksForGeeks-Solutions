class Solution {
    static final long MOD = 1000000007L;

    public int prefixStrings(int n) {
        int max = 2 * n;

        long[] fact = new long[max + 1];
        long[] invFact = new long[max + 1];

        // Factorials
        fact[0] = 1;

        for (int i = 1; i <= max; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        // Inverse factorials
        invFact[max] = power(fact[max], MOD - 2);

        for (int i = max - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }

        // C(2n, n)
        long combination = fact[2 * n];

        combination = (combination * invFact[n]) % MOD;
        combination = (combination * invFact[n]) % MOD;

        // Catalan number = C(2n, n) / (n + 1)
        long answer = (combination * power(n + 1, MOD - 2)) % MOD;

        return (int) answer;
    }

    private long power(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % MOD;
            }

            a = (a * a) % MOD;
            b >>= 1;
        }

        return result;
    }
}