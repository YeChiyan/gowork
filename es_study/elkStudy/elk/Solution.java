class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                // Minimum is in the right part
                left = mid + 1;
            } else {
                // Minimum is in the left part (including mid)
                right = mid;
            }
        }
        
        return nums[left];
    }
} 