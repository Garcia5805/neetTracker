class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;

        while(l < r){
            int m = (l+r)/2;
            if(nums[l] < nums[m]) l = m;
            else if(nums[m] < nums[r]) r = m; 
            else break;
        }
        int left = binarySearch(nums, 0, l, target);
        int right = binarySearch(nums,r, nums.length-1, target);
        return Math.max(left,right);

    }
    private int binarySearch(int[] nums, int start, int end, int target){

        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) end = mid -1;
            else start = mid +1;
        }
        return -1;

    }
}
