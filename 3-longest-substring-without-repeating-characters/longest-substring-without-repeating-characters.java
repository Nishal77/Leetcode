public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;

        HashSet<Character> window = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // Shrink window if duplicate found
            while (window.contains(current)) {
                window.remove(s.charAt(left));
                left++;
            }

            // Add current character
            window.add(current);

            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}