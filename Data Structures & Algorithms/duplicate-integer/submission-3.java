class Solution {
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> numbers = new HashSet<>();
        for(int i=0; i < nums.length; i++){
            if(!numbers.contains(nums[i])){
                numbers.add(nums[i]);
            }
            else{
                return true;
            }
        }
        return false;
    }
}
