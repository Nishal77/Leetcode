class Solution {
    Boolean[][] memo;  // memo[i][j] stores result for s[i:] and p[j:]

    public boolean isMatch(String s, String p) {

        // Why this approach:
        // Regex matching with '.' and '*' has branching choices:
        //   - '*' can mean "use it many times" OR "skip it entirely".
        // This creates overlapping subproblems. A pure recursive solution repeats work,
        // while a DP table gets bulky. So we use memoized recursion:
        //   -> clean, readable logic that follows the actual matching rules
        //   -> avoids recomputation with memo[i][j]
        //   -> guaranteed to pass given the small constraints (≤ 20 chars)
        // It fits perfectly because the problem is inherently recursive,
        // and memoization gives near-O(m*n) performance with minimal code complexity.

        memo = new Boolean[s.length() + 1][p.length() + 1];
        return match(0, 0, s, p);
    }

    private boolean match(int i, int j, String s, String p) {
        if (memo[i][j] != null) return memo[i][j];

        // pattern exhausted -> match only if string also fully consumed
        if (j == p.length())
            return memo[i][j] = (i == s.length());

        // current characters match if equal or pattern has '.'
        boolean firstMatch = (i < s.length() &&
                (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        // check if next char in pattern is '*'
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Two choices:
            // 1. Skip "x*" entirely
            // 2. Use "x*" (consume 1 char from s) if first char matches
            memo[i][j] = match(i, j + 2, s, p)
                         || (firstMatch && match(i + 1, j, s, p));
        } else {
            // Direct 1:1 match -> move both pointers if characters are valid
            memo[i][j] = firstMatch && match(i + 1, j + 1, s, p);
        }

        return memo[i][j];
    }
}
