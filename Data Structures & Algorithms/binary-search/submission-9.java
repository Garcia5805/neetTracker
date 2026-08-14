class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l<=r){
            int mid = ((r-l)/2)+l;
            System.out.println(nums[l]+" "+nums[r]+" "+nums[mid]);
            if(target < nums[mid]){
                r = mid-1;
            }
            else if(target > nums[mid]){
                l = mid+1;
            }
            else if(target == nums[mid]){
                return mid;
            }
        }
        return -1;
    }
}
