class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> lst = new HashMap<>();
        for(int i = 0; i < nums.length;i++){
            int comp = target - nums[i];
            if(lst.containsKey(comp)){
                return new int[] {lst.get(comp), i};
            }
            lst.put(nums[i], i);
        }
            
        return new int[] {};
    }
}
