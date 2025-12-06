public class Solution {

    public String longestPalindrome(String s) {

        // Why this approach:
        // Palindromes expand outward from their center. Instead of checking every substring,
        // we grow from each possible center (odd + even). This keeps the logic simple,
        // avoids extra space, and runs in O(n^2) which is optimal for constraints (n ≤ 1000).
        // It’s the most practical solution clean, intuitive, and reliably accepted.

        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Expand around single center (odd length)
            int len1 = expand(s, i, i);

            // Expand around double center (even length)
            int len2 = expand(s, i, i + 1);

            int len = Math.max(len1, len2);

            // Update longest palindrome boundaries
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {
        // Expand while characters match and stay within bounds.
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // length of valid palindrome
    }
}
