class Solution {
    public int search(int[] nums, int target) {
        int r = nums.length - 1;
        int l = 0;
        int mid = 0;
        while(l<=r){
            mid = ((r-l)/2)+ l;

            System.out.println("mid= "+mid);
            System.out.println("l= " +l);
            System.out.println("r= "+r);

            if(nums[mid]==target){
                return mid;
            }
            else if(nums[mid] < target){
                l = mid+1;
            }
            else if(nums[mid] > target){
                r = mid-1;
            }

        }






        return -1;
    }
}
