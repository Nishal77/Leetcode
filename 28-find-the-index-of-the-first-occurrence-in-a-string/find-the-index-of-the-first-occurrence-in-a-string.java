class Solution {
    public int strStr(String haystack, String needle) {

        // Why this approach:
        // For substring search with length ≤ 10^4, a simple sliding window is the most 
        // practical and readable approach. It checks each possible starting position 
        // while avoiding unnecessary extra structures. Modern languages optimize similar 
        // logic under the hood (like Python's find). It's clean, efficient enough, and 
        // exactly what interviewers expect for this problem.

        int n = haystack.length();
        int m = needle.length();

        if (m > n) return -1;

        for (int i = 0; i <= n - m; i++) {
            if (haystack.regionMatches(i, needle, 0, m)) {
                return i;
            }
        }

        return -1;
    }
}
