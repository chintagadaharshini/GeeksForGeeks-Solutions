class Solution {
    public int transform(String s1, String s2) {

        if (s1.length() != s2.length())
            return -1;

        int n = s1.length();

        // Check if both strings contain the same characters
        int[] freq = new int[256];

        for (char c : s1.toCharArray())
            freq[c]++;

        for (char c : s2.toCharArray())
            freq[c]--;

        for (int x : freq) {
            if (x != 0)
                return -1;
        }

        // Find the longest suffix of s2
        // that appears in s1 in the same order
        int i = n - 1;
        int j = n - 1;

        while (i >= 0 && j >= 0) {

            if (s1.charAt(i) == s2.charAt(j)) {
                i--;
                j--;
            } else {
                i--;
            }
        }

        // Characters before j+1 in s2
        // need to be moved to the front.
        return j + 1;
    }
}