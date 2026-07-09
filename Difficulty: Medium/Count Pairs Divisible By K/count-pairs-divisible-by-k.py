class Solution:
    def countKdivPairs(self, arr, k):
        remainder_count = [0] * k
        pairs = 0

        for number in arr:
            current_remainder = number % k

            required_remainder = (k - current_remainder) % k

            pairs += remainder_count[required_remainder]

            remainder_count[current_remainder] += 1

        return pairs