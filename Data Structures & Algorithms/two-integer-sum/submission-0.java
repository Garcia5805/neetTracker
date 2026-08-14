class Solution {
    public int[] twoSum(int[] nums, int target) {
        int diff;
        Map<Integer, Integer> numbers = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++){
            diff = target - nums[i];
            if(numbers.containsKey(diff)){
                Integer index = numbers.get(diff);
                return new int[] {index, i};
            }
            numbers.put(nums[i], i);
        }


        return new int[] {};
        
    }
}
