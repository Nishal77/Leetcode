class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // Why this approach:
        // The problem demands O(log(m+n)), so merging or linear scans won't work.
        // We binary-search on the smaller array to find a partition where:
        //  max(left parts) <= min(right parts) across both arrays.
        // This transforms the median problem into a balanced-partition problem,
        // giving a predictable log-time solution that scales and avoids unnecessary work.
        
        int n1 = nums1.length, n2 = nums2.length;

        // Always binary-search the smaller array for stability.
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int total = n1 + n2;
        int leftSize = (total + 1) / 2; // size of left half in the final partition

        int low = 0, high = n1;

        while (low <= high) {
            int cut1 = (low + high) >> 1;        // partition in nums1
            int cut2 = leftSize - cut1;          // partition in nums2

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int r1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];

            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            // Correct partition found
            if (l1 <= r2 && l2 <= r1) {
                if (total % 2 == 1) 
                    return Math.max(l1, l2);
                
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }
            
            // Move binary search window
            else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return 0.0; // Not reached
    }
}
