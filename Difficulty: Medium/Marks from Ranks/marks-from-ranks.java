import java.util.*;

class Solution {
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {

        ArrayList<Integer> ans = new ArrayList<>();

        for (int k : rank) {

            int remaining = k;

            for (int i = 0; i < l.length; i++) {

                int count = r[i] - l[i] + 1;

                if (remaining <= count) {
                    int mark = l[i] + remaining - 1;
                    ans.add(mark);
                    break;
                }

                remaining -= count;
            }
        }

        return ans;
    }
}