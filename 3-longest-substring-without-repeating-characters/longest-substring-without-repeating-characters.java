public class Solution {
    public int lengthOfLongestSubstring(String s) {

        // Why this approach:
        // The sliding window pattern is ideal because we want the longest continuous range
        // without repeating characters. Instead of restarting work every time we see a 
        // duplicate (which would be O(n²)), we shrink the window only when needed and 
        // expand otherwise. The HashSet keeps track of which characters are currently in 
        // the window, giving O(1) duplicate checks. This leads to a clean O(n) solution 
        // that scales well even for large input sizes.

        int left = 0;
        int maxLength = 0;

        HashSet<Character> window = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // If a duplicate is found, shrink window until it's removed
            while (window.contains(current)) {
                window.remove(s.charAt(left));
                left++;
            }

            // Add current character to the valid window
            window.add(current);

            // Track longest window encountered
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
