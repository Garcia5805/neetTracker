class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int index = 0;
        while(l<=r){
            index = (r+l)/2 ;
            if (nums[index] == target){return index;}
            if(nums[index] < target){l = index+1;}
            else if(nums[index] > target){r = index-1;}
            
        }

        return -1;
    }
}
