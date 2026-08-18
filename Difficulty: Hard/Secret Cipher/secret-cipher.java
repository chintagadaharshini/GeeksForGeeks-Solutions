class Solution {
    public String compress(String s) {

        int n = s.length();

        // LPS array of KMP
        int[] lps = new int[n];

        for (int i = 1; i < n; i++) {

            int j = lps[i - 1];

            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = lps[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        StringBuilder ans = new StringBuilder();

        int i = n - 1;

        while (i >= 0) {

            // '*' can only replace a repeated string
            // whose total length is even.
            if (i % 2 == 1) {

                int len = i + 1;
                int prefix = lps[i];

                // Smallest repeating unit
                int unit = len - prefix;

                // The whole string is made of two equal halves
                if (prefix >= len / 2 &&
                    len % (2 * unit) == 0) {

                    ans.append('*');

                    // Move to the first half
                    i = i / 2;
                    continue;
                }
            }

            ans.append(s.charAt(i));
            i--;
        }

        return ans.reverse().toString();
    }
}