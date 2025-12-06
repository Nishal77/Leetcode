class Solution {
    public int[] twoSum(int[] nums, int target) {
      
      // Check all pairs; j=i+1 avoids duplicates and same index.

        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[j]==target-nums[i]){
                 return new int[]{i, j};
                }
            }
        }
        return new int[]{};
}

}       
    
