class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // Why this approach:
        // Sorting + two pointer is the most practical way to solve 3Sum efficiently.
        // After sorting, fixing one number lets us reduce the rest of the problem
        // to a 2Sum search using two pointers (O(n)). This avoids brute force (O(n^3))
        // and naturally handles duplicates by skipping repeated values.
        // Overall complexity becomes O(n^2), which is optimal for n ≤ 3000.
        // Clean, predictable, and the standard accepted pattern for 3Sum.

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            // skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    // skip duplicates for the second number
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                }
            }
        }

        return res;
    }
}
