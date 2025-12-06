class Solution {

    private void backtrack(
            int n,
            int row,
            char[][] board,
            List<List<String>> result,
            boolean[] cols,
            boolean[] diag1,
            boolean[] diag2
    ) {

        // Base case: all rows filled -> we found a valid board
        if (row == n) {
            List<String> config = new ArrayList<>();
            for (char[] r : board) {
                config.add(new String(r));
            }
            result.add(config);
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row - col + n;  // main diagonal index (shift to avoid negatives)
            int d2 = row + col;      // anti diagonal index

            // If queen conflicts in column or diagonals, skip this placement
            if (cols[col] || diag1[d1] || diag2[d2]) continue;

            // Choose: place queen
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;

            // Explore next row
            backtrack(n, row + 1, board, result, cols, diag1, diag2);

            // Undo / Backtrack
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }


    public List<List<String>> solveNQueens(int n) {

        // Why this approach:
        // N-Queens is a classic constraint-satisfaction problem.
        // Backtracking is the perfect pattern because:
        //  -> We build the board row by row, only exploring valid states.
        //  -> Conflict checking must be O(1), so we track used columns
        //     and diagonals with boolean arrays.
        //  -> This prunes the search tree aggressively, making the solution fast
        //    even for n = 9.
        //  -> The pattern is structured, predictable, and aligns exactly with the
        //    nature of the puzzle—place, validate, recurse, undo.

        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];
        for (char[] r : board) Arrays.fill(r, '.');

        boolean[] cols = new boolean[n];        // tracks which columns are used
        boolean[] diag1 = new boolean[2 * n];   // main diagonals
        boolean[] diag2 = new boolean[2 * n];   // anti diagonals

        backtrack(n, 0, board, result, cols, diag1, diag2);

        return result;
    }
}
