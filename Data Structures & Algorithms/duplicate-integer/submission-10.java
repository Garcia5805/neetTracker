class Solution {
    public boolean hasDuplicate(int[] nums) {
        ArrayList<Integer> num = new ArrayList<>();
        for(int i = 0;i<nums.length;i++){
            if(!num.contains(nums[i])){
                num.add(nums[i]);
            }
            else{
                return true;
            }
        }
        return false;
    }
}