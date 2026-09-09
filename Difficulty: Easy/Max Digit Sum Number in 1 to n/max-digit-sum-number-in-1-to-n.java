class Solution {
    public int findMax(int n) {

        int best = n;
        int maxSum = digitSum(n);

        int temp = n;
        int place = 1;

        while (temp > 0) {
            int digit = temp % 10;

            if (digit > 0) {
                int candidate = (n / (place * 10)) * (place * 10)
                              + (digit - 1) * place
                              + (place - 1);

                int sum = digitSum(candidate);

                if (sum > maxSum || (sum == maxSum && candidate > best)) {
                    maxSum = sum;
                    best = candidate;
                }
            }

            temp /= 10;
            place *= 10;
        }

        return best;
    }

    private int digitSum(int x) {
        int sum = 0;

        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }

        return sum;
    }
}